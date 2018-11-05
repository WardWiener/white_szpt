package com.taiji.pubsec.szpt.xtgl.authority;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taiji.pubsec.szpt.xtgl.resource.ResourceBean;


/**
 * 权限Bean
 * 
 * @author xinfan
 *
 */
public class AuthorityBean {
	private String id;
	// 权限编码
	private String authorityCode;
	// 权限名称
	private String authorityName;
	// 权限类型
	private String authorityType;
	// 更新时间
	private Date authorityupdatedTime;
	// 资源类型
	private String resourceType;
	// 资源集合
	private List<ResourceBean> resourceLibBeans = new ArrayList<ResourceBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	public List<ResourceBean> getResourceLibBeans() {
		return resourceLibBeans;
	}

	public void setResourceLibBeans(List<ResourceBean> resourceLibBeans) {
		this.resourceLibBeans = resourceLibBeans;
	}

	public Date getAuthorityupdatedTime() {
		return authorityupdatedTime;
	}

	public void setAuthorityupdatedTime(Date authorityupdatedTime) {
		this.authorityupdatedTime = authorityupdatedTime;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
}
