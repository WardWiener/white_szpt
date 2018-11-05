package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class TrainInfo implements Serializable,ClueInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3125901280195251677L;
	
	/**
	 * 身份证号
	 */
	private String idCard ;
	/**
	 * 车次
	 */
	private String trainNumber;
	/**
	 * 出发车站
	 */
	private String startStation;
	/**
	 * 目的车站
	 */
	private String arrivalStation;
	/**
	 * 发车时间
	 */
	private Long startTime;
	
	@Override
	public String sketch() {
		return "火车车次：" + trainNumber ;
	}
	@Override
	public String detailDescription() {
		JSONObject obj = JSONObject.fromObject(this) ;
		return obj.toString();
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getArrivalStation() {
		return arrivalStation;
	}
	public void setArrivalStation(String arrivalStation) {
		this.arrivalStation = arrivalStation;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}


}
