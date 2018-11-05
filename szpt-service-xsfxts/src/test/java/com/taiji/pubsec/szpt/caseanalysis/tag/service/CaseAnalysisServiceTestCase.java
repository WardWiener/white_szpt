package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.WifiMonitorPointBean;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CaseAnalysisServiceTestCase extends TestBase {

	@Resource
	private CaseAnalysisService caseAnalysisService;
	
//	@SuppressWarnings("rawtypes")
//	@Resource
//	private Dao dao;
//	
	
	@Test
	public void findMonitorPoints() {
		System.setProperty("solr.zkhost", "192.168.19.131:2181,192.168.19.132:2181,192.168.19.133:2181/solr");
		List<WifiMonitorPointBean> wps = caseAnalysisService.findWifiMonitorPoint("106.68188667231492", "26.508364708391916", 500);
		Assert.assertEquals(3, wps.size());
		
		wps = caseAnalysisService.findWifiMonitorPoint("106.69298335345276", "26.507230119644976", 500);
		Assert.assertEquals(3, wps.size());
		
		wps = caseAnalysisService.findWifiMonitorPoint("106.69543200743443", "26.50004480909763", 500);
		Assert.assertEquals(2, wps.size());
	}
	
	@Test
	public void findFindCommonMac() throws ParseException {
		System.setProperty("solr.zkhost", "192.168.19.131:2181,192.168.19.132:2181,192.168.19.133:2181/solr");
		List<Map<String,Object>> params = new ArrayList<Map<String,Object>>();
		Date case1StartTime = DateUtils.parseDate("2016-09-10 22:09:14", "yyyy-MM-dd HH:mm:ss");
		Date case1EndTime = DateUtils.parseDate("2016-09-11 04:09:14", "yyyy-MM-dd HH:mm:ss");
		params.add(produceMapParam("52010421001733", case1StartTime, case1EndTime));
		params.add(produceMapParam("52010426002101", case1StartTime, case1EndTime));
		params.add(produceMapParam("5201042D002098", case1StartTime, case1EndTime));
		Date case2StartTime = DateUtils.parseDate("2016-08-26 04:03:51", "yyyy-MM-dd HH:mm:ss");
		Date case2EndTime = DateUtils.parseDate("2016-08-26 10:03:51", "yyyy-MM-dd HH:mm:ss");
		params.add(produceMapParam("52011425000010", case2StartTime, case2EndTime));
		params.add(produceMapParam("5201142B000011", case2StartTime, case2EndTime));
		params.add(produceMapParam("5201042A004393", case2StartTime, case2EndTime));
		Date case3StartTime = DateUtils.parseDate("2016-09-09 12:30:44", "yyyy-MM-dd HH:mm:ss");
		Date case3EndTime = DateUtils.parseDate("2016-09-09 18:30:44", "yyyy-MM-dd HH:mm:ss");
		params.add(produceMapParam("52010421004395", case3StartTime, case3EndTime));
		Map<String,Set<String>> result = caseAnalysisService.findCommonMac(params);
		
		Assert.assertEquals(2, result.size());
	}

	private Map<String, Object> produceMapParam(String wificode, Date start, Date end) {
		Map<String, Object> wp = new HashMap<String, Object>();
		wp.put("wifiPointCode", wificode);
		wp.put("fromDate", start);
		wp.put("toDate", end);
		return wp;
	}
}
