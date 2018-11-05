package com.taiji.pubsec.szpt.ajgl.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.ajgl.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.ajgl.model.CriminalPerson;
import com.taiji.pubsec.szpt.ajgl.model.SufferCaseRelation;

/**
 * 涉案嫌疑人查询服务接口
 * @author wangfx
 *
 */
public interface ICriminalSuspectService {
	
	/**
	 * 根据条件查询涉案嫌疑人
	 * @param paramMap 查询条件
	 * </br>:name TODO 姓名（模糊匹配）
	 * </br>:sex TODO 性别
	 * </br>:birthdayStart TODO 出生年月起 
	 * </br>:birthdayEnd TODO 出生年月止
	 * </br>:idNumber TODO 身份证号 （模糊匹配）
	 * </br>:address TODO 现居住地址（模糊匹配）
	 * </br>:nativePlace TODO 籍贯地址（模糊匹配）
	 * </br>:tone TODO 口音（模糊匹配）
	 * </br>:cases TODO 相关案件（模糊匹配）
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 涉案嫌疑人分页
	 */
	Pager<CriminalPerson> findCriminalSuspectByCondition(Map<String, Object> paramMap, int pageNo, int pageSize);
	
	/**
	 * 根据id查询涉案嫌疑人
	 * @param criminalSuspectId 涉案嫌疑人id
	 * @return 涉案嫌疑人
	 */
	CriminalPerson findCriminalSuspectById(String criminalSuspectId);
	
	/**
	 * 根据涉案嫌疑人id查询相关案件
	 * @param criminalSuspectId 涉案嫌疑人id
	 * @return 返回案件list
	 */
	List<CriminalBasicCase> findCriminalBasicCasesBySuspectId(String criminalSuspectId);
	
	/**
	 * 通过涉案嫌疑人id查询案件与人员关联信息
	 * @param criminalSuspectId 涉案嫌疑人id
	 * @return 返回案件与人员关联信息list
	 */
	List<CaseSupectRelation> findCaseSupectRelationBySuspectId(String criminalSuspectId);
	
	/**
	 * 通过案件嫌疑人关系id查询案件信息
	 * @param relationId 案件嫌疑人关系id
	 * @return 返回案件信息
	 */
	CriminalBasicCase findCriminalBasicCaseByCaseSupectRelationId(String relationId);
	
	/**
	 * 通过案件嫌疑人关系id查询案件嫌疑人关系信息
	 * @param relationId 案件嫌疑人关系id
	 * @return 案件嫌疑人关系信息
	 */
	CaseSupectRelation findCaseSupectRelationById(String relationId);
	
	/**
	 * 根据案件id查询案件嫌疑人关联信息
	 * @param caseId
	 * @return
	 */
	List<CaseSupectRelation> findCaseSuspectRelationByCase(String caseId);
	
	/**
	 * 通过案件id查询受害人关系信息
	 * @param caseId 案件id
	 * @return 返回案件受害人关系list
	 */
	List<SufferCaseRelation> findSufferCaseRelationsByCase(String caseId);
	
}
