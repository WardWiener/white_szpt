package com.taiji.pubsec.szpt.instruction.action.util.bean;

public class UnitSearchBean {

	private String name;
	private String fullName;
	private String code;
	private String type;
	private String level;
	private String suburbMark; // 是否为远郊区县单位

	private String parentCode;
	private String businessParentId;
	private String businessParentCode;

	private String address;
	private String officePhone;
	private String insidePhone;
	private String officeFax;
	private String insideFax;
	private String description;
	private boolean hasChildren; // 是否有下级单位

	public UnitSearchBean() {

	}

	public UnitSearchBean( String parentCode,
			String businessParentId, String businessParentCode, String name,
			String fullName, String code, String type, String level,
			String address, String officePhone, String insidePhone,
			String officeFax, String insideFax, String description,
			String hasChildren, String suburbMark) {
		this.parentCode = parentCode;
		this.businessParentId = businessParentId;
		this.businessParentCode = businessParentCode;
		this.name = name;
		this.fullName = fullName;
		this.code = code;
		this.type = type;
		this.level = level;
		this.address = address;
		this.officePhone = officePhone;
		this.insidePhone = insidePhone;
		this.officeFax = officeFax;
		this.insideFax = insideFax;
		this.description = description;
		// this.webservices = webservices;
		if ("true".equals(hasChildren)) {
			this.hasChildren = true;
		} else {
			this.hasChildren = false;
		}
		this.suburbMark = suburbMark;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSuburbMark() {
		return suburbMark;
	}

	public void setSuburbMark(String suburbMark) {
		this.suburbMark = suburbMark;
	}


	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getBusinessParentId() {
		return businessParentId;
	}

	public void setBusinessParentId(String businessParentId) {
		this.businessParentId = businessParentId;
	}

	public String getBusinessParentCode() {
		return businessParentCode;
	}

	public void setBusinessParentCode(String businessParentCode) {
		this.businessParentCode = businessParentCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getInsidePhone() {
		return insidePhone;
	}

	public void setInsidePhone(String insidePhone) {
		this.insidePhone = insidePhone;
	}

	public String getOfficeFax() {
		return officeFax;
	}

	public void setOfficeFax(String officeFax) {
		this.officeFax = officeFax;
	}

	public String getInsideFax() {
		return insideFax;
	}

	public void setInsideFax(String insideFax) {
		this.insideFax = insideFax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	
	
}
