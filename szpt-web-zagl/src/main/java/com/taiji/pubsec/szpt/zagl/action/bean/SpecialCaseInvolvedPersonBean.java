package com.taiji.pubsec.szpt.zagl.action.bean;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;

public class SpecialCaseInvolvedPersonBean {

	
	private String id;

	/**
	 * 专案
	 */
	private SpecialCase specialCase;
	/**
	 * 创建时间
	 */
	private long createdTime;
	/**
	 * 创建人
	 */
	private Person createPerson;
	/**
	 * 户籍地址
	 */
	private String householdAddress;
	/**
	 * 户籍
	 */
	private String householdRegister;
	
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 绰号
	 */
	private String nick;
	/**
	 * 手机号
	 */
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Person createPerson) {
		this.createPerson = createPerson;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getHouseholdRegister() {
		return householdRegister;
	}

	public void setHouseholdRegister(String householdRegister) {
		this.householdRegister = householdRegister;
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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SpecialCase getSpecialCase() {
		return specialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

}
