package com.taiji.pubsec.szpt.dagl.bean;

import java.util.Date;

public class YrydTrainGoOutBean {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * id
	 */
	private String idcard;

	/**
	 * 购票时间
	 */
	private String buyTicketTime;
	
	/**
	 * 发车时间
	 */
	private String departTime;
	
	/**
	 * 车次
	 */
	private String trainNumber;
	
	/**
	 * 始发地
	 */
	private String startPlace;
	
	/**
	 * 目的地
	 */
	private String endPlace;
	
	/**
	 * 车厢号
	 */
	private String carriageNum;
	
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


	public String getBuyTicketTime() {
		return buyTicketTime;
	}

	public void setBuyTicketTime(String buyTicketTime) {
		this.buyTicketTime = buyTicketTime;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
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

	public String getCarriageNum() {
		return carriageNum;
	}

	public void setCarriageNum(String carriageNum) {
		this.carriageNum = carriageNum;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
}
