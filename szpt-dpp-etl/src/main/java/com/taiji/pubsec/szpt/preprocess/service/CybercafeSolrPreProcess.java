package com.taiji.pubsec.szpt.preprocess.service;

import java.util.List;
import java.util.Map;


/**
 * 网吧上网信息存储到Solr预处理，实现抽象类{@link SolrPreProcess}。
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:00
 */
public class CybercafeSolrPreProcess extends SolrPreProcess {

	public boolean saveOrUpdate(List<Map<String,Object>> dataRows){
		return false;
	}

}