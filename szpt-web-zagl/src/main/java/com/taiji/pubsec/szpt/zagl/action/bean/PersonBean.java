package com.taiji.pubsec.szpt.zagl.action.bean;



public class PersonBean {
	
	private String id ; //人员id
	
	private String sex ; //人员性别
	
	private String name ;  //人员姓名
	
	private String type ; //人员类型
	
	private String  unit ; //所属机构名称 简称
	
	private  String state; //状态
	
	private  String roleId; //角色id
	
	private String roleName;//角色名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
