package com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.ClueSubjectDeployService;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;

@Service
public class InternetBarSubjectDeployServiceImpl implements ClueSubjectDeployService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InternetBarSubjectDeployServiceImpl.class);
	
	@Override
	public boolean deploy(SurveilListInfo surveilListInfo, String operate) {
		LOGGER.debug("部署网吧线索开始");
		LOGGER.debug("部署网吧线索结束");
		return true;
	}
}
