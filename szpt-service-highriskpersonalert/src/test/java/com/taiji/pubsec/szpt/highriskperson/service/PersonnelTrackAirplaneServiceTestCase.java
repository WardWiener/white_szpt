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
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.AirPlaneTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.DrugRehabilitationInfoService;
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
public class PersonnelTrackAirplaneServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(PersonnelTrackAirplaneServiceTestCase.class);
	
	@Autowired
	private PersonnelTrackService personnelTrackAirPlaneService;
	
	@Resource
	private DrugRehabilitationInfoService drugRehabilitationInfoService ;
	
	@Test
	public void testDrugRehabilitationInfoService(){
		System.out.println(drugRehabilitationInfoService.findNumByIdenty("123"));
	}
	
	@Test
	public void testGetPersonTracks() throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2016-7-1 00:00:00");
		Date end = sdf.parse("2019-7-28 17:06:35");
		String idcard = "130412199001233418";
		List<PersonTrackInfo> result = personnelTrackAirPlaneService.getPersonTracks(start, end, idcard);
		for(PersonTrackInfo info : result) {
			AirPlaneTrackBean tinfo = (AirPlaneTrackBean)info;
			System.out.println("id:" + tinfo.getId());
			System.out.println("starttime:" + tinfo.getSetoutTime());
			System.out.println("trainno:" + tinfo.getReachTime());
			System.out.println("startstation:" + tinfo.getOriginLocation());
			System.out.println("endstation:" + tinfo.getDestinationLocation());
			System.out.println("idcard:" + tinfo.getPersonIdcode());
			System.out.println("flightnumber:" + tinfo.getFlightNo());
			
		}
	}
	
}
