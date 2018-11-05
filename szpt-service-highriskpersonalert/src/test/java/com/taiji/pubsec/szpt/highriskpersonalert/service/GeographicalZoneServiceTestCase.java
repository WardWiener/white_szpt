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
import com.taiji.pubsec.szpt.highriskpersonalert.model.GeographicalZonePeopleInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
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
public class GeographicalZoneServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(GeographicalZoneServiceTestCase.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Autowired
	private IGeographicalZoneService geographicalZoneService;
	@Test
	@DatabaseSetup("classpath:dataset/service/geographicalZoneService/findByGeographicalZone-setup.xml")
	public void testFindByGeographicalZone() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeStart = null;
		Date timeEnd = null;
		try {
			timeStart =  sdf.parse("2016-10-13 10:10:10");
			timeEnd =  sdf.parse("2016-10-15 10:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("timeStart", timeStart);
		map.put("timeEnd", timeEnd);
		
		List<StatisticsInfo> statisticsInfoLst = geographicalZoneService.findByGeographicalZone(map);
		Assert.assertEquals(2,statisticsInfoLst.size());
		Assert.assertEquals("地缘性地区",statisticsInfoLst.get(0).getName());
		Assert.assertEquals("4",statisticsInfoLst.get(0).getValue());
		Assert.assertEquals("地缘性",statisticsInfoLst.get(1).getName());
		Assert.assertEquals("3",statisticsInfoLst.get(1).getValue());
	}
	

	@Test
	@DatabaseSetup("classpath:dataset/service/geographicalZoneService/findGeographicalZonePeopleInfoByZone-setup.xml")
	public void testFindGeographicalZonePeopleInfoByZone() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeStart = null;
		Date timeEnd = null;
		try {
			timeStart =  sdf.parse("2016-10-13 10:10:10");
			timeEnd =  sdf.parse("2016-10-15 10:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("timeStart", timeStart);
		map.put("timeEnd", timeEnd);
		map.put("zoneName", "地缘性地区");
		Pager<GeographicalZonePeopleInfo> page = geographicalZoneService.findGeographicalZonePeopleInfoByZone(map, 0, 2);
		
		Assert.assertEquals(new Integer(4),page.getTotalNum());
		Assert.assertEquals("001",page.getPageList().get(0).getId());
		
	}
	
	
//	@Test
//	@DatabaseSetup("classpath:dataset/service/alertService/updateAlert-setup.xml")
//	@ExpectedDatabases({ 
//		@ExpectedDatabase(value = "classpath:dataset/service/alertService/updateAlert-expeced.xml", table = "t_gwry_yjxx", assertionMode = DatabaseAssertionMode.NON_STRICT),
//	})
//	public void testUpdateAlert() {
//		Alert alert = alertService.findAlertById("yj001");
//		Assert.assertEquals("yj001",alert.getId());
//		alert.setPersonNames("李四");
//		alertService.updateAlert(alert);
//		dao.flush();
//	}
	

}
