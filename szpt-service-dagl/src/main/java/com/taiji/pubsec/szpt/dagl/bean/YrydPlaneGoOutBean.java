package com.taiji.pubsec.szpt.dagl.bean;

import java.util.Date;

public class YrydPlaneGoOutBean {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 航班日期
	 */
	private String stratTime;
	
	/**
	 * 航班号
	 */
	private String flyNum;
	
	/**
	 * 登机日期
	 */
	private String goAboardTime;
	
	/**
	 * 始发地
	 */
	private String startPlace;
	
	/**
	 * 目的地
	 */
	private String endPlace;
	
	/**
	 * 座位号
	 */
	private String seatNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getFlyNum() {
		return flyNum;
	}

	public void setFlyNum(String flyNum) {
		this.flyNum = flyNum;
	}



	public String getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getStratTime() {
		return stratTime;
	}

	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}

	public String getGoAboardTime() {
		return goAboardTime;
	}

	public void setGoAboardTime(String goAboardTime) {
		this.goAboardTime = goAboardTime;
	}
	
	
	

}
