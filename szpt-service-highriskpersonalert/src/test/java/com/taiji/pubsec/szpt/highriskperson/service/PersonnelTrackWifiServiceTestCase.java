package com.taiji.pubsec.szpt.highriskperson.service;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.WifiTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class PersonnelTrackWifiServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(PersonnelTrackWifiServiceTestCase.class);
	
	@Resource(name="personnelTrackWifiService")
	private PersonnelTrackService personnelTrackWifiService;
	
	
	
	@Test
	public void testGetPersonTracks() throws ParseException {
		HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY = "天津路边摊北京上海" ;
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2011-3-28 00:00:00");
		Date end = sdf.parse("2019-3-28 17:06:35");
		String idcard = "130412199001233418";
		List<PersonTrackInfo> result = personnelTrackWifiService.getPersonTracks(start, end, idcard);
		for(PersonTrackInfo info : result) {
			WifiTrackBean tinfo = (WifiTrackBean)info;
			System.out.println("id:" + tinfo.getId());
			System.out.println("entertime:" + tinfo.getEnterTime());
			System.out.println("leavetime:" + tinfo.getLeaveTime());
			System.out.println("placecode:" + tinfo.getPlaceCode());
			System.out.println("placename:" + tinfo.getPersonName());
			System.out.println("mac:" + tinfo.getMac());
			System.out.println("longitude:" + tinfo.getLongitude());
			System.out.println("latitude:" + tinfo.getLatitude());
		}
	}
	
	@Test
	public void testHRST()throws ParseException{
		
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2011-3-28 00:00:00");
		Date end = sdf.parse("2019-3-28 17:06:35");
		String idcard = "130412199001233418";
		List<PersonTrackInfo> result = personnelTrackWifiService.getPersonTracks(start, end, idcard);
		
		for(PersonTrackInfo info : result) {
			if(!(info instanceof WifiTrackBean)){
				continue ;
			}
			WifiTrackBean tinfo = (WifiTrackBean)info;
			LOGGER.debug("id:" + tinfo.getId());
			LOGGER.debug("entertime:" + tinfo.getEnterTime());
			LOGGER.debug("leavetime:" + tinfo.getLeaveTime());
			LOGGER.debug("placecode:" + tinfo.getPlaceCode());
			LOGGER.debug("placename:" + tinfo.getPersonName());
			LOGGER.debug("mac:" + tinfo.getMac());
			LOGGER.debug("longitude:" + tinfo.getLongitude());
			LOGGER.debug("latitude:" + tinfo.getLatitude());
		}
	}
	
}
