package com.taiji.pubsec.szpt.surveillance.util.message.surveillist;

import java.io.Serializable;

public class SurveilListMobileInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715255251077632183L;
	
	/**
	 * 布控单多端手机mac实体的id
	 */
	private String wifiClueId ;
	
	private String mac; //MAC地址
    private String phone;    //手机号码
   
    
    public SurveilListMobileInfo(String wifiClueId, String mac, String phone){
    	this.wifiClueId = wifiClueId;
    	this.mac = mac;
    	this.phone = phone;
    }

	public String getWifiClueId() {
		return wifiClueId;
	}

	public void setWifiClueId(String wifiClueId) {
		this.wifiClueId = wifiClueId;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

    
}
