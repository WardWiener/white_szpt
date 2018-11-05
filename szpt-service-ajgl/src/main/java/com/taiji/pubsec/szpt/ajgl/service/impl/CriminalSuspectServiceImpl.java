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
import com.taiji.pubsec.szpt.ajgl.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.ajgl.model.CriminalPerson;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalSuspectService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("criminalSuspectService")
public class CriminalSuspectServiceImpl implements ICriminalSuspectService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<CriminalPerson> findCriminalSuspectByCondition(Map<String, Object> paramMap, int pageNo,
			int pageSize) {
//		StringBuilder hqlSelect = new StringBuilder("select distinct cr.person from CaseSupectRelation as cr");
		StringBuilder xqlSelect = new StringBuilder("select p from CriminalPerson as p ");
		
		StringBuilder xqlWhere = new StringBuilder(" where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("name"))) {
			xqlWhere.append(" and p.name like :name");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("name", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("name")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("sex"))) {
			xqlWhere.append(" and p.sex = :sex");
			xqlMap.put("sex", paramMap.get("sex"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("birthdayStart"))) {
			xqlWhere.append(" and p.birthdayEnd >= :birthdayStart");
			xqlMap.put("birthdayStart", paramMap.get("birthdayStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("birthdayEnd"))) {
			xqlWhere.append(" and p.birthdayStart < :birthdayEnd");
			xqlMap.put("birthdayEnd", paramMap.get("birthdayEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("idNumber"))) {
			xqlWhere.append(" and p.idcardNo like :idNumber");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("idNumber", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("idNumber")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("address"))) {
			xqlWhere.append(" and p.address like :address");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("address", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("address")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("nativePlace"))) {
			xqlWhere.append(" and p.doorDetail like :nativePlace");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("nativePlace", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("nativePlace")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("tone"))) {
			xqlWhere.append(" and p.tone = :tone");
			xqlMap.put("tone", paramMap.get("tone"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("cases"))) {
			xqlSelect.append(" , CaseSupectRelation as cr, CriminalBasicCase as c ");
			xqlWhere.append(" and p.personId = cr.person_id and c.caseCode = cr.case_id");
			xqlWhere.append(" and (c.caseCode like :cases");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlWhere.append(" or c.caseName like :cases)");
			SQLTool.SQLAddEscape(xqlWhere);
			xqlMap.put("cases", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("cases")) + "%");
		}
		return this.dao.findByPage(CaseSupectRelation.class, xqlSelect.append(xqlWhere).toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CriminalPerson findCriminalSuspectById(String criminalSuspectId) {
		return (CriminalPerson) this.dao.findById(CriminalPerson.class, criminalSuspectId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalBasicCase> findCriminalBasicCasesBySuspectId(String criminalSuspectId) {
//		String xql = "select csrl from CaseSupectRelation as csrl where csrl.person.personId = ?";
//		List<CaseSupectRelation> caseSupectRelations = this.dao.findAllByParams(CaseSupectRelation.class, xql, new Object[]{criminalSuspectId});
//		List<CriminalBasicCase> criminalBasicCases = new ArrayList<CriminalBasicCase>();
//		for(CaseSupectRelation caseSupectRelation: caseSupectRelations){
//			String xql1 = "select cbc from CriminalBasicCase as cbc where cbc.caseCode = ?";
//			CriminalBasicCase case1 = (CriminalBasicCase) this.dao.findByParams(CriminalBasicCase.class, xql1, new Object[]{caseSupectRelation.getBasicCase().getCaseCode()});
//			criminalBasicCases.add(case1);
//		}
		
		
		StringBuilder xql = new StringBuilder("select distinct c from CriminalBasicCase as c, CaseSupectRelation as cr, CriminalPerson as p where "
				+ "p.personId = cr.person_id and c.caseCode = cr.case_id and p.personId = :criminalSuspectId");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("criminalSuspectId", criminalSuspectId);
		return dao.findAllByParams(CriminalBasicCase.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseSupectRelation> findCaseSupectRelationBySuspectId(String criminalSuspectId) {
		String xql = "select distinct csr from CaseSupectRelation as csr  where csr.person_id = ? order by csr.updateTime desc";
		return this.dao.findAllByParams(CaseSupectRelation.class, xql, new Object[]{criminalSuspectId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public CriminalBasicCase findCriminalBasicCaseByCaseSupectRelationId(String relationId) {
		CaseSupectRelation caseSupectRelation = (CaseSupectRelation) this.dao.findById(CaseSupectRelation.class, relationId);
		CriminalBasicCase criminalBasicCase = new CriminalBasicCase();
		if(caseSupectRelation != null){
			criminalBasicCase = caseSupectRelation.getBasicCase();
		}
		return criminalBasicCase;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseSupectRelation findCaseSupectRelationById(String relationId) {
		return (CaseSupectRelation) this.dao.findById(CaseSupectRelation.class, relationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseSupectRelation> findCaseSuspectRelationByCase(String caseId) {
		String xql = "select csr from CaseSupectRelation as csr where csr.case_id = :caseId";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseId", caseId);
		return dao.findAllByParams(CaseSupectRelation.class, xql, xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SufferCaseRelation> findSufferCaseRelationsByCase(String caseId) {
		String xql = "select s from SufferCaseRelation as s where s.case_id = :caseId";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseId", caseId);
		return dao.findAllByParams(SufferCaseRelation.class, xql, xqlMap) == null ? new ArrayList<SufferCaseRelation>() : dao.findAllByParams(SufferCaseRelation.class, xql, xqlMap);
	}
}
