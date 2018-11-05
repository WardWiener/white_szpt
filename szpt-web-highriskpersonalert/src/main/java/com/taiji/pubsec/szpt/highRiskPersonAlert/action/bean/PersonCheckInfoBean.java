package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

/**
 * 核查人员Bean
 * @author WangLei
 *
 */
public class PersonCheckInfoBean {

	private String id ;
	
	private String unitCode ;// 单位编码
	
	private String unitName ;// 单位名称
	
	private Long interrogatDate ;// 核查时间
	
	private String involveDrug ;// 是否涉毒字典项编码
	
	private String involveDrugName;// 是否涉毒字典项名称
	
	private String involveCriminalRecord ;// 是否有刑事前科字典项编码
	
	private String involveCriminalRecordName ;// 是否有刑事前科字字典项名称
	
	private String colorType ;// 五色预警类型字典项编码
	
	private String colorTypeName ;// 五色预警类型字典项名称
	
	private String idNum ;// 被查人员证件号码
	
	private String name ;// 被查人员姓名
	
	private String idType ;// 证件类型
	
	private String sex ;// 性别
	
	private String checkAddress ;// 核录地址
	
	private String oldAlertMessage ;// 原始预警信息
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Long getInterrogatDate() {
		return interrogatDate;
	}

	public void setInterrogatDate(Long interrogatDate) {
		this.interrogatDate = interrogatDate;
	}

	public String getInvolveDrug() {
		return involveDrug;
	}

	public void setInvolveDrug(String involveDrug) {
		this.involveDrug = involveDrug;
	}

	public String getInvolveDrugName() {
		return involveDrugName;
	}

	public void setInvolveDrugName(String involveDrugName) {
		this.involveDrugName = involveDrugName;
	}

	public String getInvolveCriminalRecord() {
		return involveCriminalRecord;
	}

	public void setInvolveCriminalRecord(String involveCriminalRecord) {
		this.involveCriminalRecord = involveCriminalRecord;
	}

	public String getInvolveCriminalRecordName() {
		return involveCriminalRecordName;
	}

	public void setInvolveCriminalRecordName(String involveCriminalRecordName) {
		this.involveCriminalRecordName = involveCriminalRecordName;
	}

	public String getColorType() {
		return colorType;
	}

	public void setColorType(String colorType) {
		this.colorType = colorType;
	}

	public String getColorTypeName() {
		return colorTypeName;
	}

	public void setColorTypeName(String colorTypeName) {
		this.colorTypeName = colorTypeName;
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
