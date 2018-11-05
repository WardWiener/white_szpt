package com.taiji.pubsec.szpt.caseanalysis.tag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseFeatureTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("caseTagService")
public class CaseTagServiceImpl implements CaseTagService{

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	private static final String TAG_TYPE = "com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag";//案件打标的类型
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CaseTagServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean tagCase(CaseTag caseTag, List<String> featureCodes, String caseCode) {
		try {
			CriminalBasicCase basicCase = (CriminalBasicCase) dao.findById(CriminalBasicCase.class, caseCode);
			caseTag.setBasicCase(basicCase);
			dao.saveOrUpdate(caseTag);
			
			Set<CaseFeatureTag> featureTags = caseTag.getFeatures();
			for(CaseFeatureTag featureTag:  featureTags){
				dao.delete(featureTag);
			}
			
			for(String str: featureCodes){
				CaseFeatureTag feature = new CaseFeatureTag();
				feature.setCaseTag(caseTag);
				feature.setFeature(str);
				dao.save(feature);
			}
		} catch (Exception e) {
			LOGGER.debug("创建/更新打标失败", e);
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CaseTag findCaseTag(String caseCode) {
		String xql = "select t from CaseTag as t where t.basicCase.id = ?";
		return (CaseTag) dao.findByParams(CaseTag.class, xql, new Object[]{caseCode});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OperationRecord> findTagOperation(String caseCode) {
		CriminalBasicCase basicCase = (CriminalBasicCase) dao.findById(CriminalBasicCase.class, caseCode);
		if(basicCase.getCaseTag() == null){
			return new ArrayList<OperationRecord>();
		}else{
			String xql = "select r from OperationRecord as r where r.targetType = ? and r.targetId = ?";
			return dao.findAllByParams(OperationRecord.class, xql, new Object[]{TAG_TYPE, basicCase.getCaseTag().getId()});
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<CriminalBasicCase> findCaseByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select distinct c from CriminalBasicCase as c, CaseTag as t, CaseFeatureTag as f where c.caseCode = t.basicCase.caseCode and t.id = f.caseTag.id ");
		Map<String, Object> xqlMap = new HashMap<String,Object>();
		if(ParamMapUtil.isNotBlank(conditions.get("caseCode"))){
			xql.append(" and c.caseCode like :caseCode ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("caseCode", "%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("caseCode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("type"))){
			xql.append(" and t.type = :type ");
			xqlMap.put("type", conditions.get("type"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("firstSorts"))){
			xql.append(" and t.firstSort in (:firstSorts) ");
			xqlMap.put("firstSorts", conditions.get("firstSorts"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("secondSorts"))){
			xql.append(" and t.secondSort in (:secondSorts) ");
			xqlMap.put("secondSorts", conditions.get("secondSorts"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("features"))){
			xql.append(" and f.feature in (:features) ");
			xqlMap.put("features", conditions.get("features"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("occurPlace"))){
			xql.append(" and t.occurPlace = :occurPlace ");
			xqlMap.put("occurPlace", conditions.get("occurPlace"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("period"))){
			xql.append(" and t.period = :period ");
			xqlMap.put("period", conditions.get("period"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("peopleNum"))){
			xql.append(" and t.peopleNum = :peopleNum ");
			xqlMap.put("peopleNum", conditions.get("peopleNum"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("entrance"))){
			xql.append(" and t.entrance = :entrance ");
			xqlMap.put("entrance", conditions.get("entrance"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("exit"))){
			xql.append(" and t.exit = :exit ");
			xqlMap.put("exit", conditions.get("exit"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("communitys"))){
			xql.append(" and t.community in (:communitys) ");
			xqlMap.put("communitys", conditions.get("communitys"));
		}
		xql.append(" order by c.caseTimeStart desc");
		return dao.findByPage(CriminalBasicCase.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@Override
	public List<CriminalBasicCase> findAllCaseByConditions(
			Map<String, Object> conditions) {
		StringBuilder xql = new StringBuilder("select distinct c from CriminalBasicCase as c, CaseTag as t, CaseFeatureTag as f where c.caseCode = t.basicCase.caseCode and t.id = f.caseTag.id ");
		Map<String, Object> xqlMap = new HashMap<String,Object>();
		if(ParamMapUtil.isNotBlank(conditions.get("caseCode"))){
			xql.append(" and c.caseCode like :caseCode ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("caseCode", "%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("caseCode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("type"))){
			xql.append(" and t.type = :type ");
			xqlMap.put("type", conditions.get("type"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("firstSorts"))){
			xql.append(" and t.firstSort in (:firstSorts) ");
			xqlMap.put("firstSorts", conditions.get("firstSorts"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("secondSorts"))){
			xql.append(" and t.secondSort in (:secondSorts) ");
			xqlMap.put("secondSorts", conditions.get("secondSorts"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("features"))){
			xql.append(" and f.feature in (:features) ");
			xqlMap.put("features", conditions.get("features"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("occurPlace"))){
			xql.append(" and t.occurPlace = :occurPlace ");
			xqlMap.put("occurPlace", conditions.get("occurPlace"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("period"))){
			xql.append(" and t.period = :period ");
			xqlMap.put("period", conditions.get("period"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("peopleNum"))){
			xql.append(" and t.peopleNum = :peopleNum ");
			xqlMap.put("peopleNum", conditions.get("peopleNum"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("entrance"))){
			xql.append(" and t.entrance = :entrance ");
			xqlMap.put("entrance", conditions.get("entrance"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("exit"))){
			xql.append(" and t.exit = :exit ");
			xqlMap.put("exit", conditions.get("exit"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("communitys"))){
			xql.append(" and t.community in (:communitys) ");
			xqlMap.put("communitys", conditions.get("communitys"));
		}
		return dao.findAllByParams(CriminalBasicCase.class, xql.toString(), xqlMap);
	}

	@Override
	public void refresh(Object obj) {
//		dao.flush();
		dao.refresh(obj);
		
	}
	
}
