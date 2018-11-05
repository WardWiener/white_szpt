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
public class AlertInfoServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(AlertInfoServiceTestCase.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource(name="defaultPersonnelTrackService")
	private PersonnelTrackService airPlaneTrackService;
	
	@Autowired
	private IAlertInfoService alertInfoService;
	
	
	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/alertInfoService/saveAlertInfo-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/service/alertInfoService/saveAlertInfo-expected.xml", table = "t_gwry_yjxx", query = "select yj_id from t_gwry_yjxx", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void textsaveAlertInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = null;
		try {
			time =  sdf.parse("2016-10-14 10:10:10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert();
		alert.setId("yj001");
		alert.setCreateTime(time);
		alert.setPersonNames("张三");
		alert.setKey("判重标识");
		alert.setPlace("场所广场");
		alert.setState("0");
		alert.setWarning("抢劫");
		
		AlertInfo alertInfo = new AlertInfo();
		alertInfo.setAlert(alert);
		alertInfoService.saveAlertInfo(alertInfo);
		dao.flush();
	}
	
}
