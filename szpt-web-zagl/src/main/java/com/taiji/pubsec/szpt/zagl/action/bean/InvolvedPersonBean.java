package com.taiji.pubsec.szpt.zagl.action.bean;


/**
 * 涉案人员
 * @author liyanpu
 *
 */
public class InvolvedPersonBean {
	
	private  String  id;
	
	private String createPersonId ; //创建人员id
	
	private String nick ; //人员绰号
	
	private String name ;  //人员姓名
	
	private String idcard ; //身份证号
	
	private String  phone ; //电话
	
	private  String householdRegister; //户籍
	
	private  String householdAddress; //户籍地址
	
	private String createdTime;//创建时间

	public String getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(String createPersonId) {
		this.createPersonId = createPersonId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHouseholdRegister() {
		return householdRegister;
	}

	public void setHouseholdRegister(String householdRegister) {
		this.householdRegister = householdRegister;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
