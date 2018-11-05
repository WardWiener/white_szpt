package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class AirPlaneTrackServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(AirPlaneTrackServiceTestCase.class);
	
	@Resource(name="defaultPersonnelTrackService")
	private PersonnelTrackService airPlaneTrackService;
	
	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/airPlaneTrackService/getPersonTracks-setup.xml")
	public void textGetPersonTracks() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			endDay =  sdf.parse("2016-10-12 10:00:00");
			startDay =  sdf.parse("2016-10-13 10:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<PersonTrackInfo> personTracks = airPlaneTrackService.getPersonTracks(startDay, endDay, "130425199001263419");
		Assert.assertEquals(2,personTracks.size());
		personTracks.get(0).getId();
		Assert.assertEquals("002",personTracks.get(0).getId());
		personTracks.get(0).getAppearTime();
	}
	
}
