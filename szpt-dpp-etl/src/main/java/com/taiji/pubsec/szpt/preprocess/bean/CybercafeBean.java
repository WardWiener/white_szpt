package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;

/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:54:59
 */
public class CybercafeBean {

	private String id;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 网吧名称
	 */
	private String name;
	/**
	 * 网吧地址
	 */
	private String address;
	/**
	 * 进入时间
	 */
	private Date enterTime;
	/**
	 * 离开时间
	 */
	private Date leaveTime;
	/**
	 * 高危人人员类型编码
	 */
	private String personTypeCode;
	/**
	 * 高危人前科类型编码
	 */
	private String criminalTypeCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getPersonTypeCode() {
		return personTypeCode;
	}

	public void setPersonTypeCode(String personTypeCode) {
		this.personTypeCode = personTypeCode;
	}

	public String getCriminalTypeCode() {
		return criminalTypeCode;
	}

	public void setCriminalTypeCode(String criminalTypeCode) {
		this.criminalTypeCode = criminalTypeCode;
	}

}