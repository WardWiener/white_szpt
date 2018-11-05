package com.taiji.pubsec.szpt.score.compute.hrp;

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
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-HrpScoreService.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public class HrpScoreServiceTest {

	@Resource
	private HrpScoreService hrpScoreService;
	
	@Test
	public void testProcessHrpScoreComputeReceiverMsg(){
		ScoreComputeMsg msg = new ScoreComputeMsg() ;
		msg.setComputeProcessId(Constant.SC_TASK_ID_HRP);
		msg.setScoreComputeObj("testHrpId1");
		hrpScoreService.processHrpScoreComputeReceiverMsg(msg);
	}
}
