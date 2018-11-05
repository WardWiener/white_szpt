package com.taiji.pubsec.szpt.dagl.bean;

import java.util.Date;

public class YrydLocusBean {
	
	/**
	 * 进入时间
	 */
	private String id ;
	
	/**
	 * 进入时间
	 */
	private String enterTime ;
	
	/**
	 * 离开时间
	 */
	private String leaveTime ;
	
	/**
	 * 场所编码
	 */
	private String placeCode ;
	
	/**
	 * 场所名称
	 */
	private String placeName ;
	
	/**
	 * mac地址名称
	 */
	private String mac ;
	
	/**
	 * 手机号
	 */
	private String phone ;
	
	/**
	 * 场所经度
	 */
	private String longitude;
	
	/**
	 * 场所纬度
	 */
	private String latitude;
	
	/**
	 * 停留时间
	 */
	private Long stayInterval ;





	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Long getStayInterval() {
		return stayInterval;
	}

	public void setStayInterval(Long stayInterval) {
		this.stayInterval = stayInterval;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	

}
