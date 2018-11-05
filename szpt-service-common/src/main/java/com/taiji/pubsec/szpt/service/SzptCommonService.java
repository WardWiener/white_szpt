package com.taiji.pubsec.szpt.service;

import java.util.List;

import com.taiji.pubsec.szpt.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.common.model.Community;

public interface SzptCommonService {

	/**
	 * 查询所有派出所的分布常量预警值
	 * @param type
	 * @return
	 */
	@Deprecated
	public List<AlertThresholdInfo> findPcsFenbuAlertThresholdInfosByType(String type) ;
	
	/**
	 * 查询所有村区
	 * @return
	 */
	public List<Community> findAllCommunity() ;
	
	/**
	 * 根据编码查询村区
	 * @param code 村区编码
	 * @return
	 */
	public Community findCommunityByCode(String code) ;
	
	/**
	 * 根据id查询村区
	 * @param id
	 * @return
	 */
	public Community findCommunityById(String id) ;

	/**
	 * 根据派出所id查询村居
	 * @return
	 */
	public List<Community> findCommunityByPcsId(String id)  ;
	
	public List<Community> findCommunityByPcsCode(String id);
}
