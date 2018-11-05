package com.taiji.pubsec.szpt.placemonitor.service;

import java.util.List;
import java.util.Map;
import com.taiji.pubsec.persistence.dao.Pager;

import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;

/**
 * 场所基本信息接口
 * @author wangfx
 *
 */
public interface PlaceBasicInfoService {
	
	/**
	 * 查询所有场所，并按照场所监控的人数降序
	 * @return
	 */
	List<PlaceBasicInfo> findAll();
	
	/**
	 * 更新WiFi场所信息
	 *
	 */
	void updatePlaceBasicInfo(PlaceBasicInfo placeBasicInfo);
	
	/**
	 * 通过id查找WiFi场所信息
	 * @return
	 */
	PlaceBasicInfo findById(String id);
	
	/**
	 * 通过code查找WiFi场所信息
	 * @return
	 */
	PlaceBasicInfo findByCode(String code);
	
	/**
	 * 通过条件查找WiFi场所信息集合
	 * name:场所名称
	 * code:场所编码
	 * address:场所地址
	 * isLoca:是否是本区域(默认 经开区,区域编码520114开头)
	 * @return 符合要求的WiFi场所信息集合
	 */
	Pager<PlaceBasicInfo> queryPlaceBasicInfoByCondition(Map<String , Object> conditions);


}
