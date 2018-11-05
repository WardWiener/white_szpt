package com.taiji.pubsec.szpt.dpp.surveillance.test.clue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
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
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.ClueProcessService;
import com.taiji.pubsec.szpt.dpp.surveillance.test.DataSetUp;
import com.taiji.pubsec.szpt.dpp.surveillance.util.service.ImgClueUtilService;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.FlightInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.HotelInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.InternetBarInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.TrainInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.WifiInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public class ClueProcessTest extends DataSetUp{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ClueProcessTest.class);
	
	@Resource
	private ClueProcessService clueProcessService ;
	
	@Resource
	private ImgClueUtilService imgClueUtilService ;
	
	@Before
	public void before(){
		saveDatabaseAttachments();
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testGetSurveilListImgAsStream(){
		imgClueUtilService.getSurveilListImgAsStream("人员布控赵bkzp1") ;
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testAllClueProcess(){
		try{
			clueProcessService.process(generateFlightInfo());
			clueProcessService.process(generateHotelInfo());
			clueProcessService.process(generateImgInfo());
			clueProcessService.process(generateInternetBarInfo());
			clueProcessService.process(generateTrainInfo());
			clueProcessService.process(generateWifiInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testFlightClueProcess(){
		try{
			clueProcessService.process(generateFlightInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testHotelClueProcess(){
		try{
			clueProcessService.process(generateHotelInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testImgClueProcess(){
		try{
			clueProcessService.process(generateImgInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testInternetBarClueProcess(){
		try{
			clueProcessService.process(generateInternetBarInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testInternetTrainClueProcess(){
		try{
			clueProcessService.process(generateTrainInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testInternetWifiClueProcess(){
		try{
			clueProcessService.process(generateWifiInfo());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private WifiInfo generateWifiInfo(){
		WifiInfo info = new WifiInfo() ;
		info.setEnterTime(new Date().getTime());
		info.setLatitude(333.2);
		info.setLeaveTime(new Date().getTime());
		info.setLongitude(555555.5);
		info.setMac("人员布控钱mac2") ;
		info.setPhone("33888888833");
		info.setPlaceCode("jiankongchangsuoCode");
		info.setPlaceName("小区1");
		return info ;
	}
	
	private TrainInfo generateTrainInfo(){
		TrainInfo info = new TrainInfo() ;
		info.setArrivalStation("淮南东");
		info.setIdCard("340403");
		info.setStartStation("北京南");
		info.setStartTime(new Date().getTime());
		info.setTrainNumber("trainNo1");
		return info ;
	}
	
	private InternetBarInfo generateInternetBarInfo(){
		InternetBarInfo info = new InternetBarInfo() ;
		info.setCyberCafeAddress("街边网吧1新建口");
		info.setCyberCafeCode("cemeraCode1");
		info.setCyberCafeName("街边网吧1");
		info.setEnterTime(new Date().getTime());
		info.setIdCard("340403");
		info.setLeaveTime(new Date().getTime());
		return info ;
	}
	
	private ImgInfo generateImgInfo(){
		ImgInfo info = new ImgInfo() ;
		info.setCameraCode("camera:123");
		info.setImgBase64("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAANSURBVBhXY/j///9/AAn7A/0FQ0XKAAAAAElFTkSuQmCC");
		info.setLatitude(222222.22);
		info.setLongitude(333333333333.33);
		info.setSurveilListCode("人员布控赵bm");
		info.setCameraAddr("camera地址");
		info.setCameraCode("camera编码");
		info.setCatchDate(new Date().getTime());
		info.setConfidenceLevel(2.2);
		return info ;
	}
	
	private HotelInfo generateHotelInfo(){
		HotelInfo info = new HotelInfo() ;
		info.setEnterTime(new Date().getTime());
		info.setHotelAddress("惠新西街南口");
		info.setHotelCode("beijinghotelcode");
		info.setHotelName("北京大酒店");
		info.setIdCard("340403222");
		info.setLeaveTime(new Date().getTime());
		return info ;
	}

	private FlightInfo generateFlightInfo(){
		FlightInfo info = new FlightInfo() ;
		info.setArrivalTime(new Date().getTime());
		info.setArriveatAirport("北京机场");
		info.setFlightNumber("国行no1");
		info.setIdCard("340403");
		info.setTakeoffAirport("贵阳机场");
		info.setTakeoffTime(new Date().getTime());
		return info ;
	}
}
