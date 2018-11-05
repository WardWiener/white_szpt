package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseSupectRelationService;

@Service("caseSupectRelationService")
public class CaseSupectRelationServiceImpl implements CaseSupectRelationService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseSupectRelation> findCaseSupectRelationsByCaseCode(String caseCode) {
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseCode", caseCode);
		String xql = "select c from CaseSupectRelation as c where c.basicCase.caseCode =:caseCode";
		return dao.findAllByParams(CaseSupectRelation.class, xql, xqlMap);
	}

}
