package com.taiji.pubsec.szpt.dagl.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件详情Bean
 */
public class CriminalBasicCaseDetailBean{

	/**
	 * 案件编号
	 */
	private String caseCode;
	
	/**
	 * 案件类别
	 */
	private String caseSort;
	/**
	 * 案件类型
	 */
	private String caseClass;
	/**
	 * 案件性质
	 */
	private String caseKind;
	/**
	 * 案件名称
	 */
	private String caseName;
	/**
	 * 案件文号
	 */
	private String caseWhid;
	/**
	 * 案件状态
	 */
	private String caseState;
	/**
	 * 案情关键词
	 */
	private String caseKeyword;
	/**
	 * 案由
	 */
	private String caseReason;
	/**
	 * 标注率
	 */
	private String markedRate;
	/**
	 * 并案标识
	 */
	private String unionFlag;
	/**
	 * 并案主案件编号
	 */
	private String mianCaseId;
	/**
	 * 地点经度
	 */
	private String longitude;
	/**
	 * 地点纬度
	 */
	private String latitude;
	/**
	 * 发案地点
	 */
	private String caseAddress;
	/**
	 * 发案地行政区划
	 */
	private String districtCode;
	/**
	 * 发案社区
	 */
	private String community;
	/**
	 * 发案时间起
	 */
	private String caseTimeStart;
	/**
	 * 发案时间止
	 */
	private String caseTimeEnd;
	/**
	 * 发现时间起
	 */
	private String discoverTimeStart;
	/**
	 * 发现时间止
	 */
	private String discoverTimeEnd;
	/**
	 * 简要案情
	 */
	private String details;
	/**
	 * 录入人
	 */
	private String inputPerson;
	/**
	 * 录入时间
	 */
	private String inputTime;
	/**
	 * 涉及国家地区
	 */
	private String countries;
	/**
	 * 是否劳教案件
	 */
	private String ifDetention;
	/**
	 * 是否涉外
	 */
	private String ifFremdness;
	/**
	 * 受伤人数
	 */
	private String injureNum;
	/**
	 * 死亡人数
	 */
	private String dieNum;
	/**
	 * 损失总价值
	 */
	private String lossValue;
	/**
	 * 修改人
	 */
	private String modifiedPerson;
	/**
	 * 修改时间
	 */
	private String modifiedTime;
	
	/**
	 * 业务分类
	 */
	private String businessType;
	
	/**
	 * 督办级别
	 */
	private String superIntendLevel;
	
	/**
	 * 保密级别
	 */
	private String secrecyLevel;
	
	/**
	 * 是否可以归档
	 */
	private String ifArchive;
	
	/**
	 * 案件带破单位
	 */
	private String inputUnit;
	
	/**
	 * 是否侵犯财产案
	 */
	private String isAgainst;
	
	/**
	 * 是否命案
	 */
	private String isMurder;
	
	/**
	 * 是否涉枪案
	 */
	private String isGun;
	
	/**
	 * 删除标识
	 */
	private String deleTag;
	
	/**
	 * 是否上报拐卖案件系统
	 */
	private String ifUpTraffick;
	
	/**
	 * 是否上报涉枪案件系统
	 */
	private String ifUpGun;
	
	/**
	 * 是否上报敲诈案件系统
	 */
	private String ifUpBlackMail;
	
	/**
	 * 是否上报命案系统
	 */
	private String ifUpMurder;
	
	/**
	 * 补录原因
	 */
	private String makUpReason;
	
	/**
	 * 补录单位行政区划
	 */
	private String makeUpCode;
	
	/**
	 * 是否涉密案件
	 */
	private String ifClassified;
	
	/**
	 * 当前办理单位
	 */
	private String dqbldw;
	
	/**
	 * 是否上传现勘系统
	 */
	private String is_xk_scxt;
	
	/**
	 * 是否上报禁毒
	 */
	private String isUpDurg;
	
	/**
	 * 涉案总价值经侦专用
	 */
	private String totalValue;
	
	/**
	 * 现勘编号,由现勘系统回写
	 */
	private String kno;
	/**
	 * 与案件关联的嫌疑人数量
	 */
	private int xyrNum;
	/**
	 * 与此案件相关的分析快照数量
	 */
	private int analSnopNum;
	/**
	 * 与此案件相关的涉案物品数量
	 */
	private int involvedObjectNum;
	
	
	/**
	 * 接处警信息
	 */
	private AlarmInfoBean alarmInfoObj;
	
