package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.WifiMonitorPointBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseAnalysisService;
import com.taiji.pubsec.szpt.solr.SolrHelper;

@Service("caseAnalysisService")
public class CaseAnalysisServiceImpl implements CaseAnalysisService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseAnalysisServiceImpl.class);
	
	private final static String COLLECTION_WIFIPOINT = "wifipoint";
	private final static String COLLECTION_WIFI = "wifitrack";

	
	private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	@Override
	public List<WifiMonitorPointBean> findWifiMonitorPoint(String longitude, String latitude, int scope) {
		String location = latitude + "," + longitude;
		double scopeInKm = (double)scope / 1000;
		LOGGER.debug("scope in km is {}", scopeInKm);
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.setFilterQueries("{!geofilt pt=" + location + " sfield=position d=" + scopeInKm + "}"); //距离转换成公里
		
		List<WifiMonitorPointBean> result = new ArrayList<WifiMonitorPointBean>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFIPOINT, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return result;

		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			WifiMonitorPointBean wmpb = new WifiMonitorPointBean();
			wmpb.setCode( (String) doc.getFieldValue("id") );
			wmpb.setName( (String) doc.getFieldValue("name") );
			// 解析经纬度数据
			String pos = (String) doc.getFieldValue("position");
			String[] loc = pos.split(",");
			wmpb.setLatitude( loc[0] );
			wmpb.setLongitude( loc[1] );
			
			result.add(wmpb);
		}
		
		return result;
	}

	@Override
	public Map<String,Set<String>> findCommonMac(List<Map<String,Object>> searchConditions) {
		Map<String,Set<String>> result = new HashMap<>();
		//存放每个wifi点命中的mac
		Map<String, Set<String>> casemap = new HashMap<>();
		
		for(Map<String, Object> cond : searchConditions){
			String wifipointcode = (String) cond.get(KEY_WIFIPOINTCODE);
			String casecode = (String) cond.get(KEY_CASECODE);
			Date startDay = (Date) cond.get(KEY_FROMDATE);
			Date endDay = (Date) cond.get(KEY_TODATE);
			Set<String> macs = getMacsFromOnePoint(startDay, endDay, wifipointcode);
			if(casemap.get(casecode) == null) {
				casemap.put(casecode, macs);
			} else {
				casemap.get(casecode).addAll(macs);
			}
		}
		
		//转换成mac都在哪些wifi点对应的案件出现过
		for(Entry<String, Set<String>> entry : casemap.entrySet()){
			String wp = entry.getKey();
			Set<String> macs = entry.getValue();
			for(String mac : macs){
				Set<String> wps = result.get(mac);
				if( null == wps ){
					wps = new HashSet<>();
					result.put(mac, wps);
				}
				wps.add(wp);
			}
		}
		
		return result;
	}

	private Set<String> getMacsFromOnePoint(Date startDay, Date endDay, String wifiPointCode){
		String start = df.format(startDay);
		String end = df.format(endDay);
		
		String qureystr = wifiPointCode;
		
		StringBuffer sb = new StringBuffer();
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
		query.set("fl", "mac");
		LOGGER.trace("查找在时间范围内某个wifi监控点的mac地址信息语句【{}】", query.toQueryString());

		Set<String> result = new HashSet<>();
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		try {
			queryResponse = SolrHelper.getInstance().getSolrClient().query(COLLECTION_WIFI, query);
			list = queryResponse.getResults();
		} catch (SolrServerException e) {
			LOGGER.error("SolrServerException", e);
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		}
		if( null == list)
			return result;
		
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			result.add( (String) doc.getFieldValue("mac") );
		}
		
		return result;
	}
}
