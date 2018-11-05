package com.taiji.pubsec.szpt.ajgl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.ajgl.model.CaseExecutionProcess;
import com.taiji.pubsec.szpt.ajgl.service.ICaseExecutionProcessService;

@Service("caseExecutionProcessService")
public class CaseExecutionProcessServiceImpl implements ICaseExecutionProcessService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseExecutionProcess> findCaseExecutionProcessByCaseAndSuspect(String caseId, String suspectName) {
		String xql = "select c from CaseExecutionProcess as c where c.caseId = :caseId and c.suspectName = :suspectName order by c.stepExecuteTime desc";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseId", caseId);
		xqlMap.put("suspectName", suspectName);
		return dao.findAllByParams(CaseExecutionProcess.class, xql, xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseExecutionProcess> findCaseExecutionProcessByCase(String caseId) {
		String xql = "select c from CaseExecutionProcess as c where c.caseId = :caseId order by c.stepExecuteTime desc";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseId", caseId);
		return dao.findAllByParams(CaseExecutionProcess.class, xql, xqlMap);
	}

}
