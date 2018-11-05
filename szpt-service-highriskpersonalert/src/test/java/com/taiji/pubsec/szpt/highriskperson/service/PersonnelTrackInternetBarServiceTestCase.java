package com.taiji.pubsec.szpt.highriskperson.service;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.InternetBarTrackBean;
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
public class PersonnelTrackInternetBarServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(PersonnelTrackInternetBarServiceTestCase.class);
	
	@Autowired
	private PersonnelTrackService personnelTrackInternetBarService;
	
	
	
	@Test
	public void testGetPersonTracks() throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2015-1-1 00:00:00");
		Date end = sdf.parse("2015-12-28 17:06:35");
		String idcard = "520102196808025014";
		List<PersonTrackInfo> result = personnelTrackInternetBarService.getPersonTracks(start, end, idcard);
		for(PersonTrackInfo info : result) {
			InternetBarTrackBean tinfo = (InternetBarTrackBean)info;
			System.out.println("id:" + tinfo.getId());
			System.out.println("starttime:" + sdf.format(tinfo.getOnlineTime()));
			System.out.println("endtime:" + sdf.format(tinfo.getOfflineTime()));
			System.out.println("name:" + tinfo.getBarName());
			System.out.println("address:" + tinfo.getBarAddress());
			System.out.println("code:" + tinfo.getBarCode());
			System.out.println("idcard:" + tinfo.getPersonIdcode());
		}
	}
	
}
