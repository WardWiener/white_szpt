/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年12月21日 下午5:55:08
 */
package com.taiji.pubsec.szpt.placemonitor.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.FieldStatsInfo;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.StatsParams;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceCountBean;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStatInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStateRecord;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService;
import com.taiji.pubsec.szpt.service.SzptDictionaryItemService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

/**
 * @author yucy
 *
 */
@Service
public class WifiStateRecordServiceImpl implements WifiStateRecordService {
	private static final Logger logger = LoggerFactory.getLogger(WifiStateRecordServiceImpl.class);

	public final static String COLLECTION_WIFI = "wifitrack";
	
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	private static long second = 1000;
	private static long minute = 60 * second;
	private static long hour = 60 * minute;
	
	@Resource
	private SzptDictionaryItemService szptDictionaryItemService; 
	@Resource
	private WifiPlaceService wifiPlaceService;
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	
	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findByMacs(java.util.List, java.util.Date, java.util.Date, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Pager<WifiStateRecord> findByMacs(List<String> macs, Date startDay, Date endDay, Integer pageNo, Integer pageSize) {
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		StringBuffer sb = new StringBuffer();
		for(String mac : macs){
			sb.append("(");
			sb.append("mac:");
			sb.append(mac);
			sb.append(")");
		}
//		String qureymacstr = sb.toString();
//		
//		sb.delete(0,sb.length());
//		sb.append("mac:").append(qureymacstr);
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);

		boolean totalFlag = false;
		if (pageNo != null && pageNo >= 0 && pageSize != null && pageSize > 0){
			query.setStart(pageNo * pageSize);
			query.setRows(pageSize);
		}else{
			totalFlag = true ;
		}

		logger.trace("查询查询一定时间范围内mac地址轨迹信息语句【{}】", query.toQueryString());
		
		Pager<WifiStateRecord> pager = new Pager<WifiStateRecord>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			list = queryResponse.getResults();

			if(totalFlag){
				Long totalNum = list.getNumFound();
				query.setStart(0) ;
				query.setRows(totalNum.intValue()) ;
				queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
				list = queryResponse.getResults();
			}

		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list)
			return pager;
		
		pager.setTotalNumber( list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			WifiStateRecord wr = new WifiStateRecord();
			wr.setMac( (String) doc.getFieldValue("mac") );
			wr.setEnterTime( ((Date) doc.getFieldValue("entertime")).getTime() );
			wr.setLeaveTime( ((Date) doc.getFieldValue("leavetime")).getTime() );
			String placeCode = (String) doc.getFieldValue("placecode") ;
			wr.setPlaceCode(placeCode);
			String placeName = (String) doc.getFieldValue("placename") ;

			// 解析经纬度数据
			String location = (String) doc.getFieldValue("placeposition");
			if(StringUtils.isNotBlank(location)){
				String[] loc = location.split(",");
				wr.setLatitude( Double.valueOf(loc[0]) );
				wr.setLongitude( Double.valueOf(loc[1]) );
			}

			if(StringUtils.isBlank(placeName) || StringUtils.isBlank(location)){

				PlaceBasicInfo pbi = placeBasicInfoService.findByCode(placeCode) ;
				if(StringUtils.isBlank(placeName) && pbi!=null){
					placeName = pbi.getInternetServicePlaceName() ;

				}
				if(wr.getLongitude()==null && pbi!=null){
					wr.setLongitude(Double.valueOf(pbi.getLongitude()));
				}
				if(wr.getLatitude()==null && pbi!=null){
					wr.setLatitude(Double.valueOf(pbi.getLatitude()));
				}
			}

			wr.setPlaceName(placeName);
			wr.setStayInterval( (Long) doc.getFieldValue("period") );
			wr.setPhone( (String) doc.getFieldValue("phonenumber") );

			pager.getPageList().add(wr);
		}
		
