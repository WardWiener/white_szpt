package com.taiji.pubsec.szpt.zhzats.service;

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
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.bean.AlarmPos;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class FactJqAnalyzeTsServiceTestCase extends TestBase {
	
	@Resource
	private FactJqAnalyzeTsService factJqAnalyzeTsService;
	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmInfosByPcsCodes-setup.xml")
	public void testFindAlarmInfosByPcsCodesByAllPcsCode() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[4];
		pcsCodes[0] =  "jkfj001";
		pcsCodes[1] =  "xhfj001";
		pcsCodes[2] =  "sjfj001";
		pcsCodes[3] =  "pqfj001";
		
		List<AlarmInfo> alarmInfoLst = factJqAnalyzeTsService.findAlarmInfosByPcsCodes(startDay, endDay, pcsCodes, null );
		Assert.assertEquals(4,alarmInfoLst.size());
		Assert.assertEquals(new Integer(4),alarmInfoLst.get(0).getCount());
		Assert.assertEquals(new Integer(0),alarmInfoLst.get(1).getCount());
		Assert.assertEquals(new Integer(0),alarmInfoLst.get(2).getCount());
		Assert.assertEquals(new Integer(0),alarmInfoLst.get(3).getCount());
		Assert.assertEquals("经开分局",alarmInfoLst.get(0).getName());
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmInfosByPcsCodes-setup.xml")
	public void testFindAlarmInfosByPcsCodes() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[1];
		pcsCodes[0] =  "jkfj001";
		List<AlarmInfo> alarmInfoLst = factJqAnalyzeTsService.findAlarmInfosByPcsCodes(startDay, endDay, pcsCodes,null);
		Assert.assertEquals(1,alarmInfoLst.size());
		Assert.assertEquals("经开分局",alarmInfoLst.get(0).getName());
		Assert.assertEquals(new Integer(4),alarmInfoLst.get(0).getCount());
	}
	
	//
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmInfosByJqlxCodesByPcsCodes-setup.xml")
	public void testFindAlarmInfosByJqlxCodesByPcsCodes() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[1];
		pcsCodes[0] =  "jkfj001";
		
		String[] jqCodelst = new String[3];
		jqCodelst[0] =  "jqlx001";
		jqCodelst[1] =  "jqlx002";
		jqCodelst[2] =  "jqlx003";
		String method="welcome";
		List<AlarmInfo> alarmInfoLst = factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodes(startDay, endDay, jqCodelst, pcsCodes,method);
		Assert.assertEquals(3,alarmInfoLst.size());
		Assert.assertEquals("抢劫",alarmInfoLst.get(0).getName());
		Assert.assertEquals(new Integer(1),alarmInfoLst.get(0).getCount());
		Assert.assertEquals("jqlx001",alarmInfoLst.get(0).getNameAdd1());
		Assert.assertEquals("盗窃",alarmInfoLst.get(1).getName());
		Assert.assertEquals(new Integer(3),alarmInfoLst.get(1).getCount());
		Assert.assertEquals("jqlx002",alarmInfoLst.get(1).getNameAdd1());
		Assert.assertEquals("杀人",alarmInfoLst.get(2).getName());
		Assert.assertEquals(new Integer(3),alarmInfoLst.get(2).getCount());
		Assert.assertEquals("jqlx003",alarmInfoLst.get(2).getNameAdd1());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmPos-setup.xml")
	public void testFindAlarmPosOne() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay =  sdf.parse("2016-10-10 01:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[2];
		pcsCodes[0] =  "xhfj001";
		pcsCodes[1] =  "pqfj001";
		
		List<AlarmPos> alarmPosLst = factJqAnalyzeTsService.findAlarmPos(startDay, endDay, pcsCodes);
		Assert.assertEquals(2,alarmPosLst.size());
		Assert.assertEquals(new Double(49.1) ,alarmPosLst.get(0).getLongitude());
		Assert.assertEquals(new Double(45.0) ,alarmPosLst.get(0).getLatitude());
		Assert.assertEquals(new Double(48.1) ,alarmPosLst.get(1).getLongitude());
		Assert.assertEquals(new Double(45.0),alarmPosLst.get(1).getLatitude());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmPos-setup.xml")
	public void testFindAlarmPosTow() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 01:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");
		
		String[] pcsCodes = new String[1];
		pcsCodes[0] =  "jkfj001";
		String[] jqCodelst = new String[3];
		jqCodelst[0] =  "jqlx005";
		jqCodelst[1] =  "jqlx004";
		jqCodelst[2] =  "jqlx003";
		
		List<AlarmPos> alarmPosLst = factJqAnalyzeTsService.findAlarmPos(startDay, endDay, jqCodelst,pcsCodes);
		Assert.assertEquals(3,alarmPosLst.size());
		Assert.assertEquals("经开分局",alarmPosLst.get(0).getName());
		Assert.assertEquals(new Double(58.1),alarmPosLst.get(0).getLongitude());
		Assert.assertEquals(new Double(45.0),alarmPosLst.get(0).getLatitude());
		Assert.assertEquals("经开分局",alarmPosLst.get(1).getName());
		Assert.assertEquals(new Double(68.1),alarmPosLst.get(1).getLongitude());
		Assert.assertEquals(new Double(45.0),alarmPosLst.get(1).getLatitude());
		Assert.assertEquals("经开分局",alarmPosLst.get(2).getName());
		Assert.assertEquals(new Double(78.1),alarmPosLst.get(2).getLongitude());
		Assert.assertEquals(new Double(45.0),alarmPosLst.get(2).getLatitude());
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmInfosByJqlxCodesByPcsCodesByTotal-setup.xml")
	public void testFindAlarmInfosByJqlxCodesByPcsCodesByTotal() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 01:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[1];
		pcsCodes[0] =  "jkfj001";
		String[] jqCodelst = new String[3];
		jqCodelst[0] =  "jqlx001";
		jqCodelst[1] =  "jqlx002";
		jqCodelst[2] =  "jqlx003";
		
		Integer total = factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDay, endDay, jqCodelst,pcsCodes);
		Assert.assertEquals(new Integer(4),total);	
	}
	
	//findAlarmInfosByPcsCodesBuShiDuan
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmInfosByPcsCodesBuShiDuan-setup.xml")
	public void testFindAlarmInfosByPcsCodesBuShiDuan() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 01:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[2];
		pcsCodes[0] = "xhfj001";
		pcsCodes[1] = "pqfj001";
		String[] jqCodelst = new String[3];
		jqCodelst[0] =  "jqlx001";
		jqCodelst[1] =  "jqlx002";
		jqCodelst[2] =  "jqlx003";
		
		List<AlarmInfo> alarmInfoLst = factJqAnalyzeTsService.findAlarmInfosByPcsCodesBuShiDuan(startDay, endDay, pcsCodes);
		Assert.assertEquals(8,alarmInfoLst.size());	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findAlarmInfosByPcsCodesBuShiDuanByJqlx-setup.xml")
	public void testfindAlarmInfosByPcsCodesBuShiDuanByJqlx() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 01:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		String[] pcsCodes = new String[3];
		pcsCodes[0] = "jkfj001";
		pcsCodes[1] = "xhfj001";
		pcsCodes[2] = "pqfj001";
		String[] jqCodelst = new String[3];
		jqCodelst[0] =  "jqlx001";
		jqCodelst[1] =  "jqlx002";
		jqCodelst[2] =  "jqlx003";
		
		List<AlarmInfo> alarmInfoLst = factJqAnalyzeTsService.findAlarmInfosByPcsCodesBuShiDuanByJqlx(startDay, endDay, jqCodelst,pcsCodes);
		Assert.assertEquals(24,alarmInfoLst.size());
		Assert.assertEquals("0~3",alarmInfoLst.get(0).getName());
		Assert.assertEquals("0~3",alarmInfoLst.get(1).getName());
		Assert.assertEquals("0~3",alarmInfoLst.get(2).getName());
		Assert.assertEquals(new Integer(0),alarmInfoLst.get(0).getCount());
		Assert.assertEquals(new Integer(0),alarmInfoLst.get(1).getCount());
		Assert.assertEquals(new Integer(0),alarmInfoLst.get(2).getCount());
		
		Assert.assertEquals(new Integer(1),alarmInfoLst.get(12).getCount());
		Assert.assertEquals("经开分局",alarmInfoLst.get(12).getNameAdd1());
		Assert.assertEquals(new Integer(1),alarmInfoLst.get(13).getCount());
		Assert.assertEquals("平桥分局",alarmInfoLst.get(13).getNameAdd1());
		Assert.assertEquals(new Integer(1),alarmInfoLst.get(14).getCount());
		Assert.assertEquals("小河分局",alarmInfoLst.get(14).getNameAdd1());
		
		Assert.assertEquals("3~6",alarmInfoLst.get(3).getName());
		Assert.assertEquals("3~6",alarmInfoLst.get(4).getName());
		Assert.assertEquals("3~6",alarmInfoLst.get(5).getName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/factJqAnalyzeTs/findGriddJq-setup.xml")
	public void testFindGriddJq() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 01:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");
	
		String[] jqCodelst = new String[3];
		jqCodelst[0] =  "jqlx001";
		jqCodelst[1] =  "jqlx002";
		jqCodelst[2] =  "jqlx003";
		
		List<AlarmPos> alarmPosLst = factJqAnalyzeTsService.findGriddJq(startDay, endDay, jqCodelst, "pcs");
		Assert.assertEquals(2,alarmPosLst.size());
//		Assert.assertEquals("经开分局",alarmPosLst.get(0).getName());
//		Assert.assertEquals("jkfj001",alarmPosLst.get(0).getCode());
	
	}
	
	
}
