package com.taiji.pubsec.szpt.bean;

public class LocationCount {

	private String name ;
	private String code ;
	private Double longitude ;
	private Double latitude ;
	
	public LocationCount() {}
	
	public LocationCount(String name, String code){
		this.name = name ;
		this.code = code ;
		
	}
	public LocationCount(String name, String code, Double longitude, Double latitude){
		this.name = name ;
		this.code = code ;
		this.longitude = longitude ;
		this.latitude = latitude ;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
