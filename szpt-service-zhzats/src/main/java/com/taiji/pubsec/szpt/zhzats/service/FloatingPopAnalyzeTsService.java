package com.taiji.pubsec.szpt.zhzats.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.zhzats.bean.FloatingPopInfo;

public interface FloatingPopAnalyzeTsService {

	/**
	 * 查询流动人口
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param pcsCodes 派出所编码集合
	 * @return FloatingPopInfo的集合， 包含unitName（单位名称）、floatingNumSum（本期的流动人口数量）
	 */
	public List<FloatingPopInfo> findFloatingPopInfosByPcsCodes(Date startDay, Date endDay, String[] pcsCodes) ;
	
}
