package com.taiji.pubsec.szpt.zhzats.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.zhzats.bean.InterrogatSituInfo;

public interface InterrogatSituAnalyzeTsService {

	/**
	 * 查询盘查人口
	 * @param startDay 开始日
	 * @param endDay 结束日，传入的参数时分秒已清零，日已+1
	 * @param pcsCodes 派出所编码集合
	 * @return InterrogatSituInfo的集合，包括派出所名称，newManpowerSum数量，newCarSum数量和newCarNotKySum数量
	 */
	public List<InterrogatSituInfo> findInterrogatSituByPcsCodes(Date startDay, Date endDay, String[] pcsCodes) ;
	
}
