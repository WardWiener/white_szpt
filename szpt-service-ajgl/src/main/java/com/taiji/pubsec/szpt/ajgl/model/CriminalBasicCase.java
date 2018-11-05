package com.taiji.pubsec.szpt.ajgl.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.ajgl.service.ICaseExecutionProcessService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalSuspectService;

/**
 * BPIP_CASE案件基本信息主表
 * @author wangfx
 * @version 1.0
 * @created 10-8月-2016 15:01:55
 */
@Entity
@Table(name = "t_ajgl_ajjbxx")
public class CriminalBasicCase implements Serializable{

	@Id
	@Column(name="caseId")
	private String caseCode;
	
	/**
	 * 打标状态
	 */
	@Column(name="dbzt")
	private String dbstatus ;
	
	/**
	 * 研判状态
	 */
	@Column(name="ypzt")
	private String ypstatus ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 办案人1
	 */
	private String HandlingPeople1;
	
	/**
	 * 办案人2
	 */
	private String HandlingPeople2;
	
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
	@Column(length = 315)
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
	@Column(length = 1600)
	private String caseKeyword;
	/**
	 * 案由
	 */
	private String caseReason;
	/**
	 * 标注率
	 */
	@Column(nullable = true)
	private Integer markedRate;
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
	@Column(length = 4000)
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date caseTimeStart;
	/**
	 * 发案时间止
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date caseTimeEnd;
	/**
	 * 发现时间起
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date discoverTimeStart;
	/**
	 * 发现时间止
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date discoverTimeEnd;
	/**
	 * 简要案情
	 */
	@Column(length = 4000)
	private String details;
	/**
	 * 录入人
	 */
	private String inputPerson;
	/**
	 * 录入时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
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
	@Column(nullable = true)
	private Integer injureNum;
	/**
	 * 死亡人数
	 */
	@Column(nullable = true)
	private Integer dieNum;
	/**
	 * 损失总价值
	 */
	@Column(nullable = true)
	private Double lossValue;
	/**
	 * 修改人
	 */
	private String modifiedPerson;
	/**
	 * 修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;
	
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
	@Column(length = 4000)
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
	@Column(nullable = true)
	private Double totalValue;
	
	/**
	 * 现勘编号,由现勘系统回写
	 */
	private String kno;
	
	/**
	 * 案件状态编码
	 */
	private String caseStateCode;
	
	/**
	 * 案件类别编码
	 */
	private String caseSortCode;
	
	/**
	 * 案件性质编码
	 */
	private String caseKindCode;
	
	/**
	 * 发案社区编码
	 */
	private String communityCode;
	
	/**
	 * 当前办理单位编码
	 */
	private String dqbldwCode;
	
