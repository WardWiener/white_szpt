package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;


/**
 * 移动电话信息 Bean
 * 
 */
public class MobilePhoneInfoBean {

	private String id;

	private String number;// 电话号码

	private Long updateTime;// 更新时间
	
	private String imei;
	
	private String mac;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
