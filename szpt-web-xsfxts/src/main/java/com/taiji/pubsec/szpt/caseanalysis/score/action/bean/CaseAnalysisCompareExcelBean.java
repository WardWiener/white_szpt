package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

/**
 * 案件分析对比结果ExcelBean
 * 
 * @author WangLei
 *
 */
public class CaseAnalysisCompareExcelBean {

	private String no;// 序号
	
	private String type;// 主案件或者可能的串并案
	
	private String caseBasisInfo;// 案件基本信息
	
	private String minScore;// 串并案分值
	
	private String suspectName;// 嫌疑人名称
	
	private String address ;// 发案地点地址
	
	private String communityName ;// 发案村区名称
	
	private String featureNames;// 作案特点名称
	
	private String occurPlaceName ;// 发案处所名称
	
	private String periodName ;// 作案时段名称
	
	private String peopleNumName ;// 作案人数名称
	
	private String entranceName ;// 作案入口名称
	
	private String exitName ;// 作案出口名称

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseBasisInfo() {
		return caseBasisInfo;
	}

	public void setCaseBasisInfo(String caseBasisInfo) {
		this.caseBasisInfo = caseBasisInfo;
	}

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getSuspectName() {
		return suspectName;
	}

	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getFeatureNames() {
		return featureNames;
	}

	public void setFeatureNames(String featureNames) {
		this.featureNames = featureNames;
	}

	public String getOccurPlaceName() {
		return occurPlaceName;
	}

	public void setOccurPlaceName(String occurPlaceName) {
		this.occurPlaceName = occurPlaceName;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getPeopleNumName() {
		return peopleNumName;
	}

	public void setPeopleNumName(String peopleNumName) {
		this.peopleNumName = peopleNumName;
	}

	public String getEntranceName() {
		return entranceName;
	}

	public void setEntranceName(String entranceName) {
		this.entranceName = entranceName;
	}

	public String getExitName() {
		return exitName;
	}

	public void setExitName(String exitName) {
		this.exitName = exitName;
	}
	
}
