package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfoTwoValue;

/**
 * 高危人员相关统计接口
 * @author wangfx
 *
 */
public interface IHighriskPeopleStatisticsService {
	
	/**
	 * 高危人员类型统计
	 * @return 统计结果
	 */
	List<StatisticsInfo> highriskPeopleTypeStatistics();
	
	/**
	 * 统计核查相关的信息，数据从核查人的数据过来
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return 统计结果
	 */
	List<PersonCheckInfo> statisticsCheckPeopleInfo(Date timeStart, Date timeEnd);
	
	/**
	 * 根据人员身份证查询核查人信息
	 * 
	 * @param idcode 高危人身份证
	 * @return 统计结果
	 */
	List<PersonCheckInfo> statisticsCheckPeopleInfoByPsersonIdcode(String idcode);
	
	/**
	 * 根据时间查询派出所对应的五色人员数量，
	 * @param timeStart 开始时间，可为空
	 * @param timeEnd 结束时间，可为空
	 * @return 统计结果列表。StatisticsInfo中key为派出所名称，value为派出所对应的五色人员数量
	 */
	List<StatisticsInfo> fiveColorPeopleStatisticsByUnit(Date timeStart, Date timeEnd);
	
	
	/**
	 * 在控人员统计
	 * @param timeStart 开始时间，可为空
	 * @param timeEnd 结束时间，可为空
	 * @param pcsCodes 派出所编码数组
	 * @return 统计结果列表。StatisticsInfoTwoValue中name为派出所名称  value为原有在控 value_two为时间段内新增
	 */
	List<StatisticsInfoTwoValue> zaiKongPeopleStatistics(Date timeStart, Date timeEnd, List<String> pcsCodes);
}
