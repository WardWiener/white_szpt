package com.taiji.pubsec.szpt.preprocess.service;

import java.util.List;
import java.util.Map;


/**
 * 案件信息存储到Solr预处理，实现抽象类{@link SolrPreProcess}。
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:54:59
 */
public class CaseSolrPreProcess extends SolrPreProcess {

	/**
	 * 创建或更新数据到HBase中。成功返回true，失败返回false。
	 * 
	 * @param obj
	 */
	public boolean saveOrUpdate(List<Map<String,Object>> dataRows){
		return false;
	}

}	