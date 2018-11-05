package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.highriskpersonalert.model.MonitoringGuardInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;

/**
 * 监守信息接口
 * @author wangfx
 *
 */
public interface IMonitoringGuardInfoService {
	
	/**
	 * 根据时间及身份证号查询监守信息
	 * @param personIdCode 身份证号
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return 统计信息
	 */
	List<StatisticsInfo> findMonitoringGuardInfoByPerson(String personIdCode, Date timeStart, Date timeEnd);

	/**
	 * 根据时间及身份证号查询监守信息
	 * @param personIdCode 身份证号
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return 监守信息集合
	 */
	List<MonitoringGuardInfo> findMonitoringGuardInfosByPerson(String personIdCode, Date timeStart, Date timeEnd);
}
