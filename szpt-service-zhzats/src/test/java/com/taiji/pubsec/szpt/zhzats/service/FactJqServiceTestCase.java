package com.taiji.pubsec.szpt.zhzats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class FactJqServiceTestCase extends TestBase {

	@Resource
	private FactJqService factJqService;
	

	
	/**
	 * 根据类型查找警情
	 * 类型为空默认查找全部
	 */
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJq/findJqByDateAndType-setup.xml")
	public void testFindJqByDateAndType() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-10 10:09:10");
			endDay =  sdf.parse("2016-11-10 10:09:10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> jqTypes = new ArrayList<String>();
		jqTypes.add("jqlx001");
		jqTypes.add("jqlx002");
		jqTypes.add("jqlx003");
		List<FactJq> jqLst = factJqService.findJqByDateAndType(jqTypes, startDay, endDay);
		FactJq jq = jqLst.get(1);
		Assert.assertEquals("002",jq.getId());
		Assert.assertEquals("bm002",jq.getCode());
		Assert.assertEquals("南广场抢劫",jq.getName());
		Assert.assertEquals("南广场",jq.getAddr());
		Assert.assertEquals("盗窃",jq.getJqlxName());
		Assert.assertEquals("jqlx002",jq.getJqlxCode());
		Assert.assertEquals("报警",jq.getJqSource());
		Assert.assertEquals("晚上南广场盗窃",jq.getJqSummary());
		Assert.assertEquals("紧急",jq.getUrgencyLevel());
		Assert.assertEquals("王林林",jq.getCallingPerson());
		Assert.assertEquals("18310222345",jq.getCallingPersonPhone());
		Date callingDate = null;
		Date answerAlarmDate = null;
		Date occurrenceTime = null;
		try {
			callingDate  = sdf.parse("2016-10-10 11:10:10");
			answerAlarmDate  = sdf.parse("2016-10-11 11:10:10");
			occurrenceTime  = sdf.parse("2016-10-11 09:09:10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(callingDate,jq.getCallingDate());
		Assert.assertEquals(answerAlarmDate,jq.getAnswerAlarmDate());
		Assert.assertEquals("jkfj001",jq.getPcsCode());
		Assert.assertEquals("经开分局",jq.getPcsName());
		Assert.assertEquals("tlw001",jq.getCountryCode());
		Assert.assertEquals("腾龙湾",jq.getCountryName());
		Assert.assertEquals(new Double(48.1) ,jq.getLongitude());
		Assert.assertEquals(new Double(45.0) ,jq.getLatitude());
		Assert.assertEquals(occurrenceTime,jq.getOccurrenceTime());
		FactJq jq0 = jqLst.get(0);
		Assert.assertEquals("抢劫",jq0.getJqlxName());
		Assert.assertEquals(3,jqLst.size());
	}	
	
	/**
	 * 根据类型查找警情
	 * 类型为空默认查找全部
	 */
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJq/findJqByDateAndType-setup.xml")
	public void testFindJqByDateAndTypeAndTypeEqNull() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-10 10:09:10");
			endDay =  sdf.parse("2016-11-10 10:09:10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> jqTypes = new ArrayList<String>();
		jqTypes.add("jqlx001");
		jqTypes.add("jqlx002");
		jqTypes.add("jqlx003");
		List<FactJq> jqLst = factJqService.findJqByDateAndType(null, startDay, endDay);
		FactJq jq = jqLst.get(1);
		Assert.assertEquals("002",jq.getId());
		Assert.assertEquals("bm002",jq.getCode());
		Assert.assertEquals("南广场抢劫",jq.getName());
		Assert.assertEquals("南广场",jq.getAddr());
		Assert.assertEquals("盗窃",jq.getJqlxName());
		Assert.assertEquals("jqlx002",jq.getJqlxCode());
		Assert.assertEquals("报警",jq.getJqSource());
		Assert.assertEquals("晚上南广场盗窃",jq.getJqSummary());
		Assert.assertEquals("紧急",jq.getUrgencyLevel());
		Assert.assertEquals("王林林",jq.getCallingPerson());
		Assert.assertEquals("18310222345",jq.getCallingPersonPhone());
		Date callingDate = null;
		Date answerAlarmDate = null;
		Date occurrenceTime = null;
		try {
			callingDate  = sdf.parse("2016-10-10 11:10:10");
			answerAlarmDate  = sdf.parse("2016-10-11 11:10:10");
			occurrenceTime  = sdf.parse("2016-10-11 09:09:10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(callingDate,jq.getCallingDate());
		Assert.assertEquals(answerAlarmDate,jq.getAnswerAlarmDate());
		Assert.assertEquals("jkfj001",jq.getPcsCode());
		Assert.assertEquals("经开分局",jq.getPcsName());
		Assert.assertEquals("tlw001",jq.getCountryCode());
		Assert.assertEquals("腾龙湾",jq.getCountryName());
		Assert.assertEquals(new Double(48.1),jq.getLongitude());
		Assert.assertEquals(new Double(45.0),jq.getLatitude());
		Assert.assertEquals(occurrenceTime,jq.getOccurrenceTime());
		FactJq jq0 = jqLst.get(0);
		Assert.assertEquals("抢劫",jq0.getJqlxName());
		Assert.assertEquals(5,jqLst.size());
	}
	
	/**
	 * 根据警情id查询警情信息
	 * @param jqId 一个警情id   
	 * @return  一条警情信息FactJq
	 */
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJq/findJqByDateAndType-setup.xml")
	public void testFindFactJqById() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-10 10:09:10");
			endDay =  sdf.parse("2016-11-10 10:09:10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FactJq jq = factJqService.findFactJqById("002");
		Assert.assertEquals("bm002",jq.getCode());
		Assert.assertEquals("南广场抢劫",jq.getName());
		Assert.assertEquals("南广场",jq.getAddr());
		Assert.assertEquals("盗窃",jq.getJqlxName());
		Assert.assertEquals("jqlx002",jq.getJqlxCode());
		Assert.assertEquals("报警",jq.getJqSource());
		Assert.assertEquals("晚上南广场盗窃",jq.getJqSummary());
		Assert.assertEquals("紧急",jq.getUrgencyLevel());
		Assert.assertEquals("王林林",jq.getCallingPerson());
		Assert.assertEquals("18310222345",jq.getCallingPersonPhone());
		Date callingDate = null;
		Date answerAlarmDate = null;
		Date occurrenceTime = null;
		try {
			callingDate  = sdf.parse("2016-10-10 11:10:10");
			answerAlarmDate  = sdf.parse("2016-10-11 11:10:10");
			occurrenceTime  = sdf.parse("2016-10-11 09:09:10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(callingDate,jq.getCallingDate());
		Assert.assertEquals(answerAlarmDate,jq.getAnswerAlarmDate());
		Assert.assertEquals("jkfj001",jq.getPcsCode());
		Assert.assertEquals("经开分局",jq.getPcsName());
		Assert.assertEquals("tlw001",jq.getCountryCode());
		Assert.assertEquals("腾龙湾",jq.getCountryName());
		Assert.assertEquals(new Double(48.1) ,jq.getLongitude());
		Assert.assertEquals(new Double(45.0) ,jq.getLatitude());
		Assert.assertEquals(occurrenceTime,jq.getOccurrenceTime());
	}	
	
//	/**
//	 * 查询警情集合
//	 * @param startDay 开始时间
//	 * @param endDay  结束时间     
//	 * @param jqlxCodes 警情类型集合
//	 * @param pcsCodes   派出所编码集合
//	 *  @return  FactJq 集合
//	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	@DatabaseSetup("classpath:dataset/factJq/factJqList-setup.xml")
//	public void testFactJqListTypeEqNull() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date startDay = null;
//		Date endDay = null;
//		try {
//			startDay =  sdf.parse("2016-10-10 08:09:10");
//			endDay =  sdf.parse("2016-11-10 10:09:10");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String[] jqTypes = new String[3];
//		jqTypes[0]="jqlx001";
//		jqTypes[1] = "jqlx002";
//		jqTypes[2] = "jqlx003";
//		String[] pcsCodes = new String[1];
//		pcsCodes[0] = "jkfj001";
//		String str = "主格";
//		//zhdyBm001
//		List<FactJq> jqLst = factJqService.factJqList(startDay, endDay, jqTypes, pcsCodes, null);
//		FactJq jq = jqLst.get(1);
//		Assert.assertEquals("bm002",jq.getCode());
//		Assert.assertEquals("南广场抢劫",jq.getName());
//		Assert.assertEquals("南广场",jq.getAddr());
//		Assert.assertEquals("盗窃",jq.getJqlxName());
//		Assert.assertEquals("jqlx002",jq.getJqlxCode());
//		Assert.assertEquals("报警",jq.getJqSource());
//		Assert.assertEquals("晚上南广场盗窃",jq.getJqSummary());
//		Assert.assertEquals("紧急",jq.getUrgencyLevel());
//		Assert.assertEquals("王林林",jq.getCallingPerson());
//		Assert.assertEquals("18310222345",jq.getCallingPersonPhone());
//		Date callingDate = null;
//		Date answerAlarmDate = null;
//		Date occurrenceTime = null;
//		try {
//			callingDate  = sdf.parse("2016-10-10 11:10:10");
//			answerAlarmDate  = sdf.parse("2016-10-11 11:10:10");
//			occurrenceTime  = sdf.parse("2016-10-11 09:09:10");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Assert.assertEquals(callingDate,jq.getCallingDate());
//		Assert.assertEquals(answerAlarmDate,jq.getAnswerAlarmDate());
//		Assert.assertEquals("jkfj001",jq.getPcsCode());
//		Assert.assertEquals("经开分局",jq.getPcsName());
//		Assert.assertEquals("tlw001",jq.getCountryCode());
//		Assert.assertEquals("腾龙湾",jq.getCountryName());
//		Assert.assertEquals(new Double(48.1),jq.getLongitude());
//		Assert.assertEquals(new Double(45.0),jq.getLatitude());
//		Assert.assertEquals(occurrenceTime,jq.getOccurrenceTime());
//	}
	
	/**
	 * 查询警情集合
	 * @param startDay 开始时间
	 * @param endDay  结束时间     
	 * @param jqlxCodes 警情类型集合
	 * @param pcsCodes   派出所编码集合
	 * @return  FactJq 集合
	 */
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJq/factJqList-setup.xml")
	public void testFactJqList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-10 08:09:10");
			endDay =  sdf.parse("2016-11-10 10:09:10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] jqTypes = new String[3];
		jqTypes[0]="jqlx001";
		jqTypes[1] = "jqlx002";
		jqTypes[2] = "jqlx003";
		String[] pcsCodes = new String[1];
		pcsCodes[0] = "zhdyBm001";
		String str = "主格";
		List<FactJq> jqLst = factJqService.factJqList(startDay, endDay, jqTypes, pcsCodes, str);
		FactJq jq = jqLst.get(1);
		Assert.assertEquals("bm007",jq.getCode());
		Assert.assertEquals("南广场抢劫",jq.getName());
		Assert.assertEquals("南广场",jq.getAddr());
		Assert.assertEquals("盗窃",jq.getJqlxName());
		Assert.assertEquals("jqlx002",jq.getJqlxCode());
		Assert.assertEquals("报警",jq.getJqSource());
		Assert.assertEquals("晚上南广场盗窃",jq.getJqSummary());
		Assert.assertEquals("紧急",jq.getUrgencyLevel());
		Assert.assertEquals("王林林",jq.getCallingPerson());
		Assert.assertEquals("18310222345",jq.getCallingPersonPhone());
		Date callingDate = null;
		Date answerAlarmDate = null;
		Date occurrenceTime = null;
		try {
			callingDate  = sdf.parse("2016-10-10 11:10:10");
			answerAlarmDate  = sdf.parse("2016-10-11 11:10:10");
			occurrenceTime  = sdf.parse("2016-10-16 09:09:10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(callingDate,jq.getCallingDate());
		Assert.assertEquals(answerAlarmDate,jq.getAnswerAlarmDate());
		Assert.assertEquals("zg001",jq.getPcsCode());
		Assert.assertEquals("主格1",jq.getPcsName());
		Assert.assertEquals("tlw001",jq.getCountryCode());
		Assert.assertEquals("腾龙湾",jq.getCountryName());
		Assert.assertEquals(new Double(48.1) ,jq.getLongitude());
		Assert.assertEquals(new Double(45.0) ,jq.getLatitude());
		Assert.assertEquals(occurrenceTime,jq.getOccurrenceTime());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/CjFeedback/cjFeedbackList-setup.xml")
	public void testFindCjFeedbackByJqId(){
		List<CjFeedback> list=this.factJqService.findCjFeedbackByJqId("002");
		Assert.assertEquals("002",list.get(0).getFactCjId());
		Assert.assertEquals("嘿嘿",list.get(2).getFkPerson());
	}
}
