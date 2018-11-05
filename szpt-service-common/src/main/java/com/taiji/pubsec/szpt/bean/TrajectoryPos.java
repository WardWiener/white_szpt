package com.taiji.pubsec.szpt.bean;

public class TrajectoryPos {
	private Double longitude ;
	private Double latitude ;
	
	public TrajectoryPos(Double longitude, Double latitude){
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
}
