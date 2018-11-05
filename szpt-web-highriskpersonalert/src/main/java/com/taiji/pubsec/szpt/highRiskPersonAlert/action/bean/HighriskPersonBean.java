package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.List;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;

/**
 * 高危人员 t_gwrt
 */
public class HighriskPersonBean {

	private String id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别，字典项
	 */
	private String sex;
	
	private String sexName;
	/**
	 * 身份证号
	 */
	private String idcode;
	/**
	 * 户籍地址
	 */
	private String registerAddress;
	/**
	 * 居住地址
	 */
	private String liveAddress;
	/**
	 * 工作地址
	 */
	private String workAddress;
	/**
	 * 职业
	 */
	private String profession;
	
	private String professionName;
	/**
	 * 收入
	 */
	private String income;
	/**
	 * 预警类型，字典项
	 */
	private String warnType;
	
	private String warnTypeName;
	/**
	 *  数据来源
	 */
	private String dataSource;
	/**
	 * 犯罪前科，字典项
	 */
	private String criminalRecord;
	
	private String criminalRecordName;
	/**
	 * 日常状况，0正常，1异常
	 */
	private String status;
	
	private String statusName;
	/**
	 * 尿检结果
	 */
	private String urineTest;
	/**
	 * 处置情况
	 */
	private String handleResult;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 采集时间
	 */
	private Long createdTime;
	/**
	 * 反馈时间
	 */
	private Long feedbackTime;
	/**
	 * 0表示新，1表示旧
	 */
	private Integer dataNew;
	/**
	 * 外号
	 */
	private String nickname;
	/**
	 * 积分
	 */
	private Double accumulatePoints;
	/**
	 * 采集人单位
	 */
	private String creatorDepartmentId;
	
	/**
	 * 出生日期
	 */
	private Long birthday;
	
	/**
	 * 国籍
	 */
	private String nationality;
	
	/**
	 * 户籍地派出所
	 */
	private String registerAddressPoliceStation;
	
	/**
	 * 户籍地详址
	 */
	private String registerAddressDetail;
	
	/**
	 * 籍贯
	 */
	private String origo;
	
	/**
	 * 民族
	 */
	private String ethnicGroup;
	
	/**
	 * 现住地详址
	 */
	private String localAddressDetail;
	
	/**
	 * 重点人员在控类型
	 */
	private String personInControlType;
	
	private String personInControlTypeName;
	
	/**
	 * 在控登记时间
	 */
	private Long inControlaRegisterTime;
	
	/**
	 * 婚姻情况
	 */
	private String marriageStatus;
	
	private String marriageStatusName;
	
	/**
	 * 就业情况
	 */
	private String employmentStatus;
	
	private String employmentStatusName;
	
	private String operateStatus;
    
    private String operateStatusName;
    
    private String usedName;

    private String education;
    
    private String educationName;
    //新增原因
    private String addReason;
    
	private String peopleType;
	
	private String peopleTypeName;
	
	private List<String> mac;
	
	private List<String> phone;
	
	private String approvePeople;
    
    private String approveContent;
    
