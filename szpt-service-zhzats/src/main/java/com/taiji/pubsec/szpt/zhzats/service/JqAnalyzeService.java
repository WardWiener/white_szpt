package com.taiji.pubsec.szpt.zhzats.service;

import com.taiji.pubsec.szpt.zhzats.model.JqAnalyze;

public interface JqAnalyzeService {

	/**
	 * 根据警情id查询反馈信息
	 * @param 	jqId 一个警情id
	 * @return 	一条警情信息JqAnalyze
	 */
	JqAnalyze findJqAnalyzeById(String jqId);
}
