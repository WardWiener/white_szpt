/**
 * Copyright 2017 Taiji
 * All right reserved.
 * Created on 2017年1月3日 上午10:42:42
 */
package com.taiji.pubsec.szpt.fullsearch.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

/**
 * @author yucy
 *
 */
@Service
public class FullTextSearchServiceImpl implements FullTextSearchService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FullTextSearchServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService#queryAlaram(java.util.Map)
	 */
	@Override
	public Pager<Map<String, Object>> queryAlaram(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer();
		
		String keyword = (String) conditions.get(SolrConstant.AlertSituation.text.getValue());
		if( null != keyword ){
			sb.append("text:").append(keyword);
		}
		String q = sb.toString();
		
		sb.delete(0,sb.length());
		Date answerTimeStart = (Date) conditions.get(SolrConstant.AlertSituation.answertime_start.getValue());
		Date answerTimeEnd = (Date) conditions.get(SolrConstant.AlertSituation.answertime_end.getValue());
		if( null != answerTimeStart && null != answerTimeEnd ){
			String start = SolrConstant.DF.format(answerTimeStart);
			String end = SolrConstant.DF.format(answerTimeEnd);
			sb.append("answertime:[").append(start).append(" TO ").append(end).append("]");
		}
		
		String type = (String) conditions.get(SolrConstant.AlertSituation.type.toString());
		if( null != type ){
			sb.append(" AND ").append("type:").append(type);
		}
		
		Date occurTimeStart = (Date) conditions.get(SolrConstant.AlertSituation.occurtime_start.getValue()); 
		Date occurTimeEnd = (Date) conditions.get(SolrConstant.AlertSituation.occurtime_end.getValue()); 
		if( null != occurTimeStart && null != occurTimeEnd ){
			String start = SolrConstant.DF.format(occurTimeStart);
			String end = SolrConstant.DF.format(occurTimeEnd);
			sb.append(" AND ").append("occurtime:[").append(start).append(" TO ").append(end).append("]");
		}
		
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		if ( pageNo >= 0 && pageSize > 0){
			query.setStart(pageNo * pageSize);
			query.setRows(pageSize);
		}
		LOGGER.trace("查询警情信息语句【{}】", query.toQueryString());
		
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_ALERT_SITUATION, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return pager;
		
		pager.setTotalNumber( list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			
			pager.getPageList().add(map);
		}
		
		return pager;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService#queryCase(java.util.Map)
	 */
	@Override
	public Pager<Map<String, Object>> queryCase(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer();
		
		String keyword = (String) conditions.get(SolrConstant.Case.text.getValue());
		if( null != keyword ){
			sb.append("text:").append(keyword);
		}
		String q = sb.toString();
		
		sb.delete(0,sb.length());
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		if ( pageNo >= 0 && pageSize > 0){
			query.setStart(pageNo * pageSize);
			query.setRows(pageSize);
		}
		LOGGER.trace("查询案件信息语句【{}】", query.toQueryString());
		
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_CASE, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return pager;
		
		pager.setTotalNumber( list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			
			pager.getPageList().add(map);
		}
		
		return pager;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService#queryPerson(java.util.Map)
	 */
	@Override
	public Pager<Map<String, Object>> queryPerson(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer();
		String keyword = (String) conditions.get(SolrConstant.Population.text.getValue());
		if( null != keyword ){
			sb.append(" AND ");
			sb.append("text:").append(keyword);
		}else{
			sb.append("*:*");
		}
		String q = sb.toString();
		
		sb.delete(0,sb.length());
		Date collectTimeStart = (Date) conditions.get(SolrConstant.Population.collecttime_start.getValue()); 
		Date collectTimeEnd = (Date) conditions.get(SolrConstant.Population.collecttime_end.getValue()); 
		if( null != collectTimeStart && null != collectTimeEnd ){
			String start = SolrConstant.DF.format(collectTimeStart);
			String end = SolrConstant.DF.format(collectTimeEnd);
			sb.append("collecttime:[").append(start).append(" TO ").append(end).append("]");
		}
		
		/**
		 * 人员类型
		 */
		List<String> personTypeCodes = (List<String>)conditions.get(SolrConstant.Population.persontypecode.getValue());
		if(personTypeCodes!=null && personTypeCodes.size()>0){
			if(sb.length()>0){
				sb.append(" AND ");
			}
			sb.append(SolrConstant.Population.persontypecode.getValue()).append(":") ;
			sb.append("(") ;
			for(int i=0; i<personTypeCodes.size(); i++){
				sb.append(personTypeCodes.get(i) + "*") ;
				if(personTypeCodes.size()>1 && i<(personTypeCodes.size()-1)){
					sb.append(" OR ");
				}
			}
			sb.append(")") ;
		}
		
		/**
		 * 预警类型
		 */
		List<String> alertTypes = (List<String>)conditions.get(SolrConstant.Population.alertlevel.getValue());
		if(alertTypes!=null && alertTypes.size()>0){
			
			if(sb.length()>0){
				sb.append(" AND ") ;
			}
			sb.append(SolrConstant.Population.alertlevel.getValue()).append(":") ;
			sb.append("(") ;
			for(int i=0; i<alertTypes.size(); i++){
				sb.append(alertTypes.get(i));
				if(alertTypes.size()>1 && i<(alertTypes.size()-1)){
					sb.append(" OR ") ;
				}
			}
			sb.append(")") ;
		}
		
		/**
		 * 是否是高危人
		 */
		String isHighRisk = (String)conditions.get(SolrConstant.Population.type.getValue()) ;
		if(StringUtils.isNotBlank(isHighRisk)){
			if(sb.length()>0){
				sb.append(" AND ") ;
			}
			sb.append(SolrConstant.Population.type.getValue()).append(":").append(isHighRisk) ;
		}
		
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		if ( pageNo >= 0 && pageSize > 0){
			query.setStart(pageNo * pageSize);
			query.setRows(pageSize);
		}
		LOGGER.trace("查询高危人信息语句【{}】", query.toQueryString());
		
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_POPULATION, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return pager;
		
		pager.setTotalNumber( list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			
			pager.getPageList().add(map);
		}
		
		return pager;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService#queryPlace(java.util.Map)
	 */
	@Override
	public Pager<Map<String, Object>> queryPlace(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer();
		
/*		String keyword = (String) conditions.get(SolrConstant.Place.text);
		if( null != keyword ){
			sb.append("text:").append(keyword);
		}
		String q = sb.toString();*/
		
		sb.delete(0,sb.length());
		Date collectTimeStart = (Date) conditions.get(SolrConstant.Place.collecttime_start.getValue()); 
		Date collectTimeEnd = (Date) conditions.get(SolrConstant.Place.collecttime_end.getValue()); 
		if( null != collectTimeStart && null != collectTimeEnd ){
			String start = SolrConstant.DF.format(collectTimeStart);
			String end = SolrConstant.DF.format(collectTimeEnd);
			sb.append("collecttime:[").append(start).append(" TO ").append(end).append("]");
		}
		
		String q = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		//query.setFilterQueries(fq);
		if ( pageNo >= 0 && pageSize > 0){
			query.setStart(pageNo * pageSize);
			query.setRows(pageSize);
		}
		LOGGER.trace("查询场所信息语句【{}】", query.toQueryString());
		
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_PLACE, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return pager;
		
		pager.setTotalNumber( list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			
			pager.getPageList().add(map);
		}
		
		return pager;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService#queryInstruction(java.util.Map)
	 */
	@Override
	public Pager<Map<String, Object>> queryInstruction(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuffer sb = new StringBuffer();
		
		sb.delete(0,sb.length());
		Date createTimeStart = (Date) conditions.get(SolrConstant.Instruction.createtime_start.getValue()); 
		Date createTimeEnd = (Date) conditions.get(SolrConstant.Instruction.createtime_end.getValue()); 
		if( null != createTimeStart && null != createTimeEnd ){
			String start = SolrConstant.DF.format(createTimeStart);
			String end = SolrConstant.DF.format(createTimeEnd);
			sb.append("createtime:[").append(start).append(" TO ").append(end).append("]");
		}
		
		String type = (String) conditions.get(SolrConstant.Instruction.type);
		if( null != type ){
			sb.append(" AND ").append("type:").append(type);
		}
		
		Date feedbacktimeStart = (Date) conditions.get(SolrConstant.Instruction.askfeedbacktime_start.getValue()); 
		Date feedbackTimeEnd = (Date) conditions.get(SolrConstant.Instruction.askfeedbacktime_end.getValue()); 
		if( null != feedbacktimeStart && null != feedbackTimeEnd ){
			String start = SolrConstant.DF.format(feedbacktimeStart);
			String end = SolrConstant.DF.format(feedbackTimeEnd);
			sb.append(" AND ").append("askfeedbacktime:[").append(start).append(" TO ").append(end).append("]");
		}

		String receiveUnit = (String) conditions.get(SolrConstant.Instruction.receiveunit.getValue());
		if( null != receiveUnit ){
			sb.append(" AND ").append("receiveunit:").append(receiveUnit);
		}
		
		String q = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);

		if ( pageNo >= 0 && pageSize > 0){
			query.setStart(pageNo * pageSize);
			query.setRows(pageSize);
		}
		LOGGER.trace("查询指令信息语句【{}】", query.toQueryString());
		
		Pager<Map<String, Object>> pager = new Pager<Map<String, Object>>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_INSTRUCTION, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return pager;
		
		pager.setTotalNumber( list.getNumFound() );
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			
			pager.getPageList().add(map);
		}
		
		return pager;
	}

}
