package com.taiji.pubsec.szpt.dpp.surveillance.test.remote;

import java.util.Date;

import javax.annotation.Resource;

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
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.FlightInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.HotelInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.InternetBarInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.TrainInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.WifiInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListMsg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-remote.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public class RemoteTest {
	
	@Resource
	protected KafkaProducer kafkaProducer ;

	@Test
	public void businessToSurveil(){
		SurveilListMsg msg = new SurveilListMsg() ;
		msg.setOperationType(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_ADD_OR_UPDATE);
		msg.setSurveilListCode("人员布控赵bm");
		
		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_BUSINESS_TO_SURVEIL(), SerializeUtils.serialize(msg));
		
		SurveilListMsg msg1 = new SurveilListMsg() ;
		msg1.setOperationType(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_ADD_OR_UPDATE);
		msg1.setSurveilListCode("人员布控钱bm");
		
		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_BUSINESS_TO_SURVEIL(), SerializeUtils.serialize(msg1));
	}
	
	@Test
	public void cancelBusinessToSurveil(){
		SurveilListMsg msg = new SurveilListMsg() ;
		msg.setOperationType(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL);
		msg.setSurveilListCode("人员布控赵bm");
		
		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_BUSINESS_TO_SURVEIL(), SerializeUtils.serialize(msg));
		
		SurveilListMsg msg1 = new SurveilListMsg() ;
		msg1.setOperationType(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL);
		msg1.setSurveilListCode("人员布控钱bm");
		
		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_BUSINESS_TO_SURVEIL(), SerializeUtils.serialize(msg1));
	}
	
	@Test
	public void processDefaultClues(){
//		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_DEFAULT_CLUE_TO_SURVEIL(), SerializeUtils.serialize(generateFlightInfo()));
		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_DEFAULT_CLUE_TO_SURVEIL(), SerializeUtils.serialize(generateHotelInfo()));
//		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_DEFAULT_CLUE_TO_SURVEIL(), SerializeUtils.serialize(generateWifiInfo()));
//		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_DEFAULT_CLUE_TO_SURVEIL(), SerializeUtils.serialize(generateTrainInfo()));
//		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_DEFAULT_CLUE_TO_SURVEIL(), SerializeUtils.serialize(generateInternetBarInfo()));
//		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_DEFAULT_CLUE_TO_SURVEIL(), SerializeUtils.serialize(generateImgInfo()));
	}
	
	private FlightInfo generateFlightInfo(){
		FlightInfo info = new FlightInfo() ;
		info.setArrivalTime(new Date().getTime());
		info.setArriveatAirport("武汉天河机场");
		info.setFlightNumber("海南航空2");
		info.setIdCard("350500196203245513");
		info.setTakeoffAirport("贵阳龙洞堡机场");
		info.setTakeoffTime(new Date().getTime());
		return info ;
	}
	
	private HotelInfo generateHotelInfo(){
		HotelInfo info = new HotelInfo() ;
		info.setEnterTime(new Date().getTime());
		info.setHotelAddress("惠新西街南口");
		info.setHotelCode("beijinghotelcode");
		info.setHotelName("北京大酒店2");
		info.setIdCard("350500196203245513");
		info.setLeaveTime(new Date().getTime());
		return info ;
	}
	
	private WifiInfo generateWifiInfo(){
		WifiInfo info = new WifiInfo() ;
		info.setEnterTime(new Date().getTime());
		info.setLatitude(26.50294951194965);
		info.setLeaveTime(new Date().getTime());
		info.setLongitude(106.6938027614797);
		info.setMac("BB-BB-BB-BB-BB-BB") ;
		info.setPhone("18612390215");
		info.setPlaceCode("5201142B000011");
		info.setPlaceName("平桥派出所对面");
		return info ;
	}
	
	private TrainInfo generateTrainInfo(){
		TrainInfo info = new TrainInfo() ;
		info.setArrivalStation("淮南东");
		info.setIdCard("350500196203245513");
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
		info.setIdCard("350500196203245513");
		info.setLeaveTime(new Date().getTime());
		return info ;
	}
	
	private ImgInfo generateImgInfo(){
		ImgInfo info = new ImgInfo() ;
		info.setCameraCode("camera:123");
		info.setImgBase64("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAANSURBVBhXY/j///9/AAn7A/0FQ0XKAAAAAElFTkSuQmCC");
		info.setLatitude(26.486144802098487);
		info.setLongitude(106.694931740186);
		info.setSurveilListCode("BK201700031");
		info.setCameraAddr("camera地址");
		info.setCameraCode("camera编码");
		info.setCatchDate(new Date().getTime());
		info.setConfidenceLevel(2.2);
		return info ;
	}
}
