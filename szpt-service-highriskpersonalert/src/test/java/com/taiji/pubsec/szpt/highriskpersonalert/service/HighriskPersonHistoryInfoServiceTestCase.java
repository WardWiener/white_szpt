package com.taiji.pubsec.szpt.highriskpersonalert.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfoTwoValue;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class HighriskPersonHistoryInfoServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(HighriskPersonHistoryInfoServiceTestCase.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Autowired
	private IHighriskPersonHistoryInfoService highriskPersonHistoryInfoService;
	@Test
	@DatabaseSetup("classpath:dataset/service/highriskPersonHistoryInfoService/statisticsHighPersonHistoryInfo-setup.xml")
	public void testFindHPersonsCountByPeopleTypeByDayPart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-13 10:10:10");
			endDay =  sdf.parse("2016-10-15 10:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List <String> peopleTypeCodes = new ArrayList<>();
		Map<String, Object> statisticsHighPersonHistoryInfo = highriskPersonHistoryInfoService.statisticsHighPersonHistoryInfo(startDay, endDay);
		
	}
	
}
