package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

/**
 * 涉案物品
 * 
 * @author wangfx
 */
@Entity
@Table(name="t_xsajfx_sawp")
public class CriminalObject {

	/**
	 * 物品编号
	 */
	@Id
	@Column(name="wpbh", length = 50)
	private String objid;
	
	/**
	 * 物品类型名称
	 */
	@Column(name="wplx", length = 50)
	private String type;
	
	/**
	 * 物品名称
	 */
	@Column(name="wpmc", length = 90)
	private String name;
	
	/**
	 * 产地
	 */
	@Column(name="cd", length = 90)
	private String producingArea;
	
	/**
	 * 品牌
	 */
	@Column(name="pp", length = 90)
	private String tradeMark;
	
	/**
	 * 号码(车牌号)
	 */
	@Column(name="hm", length = 90)
	private String serialNumber;
	
	/**
	 * 型号1
	 */
	@Column(name="xh1", length = 90)
	private String model;
	
	/**
	 * 型号2
	 */
	@Column(name="xh2", length = 90)
	private String models;
	
	/**
	 * 物品成色
	 */
	@Column(name="wpcs", length = 30)
	private String quality;
	
	/**
	 * 重量
	 */
	@Column(name="zl", length = 20)
	private String weight;
	
	/**
	 * 重量单位
	 */
	@Column(name="zldw", length = 20)
	private String weightUnit;
	
	/**
	 * 物品数量
	 */
	@Column(name="sl", length = 20)
	private String amounts;
	
	/**
	 * 数量单位
	 */
	@Column(name="sldw", length = 20)
	private String amountUnit;
	
	/**
	 * 颜色1名称
	 */
	@Column(name="ys1", length = 10)
	private String color1;
	
	/**
	 * 颜色2名称
	 */
	@Column(name="ys2", length = 10)
	private String color2;
	
	/**
	 * 颜色3名称
	 */
	@Column(name="ys3", length = 10)
	private String color3;
	
	/**
	 * 其他特征(1000)
	 */
	@Column(name="qttz", length = 1000)
	private String otherFeature;
	
	/**
	 * 特征描述及备注
	 */
	@Column(name="bz", length = 1500)
	private String annex;
	
	/**
	 * 物品状态
	 */
	@Column(name="zt", length = 50)
	private String itemStatus;
	
	/**
	 * 购买日期
	 */
	@Column(name="gmrq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	
	/**
	 * 购买地址
	 */
	@Column(name="gmdz", length = 180)
	private String purchaseAddress;
	
	/**
	 * 价值
	 */
	@Column(name="jz", length = 30)
	private String value;
	
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
	
	/**
	 * 案事件基本信息
	 */
	@ManyToOne
	@JoinColumn(name="ajbh")
	@Index(name="index_t_xsajfx_sawp_ajbh")
	private CriminalBasicCase basicCase;

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProducingArea() {
		return producingArea;
	}

	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColor3() {
		return color3;
	}

	public void setColor3(String color3) {
		this.color3 = color3;
	}

	public String getOtherFeature() {
		return otherFeature;
	}

	public void setOtherFeature(String otherFeature) {
		this.otherFeature = otherFeature;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseAddress() {
		return purchaseAddress;
	}

	public void setPurchaseAddress(String purchaseAddress) {
		this.purchaseAddress = purchaseAddress;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAmounts() {
		return amounts;
	}

	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}
	
	

}