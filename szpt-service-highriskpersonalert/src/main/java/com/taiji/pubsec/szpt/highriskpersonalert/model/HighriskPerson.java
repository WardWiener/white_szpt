package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;

/**
 * 高危人员 
 * @author huangda
 * @version 1.0
 */
@Entity
@Table(name = "t_gwry_ryxx")
public class HighriskPerson implements Serializable{
	
	private static final long serialVersionUID = 2779883291192736232L;
	public static final int HIGHRISK_PERSON_DATA_NEW  = 0;
	public static final int HIGHRISK_PERSON_DATA_OLD = 1;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	/**
	 * 姓名
	 */
	@Column(name = "xm")
	private String name;
	/**
	 * 性别，字典项
	 */
	@Column(name = "xb")
	private String sex;
	/**
	 * 身份证号
	 */
	@Column(name = "sfzh")
	private String idcode;
	/**
	 * 户籍地址
	 */
	@Column(name = "hjdz")
	private String registerAddress;
	/**
	 * 居住地址
	 */
	@Column(name = "jzdz")
	private String liveAddress;
	/**
	 * 工作地址
	 */
	@Column(name = "gzdz")
	private String workAddress;
	/**
	 * 职业
	 */
	@Column(name = "zy")
	private String profession;
	/**
	 * 收入
	 */
	@Column(name = "sr")
	private Integer income;
	/**
	 * 预警类型，字典项
	 */
	@Column(name = "yjlx")
	private String warnType;
	/**
	 * 数据来源
	 */
	@Column(name = "sjly")
	private String dataSource;
	/**
	 * 日常状况，0正常，1异常
	 */
	@Column(name = "rczk")
	private String status;
	/**
	 * 尿检结果
	 */
	@Column(name = "njjg")
	private String urineTest;
	/**
	 * 处置情况
	 */
	@Column(name = "czqk")
	private String handleResult;
	/**
	 * 创建人
	 */
	@Column(name = "cjr")
	private String creator;
	/**
	 * 采集时间
	 */
	@Column(name = "cjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	/**
	 * 反馈时间
	 */
	@Column(name = "fksj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date feedbackTime;
	/**
	 * 0表示新，1表示旧
	 */
	@Column(name = "sjzt")
	private Integer dataNew;
	/**
	 * 外号
	 */
	@Column(name = "wh")
	private String nickname;
	/**
	 * 积分
	 */
	@Column(name = "jf")
	private Double accumulatePoints;
	/**
	 * 采集人单位
	 */
	@Column(name = "cjrdw_id")
	private String creatorDepartmentId;
	
	/**
	 * 出生日期
	 */
	@Column(name = "csrq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	/**
	 * 国籍
	 */
	@Column(name = "gj")
	private String nationality;
	
	/**
	 * 户籍地派出所
	 */
	@Column(name = "hjdpcs")
	private String registerAddressPoliceStation;
	
	/**
	 * 户籍地详址
	 */
	@Column(name = "hjdxz")
	private String registerAddressDetail;
	
	/**
	 * 籍贯
	 */
	@Column(name = "jg")
	private String origo;
	
	/**
	 * 民族
	 */
	@Column(name = "mz")
	private String ethnicGroup;
	
	/**
	 * 现住地详址
	 */
	@Column(name = "xzdxz")
	private String localAddressDetail;
	
	/**
	 * 在控类型
	 */
	@Column(name = "zklx")
	private String personInControlType;
	
	/**
	 * 在控登记时间
	 */
	@Column(name = "zkdjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inControlaRegisterTime;
	
	/**
	 * 婚姻情况
	 */
	@Column(name = "hyqk")
	private String marriageStatus;
	
	/**
	 * 就业情况
	 */
	@Column(name = "jyqk")
	private String employmentStatus;
	
	/**
	 * 操作状态：0新增1待审批2审批通过3审批驳回
	 */
	@Column(name = "czzt")
	private String operateStatus;
	
	/**
	 * 曾用名
	 */
	@Column(name = "cym")
	private String usedName;
	
	/**
	 * 文化程度
	 */
	@Column(name = "whcd")
	private String education;
	
	/**
	 * 新增原因		
	 */
	@Column(name = "xzyy")
	private String addReason;
	
	/**
	 * 附件
	 */
	@Transient
	private List<Attachment> attachs = new ArrayList<>();
	
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/**
	 * 移动电话信息
	 */
	@OrderBy("number desc, mac desc, updatedTime desc")
	@OneToMany(mappedBy = "highriskPerson")
	private Set<MobilePhoneInfo> mobilePhoneInfos = new HashSet<MobilePhoneInfo>();
	
	/**
	 * 高危人员类型
	 */
	@OrderBy("updateTime desc")
	@OneToMany(mappedBy = "highriskPerson")
	private Set<HighriskPeopleType> highriskPeopleTypes = new HashSet<HighriskPeopleType>();
	
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
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public String getWarnType() {
		return warnType;
	}
	public void setWarnType(String warnType) {
		this.warnType = warnType;
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
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	public Integer getDataNew() {
		return dataNew;
	}
	public void setDataNew(Integer dataNew) {
		this.dataNew = dataNew;
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
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getCreatorDepartmentId() {
		return creatorDepartmentId;
	}
	public void setCreatorDepartmentId(String creatorDepartmentId) {
		this.creatorDepartmentId = creatorDepartmentId;
	}
	public List<Attachment> getAttachs() {
		IAttachmentCustomizedService attachmentCustomizedService = (IAttachmentCustomizedService) SpringContextUtil.getBean("attachmentCustomizedService");
		return attachmentCustomizedService.findByTargetIdAndType(id, HighriskPerson.class.getName());
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
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
	public String getPersonInControlType() {
		return personInControlType;
	}
	public void setPersonInControlType(String personInControlType) {
		this.personInControlType = personInControlType;
	}
	public Date getInControlaRegisterTime() {
		return inControlaRegisterTime;
	}
	public void setInControlaRegisterTime(Date inControlaRegisterTime) {
		this.inControlaRegisterTime = inControlaRegisterTime;
	}
	public String getLocalAddressDetail() {
		return localAddressDetail;
	}
	public void setLocalAddressDetail(String localAddressDetail) {
		this.localAddressDetail = localAddressDetail;
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
	public void setAttachs(List<Attachment> attachs) {
		this.attachs = attachs;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
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
	public String getAddReason() {
		return addReason;
	}
	public void setAddReason(String addReason) {
		this.addReason = addReason;
	}
	public Set<MobilePhoneInfo> getMobilePhoneInfos() {
		return mobilePhoneInfos;
	}
	public void setMobilePhoneInfos(Set<MobilePhoneInfo> mobilePhoneInfos) {
		this.mobilePhoneInfos = mobilePhoneInfos;
	}
	public Set<HighriskPeopleType> getHighriskPeopleTypes() {
		return highriskPeopleTypes;
	}
	public void setHighriskPeopleTypes(Set<HighriskPeopleType> highriskPeopleTypes) {
		this.highriskPeopleTypes = highriskPeopleTypes;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}