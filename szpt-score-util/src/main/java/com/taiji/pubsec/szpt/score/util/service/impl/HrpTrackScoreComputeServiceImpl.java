package com.taiji.pubsec.szpt.score.util.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.score.util.service.HrpTrackScoreComputeService;
import com.taiji.pubsec.szpt.solr.SolrHelper;

@Service("hrpScoreUtilTrackScoreComputeService")
public class HrpTrackScoreComputeServiceImpl implements HrpTrackScoreComputeService{
	private static final Logger logger = LoggerFactory.getLogger(HrpTrackScoreComputeServiceImpl.class);
	
	private final static String COLLECTION_FLIGHT = "flight";
	private final static String COLLECTION_TRAIN = "trainticket";
	private final static String COLLECTION_CYBERCAFE = "cybercafe";
	private final static String COLLECTION_HOTEL = "accommodation";
	
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

	@Override
	public Integer countGyFlyAndTrainTravelTimes(String identy, Date startDay, Date endDay) {
		return countFlyAboutGy(identy, startDay, endDay) + countTrainAboutGy(identy, startDay, endDay);
	}

	@Override
	public Long countInternetBarStayTime(String identy, Date startDay, Date endDay) {
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		StringBuffer sb = new StringBuffer();
		sb.append("idcard:").append(identy);
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.set("fl", "entertime,leavetime");
		logger.trace("查询网吧停留时间语句【{}】", query.toQueryString());
		
		Long staytime = 0L;

		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_CYBERCAFE, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list)
			return staytime;
		
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Date entertime = (Date) doc.getFieldValue("entertime");
			Date leavetime = (Date) doc.getFieldValue("leavetime");
			staytime += (leavetime.getTime() - entertime.getTime());
		}
		
		return staytime;
	}

	@Override
	public Long countHotelStayTime(String identy, Date startDay, Date endDay) {
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		StringBuffer sb = new StringBuffer();
		sb.append("idcard:").append(identy);
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("entertime:[").append(start).append(" TO ").append(end).append("]")
	      .append(" OR leavetime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.set("fl", "entertime,leavetime");
		logger.trace("查询酒店（住宿）停留时间语句【{}】", query.toQueryString());
		
		Long staytime = 0L;

		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_HOTEL, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list)
			return staytime;
		
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Date entertime = (Date) doc.getFieldValue("entertime");
			Date leavetime = (Date) doc.getFieldValue("leavetime");
			staytime += (leavetime.getTime() - entertime.getTime());
		}
		
		return staytime;
	}

	private Integer countFlyAboutGy(String identy, Date startDay, Date endDay){
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		StringBuffer sb = new StringBuffer();
		sb.append("idcard:").append(identy).append(" AND (takeoffairport:")
		    .append("贵阳").append(" OR arriveatairport:").append("贵阳)");
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("takeofftime:[").append(start).append(" TO ").append(end).append("]")
		    .append(" OR arriveattime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
			
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.set("fl", "id");
		query.setStart(0);
		//只是统计总数，所以不用返回数据
		query.setRows(1);
		logger.trace("查询飞机航班语句【{}】", query.toQueryString());
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_FLIGHT, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list)
			return 0;

		return (int) list.getNumFound();
	}
	

	private Integer countTrainAboutGy(String identy, Date startDay, Date endDay){
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		StringBuffer sb = new StringBuffer();
		sb.append("idcard:").append(identy).append(" AND (startstation:")
		    .append("贵阳").append(" OR arriveatstation:").append("贵阳)");
		String q = sb.toString();
		sb.delete(0,sb.length());
		sb.append("starttime:[").append(start).append(" TO ").append(end).append("]");
		String fq = sb.toString();
			
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		query.setFilterQueries(fq);
		query.set("fl", "id");
		query.setStart(0);
		//只是统计总数，所以不用返回数据
		query.setRows(1);
		logger.trace("查询火车语句【{}】", query.toQueryString());
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_TRAIN, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			logger.error("SolrServerException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		if( null == list)
			return 0;

		return (int) list.getNumFound();
	}

}
