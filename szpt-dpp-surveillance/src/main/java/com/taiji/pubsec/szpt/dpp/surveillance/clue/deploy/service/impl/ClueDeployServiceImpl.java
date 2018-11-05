package com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.ClueDeployService;
import com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service.ClueSubjectDeployService;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;

@Service
public class ClueDeployServiceImpl implements ClueDeployService, ApplicationContextAware{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClueDeployServiceImpl.class);
	
	Map<String, ClueSubjectDeployService> deployers = new HashMap<String, ClueSubjectDeployService>() ;

	@Override
	public void deploy(SurveilListInfo surveilListInfo, String operate) {
		
		LOGGER.debug("部署线索开始：操作：" + operate);
		
		for(Map.Entry<String, ClueSubjectDeployService> entry:deployers.entrySet()){
			ClueSubjectDeployService deployer = entry.getValue() ;
			deployer.deploy(surveilListInfo, operate);
		}
		
		LOGGER.debug("部署线索结束：操作：" + operate);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		deployers = applicationContext.getBeansOfType(ClueSubjectDeployService.class) ;
	}
}