	/**
	 * 涉案物品
	 */
	private List<CriminalObjectBean> criminalObjectLst = new ArrayList<CriminalObjectBean>();
	
	/**
	 * 嫌疑人信息
	 */
	private List<CaseSupectRelationBean> caseSupectRelationLst = new ArrayList<CaseSupectRelationBean>();
	
	/**
	 * 报案人 受害人信息
	 */
	private List<SufferCaseRelationBean> sufferCaseRelationLst = new ArrayList<SufferCaseRelationBean>();
	
	/**
	 * 流程信息
	 */
	private List<CaseExecutionProcessBean> caseExecutionProcessLst = new ArrayList<CaseExecutionProcessBean>();
	
	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseSort() {
		return caseSort;
	}

	public void setCaseSort(String caseSort) {
		this.caseSort = caseSort;
	}

	public String getCaseClass() {
		return caseClass;
	}

	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}

	public String getCaseKind() {
		return caseKind;
	}

	public void setCaseKind(String caseKind) {
		this.caseKind = caseKind;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseWhid() {
		return caseWhid;
	}

	public void setCaseWhid(String caseWhid) {
		this.caseWhid = caseWhid;
	}

	public String getCaseState() {
		return caseState;
	}

	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}

	public String getCaseKeyword() {
		return caseKeyword;
	}

	public void setCaseKeyword(String caseKeyword) {
		this.caseKeyword = caseKeyword;
	}

	public String getCaseReason() {
		return caseReason;
	}

	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}

	public String getMarkedRate() {
		return markedRate;
	}

	public void setMarkedRate(String markedRate) {
		this.markedRate = markedRate;
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

	public String getCaseAddress() {
		return caseAddress;
	}

	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(String caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public String getCaseTimeEnd() {
		return caseTimeEnd;
	}

	public void setCaseTimeEnd(String caseTimeEnd) {
		this.caseTimeEnd = caseTimeEnd;
	}

	public String getDiscoverTimeStart() {
		return discoverTimeStart;
	}

	public void setDiscoverTimeStart(String discoverTimeStart) {
		this.discoverTimeStart = discoverTimeStart;
	}

	public String getDiscoverTimeEnd() {
		return discoverTimeEnd;
	}

	public void setDiscoverTimeEnd(String discoverTimeEnd) {
		this.discoverTimeEnd = discoverTimeEnd;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getIfDetention() {
		return ifDetention;
	}

	public void setIfDetention(String ifDetention) {
		this.ifDetention = ifDetention;
	}

	public String getIfFremdness() {
		return ifFremdness;
	}

	public void setIfFremdness(String ifFremdness) {
		this.ifFremdness = ifFremdness;
	}

	public String getInjureNum() {
		return injureNum;
	}

	public void setInjureNum(String injureNum) {
		this.injureNum = injureNum;
	}

	public String getDieNum() {
		return dieNum;
	}

	public void setDieNum(String dieNum) {
		this.dieNum = dieNum;
	}

	public String getLossValue() {
		return lossValue;
	}

	public void setLossValue(String lossValue) {
		this.lossValue = lossValue;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getSuperIntendLevel() {
		return superIntendLevel;
	}

	public void setSuperIntendLevel(String superIntendLevel) {
		this.superIntendLevel = superIntendLevel;
	}

	public String getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSecrecyLevel(String secrecyLevel) {
		this.secrecyLevel = secrecyLevel;
	}

	public String getIfArchive() {
		return ifArchive;
	}

	public void setIfArchive(String ifArchive) {
		this.ifArchive = ifArchive;
	}

	public String getInputUnit() {
		return inputUnit;
	}

	public void setInputUnit(String inputUnit) {
		this.inputUnit = inputUnit;
	}

	public String getIsAgainst() {
		return isAgainst;
	}

	public void setIsAgainst(String isAgainst) {
		this.isAgainst = isAgainst;
	}

	public String getIsMurder() {
		return isMurder;
	}

	public void setIsMurder(String isMurder) {
		this.isMurder = isMurder;
	}

	public String getIsGun() {
		return isGun;
	}

	public void setIsGun(String isGun) {
		this.isGun = isGun;
	}

	public String getDeleTag() {
		return deleTag;
	}

	public void setDeleTag(String deleTag) {
		this.deleTag = deleTag;
	}

	public String getIfUpTraffick() {
		return ifUpTraffick;
	}

	public void setIfUpTraffick(String ifUpTraffick) {
		this.ifUpTraffick = ifUpTraffick;
	}

	public String getIfUpGun() {
		return ifUpGun;
	}

	public void setIfUpGun(String ifUpGun) {
		this.ifUpGun = ifUpGun;
	}

	public String getIfUpBlackMail() {
		return ifUpBlackMail;
	}

	public void setIfUpBlackMail(String ifUpBlackMail) {
		this.ifUpBlackMail = ifUpBlackMail;
	}

	public String getIfUpMurder() {
		return ifUpMurder;
	}

	public void setIfUpMurder(String ifUpMurder) {
		this.ifUpMurder = ifUpMurder;
	}

	public String getMakUpReason() {
		return makUpReason;
	}

	public void setMakUpReason(String makUpReason) {
		this.makUpReason = makUpReason;
	}

	public String getMakeUpCode() {
		return makeUpCode;
	}

	public void setMakeUpCode(String makeUpCode) {
		this.makeUpCode = makeUpCode;
	}

	public String getIfClassified() {
		return ifClassified;
	}

	public void setIfClassified(String ifClassified) {
		this.ifClassified = ifClassified;
	}

	public String getDqbldw() {
		return dqbldw;
	}

	public void setDqbldw(String dqbldw) {
		this.dqbldw = dqbldw;
	}

	public String getIs_xk_scxt() {
		return is_xk_scxt;
	}

	public void setIs_xk_scxt(String is_xk_scxt) {
		this.is_xk_scxt = is_xk_scxt;
	}

	public String getIsUpDurg() {
		return isUpDurg;
	}

	public void setIsUpDurg(String isUpDurg) {
		this.isUpDurg = isUpDurg;
	}

	public String getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

	public String getKno() {
		return kno;
	}

	public void setKno(String kno) {
		this.kno = kno;
	}

	public List<CriminalObjectBean> getCriminalObjectLst() {
		return criminalObjectLst;
	}

	public void setCriminalObjectLst(List<CriminalObjectBean> criminalObjectLst) {
		this.criminalObjectLst = criminalObjectLst;
	}


	public List<CaseSupectRelationBean> getCaseSupectRelationLst() {
		return caseSupectRelationLst;
	}

	public void setCaseSupectRelationLst(
			List<CaseSupectRelationBean> caseSupectRelationLst) {
		this.caseSupectRelationLst = caseSupectRelationLst;
	}

	public List<SufferCaseRelationBean> getSufferCaseRelationLst() {
		return sufferCaseRelationLst;
	}

	public void setSufferCaseRelationLst(
			List<SufferCaseRelationBean> sufferCaseRelationLst) {
		this.sufferCaseRelationLst = sufferCaseRelationLst;
	}

	public List<CaseExecutionProcessBean> getCaseExecutionProcessLst() {
		return caseExecutionProcessLst;
	}

	public void setCaseExecutionProcessLst(List<CaseExecutionProcessBean> caseExecutionProcessLst) {
		this.caseExecutionProcessLst = caseExecutionProcessLst;
	}

	public AlarmInfoBean getAlarmInfoObj() {
		return alarmInfoObj;
	}

	public void setAlarmInfoObj(AlarmInfoBean alarmInfoObj) {
		this.alarmInfoObj = alarmInfoObj;
	}

	public int getXyrNum() {
		return xyrNum;
	}

	public void setXyrNum(int xyrNum) {
		this.xyrNum = xyrNum;
	}

	public int getAnalSnopNum() {
		return analSnopNum;
	}

	public void setAnalSnopNum(int analSnopNum) {
		this.analSnopNum = analSnopNum;
	}

	public int getInvolvedObjectNum() {
		return involvedObjectNum;
	}

	public void setInvolvedObjectNum(int involvedObjectNum) {
		this.involvedObjectNum = involvedObjectNum;
	}

}