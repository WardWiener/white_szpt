package com.taiji.pubsec.szpt.caseanalysis.score.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.szpt.kafka.KafkaReceiver;

/**
 * 接收来自案件积分计算的结果
 * @author genganpeng
 *
 */
public class CaseScoreComputeResultReceiver extends KafkaReceiver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseScoreComputeResultReceiver.class);
	


	@Override
	public void processData(byte[] message) {
		
		String mainCaseCode = new String(message);
		
		/**
		 * 计算成功回来的是mainCaseCode，计算失败发送回来的是mainCaseCode+无积分计算模板！
		 * 参见CaseScoreServiceImpl的125行
		 */
		LOGGER.debug("receive main case code:{}", mainCaseCode);
		
	}
}
