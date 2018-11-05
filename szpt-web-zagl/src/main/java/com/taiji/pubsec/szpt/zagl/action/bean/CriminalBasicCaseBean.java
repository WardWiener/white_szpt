package com.taiji.pubsec.szpt.zagl.action.bean;


public class CriminalBasicCaseBean {
	
	private String caseCode;  //案件id code
	
	private String caseName ;  //案件名称

	private String dqbldw;  //办理单位
	
	private String handlingPeople; //办案民警
	
	private  long   caseTimeStart;  //案发时间
	 
	private  String  caseSort;  //案件类别
	
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

	public String getDqbldw() {
		return dqbldw;
	}

	public void setDqbldw(String dqbldw) {
		this.dqbldw = dqbldw;
	}

	public long getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(long caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public String getCaseSort() {
		return caseSort;
	}

	public void setCaseSort(String caseSort) {
		this.caseSort = caseSort;
	}

	public String getHandlingPeople() {
		return handlingPeople;
	}

	public void setHandlingPeople(String handlingPeople) {
		this.handlingPeople = handlingPeople;
	}
	
}