		return pager;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findByMacs(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WifiStateRecord> findByMacs(List<String> macs, Date startDay, Date endDay) {
		return findByMacs(macs, startDay, endDay, null, null).getPageList();
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findByPersonType(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<PlaceCountBean> findByPersonType(List<String> personType, Date startDay, Date endDay) {
//		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
		
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for(String pt : personType){
//			List<String> dts = szptDictionaryItemService.findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(pt, typeCode);
//			for(String dt : dts){
//				if( sb.length() > 0){	
//					sb.append(" OR ");
//				}
//				sb.append("persontypecode:").append(dt);
//			}
			if( sb.length() > 0){	
				sb.append(" OR ");
			}
			sb.append("persontypecode:").append(pt).append("*");
		}
		String qureyptsstr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得人员类型{}的全部子类型共耗时{}", personType, translateTime(etime-stime) );
		
		sb.delete(0,sb.length());
		sb.append(qureyptsstr);
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		SolrQuery query1 = new SolrQuery();//拼装满足条件的wifi轨迹
		query1.set("defType", "edismax");
		query1.setQuery(q);
		query1.setFilterQueries(fq);
		
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setRows(0);
		query.set("stats", true);
		query.set("stats.field", "{!tag=piv1 sum=true}period");
		query.setFacet(true);
		query.set("facet.pivot", "{!stats=piv1}placecode");
		logger.trace("按照人员类型查询场所的出入数量统计语句【{}】", query.toQueryString());
		
		List<PlaceCountBean> result = new ArrayList<PlaceCountBean>();
		
		SolrDocumentList list = null;
		NamedList<List<PivotField>> pivotstats = null;
		QueryResponse queryResponse = null;
		QueryResponse queryResponse1 = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			pivotstats = queryResponse.getFacetPivot();
			
			//查询所有满足条件的wifi轨迹点
			queryResponse1 = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query1);
			list = queryResponse1.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}		
		List<PivotField> pivotfields = pivotstats.get("placecode");
		if(null != pivotfields && pivotfields.size() > 0){
			for (PivotField pf : pivotfields) {
				//监控点名称
				String placecode = (String) pf.getValue();
				//出入数量统计
				int count = pf.getCount();
				
				PlaceCountBean placecount = new PlaceCountBean();
				placecount.setCount(count);
				placecount.setPlaceCode(placecode);
				
				//拼装mac集合
				Iterator<SolrDocument> it = list.iterator();
				if(null != list){
					while(it.hasNext()){
						SolrDocument doc = it.next();
						if(placecode.equals(doc.getFieldValue(SolrConstant.WifiTrack.placecode.getValue()))){
							//拼装集合
							Object macObj = doc.getFieldValue(SolrConstant.WifiTrack.mac.getValue());
							if(macObj != null){
								placecount.getMacs().add(macObj.toString());
							}
						}
					}
				}
				
				for(Entry<String, FieldStatsInfo> entry : pf.getFieldStatsInfo().entrySet()){
					if("period".equals(entry.getKey())){
						//出入时间总和统计
						long sum = ((Double) entry.getValue().getSum()).longValue();
						placecount.setStayTime(sum);
						break;
					}
				}
				result.add(placecount);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findByMacsByTopPlaceAndTimes(java.util.List, java.util.Date, java.util.Date, java.lang.Integer)
	 */
	@Override
	public List<PlaceCountBean> findByMacsByTopPlaceAndTimes(List<String> macs, Date startDay, Date endDay, Integer descNum) {
		String start = df.format(startDay);
		String end = df.format(endDay);

		StringBuffer sb = new StringBuffer();
		for(String mac : macs){
			sb.append("(");
			sb.append("mac:");
			sb.append(mac);
			sb.append(")");
		}

		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
//		mac, //mac地址
//		placecode,//场所编码
//		placename,//场所名称
//		entertime,//进入时间
//		leavetime,//离开时间
//		phonenumber,//手机号
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setFacet(true);
		query.addFacetField("placecode"); 
		query.setFacetSort("count");
		query.set("facet.offset", 0);
		query.setFacetLimit(descNum);
		logger.trace("查询出现高危人次数最多的前{}个场所语句【{}】", descNum, query.toQueryString());
		
		List<PlaceCountBean> result = new ArrayList<>();
		
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == facets)
			return result;
		
		for(FacetField facet : facets){
			if( !"placecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				int ct = (int) count.getCount() ;
				if(ct<=0){
					continue;
				}
				PlaceCountBean pcb = new PlaceCountBean();
	    		pcb.setPlaceCode(count.getName());
	    		pcb.setCount( ct );
	    		result.add(pcb);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findByMacsByTopAndStayeTime(java.util.List, java.util.Date, java.util.Date, java.lang.Integer)
	 */
	@Override
	public List<PlaceCountBean> findByMacsByTopAndStayeTime(List<String> macs, Date startDay, Date endDay, Integer descNum) {
		String start = df.format(startDay);
		String end = df.format(endDay);

		StringBuffer sb = new StringBuffer();
		for(String mac : macs){
			sb.append("(");
			sb.append("mac:");
			sb.append(mac);
			sb.append(")");
		}

		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		

//			mac, //mac地址
//			placecode,//场所编码
//			placename,//场所名称
//			entertime,//进入时间
//			leavetime,//离开时间
//			phonenumber,//手机号

		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setParam(StatsParams.STATS, true);
		query.setParam(StatsParams.STATS_FIELD, "period");
		query.setParam(StatsParams.STATS_FACET, "placecode");
		query.setRows(0);
		logger.trace("查询高危人停留总时长最长的前{}个场所语句【{}】", descNum, query.toQueryString());

		List<PlaceCountBean> result = new ArrayList<>();
		
		Map<String, FieldStatsInfo> fieldstatsinfo = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			fieldstatsinfo = queryResponse.getFieldStatsInfo();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( null == fieldstatsinfo)
			return result;
		
		FieldStatsInfo periodstatis = fieldstatsinfo.get("period");
    	List<FieldStatsInfo> stats = periodstatis.getFacets().get("placecode");
    	for(FieldStatsInfo stat : stats){
    		PlaceCountBean pcb = new PlaceCountBean();
    		pcb.setPlaceCode(stat.getName());
    		pcb.setCount(stat.getCount().intValue());
    		pcb.setStayTime(((Double)stat.getSum()).longValue());
    		result.add(pcb);
    	}
    	
    	Collections.sort(result, new Comparator<PlaceCountBean>() {

			@Override
			public int compare(PlaceCountBean o1, PlaceCountBean o2) {
				return (int) (o2.getStayTime() - o1.getStayTime());
			}

		});
    	
    	if(result.size() > descNum) {
    		return result.subList(0, descNum);
    	} 
    	return result;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findDetailByPlaceByMacs(java.util.List, java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WifiStateRecord> findDetailByPlaceByMacs(List<String> macs, List<String> placeCode, Date startDay, Date endDay) {
		String start = df.format(startDay);
		String end = df.format(endDay);

		StringBuffer sb = new StringBuffer();
		for(String mac : macs){
			if( sb.length() > 0){	
				sb.append(" OR ");
			}
			sb.append("mac:").append(mac);
		}
		String qureymacstr = sb.toString();
		
		sb.delete(0,sb.length());
		for(String place : placeCode){
			if( sb.length() > 0){	
				sb.append(" OR ");
			}
			sb.append("placecode:").append(place);
		}
		String queryplacestr = sb.toString();
		
		sb.delete(0,sb.length());
		sb.append("(").append(qureymacstr).append(") AND (").append(queryplacestr).append(")") ;
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setSort("placecode", ORDER.asc);

		List<WifiStateRecord> result = new ArrayList<>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if( null == list )
			return result;
	
//		System.out.println("总数：" + list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			WifiStateRecord wsr = new WifiStateRecord();
			wsr.setMac( (String) doc.getFieldValue("mac") );
			wsr.setEnterTime( ((Date) doc.getFieldValue("entertime")).getTime() );
			wsr.setLeaveTime( ((Date) doc.getFieldValue("leavetime")).getTime() );
			wsr.setStayInterval( (Long) doc.getFieldValue("period") );
			wsr.setPhone( (String) doc.getFieldValue("phonenumber") );

			String plcCode = (String) doc.getFieldValue("placecode") ;
			wsr.setPlaceCode(plcCode);
			String placeName = (String) doc.getFieldValue("placename") ;

			// 解析经纬度数据
			String location = (String) doc.getFieldValue("placeposition");
			if(StringUtils.isNotBlank(location)){
				String[] loc = location.split(",");
				wsr.setLatitude( Double.valueOf(loc[0]) );
				wsr.setLongitude( Double.valueOf(loc[1]) );
			}

			if(StringUtils.isBlank(placeName) || StringUtils.isBlank(location)){

				PlaceBasicInfo pbi = placeBasicInfoService.findByCode(plcCode) ;
				if(StringUtils.isBlank(placeName) && pbi!=null){
					placeName = pbi.getInternetServicePlaceName() ;

				}
				if(wsr.getLongitude()==null && pbi!=null){
					wsr.setLongitude(Double.valueOf(pbi.getLongitude()));
				}
				if(wsr.getLatitude()==null && pbi!=null){
					wsr.setLatitude(Double.valueOf(pbi.getLatitude()));
				}
			}

			wsr.setPlaceName(placeName);

			result.add(wsr);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findStatCountByPcs(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WifiStatInfo> findStatCountByPcs(List<String> pcsCodes, Date startDay, Date endDay) {
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		//存放wifi场所与所属派出所的映射，以便后面合并同派出所数据的时候使用
		Map<String, String> placepcs = new HashMap<>();
		
		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for(String pcscode : pcsCodes){
			List<String> wifiplaces = wifiPlaceService.findWifiPlaceCodesByPcsCode(pcscode);
			for(String wifiplace : wifiplaces){
				placepcs.put(wifiplace, pcscode);
				if( sb.length() > 0){	
					sb.append(" ");
				}
				sb.append(wifiplace);
			}
		}
		String qureywifiplacecodestr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得派出所{}的全部wifi场所共耗时{}", pcsCodes, translateTime(etime-stime) );
		
		sb.delete(0,sb.length());
		sb.append("placecode:").append(qureywifiplacecodestr);
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
		query.addFacetField("placecode");
		query.setRows(0);
//		query.setFacetSort("count");
		logger.trace("按照派出所查询wifi的采集数量语句【{}】", query.toQueryString());
		
		List<WifiStatInfo> result = new ArrayList<>();
		Map<String, WifiStatInfo> map = new HashMap<>();
		
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == facets)
			return result;
		
		for(FacetField facet : facets){
			if( !"placecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				//获得该wifi场所的派出所代码
				String pcscode = placepcs.get(count.getName());
				// 获得派出所的统计对象
				WifiStatInfo wsi = map.get(pcscode);
				if( null == wsi ){
					wsi = new WifiStatInfo();
					wsi.setKey(pcscode);
					wsi.setCount( (int) count.getCount() );
					map.put(count.getName(), wsi);
				}else{
					wsi.setCount( wsi.getCount() + (int) count.getCount() );
				}
			}
		}
		WifiStatInfo[] wsis = (WifiStatInfo[]) map.values().toArray();
		
		return Arrays.asList(wsis);
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService#findStateByPcsByHPersonColor(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<WifiStatInfo> findStateByPcsByHPersonColor(List<String> pcsCodes, Date startDay, Date endDay) {
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		//存放wifi场所与所属派出所的映射，以便后面合并同派出所数据的时候使用
		Map<String, String> placepcs = new HashMap<>();
		
		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for(String pcscode : pcsCodes){
			List<String> wifiplaces = wifiPlaceService.findWifiPlaceCodesByPcsCode(pcscode);
			for(String wifiplace : wifiplaces){
				placepcs.put(wifiplace, pcscode);
				if( sb.length() > 0){	
					sb.append(" ");
				}
				sb.append(wifiplace);
			}
		}
		String qureywifiplacecodestr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得派出所{}的全部wifi场所共耗时{}", pcsCodes, translateTime(etime-stime) );
		
		sb.delete(0,sb.length());
		sb.append("placecode:").append(qureywifiplacecodestr).append(" AND tag:").append("fivecolor");
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
		query.addFacetField("placecode");
		query.setRows(0);
//		query.setFacetSort("count");
		logger.trace("按照派出所查询wifi的采集数量语句【{}】", query.toQueryString());
		
		List<WifiStatInfo> result = new ArrayList<>();
		Map<String, WifiStatInfo> map = new HashMap<>();
		
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == facets)
			return result;
		
		for(FacetField facet : facets){
			if( !"placecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				//获得该wifi场所的派出所代码
				String pcscode = placepcs.get(count.getName());
				// 获得派出所的统计对象
				WifiStatInfo wsi = map.get(pcscode);
				if( null == wsi ){
					wsi = new WifiStatInfo();
					wsi.setKey(pcscode);
					wsi.setCount( (int) count.getCount() );
					map.put(count.getName(), wsi);
				}else{
					wsi.setCount( wsi.getCount() + (int) count.getCount() );
				}
			}
		}
		WifiStatInfo[] wsis = (WifiStatInfo[]) map.values().toArray();
		
		return Arrays.asList(wsis);
	}

	@Override
	public List<WifiStatInfo> findCountByPersonTypes(List<String> personTypes, Date startDay, Date endDay) {
		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
		
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for(String pt : personTypes){
			List<String> dts = szptDictionaryItemService.findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(pt, typeCode);
			for(String dt : dts){
				if( sb.length() > 0){	
					sb.append(" OR ");
				}
				sb.append("persontypecode:").append(dt);
			}
		}
		String qureyptsstr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得人员类型{}的全部子类型共耗时{}", personTypes, translateTime(etime-stime) );
		
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
		logger.trace("按照时间范围查询某一组人员类别wifi轨迹数量的记录语句【{}】", query.toQueryString());
		
		List<WifiStatInfo> result = new ArrayList<>();
		
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == facets)
			return result;
		
		for(FacetField facet : facets){
			if( !"persontypecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				WifiStatInfo wsi = new WifiStatInfo();
				wsi.setKey(count.getName());
	    		wsi.setCount( (int) count.getCount() );
	    		result.add(wsi);
			}
		}
		
		return result;
	}

	@Override
	public List<WifiStateRecord> findByPlaces(String placeCode, Date startDay, Date endDay) {
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		StringBuffer sb = new StringBuffer();
		String qureystr = placeCode;
		
		sb.append("placecode:").append(qureystr);
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.setSort("entertime", ORDER.asc);
		logger.trace("按照时间范围查询某个场所的wifi记录语句【{}】", query.toQueryString());
		
		List<WifiStateRecord> result = new ArrayList<WifiStateRecord>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list)
			return result;
		
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			WifiStateRecord wr = new WifiStateRecord();
			wr.setMac( (String) doc.getFieldValue("mac") );
			wr.setEnterTime( ((Date) doc.getFieldValue("entertime")).getTime() );
			wr.setLeaveTime( ((Date) doc.getFieldValue("leavetime")).getTime() );
			wr.setStayInterval( (Long) doc.getFieldValue("period") );
			wr.setPhone( (String) doc.getFieldValue("phonenumber") );

			String plcCode = (String) doc.getFieldValue("placecode") ;
			wr.setPlaceCode(plcCode);
			String placeName = (String) doc.getFieldValue("placename") ;

			// 解析经纬度数据
			String location = (String) doc.getFieldValue("placeposition");
			if(StringUtils.isNotBlank(location)){
				String[] loc = location.split(",");
				wr.setLatitude( Double.valueOf(loc[0]) );
				wr.setLongitude( Double.valueOf(loc[1]) );
			}

			if(StringUtils.isBlank(placeName) || StringUtils.isBlank(location)){

				PlaceBasicInfo pbi = placeBasicInfoService.findByCode(plcCode) ;
				if(StringUtils.isBlank(placeName) && pbi!=null){
					placeName = pbi.getInternetServicePlaceName() ;

				}
				if(wr.getLongitude()==null && pbi!=null){
					wr.setLongitude(Double.valueOf(pbi.getLongitude()));
				}
				if(wr.getLatitude()==null && pbi!=null){
					wr.setLatitude(Double.valueOf(pbi.getLatitude()));
				}
			}

			wr.setPlaceName(placeName);
			
			result.add(wr);
		}

		return result;
	}

	@Override
	public List<WifiStatInfo> findByPlaces(String placeCode, List<String> personTypes, Date startDay, Date endDay) {
//		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
		
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		long stime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for(String pt : personTypes){
//			List<String> dts = szptDictionaryItemService.findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(pt, typeCode);
//			for(String dt : dts){
//				if( sb.length() > 0){	
//					sb.append(" OR ");
//				}
//				sb.append("persontypecode:").append(dt);
//			}
			if( sb.length() > 0){	
				sb.append(" OR ");
			}
			sb.append("persontypecode:").append(pt).append("*");
		}
		String qureyptsstr = sb.toString();
		long etime =  System.currentTimeMillis();
		logger.trace( "获得人员类型{}的全部子类型共耗时{}", personTypes, translateTime(etime-stime) );
		
		sb.delete(0,sb.length());
		sb.append("(").append(qureyptsstr).append(")").append(" AND placecode:").append(placeCode);
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
		logger.trace("按照时间范围查询某一组人员类别wifi轨迹数量的记录语句【{}】", query.toQueryString());
		
		List<WifiStatInfo> result = new ArrayList<>();
		
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == facets)
			return result;
		
		for(FacetField facet : facets){
			if( !"persontypecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				if(isTargetPersonType(personTypes, count.getName())) { //只需要传入类型对应的数量
					WifiStatInfo wsi = new WifiStatInfo();
					wsi.setKey(count.getName());
					wsi.setCode(count.getName());
					wsi.setCount( (int) count.getCount() );
					result.add(wsi);
				}
			}
		}
		
		return result;
	}
	
	private boolean isTargetPersonType(List<String> personTypeCodes, String hitCode) {
		boolean result = false;
		for (String requestCode : personTypeCodes) {
			if(hitCode.startsWith(requestCode)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	private String translateTime(long interval){
		long hours = interval / hour;
		long minutes = ( interval % hour ) / minute;
		long seconds = ( interval % second ) / second;
		long ms = ( interval % 1000 );
		
		return hours + "小时" + minutes + "分钟" + seconds + "秒" + ms + "毫秒";
	}

}
