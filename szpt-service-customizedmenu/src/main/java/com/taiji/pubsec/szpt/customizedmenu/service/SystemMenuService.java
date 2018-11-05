package com.taiji.pubsec.szpt.customizedmenu.service;

import java.util.List;

import com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu;

public interface SystemMenuService {
	
	/**
	 * 添加系统菜单
	 * @param 自定义菜单
	 *
	 */
	public void saveSystemMenu(SystemMenu sm);
	
	/**
	 * 删除系统菜单
	 * @param 自定义菜单
	 *
	 */
	public void deleteSystemMenu(SystemMenu sm);
	
	/**
	 * 
	 * @param 自定义菜单
	 *
	 */
	public List<SystemMenu> findAllSystemMenuByParentId(String parentId);
	
	/**
	 * 
	 * @param 自定义菜单
	 *
	 */
	public List<SystemMenu> findAllSystemMenuByType(String type);
	
	/**
	 * 查找所有的功能选项
	 * @param 自定义菜单
	 *
	 */
	public List<SystemMenu> findAll();
	
	/**
	 * 通过id查询systemMenu
	 * @param 菜单
	 *
	 */
	public SystemMenu findById(String id);


}
