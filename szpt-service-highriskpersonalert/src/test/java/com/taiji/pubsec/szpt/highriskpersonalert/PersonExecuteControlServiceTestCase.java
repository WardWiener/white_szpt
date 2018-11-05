package com.taiji.pubsec.szpt.highriskpersonalert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonMobileInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class PersonExecuteControlServiceTestCase extends TestBase{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PersonExecuteControlServiceTestCase.class);
	
	@Resource
	private IPersonExecuteControlService personExecuteControlService;
	@Resource
	private Dao dao;
	
	@Test
	public void test() {
		Assert.assertEquals(1, 1);
		List<String> list = new ArrayList<String>();
		Assert.assertFalse(ParamMapUtil.isNotBlank(list));
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/personExecuteControl/findPersonExecuteControlByCondition-setup.xml")
	public void textFindPersonExecuteControlByCondition() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", "bh001");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDay = null;
		Date endDay = null;
		try {
			startDay =  sdf.parse("2016-10-14 10:11:10");
			endDay =  sdf.parse("2016-10-14 10:11:11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("timeStart",startDay);
		map.put("timeEnd",endDay);
		map.put("personName", "小");
		map.put("personIdcode", "sfzh001");
		map.put("note", "你");//备注
		map.put("status", "启用");//数据字典：启用；失效
		Pager<PersonExecuteControl> PersonExecuteControlLsit=personExecuteControlService.findPersonExecuteControlByCondition(map,0,20);
		Assert.assertEquals(1,PersonExecuteControlLsit.getPageList().size());
		PersonExecuteControl info = PersonExecuteControlLsit.getPageList().get(0);
		Assert.assertEquals("小明",info.getPersonName());
		Assert.assertEquals("你好吗",info.getNote());
		Assert.assertEquals("启用",info.getStatus());
	}
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/personExecuteControl/savePersonExecuteControl-expected.xml", table = "t_gwry_rybk",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void textSavePersonExecuteControl() {
		List<PersonMobileInfo> pMobileList=new ArrayList<PersonMobileInfo>();
		PersonMobileInfo mobile=new PersonMobileInfo();
		mobile.setId("ydid004");
		mobile.setNumber("13680808855");
		mobile.setMac("mac地址4");
		pMobileList.add(mobile);
		PersonMobileInfo mobile1=new PersonMobileInfo();
		mobile1.setId("ydid005");
		mobile1.setNumber("13680808555");
		mobile1.setMac("mac地址5");
		pMobileList.add(mobile1);
		PersonExecuteControl person=new PersonExecuteControl();
		person.setNum("bh004");
		person.setIdCardNo("sfzh0004");
		person.setPersonName("小龙");
		personExecuteControlService.savePersonExecuteControl(person,pMobileList);
		this.dao.flush();
		Assert.assertEquals(person,mobile.getPersonExecuteControl());
		Assert.assertEquals(person,mobile1.getPersonExecuteControl());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	//@DatabaseSetup("classpath:dataset/personExecuteControl/savePersonExecuteControl-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/personExecuteControl/savePersonExecuteControl-expected.xml", table = "t_gwry_rybk",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void textSaveOperationRecord() {
		OperationRecord mobile=new OperationRecord(new PersonCheckInfo());
		mobile.setContent("操作内容");
		String s=personExecuteControlService.saveOperationRecord(mobile);
		this.dao.flush();
		OperationRecord op=(OperationRecord) dao.findById(OperationRecord.class, s);
		Assert.assertEquals("操作内容",op.getContent());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/personExecuteControl/findOperationRecord-setup.xml")
	public void textFindOperationRecordByPersonExecuteControl() {
		List<OperationRecord> list=personExecuteControlService.findOperationRecordByPersonExecuteControl("ywdxid001");
		Assert.assertEquals(1,list.size());
		OperationRecord info = list.get(0);
		Assert.assertEquals("小红他哥",info.getOperator());
	}

}
