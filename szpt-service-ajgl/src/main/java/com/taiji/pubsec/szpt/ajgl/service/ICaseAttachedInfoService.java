package com.taiji.pubsec.szpt.ajgl.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.ajgl.model.CaseAttachedInfo;


/**
 * 案件附加信息查询接口，案件到期提醒
 * @author wangfx
 *
 */
public interface ICaseAttachedInfoService {
	
	/**
	 * 创建案件到期提醒信息
	 * @param caseAttachedInfo 案件到期提醒信息
	 * @param caseId 案件id
	 */
	void createCaseAttachedInfo(CaseAttachedInfo caseAttachedInfo, String caseId);
	
	/**
	 * 更新案件提醒信息，仅修改案件法制审核人、关注状态、备注
	 * @param caseAttachedInfo 案件到期提醒信息
	 */
	void updateCaseAttachedInfo(CaseAttachedInfo caseAttachedInfo);
	
	/**
	 * 根据案件类别（刑事案件or行政案件）、查询日期、案件是否关注、办案单位、案件名称、备注查询案件到期提醒信息
	 * @param paramMap 查询条件map
	 * <br>:caseClass （刑事案件or行政案件）
	 * <br>：timeStart 查询日期起
	 * <br>：timeEnd 查询日期止
	 * <br>：caseAttentionState 案件是否关注
	 * <br>： attendUnit 办案单位
	 * <br>：caseName 案件名称
	 * <br>：note 备注                         
	 * @return
	 */
	List<CaseAttachedInfo> findCaseAttachedInfoByCondition(Map<String, Object> paramMap);
	
	/**
	 * 通过案件编号查询相应的案件附加信息
	 * @param caseCode 案件编号
	 * @return 返回案件附件信息
	 */
	public CaseAttachedInfo findByCaseCode(String caseCode);

}
