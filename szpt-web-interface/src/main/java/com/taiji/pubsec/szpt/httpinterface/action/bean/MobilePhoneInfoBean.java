package com.taiji.pubsec.szpt.httpinterface.action.bean;

import java.util.Date;

import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

/**
 * 终端设备信息
 * @author wangfx
 *
 */
public class MobilePhoneInfoBean {
	
	private String id;
	
	/**
	 * 电话号码
	 */
	private String number;
	
	/**
	 * ime号
	 */
	private String imei;
	
	/**
	 * Mac地址
	 */
	private String mac;
	
	/**
	 * 更新时间
	 */
	private Date updatedTime;
	
	/**
	 * 更新时间
	 */
	private String updatedTimeStr;

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

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedTimeStr() {
		return (null == updatedTime ? "" : HighriskPersonUtil.date2str(updatedTime));
	}

	public void setUpdatedTimeStr(String updatedTimeStr) {
		this.updatedTimeStr = updatedTimeStr;
	}


}
