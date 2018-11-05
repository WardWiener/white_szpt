package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 涉案物品Bean
 */
public class CriminalObjectBean {

	/**
	 * id
	 */
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
	private String purchaseString;
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
	private String inputTime;
	/**
	 * 品牌
	 */
	private String tradeMark;
	/**
	 * 其他特征(1000)
	 */
	private String otherFeature;
	/**
	 * 数量单位
	 */
	private String amountUnit;
	/**
	 * 特征描述及备注
	 */
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
	private String modifiedTime;
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
	private String weight;
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
	private String updateTime;
	
	private String objectTz;//物品特征
	
	private String savefield;//保管区
	private String saveCupboard;//保管柜
	private String wswh;//文书文号
	private String wsCode;//文书编号
	private String suspectName;//嫌疑人姓名
	private String caseCode;//案件编号
	private String caseName;//案件名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProducingArea() {
		return producingArea;
	}

	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
	}

	public String getPurchaseAdd() {
		return purchaseAdd;
	}

	public void setPurchaseAdd(String purchaseAdd) {
		this.purchaseAdd = purchaseAdd;
	}

	public String getPurchaseString() {
		return purchaseString;
	}

	public void setPurchaseString(String purchaseString) {
		this.purchaseString = purchaseString;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}

	public String getOtherFeature() {
		return otherFeature;
	}

	public void setOtherFeature(String otherFeature) {
		this.otherFeature = otherFeature;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public String getObjid() {
		return objid;
	}

	public void setObjid(String objid) {
		this.objid = objid;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getAmounts() {
		return amounts;
	}

	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeightUnit() {
		return weightUnit;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getObjectTz() {
		return objectTz;
	}

	public void setObjectTz(String objectTz) {
		this.objectTz = objectTz;
	}

	public String getSavefield() {
		return savefield;
	}

	public void setSavefield(String savefield) {
		this.savefield = savefield;
	}

	public String getSaveCupboard() {
		return saveCupboard;
	}

	public void setSaveCupboard(String saveCupboard) {
		this.saveCupboard = saveCupboard;
	}

	public String getWswh() {
		return wswh;
	}

	public void setWswh(String wswh) {
		this.wswh = wswh;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getSuspectName() {
		return suspectName;
	}

	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
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
	
}