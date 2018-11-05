package com.taiji.pubsec.szpt.score.compute.caseanalysis.receiver;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.service.CaseScoreService;

public class CaseScoreComputeReceiver extends KafkaReceiver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseScoreComputeReceiver.class);

	@Resource
	protected CaseScoreService caseScoreService;
	@Resource
	protected KafkaProducer kafkaProducer;
	
	private String resultTopic;
	
	@Override
	public void processData(byte[] message) {
		//传入两个参数，主案件id（必有）和比对案件id列表（可选）
		
//		Object obj = (Map)SerializeUtils.unserialize(message);
		
		String mainCaseCode = new String(message);
		LOGGER.debug("main case code to pass : {}", mainCaseCode);
		caseScoreService.processScoreCompute(mainCaseCode);
	}

	public String getResultTopic() {
		return resultTopic;
	}

	public void setResultTopic(String resultTopic) {
		this.resultTopic = resultTopic;
	}

}
