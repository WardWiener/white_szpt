package com.taiji.pubsec.szpt.dagl.bean;

import java.util.Date;

public class YrydCybercafeBean {
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 网吧名称
	 */
	private String name;
	
	/**
	 * 网吧地址
	 */
	private String address;
	
	/**
	 * 上网时间
	 */
	private String startTime;
	
	/**
	 * 下网时间
	 */
	private String endTime;
	
	/**
	 * 终端号
	 */
	private String terminalNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