	/**
	 * 时间戳
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updateTime;
	
	@Transient
	private AlarmInfo alarmInfo;
	
	@OneToMany(mappedBy = "criminalBasicCase")
	private Set<ArchivedFile> archivedFiles = new HashSet<ArchivedFile>();	//卷宗文书
	
	@Transient
	@OneToOne(mappedBy = "basicCase")
	private CaseAttachedInfo caseAttachedInfo;	//案件附加信息
	
//	@OneToMany(mappedBy = "basicCase")
//	private List<CaseSupectRelation> caseSupectRelations = new ArrayList<CaseSupectRelation>();//案件嫌疑人关系
	
	@OneToMany(mappedBy = "criminalBasicCase")
	private Set<CriminalObject> criminalObjects = new HashSet<CriminalObject>();
	
	public String getCaseSort() {
		return caseSort;
	}

	public String getCaseClass() {
		return caseClass;
	}

	public String getCaseName() {
		return caseName;
	}

	public String getCaseWhid() {
		return caseWhid;
	}

	public String getCaseState() {
		return caseState;
	}

	public String getCaseKeyword() {
		return caseKeyword;
	}

	public String getCaseReason() {
		return caseReason;
	}

	public Integer getMarkedRate() {
		return markedRate;
	}

	public String getUnionFlag() {
		return unionFlag;
	}

	public String getMianCaseId() {
		return mianCaseId;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getCaseAddress() {
		return caseAddress;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public String getCommunity() {
		return community;
	}

	public Date getCaseTimeStart() {
		return caseTimeStart;
	}

	public Date getCaseTimeEnd() {
		return caseTimeEnd;
	}

	public Date getDiscoverTimeStart() {
		return discoverTimeStart;
	}

	public Date getDiscoverTimeEnd() {
		return discoverTimeEnd;
	}

	public String getDetails() {
		return details;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public String getCountries() {
		return countries;
	}

	public String getIfDetention() {
		return ifDetention;
	}

	public String getIfFremdness() {
		return ifFremdness;
	}

	public Integer getInjureNum() {
		return injureNum;
	}

	public Integer getDieNum() {
		return dieNum;
	}

	public Double getLossValue() {
		return lossValue;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setCaseSort(String caseSort) {
		this.caseSort = caseSort;
	}

	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setCaseWhid(String caseWhid) {
		this.caseWhid = caseWhid;
	}

	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}

	public void setCaseKeyword(String caseKeyword) {
		this.caseKeyword = caseKeyword;
	}

	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}

	public void setMarkedRate(Integer markedRate) {
		this.markedRate = markedRate;
	}

	public void setUnionFlag(String unionFlag) {
		this.unionFlag = unionFlag;
	}

	public void setMianCaseId(String mianCaseId) {
		this.mianCaseId = mianCaseId;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public void setCaseTimeStart(Date caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public void setCaseTimeEnd(Date caseTimeEnd) {
		this.caseTimeEnd = caseTimeEnd;
	}

	public void setDiscoverTimeStart(Date discoverTimeStart) {
		this.discoverTimeStart = discoverTimeStart;
	}

	public void setDiscoverTimeEnd(Date discoverTimeEnd) {
		this.discoverTimeEnd = discoverTimeEnd;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public void setIfDetention(String ifDetention) {
		this.ifDetention = ifDetention;
	}

	public void setIfFremdness(String ifFremdness) {
		this.ifFremdness = ifFremdness;
	}

	public void setInjureNum(Integer injureNum) {
		this.injureNum = injureNum;
	}

	public void setDieNum(Integer dieNum) {
		this.dieNum = dieNum;
	}

	public void setLossValue(Double lossValue) {
		this.lossValue = lossValue;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCaseKind() {
		return caseKind;
	}

	public void setCaseKind(String caseKind) {
		this.caseKind = caseKind;
	}

	public String getSuperIntendLevel() {
		return superIntendLevel;
	}

	public String getSecrecyLevel() {
		return secrecyLevel;
	}

	public void setSuperIntendLevel(String superIntendLevel) {
		this.superIntendLevel = superIntendLevel;
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

	public String getIfUpGun() {
		return ifUpGun;
	}

	public String getIfUpBlackMail() {
		return ifUpBlackMail;
	}

	public String getIfUpMurder() {
		return ifUpMurder;
	}

	public String getMakUpReason() {
		return makUpReason;
	}

	public String getMakeUpCode() {
		return makeUpCode;
	}

	public void setIfUpTraffick(String ifUpTraffick) {
		this.ifUpTraffick = ifUpTraffick;
	}

	public void setIfUpGun(String ifUpGun) {
		this.ifUpGun = ifUpGun;
	}

	public void setIfUpBlackMail(String ifUpBlackMail) {
		this.ifUpBlackMail = ifUpBlackMail;
	}

	public void setIfUpMurder(String ifUpMurder) {
		this.ifUpMurder = ifUpMurder;
	}

	public void setMakUpReason(String makUpReason) {
		this.makUpReason = makUpReason;
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

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getKno() {
		return kno;
	}

	public void setKno(String kno) {
		this.kno = kno;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getDbstatus() {
		return dbstatus;
	}

	public void setDbstatus(String dbstatus) {
		this.dbstatus = dbstatus;
	}

	public String getYpstatus() {
		return ypstatus;
	}

	public void setYpstatus(String ypstatus) {
		this.ypstatus = ypstatus;
	}
	
//	public void setCaseSupectRelations(List<CaseSupectRelation> caseSupectRelations) {
//		this.caseSupectRelations = caseSupectRelations;
//	}
//
//	public List<CaseSupectRelation> getCaseSupectRelations() {
//		return caseSupectRelations;
//	}

	public Set<ArchivedFile> getArchivedFiles() {
		return archivedFiles;
	}

	public void setArchivedFiles(Set<ArchivedFile> archivedFiles) {
		this.archivedFiles = archivedFiles;
	}

	public Set<CriminalObject> getCriminalObjects() {
		return criminalObjects;
	}

	public void setCriminalObjects(Set<CriminalObject> criminalObjects) {
		this.criminalObjects = criminalObjects;
	}

	public String getCaseStateCode() {
		return caseStateCode;
	}

	public void setCaseStateCode(String caseStateCode) {
		this.caseStateCode = caseStateCode;
	}

	public String getCaseSortCode() {
		return caseSortCode;
	}

	public void setCaseSortCode(String caseSortCode) {
		this.caseSortCode = caseSortCode;
	}

	public String getCaseKindCode() {
		return caseKindCode;
	}

	public void setCaseKindCode(String caseKindCode) {
		this.caseKindCode = caseKindCode;
	}

	public String getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}

	public String getDqbldwCode() {
		return dqbldwCode;
	}

	public void setDqbldwCode(String dqbldwCode) {
		this.dqbldwCode = dqbldwCode;
	}

	public AlarmInfo getAlarmInfo() {
		ICriminalCaseService criminalCaseService = (ICriminalCaseService) SpringContextUtil.getBean("criminalCaseService");
		AlarmInfo alarmInfo = criminalCaseService.findAlarmInfoById(caseCode);
		return alarmInfo;
	}
	
	public String getHandlingPeople1() {
		return HandlingPeople1;
	}

	public void setHandlingPeople1(String handlingPeople1) {
		HandlingPeople1 = handlingPeople1;
	}

	public String getHandlingPeople2() {
		return HandlingPeople2;
	}

	public void setHandlingPeople2(String handlingPeople2) {
		HandlingPeople2 = handlingPeople2;
	}

	public CaseAttachedInfo getCaseAttachedInfo() {
		ICriminalCaseService criminalCaseService = (ICriminalCaseService) SpringContextUtil.getBean("criminalCaseService");
		CaseAttachedInfo caseAttachedInfo = criminalCaseService.findCaseAttachedInfoById(caseCode);
		return caseAttachedInfo;
	}
	
	public void setAlarmInfo() {
		ICriminalCaseService criminalCaseService = (ICriminalCaseService) SpringContextUtil.getBean("criminalCaseService");
		 this.alarmInfo = criminalCaseService.findAlarmInfoById(caseCode);
	}
	
	public List<CaseExecutionProcess> getCaseExecutionProcesses() {
		ICaseExecutionProcessService caseExecutionProcessService = (ICaseExecutionProcessService) SpringContextUtil.getBean("caseExecutionProcessService");
		List<CaseExecutionProcess> caseExecutionProcesses = caseExecutionProcessService.findCaseExecutionProcessByCase(caseCode);
		return caseExecutionProcesses;
	}
	
	public List<CaseSupectRelation> getCaseSupectRelations(){
		ICriminalSuspectService criminalSuspectService = (ICriminalSuspectService) SpringContextUtil.getBean("criminalSuspectService");
		List<CaseSupectRelation> caseSupectRelations = criminalSuspectService.findCaseSuspectRelationByCase(caseCode);
		return caseSupectRelations;
	}
	
	public List<SufferCaseRelation> getSufferCaseRelations(){
		ICriminalSuspectService criminalSuspectService = (ICriminalSuspectService) SpringContextUtil.getBean("criminalSuspectService");
		List<SufferCaseRelation> sufferCaseRelations = criminalSuspectService.findSufferCaseRelationsByCase(caseCode);
		return sufferCaseRelations;
	}
}