package com.taiji.pubsec.szpt.ajgl.service;

import com.taiji.pubsec.szpt.ajgl.model.CriminalObject;

/**
 * 涉案物品查询服务接口
 * @author chengkai
 *
 */
public interface ICriminalObjectService {
	
	/**
	 * 通过id查询涉案物品
	 * @param id 涉案物品id
	 * @return 返回涉案物品信息
	 */
	CriminalObject findById(String id);
	
}
