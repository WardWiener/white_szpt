package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;

/**
 * 涉案人员Service
 * 
 * @author WangLei
 *
 */
public interface CriminalPersonService {

	/**
	 * 根据身份证号查询嫌疑人
	 * 
	 * @param idcard 身份证号
	 * @return 嫌疑人
	 */
	public CriminalPerson findCriminalPersonByIdcard(String idcard);
}
