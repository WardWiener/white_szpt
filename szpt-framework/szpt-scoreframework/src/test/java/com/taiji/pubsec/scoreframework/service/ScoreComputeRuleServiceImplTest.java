package com.taiji.pubsec.scoreframework.service;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.DbunitTestCase;
import com.taiji.pubsec.scoreframework.MokeExecutor;
import com.taiji.pubsec.scoreframework.MokeRuleA;
import com.taiji.pubsec.scoreframework.MokeRuleB;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.service.ScoreComputePointService;
import com.taiji.pubsec.scoreframework.service.ScoreComputeRuleService;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;

public class ScoreComputeRuleServiceImplTest extends DbunitTestCase {

	private static Logger log = LogManager.getLogger(ScoreComputeRuleServiceImplTest.class);
			
	@Resource
	private Dao dao;
	
	@Resource(name="scoreRuleService")
	private ScoreComputeRuleService scoreRuleService;
	@Resource
	private ScoreComputePointService scorePointService;
	@Resource
	private ScoreComputeTaskService scoreTaskService;
	
	@Test
	@ExpectedDatabases({
		@ExpectedDatabase(value = "classpath:com/taiji/pubsec/scoreframework/service/createScorePoint-rule-expected.xml", table = "t_scoreframework_scorerule",  assertionMode = DatabaseAssertionMode.NON_STRICT),
		@ExpectedDatabase(value = "classpath:com/taiji/pubsec/scoreframework/service/createScorePoint-cp-expected.xml", table = "t_scoreframework_scorepoint", assertionMode = DatabaseAssertionMode.NON_STRICT) })
	public void testCreateScorePoint(){
		ScoreComputePointImpl cp = new ScoreComputePointImpl();
		cp.setScoreExecutorId("ce123");
		cp.setScoreExecutorType(MokeExecutor.class.getName());
		cp.setDescription("测试用的检查点");
		scorePointService.save(cp);
		
		ScoreComputeRuleImpl rulea = new ScoreComputeRuleImpl();
		rulea.setType(MokeRuleA.class.getName());
		rulea.setRuleId("a123");
		rulea.setDescription("测试用的规则a。");
		rulea.setScorePoint(cp);
		scoreRuleService.save(rulea);
		
		ScoreComputeRuleImpl ruleb = new ScoreComputeRuleImpl();
		ruleb.setType(MokeRuleB.class.getName());
		ruleb.setRuleId("b123");
		ruleb.setDescription("测试用的规则b。");
		ruleb.setScorePoint(cp);
		scoreRuleService.save(ruleb);
		dao.flush();
//		cp.getCheckRules().add(rulea);
//		cp.getCheckRules().add(ruleb);
//		checkPointService.update(cp);
		
		log.info("scorepoint bean's id : " + cp.getId());
		Assert.assertNotNull(rulea.getScorePoint().getId());
		Assert.assertNotSame("", rulea.getScorePoint().getId());
		Assert.assertEquals(rulea.getScorePoint().getId(), ruleb.getScorePoint().getId());
		Assert.assertEquals(cp.getId(), ruleb.getScorePoint().getId());
	}
	
	@Test
	@DatabaseSetup("classpath:com/taiji/pubsec/scoreframework/service/findScorePoint-setup.xml")
	public void testFindScorePoint(){
		ScoreComputePointImpl cp = (ScoreComputePointImpl)scorePointService.findById("cp1");
		Assert.assertNotNull(cp);
		Assert.assertEquals("测试用的检查点", cp.getDescription());
		Assert.assertEquals("ce123", cp.getScoreExecutorId());
		Assert.assertEquals("com.taiji.pubsec.scoreframework.MokeExecutor", cp.getScoreExecutorType());
		Assert.assertEquals(2, cp.getScoreRules().size());
	}
	
	@Test
	@ExpectedDatabases({
		@ExpectedDatabase(value = "classpath:com/taiji/pubsec/scoreframework/service/addTaskParentChildren-task-expected.xml", table = "t_scoreframework_scoretask",  assertionMode = DatabaseAssertionMode.NON_STRICT)})
	public void testAddTaskParentChildren(){
		ScoreComputeTaskImpl task = new ScoreComputeTaskImpl();
		task.setWeight(1.0);
		task.setId("parent");

		ScoreComputeTaskImpl child1 = new ScoreComputeTaskImpl();
		child1.setWeight(0.3);
		child1.setId("child1");
		ScoreComputeTaskImpl child2 = new ScoreComputeTaskImpl();
		child2.setWeight(0.7);
		child2.setId("child2");
		scoreTaskService.save(child1);
		scoreTaskService.save(child2);
		
		task.getChildren().add(child1);
		task.getChildren().add(child2);
		scoreTaskService.save(task);
		dao.flush();
	}
	
	@Test
	@DatabaseSetup(value = "classpath:com/taiji/pubsec/scoreframework/service/getTaskParentChildren-task-expected.xml")
	public void testGetTaskParentChildren(){
		ScoreComputeTask task = scoreTaskService.findById("t3");
		Assert.assertNotNull(task);
		Assert.assertEquals(2, task.getChildren().size());
		for(ScoreComputeTask child : task.getChildren()){
			ScoreComputeTaskImpl c = (ScoreComputeTaskImpl)child;
			Assert.assertEquals(task.getId(), c.getParent().getId());
		}
		
	}

}
