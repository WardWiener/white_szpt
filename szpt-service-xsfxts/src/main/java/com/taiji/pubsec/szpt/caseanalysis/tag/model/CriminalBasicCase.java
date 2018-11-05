package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 案件基本信息主表
 * 
 * @author wangfx
 * 
 */
@Entity
@Table(name="t_xsajfx_ajjbxx")
public class CriminalBasicCase {

	/**
	 * 案件编号
	 */
	@Id
	@Column(name="ajbh", length = 50)
	private String caseCode;
	
	/**
	 * 案件名称
	 */
	@Column(name="ajmc", length = 200)
	private String caseName;
	
	/**
	 * 现场勘验编码,由现勘系统回写
	 */
	@Column(name="xckybh", length = 50)
	private String kno;
	
	/**
	 * 案件类型名称：行政/刑事
	 */
	@Column(name="ajlxmc", length = 50)
	private String caseTypeName;
	
	/**
	 * 案件类别编码
	 */
	@Column(name="ajlbbm", length = 50)
	private String caseSort;
	
	/**
	 * 案件类别名称
	 */
	@Column(name="ajlbmc", length = 100)
	private String caseSortName;
	
	/**
	 * 案件性质编码
	 */
	@Column(name="ajxzbm", length = 50)
	private String caseKind;
	
	/**
	 * 案件性质名称
	 */
	@Column(name="ajxzmc", length = 100)
	private String caseKindName;
	
	/**
	 * 案件状态名称
	 */
	@Column(name="ajztmc", length = 50)
	private String caseStateName;
	
	/**
	 * 发现时间起
	 */
	@Column(name = "fxsjqs")
	@Temporal(TemporalType.TIMESTAMP)
	private Date discoverTimeStart;
	
	/**
	 * 发现时间止
	 */
	@Column(name = "fxsjjs")
	@Temporal(TemporalType.TIMESTAMP)
	private Date discoverTimeEnd;
	
	/**
	 * 发案时间起
	 */
	@Column(name = "afsjqs")
	@Temporal(TemporalType.TIMESTAMP)
	private Date caseTimeStart;
	
	/**
	 * 发案时间止
	 */
	@Column(name = "afsjjs")
	@Temporal(TemporalType.TIMESTAMP)
	private Date caseTimeEnd;
	
	/**
	 * 村区编码
	 */
	@Column(name="cqbm", length = 50)
	private String communityCode;
	
	/**
	 * 发案社区名称
	 */
	@Column(name="cqmc", length = 50)
	private String community;
	
	/**
	 * 辖区编码
	 */
	@Column(name="xqbm", length = 50)
	private String regionCode;
	
	/**
	 * 辖区名称
	 */
	@Column(name="xqmc", length = 50)
	private String region;
	
	/**
	 * 发案地行政区划名称
	 */
	@Column(name="xzqhmc", length = 50)
	private String district;
	
	/**
	 * 涉及国家地区名称
	 */
	@Column(name="gj", length = 50)
	private String country;
	
	/**
	 * 发案地点
	 */
	@Column(name="fadd", length = 250)
	private String caseAddress;
	
	/**
	 * 简要案情
	 */
	@Column(name="jyaq", length = 4000)
	private String details;
	
	/**
	 * 案情关键词
	 */
	@Column(name="gjz", length = 1600)
	private String caseKeyword;
	
	/**
	 * 损失总价值
	 */
	@Column(name="ssjz")
	private Double lossValue;
	
	/**
	 * 受伤人数
	 */
	@Column(name="ssrs")
	private Integer injuredCount;
	
	/**
	 * 死亡人数
	 */
	@Column(name="swrs")
	private Integer deadCount;
	
	/**
	 * 并案标识
	 */
	@Column(name="babs", length = 1)
	private String unionFlag;
	
	/**
	 * 并案主案件编号
	 */
	@Column(name="bazajbh", length = 50)
	private String mianCaseId;
	
	/**
	 * 地点经度
	 */
	@Column(name="jd", length = 50)
	private String longitude;
	
	/**
	 * 地点纬度
	 */
	@Column(name="wd", length = 50)
	private String latitude;
	
	/**
	 * 录入人
	 */
	@Column(name="lrr", length = 50)
	private String inputPerson;
	
	/**
	 * 录入时间
	 */
	@Column(name = "lrsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
	
	/**
	 * 修改人
	 */
	@Column(name="xgr", length = 50)
	private String modifiedPerson;
	
	/**
	 * 修改时间
	 */
	@Column(name = "xgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;
	
	/**
	 * 立案时间
	 */
	@Column(name = "lasj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmDate;
	
	/**
	 * 办案人
	 */
	@Column(name="bar", length = 50)
	private String handlePolice;
	
	/**
	 * 办案人1警号
	 */
	@Column(name = "bar1jh")
	private String HandlingPeople1Num;
	
	/**
	 * 办案人2警号
	 */
	@Column(name = "bar2jh")
	private String HandlingPeople2Num;
	
	/**
	 * 办案单位
	 */
	@Column(name="badw", length = 50)
	private String handleUnit;
	
	/**
	 * 案件打标
	 */
	@OneToOne(mappedBy="basicCase", fetch=FetchType.EAGER)
	private CaseTag caseTag;
	
	/**
	 * 是否破案
	 */
	@Column(name="sfpa")
	private String ifSolved;

	public String getIfSolved() {
		return ifSolved;
	}

	public void setIfSolved(String ifSolved) {
		this.ifSolved = ifSolved;
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

	public Date getDiscoverTimeStart() {
		return discoverTimeStart;
	}

	public void setDiscoverTimeStart(Date discoverTimeStart) {
		this.discoverTimeStart = discoverTimeStart;
	}

	public Date getDiscoverTimeEnd() {
		return discoverTimeEnd;
	}

	public void setDiscoverTimeEnd(Date discoverTimeEnd) {
		this.discoverTimeEnd = discoverTimeEnd;
	}

	public Date getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(Date caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public Date getCaseTimeEnd() {
		return caseTimeEnd;
	}

	public void setCaseTimeEnd(Date caseTimeEnd) {
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

	public Double getLossValue() {
		return lossValue;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public Integer getInjuredCount() {
		return injuredCount;
	}

	public void setInjuredCount(Integer injuredCount) {
		this.injuredCount = injuredCount;
	}

	public Integer getDeadCount() {
		return deadCount;
	}

	public void setDeadCount(Integer deadCount) {
		this.deadCount = deadCount;
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

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
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

	public CaseTag getCaseTag() {
		return caseTag;
	}

	public void setCaseTag(CaseTag caseTag) {
		this.caseTag = caseTag;
	}

	public String getHandlingPeople1Num() {
		return HandlingPeople1Num;
	}

	public void setHandlingPeople1Num(String handlingPeople1Num) {
		HandlingPeople1Num = handlingPeople1Num;
	}

	public String getHandlingPeople2Num() {
		return HandlingPeople2Num;
	}

	public void setHandlingPeople2Num(String handlingPeople2Num) {
		HandlingPeople2Num = handlingPeople2Num;
	}
	
	
}