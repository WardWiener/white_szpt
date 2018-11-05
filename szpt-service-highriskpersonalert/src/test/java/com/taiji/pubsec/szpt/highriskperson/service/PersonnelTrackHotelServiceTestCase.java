package com.taiji.pubsec.szpt.highriskperson.service;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.PersonExecuteControlServiceTestCase;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HotelTrackBean;
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
public class PersonnelTrackHotelServiceTestCase extends TestBase{
	private final static Logger LOGGER = LoggerFactory.getLogger(PersonnelTrackHotelServiceTestCase.class);
	
	@Autowired
	private PersonnelTrackService personnelTrackHotelService;
	
	
	
	@Test
	public void testGetPersonTracks() throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse("2011-3-28 00:00:00");
		Date end = sdf.parse("2019-3-28 17:06:35");
		String idcard = "130412199001233418";
		List<PersonTrackInfo> result = personnelTrackHotelService.getPersonTracks(start, end, idcard);
		for(PersonTrackInfo info : result) {
			HotelTrackBean hinfo = (HotelTrackBean)info;
			System.out.println("id:" + hinfo.getId());
			System.out.println("hotelcode:" + hinfo.getHotelCode());
			System.out.println("hotelname:" + hinfo.getHotelName());
			System.out.println("hoteladdress:" + hinfo.getHotelAddr());
			System.out.println("idcard:" + hinfo.getPersonIdcode());
			System.out.println("entertime:" + hinfo.getEnterTime().toString());
			System.out.println("leavetime:" + hinfo.getLeaveTime().toString());
		}
	}
	
}
