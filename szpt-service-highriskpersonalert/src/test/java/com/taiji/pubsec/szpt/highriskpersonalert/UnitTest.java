package com.taiji.pubsec.szpt.highriskpersonalert;

import java.util.Date;
import java.util.Map;

import org.junit.Assert;
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
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;
import com.taiji.pubsec.szpt.test.TestBase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class UnitTest extends TestBase{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UnitTest.class);
	
	@Test
	public void test() {
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void springContextTest() {
		 Map<String, PersonnelTrackService> beanMap = SpringContextUtil.getApplicationContext().getBeansOfType(PersonnelTrackService.class);
		for(Map.Entry<String, PersonnelTrackService> entry : beanMap.entrySet()) {
			if(entry.getKey().equals("defaultPersonnelTrackService")) {
				LOGGER.debug("哇哈哈");
			}else{
				LOGGER.debug("key:" + entry.getKey() + "\t" + "value:" + entry.getValue());
			}
		}
		 LOGGER.debug(beanMap.toString());
	}
	
	@Test
	public void dateFormatTest() {
		String dateStr = HighriskPersonUtil.highriskPersonTrackDateFormat(new Date());
		String timeStr = HighriskPersonUtil.highriskPersonTrackTimeFormat(new Date());
		LOGGER.debug(dateStr);
		LOGGER.debug(timeStr);
	}
	
	@Test
	public void properitiesTest() {
		LOGGER.debug(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY);
	}

}
