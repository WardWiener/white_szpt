package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 涉案人员信息
 * 
 * @author wangfx
 */
@Entity
@Table(name="t_xsajfx_sary")
public class CriminalPerson {

	/**
	 * 人员编号
	 */
	@Id
	@Column(name="rybh", length = 50)
	private String personId;
	
	/**
	 * 姓名
	 */
	@Column(name="ryxm", length = 60)
	private String name;
	
	/**
	 * 别名
	 */
	@Column(name="bm", length = 60)
	private String aliasName;
	
	/**
	 * 绰号
	 */
	@Column(name="ch", length = 60)
	private String nickName;
	
	/**
	 * 性别名称
	 */
	@Column(name="xb", length = 10)
	private String sex;
	
	/**
	 * 出生日期
	 */
	@Column(name="csrq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	/**
	 * 民族名称
	 */
	@Column(name="mz", length = 20)
	private String nation;
	
	/**
	 * 身份证号
	 */
	@Column(name="sfzh", length = 30)
	private String idcardNo;
	
	/**
	 * 联系电话
	 */
	@Column(name="lxdh", length = 90)
	private String telephone;
	
	/**
	 * 文化程度名称
	 */
	@Column(name="whcd", length = 20)
	private String culture;
	
	/**
	 * 政治面貌名称
	 */
	@Column(name="zzmm", length = 50)
	private String politics;
	
	/**
	 * 婚否名称
	 */
	@Column(name="hf", length = 10)
	private String ifMarry;
	
	/**
	 * 职务
	 */
	@Column(name="zw", length = 90)
	private String headShip;
	
	/**
	 * 职业
	 */
	@Column(name="zy", length = 60)
	private String job;
	
	/**
	 * 特殊身份
	 */
	@Column(name="tssf", length = 50)
	private String specialIdentity;
	
	/**
	 * 宗教信仰名称
	 */
	@Column(name="zjxy", length = 50)
	private String faith;
	
	/**
	 * 口音名称
	 */
	@Column(name="ky", length = 50)
	private String tone;
	
	/**
	 * 籍贯国籍名称
	 */
	@Column(name="jggj", length = 50)
	private String nativePlace;
	
	/**
	 * 出生地名称
	 */
	@Column(name="csd", length = 50)
	private String birthDistrict;
	
	/**
	 * 出生地详细住址
	 */
	@Column(name="csdxz", length = 300)
	private String birthDetail;
	
	/**
	 * 户籍地名称
	 */
	@Column(name="hjd", length = 50)
	private String door;
	
	/**
	 * 户籍地详细住址
	 */
	@Column(name="hjdxz", length = 300)
	private String doorDetail;
	
	/**
	 * 现住址名称
	 */
	@Column(name="xzd", length = 50)
	private String address;
	
	/**
	 * 现住地详细地址
	 */
	@Column(name="xzdxz", length = 300)
	private String addressDetail;
	
	/**
	 * 工作单位
	 */
	@Column(name="gzdw", length = 400)
	private String employUnit;
	
	/**
	 * 工作单位地址
	 */
	@Column(name="gzdwdz", length = 150)
	private String employAdress;
	
	/**
	 * QQ号码
	 */
	@Column(name="qq", length = 100)
	private String qqNo;
	
	/**
	 * 电子邮箱
	 */
	@Column(name="dzyj", length = 100)
	private String email;
	
	/**
	 * 身高
	 */
	@Column(name="sg")
	private Double staturest;
	
	/**
	 * 体重
	 */
	@Column(name="tz")
	private Double avoirdupois;
	
	/**
	 * 体型名称
	 */
	@Column(name="tx", length = 50)
	private String bodilyForm;
	
	/**
	 * 脸型名称
	 */
	@Column(name="lx", length = 50)
	private String faceform;
	
	/**
	 * 足长
	 */
	@Column(name="zc")
	private Double footSize;
	
	/**
	 * 鞋号
	 */
	@Column(name="xh")
	private Double shoesSize;
	
	/**
	 * 血型
	 */
	@Column(name="xx", length = 50)
	private String bloodType;
	
	/**
	 * 嗜好
	 */
	@Column(name="sh", length = 400)
	private String addiction;
	
	/**
	 * 录入人
	 */
	@Column(name="lrr", length = 50)
	private String inputPerson;
	
	/**
	 * 录入时间
	 */
	@Column(name="lrsj")
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
	@Column(name="xgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getIfMarry() {
		return ifMarry;
	}

	public void setIfMarry(String ifMarry) {
		this.ifMarry = ifMarry;
	}

	public String getHeadShip() {
		return headShip;
	}

	public void setHeadShip(String headShip) {
		this.headShip = headShip;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSpecialIdentity() {
		return specialIdentity;
	}

	public void setSpecialIdentity(String specialIdentity) {
		this.specialIdentity = specialIdentity;
	}

	public String getFaith() {
		return faith;
	}

	public void setFaith(String faith) {
		this.faith = faith;
	}

	public String getTone() {
		return tone;
	}

	public void setTone(String tone) {
		this.tone = tone;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getBirthDistrict() {
		return birthDistrict;
	}

	public void setBirthDistrict(String birthDistrict) {
		this.birthDistrict = birthDistrict;
	}

	public String getBirthDetail() {
		return birthDetail;
	}

	public void setBirthDetail(String birthDetail) {
		this.birthDetail = birthDetail;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getDoorDetail() {
		return doorDetail;
	}

	public void setDoorDetail(String doorDetail) {
		this.doorDetail = doorDetail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getEmployUnit() {
		return employUnit;
	}

	public void setEmployUnit(String employUnit) {
		this.employUnit = employUnit;
	}

	public String getEmployAdress() {
		return employAdress;
	}

	public void setEmployAdress(String employAdress) {
		this.employAdress = employAdress;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getStaturest() {
		return staturest;
	}

	public void setStaturest(Double staturest) {
		this.staturest = staturest;
	}

	public Double getAvoirdupois() {
		return avoirdupois;
	}

	public void setAvoirdupois(Double avoirdupois) {
		this.avoirdupois = avoirdupois;
	}

	public String getBodilyForm() {
		return bodilyForm;
	}

	public void setBodilyForm(String bodilyForm) {
		this.bodilyForm = bodilyForm;
	}

	public String getFaceform() {
		return faceform;
	}

	public void setFaceform(String faceform) {
		this.faceform = faceform;
	}

	public Double getFootSize() {
		return footSize;
	}

	public void setFootSize(Double footSize) {
		this.footSize = footSize;
	}

	public Double getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(Double shoesSize) {
		this.shoesSize = shoesSize;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getAddiction() {
		return addiction;
	}

	public void setAddiction(String addiction) {
		this.addiction = addiction;
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
	

	
}