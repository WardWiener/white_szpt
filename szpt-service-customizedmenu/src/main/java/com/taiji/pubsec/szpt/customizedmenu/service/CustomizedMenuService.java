package com.taiji.pubsec.szpt.customizedmenu.service;

import java.util.List;

import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;
import com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu;
import com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu;

public interface CustomizedMenuService {
	
	/**
	 * 
	 * @param 用户id
	 * @return 用户关联的所有模块
	 */
	public List<CustomizedMenu> findAllModuleUrlByUserId(String acountId , String type);
	
	/**
	 * 删除用户关联的url
	 * @return 所有模块的url
	 */
	public void deleteCustomizedMenu(String UserId,String type);
	
	/**
	 * 添加自定义菜单
	 * @param 自定义菜单
	 *
	 */
	public void saveCustomizedMenu(CustomizedMenu cm);
	
}
