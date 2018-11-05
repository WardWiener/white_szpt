package com.taiji.pubsec.szpt.fullsearch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class WifiTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(WifiTest.class) ;
	
	@Resource
	private FullTextSearchService fullTextSearchService;
	
	@Test
	public void addHightPersonIndex(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String mac = "00-25-64-76-BD-40" ;
		String placecode = "123456" ;

		Date now = new Date() ;
		Calendar endCal = Calendar.getInstance() ;
		endCal.set(2017, 1, 1);
		
		String placename = "路边摊监控点1" ;
		String entertime = String.valueOf(now.getTime()) ;
		String leavetime = String.valueOf(endCal.getTime().getTime()) ; 
		String period = String.valueOf((endCal.getTime().getTime() - now.getTime())) ; 
		String phonenumber = "13112341234" ;
		String placeposition = "26.50601012194,106.67274835561507" ;
		String persontypecode = "017004000,017001001006,017004002,003001" ;
		String criminaltypecode = "017004001004010,017004001005000" ;
		String tag = "fivecolor" ;
		
		map.put(SolrConstant.WifiTrack.placecode.getValue(), placename);
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		map.put(SolrConstant.WifiTrack.entertime.getValue(), sdfdst.format(now));
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), sdfdst.format(endCal.getTime()));
		map.put(SolrConstant.WifiTrack.period.getValue(),  period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), phonenumber);
		map.put(SolrConstant.WifiTrack.placeposition.getValue(),  placeposition);
		map.put(SolrConstant.WifiTrack.persontypecode.getValue(),  persontypecode);
		map.put(SolrConstant.WifiTrack.criminaltypecode.getValue(), criminaltypecode);
		map.put(SolrConstant.WifiTrack.tag.getValue(), tag);
		
		String id = mac+placecode+entertime+leavetime ;
		
		map.put("id", id);
		
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
		
	}
	
	@Test
	public void testSearchPersons()throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(SolrConstant.WifiTrack.phonenumber.getValue() + ":" + "13112341234") ;
		String q = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.setQuery(q);
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		
		query.setStart(0);
		query.setRows(1000);

		queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_WIFI_TRACK, query);
		list = queryResponse.getResults();

		LOGGER.debug("总数：{}", list.getNumFound());
		LOGGER.debug("*****************当前页开始**********************");
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			LOGGER.debug("每行数据：{}", map.toString());
		}
		LOGGER.debug("*****************当前页结束**********************");
	}
	
	@Test
	public void testSearchPhoneOrMac()throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append(" 13112341234") ;
		String q = sb.toString();
		
		SolrQuery query = new SolrQuery();
		query.set("defType", "edismax");
		query.set("qf", "mac phonenumber");
		query.setQuery(q);
		
		SolrDocumentList list = null;
		QueryResponse queryResponse = null;
		
		String fq = "entertime:[2011-03-28T00:00:00Z TO 2019-03-28T17:06:35Z] OR leavetime:[2011-03-28T00:00:00Z TO 2019-03-28T17:06:35Z]" ;
		query.setFilterQueries(fq);

		queryResponse = SolrHelper.getInstance().getSolrClient().query(SolrConstant.COLLECTION_WIFI_TRACK, query);
		list = queryResponse.getResults();

		LOGGER.debug("总数：{}", list.getNumFound());
		LOGGER.debug("*****************当前页开始**********************");
		Iterator<SolrDocument> it = list.iterator();
		while(it.hasNext()){
			SolrDocument doc = it.next();
			Map<String, Object> map = doc.getFieldValueMap();
			LOGGER.debug("每行数据：{}", map.toString());
		}
		LOGGER.debug("*****************当前页结束**********************");
	}


	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
	
}
