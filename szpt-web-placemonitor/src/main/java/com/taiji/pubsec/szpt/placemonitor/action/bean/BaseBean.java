package com.taiji.pubsec.szpt.placemonitor.action.bean;

/**
 * 基础bean(可任意加属性);
 * 
 * @author WL-PC
 *
 */
public class BaseBean {

	private Long startTime; // 开始时间

	private Long endTime; // 结束时间

	private String placeName; // 场所名称

	private String deviceSum; // 设备总数

	private String longitude;// 经度

	private String latitude;// 纬度

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDeviceSum() {
		return deviceSum;
	}

	public void setDeviceSum(String deviceSum) {
		this.deviceSum = deviceSum;
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
