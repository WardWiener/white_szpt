package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 涉案物品
 * @author wangfx
 * @version 1.0
 * @created 10-8月-2016 15:03:35
 */
@Entity
@Table(name = "t_ajgl_sawp")
public class CriminalObject {

	/**
	 * id
	 */
	@Id
	private String id;
	/**
	 * 产地
	 */
	private String producingArea;
	/**
	 * 购买地址
	 */
	private String purchaseAdd;
	/**
	 * 购买日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	/**
	 * 号码(车牌号)
	 */
	private String serialNumber;
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
	 * 品牌
	 */
	private String tradeMark;
	/**
	 * 其他特征(1000)
	 */
	@Column(length = 1000)
	private String otherFeature;
	/**
	 * 数量单位
	 */
	private String amountUnit;
	/**
	 * 特征描述及备注
	 */
	@Column(length = 4000)
	private String annex;
	/**
	 * 物品编号
	 */
	private String objid;
	/**
	 * 物品成色
	 */
	private String quality;
	/**
	 * 物品类型
	 */
	private String objectType;
	/**
	 * 物品名称
	 */
	private String objectName;
	/**
	 * 物品数量
	 */
	private String amounts;
	/**
	 * 物品状态
	 */
	private String itemStatus;
	/**
	 * 型号
	 */
	private String model;
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
	 * 颜色1
	 */
	private String color1;
	/**
	 * 颜色2
	 */
	private String color2;
	/**
	 * 颜色3
	 */
	private String color3;
	/**
	 * 重量
	 */
	@Column(nullable = true)
	private Integer weight;
	/**
	 * 重量单位
	 */
	private String weightUnit;
	
	/**
	 * 价值
	 */
	private String value;
	
	/**
	 * 时间戳
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updateTime;
	
	/**
	 * 案事件基本信息
	 */
	@ManyToOne
	@JoinColumn(name = "basicCase_id")
	private CriminalBasicCase criminalBasicCase;
	
	public String getId() {
		return id;
	}
	public String getProducingArea() {
		return producingArea;
	}
	public String getPurchaseAdd() {
		return purchaseAdd;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public String getInputPerson() {
		return inputPerson;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public String getTradeMark() {
		return tradeMark;
	}
	public String getOtherFeature() {
		return otherFeature;
	}
	public String getAmountUnit() {
		return amountUnit;
	}
	public String getAnnex() {
		return annex;
	}
	public String getObjid() {
		return objid;
	}
	public String getQuality() {
		return quality;
	}
	public String getObjectType() {
		return objectType;
	}
	public String getObjectName() {
		return objectName;
	}
	public String getAmounts() {
		return amounts;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public String getModel() {
		return model;
	}
	public String getModifiedPerson() {
		return modifiedPerson;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public String getColor1() {
		return color1;
	}
	public String getColor2() {
		return color2;
	}
	public String getColor3() {
		return color3;
	}
	public Integer getWeight() {
		return weight;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
	}
	public void setPurchaseAdd(String purchaseAdd) {
		this.purchaseAdd = purchaseAdd;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
	public void setOtherFeature(String otherFeature) {
		this.otherFeature = otherFeature;
	}
	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	public void setAnnex(String annex) {
		this.annex = annex;
	}
	public void setObjid(String objid) {
		this.objid = objid;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public void setColor1(String color1) {
		this.color1 = color1;
	}
	public void setColor2(String color2) {
		this.color2 = color2;
	}
	public void setColor3(String color3) {
		this.color3 = color3;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public CriminalBasicCase getCriminalBasicCase() {
		return criminalBasicCase;
	}
	public void setCriminalBasicCase(CriminalBasicCase criminalBasicCase) {
		this.criminalBasicCase = criminalBasicCase;
	}
	
	

}