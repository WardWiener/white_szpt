package com.taiji.pubsec.szpt.zagl.action.bean;


public class SpecialCaseReportBean {
	
	private String id;

	/**
	 * 附件id
	 */
	private String attachmentId;
	/**
	 * 专案id
	 */
	private String  caseID;
	/**
	 * 创建时间
	 */
	private long createdTime;
	/**
	 * 创建人
	 */
	private String createPerson;
	/**
	 * 创建人ID
	 */
	private String createPersonID;
	/**
	 * 录入机构名称
	 */
	private String unitName;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public String getCreatePersonID() {
		return createPersonID;
	}

	public void setCreatePersonID(String createPersonID) {
		this.createPersonID = createPersonID;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
