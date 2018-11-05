package com.taiji.pubsec.szpt.zagl.action.bean;

import java.util.List;

public class ProjectsMaintenanceBean {
	/**
	 * 案件id
	 */
	private String id;
	/**
	 * 案件编码
	 */
	private String code;
	/**
	 * 案件性质
	 */
	private String nature;

	/**
	 * 案情简介
	 */
	private String content;
	/**
	 * 创建日期
	 */
	private String createdTime;
	
	/**
	 * 专案名称
	 */
	private String name;
	
	/**
	 * 最后更新时间
	 */
	private String updatedTime;
	/**
	 * 涉及子案件id
	 */
	private List<String> sonIDList;
	/**
	 * 涉及子案件编码
	 */
	private List<String> sonCaseCodeList;
	/**
	 * 专案组成员
	 */
	private List<String> zazcyList;
	/**
	 * 专案组成员角色
	 */
	private List<String> roleList;
	/**
	 * 关联情报条数
	 */
	private  int  qbSize;
	
	/**
	 * 是否置顶(1 置顶  0  不置顶)
	 */
	private String isStick;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getZazcyList() {
		return zazcyList;
	}
	public void setZazcyList(List<String> zazcyList) {
		this.zazcyList = zazcyList;
	}
	public int getQbSize() {
		return qbSize;
	}
	public void setQbSize(int qbSize) {
		this.qbSize = qbSize;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public String getIsStick() {
		return isStick;
	}
	public void setIsStick(String isStick) {
		this.isStick = isStick;
	}
	public List<String> getSonIDList() {
		return sonIDList;
	}
	public void setSonIDList(List<String> sonIDList) {
		this.sonIDList = sonIDList;
	}
	public List<String> getSonCaseCodeList() {
		return sonCaseCodeList;
	}
	public void setSonCaseCodeList(List<String> sonCaseCodeList) {
		this.sonCaseCodeList = sonCaseCodeList;
	}
}
