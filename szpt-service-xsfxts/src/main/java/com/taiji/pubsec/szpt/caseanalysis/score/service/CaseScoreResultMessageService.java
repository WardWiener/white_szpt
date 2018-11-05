package com.taiji.pubsec.szpt.caseanalysis.score.service;

import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.MessageResultBean;

/**
 * 计算结果推送消息接口
 * 
 * @author dixiaofeng
 */
public interface CaseScoreResultMessageService {

	/**
	 * 按条件查询计算结果消息记录。对结果消息、打标基础表、案件基本信息表进行关联查询。
	 * 
	 * @param conditions 查询条件，包括：
	 * <br>caseCodeLists:案件编号list
	 * <br>type:案件类别
	 * <br>caseState:案件状态
	 * <br>handlePolice:办案民警
	 * <br>firstSort:案件性质一级
	 * <br>secondSort:案件性质二级
	 * <br>caseTimeStartFrom:案发时间起始
	 * <br>caseTimeStartTo:案发时间结束
	 * <br>systemAutoPushTimeFrom:系统自动推送时间起始
	 * <br>systemAutoPushTimeTo:系统自动推送时间结束
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回案件消息分页信息
	 */
	public Pager<MessageResultBean> findMessageByConditions(Map<String,Object> conditions, int pageNo, int pageSize);

	/**
	 * 更新消息已读状态。
	 * @param messageId 消息id
	 * @return 成功返回true；失败返回false。
	 */
	public boolean receiveMessage(String messageId);

}