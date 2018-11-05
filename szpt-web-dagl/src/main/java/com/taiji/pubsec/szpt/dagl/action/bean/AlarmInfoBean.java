package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 接处警信息Bean
 */
public class AlarmInfoBean{

	/**
	 * 报警电话
	 */
	private String rvcallNo;
	/**
	 * 报警人
	 */
	private String rvcallPerson;
	/**
	 * 发生地点
	 */
	private String happenPlace;
	/**
	 * 出动船支（次）
	 */
	private String boatNum;
	/**
	 * 出动机动车（次）
	 */
	private String voitureNum;
	/**
	 * 出动警力数
	 */
	private String policeNum;
	/**
	 * 出动协勤数
	 */
	private String policeAssNum;
	/**
	 * 处警单位
	 */
	private String disposeUnit;
	/**
	 * 处警简要情况(4000)
	 */
	private String disposeDetails;
	/**
	 * 处警结果
	 */
	private String disposeResult;
	/**
	 * 处警人1
	 */
	private String disposePerson;
	/**
	 * 处警人2
	 */
	private String disposePerson2;
	/**
	 * 处警意见
	 */
	private String disposeOpinion;
	/**
	 * 到达现场时间
	 */
	private String arriveTime;
	/**
	 * 接警单位
	 */
	private String receiveUnit;
	/**
	 * 接警内容(1500)
	 */
	private String reportDetails;
	/**
	 * 接警人
	 */
	private String receivePerson;
	/**
	 * 接警时间
	 */
	private String reportTime;
	/**
	 * 接警形式
	 */
	private String reportMode;
	/**
	 * 解救被拐卖儿童数
	 */
	private String rescueyNum;
	/**
	 * 解救被拐卖妇女数
	 */
	private String rescuewNum;
	/**
	 * 解救人质数
	 */
	private String rescurhNum;
	/**
	 * 警情类别
	 */
	private String alarmType;
	/**
	 * 救助群众
	 */
	private String salvationPeople;
	/**
	 * 救助伤员
	 */
	private String salvationWou;
	/**
	 * 留置审查人数
	 */
	private String censorNum;
	
	/**
	 * 是否现场处罚
	 */
	private String ifSpotfine;
	/**
	 * 是否现场调解
	 */
	private String ifSpotReconcile;
	
	/**
	 * 辖区
	 */
	private String popedom;

	public String getRvcallNo() {
		return rvcallNo;
	}

	public void setRvcallNo(String rvcallNo) {
		this.rvcallNo = rvcallNo;
	}

	public String getBoatNum() {
		return boatNum;
	}

	public void setBoatNum(String boatNum) {
		this.boatNum = boatNum;
	}

	public String getVoitureNum() {
		return voitureNum;
	}

	public void setVoitureNum(String voitureNum) {
		this.voitureNum = voitureNum;
	}

	public String getPoliceNum() {
		return policeNum;
	}

	public void setPoliceNum(String policeNum) {
		this.policeNum = policeNum;
	}

	public String getPoliceAssNum() {
		return policeAssNum;
	}

	public void setPoliceAssNum(String policeAssNum) {
		this.policeAssNum = policeAssNum;
	}

	public String getDisposeUnit() {
		return disposeUnit;
	}

	public void setDisposeUnit(String disposeUnit) {
		this.disposeUnit = disposeUnit;
	}

	public String getDisposeDetails() {
		return disposeDetails;
	}

	public void setDisposeDetails(String disposeDetails) {
		this.disposeDetails = disposeDetails;
	}

	public String getDisposePerson() {
		return disposePerson;
	}

	public void setDisposePerson(String disposePerson) {
		this.disposePerson = disposePerson;
	}

	public String getDisposePerson2() {
		return disposePerson2;
	}

	public void setDisposePerson2(String disposePerson2) {
		this.disposePerson2 = disposePerson2;
	}

	public String getDisposeOpinion() {
		return disposeOpinion;
	}

	public void setDisposeOpinion(String disposeOpinion) {
		this.disposeOpinion = disposeOpinion;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getReceiveUnit() {
		return receiveUnit;
	}

	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
	}

	public String getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}

	public String getRescueyNum() {
		return rescueyNum;
	}

	public void setRescueyNum(String rescueyNum) {
		this.rescueyNum = rescueyNum;
	}

	public String getRescuewNum() {
		return rescuewNum;
	}

	public void setRescuewNum(String rescuewNum) {
		this.rescuewNum = rescuewNum;
	}

	public String getRescurhNum() {
		return rescurhNum;
	}

	public void setRescurhNum(String rescurhNum) {
		this.rescurhNum = rescurhNum;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getSalvationPeople() {
		return salvationPeople;
	}

	public void setSalvationPeople(String salvationPeople) {
		this.salvationPeople = salvationPeople;
	}

	public String getSalvationWou() {
		return salvationWou;
	}

	public void setSalvationWou(String salvationWou) {
		this.salvationWou = salvationWou;
	}

	public String getCensorNum() {
		return censorNum;
	}

	public void setCensorNum(String censorNum) {
		this.censorNum = censorNum;
	}

	public String getIfSpotfine() {
		return ifSpotfine;
	}

	public void setIfSpotfine(String ifSpotfine) {
		this.ifSpotfine = ifSpotfine;
	}

	public String getIfSpotReconcile() {
		return ifSpotReconcile;
	}

	public void setIfSpotReconcile(String ifSpotReconcile) {
		this.ifSpotReconcile = ifSpotReconcile;
	}

	public String getPopedom() {
		return popedom;
	}

	public void setPopedom(String popedom) {
		this.popedom = popedom;
	}

	public String getDisposeResult() {
		return disposeResult;
	}

	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}

	public String getRvcallPerson() {
		return rvcallPerson;
	}

	public void setRvcallPerson(String rvcallPerson) {
		this.rvcallPerson = rvcallPerson;
	}

	public String getHappenPlace() {
		return happenPlace;
	}

	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
}