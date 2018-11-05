package com.taiji.pubsec.szpt.score.util.service;

import java.util.Date;

public interface HrpTrackScoreComputeService {

	/**
	 * 统计飞机、火车以贵阳为起点或终点的次数。
	 * @param identy 身份证号
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return 次数：从贵阳出去，或进入贵阳算一次
	 */
	public Integer countGyFlyAndTrainTravelTimes(String identy, Date startDay, Date endDay) ;
	
	/**
	 * 查询一定时间范围内的网吧停留时间
	 * @param identy 身份证号
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return 停留总时长，毫秒
	 */
	public Long countInternetBarStayTime(String identy, Date startDay, Date endDay) ;
	
	/**
	 * 查询一定时间范围内的酒店停留时间
	 * @param identy 身份证号
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @return 停留总时长，毫秒
	 */
	public Long countHotelStayTime(String identy, Date startDay, Date endDay) ;
}
