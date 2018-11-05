package com.taiji.pubsec.szpt.preprocess.service;

import java.util.List;
import java.util.Map;


/**
 * ETL数据预处理接口。
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:00
 */
public interface DataPreProcess {

	/**
	 * 创建或更新数据到HBase中。成功返回true，失败返回false。
	 * 
	 * @param dataRows 多行数据，每行数据以key-value表示，key为存储列名，value为对应值对象。
	 */
	public boolean saveOrUpdate(List<Map<String,Object>> dataRows);

}