package com.taiji.pubsec.szpt.operationrecord.service;

import javax.annotation.Resource;

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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class StandardLogRecordServiceTestCase extends TestBase{

	@Resource
	private StandardLogRecordService logRecordService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/addLogRecord-expected.xml", table="t_jzrzjl", assertionMode = DatabaseAssertionMode.NON_STRICT)
	})
	public void testAddLog() {
		StandardLogRecord r = new StandardLogRecord();
		r.setUserId("uid");
		r.setUnitName("unitname");
		r.setUnitCode("unitcode");
		r.setOperator("username");
		r.setOperateTime("20161216102245");
		r.setTerminalId("terminalid");
		r.setOperationType(StandardLogRecord.OPERATETYPE_QUERY);
		r.setResult(StandardLogRecord.OPERATERESULT_SUCCESS);
		r.setErrorCode("1000");
		r.setFunctionModule("funcname");
		r.setCondition("name='test'");
		logRecordService.save(r);
		dao.flush();
	}
	//query = "select User_ID,Organization,Organization_ID,User_Name,Operate_Time,Terminal_ID,Operate_Type,Operate_Result,Error_Code,Operate_Name,Operate_Condition from t_jzrzjl", 
	
}
