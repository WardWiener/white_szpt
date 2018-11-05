package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_gwry_hcrytj")
public class PersonCheckInfo {
	
	@Id
	private String id ;
	
	/**
	 * 单位编码
	 */
	@Column(name="dwbm")
	private String unitCode ;
	
	/**
	 * 单位名称
	 */
	@Column(name="dwmc")
	private String unitName ;
	
	/**
	 * 核查时间
	 */
	@Column(name="hcsj")
	private Date interrogatDate ;
	
	/**
	 * 涉毒，值为字典项是否
	 */
	@Column(name="sfsd")
	private String involveDrug ;
	
	/**
	 * 刑事前科，值为字典项是否
	 */
	@Column(name="sfxsqk")
	private String involveCriminalRecord ;
	
	/**
	 * 五色预警类型、字典项
	 */
	@Column(name="wsyjlx")
	private String colorType ;

	/**
	 * 被查人员证件号码
	 */
	@Column(name="zjhm")
	private String idNum ;
	
	/**
	 * 被查人员姓名
	 */
	@Column(name="ryxm")
	private String name ;
	
	/**
	 * 证件类型
	 */
	@Column(name="zjlx")
	private String idType ;
	
	/**
	 * 性别
	 */
	@Column(name="ryxb")
	private String sex ;
	
	/**
	 * 核录地址
	 */
	@Column(name="hldz")
	private String checkAddress ;
	
	/**
	 * 原始预警信息
	 */
	@Column(name="yjxx")
	private String oldAlertMessage ;
	
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Date getInterrogatDate() {
		return interrogatDate;
	}

	public void setInterrogatDate(Date interrogatDate) {
		this.interrogatDate = interrogatDate;
	}

	public String getInvolveDrug() {
		return involveDrug;
	}

	public void setInvolveDrug(String involveDrug) {
		this.involveDrug = involveDrug;
	}

	public String getInvolveCriminalRecord() {
		return involveCriminalRecord;
	}

	public void setInvolveCriminalRecord(String involveCriminalRecord) {
		this.involveCriminalRecord = involveCriminalRecord;
	}

	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCheckAddress() {
		return checkAddress;
	}

	public void setCheckAddress(String checkAddress) {
		this.checkAddress = checkAddress;
	}

	public String getOldAlertMessage() {
		return oldAlertMessage;
	}

	public void setOldAlertMessage(String oldAlertMessage) {
		this.oldAlertMessage = oldAlertMessage;
	}
	
	
}
