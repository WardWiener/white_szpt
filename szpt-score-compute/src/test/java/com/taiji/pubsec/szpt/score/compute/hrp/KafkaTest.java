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
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hrp.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public class KafkaTest {
	
	@Resource
	private KafkaProducer kafkaProducer ;

	@Test
	public void testHrpCompute(){
		toTestHrpCompute() ;
	}
	
	private void toTestHrpCompute(){
		ScoreComputeMsg msg = new ScoreComputeMsg() ;
		
		msg.setComputeProcessId(Constant.PARAMETER_TAG_COMPUTEPROCESS_ID);
		msg.setScoreComputeObj("testHrpId1");
		
		kafkaProducer.sendData("topic-scorecompute-hp", SerializeUtils.serialize(msg));
	}
	
}
