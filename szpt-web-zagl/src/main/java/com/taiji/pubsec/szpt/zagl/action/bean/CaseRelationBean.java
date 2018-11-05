package com.taiji.pubsec.szpt.zagl.action.bean;

public class CaseRelationBean {
	
	private String id;
	
	/**
	 * 子案件编码
	 */
	private String caseCode;
	
	/**
	 * 子案件名称
	 */
	private String caseName;
	

	/**
	 * 办案民警，多个用，隔开存储
	 */
	private String workers;


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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkers() {
		return workers;
	}

	public void setWorkers(String workers) {
		this.workers = workers;
	}

}
