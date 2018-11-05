package com.taiji.pubsec.szpt.placemonitor.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.placemonitor.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.StatisticsInfoTwoValue;

/**
 * 场所相关统计
 * @author genganpeng
 *
 */
public interface IPlaceStatisticsService {

	/**
	 * 根据时间查询派出所对应的监控场所数量，
	 * @return 统计结果列表。StatisticsInfo中name为派出所名称  value为场所数量
	 */
	List<StatisticsInfo> placeStatistics();
	
	/**
	 * wifi网络围栏统计
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param pcscodes 派出所编码列表
	 * @return 统计结果列表。StatisticsInfoTwoValue中name为派出所名称  value为采集数量 value_two为五色人员数量
	 */
	List<StatisticsInfoTwoValue> wifiStatistics(Date startDay, Date endDay, List<String> pcscodes);
	
}
