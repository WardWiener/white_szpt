package com.taiji.pubsec.szpt.placemonitor.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.StatisticsInfoTwoValue;
import com.taiji.pubsec.szpt.placemonitor.service.IPlaceStatisticsService;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.solr.SolrHelper;

@Component("placeStatisticsSerivice")
public class PlaceStatisticsServiceImpl implements IPlaceStatisticsService {
	@Resource
	private Dao dao;
	@Resource
	private IUnitService unitService;
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	private static final Logger logger = LoggerFactory.getLogger(PlaceStatisticsServiceImpl.class);
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	
	@Override
	public List<StatisticsInfo> placeStatistics() {
		String xql = "select new " +  StatisticsInfo.class.getName() + "(t.areaDepartmentId, count(t.id)) from PlaceMonitor as t group by areaDepartmentId";
		List<StatisticsInfo> list = this.dao.findByXql(xql, new HashMap<String, Object>());
		for (StatisticsInfo statisticsInfo : list ) {
			statisticsInfo.setName(unitService.findById(statisticsInfo.getName()).getShortName());
		}
		
		return list;
	}


	@Override
	public List<StatisticsInfoTwoValue> wifiStatistics(Date startDay, Date endDay, List<String> pcscodes) {
		String start = df.format(startDay);
		String end = df.format(endDay);

		StringBuffer sb = new StringBuffer();
		sb.append("*:*");
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
		query.setFacetSort("count");
		query.set("facet.offset", 0);
		
		//查询这个时间段内的所有wifi监控的人数
		List<FacetField> facets = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(WifiStateRecordServiceImpl.COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		
		List<StatisticsInfoTwoValue> result = new ArrayList<StatisticsInfoTwoValue>();
		if( null == facets) {
			return result;
		}
		
		Map<String, StatisticsInfoTwoValue> placeStatistics = new HashMap<String, StatisticsInfoTwoValue>();
		for(FacetField facet : facets){
			if( !"placecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				placeStatistics.put(count.getName(), new StatisticsInfoTwoValue(count.getName(), String.valueOf(count.getCount()), "0"));
			}
		}
		
		//查询这个时间段内的所有wifi监控的人数(只是高危人员)
		sb.delete(0,sb.length());
		sb.append("*:*").append(" AND tag:").append("fivecolor");
		query.setQuery(sb.toString());
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(WifiStateRecordServiceImpl.COLLECTION_WIFI, query);
			facets = queryResponse.getFacetFields();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		
		for(FacetField facet : facets){
			if( !"placecode".equals(facet.getName()) ){
				continue;
			}
			for( Count count : facet.getValues() ){
				if(placeStatistics.containsKey(count.getName())) {
					placeStatistics.get(count.getName()).setValue_two(String.valueOf(count.getCount()));
				} else {
					placeStatistics.put(count.getName(), new StatisticsInfoTwoValue(count.getName(), "0", String.valueOf(count.getCount())));
				}
			}
		}
		
		
		//查询各个场所对应的所属派出所
		Map<String, StatisticsInfoTwoValue> unitStatistics = new HashMap<String, StatisticsInfoTwoValue>();
		for (StatisticsInfoTwoValue placeStatistic : placeStatistics.values() ) {
			if(!StringUtils.isEmpty(placeStatistic.getName())){
				PlaceBasicInfo placeBasicInfo = placeBasicInfoService.findByCode(placeStatistic.getName());
				//所属派出所非空
				if (placeBasicInfo != null && !StringUtils.isEmpty(placeBasicInfo.getAreaDepartmentId())) {
					String unitName = unitService.findById(placeBasicInfo.getAreaDepartmentId()).getShortName();
					String unitCode = unitService.findById(placeBasicInfo.getAreaDepartmentId()).getCode();
					if(pcscodes.contains(unitCode)) {
						if(!unitStatistics.containsKey(unitName)) {
							unitStatistics.put(unitName, new StatisticsInfoTwoValue(unitName, "0", "0"));
						}
						StatisticsInfoTwoValue unitStatistic = unitStatistics.get(unitName);
						int oldCount = Integer.parseInt(unitStatistic.getValue());
						int oldHrpCount = Integer.parseInt(unitStatistic.getValue_two());
						int placeCount = Integer.parseInt(placeStatistic.getValue());
						int placeHrpCount = Integer.parseInt(placeStatistic.getValue_two());
						unitStatistic.setValue(String.valueOf(oldCount+placeCount));
						unitStatistic.setValue_two(String.valueOf(oldHrpCount+placeHrpCount));
					}
				}
			}
		} 
		result.addAll(unitStatistics.values());
		return result;
	}


	

}
