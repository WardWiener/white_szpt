package com.taiji.pubsec.szpt.community.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.bean.LocationCount;
import com.taiji.pubsec.szpt.bean.TrajectoryPos;
import com.taiji.pubsec.szpt.common.model.Location;
import com.taiji.pubsec.szpt.common.model.Trajectory;
import com.taiji.pubsec.szpt.service.LocationCommonService;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class LocationCommonServiceTestCase extends TestBase{

	@Resource
	private LocationCommonService locationCommonService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/community/locationCommonService/createLocation-expected.xml", table = "t_zhzats_jwzh_wzxx",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void testCreateLocation() {
		Location location=new Location();
		location.setId("id001");
		location.setPersonName("小明");
		locationCommonService.createLocation(location);
		this.dao.flush();
	}
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/community/locationCommonService/updateLocation-expected.xml", table = "t_zhzats_jwzh_wzxx",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void testUpdateLocation() {
		Location location=new Location();
		location.setPersonName("小明");
		location.setId("id001");
		locationCommonService.updateLocation(location);
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findLocationByIdentifier-setup.xml")
	public void testFindLocationByIdentifier() {
		Location location = locationCommonService.findLocationByIdentifier("dw001");
		Assert.assertEquals("小刚", location.getPersonName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findLocationByTypeAndIdentifier-setup.xml")
	public void testFindLocationByTypeAndIdentifier() {
		Location location = locationCommonService.findLocationByTypeAndIdentifier("gps","dw001");
		Assert.assertEquals("小明", location.getPersonName());
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/community/locationCommonService/createTrajectory-expected.xml", table = "t_zhzats_jwzh_gjxx",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void testCreateTrajectory() {
		Trajectory trajectory=new Trajectory();
		trajectory.setType("lx");
		trajectory.setId("id001");
		locationCommonService.createTrajectory(trajectory);
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findTrajectoriesByIdentifier-setup.xml")
	public void testFindTrajectoriesByIdentifier() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-9-14 10:11:10");
		Date endDay = sdf.parse("2016-11-14 10:11:11");
		List<Trajectory> list = locationCommonService.findTrajectoriesByIdentifier("dw001",startDay,endDay);
		Assert.assertEquals(3, list.size());
		Assert.assertEquals("小华",list.get(0).getType());
		Assert.assertEquals("小刚",list.get(2).getType());
	}
	
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findAllLocations-setup.xml")
	public void textFindLocationsByUnitName() {	 // 无unitName 这个属性
//		List<Location>  list=locationCommonService.findLocationsByUnitName();
//		Assert.assertEquals(3,list.size());
//		Assert.assertEquals("小明",list.get(0).getPersonName());
//		Assert.assertEquals("小刚",list.get(2).getPersonName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findAllLocationsOther-setup.xml")
	public void textFindAllLocationsOther() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-9-14 10:11:10");
			endDay =  sdf.parse("2016-11-14 10:11:11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<TrajectoryPos> list= locationCommonService.findAllLocations(startDay,endDay,new String[0]);
		Assert.assertEquals(3,list.size());
		Assert.assertEquals(new Double(110.112) ,list.get(0).getLatitude());
		Assert.assertEquals(new Double(120.003) ,list.get(2).getLongitude());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findAllLocationsByCount-setup.xml")
	public void testFindAllLocationsByCount() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-9-14 10:11:10");
		Date endDay = sdf.parse("2016-11-14 10:11:11");
		
		List<LocationCount> list= locationCommonService.findAllLocationsByCount(startDay,endDay,new String[0],"主格");
		Assert.assertEquals(2, list.size());
		for(LocationCount lc : list) {
			if(lc.getClass().equals("bm001")) {
				Assert.assertEquals("北京公安局", lc.getName());
			}
			if(lc.getClass().equals("bm002")) {
				Assert.assertEquals("上海公安局", lc.getName());
			}
		}
		
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/findLatestLocations-setup.xml")
	public void testFindLatestLocations() throws ParseException {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-12-14 10:21:10");
		List<Location> list = locationCommonService.findLatestLocations(startDay, 30);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals("1", list.get(0).getId());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/locationCommonService/locationList-setup.xml")
	public void testLocationList() throws ParseException {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2015-9-14 10:11:10");
		Date endDay = sdf.parse("2016-11-14 10:11:11");
		List<Location> list= locationCommonService.locationList(startDay, endDay, new String[]{"bm001","bm002"});
		Assert.assertEquals(2, list.size());
		Assert.assertEquals("1", list.get(0).getId());
		Assert.assertEquals("2", list.get(1).getId());
	}
}
