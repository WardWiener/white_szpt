package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest.METHOD;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.bean.AjBean;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.HighriskPersonAnalyzeService;
import com.taiji.pubsec.szpt.placemonitor.service.impl.WifiStateRecordServiceImpl;
import com.taiji.pubsec.szpt.service.SzptDictionaryItemService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;
import com.taiji.pubsec.szpt.util.Constant.SHI_DUAN;
@Service("highriskPersonAnalyzeService")
public class HighriskPersonAnalyzeServiceImpl implements HighriskPersonAnalyzeService{
	private static final Logger logger = LoggerFactory.getLogger(WifiStateRecordServiceImpl.class);

	private final static String COLLECTION_WIFI = "wifitrack";
	private final static String COLLECTION_HOTEL = "accommodation";
	private final static String COLLECTION_CYBERCAFE = "cybercafe";
	private final static String COLLECTION_FLIGHT = "flight";
	private final static String COLLECTION_TRAIN = "trainticket";
	
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	@Resource
	private SzptDictionaryItemService szptDictionaryItemService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	private static long second = 1000;
	private static long minute = 60 * second;
	private static long hour = 60 * minute;

	private static int SOLR_STEP = 1000 ;
	
	@Override
	public List<AlarmInfo> findHPersonsCountByPeopleTypeByDayPart(
			Date startDay, Date endDay, List<String> peopleTypeCodes) {

		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
		
		//存放各子类型对应的一级类型的映射
		Map<String, String> persontypecodemap = new HashMap<>();
		
		List<String> ptCodes = new ArrayList<>() ;
		
		for(String pt : peopleTypeCodes){
			List<String> dts = szptDictionaryItemService.findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(pt, typeCode);
			ptCodes.addAll(dts) ;
			for(String dt : dts){
				persontypecodemap.put(dt, pt);
			}
		}

		Map<String, AlarmInfo> resultmap = new HashMap<>();

		List<String> batchlist = new ArrayList<>();
		for(String pt : ptCodes){
			batchlist.add(pt);
			if(batchlist.size()>=SOLR_STEP){
				this.findHPersonsCountByPeopleTypeByDayPartByPage(startDay, endDay, batchlist, resultmap, persontypecodemap);
				batchlist.clear();
			}
		}
		if(batchlist.size() > 0){
			this.findHPersonsCountByPeopleTypeByDayPartByPage(startDay, endDay, batchlist, resultmap, persontypecodemap);
		}

		AlarmInfo[] ais = new AlarmInfo[resultmap.size()];
		resultmap.values().toArray(ais);
		return Arrays.asList(ais);
	}

	private void findHPersonsCountByPeopleTypeByDayPartByPage(Date startDay, Date endDay, List<String> ptCodes, Map<String, AlarmInfo> resultmap, Map<String, String> persontypecodemap){

		String start = df.format(startDay);
		String end = df.format(endDay);

		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();

		if(ptCodes.size()>0){
			sb.append("persontypecode:(") ;
			sb.append(StringUtils.join(ptCodes, " OR ")) ;
			sb.append(")") ;
		}

		String persontypestr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得人员类型{}的全部子类型共耗时{}", ptCodes, translateTime(etime-stime) );

		populateDayPart(resultmap, SolrConstant.COLLECTION_WIFI_TRACK, persontypestr, start, end, persontypecodemap);
		populateDayPart(resultmap, SolrConstant.COLLECTION_ACCOMMODATION, persontypestr, start, end, persontypecodemap);
		populateDayPart(resultmap, SolrConstant.COLLECTION_CYBERCAFE, persontypestr, start, end, persontypecodemap);
		populateDayPart(resultmap, SolrConstant.COLLECTION_FLIGHT, persontypestr, start, end, persontypecodemap);
		populateDayPart(resultmap, SolrConstant.COLLECTION_TRAIN_TICKET, persontypestr, start, end, persontypecodemap);
	}
	
