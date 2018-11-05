package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import javax.persistence.Column;

public class CriminalBasicCaseBean {

	private String caseCode;
	
	/**
	 * 案件类别
	 */
	private String caseSort;
	/**
	 * 案件类型
	 */
	private String caseClass;
	/**
	 * 案件性质
	 */
	private String caseKind;
	/**
	 * 案件名称
	 */
	@Column(length = 315)
	private String caseName;
	/**
	 * 地点经度
	 */
	private String longitude;
	/**
	 * 地点纬度
	 */
	@Column(length = 4000)
	private String latitude;
	/**
	 * 发案地点
	 */
	private String caseAddress;
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getCaseSort() {
		return caseSort;
	}
	public void setCaseSort(String caseSort) {
		this.caseSort = caseSort;
	}
	public String getCaseClass() {
		return caseClass;
	}
	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}
	public String getCaseKind() {
		return caseKind;
	}
	public void setCaseKind(String caseKind) {
		this.caseKind = caseKind;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getCaseAddress() {
		return caseAddress;
	}
	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}
}