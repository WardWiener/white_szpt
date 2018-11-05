package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class FlightInfo implements Serializable,ClueInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8588075170608533998L;
	/**
	 * 身份证号
	 */
	private String idCard ;
	/**
	 * 航班号
	 */
	private String flightNumber ;
	/**
	 * 起飞机场
	 */
	private String takeoffAirport ;
	/**
	 * 到达机场
	 */
	private String arriveatAirport ;
	/**
	 * 起飞时间
	 */
	private Long takeoffTime ;
	/**
	 * 到达时间
	 */
	private Long arrivalTime ;
	
	@Override
	public String sketch() {
		return "航班号：" + flightNumber;
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
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getTakeoffAirport() {
		return takeoffAirport;
	}
	public void setTakeoffAirport(String takeoffAirport) {
		this.takeoffAirport = takeoffAirport;
	}
	public String getArriveatAirport() {
		return arriveatAirport;
	}
	public void setArriveatAirport(String arriveatAirport) {
		this.arriveatAirport = arriveatAirport;
	}
	public Long getTakeoffTime() {
		return takeoffTime;
	}
	public void setTakeoffTime(Long takeoffTime) {
		this.takeoffTime = takeoffTime;
	}
	public Long getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
