package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.GeographicalZonePeopleInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;

/**
 * 地缘性查询接口
 * @author wangfx
 *
 */
public interface IGeographicalZoneService {
	
	/**
	 * 根据时间区间查询地缘性人员新
	 * @param paramMap 查询条件
	 * <br>:timeStart 开始时间
	 * <br>:timeEnd 结束时间
	 * @return 统计结果
	 */
	List<StatisticsInfo> findByGeographicalZone(Map<String, Object> paramMap);
	
	/**
	 * 根据时间区间、区域查询地缘性人员信息
	 * @param paramMap 查询条件
	 * <br>:zoneName 区域名称
	 * <br>:timeStart 开始时间
	 * <br>:timeEnd 结束时间
	 * @param pageNo 页数
	 * @param pageSize 条数
	 * @return 人员信息分页
	 */
	Pager<GeographicalZonePeopleInfo> findGeographicalZonePeopleInfoByZone(Map<String, Object> paramMap, int pageNo, int pageSize);

}
