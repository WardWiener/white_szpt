package com.taiji.pubsec.szpt.dpp.surveillance.clue.message;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.ClueProcessService;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgInfo;

/**
 * @author huangcheng
 */
public class ImgClueReceiver extends KafkaReceiver{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgClueReceiver.class);
	
	@Resource
	private ClueProcessService clueProcessService ;

	@Override
	public void processData(byte[] message) {
		
		LOGGER.debug("从内外网接收图片线索");
		
		Object obj = SerializeUtils.unserialize(message);
		ImgInfo[] infos = (ImgInfo[])obj; 
		this.clueProcessService.process(infos);
	}
}
