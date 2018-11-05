package com.taiji.pubsec.szpt.zagl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
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
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessage;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessageStickRecord;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class SpecialCaseMessageServiceTestCase extends TestBase {

	@Resource
	private SpecialCaseMessageService specialCaseMessageService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/addMessage-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMessage/addMessage-expected.xml", table = "t_zagl_zaly",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void addMessage() throws ParseException {
		SpecialCaseMessage message = new SpecialCaseMessage();
		message.setContent("nr");
		message.setCreatedTime(DateUtils.parseDate("2016-10-10 10:10:10", "yy-MM-dd HH:mm:ss"));
		message.setCreatePerson((Person) dao.findById(Person.class, "ryId"));
		message.setSpecialCase((SpecialCase) dao.findById(SpecialCase.class, "zazlId"));
		
		specialCaseMessageService.addMessage(message);
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/deleteMessage-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMessage/deleteMessage-expected.xml", table = "t_zagl_zaly",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void deleteMessage() {
		specialCaseMessageService.deleteMessage("zalyId");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/findMessageByConditions-setup.xml")
	public void findMessageByConditions() throws ParseException {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("pageNo", 0);
		conditions.put("pageSize", 3);
		conditions.put("createTimeStart", "1475633410000");
		conditions.put("createTimeEnd", "1476497410000");
		Pager<SpecialCaseMessage> mPager = specialCaseMessageService.findMessageByConditions(conditions, 0, 3);
		Assert.assertEquals(6, mPager.getTotalNum().intValue());
		Assert.assertEquals(3, mPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/findMessgeByPerson-setup.xml")
	public void findMessgeByPerson() {
		Pager<SpecialCaseMessage> mPager = specialCaseMessageService.findMessgeByPerson("ryId", "zaId", 0, 3);
		Assert.assertEquals(0, mPager.getTotalNum().intValue());
		Assert.assertEquals(0, mPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/findStickyMessage-setup.xml")
	public void findStickyMessage() {
		List<SpecialCaseMessageStickRecord> mList = specialCaseMessageService.findStickyMessage("ryId", "zazlId");
		Assert.assertEquals(6, mList.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/saveStickMessage-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMessage/saveStickMessage-expected.xml", table = "t_zagl_lyzdjl",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void saveStickMessage() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		specialCaseMessageService.stickMessage("zalyId", "ryId", sdf.parse("2016-10-5 10:10:10"));
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/saveUnstickMessage-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMessage/saveUnstickMessage-expected.xml", table = "t_zagl_lyzdjl",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void saveUnstickMessage() {
		specialCaseMessageService.unstickMessage("zalyId", "ryId");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/findById-setup.xml")
	public void findById() {
		SpecialCaseMessage message = specialCaseMessageService.findById("zalyId");
		Assert.assertEquals("nr", message.getContent());
		Assert.assertEquals("ryId", message.getCreatePerson().getId());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/findStickRecordByPersonIdAndMessageId-setup.xml")
	public void findStickRecordByPersonIdAndMessageId() {
		boolean flag = specialCaseMessageService.findStickRecordByPersonIdAndMessageId("ryId", "zalyId");
		Assert.assertEquals(true, flag);
	}
	
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMessage/findMessageByCondition-setup.xml")
	public void findMessageByCondition() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("flag", false);
		Map<String, List<SpecialCaseMessage>> messages = specialCaseMessageService.findMessageByCondition(conditions);
		Assert.assertEquals(1, messages.size());
	}
}
