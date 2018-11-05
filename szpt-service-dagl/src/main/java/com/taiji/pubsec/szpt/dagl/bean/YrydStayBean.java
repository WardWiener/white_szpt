package com.taiji.pubsec.szpt.dagl.bean;

public class YrydStayBean {
	
	private String id;
	
	/**
	 * 登记日期
	 */
	private String startDate;
	/**
	 * 到期日期
	 */
	private String endDate;
	
	/**
	 * 暂住事由
	 */
	private String reason;
	
	/**
	 * 暂住场所
	 */
	private String place;
	
	/**
	 * 区县
	 */
	private String district;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	
}
