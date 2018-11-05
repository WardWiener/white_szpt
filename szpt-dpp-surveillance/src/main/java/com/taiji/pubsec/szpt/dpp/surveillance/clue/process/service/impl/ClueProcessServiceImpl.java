package com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.ClueProcessService;
import com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.ClueSubjectProcessService;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ClueInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClueProcessServiceImpl implements ClueProcessService, ApplicationContextAware{

	private static final Logger LOGGER = LoggerFactory.getLogger(ClueProcessServiceImpl.class) ;
	
	Map<String, ClueSubjectProcessService> processors = new HashMap<String, ClueSubjectProcessService>() ;

	protected KafkaProducer kafkaProducer ;

	@Override
	public void process(ClueInfo clueInfo) {
		for(Map.Entry<String, ClueSubjectProcessService> entry:processors.entrySet()){
			ClueSubjectProcessService processor = entry.getValue() ;
			List<SurveilListResult> list = processor.process(clueInfo) ;
			if(list!=null && list.size()>0){
				for(SurveilListResult result:list){
					this.sendResult(result);
				}
			}
		}
	}

	@Override
	public void sendResult(SurveilListResult result) {
		LOGGER.debug("结果发送到业务端：id:" + result.getId());
		if(StringUtils.isBlank(result.getId())){
			LOGGER.error("结果的id为空");
			return ;
		}
		kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_RESULT_TO_BUSINESS(), SerializeUtils.serialize(result.getId()));
	}

	@Resource
	@Override
	public void setKafkaProducer(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer ;
	}

	@Override
	public void process(ClueInfo[] clueInfos) {
		for(ClueInfo clue:clueInfos){
			this.process(clue);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		processors = applicationContext.getBeansOfType(ClueSubjectProcessService.class) ;
	}
}
