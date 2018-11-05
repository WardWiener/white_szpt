package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.List;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalFord;

/**
 * 涉案团伙Service
 * 
 * @author WangLei
 *
 */
public interface CriminalFordService {

	/**
	 * 根据案件编号查询涉案团伙
	 * 
	 * @param caseCode 案件编号
	 * @return 涉案团伙集合
	 */
	public List<CriminalFord> findCriminalFordsByCaseCode(String caseCode);
}
