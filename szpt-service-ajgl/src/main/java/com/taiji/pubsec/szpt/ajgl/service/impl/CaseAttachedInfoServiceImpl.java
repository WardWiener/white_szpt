package com.taiji.pubsec.szpt.ajgl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.ajgl.model.CaseAttachedInfo;
import com.taiji.pubsec.szpt.ajgl.service.ICaseAttachedInfoService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("caseAttachedInfoService")
public class CaseAttachedInfoServiceImpl  implements ICaseAttachedInfoService{
	
	@SuppressWarnings("rawtypes")
	@Resource 
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public void createCaseAttachedInfo(CaseAttachedInfo caseAttachedInfo, String caseId) {
		CaseAttachedInfo caseInfo = this.findByCaseCode(caseId);
		if(caseInfo != null){
			caseInfo.setDoPerson(caseAttachedInfo.getDoPerson());
			caseInfo.setOneRefundInvestigationTime(caseAttachedInfo.getOneRefundInvestigationTime());
			caseInfo.setTwoRefundInvestigationTime(caseAttachedInfo.getTwoRefundInvestigationTime());
			this.dao.update(caseInfo);
		}else{
			caseInfo = new CaseAttachedInfo();
			caseInfo.setCaseCode(caseId);
			caseInfo.setDoPerson(caseAttachedInfo.getDoPerson());
			caseInfo.setOneRefundInvestigationTime(caseAttachedInfo.getOneRefundInvestigationTime());
			caseInfo.setTwoRefundInvestigationTime(caseAttachedInfo.getTwoRefundInvestigationTime());
			this.dao.save(caseInfo);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateCaseAttachedInfo(CaseAttachedInfo caseAttachedInfo) {
		dao.update(caseAttachedInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseAttachedInfo> findCaseAttachedInfoByCondition(Map<String, Object> paramMap) {
		StringBuilder xql = new StringBuilder("select ci from CaseAttachedInfo as ci where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(paramMap.get("caseClass"))) {
			xql.append(" and ci.basicCase.caseClass = :caseClass");
			xqlMap.put("caseClass", paramMap.get("caseClass"));
		}
		// TODO  查询日期
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			
		}
		// TODO  查询日期
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseAttentionState"))) {
			xql.append(" and ci.caseAttentionState = :caseAttentionState");
			xqlMap.put("caseAttentionState", paramMap.get("caseAttentionState"));
		}
		//TODO 办案单位
		if(ParamMapUtil.isNotBlank(paramMap.get("attendUnit"))) {
			
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseName"))) {
			xql.append(" and ci.basicCase.caseName like :caseName");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("caseName", "%" + SQLTool.SQLSpecialChTranfer((String) paramMap.get("caseName")) + "%");
			
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("note"))) {
			xql.append(" and ci.note like :note");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("note", "%" + SQLTool.SQLSpecialChTranfer((String) paramMap.get("note")) + "%");
		}
		return dao.findAllByParams(CaseAttachedInfo.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseAttachedInfo findByCaseCode(String caseCode) {
		String xql = "select c from CaseAttachedInfo as c where c.caseCode = ?";
		return (CaseAttachedInfo) this.dao.findByParams(CaseAttachedInfo.class, xql, new Object[]{caseCode});
	}

}
