package com.taiji.pubsec.szpt.zhzats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zhzats.bean.DailyReportPersonInfo;
import com.taiji.pubsec.szpt.zhzats.model.DailyReportPerson;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class DailyReportPersonTsServiceTestCase extends TestBase {

	@Resource
	private DailyReportPersonTsService dailyReportPersonTsService;

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/dailyReportPerson/findDailyReportPerson-setup.xml")
	public void testFindDailyReportPersonByPcs() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");;
		Date endDay = sdf.parse("2016-11-10 10:09:10");;
		String[] pcsCodes = new String[4];
		pcsCodes[0] =  "jkfj001";
		pcsCodes[1] =  "xhfj001";
		pcsCodes[2] =  "sjfj001";
		
		 List<DailyReportPersonInfo> dailyReportPersonInfoLst = dailyReportPersonTsService.findDailyReportPerson(startDay, endDay, pcsCodes,"");
		
		 Assert.assertEquals(3,dailyReportPersonInfoLst.size());
		 DailyReportPersonInfo info = dailyReportPersonInfoLst.get(0);
		 Assert.assertEquals("经开分局",info.getName());
		 Assert.assertEquals(1, info.getCount().intValue());
	}	
	
	@Test
	@DatabaseSetup("classpath:dataset/dailyReportPerson/findDailyReportPerson-setup.xml")
	public void testFindDailyReportPersonByZhuge() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");;
		Date endDay = sdf.parse("2016-11-10 10:09:10");
		List<DailyReportPersonInfo> dailyReportPersonInfoLst = dailyReportPersonTsService.findDailyReportPerson(startDay, endDay, null, "主格");
		
		Assert.assertEquals(1, dailyReportPersonInfoLst.size());
		DailyReportPersonInfo info = dailyReportPersonInfoLst.get(0);
		Assert.assertEquals("主格02",info.getName());
		Assert.assertEquals(2, info.getCount().intValue());

	}	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/dailyReportPerson/findDailyReportPerson-setup.xml")
	public void testDailyReportPersonTsList() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = sdf.parse("2016-10-10 10:09:10");;
		Date endDay = sdf.parse("2016-11-10 10:09:10");;

		String[] pcsCodes = new String[4];
		pcsCodes[0] =  "jkfj001";
		pcsCodes[1] =  "xhfj001";
		pcsCodes[2] =  "sjfj001";
		pcsCodes[3] =  "pqfj001";
		
		List<DailyReportPerson> dailyReportPersonLst = dailyReportPersonTsService.dailyReportPersonTsList(startDay, endDay, pcsCodes);
		Assert.assertEquals(4,dailyReportPersonLst.size());
		Collections.sort(dailyReportPersonLst,new PriceComparator());
		DailyReportPerson person = dailyReportPersonLst.get(0);
		Assert.assertEquals("001",person.getId());
		Assert.assertEquals("001",person.getPersonJh());
		Assert.assertEquals("小明",person.getPersonName());
		Assert.assertEquals("zhdy001",person.getOrderCellId());
		Assert.assertEquals("jkfj001",person.getOrderCellCode());
	    Assert.assertEquals("经开分局",person.getOrderCellName());
	}
	
	
	
	
}

//自定义比较器：按书的价格排序  
 class PriceComparator implements Comparator {  
    public int compare(DailyReportPerson object1, DailyReportPerson object2) {// 实现接口中的方法  
        return new Integer(Integer.valueOf(object1.getId()) ).compareTo(Integer.valueOf(object2.getId()));  
    }

	@Override
	public int compare(Object o1, Object o2) {
		DailyReportPerson info1 = (DailyReportPerson) o1;
		DailyReportPerson info2 = (DailyReportPerson) o2;
        return new Integer(Integer.valueOf(info1.getId()) ).compareTo(Integer.valueOf(info2.getId()));  
	}  
} 

