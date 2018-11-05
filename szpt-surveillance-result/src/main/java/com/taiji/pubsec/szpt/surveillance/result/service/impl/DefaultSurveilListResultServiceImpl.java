package com.taiji.pubsec.szpt.surveillance.result.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.service.SurveilListResultService;

@Service
public class DefaultSurveilListResultServiceImpl implements SurveilListResultService{

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public void save(SurveilListResult entity) {
		this.dao.save(entity);
	}
	
	
}
