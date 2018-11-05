package com.taiji.pubsec.szpt.bean;

public class TimeIntervalBean{
	/**
	 * 进入时间
	 */
	private Long enterTime;
	
	/**
	 * 离开时间
	 */
	private Long leaveTime ;
	
	/**
	 * 离开时间
	 */
	private String macAddress ;
	
	public Long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}

	public Long getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
}