package com.taiji.pubsec.szpt.dagl.bean;

public class YrydCameraBean {
	
	private String id;
	
	/**
	 * 通过日期
	 */
	private String passDate;
	
	/**
	 * 通过时间
	 */
	private String passTime;
	
	/**
	 * 卡口类型
	 */
	private String cameraName;
	
	/**
	 * 卡口地址
	 */
	private String cameraAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassDate() {
		return passDate;
	}

	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}

	
	public String getCameraName() {
		return cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	public String getCameraAddress() {
		return cameraAddress;
	}

	public void setCameraAddress(String cameraAddress) {
		this.cameraAddress = cameraAddress;
	}

	

	
}
