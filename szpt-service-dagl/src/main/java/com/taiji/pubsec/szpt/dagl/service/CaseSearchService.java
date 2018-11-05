package com.taiji.pubsec.szpt.dagl.service;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.dagl.bean.CaseBriefInfo;

/**
 * 案件检索接口
 * @author dixiaofeng
 * @version 1.0
 * @created 21-二月-2017 9:58:01
 */
public interface CaseSearchService {

	/**
	 * 根据案件编号获取案件简要信息，除基本字段外，涉案物品数量、嫌疑人数量、快照数量分别调用
	 * 
	 * @param caseCode    案件编码
	 */
	public CaseBriefInfo findCaseBriefInfo(String caseCode);

	/**
	 * 根据关键字检索案件名称、编码和内容，分页返回匹配的案件编码。使用solar接口实现。
	 * 
	 * @param keyword
	 * @param pageNo    页号
	 * @param pageSize    每页数量
	 */
	public Pager<String> searchCase(String keyword, int pageNo, int pageSize);

}