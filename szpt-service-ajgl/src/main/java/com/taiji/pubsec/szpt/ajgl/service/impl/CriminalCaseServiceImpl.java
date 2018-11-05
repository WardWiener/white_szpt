package com.taiji.pubsec.szpt.ajgl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.ajgl.model.AlarmInfo;
import com.taiji.pubsec.szpt.ajgl.model.CaseAttachedInfo;
import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.ajgl.model.CriminalObject;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("criminalCaseService")
public class CriminalCaseServiceImpl implements ICriminalCaseService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<CriminalBasicCase> findCriminalCaseByCondition(Map<String, Object> paramMap, int pageNo,
			int pageSize) {
		StringBuilder xqlSelecta = new StringBuilder("select c from CriminalBasicCase as c where 1 = 1");
		StringBuilder xqlSelectb = new StringBuilder("select a.basicCase from AlarmInfo as a left join a.basicCase as c where 1 = 1");
		StringBuilder xqlWhere = new StringBuilder();
		
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("caseTimeStart"))) {
			xqlWhere.append(" and c.caseTimeEnd >= :caseTimeStart");
			xqlMap.put("caseTimeStart", paramMap.get("caseTimeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseTimeEnd"))) {
			xqlWhere.append(" and c.caseTimeStart < :caseTimeEnd");
			xqlMap.put("caseTimeEnd", paramMap.get("caseTimeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseCode"))) {
			xqlWhere.append(" and c.caseCode like :caseCode");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("caseCode", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("caseCode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseName"))) {
			xqlWhere.append(" and c.caseName like :caseName");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("caseName", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("caseName")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseSort"))) {
			xqlWhere.append(" and c.caseSort = :caseSort");
			xqlMap.put("caseSort", paramMap.get("caseSort"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseKind"))) {
			xqlWhere.append(" and c.caseKind = :caseKind");
			xqlMap.put("caseKind", paramMap.get("caseKind"));
		}
		//TODO 案件时段筛选条件
		
		if(ParamMapUtil.isNotBlank(paramMap.get("community"))) {
			xqlWhere.append(" and c.community = :community");
			xqlMap.put("community", paramMap.get("community"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseState"))) {
			xqlWhere.append(" and c.caseState in (:caseState)");
			xqlMap.put("caseState", paramMap.get("caseState"));
		}
		
		if(ParamMapUtil.isNotBlank(paramMap.get("caseAddress"))) {
			xqlWhere.append(" and c.caseAddress like :caseAddress");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("caseAddress", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("caseAddress")) + "%");
		}
		if ((ParamMapUtil.isNotBlank(paramMap.get("handingUnit"))) || (ParamMapUtil.isNotBlank(paramMap.get("popedom"))) || (ParamMapUtil.isNotBlank(paramMap.get("disposePerson")))) {
			if(ParamMapUtil.isNotBlank(paramMap.get("handingUnit"))) {
				xqlWhere.append(" and a.handingUnit = :handingUnit");
				xqlMap.put("handingUnit", paramMap.get("handingUnit"));
			}
			if(ParamMapUtil.isNotBlank(paramMap.get("popedom"))) {
				xqlWhere.append(" and a.popedom = :popedom");
				xqlMap.put("popedom", paramMap.get("popedom"));
			}
			if(ParamMapUtil.isNotBlank(paramMap.get("disposePerson"))){
				xqlWhere.append(" and (a.disposePerson like :disposePerson ");
				SQLTool.SQLAddEscape(xqlWhere);
				xqlMap.put("disposePerson", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("disposePerson")) + "%");
				xqlWhere.append(" or a.disposePerson2 like :disposePerson) ");
				SQLTool.SQLAddEscape(xqlWhere);
				xqlMap.put("disposePerson", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("disposePerson")) + "%");
			}
			return dao.findByPage(AlarmInfo.class, xqlSelectb.append(xqlWhere).toString(), xqlMap, pageNo, pageSize);
		}else {
			return this.dao.findByPage(CriminalBasicCase.class, xqlSelecta.append(xqlWhere).toString(), xqlMap, pageNo, pageSize);
		}
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public CriminalBasicCase findCriminalCaseById(String criminalCaseId) {
		return (CriminalBasicCase) this.dao.findById(CriminalBasicCase.class, criminalCaseId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CriminalBasicCase findCriminalCaseByCaseId(String caseId) {
		String xql = "select c from CriminalBasicCase as c where c.caseCode = :caseId";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("caseId", caseId);
		List<CriminalBasicCase> list = this.dao.findAllByParams(CriminalBasicCase.class, xql, xqlMap);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<String> findDistinctConditionValues(String comditionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AlarmInfo findAlarmInfoById(String alarmId) {
		return (AlarmInfo) this.dao.findById(AlarmInfo.class, alarmId);
	}

	@Override
	public Map<CriminalBasicCase, String> findRelatedCriminalBasicCaseById(String caseId) {
		Map<CriminalBasicCase, String> aMap = new HashMap<CriminalBasicCase, String>();
//		CriminalBasicCase case1 = (CriminalBasicCase) this.dao.findById(CriminalBasicCase.class, caseId);
//		List<CriminalBasicCase> cases = new ArrayList<CriminalBasicCase>();
//		String xql = "select cbc from CriminalBasicCase as cbc where cbc.mianCaseId = ?";
//		cases = this.dao.findAllByParams(CriminalBasicCase.class, xql, new Object[]{caseId});
//		if(!cases.isEmpty()){
//			for(CriminalBasicCase criminalBasicCase: cases){
//				
//			}
//		}
//		if(case1.getMianCaseId() != null){
//			CriminalBasicCase case2 = (CriminalBasicCase) this.dao.findById(CriminalBasicCase.class, case1.getMianCaseId());
//		}
		
		return aMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<CriminalBasicCase> findCriminalBasicCasesByQueryConditions(Map<String, Object> queryConditions,
			int pageNo, int pageSize) {
		
		StringBuilder xqlSelecta = new StringBuilder("select c from CriminalBasicCase as c where 1 = 1");
		StringBuilder xqlSelectb = new StringBuilder("select a.basicCase from AlarmInfo as a left join a.basicCase as c where 1 = 1");
		StringBuilder xqlWhere = new StringBuilder();
		
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(queryConditions.get("caseName"))) {
			xqlWhere.append(" and c.caseName like :caseName");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("caseName", "%" + SQLTool.SQLSpecialChTranfer((String)queryConditions.get("caseName")) + "%");
		}
		if(ParamMapUtil.isNotBlank(queryConditions.get("caseCode"))) {
			xqlWhere.append(" and c.caseCode like :caseCode");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("caseCode", "%" + SQLTool.SQLSpecialChTranfer((String)queryConditions.get("caseCode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(queryConditions.get("disposePerson"))){
			xqlWhere.append(" and (a.disposePerson like :disposePerson ");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("disposePerson", "%" + SQLTool.SQLSpecialChTranfer((String)queryConditions.get("disposePerson")) + "%");
			xqlWhere.append(" or a.disposePerson2 like :disposePerson) ");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("disposePerson", "%" + SQLTool.SQLSpecialChTranfer((String)queryConditions.get("disposePerson")) + "%");
			
			return dao.findByPage(AlarmInfo.class, xqlSelectb.append(xqlWhere).toString(), xqlMap, pageNo, pageSize);
		} else {
			return this.dao.findByPage(CriminalBasicCase.class, xqlSelecta.append(xqlWhere).toString(), xqlMap, pageNo, pageSize);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<CriminalBasicCase> findCriminalBasicCase(String nameOrCode, int start, int length) {
		StringBuilder xql = new StringBuilder("select c from CriminalBasicCase as c where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(nameOrCode)) {
			xql.append(" and (c.caseCode like :nameOrCode ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("nameOrCode", "%" + SQLTool.SQLSpecialChTranfer(nameOrCode) + "%");
			xql.append(" or c.caseName like :nameOrCode) ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("nameOrCode", "%" + SQLTool.SQLSpecialChTranfer(nameOrCode) + "%");
		}
		
		return this.dao.findByPage(CriminalBasicCase.class, xql.toString(), xqlMap, start, length);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SufferCaseRelation> findSufferCaseRelationByCase(String caseId) {
		String xql = "select scr from SufferCaseRelation as scr where scr.case_id = ?";
		return this.dao.findAllByParams(SufferCaseRelation.class, xql, new Object[]{caseId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalObject> findCriminalObjectByCase(String caseId) {
		String xql = "select co from CriminalObject as co where co.basicCase_id = ?";
		return this.dao.findAllByParams(CriminalObject.class, xql, new Object[]{caseId});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalBasicCase> findCriminalCaseByCaseSort(Map<String, Object> paramMap){
		StringBuilder xql = new StringBuilder("select c from CriminalBasicCase as c where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("startTime"))) {
			xql.append(" and c.caseTimeEnd >= :caseTimeStart");
			xqlMap.put("caseTimeStart", paramMap.get("startTime"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("endTime"))) {
			xql.append(" and c.caseTimeStart < :caseTimeEnd");
			xqlMap.put("caseTimeEnd", paramMap.get("endTime"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("caseSort"))) {
			xql.append(" and c.caseSort in (:caseSort)");
			xqlMap.put("caseSort", paramMap.get("caseSort"));
		}
		return this.dao.findAllByParams(CriminalBasicCase.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseAttachedInfo findCaseAttachedInfoById(String caseId) {
		String xql = "from CaseAttachedInfo as c where c.basicCase.caseCode = ?";
		return (CaseAttachedInfo) this.dao.findByParams(CaseAttachedInfo.class, xql, new Object[]{caseId});
	}

	@Override
	public List<CriminalBasicCase> findCriminalBasicCasesByCodeOrName(String nameOrCode) {
		StringBuilder xql = new StringBuilder("select c from com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase as c where 1 = 1 ");
		List<String> params = new ArrayList<String>();
		if(ParamMapUtil.isNotBlank(nameOrCode)) {
			xql.append(" and (c.caseCode like ? ");
			SQLTool.SQLAddEscape(xql);
			params.add("%"+SQLTool.SQLSpecialChTranfer(nameOrCode) + "%");
			xql.append(" or c.caseName like ? ");
			SQLTool.SQLAddEscape(xql);
			xql.append(" )");
			params.add("%"+SQLTool.SQLSpecialChTranfer(nameOrCode) + "%");
		}
		return this.dao.findAllByParams(CriminalBasicCase.class, xql.toString(), params.toArray());
	}

	
}
