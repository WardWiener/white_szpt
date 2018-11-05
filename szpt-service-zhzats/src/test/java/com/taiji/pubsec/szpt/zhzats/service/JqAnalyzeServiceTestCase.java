package com.taiji.pubsec.szpt.zhzats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.taiji.pubsec.szpt.zhzats.model.JqAnalyze;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class JqAnalyzeServiceTestCase extends TestBase {

	@Resource
	private JqAnalyzeService JqAnalyzeService;
	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/jqAnalyze/findJqAnalyzeById-setup.xml")
	public void testFindJqAnalyzeById() {
		JqAnalyze jqAnalyze = JqAnalyzeService.findJqAnalyzeById("001");
		Assert.assertEquals("001", jqAnalyze.getId());
		Assert.assertEquals("圆的", jqAnalyze.getMaterialChara());
		Assert.assertEquals("撞击", jqAnalyze.getHarmedWay());
		Assert.assertEquals("窗户", jqAnalyze.getRunWay());
		Assert.assertEquals("门", jqAnalyze.getRunDirect());
		Assert.assertEquals("长发飘飘", jqAnalyze.getSuspectHair());
		Assert.assertEquals("黄色", jqAnalyze.getSuspectSkin());
		Assert.assertEquals("28", jqAnalyze.getSuspectAge());
		Assert.assertEquals("无", jqAnalyze.getSuspectOtherChara());
		Assert.assertEquals("175", jqAnalyze.getSuspectHeight());
		Assert.assertEquals("头戴丝袜", jqAnalyze.getSuspectCarryItemChara());
		Assert.assertEquals("微胖", jqAnalyze.getSuspectBody());
		Assert.assertEquals("未知", jqAnalyze.getSuspectSex());
		Assert.assertEquals("时髦", jqAnalyze.getSuspectClothChara());
		Assert.assertEquals("撞击", jqAnalyze.getSuspectCrimeWay());
		Assert.assertEquals("大锤", jqAnalyze.getSuspectCrimeTool());
		Assert.assertEquals("无", jqAnalyze.getCameras());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = null;
		try {
			time =  sdf.parse("2016-10-10 10:10:10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(time, jqAnalyze.getTimestamp());
	}
}
