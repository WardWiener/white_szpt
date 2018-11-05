package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;

/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:00
 */
public class FlightBean {

	private String id;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 航班号
	 */
	private String flightNumber;
	/**
	 * 起飞机场
	 */
	private String takeoffAirport;
	/**
	 * 到达机场
	 */
	private String arriveatAirport;
	/**
	 * 起飞时间
	 */
	private Date takeoffTime;
	/**
	 * 到达时间
	 */
	private Date arriveatTime;
	/**
	 * 高危人人员类型编码
	 */
	private String personTypeCode;
	/**
	 * 高危人犯罪前科编码
	 */
	private String criminalTypeCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
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

	public Date getTakeoffTime() {
		return takeoffTime;
	}

	public void setTakeoffTime(Date takeoffTime) {
		this.takeoffTime = takeoffTime;
	}

	public Date getArriveatTime() {
		return arriveatTime;
	}

	public void setArriveatTime(Date arriveatTime) {
		this.arriveatTime = arriveatTime;
	}

	public String getPersonTypeCode() {
		return personTypeCode;
	}

	public void setPersonTypeCode(String personTypeCode) {
		this.personTypeCode = personTypeCode;
	}

	public String getCriminalTypeCode() {
		return criminalTypeCode;
	}

	public void setCriminalTypeCode(String criminalTypeCode) {
		this.criminalTypeCode = criminalTypeCode;
	}

}