package com.taiji.pubsec.szpt.caseanalysis.tag.action.bean;

/**
 * 案件基本信息Bean
 * 
 * @author WangLei
 *
 */
public class CriminalBasicCaseBean {

	private String caseCode;// 案件编号
	
	private String caseName;// 案件名称
	
	private String kno;// 现场勘验编码,由现勘系统回写
	
	private String caseTypeName;// 案件类型名称：行政/刑事
	
	private String caseSort;// 案件类别编码
	
	private String caseSortName;// 案件类别名称
	
	private String caseKind;// 案件性质编码
	
	private String caseKindName;// 案件性质名称
	
	private String caseStateName;// 案件状态名称
	
	private Long discoverTimeStart;// 发现时间起
	
	private Long discoverTimeEnd;// 发现时间止
	
	private Long caseTimeStart;// 发案时间起
	
	private Long caseTimeEnd;// 发案时间止
	
	private String communityCode;// 村区编码
	
	private String community;// 发案社区名称
	
	private String regionCode;// 辖区编码
	
	private String region;// 辖区名称
	
	private String district;// 发案地行政区划名称
	
	private String country;// 涉及国家地区名称
	
	private String caseAddress;// 发案地点
	
	private String details;// 简要案情
	
	private String caseKeyword;// 案情关键词
	
	private String lossValue;// 损失总价值
	
	private String injuredCount;// 受伤人数
	
	private String deadCount;// 死亡人数
	
	private String unionFlag;// 并案标识
	
	private String mianCaseId;// 并案主案件编号
	
	private String longitude;// 地点经度
	
	private String latitude;// 地点纬度
	
	private String inputPerson;// 录入人
	
	private Long inputTime;// 录入时间
	
	private String modifiedPerson;// 修改人
	
	private Long modifiedTime;// 修改时间
	
	private Long confirmDate;// 立案时间
	
	private String handlePolice;// 办案人
	
	private String handleUnit;// 办案单位
	
	private String caseTagId;// 案件打标
	
	private String scoreTemplateName;// 打分模版名称

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

	public String getKno() {
		return kno;
	}

	public void setKno(String kno) {
		this.kno = kno;
	}

	public String getCaseTypeName() {
		return caseTypeName;
	}

	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
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

	public String getCaseKind() {
		return caseKind;
	}

	public void setCaseKind(String caseKind) {
		this.caseKind = caseKind;
	}

	public String getCaseKindName() {
		return caseKindName;
	}

	public void setCaseKindName(String caseKindName) {
		this.caseKindName = caseKindName;
	}

	public String getCaseStateName() {
		return caseStateName;
	}

	public void setCaseStateName(String caseStateName) {
		this.caseStateName = caseStateName;
	}

	public Long getDiscoverTimeStart() {
		return discoverTimeStart;
	}

	public void setDiscoverTimeStart(Long discoverTimeStart) {
		this.discoverTimeStart = discoverTimeStart;
	}

	public Long getDiscoverTimeEnd() {
		return discoverTimeEnd;
	}

	public void setDiscoverTimeEnd(Long discoverTimeEnd) {
		this.discoverTimeEnd = discoverTimeEnd;
	}

	public Long getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(Long caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public Long getCaseTimeEnd() {
		return caseTimeEnd;
	}

	public void setCaseTimeEnd(Long caseTimeEnd) {
		this.caseTimeEnd = caseTimeEnd;
	}

	public String getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCaseAddress() {
		return caseAddress;
	}

	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCaseKeyword() {
		return caseKeyword;
	}

	public void setCaseKeyword(String caseKeyword) {
		this.caseKeyword = caseKeyword;
	}

	public String getUnionFlag() {
		return unionFlag;
	}

	public void setUnionFlag(String unionFlag) {
		this.unionFlag = unionFlag;
	}

	public String getMianCaseId() {
		return mianCaseId;
	}

	public void setMianCaseId(String mianCaseId) {
		this.mianCaseId = mianCaseId;
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

	public String getInputPerson() {
		return inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public Long getInputTime() {
		return inputTime;
	}

	public void setInputTime(Long inputTime) {
		this.inputTime = inputTime;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public Long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Long confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getHandlePolice() {
		return handlePolice;
	}

	public void setHandlePolice(String handlePolice) {
		this.handlePolice = handlePolice;
	}

	public String getHandleUnit() {
		return handleUnit;
	}

	public void setHandleUnit(String handleUnit) {
		this.handleUnit = handleUnit;
	}

	public String getCaseTagId() {
		return caseTagId;
	}

	public void setCaseTagId(String caseTagId) {
		this.caseTagId = caseTagId;
	}

	public String getLossValue() {
		return lossValue;
	}

	public void setLossValue(String lossValue) {
		this.lossValue = lossValue;
	}

	public String getInjuredCount() {
		return injuredCount;
	}

	public void setInjuredCount(String injuredCount) {
		this.injuredCount = injuredCount;
	}

	public String getDeadCount() {
		return deadCount;
	}

	public void setDeadCount(String deadCount) {
		this.deadCount = deadCount;
	}

	public String getScoreTemplateName() {
		return scoreTemplateName;
	}

	public void setScoreTemplateName(String scoreTemplateName) {
		this.scoreTemplateName = scoreTemplateName;
	}
	
	
}
