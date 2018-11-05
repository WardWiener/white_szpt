package com.taiji.pubsec.szpt.dpp.surveillance.clue.message;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.ClueProcessService;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ClueInfo;

public class ClueReceiver extends KafkaReceiver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClueReceiver.class);

	@Resource
	protected ClueProcessService clueProcessService ;

	@Override
	public void processData(byte[] message) {
		
		LOGGER.debug("接收线索");
		
		Object obj = SerializeUtils.unserialize(message);
		ClueInfo clue = (ClueInfo)obj; 
		
		clueProcessService.process(clue);
	}
	
	
}
