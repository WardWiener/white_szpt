package com.taiji.pubsec.szpt.dagl.bean;

public class YaydBean {
	
	public static String CASENAME="title" ;
	public static String CASECODE="no" ;
	public static String CASESTATTIME="time";
	public static String CASEADDRESS="address" ;
	public static String CASESORT="type" ;
	public static String CASESORTNAME="typeName" ;
	public static String PROCESSNAME="prcessName" ; 
	public static String LEAD="qingbaoxiansuo" ; 
	public static String FXSNAPSHOOT="fenxikuaizhao" ; 
	public static String SUSPECT="xianyiren" ; 
	public static String EVIDENCE="wuzheng" ; 

	private String id;
	private String caseName ;
	private String caseCode ;
	private Long caseTimeStartTime ;
	private Long caseTimeEndTime ;
	private String caseAddress ;
	private String caseSort ;
	private String caseSortName ;
	private String processName ;
	
	/**
	 * 线索
	 */
	private int lead ;
	/**
	 * 分析快照
	 */
	private int fxSnapshoot ;
	/**
	 * 嫌疑人
	 */
	private int suspect ;
	/**
	 * 物证
	 */
	private int evidence ;
	
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public Long getCaseTimeStartTime() {
		return caseTimeStartTime;
	}
	public void setCaseTimeStartTime(Long caseTimeStartTime) {
		this.caseTimeStartTime = caseTimeStartTime;
	}
	public Long getCaseTimeEndTime() {
		return caseTimeEndTime;
	}
	public void setCaseTimeEndTime(Long caseTimeEndTime) {
		this.caseTimeEndTime = caseTimeEndTime;
	}
	public String getCaseAddress() {
		return caseAddress;
	}
	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}
	public String getCaseSort() {
		return caseSort;
	}
	public void setCaseSort(String caseSort) {
		this.caseSort = caseSort;
	}
	public String getCaseSortName() {
		return caseSortName;
	}
	public void setCaseSortName(String caseSortName) {
		this.caseSortName = caseSortName;
	}
	public int getLead() {
		return lead;
	}
	public void setLead(int lead) {
		this.lead = lead;
	}
	public int getFxSnapshoot() {
		return fxSnapshoot;
	}
	public void setFxSnapshoot(int fxSnapshoot) {
		this.fxSnapshoot = fxSnapshoot;
	}
	public int getSuspect() {
		return suspect;
	}
	public void setSuspect(int suspect) {
		this.suspect = suspect;
	}
	public int getEvidence() {
		return evidence;
	}
	public void setEvidence(int evidence) {
		this.evidence = evidence;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
