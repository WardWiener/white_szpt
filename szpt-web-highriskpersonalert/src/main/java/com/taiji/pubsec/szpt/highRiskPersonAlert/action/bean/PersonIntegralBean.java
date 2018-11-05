package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;



/**
 * 人员布控
 */
public class PersonIntegralBean {

	private String id;
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 身份证号
	 */
	private String idcode;
	
	/**
	 * 积分
	 */
	private int accumulatePoints;

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

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public int getAccumulatePoints() {
		return accumulatePoints;
	}

	public void setAccumulatePoints(int accumulatePoints) {
		this.accumulatePoints = accumulatePoints;
	}
}
