package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

/**
 * 可能的串并案ExcelBean
 * 
 * @author WangLei
 *
 */
public class PossibleAlikeCaseExcelBean {

	private String no;// 序号
	
	private String minScore;// 分值
	
	private String scoreTemplateName;// 模版名称
	
	private String caseCode ;// 案件编号
	
	private String caseName ;// 案件名称
	
	private String caseTimeStart;// 发案时间起
	
	private String typeName ;// 案件类别名称
	
	private String firstSortName ;// 案件性质一级名称
	
	private String secondSortName ;// 案件性质二级名称
	
	private String featureNames;// 作案特点名称
	
	private String occurPlaceName ;// 发案处所名称
	
	private String periodName ;// 作案时段名称
	
	private String peopleNumName ;// 作案人数名称
	
	private String entranceName ;// 作案入口名称
	
	private String exitName ;// 作案出口名称
	
	private String communityName ;// 发案村区名称
	
	private String isIgnored;// 是否忽略

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getScoreTemplateName() {
		return scoreTemplateName;
	}

	public void setScoreTemplateName(String scoreTemplateName) {
		this.scoreTemplateName = scoreTemplateName;
	}

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

	public String getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(String caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
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

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getIsIgnored() {
		return isIgnored;
	}

	public void setIsIgnored(String isIgnored) {
		this.isIgnored = isIgnored;
	}
	
}
