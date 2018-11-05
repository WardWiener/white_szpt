package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalFord;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalFordService;

@Service("CriminalFordService")
public class CriminalFordServiceImpl implements CriminalFordService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalFord> findCriminalFordsByCaseCode(String caseCode) {
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseCode", caseCode);
		String xql = "select c from CriminalFord as c where c.basicCase.caseCode =:caseCode";
		return dao.findAllByParams(CriminalFord.class, xql, xqlMap);
	}

}
