package com.taiji.pubsec.szpt.caseanalysis.score.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.MessageResultBean;
import com.taiji.pubsec.szpt.test.TestBase;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CaseScoreResultMessageServiceTestCase extends TestBase {

	@Resource
	private CaseScoreResultMessageService caseScoreResultMessageService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/caseScoreResultMessage/findMessageByConditions-setup.xml")
	public void findMessageByConditions() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> conditions = new HashMap<String, Object>();
		List<String> caseCodeLists = new ArrayList<String>();
		caseCodeLists.add("ajbh1");
		caseCodeLists.add("ajbh2");
		caseCodeLists.add("ajbh3");
		caseCodeLists.add("ajbh4");
		conditions.put("caseCodeLists", caseCodeLists);
		conditions.put("type", "ajlb");
		conditions.put("caseState", "ajzt");
		conditions.put("handlePolice", "bar");
		conditions.put("firstSort", "ajxzyj");
		conditions.put("secondSort", "ajxzej");
		conditions.put("caseTimeStartFrom", sdf.parse("2010-10-5 10:10:10"));
		conditions.put("caseTimeStartTo", sdf.parse("2010-10-10 15:10:10"));
		conditions.put("systemAutoPushTimeFrom", sdf.parse("2010-10-5 10:10:10"));
		conditions.put("systemAutoPushTimeTo", sdf.parse("2010-10-15 10:10:10"));
		Pager<MessageResultBean> beanPager = caseScoreResultMessageService.findMessageByConditions(conditions, 0, 3);
		Assert.assertEquals(4, beanPager.getTotalNum().intValue());
		Assert.assertEquals(3, beanPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseScoreResultMessage/receiveMessage-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/caseScoreResultMessage/receiveMessage-expected.xml", table = "t_xsajfx_cbafxjgxx",  assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void receiveMessage() {
		boolean flag = caseScoreResultMessageService.receiveMessage("xxId");
		dao.flush();
		Assert.assertEquals(true, flag);
	}
}
