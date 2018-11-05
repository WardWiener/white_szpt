package com.taiji.pubsec.szpt.caseanalysis.score.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.RobberyTheftCaseScoreTemplateRule;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.test.TestBase;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class ScoreTemplateServiceTestCase extends TestBase{

	@Resource
	private ScoreTemplateService scoreTemplateService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	public void testFindAllTemplate() {
		Assert.assertEquals(2, scoreTemplateService.findAllTemplate().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	public void testFindTemplateById() {
		RobberyTheftCaseScoreTemplate cst = scoreTemplateService.findTemplate("1");
		Assert.assertEquals("01", cst.getCode());
		Assert.assertEquals("name1", cst.getName());
		Assert.assertEquals("0001", cst.getType());
		Assert.assertEquals("0001001", cst.getFirstSort());
		Assert.assertEquals("0001001001", cst.getSecondSort());
		Assert.assertEquals("1", cst.getState());
		Assert.assertEquals("remark1", cst.getRemarks());
		Assert.assertEquals(60, cst.getMinScore());
		Assert.assertEquals("cjr1", cst.getCreatePerson());
		Assert.assertEquals("2016-10-10 13:10:10", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cst.getCreatedTime()));
		Assert.assertEquals("gxr1", cst.getUpdatePerson());
		Assert.assertEquals("2016-12-10 10:10:10", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cst.getUpdateTime()));
		Assert.assertEquals("jsrw1", cst.getComputeTaskId());
		
		Assert.assertEquals(1, cst.getRules().size());
		Assert.assertEquals("key_communities", cst.getRules().get(0).getItem());
		Assert.assertEquals("2.0", cst.getRules().get(0).getWeight());
		Assert.assertEquals("45,85,90", cst.getRules().get(0).getRule());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	public void testFindTemplateByType() {
		RobberyTheftCaseScoreTemplate cst = scoreTemplateService.findTemplate("0001", "0001001", "0001001001");
		Assert.assertNotNull(scoreTemplateService.findTemplate("0001", "0001001", "0001001001"));
		Assert.assertNotNull(scoreTemplateService.findTemplate("0001", "0001002", null));
		Assert.assertNull(scoreTemplateService.findTemplate("0001", "0001001", "0001001002"));
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	public void testHasSameCodeTemplate() {
		Assert.assertTrue(scoreTemplateService.hasSameCodeTemplate("01"));
		
		Assert.assertFalse(scoreTemplateService.hasSameCodeTemplate("03"));
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	public void testHasSameNameTemplate() {
		Assert.assertTrue(scoreTemplateService.hasSameNameTemplate("name1"));
		Assert.assertFalse(scoreTemplateService.hasSameNameTemplate("name03"));
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	public void testEnableDeleted() {
		Assert.assertFalse(scoreTemplateService.enableDeleted("1"));
		Assert.assertTrue(scoreTemplateService.enableDeleted("2"));
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/deleteTemplate-expected.xml", table = "t_xsajfx_cbajfmb",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testDeleteTemplete() {
		scoreTemplateService.deleteTemplete("1");
		dao.flush();
	}

	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/deleteTemplate-expected.xml", table = "t_scoreframework_scoretask",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testDeleteTempleteForTask() {
		scoreTemplateService.deleteTemplete("1");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/deleteTemplate-expected.xml", table = "t_scoreframework_scorepointcfg",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testDeleteTempleteForPointConfig() {
		scoreTemplateService.deleteTemplete("1");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/deleteTemplate-expected.xml", table = "t_scoreframework_scorepoint",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testDeleteTempleteForPoint() {
		scoreTemplateService.deleteTemplete("1");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/deleteTemplate-expected.xml", table = "t_scoreframework_scorerule",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testDeleteTempleteForRule() {
		scoreTemplateService.deleteTemplete("1");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/findTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/deleteTemplate-expected.xml", table = "t_scoreframework_groovyrule",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testDeleteTempleteForGroovyRule() {
		scoreTemplateService.deleteTemplete("1");
		dao.flush();
	}
	
	private void createTemplate() {
		RobberyTheftCaseScoreTemplate template = new RobberyTheftCaseScoreTemplate();
		template.setCode("01");
		template.setName("name1");
		template.setType("0001");
		template.setFirstSort("0001001");
		template.setSecondSort("0001001001");
		template.setState("1");
		template.setRemarks("remark1");
		template.setMinScore(60);
		template.setCreatePerson("cjr1");
//		template.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-10-10 13:10:10"));
		template.setUpdatePerson("gxr1");
//		template.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-12-10 10:10:10"));
		List<RobberyTheftCaseScoreTemplateRule> rules = new ArrayList<RobberyTheftCaseScoreTemplateRule>();
		RobberyTheftCaseScoreTemplateRule rule = new RobberyTheftCaseScoreTemplateRule();
		rule.setItem(CaseTag.KEY_CASECOMMUNITY);
		rule.setWeight("1");
		rule.setRule("40,80,100");
		rules.add(rule);
		
		RobberyTheftCaseScoreTemplateRule rule1 = new RobberyTheftCaseScoreTemplateRule();
		rule1.setItem(CaseTag.KEY_CASEPLACE);
		rule1.setWeight("0.5");
		rule1.setRule("20,50,75,100");
		rules.add(rule1);
		Assert.assertTrue(scoreTemplateService.createTemplate(template, rules));
		dao.flush();
	}
	
	private void updateTemplate() {
		RobberyTheftCaseScoreTemplate template = scoreTemplateService.findTemplate("template1");
		template.setCode("02");
		template.setName("name2");
		template.setType("0002");
		template.setFirstSort("0002001");
		template.setSecondSort("0002001001");
		template.setState("0");
		template.setRemarks("remark2");
		template.setMinScore(80);
		template.setCreatePerson("cjr2");
		template.setUpdatePerson("gxr2");
		Map<String,RobberyTheftCaseScoreTemplateRule> rules = new HashMap<String,RobberyTheftCaseScoreTemplateRule>();
		RobberyTheftCaseScoreTemplateRule rule = new RobberyTheftCaseScoreTemplateRule();
		rule.setItem(CaseTag.KEY_CASECOMMUNITY);
		rule.setWeight("2");
		rule.setRule("45,85,90");
		rules.put(CaseTag.KEY_CASECOMMUNITY, rule);
		
		RobberyTheftCaseScoreTemplateRule rule1 = new RobberyTheftCaseScoreTemplateRule();
		rule1.setItem(CaseTag.KEY_CASEPLACE);
		rule1.setWeight("1.5");
		rule1.setRule("23,57,79,90");
		rules.put(CaseTag.KEY_CASEPLACE, rule1);
		Assert.assertTrue(scoreTemplateService.updateTemplate(template, rules));
		dao.flush();
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/addTemplate-expected.xml", table = "t_xsajfx_cbajfmb",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddTemplateWithTemplateTable() throws ParseException {
		createTemplate();
		
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/addTemplate-expected.xml", table = "t_scoreframework_scoretask",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddTemplateWithScoreTaskTable() throws ParseException {
		createTemplate();
		
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/addTemplate-expected.xml", table = "t_scoreframework_scorepoint",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddTemplateWithScorePointTable() throws ParseException {
		createTemplate();
		
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/addTemplate-expected.xml", table = "t_scoreframework_scorepointcfg",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddTemplateWithScorePointConfigTable() throws ParseException {
		createTemplate();
		
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/addTemplate-expected.xml", table = "t_scoreframework_groovyrule",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddTemplateWithGroovyRuleTable() throws ParseException {
		createTemplate();
		
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/addTemplate-expected.xml", table = "t_scoreframework_scorerule",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddTemplateWithScoreRuleTable() throws ParseException {
		createTemplate();
		
	}

	@Test
	@DatabaseSetup("classpath:dataset/score/updateTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/updateTemplate-expected.xml", table = "t_xsajfx_cbajfmb",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testUpdateTemplateWithTemplateTable() throws ParseException {
		updateTemplate();
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/updateTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/updateTemplate-expected.xml", table = "t_scoreframework_scoretask",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testUpdateTemplateWithScoreTaskTable() throws ParseException {
		updateTemplate();
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/updateTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/updateTemplate-expected.xml", table = "t_scoreframework_scorepoint",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testUpdateTemplateWithScorePointTable() throws ParseException {
		updateTemplate();
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/updateTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/updateTemplate-expected.xml", table = "t_scoreframework_scorepointcfg",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testUpdateTemplateWithScorePointConfigTable() throws ParseException {
		updateTemplate();
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/updateTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/updateTemplate-expected.xml", table = "t_scoreframework_groovyrule",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testUpdateTemplateWithGroovyRuleTable() throws ParseException {
		updateTemplate();
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/updateTemplate-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/score/updateTemplate-expected.xml", table = "t_scoreframework_scorerule",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testUpdaetTemplateWithScoreRuleTable() throws ParseException {
		updateTemplate();
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/score/matchedTemplate-setup.xml")
	public void testFindMatcheddTemplate() throws ParseException {
		Assert.assertEquals("t1",scoreTemplateService.findMatchedTemplate("ajbh1").getId());
		Assert.assertEquals("t2",scoreTemplateService.findMatchedTemplate("ajbh2").getId());
		Assert.assertNull(scoreTemplateService.findMatchedTemplate("ajbh3"));
	}
	
}
