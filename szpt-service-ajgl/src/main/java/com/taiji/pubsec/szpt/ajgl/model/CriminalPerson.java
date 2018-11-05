package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalPersonService;

/**
 * 涉案人员
 * @author wangfx
 * @version 1.0
 * @created 10-8月-2016 15:04:17
 */
@Entity
@Table(name = "t_ajgl_sary")
public class CriminalPerson {
	
	/**
	 * 人员编号
	 */
	@Id
	private String personId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * QQ号码
	 */
	private String qqNo;
	/**
	 * 备注
	 */
	@Column(length = 4000)
	private String annex;
	/**
	 * 姓名拼音
	 */
	private String spellName;
	/**
	 * 别名
	 */
	private String aliasName;
	/**
	 * 别名拼音
	 */
	private String aliasSpell;
	/**
	 * 曾用名
	 */
	private String usedName;
	/**
	 * 人员状态
	 */
	private String personState;
	/**
	 * 曾用名拼音
	 */
	private String usedSpell;
	/**
	 * 绰号
	 */
	private String nickName;
	/**
	 * 绰号拼音
	 */
	private String nickSpell;
	/**
	 * 出生地代码
	 */
	private String birthCode;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生地详细住址
	 */
	private String birthDetail;
	/**
	 * 出生日期起
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdayStart;
	/**
	 * 身份证号
	 */
	private String idcardNo;
	/**
	 * 出生日期止
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdayEnd;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 工作单位
	 */
	private String employUnit;
	/**
	 * 工作单位地址
	 */
	private String employAdress;
	/**
	 * 户籍地代码
	 */
	private String door;
	/**
	 * 户籍地详细住址
	 */
	private String doorDetail;
	/**
	 * 婚否
	 */
	private String ifMarry;
	/**
	 * 籍贯国籍
	 */
	private String nativePlace;
	/**
	 * 政治面貌
	 */
	private String politics;
	/**
	 * 口音
	 */
	private String tone;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 录入人
	 */
	private String inputPerson;
	/**
	 * 现住址代码
	 */
	private String address;
	/**
	 * 录入时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
	/**
	 * 现住地详细地址
	 */
	private String addressDetail;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 文化程度
	 */
	private String culture;
	/**
	 * 宗教信仰
	 */
	private String faith;
	/**
	 * 其他专长
	 */
	private String otherspecialty;
	/**
	 * 情况说明(750)
	 */
	@Column(length = 750)
	private String thingExplain;
	/**
	 * 职业
	 */
	private String job;
	/**
	 * 特殊身份
	 */
	private String specialIdentity;
	/**
	 * 身高
	 */
	@Column(nullable = true)
	private Double staturest;
	/**
	 * 体型
	 */
	private String bodilyForm;
	/**
	 * 脸型
	 */
	private String faceForm;
	/**
	 * 体重
	 */
	@Column(nullable = true)
	private Double avoirdupois;
	/**
	 * 足长
	 */
	@Column(nullable = true)
	private Double footSize;
	/**
	 * 鞋号
	 */
	@Column(nullable = true)
	private Double shoesSize;
	/**
	 * 血型
	 */
	private String bloodType;
	/**
	 * 是否会驾驶
	 */
	private String ifDrive;
	/**
	 * 是否经常上网
	 */
	private String ifOftenNet;
	/**
	 * 嗜好
	 */
	private String addiction;
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
	 * 职务
	 */
	private String headShip;
	
	/**
	 * 性别编码
	 */
	private String sexCode;
	
	/**
	 * 出生地代码编码
	 */
	private String birthCodeCode;
	
	/**
	 * 户籍地代码编码
	 */
	private String doorCode;
	
	/**
	 * 口音编码
	 */
	private String toneCode;
	
	/**
	 * 现住址代码编码
	 */
	private String addressCode;
	
