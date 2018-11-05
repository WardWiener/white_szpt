package com.taiji.pubsec.szpt.bean;

public class AjBean {

	/**
	 * 案件名称
	 */
	private String ajName ;
	/**
	 * 案件编号
	 */
	private String ajCode ;
	/**
	 * 发案时间
	 */
	private Long faTime ;
	/**
	 * 纬度
	 */
	private Double latitude ;
	/**
	 * 经度
	 */
	private Double longitude ;
	
	public String getAjName() {
		return ajName;
	}
	public void setAjName(String ajName) {
		this.ajName = ajName;
	}
	public String getAjCode() {
		return ajCode;
	}
	public void setAjCode(String ajCode) {
		this.ajCode = ajCode;
	}
	public Long getFaTime() {
		return faTime;
	}
	public void setFaTime(Long faTime) {
		this.faTime = faTime;
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
}
