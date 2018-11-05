package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.math.RandomUtils;
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
import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model.ImgClueResult;
import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model.SurveilList;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-remote.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(false)
public class RemoteTest {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RemoteTest.class) ;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Test
	public void testSaveResultToScan(){
		SurveilList slt = randomSurveilList() ;
		
		LOGGER.debug("布控单id:{}，编码:{}", slt.getId(), slt.getSurveilListCode());
		
		ImgClueResult result = new ImgClueResult() ;
		result.setCameraAddr("随机摄像头地址" + UUID.randomUUID().toString());
		result.setCameraCode("随机摄像头编码" + UUID.randomUUID().toString());
		result.setCatchDate(new Date());
		result.setConfidenceLevel(RandomUtils.nextDouble());
		
		result.setImg(getImgByte());
		
		result.setLatitude(RandomUtils.nextDouble());
		result.setLongitude(RandomUtils.nextDouble());
		
		result.setStatus(ImgClueResult.DTP_DG_IMG_CLUE_RESULT_UNREAD);
		result.setSurveilList(slt);
		
		this.dao.save(result);
		
	}
	
	private byte[] getImgByte(){
		String imgBase64 = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAANSURBVBhXY/j///9/AAn7A/0FQ0XKAAAAAElFTkSuQmCC";
		return Base64CryptFmtUtil.decodeToByte(imgBase64.getBytes()) ;
	}
	
	@SuppressWarnings("unchecked")
	private SurveilList randomSurveilList(){
		String xql = "select s from " + SurveilList.class.getName() + " as s where"
				+ " s.status=:status"
				+ " and s.startDate<=:nowDate"
				+ " and s.endDate>=:nowDate" ;
		Map<String, Object> xqlMap = new HashMap<String, Object>() ;
		xqlMap.put("status", SurveillanceUtilConstant.DICT_ON) ;
		
/*		Calendar startCal = Calendar.getInstance() ;
		startCal.set(2015, 2, 2);*/
		
		xqlMap.put("nowDate", new Date()) ;
		List<SurveilList> list = this.dao.findAllByParams(SurveilList.class, xql, xqlMap) ;
		
		if(list.size()==0){
			return null;
		}
		
		int radomInt = RandomUtils.nextInt(list.size()-1) ;
		
		return list.get(radomInt) ;
	}
}
