package com.taiji.pubsec.szpt.caseanalysis.score.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResultDetail;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreResultService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("scoreResultService")
public class ScoreResultServiceImpl implements ScoreResultService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ScoreResultServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CaseScoreResult> findScoredCases(String caseCode, Map<String, Object> conditions) {
		StringBuilder xql = new StringBuilder("select distinct r from CaseScoreResult as r, CaseTag as t left join t.features as f where r.subCase = t.basicCase.caseCode ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		xql.append(" and r.mainCase = :mainCase ");
		xqlMap.put("mainCase", caseCode);
		if(DICT.DICT_NO.getValue().equals(conditions.get("caseState"))){
			xql.append(" and r.isFinished = :isFinished ");
			xqlMap.put("isFinished", false);
		}
		if(DICT.DICT_NO.getValue().equals(conditions.get("showHide"))){
			xql.append(" and r.isIgnored = :isIgnored ");
			xqlMap.put("isIgnored", false);
		}
		if(ParamMapUtil.isNotBlank(conditions.get("minScore"))){
			xql.append(" and r.score >= :score ");
			xqlMap.put("score", Double.parseDouble(conditions.get("minScore").toString()));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("feature"))){
			xql.append(" and f.feature = :feature ");
			xqlMap.put("feature", conditions.get("feature"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("occurPlace"))){
			xql.append(" and t.occurPlace = :occurPlace ");
			xqlMap.put("occurPlace", conditions.get("occurPlace"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("community"))){
			xql.append(" and t.community in (:community) ");
			xqlMap.put("community", conditions.get("community"));
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
		xql.append(" order by r.score desc");
		List<CaseScoreResult> csrs = dao.findAllByParams(CaseScoreResult.class, xql.toString(), xqlMap);
		if(csrs == null || csrs.isEmpty()){
			return csrs;
		}
		for(CaseScoreResult csr : csrs){
			csr.setScore(formatDouble(csr.getScore()));
		}
		return csrs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean createOrUpdateScoreResult(CaseScoreResult result) {
		try {
			dao.saveOrUpdate(result);
		} catch (Exception e) {
			LOGGER.debug("创建(或更新)积分计算结果失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseScoreResult findScoreResult(String mainCaseCode, String subCaseCode) {
		String xql = "select r from CaseScoreResult as r where r.mainCase = ? and r.subCase = ?";
		return (CaseScoreResult) dao.findByParams(CaseScoreResult.class, xql, new Object[]{mainCaseCode, subCaseCode});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CriminalBasicCase> findIgnoredCases(String mainCaseCode) {
		String hql = "select cs from CriminalBasicCase cs, CaseScoreResult sr where sr.mainCase = ? and sr.isIgnored = ? and sr.subCase = cs.caseCode";
		return dao.findAllByParams(CriminalBasicCase.class, hql, new Object[]{mainCaseCode, true});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean createOrUpdateCaseScoreResultDetail(CaseScoreResultDetail detail) {
		try {
			dao.saveOrUpdate(detail);
		} catch (Exception e) {
			LOGGER.debug("创建(或更新)串并案打分结果明细失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CaseScoreResultDetail findDetailByResultIdAndRuleName(String resultId, String ruleName) {
		String xql = "select d from CaseScoreResultDetail as d where d.result.id = ? and d.scoreRuleName = ?";
		return (CaseScoreResultDetail) dao.findByParams(CaseScoreResultDetail.class, xql, new Object[]{resultId, ruleName});
	}
	
	public void refresh(Object obj) {
		dao.flush();
		dao.refresh(obj);
	}
	
	private double formatDouble(double score){
		String scoreStr = String.valueOf(score);
		if(scoreStr.indexOf(".") != -1 && (scoreStr.length() - (scoreStr.indexOf(".")+1)) > 2){
			BigDecimal b = new BigDecimal(score);
			double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			String f1Str = String.valueOf(f1);
			if("0".equals(f1Str.substring(f1Str.length() - 1, f1Str.length()))){
				f1Str = f1Str.substring(0, f1Str.length() - 1);
			}
			return Double.valueOf(f1Str);
		}
		return score;
	}

}
