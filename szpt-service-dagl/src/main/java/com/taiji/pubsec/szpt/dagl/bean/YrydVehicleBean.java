package com.taiji.pubsec.szpt.dagl.bean;

public class YrydVehicleBean {
	
	private String id;
	
	/**
	 * 号牌号码
	 */
	private String licenseNumber;
	
	/**
	 * 号牌种类
	 */
	private String licenseType;
	
	/**
	 * 车辆类型
	 */
	private String vehicleClass;
	
	/**
	 * 品牌型号
	 */
	private String model;
	
	/**
	 * 车身颜色
	 */
	private String color;
	
	/**
	 * 发动机状态
	 */
	private String engineStatus;
	
	/**
	 * 卡口
	 */
	private String camera;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngineStatus() {
		return engineStatus;
	}

	public void setEngineStatus(String engineStatus) {
		this.engineStatus = engineStatus;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}
	
}
