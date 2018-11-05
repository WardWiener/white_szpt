package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.List;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseSupectRelation;

/**
 * 涉案嫌疑人Service
 * 
 * @author WangLei
 *
 */
public interface CaseSupectRelationService {

	/**
	 * 根据案件编号查询涉案嫌疑人
	 * 
	 * @param caseCode 案件编号
	 * @return 涉案嫌疑人集合
	 */
	public List<CaseSupectRelation> findCaseSupectRelationsByCaseCode(String caseCode);
}
