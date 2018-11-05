package com.taiji.pubsec.szpt.zhzats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.cometd.bayeux.server.Authorizer.Result;
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
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.model.JqVideo;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class YanpanServiceTestCase extends TestBase {

	@Resource
	private YanpanService yanpanService;
	
	@Test
	@DatabaseSetup("classpath:dataset/factJq/factJqList-setup.xml")
	public void testFindXsjqByPage() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");
		Date endDay = sdf.parse("2016-11-10 10:09:10");

		List<String> jqTypes = new ArrayList<String>();
		jqTypes.add("jqlx001");
		jqTypes.add("jqlx002");
		jqTypes.add("jqlx003");
		
		Map<String,Object> params = new HashMap<String,Object>();
//		Pager<FactJq> jqLst = yanpanService.findXsjqByPage(params, 0, 2);
//		Assert.assertEquals(10, jqLst.getTotalNum().intValue());
//		Assert.assertEquals(2, jqLst.getPageList().size());
//		Assert.assertEquals("2016-10-19 09:09:10", sdf.format(jqLst.getPageList().get(0).getOccurrenceTime()));
//		Assert.assertEquals("2016-10-18 09:09:10", sdf.format(jqLst.getPageList().get(1).getOccurrenceTime()));
//		FactJq jq = jqLst.gget(1);
//		Assert.assertEquals("002",jq.getId());
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
//		Assert.assertEquals(new Double(48.1) ,jq.getLongitude());
//		Assert.assertEquals(new Double(45.0) ,jq.getLatitude());
//		Assert.assertEquals(occurrenceTime,jq.getOccurrenceTime());
//		FactJq jq0 = jqLst.get(0);
//		Assert.assertEquals("抢劫",jq0.getJqlxName());
//		Assert.assertEquals(3,jqLst.size());
	}	
	
	@Test
	@DatabaseSetup("classpath:dataset/jqvideo/jqvideo-setup.xml")
	public void testQueryJqVideo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<JqVideo> reslut = yanpanService.queryJqVideo("jqbm001");
		Assert.assertEquals(1, reslut.size());
		JqVideo vd = reslut.get(0);
		Assert.assertEquals("001",vd.getId());
		Assert.assertEquals("jqbm001",vd.getJqCode());
		Assert.assertEquals("1",vd.getFileId());
		Assert.assertEquals("mc1",vd.getFileName());
		Assert.assertEquals("1",vd.getFileType());
		Assert.assertEquals("bfdz001",vd.getPlayAdd());
		Assert.assertEquals("yldz001",vd.getShowAdd());
		Assert.assertEquals("jh01",vd.getProducerNum());
		Assert.assertEquals("xm01",vd.getProducerName());
		Assert.assertEquals("dw01",vd.getProducerDwCode());
		Assert.assertEquals("2016-10-10 10:10:10",sdf.format(vd.getStartDate()));
		Assert.assertEquals("2016-10-10 11:10:10",sdf.format(vd.getEndDate()));
		
		Assert.assertEquals("123",vd.getVideoLength());
		Assert.assertEquals("321",vd.getSaveVideoLength());
		Assert.assertEquals(200,vd.getFileSize().intValue());
		Assert.assertEquals("0",vd.getStatus());
		Assert.assertEquals("2016-10-10 09:09:10",sdf.format(vd.getUpdateDate()));
	}
	
	
}
