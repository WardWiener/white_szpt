package com.taiji.pubsec.szpt.placemonitor.service;

import java.util.List;

public interface WifiPlaceService {

	/**
	 * 查询某个派出所下面的wifi监控场所的编码
	 * @param pcsCode 派出所编码
	 * @return wifi监控场所的编码的集合
	 */
	public List<String> findWifiPlaceCodesByPcsCode(String pcsCode) ;
}