	/**
	 * 时间戳
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updateTime;
	
//	@OneToMany(mappedBy = "person")
//	private List<SufferCaseRelation> sufferCaseRelations = new ArrayList<SufferCaseRelation>();
	
//	@OneToMany(mappedBy = "person")
//	private List<CaseSupectRelation> caseSupectRelations = new ArrayList<CaseSupectRelation>();
	
	public String getPersonId() {
		return personId;
	}

	public String getName() {
		return name;
	}

	public String getQqNo() {
		return qqNo;
	}

	public String getAnnex() {
		return annex;
	}

	public String getSpellName() {
		return spellName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public String getAliasSpell() {
		return aliasSpell;
	}

	public String getUsedName() {
		return usedName;
	}

	public String getUsedSpell() {
		return usedSpell;
	}

	public String getNickName() {
		return nickName;
	}

	public String getNickSpell() {
		return nickSpell;
	}

	public String getBirthCode() {
		return birthCode;
	}

	public String getSex() {
		return sex;
	}

	public String getBirthDetail() {
		return birthDetail;
	}

	public Date getBirthdayStart() {
		return birthdayStart;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public Date getBirthdayEnd() {
		return birthdayEnd;
	}

	public String getEmail() {
		return email;
	}

	public String getEmployUnit() {
		return employUnit;
	}

	public String getEmployAdress() {
		return employAdress;
	}

	public String getDoor() {
		return door;
	}

	public String getDoorDetail() {
		return doorDetail;
	}

	public String getIfMarry() {
		return ifMarry;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public String getPolitics() {
		return politics;
	}

	public String getTone() {
		return tone;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public String getAddress() {
		return address;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public String getNation() {
		return nation;
	}

	public String getCulture() {
		return culture;
	}

	public String getFaith() {
		return faith;
	}

	public String getOtherspecialty() {
		return otherspecialty;
	}

	public String getThingExplain() {
		return thingExplain;
	}

	public String getJob() {
		return job;
	}

	public String getSpecialIdentity() {
		return specialIdentity;
	}

	public Double getStaturest() {
		return staturest;
	}

	public String getBodilyForm() {
		return bodilyForm;
	}

	public Double getAvoirdupois() {
		return avoirdupois;
	}

	public Double getFootSize() {
		return footSize;
	}

	public Double getShoesSize() {
		return shoesSize;
	}

	public String getBloodType() {
		return bloodType;
	}

	public String getIfDrive() {
		return ifDrive;
	}

	public String getIfOftenNet() {
		return ifOftenNet;
	}

	public String getAddiction() {
		return addiction;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public String getHeadShip() {
		return headShip;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public void setAliasSpell(String aliasSpell) {
		this.aliasSpell = aliasSpell;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

	public void setUsedSpell(String usedSpell) {
		this.usedSpell = usedSpell;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setNickSpell(String nickSpell) {
		this.nickSpell = nickSpell;
	}

	public void setBirthCode(String birthCode) {
		this.birthCode = birthCode;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setBirthDetail(String birthDetail) {
		this.birthDetail = birthDetail;
	}

	public void setBirthdayStart(Date birthdayStart) {
		this.birthdayStart = birthdayStart;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public void setBirthdayEnd(Date birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployUnit(String employUnit) {
		this.employUnit = employUnit;
	}

	public void setEmployAdress(String employAdress) {
		this.employAdress = employAdress;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public void setDoorDetail(String doorDetail) {
		this.doorDetail = doorDetail;
	}

	public void setIfMarry(String ifMarry) {
		this.ifMarry = ifMarry;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public void setTone(String tone) {
		this.tone = tone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public void setFaith(String faith) {
		this.faith = faith;
	}

	public void setOtherspecialty(String otherspecialty) {
		this.otherspecialty = otherspecialty;
	}

	public void setThingExplain(String thingExplain) {
		this.thingExplain = thingExplain;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setSpecialIdentity(String specialIdentity) {
		this.specialIdentity = specialIdentity;
	}

	public void setStaturest(Double staturest) {
		this.staturest = staturest;
	}

	public void setBodilyForm(String bodilyForm) {
		this.bodilyForm = bodilyForm;
	}

	public void setAvoirdupois(Double avoirdupois) {
		this.avoirdupois = avoirdupois;
	}

	public void setFootSize(Double footSize) {
		this.footSize = footSize;
	}

	public void setShoesSize(Double shoesSize) {
		this.shoesSize = shoesSize;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public void setIfDrive(String ifDrive) {
		this.ifDrive = ifDrive;
	}

	public void setIfOftenNet(String ifOftenNet) {
		this.ifOftenNet = ifOftenNet;
	}

	public void setAddiction(String addiction) {
		this.addiction = addiction;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public void setHeadShip(String headShip) {
		this.headShip = headShip;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFaceForm() {
		return faceForm;
	}

	public void setFaceForm(String faceForm) {
		this.faceForm = faceForm;
	}

	public String getPersonState() {
		return personState;
	}

	public void setPersonState(String personState) {
		this.personState = personState;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getBirthCodeCode() {
		return birthCodeCode;
	}

	public void setBirthCodeCode(String birthCodeCode) {
		this.birthCodeCode = birthCodeCode;
	}

	public String getDoorCode() {
		return doorCode;
	}

	public void setDoorCode(String doorCode) {
		this.doorCode = doorCode;
	}

	public String getToneCode() {
		return toneCode;
	}

	public void setToneCode(String toneCode) {
		this.toneCode = toneCode;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	//	public List<CaseSupectRelation> getCaseSupectRelations() {
//		return caseSupectRelations;
//	}
//
//	public void setCaseSupectRelations(List<CaseSupectRelation> caseSupectRelations) {
//		this.caseSupectRelations = caseSupectRelations;
//	}
	//TODO
	public List<CaseSupectRelation> getCaseSupectRelations(){
		ICriminalPersonService criminalPersonService = (ICriminalPersonService) SpringContextUtil.getBean("criminalPersonService");
		List<CaseSupectRelation> caseSupectRelations = criminalPersonService.findCaseSupectRelationsByPerson(personId);
		return caseSupectRelations;
	}
	
	public List<SufferCaseRelation> getSufferCaseRelations(){
		ICriminalPersonService criminalPersonService = (ICriminalPersonService) SpringContextUtil.getBean("criminalPersonService");
		List<SufferCaseRelation> sufferCaseRelations = criminalPersonService.findSufferCaseRelationsByPerson(personId);
		return sufferCaseRelations;
	}
	
}