	/*
	 * 统计wifi/住宿/网吧/飞机的人员类型-时段数量
	 * resultmap用于存放不同类型不同时段的统计对象，key=一级类型代码+时段
	 */
	private void populateDayPart(Map<String, AlarmInfo> resultmap, String collection, String persontypestr, String start, String end, Map<String, String> persontypecodemap){
		//不同collection中开始时间和结束时间名称可能不同（火车没有结束时间），
		//根据不同情况来给开始时间字段名称赋值
		String entertime = "entertime";
		if( COLLECTION_FLIGHT.equals(collection) ){
			entertime = "takeofftime";
		}else if( COLLECTION_TRAIN.equals(collection) ){
			entertime = "starttime";
		}
		String leavetime = "leavetime";
		if( COLLECTION_FLIGHT.equals(collection) ){
			leavetime = "arriveattime";
		}else if( COLLECTION_TRAIN.equals(collection) ){
			leavetime = null;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(persontypestr);
		String q = sb.toString();
		sb.delete(0,sb.length());
		if( null == leavetime ){
			sb.append(entertime).append(":[").append(start).append(" TO ").append(end).append("]");
		}else{
			sb.append(entertime).append(":[").append(start).append(" TO ").append(end).append("]")
		      .append(" OR ").append(leavetime).append(":[").append(start).append(" TO ").append(end).append("]");
		}
		String fq = sb.toString();
		
		//通过facet的pivot+range搜索统计出每个类型搜索时间范围内每天8个时段的统计数据，
		//后面再将每天的数据合并成8个时段的数据
		SolrQuery query = new SolrQuery();
	
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setFacet(true);
		query.set("facet.range", "{!tag=r1}" + entertime);
		//将统计开始时段设置为当天的零点开始，这样保证8个时段的划分从0点开始
		query.set("facet.range.start", start + "/DAY");
		//将统计结束时段设置为当天的24点结束
		query.set("facet.range.end", end + "/DAY+1DAY");
		//间隔设置为3小时，保证结果为每天8个时段
		query.set("facet.range.gap", "+3HOUR");
		query.set("facet.pivot", "{!range=r1}persontypecode");
		query.setFacetSort("count");
		query.setRows(0);
		
		logger.debug("solr最终查询语句："+query.getQuery());
		
		NamedList<List<PivotField>> pivotranges = null;
		QueryResponse queryResponse = null;

		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(collection, query, METHOD.POST);
			pivotranges = queryResponse.getFacetPivot();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
		List<PivotField> pivotfields = pivotranges.get("persontypecode");
		for (PivotField pf : pivotfields) {
			//人员的类型代码，不一定是一级类型
			String rawpersontype = (String) pf.getValue();
			//将原始类型转换成对应的一级类型
			String persontype = persontypecodemap.get(rawpersontype);
			for(RangeFacet<?,?> rf : pf.getFacetRanges()){
				if( !entertime.equals( rf.getName() ) ){
					continue;
				}
				//获得类型下面的各范围统计对象
				List<RangeFacet.Count> counts = rf.getCounts();
				for (RangeFacet.Count fcount : counts) {
					//将solr的时间范围转换成业务所需的时段
					String rangename = getRange(fcount.getValue());
					if(null==rangename){
						logger.error("solr返回的时段{}不能映射到8个时段中的任何一个。", fcount.getValue());
						continue;
					}
					//通过key定义该原始solr统计属于哪个类型+时段的统计，便于后面的合并
					String key = persontype + rangename;
					AlarmInfo alarminfo = resultmap.get(key);
					if( null == alarminfo ){
						alarminfo = new AlarmInfo(fcount.getCount());
						alarminfo.setName(rangename);
						DictionaryItem dict = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, persontype, null);
						alarminfo.setNameAdd1(dict.getName());
						alarminfo.setNameCode(persontype);
						resultmap.put(key,alarminfo);
					}else{
						//合并solr分类下的时段统计结果
						alarminfo.setCount( alarminfo.getCount() + fcount.getCount() );
					}
				}
			}
		}
	}

	private String getRange(String raw){
		if(raw.endsWith("T00:00:00Z")){
			return SHI_DUAN.RANGE1.getKey();
		}
		if(raw.endsWith("T03:00:00Z")){
			return SHI_DUAN.RANGE2.getKey();
		}
		if(raw.endsWith("T06:00:00Z")){
			return SHI_DUAN.RANGE3.getKey();
		}
		if(raw.endsWith("T09:00:00Z")){
			return SHI_DUAN.RANGE4.getKey();
		}
		if(raw.endsWith("T12:00:00Z")){
			return SHI_DUAN.RANGE5.getKey();
		}
		if(raw.endsWith("T15:00:00Z")){
			return SHI_DUAN.RANGE6.getKey();
		}
		if(raw.endsWith("T18:00:00Z")){
			return SHI_DUAN.RANGE7.getKey();
		}
		if(raw.endsWith("T21:00:00Z")){
			return SHI_DUAN.RANGE8.getKey();
		}
		return null;
	}

