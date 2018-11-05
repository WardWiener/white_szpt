package com.taiji.pubsec.szpt.xtgl.role;


import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.xtgl.authority.AuthorityBean;


/**
 * 角色Bean
 * 
 * @author xinfan
 *
 */
public class RoleBean {

	private String id;
	// 角色名称
	private String roleName;
	// 角色状态
	private String status;
	// 角色编码
	private String roleCode;
	private List<AuthorityBean> authorityBeanBeans = new ArrayList<AuthorityBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public List<AuthorityBean> getAuthorityBeanBeans() {
		return authorityBeanBeans;
	}

	public void setAuthorityBeanBeans(List<AuthorityBean> authorityBeanBeans) {
		this.authorityBeanBeans = authorityBeanBeans;
	}

}
