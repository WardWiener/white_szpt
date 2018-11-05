package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.List;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.SufferCaseRelation;

/**
 * 报案人/受害人Service
 * 
 * @author WangLei
 *
 */
public interface SufferCaseRelationService {

	/**
	 * 根据案件编号查询报案人/受害人
	 * 
	 * @param caseCode 案件编号
	 * @return 报案人/受害人集合
	 */
	public List<SufferCaseRelation> findSufferCaseRelationsByCaseCode(String caseCode);
}
