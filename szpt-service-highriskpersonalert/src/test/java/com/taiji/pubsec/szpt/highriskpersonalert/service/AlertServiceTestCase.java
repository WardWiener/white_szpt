package com.taiji.pubsec.szpt.highriskpersonalert.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.PersonExecuteControlServiceTestCase;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;
import com.taiji.pubsec.szpt.highriskpersonalert.model.AlertInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class AlertServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(AlertServiceTestCase.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Autowired
	private AlertService alertService;
	
	@Test
	@DatabaseSetup("classpath:dataset/service/alertService/findAllAlert-setup.xml")
	public void testFindAllAlert() {
		Pager<Alert> page = alertService.findAllAlert("0",1,2);
		Assert.assertEquals(new Integer(4),page.getTotalNum());
		Assert.assertEquals(2,page.getPageList().size());
//		Assert.assertEquals("yj002",page.getPageList().get(0).getId());
//		Assert.assertEquals("yj001",page.getPageList().get(1).getId());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/alertService/updateAlert-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/service/alertService/updateAlert-expeced.xml", table = "t_gwry_yjxx", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void testUpdateAlert() {
		Alert alert = alertService.findAlertById("yj001");
		Assert.assertEquals("yj001",alert.getId());
		alert.setPersonNames("李四");
		alertService.updateAlert(alert);
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/alertService/findByAlert-setup.xml")
	public void testFindByAlert() {
		Alert alert = new Alert();
		alert.setId("yj001");
		alert.setPersonNames("张三");
		alert.setKey("判重标识");
		alert.setPlace("场所广场");
		alert.setState("0");
		alert.setWarning("抢劫");
		
		List<Alert> alertLst = alertService.findByAlert(alert);
		Assert.assertEquals(3,alertLst.size());
		Assert.assertEquals("yj002",alertLst.get(1).getId());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/alertService/findAlertNumByState-setup.xml")
	public void testFindAlertNumByState() {
		int num = alertService.findAlertNumByState("0");
		Assert.assertEquals(4,num);
	}
	
}
