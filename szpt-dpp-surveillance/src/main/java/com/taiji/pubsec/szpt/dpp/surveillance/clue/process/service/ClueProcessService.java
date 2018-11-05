package com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service;

import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ClueInfo;

public interface ClueProcessService {
	/**
	 * 处理线索
	 * @param clueInfo
	 */
	public void process(ClueInfo clueInfo) ;

	public void process(ClueInfo[] clueInfos) ;

	public void sendResult(SurveilListResult result) ;

	public void setKafkaProducer(KafkaProducer kafkaProducer) ;
}
