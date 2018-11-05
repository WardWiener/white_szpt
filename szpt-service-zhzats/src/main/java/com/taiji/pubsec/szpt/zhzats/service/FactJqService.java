package com.taiji.pubsec.szpt.zhzats.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.zhzats.model.CjFeedback;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;


public interface FactJqService {
	
	@Deprecated
	public List<FactJq> findJqByDateAndType(List<String> jqTypes, Date timeStart, Date timeEnd);

	/**
	 * 根据警情id查询警情信息
	 * @param jqId 一个警情id
	 * @return  一条警情信息FactJq
	 */
	FactJq findFactJqById(String jqId);
	
	/**
	 * 根据警情id查询警情信息
	 * @param jqId 一个警情id
	 * @return  一条警情信息FactJq
	 */
	FactJq findFactJqByCode(String Code);
	/**
	 * 查询警情集合
	 * @param startDay 开始时间
	 * @param endDay  结束时间
	 * @param jqlxCodes 警情类型集合
	 * @param pcsCodes   派出所编码集合
	 * @param analyseBaseType 分析基础类型（派出所   主格）
	 * @return  FactJq 集合
	 */
	public List<FactJq> factJqList(Date startDay, Date endDay, String[] jqlxCodes,String[] pcsCodes, String analyseBaseType);
	
	/**
	 * 根据警情id查询JWZH中的处警反馈信息
	 * 
	 * @param jqId 警情id
	 * @return
	 */
	public List<CjFeedback> findCjFeedbackByJqId(String jqId);

}
