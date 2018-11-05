package com.taiji.pubsec.szpt.dagl.service;

import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.dagl.bean.PersonBriefInfo;

/**
 * @author dixiaofeng
 * @version 1.0
 * @created 21-二月-2017 9:57:39
 */
public interface PersonSearchService {

	/**
	 * 按身份证号查询人员简要信息。使用solar接口实现。
	 * 
	 * @param idcard    身份证号
	 */
	public Map<String, Object> searchPerson(String idcard);
	
	/**
	 * 按身份证号查询人员简要信息。使用solar接口实现。
	 * 
	 * @param idcard    身份证号
	 */
	public Pager<Map<String, Object>> searchPersonGetPager(Map<String,Object> conditions,int pageNo, int pageSize);

}