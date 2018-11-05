package com.taiji.pubsec.szpt.bean;

public class AlertThresholdInfo {

	/**
	 * 单位名称
	 */
	private String name ;
	/**
	 * 单位编码
	 */
	private String code ;
	/**
	 * 单位预警值
	 */
	private Integer blueHoldValue ;
	
	private Integer yellowHoldValue ;
	
	private Integer orangeHoldValue ;
	
	private Integer redHoldValue ;
	
	public AlertThresholdInfo(String name, String code, Integer blueHoldValue, Integer yellowHoldValue, Integer orangeHoldValue, Integer redHoldValue){
		this.name = name ;
		this.code = code ;
		this.blueHoldValue = blueHoldValue ;
		this.yellowHoldValue = yellowHoldValue ;
		this.orangeHoldValue = orangeHoldValue ;
		this.redHoldValue = redHoldValue ;
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

	public Integer getBlueHoldValue() {
		return blueHoldValue;
	}

	public void setBlueHoldValue(Integer blueHoldValue) {
		this.blueHoldValue = blueHoldValue;
	}

	public Integer getYellowHoldValue() {
		return yellowHoldValue;
	}

	public void setYellowHoldValue(Integer yellowHoldValue) {
		this.yellowHoldValue = yellowHoldValue;
	}

	public Integer getRedHoldValue() {
		return redHoldValue;
	}

	public void setRedHoldValue(Integer redHoldValue) {
		this.redHoldValue = redHoldValue;
	}

	public Integer getOrangeHoldValue() {
		return orangeHoldValue;
	}

	public void setOrangeHoldValue(Integer orangeHoldValue) {
		this.orangeHoldValue = orangeHoldValue;
	}

	
	
}
