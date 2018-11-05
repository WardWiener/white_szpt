package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.ResultBean;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.TagCountResultBean;
import com.taiji.pubsec.szpt.test.TestBase;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CaseStatisticServiceTestCase extends TestBase {

	@Resource
	private CaseStatisticService caseStatisticService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countByTagOccurPlace-setup.xml")
	public void countByTagOccurPlace() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("fromDate", sdf.parse("2010-10-12 10:10:10"));
		conditions.put("toDate", sdf.parse("2010-10-22 10:10:10"));
		conditions.put("type", "ajlb");
		conditions.put("firstSort", "ajxzyj");
		conditions.put("secondSort", "ajxzej");
		List<TagCountResultBean> resultBeans = caseStatisticService.countByTagOccurPlace(conditions);
		Assert.assertEquals(3, resultBeans.size());
		Assert.assertEquals("-", resultBeans.get(2).getProportion());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countByTagCaseFeature-setup.xml")
	public void countByTagCaseFeature() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("fromDate", sdf.parse("2010-10-12 10:10:10"));
		conditions.put("toDate", sdf.parse("2010-10-22 10:10:10"));
		conditions.put("type", "ajlb");
		conditions.put("firstSort", "ajxzyj");
		conditions.put("secondSort", "ajxzej");
		//List<TagCountResultBean> resultBeans = caseStatisticService.countByTagCaseFeature(conditions);
/*		Assert.assertEquals(3, resultBeans.size());
		Assert.assertEquals("-", resultBeans.get(2).getProportion());*/
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countByTagCasePeriod-setup.xml")
	public void countByTagCasePeriod() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> conditions = new HashMap<String, Object>();
		conditions.put("fromDate", sdf.parse("2010-10-12 10:10:10"));
		conditions.put("toDate", sdf.parse("2010-10-22 10:10:10"));
		conditions.put("type", "ajlb");
		conditions.put("firstSort", "ajxzyj");
		conditions.put("secondSort", "ajxzej");
/*		List<TagCountResultBean> resultBeans = caseStatisticService.countByTagCasePeriod(conditions);
		Assert.assertEquals(3, resultBeans.size());
		Assert.assertEquals("-", resultBeans.get(2).getProportion());*/
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countRipoffCaseByRegion-setup.xml")
	public void countRipoffCaseByRegion() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Integer>> results = caseStatisticService.countRipoffCaseByRegion("pcs1", sdf.parse("2010-10-5 10:10:10"), sdf.parse("2010-10-25 10:10:10"));
		Assert.assertEquals(5, (int)results.get(0).get("pcs1"));
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countByTagCasePeriod2-setup.xml")
	public void countByTagCasePeriod2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ResultBean> resultBeans = caseStatisticService.countByTagCasePeriod("pcs1", sdf.parse("2010-10-5 10:10:10"), sdf.parse("2010-10-25 10:10:10"), 1, new String[]{"zaxz1"});
		
		Assert.assertEquals(3, resultBeans.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countByTagOccurPlace2-setup.xml")
	public void countByTagOccurPlace2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ResultBean> resultBeans = caseStatisticService.countByTagOccurPlace("pcs1", sdf.parse("2010-10-5 10:10:10"), sdf.parse("2010-10-25 10:10:10"), 1, new String[]{"zaxz1"});
		
		Assert.assertEquals(3, resultBeans.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseStatistic/countRobberyCaseByRegion-setup.xml")
	public void countRobberyCaseByRegion() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Integer>> results = caseStatisticService.countRobberyCaseByRegion("pcs1", sdf.parse("2010-10-5 10:10:10"), sdf.parse("2010-10-25 10:10:10"));
		Assert.assertEquals(5, (int)results.get(0).get("pcs1"));
	}
}
