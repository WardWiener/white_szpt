package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.bean.CaseInfoServiceBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ArchivedFile;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseAlarmRelation;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CrimialCaseNote;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ImpoundedObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;

@Service("caseService")
public class CaseServiceImpl implements CaseService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<CriminalBasicCase> findCaseByConditions(Map<String, Object> params, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select c from CriminalBasicCase as c left join c.caseTag as t where 1 = 1 ");
		xql.append(" and c.caseTypeName = '刑事案件' ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();

		if(ParamMapUtil.isNotBlank(params.get("caseCode"))){
			xql.append(" and c.caseCode like :caseCode ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("caseCode", "%" + SQLTool.SQLSpecialChTranfer((String) params.get("caseCode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(params.get("caseName"))){
			xql.append(" and c.caseName like :caseName ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("caseName", "%" + SQLTool.SQLSpecialChTranfer((String) params.get("caseName")) + "%");
		}
		if(ParamMapUtil.isNotBlank(params.get("caseAddress"))){
			xql.append(" and c.caseAddress like :caseAddress ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("caseAddress", "%" + SQLTool.SQLSpecialChTranfer((String) params.get("caseAddress")) + "%");
		}
		
		if(params.get("caseSort") instanceof String){
			if(ParamMapUtil.isNotBlank(params.get("caseSort"))){
				xql.append(" and c.caseSort like :caseSort ");
				SQLTool.SQLAddEscape(xql);
				xqlMap.put("caseSort", SQLTool.SQLSpecialChTranfer((String) params.get("caseSort")) + "%");
			}
		}else if(params.get("caseSort") instanceof List){
			List<String> list = (List<String>)params.get("caseSort");
			if(list != null && !list.isEmpty()){
				xql.append(" and c.caseSort in (:caseSort) ");
				xqlMap.put("caseSort",list);
			}
		}
		if(params.get("caseKind") instanceof String){
			if(ParamMapUtil.isNotBlank(params.get("caseKind"))){
				xql.append(" and c.caseKind like :caseKind ");
				SQLTool.SQLAddEscape(xql);
				xqlMap.put("caseKind", SQLTool.SQLSpecialChTranfer((String) params.get("caseKind")) + "%");
			}
		}else if(params.get("caseKind") instanceof List){
			List<String> list = (List<String>)params.get("caseKind");
			if(list != null && !list.isEmpty()){
				xql.append(" and c.caseKind in (:caseKind) ");
				xqlMap.put("caseKind",list);
			}
		}
		
		if(ParamMapUtil.isNotBlank(params.get("regionCode"))){
			xql.append(" and c.regionCode = :regionCode ");
			xqlMap.put("regionCode", params.get("regionCode"));
		}
		if(ParamMapUtil.isNotBlank(params.get("communityCode"))){
			xql.append(" and c.communityCode = :communityCode ");
			xqlMap.put("communityCode", params.get("communityCode"));
		}
		if(ParamMapUtil.isNotBlank(params.get("discoverTimeStart"))){
			xql.append(" and c.discoverTimeStart >= :discoverTimeStart ");
			xqlMap.put("discoverTimeStart", params.get("discoverTimeStart"));
		}
		if(ParamMapUtil.isNotBlank(params.get("discoverTimeEnd"))){
			xql.append(" and c.discoverTimeStart < :discoverTimeEnd ");
			xqlMap.put("discoverTimeEnd", params.get("discoverTimeEnd"));
		}
		if(ParamMapUtil.isNotBlank(params.get("polices"))){
			xql.append(" and c.handlePolice like :handlePolice ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("handlePolice", "%" + SQLTool.SQLSpecialChTranfer((String) params.get("polices")) + "%");
		}
		if(ParamMapUtil.isNotBlank(params.get("handlingUnit"))){
			xql.append(" and c.handleUnit = :handleUnit ");
			xqlMap.put("handlingUnit", params.get("handlingUnit"));
		}
		if(ParamMapUtil.isNotBlank(params.get("ifSolved"))){
			xql.append(" and c.ifSolved = :ifSolved ");
			xqlMap.put("ifSolved", params.get("ifSolved"));
		}
		if(DICT.DICT_TAG_NO.getValue().equals(params.get("tagState"))){
			xql.append(" and t.id is null ");
		}else if(DICT.DICT_TAG_YES.getValue().equals(params.get("tagState"))){
			xql.append(" and t.id is not null ");
		}
		xql.append(" order by c.discoverTimeStart desc");
		
		return dao.findByPage(CriminalBasicCase.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CriminalBasicCase findCaseByCode(String caseCode) {
		return (CriminalBasicCase) dao.findById(CriminalBasicCase.class, caseCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FactJq findAlarmByCase(String caseCode) {
		String xql = "select r from CaseAlarmRelation as r where r.caseCode.caseCode = ?";
		CaseAlarmRelation relation = (CaseAlarmRelation) dao.findByParams(CaseAlarmRelation.class, xql, new Object[]{caseCode});
		if(relation != null && relation.getAlarmId() != null){
			String xql1 = "select j from FactJq as j where j.id = ?";
			FactJq jq = (FactJq) dao.findByParams(FactJq.class, xql1, new Object[]{relation.getAlarmId()});
			return jq;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrimialCaseNote> findNotesByCase(String caseCode) {
		String xql = "select n from CrimialCaseNote as n where n.basicCase.caseCode = ? order by n.sort";
		return dao.findAllByParams(CrimialCaseNote.class, xql, new Object[]{caseCode});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalPerson> findSuspectsByCase(String caseCode) {
		String xql = "select p from CaseSupectRelation as r, CriminalPerson as p where r.criminalPerson.id = p.personId and r.basicCase.caseCode = ?";
		return dao.findAllByParams(CriminalPerson.class, xql, new Object[]{caseCode});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalObject> findObjectsByCase(String caseCode) {
		String xql = "select o from CriminalObject as o where o.basicCase.caseCode = ?";
		return dao.findAllByParams(CriminalObject.class, xql, new Object[]{caseCode});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalBasicCase> findCaseBySuspect(String suspectCode) {
		String xql = "select c from CaseSupectRelation as r, CriminalBasicCase as c where r.basicCase.caseCode = c.caseCode and r.criminalPerson.personId = ?";
		return dao.findAllByParams(CriminalBasicCase.class, xql, new Object[]{suspectCode});
	}

	@Override
	public List<CriminalBasicCase> findRelatedCase(String caseCode) {
		List<CriminalBasicCase> relatedCases = new ArrayList<CriminalBasicCase>();
		List<CriminalBasicCase> findCases = new ArrayList<CriminalBasicCase>();
		CriminalBasicCase basicCase = this.findCaseByCode(caseCode);
		findCases.add(basicCase);
		for(; findCases.size() != 0; ){
			CriminalBasicCase case1 = findCases.get(0);
			findCases.remove(0);
			relatedCases.add(case1);
			CriminalBasicCase criminalBasicCase = this.findMainCaseBySonCaseId(case1.getCaseCode());
			if(criminalBasicCase != null && (!findCases.contains(criminalBasicCase) && !relatedCases.contains(criminalBasicCase))){
				findCases.add(criminalBasicCase);
			}
			List<CriminalBasicCase> criminalBasicCases = this.findSonCasesByMainCaseId(case1.getCaseCode());
			for(CriminalBasicCase criminalBasicCase2: criminalBasicCases){
				if(!findCases.contains(criminalBasicCase2) && !relatedCases.contains(criminalBasicCase2)){
					findCases.add(criminalBasicCase2);
				}
			}
		}
		relatedCases.remove(basicCase);
		return relatedCases;
	}
		
	@SuppressWarnings("unchecked")
	private CriminalBasicCase findMainCaseBySonCaseId(String caseId){
		CriminalBasicCase sonCase = (CriminalBasicCase) this.dao.findById(CriminalBasicCase.class, caseId);
		if(sonCase != null && sonCase.getMianCaseId() != null){
			return (CriminalBasicCase) this.dao.findById(CriminalBasicCase.class, sonCase.getMianCaseId());
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<CriminalBasicCase> findSonCasesByMainCaseId(String caseId){
		List<CriminalBasicCase> sonCases = new ArrayList<CriminalBasicCase>();
		String xql = "select c from CriminalBasicCase as c where c.mianCaseId = ?";
		sonCases = this.dao.findAllByParams(CriminalBasicCase.class, xql, new Object[]{caseId});
		return sonCases;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArchivedFile> findArchivedFileCase(String caseCode) {
		String xql = "select f from ArchivedFile as f where f.basicCase.caseCode = ?";
		return dao.findAllByParams(ArchivedFile.class, xql, new Object[]{caseCode});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CaseInfoServiceBean> findCaseInfoByConditions(Date caseTimeStart, Date caseTimeEnd, String[] pcsCodes) {
		List<CaseInfoServiceBean> beanList = new ArrayList<CaseInfoServiceBean>();
		StringBuilder xql = new StringBuilder("select t from CaseTag as t, Community as c, Unit u  where c.code = t.community and c.unitId=u.id ");

		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(caseTimeStart)){
			xql.append(" and t.basicCase.caseTimeStart >= :caseTimeStart ");
			xqlMap.put("caseTimeStart",caseTimeStart);
		}
		if(ParamMapUtil.isNotBlank(caseTimeEnd)){
			xql.append(" and t.basicCase.caseTimeStart < :caseTimeEnd ");
			xqlMap.put("caseTimeEnd", caseTimeEnd);
		}
		if(ParamMapUtil.isNotBlank(pcsCodes)){
			List<String> key = Arrays.asList(pcsCodes) ;
			xql.append(" and u.code in (:code) ");
			xqlMap.put("code", key);
		}
		List<CaseTag> caseTags = dao.findAllByParams(CaseTag.class, xql.toString(), xqlMap);
		for(CaseTag caseTag: caseTags){
			CaseInfoServiceBean bean = new CaseInfoServiceBean();
			if(caseTag.getLatitude() == null && caseTag.getLongitude() == null){
				bean.setLatitude(caseTag.getBasicCase().getLatitude());
				bean.setLongitude(caseTag.getBasicCase().getLongitude());
			}else{
				bean.setLatitude(caseTag.getLatitude());
				bean.setLongitude(caseTag.getLongitude());
			}
			beanList.add(bean);
		}
		return beanList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImpoundedObject> findImpoundedObjectsByCase(String caseCode) {
		String xql = "select i from ImpoundedObject as i where i.basicCase.caseCode = ?";
		return dao.findAllByParams(ImpoundedObject.class, xql, new Object[]{caseCode});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalBasicCase> findCaseBySuspectIdcard(String suspectIdcard) {
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		String xql = "select c from CaseSupectRelation as r, CriminalBasicCase as c where r.basicCase.caseCode = c.caseCode and r.criminalPerson.idcardNo = :suspectIdcard";
		xqlMap.put("suspectIdcard", suspectIdcard);
		return dao.findAllByParams(CriminalBasicCase.class, xql, xqlMap);
	}

}
