package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPersonHistoryInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;

/**
 * 高危人员历史信息接口
 * @author wangfx
 *
 */
public interface IHighriskPersonHistoryInfoService {
	
	/**
	 * 开始时间和结束时间统计高危人员的新增五色预警人员和人员类别调整情况
	 * @param timeStart 开始时间
	 * @param timeEnd 结束时间
	 * @return resultMap
	 * <br>:highriskPersonAdjustment 高温人员调整Map
	 * <li>key:五色+其他 ,value:Map(key:操作方法（新增or删除）value：计数)
	 * <br>:personTypeAdjustment 人员调整Map
	 * <li>key:人员类型，value:Map(key:操作方法（新增or删除）value：计数)
	 * <br>:newAddHighriskPerson 新增高危人员Map
	 * <li>key:犯罪类型，value:计数
	 */
	Map<String, Object> statisticsHighPersonHistoryInfo(Date timeStart, Date timeEnd);

	/**
	 * add
	 * @param highriskPersonHistoryInfo
	 */
	void addHighriskPersonHistoryInfo(HighriskPersonHistoryInfo highriskPersonHistoryInfo);
}
