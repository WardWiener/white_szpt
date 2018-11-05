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
import com.taiji.pubsec.szpt.test.TestBase;
//import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
//import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;
import com.taiji.pubsec.szpt.zhzats.bean.InterrogatSituInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class InterrogatSituAnalyzeTsServiceTestCase extends TestBase {

	@Resource
	private InterrogatSituAnalyzeTsService interrogatSituAnalyzeTsService;
	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/interrogatSitu/findInterrogatSituByPcsCodes-setup.xml")
	public void testFindInterrogatSituByPcsCodes() {
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
		String[] pcsCodeLst = new String[2];
		pcsCodeLst[0]="0001";
		pcsCodeLst[1]="0002";
		List<InterrogatSituInfo> situInfo = interrogatSituAnalyzeTsService.findInterrogatSituByPcsCodes(startDay, endDay, pcsCodeLst);
		Assert.assertEquals(new Integer(31), situInfo.get(0).getNewManpowerSum());
		Assert.assertEquals(new Integer(12), situInfo.get(0).getNewCarNotKySum());
		Assert.assertEquals(new Integer(15), situInfo.get(0).getNewCarSum());
		Assert.assertEquals("经开分局", situInfo.get(0).getName());
		
		Assert.assertEquals(new Integer(60), situInfo.get(1).getNewManpowerSum());
		Assert.assertEquals(new Integer(9), situInfo.get(1).getNewCarNotKySum());
		Assert.assertEquals(new Integer(18), situInfo.get(1).getNewCarSum());
		Assert.assertEquals("小河分局", situInfo.get(1).getName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/interrogatSitu/findInterrogatSituByPcsCodes-setup.xml")
	public void testFindInterrogatSituByPcsCodesOneDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-10 10:09:10");
			endDay =  sdf.parse("2016-10-11 10:09:10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] pcsCodeLst = new String[2];
		pcsCodeLst[0]="0001";
		pcsCodeLst[1]="0002";
		List<InterrogatSituInfo> situInfo = interrogatSituAnalyzeTsService.findInterrogatSituByPcsCodes(startDay, endDay, pcsCodeLst);
		Assert.assertEquals(new Integer(18), situInfo.get(0).getNewManpowerSum());
		Assert.assertEquals(new Integer(1), situInfo.get(0).getNewCarNotKySum());
		Assert.assertEquals(new Integer(2), situInfo.get(0).getNewCarSum());
		Assert.assertEquals("经开分局", situInfo.get(0).getName());
	}
	
	//当派出所编码等于空时,查询所有派出所
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/interrogatSitu/findInterrogatSituByPcsCodes-setup.xml")
	public void testFindInterrogatSituByPcsCodesAndPcsCodesEqNull() {
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
		List<InterrogatSituInfo> situInfo = interrogatSituAnalyzeTsService.findInterrogatSituByPcsCodes(startDay, endDay, null);
		Assert.assertEquals(new Integer(31), situInfo.get(0).getNewManpowerSum());
		Assert.assertEquals(new Integer(12), situInfo.get(0).getNewCarNotKySum());
		Assert.assertEquals(new Integer(15), situInfo.get(0).getNewCarSum());
		Assert.assertEquals("经开分局", situInfo.get(0).getName());
		
		Assert.assertEquals(new Integer(60), situInfo.get(1).getNewManpowerSum());
		Assert.assertEquals(new Integer(9), situInfo.get(1).getNewCarNotKySum());
		Assert.assertEquals(new Integer(18), situInfo.get(1).getNewCarSum());
		Assert.assertEquals("小河分局", situInfo.get(1).getName());
	}
	
}
