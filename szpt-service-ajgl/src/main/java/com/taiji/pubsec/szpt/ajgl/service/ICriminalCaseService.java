package com.taiji.pubsec.szpt.ajgl.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.ajgl.model.AlarmInfo;
import com.taiji.pubsec.szpt.ajgl.model.CaseAttachedInfo;
import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.ajgl.model.CriminalObject;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;

/**
 * 案件查询服务接口
 * @author wangfx
 *
 */
public interface ICriminalCaseService {
	
	/**
	 * 根据条件查询案件信息
	 * @param paramMap 查询条件
	 * </br>:caseTimeStart 发案时间起
	 * </br>:caseTimeEnd 发案时间止
	 * </br>:caseId 案件编号（模糊匹配）
	 * </br>:caseName 案件名称（模糊匹配）
	 * </br>:caseSort 案件类别
	 * </br>:caseKind 案件性质
	 * </br>:TODO 案件时段 待确认 （有对应的字段）
	 * </br>:handingUnit 办案单位   
	 * </br>:popedom 辖区
	 * </br>:community 发案社区
	 * </br>:caseState 发案状态（多选，List）
	 * </br>:disposePerson 办案民警（录入人，模糊匹配）
	 * </br>:caseAddress 发案地点（模糊匹配）
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 案事件分页信息
	 */
	Pager<CriminalBasicCase> findCriminalCaseByCondition(Map<String, Object> paramMap, int pageNo, int pageSize);

	/**
	 * 根据案件基本信息id查询案件信息（案件详情、嫌疑人、涉案物品、并案从案 通过关联信息查询，卷宗信息暂无）
	 * @param criminalCaseId 案件基本信息id
	 * @return 案件信息
	 */
	CriminalBasicCase findCriminalCaseById(String criminalCaseId);
	
	/**
	 * 根据案件编号查询案件信息（案件详情、嫌疑人、涉案物品、并案从案 通过关联信息查询，卷宗信息暂无）
	 * @param caseId 案件编号
	 * @return 案件信息
	 */
	CriminalBasicCase findCriminalCaseByCaseId(String caseId);
	
	/**
	 * 根据id查询接处警信息
	 * @param alarmId 案件id
	 * @return 接处警信息
	 */
	AlarmInfo findAlarmInfoById(String alarmId);
	
	/**
	 * 案件查询条件类型
	 * @param comditionKey
	 * </br>caseSort 案件类别
	 * </br>caseKind 案件性质
	 * <br>caseSlot 案件时段
	 * </br>popedom 发案辖区
	 * </br>community 发案社区
	 * </br>caseState 发案状态
	 * @return 改查询条件对应数据库里的value值列表
	 */
	List<String> findDistinctConditionValues(String comditionKey);
	
	/**
	 * 通过案件id查询与该案件有主从关系的案件list和主案标识
	 * @param caseId 案件id
	 * @return 返回案件list和该案件是否是主案的标识
	 */
	Map<CriminalBasicCase, String> findRelatedCriminalBasicCaseById(String caseId);
	
	/**
	 * 通过案件名称、案件编号、办案民警查询案件信息
	 * @param queryConditions 查询条件，包括：
	 * <br>:caseName 案件名称
	 * <br>:caseCode 案件编号
	 * <br>:disposePerson 办案民警
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 返回案件分页信息
	 */
	Pager<CriminalBasicCase> findCriminalBasicCasesByQueryConditions(Map<String, Object> queryConditions, int pageNo, int pageSize);
	
	Pager<CriminalBasicCase> findCriminalBasicCase(String nameOrCode, int start, int length);
	
	/**
	 * 通过案件id查询案件嫌疑人关系信息列表
	 * @param caseId 案件id
	 * @return 返回案件嫌疑人关系信息列表
	 */
	List<SufferCaseRelation> findSufferCaseRelationByCase(String caseId);
	
	/**
	 * 通过案件id查询涉案物品列表
	 * @param caseId 案件id
	 * @return 返回涉案物品信息列表
	 */
	List<CriminalObject> findCriminalObjectByCase(String caseId);
	
	List<CriminalBasicCase> findCriminalCaseByCaseSort(Map<String, Object> queryConditions);
	
	/**
	 * 根据id查询案件附加信息
	 * @param caseId 案件id
	 * @return 案件附加信息
	 */
	CaseAttachedInfo findCaseAttachedInfoById(String caseId);
	/**
	 * 根据案件编号或者案件名称查找对应的案件
	 * @param condition
	 * @return
	 */
	List<CriminalBasicCase> findCriminalBasicCasesByCodeOrName(String condition);
}
