package com.taiji.pubsec.szpt.placemonitor.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.placemonitor.pojo.WifiPlaceInAndOutInfoBean;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStateRecord;

/**
 * 监控场所接口
 * @author wangfx
 *
 */
public interface PlaceMonitorService {
	
	public List<WifiPlaceInAndOutInfoBean> findAllByMacs(List<String> macs) ;
	
	/**
	 * 分组查询监控场所的设备数量
	 * @return groupName 场所名称  count 设备数量
	 */
	List<PlaceSumInfo> sumupDeviceaByPlace();
	
	/**
	 * 查询监控场所总数量
	 * @return 监控场所总数量
	 */
	Integer sumupPlaceNum();
		
	/**
	 * 根据场所名称查询场所详细信息，场所名称业务上唯一不重复
	 * @param placeName 场所名称
	 * @return 场所详细信息
	 */
	PlaceBasicInfo findPlaceBasicInfoByPlaceName(String placeName);
	
	/**
	 * 统计监控的设备数量
	 * @return
	 */
	int sumupMonitorDeviceCount();
	
	/**
	 * 按照mac查询当前时间窗口内的出入记录
	 * @param mac mac地址
	 * @return 出入记录集合
	 */
	public List<Map<String, Object>> findWifiRecordInRealTimeByMac(String mac) ;
	
	/**
	 * 按照场所编码查询当前时间窗口内的出入记录
	 * @param placeCode 场所编码
	 * @return 出入记录集合
	 */
	public List<Map<String, Object>> findWifiRecordInRealTimeByPlace(String placeCode) ;
	

}
