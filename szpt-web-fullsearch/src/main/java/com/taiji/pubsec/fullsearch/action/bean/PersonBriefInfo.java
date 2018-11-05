package com.taiji.pubsec.fullsearch.action.bean;

import java.util.Date;

import javax.persistence.Column;

/**
 * 案件检索结果
 * @author dixiaofeng
 * @version 1.0
 * @created 21-二月-2017 9:57:49
 */
public class PersonBriefInfo {
	

	/**
	 * 户籍地址
	 */
	private String id;
	
	/**
	 * 户籍地址
	 */
	private String address;
	/**
	 * 出生地
	 */
	private String birthaddress;
	/**
	 * 出生日期
	 */
	private String birthday;
	/**
	 * 死亡日期
	 */
	private Date deadDate;
	/**
	 * 文化程度
	 */
	private String culture;
	/**
	 * 户主
	 */
	private String householder;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 职业
	 */
	private String occupation;
	/**
	 * 婚姻情况
	 */
	private String marry;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 与户主关系
	 */
	private String relation;
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 是否是高危人
	 */
	private String type;
	
	/**
	 * 是否是嫌疑人
	 */
	private String sfsXyy;
	/**
	 * 曾用名
	 */
	private String oldname;
	
	/**
	 * 现住地
	 */
	private String localAddress;
	
	/**
	 * 预警级别
	 */
	private String alertlevel;
	
	/**
	 * 前科类型
	 */
	private String qianKeType;
	
	/**
	 * 人员类别
	 */
	private String persontypeName;
	
	private Boolean sfbGz;
	
	public String getPersontypeName() {
		return persontypeName;
	}
	public void setPersontypeName(String persontypeName) {
		this.persontypeName = persontypeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthaddress() {
		return birthaddress;
	}
	public void setBirthaddress(String birthaddress) {
		this.birthaddress = birthaddress;
	}
	
	public Date getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}
	
	public String getCulture() {
		return culture;
	}
	public void setCulture(String culture) {
		this.culture = culture;
	}
	public String getHouseholder() {
		return householder;
	}
	public void setHouseholder(String householder) {
		this.householder = householder;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getMarry() {
		return marry;
	}
	public void setMarry(String marry) {
		this.marry = marry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAlertlevel() {
		return alertlevel;
	}
	public void setAlertlevel(String alertlevel) {
		this.alertlevel = alertlevel;
	}
	public Boolean getSfbGz() {
		return sfbGz;
	}
	public void setSfbGz(Boolean sfbGz) {
		this.sfbGz = sfbGz;
	}
	public String getSfsXyy() {
		return sfsXyy;
	}
	public void setSfsXyy(String sfsXyy) {
		this.sfsXyy = sfsXyy;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public String getQianKeType() {
		return qianKeType;
	}
	public void setQianKeType(String qianKeType) {
		this.qianKeType = qianKeType;
	}
	
}