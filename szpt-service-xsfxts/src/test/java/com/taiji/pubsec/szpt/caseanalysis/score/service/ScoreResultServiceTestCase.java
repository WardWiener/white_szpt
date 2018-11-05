package com.taiji.pubsec.szpt.caseanalysis.score.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResultDetail;
import com.taiji.pubsec.szpt.test.TestBase;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class ScoreResultServiceTestCase extends TestBase {

	@Resource
	private ScoreResultService scoreResultService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/findScoredCases-setup.xml")
	public void findScoredCases() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("caseState", "0");
		conditions.put("showHide", "0");
		conditions.put("minScore", 40d);
		conditions.put("feature", "zatd1");
		conditions.put("occurPlace", "facs");
		List<String> communitys = new ArrayList<String>();
		communitys.add("facq");
		conditions.put("community", communitys);
		conditions.put("period", "zasd");
		conditions.put("peopleNum", "zars");
		conditions.put("entrance", "zark");
		conditions.put("exit", "zack");
		List<CaseScoreResult> results = scoreResultService.findScoredCases("ajbh", conditions);
		Assert.assertEquals(3, results.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/createScoreResult-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/scoreResult/createScoreResult-expected.xml", table = "t_xsajfx_cbafxjg",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void createScoreResult() throws ParseException {
		CaseScoreResult result = new CaseScoreResult();
		result.setFinished(false);
		result.setIgnored(false);
		result.setMainCase("ajbh");
		result.setScore(50d);
		result.setScoreTime(DateUtils.parseDate("2010-10-10 10:10:10", "yy-MM-dd HH:mm:ss"));
		result.setSubCase("bdajbh");
		result.setTemplateId("mbid");
		boolean flag = scoreResultService.createOrUpdateScoreResult(result);
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/updateScoreResult-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/scoreResult/updateScoreResult-expected.xml", table = "t_xsajfx_cbafxjg",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void updateScoreResult() {
		CaseScoreResult result = (CaseScoreResult) dao.findById(CaseScoreResult.class, "jgId");
		result.setFinished(true);
		result.setIgnored(true);
		result.setScore(60d);
		result.setTemplateId("mbid1");
		boolean flag = scoreResultService.createOrUpdateScoreResult(result);
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/createScoreResultDetail-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/scoreResult/createScoreResultDetail-expected.xml", table = "t_xsajfx_cbafxjgmx",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void createScoreResultDetail() {
		CaseScoreResultDetail detail = new CaseScoreResultDetail();
		CaseScoreResult result = (CaseScoreResult) dao.findById(CaseScoreResult.class, "jgId");
		detail.setScoreRuleName("gzmc");
		detail.setScore(20d);
		detail.setResult(result);
		boolean flag = scoreResultService.createOrUpdateCaseScoreResultDetail(detail);
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/updateCaseScoreResultDetail-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/scoreResult/updateCaseScoreResultDetail-expected.xml", table = "t_xsajfx_cbafxjgmx",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void updateCaseScoreResultDetail() {
		CaseScoreResultDetail detail = (CaseScoreResultDetail) dao.findById(CaseScoreResultDetail.class, "mxId");
		detail.setScoreRuleName("gzmc2");
		detail.setScore(40d);
		boolean flag = scoreResultService.createOrUpdateCaseScoreResultDetail(detail);
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/testFindScoreResult-setup.xml")
	public void testFindScoreResult() {
		CaseScoreResult result =  scoreResultService.findScoreResult("ajbh1", "ajbh4");
		Assert.assertEquals(true, result.isIgnored());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/findScoreResult-setup.xml")
	public void testFindIgnoredCases() {
		Assert.assertEquals(1, scoreResultService.findIgnoredCases("ajbh1").size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/scoreResult/findDetailByResultIdAndRuleName-setup.xml")
	public void findDetailByResultIdAndRuleName() {
		CaseScoreResultDetail detail = scoreResultService.findDetailByResultIdAndRuleName("jgId", "gzmc2");
		Assert.assertEquals(30d, detail.getScore(), 0.0);
	}
}
