package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;

public interface CaseTagService {
	
	/**
	 * 创建/更新打标
	 * 
	 * @param caseTag 打标基本信息
	 * @param featureCodes 特点编码集合
	 * @param caseCode 案件编码
	 * @return 是否新建或更新成功
	 */
	public boolean tagCase(CaseTag caseTag, List<String> featureCodes, String caseCode);

	/**
	 * 根据案件编码查询打标
	 * 
	 * @param caseCode 案件编码
	 * @return
	 */
	public CaseTag findCaseTag(String caseCode);
	
	/**
	 * 根据案件编码查询操作记录
	 * 
	 * @param caseCode 案件编码
	 * @return
	 */
	public List<OperationRecord> findTagOperation(String caseCode);
	
	/**
	 * 根据条件分页查找案件
	 * @param conditions 查询条件map
	 * 	<br>"caseCode",      	  "案件编号"
	 *	<br>"type",      	  	  "案件类别"
	 *	<br>"firstSorts",      	  "案件一级性质，List集合"
	 *	<br>"secondSorts",        "案件二级性质，List集合"
	 *	<br>"features",      	  "作案特点，List集合"
	 *	<br>"occurPlace",  		  "作案处所"
	 *	<br>"period",             "作案时段"
	 *	<br>"peopleNum",          "作案人数"
	 *  <br>"entrance",           "作案进口"
	 *  <br>"exit",               "作案出口"  
	 *  <br>"communitys",          "发案社区，List集合"
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 案件基本信息
	 */
	
	/*这个返回值有变化*/
	public Pager<CriminalBasicCase> findCaseByConditions(Map<String,Object> conditions, int pageNo, int pageSize);
	
	
	/**
	 * 根据条件分页查找案件
	 * @param conditions 查询条件map
	 * 	<br>"caseCode",      	  "案件编号"
	 *	<br>"type",      	  	  "案件类别"
	 *	<br>"firstSorts",      	  "案件一级性质，List集合"
	 *	<br>"secondSorts",        "案件二级性质，List集合"
	 *	<br>"features",      	  "作案特点，List集合"
	 *	<br>"occurPlace",  		  "作案处所"
	 *	<br>"period",             "作案时段"
	 *	<br>"peopleNum",          "作案人数"
	 *  <br>"entrance",           "作案进口"
	 *  <br>"exit",               "作案出口"  
	 *  <br>"communitys",          "发案社区，List集合"
	 
	 * @return 案件基本信息
	 */
	public List<CriminalBasicCase> findAllCaseByConditions(Map<String,Object> conditions);
	
	public void refresh(Object obj);
	
}
