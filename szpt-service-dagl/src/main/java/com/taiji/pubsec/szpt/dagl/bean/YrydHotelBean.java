package com.taiji.pubsec.szpt.dagl.bean;

import java.util.Date;

public class YrydHotelBean {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 旅馆名称
	 */
	private String name;
	
	/**
	 * 旅馆地址
	 */
	private String address;
	
	/**
	 * 入住时间
	 */
	private String stratTime;
	
	/**
	 * 退宿时间
	 */
	private String endTime;
	
	/**
	 * 房间号
	 */
	private String roomNum ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	
	

}
