package com.taiji.pubsec.szpt.fullsearch.service;

import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;


/**
 * @author dixiaofeng
 * @version 1.0
 * @created 27-12月-2016 14:41:31
 */
public interface FullTextSearchService {
	/**
	 * 查询警情信息。
	 * 
	 * @param conditions
	 * 参数map中key含义：keyword关键字；answerTimeStart接警时间开始；answerTimeEnd接警时间结束；type警情类别；occurT
	 * imeStart发生时间开始；occurTimeEnd发生时间结束
	 * @param pageNo 页号，从0开始计数
	 * @param pageSize 每页数量
	 * @return 分页对象，业务数据以key-value形式返回，key为solr存储警情collection（alertsituation）字段名
	 */
	public Pager<Map<String,Object>> queryAlaram(Map<String,Object> conditions, int pageNo, int pageSize);

	/**
	 * 查询案件信息。
	 * 
	 * @param conditions
	 * 参数map中key含义：keyword关键字；occurTimeStart发生时间开始；occurTimeEnd发生时间结束
	 * key: @see com.taiji.pubsec.szpt.fullsearch.field.Case
	 * @param pageNo 页号，从0开始计数
	 * @param pageSize 每页数量
	 * @return 分页对象，业务数据以key-value形式返回，key为solr存储案件collection（case）字段名
	 */
	public Pager<Map<String,Object>> queryCase(Map<String,Object> conditions, int pageNo, int pageSize);

	/**
	 * 查询高危人信息。population collection中查询类型是高危人的记录。
	 * 
	 * @param conditions
	 * 参数map中key: @see com.taiji.pubsec.szpt.fullsearch.field.Population
	 * @param pageNo 页号，从0开始计数
	 * @param pageSize 每页数量
	 * @return 分页对象，业务数据以key-value形式返回，key为solr存储人口collection（population）字段名
	 */
	public Pager<Map<String,Object>> queryPerson(Map<String,Object> conditions, int pageNo, int pageSize);

	/**
	 * 查询场所信息。
	 * 
	 * @param conditions
	 * 参数map中key含义：keyword关键字；collectTimeStart采集时间开始；collectTimeEnd采集时间结束
	 * @param pageNo 页号，从0开始计数
	 * @param pageSize 每页数量
	 * @return 分页对象，业务数据以key-value形式返回，key为solr存储场所collection（place）字段名
	 */
	public Pager<Map<String,Object>> queryPlace(Map<String,Object> conditions, int pageNo, int pageSize);

	/**
	 * 查询指令信息。
	 * 
	 * @param conditions
	 * 参数map中key含义：keyword关键字；createTimeStart创建时间开始；createTimeEnd创建时间结束；type指令类型；feedba
	 * cktimeStart要求反馈时间开始；feedbackTimeEnd反馈时间结束；receiveUnit接收单位
	 * @param pageNo 页号，从0开始计数
	 * @param pageSize 每页数量
	 * @return 分页对象，业务数据以key-value形式返回，key为solr存储指令collection（instruction）字段名
	 */
	public Pager<Map<String,Object>> queryInstruction(Map<String,Object> conditions, int pageNo, int pageSize);

}