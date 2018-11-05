package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.SufferCaseRelation;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.SufferCaseRelationService;

@Service("sufferCaseTagRelationService")
public class SufferCaseRelationServiceImpl implements SufferCaseRelationService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SufferCaseRelation> findSufferCaseRelationsByCaseCode(String caseCode) {
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseCode", caseCode);
		String xql = "select s from SufferCaseRelation as s where s.basicCase.caseCode =:caseCode";
		return dao.findAllByParams(SufferCaseRelation.class, xql, xqlMap);
	}

}
