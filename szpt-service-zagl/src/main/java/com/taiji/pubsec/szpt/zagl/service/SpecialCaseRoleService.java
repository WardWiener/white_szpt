package com.taiji.pubsec.szpt.zagl.service;

import java.util.List;

import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;


/**
 * 专案角色服务接口，增删该查启用停用
 * @author dixiaofeng
 * @version 1.0
 * @created 21-11月-2016 19:24:13
 */
public interface SpecialCaseRoleService {

	/**
	 * 查询所有的角色
	 * @return 返回所有的角色list，按角色的编码排序
	 */
	List<SpecialCaseRole> findOnloadData();
	
	/**
	 * 通过id查询角色
	 * @param roleId 角色id
	 * @return 返回角色
	 */
	SpecialCaseRole findById(String roleId);

	/**
	 * 添加角色
	 * @param name 角色名称
	 * @return
	 */
	void saveSpecialCaseRole(String name);

	/**
	 * 修改角色--以code为基准
	 * @param name 角色名称
	 * @param code 角色编码
	 * @return
	 */
	void updateSpecialCaseRole(String name, String code);

	/**
	 * 删除角色
	 * @param code 角色编码
	 * @return
	 */
	void deleteSpecialCaseRole(String code);

	/**
	 * 停用多个角色
	 * @param codeArr 角色编码list
	 */
	void stopSpecialCaseRole(List<String> codeArr);

	/**
	 * 启用多个角色
	 * @param codeArr 角色编码list
	 */
	void startSpecialCaseRole(List<String> codeArr);
	
	/**
	 * 查询相应状态的专案角色
	 * @param state 角色状态 
	 * @return 返回专案角色list
	 */
	List<SpecialCaseRole> findRolesByState(String state);
	
	/**
	 * 通过人员id和专案id查询该人员在这个专案中对应的角色
	 * @param personId 人员id
	 * @param caseId 专案id
	 * @return 返回角色信息，没找到返回null
	 */
	SpecialCaseRole findRoleByPersonIdAndCaseId(String personId, String caseId);
	
	/**
	 * 通过角色id查询该角色是否已分配人员
	 * @param roleId 角色id
	 * @return 若已分配人员，返回true；否则，返回false
	 */
	boolean isRoleUsed(String roleId);
}