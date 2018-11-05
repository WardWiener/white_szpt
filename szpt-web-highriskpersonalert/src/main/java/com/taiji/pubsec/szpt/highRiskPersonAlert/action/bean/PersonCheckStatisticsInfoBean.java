package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

public class PersonCheckStatisticsInfoBean {

	private String checkUnitCode; // 核查单位

	private String checkUnitName; // 核查单位

	private int orange; // 橙色人员

	private int white; // 白色人员

	private int criminalRecord; // 刑事前科

	private int drugRelatedPerson; // 涉毒人员

	private int sum; // 核查人数

	public PersonCheckStatisticsInfoBean(String checkUnitCode,
			String checkUnitName) {
		this.checkUnitCode = checkUnitCode;
		this.checkUnitName = checkUnitName;
		sum = 0;
		orange = 0;
		white = 0;
		criminalRecord = 0;
		drugRelatedPerson = 0;
	}

	public void sumIncrement() {
		sum++;
	}

	public void orangeIncrement() {
		orange++;
	}

	public void whiteIncrement() {
		white++;
	}

	public void criminalRecordIncrement() {
		criminalRecord++;
	}

	public void drugRelatedPersonIncrement() {
		drugRelatedPerson++;
	}

	public String getCheckUnitCode() {
		return checkUnitCode;
	}

	public void setCheckUnitCode(String checkUnitCode) {
		this.checkUnitCode = checkUnitCode;
	}

	public int getOrange() {
		return orange;
	}

	public void setOrange(int orange) {
		this.orange = orange;
	}

	public int getWhite() {
		return white;
	}

	public void setWhite(int white) {
		this.white = white;
	}

	public int getCriminalRecord() {
		return criminalRecord;
	}

	public void setCriminalRecord(int criminalRecord) {
		this.criminalRecord = criminalRecord;
	}

	public int getDrugRelatedPerson() {
		return drugRelatedPerson;
	}

	public void setDrugRelatedPerson(int drugRelatedPerson) {
		this.drugRelatedPerson = drugRelatedPerson;
	}

	public String getCheckUnitName() {
		return checkUnitName;
	}

	public void setCheckUnitName(String checkUnitName) {
		this.checkUnitName = checkUnitName;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
