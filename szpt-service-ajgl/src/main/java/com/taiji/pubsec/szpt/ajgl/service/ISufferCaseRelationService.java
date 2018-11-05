package com.taiji.pubsec.szpt.ajgl.service;

import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;

/**
 * 受害人查询服务接口
 * @author chengkai
 *
 */
public interface ISufferCaseRelationService {
	
	/**
	 * 通过案件受害人关系id查询案件受害人关系信息
	 * @param relationId 案件受害人关系id
	 * @return 返回啊你就按受害人关系信息
	 */
	SufferCaseRelation findSufferCaseRelationById(String relationId);
	
}