	@Override
	public List<AjBean> findHPersonAj(Date startDay, Date endDay, String identy) {
		
		return null;
	}

	@Override
	public List<AlarmInfo> findHPersonsCountByPeopleTypeForInternetBar(Date startDay, Date endDay,
			List<String> peopleTypeCodes) {
		return findHPersonsCountByPeopleType(startDay, endDay, peopleTypeCodes, COLLECTION_CYBERCAFE, "网吧");
	}

	@Override
	public List<AlarmInfo> findHPersonsCountByPeopleTypeForHotel(Date startDay, Date endDay, List<String> peopleTypeCodes) {
		return findHPersonsCountByPeopleType(startDay, endDay, peopleTypeCodes, COLLECTION_HOTEL, "旅馆");
	}

	private List<AlarmInfo> findHPersonsCountByPeopleType(Date startDay, Date endDay, List<String> peopleTypeCodes, String collection, String collectionName) {

		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();

		//存放各子类型对应的查询类型的映射
		Map<String, String> persontypecodemap = new HashMap<>();
		long stime = System.currentTimeMillis();

		List<String> ptCodes = new ArrayList<>() ;

		StringBuffer sb = new StringBuffer();
		for(String pt : peopleTypeCodes){
			List<String> dts = szptDictionaryItemService.findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(pt, typeCode);
			ptCodes.addAll(dts) ;
			for(String dt : dts){
				persontypecodemap.put(dt, pt);
			}
		}

		Map<String, AlarmInfo> resultmap = new HashMap<>();

		List<String> batchlist = new ArrayList<>();
		for(String pt : ptCodes){
			batchlist.add(pt);
			if(batchlist.size()>=SOLR_STEP){
				this.findHPersonsCountByPeopleTypeBySolr(startDay, endDay, batchlist, resultmap, persontypecodemap, collection, collectionName);
				batchlist.clear();
			}
		}
		if(batchlist.size() > 0){
			this.findHPersonsCountByPeopleTypeBySolr(startDay, endDay, batchlist, resultmap, persontypecodemap, collection, collectionName);
		}

		AlarmInfo[] ais = new AlarmInfo[resultmap.size()];
		resultmap.values().toArray(ais);
		return Arrays.asList(ais);
	}
	
	private void findHPersonsCountByPeopleTypeBySolr(Date startDay, Date endDay, List<String> ptCodes, Map<String, AlarmInfo> resultmap, Map<String, String> persontypecodemap, String collection, String collectionName) {
		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
		
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		if(ptCodes.size()>0){
			sb.append("persontypecode:(") ;
			sb.append(StringUtils.join(ptCodes, " OR ")) ;
			sb.append(")") ;
		}
		
		String qureyptsstr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得人员类型{}的全部子类型共耗时{}", ptCodes, translateTime(etime-stime) );

		sb.delete(0,sb.length());
		sb.append(qureyptsstr);
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setFacet(true);
		query.addFacetField("persontypecode");
		query.setFacetSort("count");
		query.setRows(0);
		
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(collection, query, METHOD.POST);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		
		
		for(FacetField facet : facets){
			if( !"persontypecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				//获取该类型对应的原始请求类型
				String originaltype = persontypecodemap.get(count.getName());
				AlarmInfo ai = resultmap.get(originaltype);
				if( null == ai ){
					ai = new AlarmInfo((int) count.getCount());
					//DictionaryItem dict = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, originaltype, null);
					//ai.setName(dict.getName());
					ai.setNameCode(originaltype);
					resultmap.put(originaltype, ai);
				}else{
					ai.setCount((int) (ai.getCount() + count.getCount()));
				}
			}
		}
	}
	
	private String translateTime(long interval){
		long hours = interval / hour;
		long minutes = ( interval % hour ) / minute;
		long seconds = ( interval % second ) / second;
		long ms = ( interval % 1000 );
		
		return hours + "小时" + minutes + "分钟" + seconds + "秒" + ms + "毫秒";
	}

//	@Override
//	public List<AlarmInfo> findHPersonsCountByPeopleTypeByPlaceCodes(Date startDay, Date endDay,
//			List<String> placeCodes, List<String> peopleTypeCodes) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
