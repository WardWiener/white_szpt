package com.taiji.pubsec.szpt.caseanalysis.tag.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件打标Bean
 * 
 * @author WangLei
 *
 */
public class CaseTagBean {

	private String id;
	
	private String caseCode ;// 案件编号
	
	private String caseName ;// 案件名称
	
	private String level ;// 案件级别编码
	
	private String levelName ;// 案件级别名称
	
	private String type ;// 案件类别编码
	
	private String typeName ;// 案件类别名称
	
	private String firstSort ;// 案件性质一级编码
	
	private String firstSortName ;// 案件性质一级名称
	
	private String secondSort ;// 案件性质二级编码
	
	private String secondSortName ;// 案件性质二级名称
	
	private String longitude;// 发案地点经度
	
	private String latitude;// 发案地点维度
	
	private String address ;// 发案地点地址
	
	private String community ;// 发案村区编码
	
	private String communityName ;// 发案村区名称
	
	private String placeType;// 涉案场所类型编码
	
	private String placeTypeName;// 涉案场所类型名称
	
	private String placeName;// 涉案场所名称
	
	private String occurPlace ;// 发案处所编码
	
	private String occurPlaceName ;// 发案处所名称
	
	private String entrance ;// 作案入口编码
	
	private String entranceName ;// 作案入口名称
	
	private String exit ;// 作案出口编码
	
	private String exitName ;// 作案出口名称
	
	private String peopleNum ;// 作案人数编码
	
	private String peopleNumName ;// 作案人数名称
	
	private String period ;// 作案时段，字典项 一天分为8份
	
	private String periodName ;// 作案时段名称
	
	private List<String> featureCodes = new ArrayList<String>();// 作案特点
	
	private String featureNames;// 作案特点名称
	
	private String createPersonId;// 创建人id
	
	private Long createTime;// 创建时间
	
	private String updatePersonId;// 更新人id
	
	private Long updateTime;// 更新时间
	
	private Long caseTimeStart;// 发案时间起
	
	private Long caseTimeEnd;// 发案时间止

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFirstSort() {
		return firstSort;
	}

	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}

	public String getFirstSortName() {
		return firstSortName;
	}

	public void setFirstSortName(String firstSortName) {
		this.firstSortName = firstSortName;
	}

	public String getSecondSort() {
		return secondSort;
	}

	public void setSecondSort(String secondSort) {
		this.secondSort = secondSort;
	}

	public String getSecondSortName() {
		return secondSortName;
	}

	public void setSecondSortName(String secondSortName) {
		this.secondSortName = secondSortName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getOccurPlace() {
		return occurPlace;
	}

	public void setOccurPlace(String occurPlace) {
		this.occurPlace = occurPlace;
	}

	public String getOccurPlaceName() {
		return occurPlaceName;
	}

	public void setOccurPlaceName(String occurPlaceName) {
		this.occurPlaceName = occurPlaceName;
	}

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getEntranceName() {
		return entranceName;
	}

	public void setEntranceName(String entranceName) {
		this.entranceName = entranceName;
	}

	public String getExit() {
		return exit;
	}

	public void setExit(String exit) {
		this.exit = exit;
	}

	public String getExitName() {
		return exitName;
	}

	public void setExitName(String exitName) {
		this.exitName = exitName;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getPeopleNumName() {
		return peopleNumName;
	}

	public void setPeopleNumName(String peopleNumName) {
		this.peopleNumName = peopleNumName;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public List<String> getFeatureCodes() {
		return featureCodes;
	}

	public void setFeatureCodes(List<String> featureCodes) {
		this.featureCodes = featureCodes;
	}

	public String getFeatureNames() {
		return featureNames;
	}

	public void setFeatureNames(String featureNames) {
		this.featureNames = featureNames;
	}

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUpdatePersonId() {
		return updatePersonId;
	}

	public void setUpdatePersonId(String updatePersonId) {
		this.updatePersonId = updatePersonId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getPlaceTypeName() {
		return placeTypeName;
	}

	public void setPlaceTypeName(String placeTypeName) {
		this.placeTypeName = placeTypeName;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
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
	
	
}
