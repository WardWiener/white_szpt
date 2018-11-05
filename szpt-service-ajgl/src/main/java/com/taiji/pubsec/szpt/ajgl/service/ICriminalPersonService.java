package com.taiji.pubsec.szpt.ajgl.service;

import java.util.List;

import com.taiji.pubsec.szpt.ajgl.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.ajgl.model.CriminalPerson;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;

/**
 * 涉案人员服务接口
 * @author wangfx
 *
 */
public interface ICriminalPersonService {
	
	/**
	 * 通过id查询涉案人员
	 * @param id 涉案人员id
	 * @return 返回涉案人员信息
	 */
	CriminalPerson findById(String id);
	
	/**
	 * 通过人员ID查询案件嫌疑人关系
	 * @param personId 人员id
	 * @return 返回案件嫌疑人关系信息
	 */
	CaseSupectRelation findCaseSupectRelationByPerson(String personId);
	
	/**
	 * 通过人员ID查询案件受害人关系
	 * @param personId 人员id
	 * @return 返回案件受害人关系信息
	 */
	SufferCaseRelation findSufferCaseRelationByPerson(String personId);
	
	/**
	 * 通过人员ID查询案件嫌疑人关系
	 * @param personId 人员id
	 * @return 返回案件嫌疑人关系信息
	 */
	List<CaseSupectRelation> findCaseSupectRelationsByPerson(String personId);
	
	/**
	 * 通过人员id查询案件受害人关系list
	 * @param persinId 人员id
	 * @return 返回案件受害人关系list
	 */
	List<SufferCaseRelation> findSufferCaseRelationsByPerson(String personId);
	
}
