package com.taiji.pubsec.szpt.ajgl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;
import com.taiji.pubsec.szpt.ajgl.service.ISufferCaseRelationService;

@Service("sufferCaseRelationService")
public class SufferCaseRelationServiceImpl implements ISufferCaseRelationService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public SufferCaseRelation findSufferCaseRelationById(String relationId) {
		return (SufferCaseRelation) this.dao.findById(SufferCaseRelation.class, relationId);
	}

}
