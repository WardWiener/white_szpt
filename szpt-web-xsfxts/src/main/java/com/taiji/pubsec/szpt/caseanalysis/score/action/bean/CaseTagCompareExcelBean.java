package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

/**
 * 案件打标对比ExcelBean
 * 
 * @author WangLei
 *
 */
public class CaseTagCompareExcelBean {

	private String no;// 序号
	
	private String caseBasicInfo;// 案件基本信息
	
	private String typeName ;// 案件类别名称
	
	private String firstSortName ;// 案件性质一级名称
	
	private String secondSortName ;// 案件性质二级名称
	
	private String suspectName;// 嫌疑人名称
	
	private String address ;// 发案地点地址
	
	private String communityName ;// 发案村区名称
	
	private String featureNames;// 作案特点名称
	
	private String occurPlaceName ;// 发案处所名称
	
	public String periodName ;// 作案时段名称
	
	private String peopleNumName ;// 作案人数名称
	
	private String entranceName ;// 作案入口名称
	
	private String exitName ;// 作案出口名称

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCaseBasicInfo() {
		return caseBasicInfo;
	}

	public void setCaseBasicInfo(String caseBasicInfo) {
		this.caseBasicInfo = caseBasicInfo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFirstSortName() {
		return firstSortName;
	}

	public void setFirstSortName(String firstSortName) {
		this.firstSortName = firstSortName;
	}

	public String getSecondSortName() {
		return secondSortName;
	}

	public void setSecondSortName(String secondSortName) {
		this.secondSortName = secondSortName;
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
