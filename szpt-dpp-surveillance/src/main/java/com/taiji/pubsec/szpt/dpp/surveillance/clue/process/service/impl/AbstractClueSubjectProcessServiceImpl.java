package com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.ClueSubjectProcessService;
import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.service.SurveilListResultService;

@Service
public abstract class AbstractClueSubjectProcessServiceImpl implements ClueSubjectProcessService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractClueSubjectProcessServiceImpl.class);
	
	protected SurveilListService surveilListService ;
	
	protected SurveilListResultService surveilListResultService ;

	@Override
	public SurveilListResult saveSurveilListResultInfo(SurveilListResult result) {
		surveilListResultService.save(result);
		LOGGER.debug("保存结果：" + result.getClueType() + " id:" + result.getId());
		return result ;
	}
}
