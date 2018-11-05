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
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.ptjc.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.ptjc.model.PenalConstant;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)


@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class PenalConstantServiceTestCase extends TestBase{
	
	@Resource
	private DistributionRuleService distributionRuleService;
	
	@Resource
	private PenalConstantService penalConstantService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/service/penalConstantService/findPenalConstantByUnitCodeAndType-setup.xml")
	public void testFindPenalConstantByUnitCodeAndType() {
		List<String> unitCodeLst = new ArrayList<String>();
		unitCodeLst.add("bm002");
		unitCodeLst.add("bm003");
		List<PenalConstant> penalConstantLst = penalConstantService.findPenalConstantByUnitCodeAndType(unitCodeLst, "DAY");
		Assert.assertEquals(4, penalConstantLst.size());
		Assert.assertEquals("平桥派出所",penalConstantLst.get(0).getUnitName());
		Assert.assertEquals("1<2",penalConstantLst.get(0).getRange());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/penalConstantService/deletePenalConstantByUnitCode-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/service/penalConstantService/deletePenalConstantByUnitCode-expected.xml", table = "t_ptjc_xscl",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void testDeletePenalConstantByUnitCode() {
		List<String> unitCodeLst = new ArrayList<String>();
		unitCodeLst.add("bm002");
		unitCodeLst.add("bm003");
		penalConstantService.deletePenalConstantByUnitCode(unitCodeLst, "DAY");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/penalConstantService/findPcsAlertThresholdInfosByType-setup.xml")
	public void testFindPcsAlertThresholdInfosByType() {
		List<AlertThresholdInfo> alertThresholdInfoLst = penalConstantService.findPcsAlertThresholdInfosByType("Day");
		Assert.assertEquals(3, alertThresholdInfoLst.size());
		Assert.assertEquals(new Integer(2),alertThresholdInfoLst.get(0).getRedHoldValue());
		Assert.assertEquals(new Integer(1),alertThresholdInfoLst.get(0).getBlueHoldValue());
		Assert.assertEquals("平桥派出所",alertThresholdInfoLst.get(0).getName());
		Assert.assertEquals(new Integer(0),alertThresholdInfoLst.get(2).getRedHoldValue());
		Assert.assertEquals(new Integer(0),alertThresholdInfoLst.get(2).getBlueHoldValue());
	}
	
}
