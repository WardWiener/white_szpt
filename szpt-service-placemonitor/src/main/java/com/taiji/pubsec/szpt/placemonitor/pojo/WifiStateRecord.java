package com.taiji.pubsec.szpt.placemonitor.pojo;

public class WifiStateRecord {

	/**
	 * 进入时间
	 */
	private Long enterTime;
	
	/**
	 * 离开时间
	 */
	private Long leaveTime ;
	
	/**
	 * 纬度
	 */
	private Double latitude;
	
	/**
	 * 经度
	 */
	private Double longitude ;
	
	/**
	 * 手机号
	 */
	private String phone ;
	
	/**
	 * mac地址
	 */
	private String mac ;
	
	/**
	 * 场所名称
	 */
	private String placeName ;
	
	/**
	 * 场所编码
	 */
	private String placeCode ;
	
	/**
	 * 停留时间
	 */
	private Long stayInterval ;
	
	/**
	 * 人员名称
	 */
	private String personName ;
	
	/**
	 * 身份证号
	 */
	private String identy ;

	public Long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}

	public Long getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Long getStayInterval() {
		return stayInterval;
	}

	public void setStayInterval(Long stayInterval) {
		this.stayInterval = stayInterval;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIdenty() {
		return identy;
	}

	public void setIdenty(String identy) {
		this.identy = identy;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
