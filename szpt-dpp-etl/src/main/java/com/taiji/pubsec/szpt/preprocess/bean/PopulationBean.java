package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;

/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:00
 */
public class PopulationBean {

	private String id;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 警情名称
	 */
	private String name;
	/**
	 * 接警时间
	 */
	private String oldName;
	/**
	 * 采集时间
	 */
	private Date collectTime;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 类型（是否高危人）
	 */
	private String type;
	/**
	 * 高危人人员类型名称
	 */
	private String personType;
	/**
	 * 高危人前科类型名称
	 */
	private String criminalType;
	/**
	 * 预警级别名称
	 */
	private String alertLevel;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 出生地
	 */
	private String birthAddress;
	/**
	 * 户籍地
	 */
	private String address;
	/**
	 * 文化程度
	 */
	private String culture;
	/**
	 * 婚姻情况
	 */
	private String marry;
	/**
	 * 职业
	 */
	private String occupation;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 户主姓名
	 */
	private String householder;
	/**
	 * 与户主关系
	 */
	private String relation;

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

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getCriminalType() {
		return criminalType;
	}

	public void setCriminalType(String criminalType) {
		this.criminalType = criminalType;
	}

	public String getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHouseholder() {
		return householder;
	}

	public void setHouseholder(String householder) {
		this.householder = householder;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}