package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.CollectInfoSituation;

/**
 * 采集信息情况service
 * 
 * @author WangLei
 *
 */
public interface CollectInfoSituationService {

	/**
	 * 根据身份证号码查询采集信息情况
	 * 
	 * @param idcard 身份证号
	 * @return
	 */
	public CollectInfoSituation findCollectInfoSituationByIdcard(String idcard);
}
