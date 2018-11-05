package com.taiji.pubsec.szpt.caseanalysis.score.bean;

public class MessageResultBean {
	
	private String caseCode;	//案件编号
	
	private String caseName;	//案件名称
	
	private String type;	//案件类别
	
	private String caseState;	//案件状态名称
	
	private String firstSort ;	//案件性质一级
	
	private String secondSort ;	//案件性质二级
	
	private String handlePolice;	//办案民警
	
	private long caseTimeStart;	//案发时间
	
	private long systemAutoPushTime;	//系统自动推送时间

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseState() {
		return caseState;
	}

	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}

	public String getFirstSort() {
		return firstSort;
	}

	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}

	public String getSecondSort() {
		return secondSort;
	}

	public void setSecondSort(String secondSort) {
		this.secondSort = secondSort;
	}

	public String getHandlePolice() {
		return handlePolice;
	}

	public void setHandlePolice(String handlePolice) {
		this.handlePolice = handlePolice;
	}

	public long getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(long caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public long getSystemAutoPushTime() {
		return systemAutoPushTime;
	}

	public void setSystemAutoPushTime(long systemAutoPushTime) {
		this.systemAutoPushTime = systemAutoPushTime;
	}
	
	
	
}
