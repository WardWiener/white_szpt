package com.taiji.pubsec.szpt.preprocess.service;

import java.util.List;
import java.util.Map;


/**
 * 租赁信息存储到HBase预处理，实现抽象类{@link HBasePreProcess}。
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:01
 */
public class TenementHBasePreProcess extends HBasePreProcess {


	public boolean saveOrUpdate(List<Map<String,Object>> dataRows){
		return false;
	}

}