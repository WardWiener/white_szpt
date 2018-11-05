package com.taiji.pubsec.szpt.ptjc.service;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.ptjc.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.ptjc.model.DistributionRule;
import com.taiji.pubsec.szpt.service.SzptCommonService;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class DistributionRuleServiceTestCase extends TestBase{
	
	@Resource
	private DistributionRuleService distributionRuleService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/distributionRuleService/findPcsFenbuAlertThresholdInfosByType-setup.xml")
	public void testFindPcsFenbuAlertThresholdInfosByType() {
		List<AlertThresholdInfo> alertThresholdInfoLst = distributionRuleService.findPcsFenbuAlertThresholdInfosByType("lb001");
		Assert.assertEquals(3, alertThresholdInfoLst.size());
		Assert.assertEquals(new Integer(2),alertThresholdInfoLst.get(0).getRedHoldValue());
		Assert.assertEquals(new Integer(1),alertThresholdInfoLst.get(0).getBlueHoldValue());
		Assert.assertEquals("平桥派出所",alertThresholdInfoLst.get(0).getName());
		Assert.assertEquals(new Integer(3),alertThresholdInfoLst.get(1).getRedHoldValue());
		Assert.assertEquals(new Integer(0),alertThresholdInfoLst.get(2).getRedHoldValue());
		Assert.assertEquals(new Integer(0),alertThresholdInfoLst.get(2).getBlueHoldValue());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/distributionRuleService/typeCode-setup.xml")
	public void testFindDistributionRuleByTargetAndAlarmTypeCode() {
		List<String> targetIdLst = new ArrayList<String>();
		targetIdLst.add("dw002");
		targetIdLst.add("dw003");
		List<DistributionRule>  distributionRuleLst= distributionRuleService.findDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, "com.taiji.pubsec.szpt.common.model.SzptUnit", "lb001");
		Assert.assertEquals(3, distributionRuleLst.size());
		Assert.assertEquals("RED", distributionRuleLst.get(0).getColor());
		Assert.assertEquals("2<3", distributionRuleLst.get(0).getRange());
		Assert.assertEquals("bm002", distributionRuleLst.get(0).getTargetCode());
		Assert.assertEquals("dw002", distributionRuleLst.get(0).getTargetId());
		Assert.assertEquals("平桥派出所", distributionRuleLst.get(0).getTargetName());
		Assert.assertEquals("com.taiji.pubsec.szpt.common.model.SzptUnit", distributionRuleLst.get(0).getTargetType());
		Assert.assertEquals("3<4", distributionRuleLst.get(1).getRange());
		Assert.assertEquals("BLUE", distributionRuleLst.get(2).getColor());
	}

	@Test
	@DatabaseSetup("classpath:dataset/service/distributionRuleService/typeCode-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/service/distributionRuleService/typeCode-expected.xml", table = "t_ptjc_fbgz",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void testDeleteDistributionRuleByTargetAndAlarmTypeCode() {
		List<String> targetIdLst = new ArrayList<String>();
		targetIdLst.add("dw002");
		targetIdLst.add("dw003");
		distributionRuleService.deleteDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, "com.taiji.pubsec.szpt.common.model.SzptUnit", "lb001");
		dao.flush();
	}
	
}
