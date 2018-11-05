package com.taiji.pubsec.szpt.dagl.bean;

public class YrydCensusBean {
	
	private String id;
	
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 身份证号
	 */
	private String idNum;
	
	/**
	 * 与户主关系
	 */
	private String relationWithHouseholder;
	
	/**
	 * 户籍地址
	 */
	private String censusAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getRelationWithHouseholder() {
		return relationWithHouseholder;
	}

	public void setRelationWithHouseholder(String relationWithHouseholder) {
		this.relationWithHouseholder = relationWithHouseholder;
	}

	public String getCensusAddress() {
		return censusAddress;
	}

	public void setCensusAddress(String censusAddress) {
		this.censusAddress = censusAddress;
	}
	
}
