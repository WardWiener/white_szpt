package com.taiji.pubsec.szpt.highriskpersonalert.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfoTwoValue;
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
public class HighriskPeopleStatisticsServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(HighriskPeopleStatisticsServiceTestCase.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Autowired
	private IHighriskPeopleStatisticsService highriskPeopleStatisticsService;
	@Test
	@DatabaseSetup("classpath:dataset/service/highriskPeopleStatisticsService/highriskPeopleTypeStatistics-setup.xml")
	public void testFindByGeographicalZone() {
		List<StatisticsInfo> statisticsInfoLst = highriskPeopleStatisticsService.highriskPeopleTypeStatistics();
		Assert.assertEquals(2,statisticsInfoLst.size());
		Assert.assertEquals("bm0",statisticsInfoLst.get(0).getName());
		Assert.assertEquals("6",statisticsInfoLst.get(0).getValue());
		Assert.assertEquals("未标识",statisticsInfoLst.get(1).getName());
		Assert.assertEquals("0",statisticsInfoLst.get(1).getValue());
		Assert.assertEquals(1,1);
	}
	
	
	@Test
	@DatabaseSetup("classpath:dataset/service/highriskPeopleStatisticsService/statisticsCheckPeopleInfo-setup.xml")
	public void testStatisticsCheckPeopleInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeStart = null;
		Date timeEnd = null;
		try {
			timeStart =  sdf.parse("2016-10-13 10:10:10");
			timeEnd =  sdf.parse("2016-10-15 10:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<PersonCheckInfo> personCheckInfoLst = highriskPeopleStatisticsService.statisticsCheckPeopleInfo(timeStart, timeEnd);
		Assert.assertEquals(4,personCheckInfoLst.size());
		Assert.assertEquals("001",personCheckInfoLst.get(0).getId());
		Assert.assertEquals("经开分局",personCheckInfoLst.get(0).getUnitName());
		Assert.assertEquals("002",personCheckInfoLst.get(1).getId());
		Assert.assertEquals("小河分局",personCheckInfoLst.get(1).getUnitName());
		
	}
	
	
	
	
	@Test
	@DatabaseSetup("classpath:dataset/service/highriskPeopleStatisticsService/fiveColorPeopleStatisticsByUnit-setup.xml")
	public void testFiveColorPeopleStatisticsByUnit() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeStart = null;
		Date timeEnd = null;
		try {
			timeStart =  sdf.parse("2016-10-13 10:10:10");
			timeEnd =  sdf.parse("2016-10-15 10:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<StatisticsInfo> statisticsInfoLst = highriskPeopleStatisticsService.fiveColorPeopleStatisticsByUnit(timeStart, timeEnd);
		Assert.assertEquals(2,statisticsInfoLst.size());
		Assert.assertEquals("经开分局",statisticsInfoLst.get(0).getName());
		Assert.assertEquals("3",statisticsInfoLst.get(0).getValue());
		Assert.assertEquals("小河分局",statisticsInfoLst.get(1).getName());
		Assert.assertEquals("2",statisticsInfoLst.get(1).getValue());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/highriskPeopleStatisticsService/zaiKongPeopleStatistics-setup.xml")
	public void testZaiKongPeopleStatistics() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date timeStart = sdf.parse("2016-10-13 10:10:10");
		Date timeEnd = sdf.parse("2016-10-15 10:00:00");

		List<StatisticsInfoTwoValue> result = highriskPeopleStatisticsService.zaiKongPeopleStatistics(timeStart, timeEnd, null);
		Assert.assertEquals(2, result.size());
		for(StatisticsInfoTwoValue stat : result) {
			if(stat.getName().equals("经开分局")) {
				Assert.assertEquals("0", stat.getValue());
				Assert.assertEquals("3", stat.getValue_two());
			}
			if(stat.getName().equals("小河分局")) {
				Assert.assertEquals("0", stat.getValue());
				Assert.assertEquals("2", stat.getValue_two());
			}
		}
		
	}
	
}
