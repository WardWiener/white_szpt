package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;

/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:01
 */
public class TrainTicketBean {

	private String id;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 车次
	 */
	private String trainNumber;
	/**
	 * 出发站
	 */
	private String takeoffStation;
	/**
	 * 到达站
	 */
	private String arriveatStation;
	/**
	 * 发车时间
	 */
	private Date takeoffTime;
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

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTakeoffStation() {
		return takeoffStation;
	}

	public void setTakeoffStation(String takeoffStation) {
		this.takeoffStation = takeoffStation;
	}

	public String getArriveatStation() {
		return arriveatStation;
	}

	public void setArriveatStation(String arriveatStation) {
		this.arriveatStation = arriveatStation;
	}

	public Date getTakeoffTime() {
		return takeoffTime;
	}

	public void setTakeoffTime(Date takeoffTime) {
		this.takeoffTime = takeoffTime;
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