package com.taiji.pubsec.szpt.score.compute.highriskperson.receiver;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;

public class HrpScoreComputeReceiver extends KafkaReceiver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HrpScoreComputeReceiver.class);
	
	@Resource
	private HrpScoreService hrpScoreService;

	@Override
	public void processData(byte[] message) {
		
		Object obj = SerializeUtils.unserialize(message);
		ScoreComputeMsg msg = (ScoreComputeMsg)obj;
		
		//获取异常，防止事物回滚，造成监控数据丢失	
		try {
			hrpScoreService.processHrpScoreComputeReceiverMsg(msg);
		} catch (Exception e) {
			LOGGER.error("高危人员积分异常", e);
		}
	}
}
