package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;


public class PersonTrackInfoBean {

	/**
	 * 轨迹描述
	 * @return
	 */
	private String trackDescription;
	
	/**
	 * 轨迹类型：如旅馆酒店、火车、网吧、飞机。
	 * @return
	 */
	private String trackType;
	
	/**
	 * 轨迹类型：如旅馆旅馆酒店、火车、网吧、飞机。
	 * <br>具体描述酒店、火车到站、火车出站、网吧、飞机出港、飞机入港。
	 * <br>具体描述根据配置文件中的当前位置判断出站入站之类
	 * @return
	 */
	private String trackTypeDescription;
	
	private String placeName;
	
	private Long appearTime; 
	
	private String longitude;
	
	private String latitude;
	
	private String originLocation;
	
	private String destinationLocation;
	
	private boolean isWifi;
	
	private Long leaveTime; 

	public String getTrackDescription() {
		return trackDescription;
	}

	public void setTrackDescription(String trackDescription) {
		this.trackDescription = trackDescription;
	}

	public String getTrackType() {
		return trackType;
	}

	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}

	public String getTrackTypeDescription() {
		return trackTypeDescription;
	}

	public void setTrackTypeDescription(String trackTypeDescription) {
		this.trackTypeDescription = trackTypeDescription;
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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Long getAppearTime() {
		return appearTime;
	}

	public void setAppearTime(Long appearTime) {
		this.appearTime = appearTime;
	}

	public String getOriginLocation() {
		return originLocation;
	}

	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public boolean getIsWifi() {
		return isWifi;
	}

	public void setIsWifi(boolean isWifi) {
		this.isWifi = isWifi;
	}

	public Long getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}
	
}