    private Long approveTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcode() {
		return idcode;
	}
	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getWarnType() {
		return warnType;
	}
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
	public String getCriminalRecord() {
		return criminalRecord;
	}
	public void setCriminalRecord(String criminalRecord) {
		this.criminalRecord = criminalRecord;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrineTest() {
		return urineTest;
	}
	public void setUrineTest(String urineTest) {
		this.urineTest = urineTest;
	}
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public Long getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Double getAccumulatePoints() {
		return accumulatePoints;
	}
	public void setAccumulatePoints(Double accumulatePoints) {
		this.accumulatePoints = accumulatePoints;
	}
	public String getCreatorDepartmentId() {
		return creatorDepartmentId;
	}
	public void setCreatorDepartmentId(String creatorDepartmentId) {
		this.creatorDepartmentId = creatorDepartmentId;
	}
//	public List<Attachment> getAttachs() {
//		IAttachmentCustomizedService attachmentCustomizedService = (IAttachmentCustomizedService) SpringContextUtil.getBean("attachmentCustomizedService");
//		return attachmentCustomizedService.findByTargetIdAndType(id, HighriskPersonBean.class.getName());
//	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getWarnTypeName() {
		return warnTypeName;
	}
	public void setWarnTypeName(String warnTypeName) {
		this.warnTypeName = warnTypeName;
	}
	public String getCriminalRecordName() {
		return criminalRecordName;
	}
	public void setCriminalRecordName(String criminalRecordName) {
		this.criminalRecordName = criminalRecordName;
	}
	public String getPeopleTypeName() {
		return peopleTypeName;
	}
	public void setPeopleTypeName(String peopleTypeName) {
		this.peopleTypeName = peopleTypeName;
	}
	public String getPeopleType() {
		return peopleType;
	}
	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public List<String> getMac() {
		return mac;
	}
	public void setMac(List<String> mac) {
		this.mac = mac;
	}
	public List<String> getPhone() {
		return phone;
	}
	public void setPhone(List<String> phone) {
		this.phone = phone;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getRegisterAddressPoliceStation() {
		return registerAddressPoliceStation;
	}
	public void setRegisterAddressPoliceStation(String registerAddressPoliceStation) {
		this.registerAddressPoliceStation = registerAddressPoliceStation;
	}
	public String getRegisterAddressDetail() {
		return registerAddressDetail;
	}
	public void setRegisterAddressDetail(String registerAddressDetail) {
		this.registerAddressDetail = registerAddressDetail;
	}
	public String getOrigo() {
		return origo;
	}
	public void setOrigo(String origo) {
		this.origo = origo;
	}
	public String getEthnicGroup() {
		return ethnicGroup;
	}
	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}
	public String getLocalAddressDetail() {
		return localAddressDetail;
	}
	public void setLocalAddressDetail(String localAddressDetail) {
		this.localAddressDetail = localAddressDetail;
	}
	public String getPersonInControlType() {
		return personInControlType;
	}
	public void setPersonInControlType(String personInControlType) {
		this.personInControlType = personInControlType;
	}
	public Long getInControlaRegisterTime() {
		return inControlaRegisterTime;
	}
	public void setInControlaRegisterTime(Long inControlaRegisterTime) {
		this.inControlaRegisterTime = inControlaRegisterTime;
	}
	public String getMarriageStatus() {
		return marriageStatus;
	}
	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	public String getOperateStatusName() {
		return operateStatusName;
	}
	public void setOperateStatusName(String operateStatusName) {
		this.operateStatusName = operateStatusName;
	}
	public String getUsedName() {
		return usedName;
	}
	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEducationName() {
		return educationName;
	}
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}
	public String getAddReason() {
		return addReason;
	}
	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}
	public String getMarriageStatusName() {
		return marriageStatusName;
	}
	public void setMarriageStatusName(String marriageStatusName) {
		this.marriageStatusName = marriageStatusName;
	}
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	public String getEmploymentStatusName() {
		return employmentStatusName;
	}
	public void setEmploymentStatusName(String employmentStatusName) {
		this.employmentStatusName = employmentStatusName;
	}
	public String getPersonInControlTypeName() {
		return personInControlTypeName;
	}
	public void setPersonInControlTypeName(String personInControlTypeName) {
		this.personInControlTypeName = personInControlTypeName;
	}
	public String getApprovePeople() {
		return approvePeople;
	}
	public void setApprovePeople(String approvePeople) {
		this.approvePeople = approvePeople;
	}
	public String getApproveContent() {
		return approveContent;
	}
	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}
	public Long getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Long approveTime) {
		this.approveTime = approveTime;
	}
	public Integer getDataNew() {
		return dataNew;
	}
	public void setDataNew(Integer dataNew) {
		this.dataNew = dataNew;
	}
}