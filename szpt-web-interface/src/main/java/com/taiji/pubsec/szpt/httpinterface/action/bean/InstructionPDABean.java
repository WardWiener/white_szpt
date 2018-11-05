package com.taiji.pubsec.szpt.httpinterface.action.bean;

import java.util.Date;

public class InstructionPDABean {
	
	
	private String id;	//指令id
	
	private Date createTime;	//创建时间
	
	private String content;	//指令内容
	
	private String createPeopleDepartmentName;	//创建人单位
	
	private String source; 	//来源
	
	private String type;	//指令类型
	
	private String name;	//盘查人员姓名
	
	private String idCardNo;	//盘查人员身份证号
	
	private String address;	//盘查地点
	
	private String signStatus;	//签收状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatePeopleDepartmentName() {
		return createPeopleDepartmentName;
	}

	public void setCreatePeopleDepartmentName(String createPeopleDepartmentName) {
		this.createPeopleDepartmentName = createPeopleDepartmentName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	
	
}
