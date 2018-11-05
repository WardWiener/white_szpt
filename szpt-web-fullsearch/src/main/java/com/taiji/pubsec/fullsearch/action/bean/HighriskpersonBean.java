package com.taiji.pubsec.fullsearch.action.bean;

/**
 * 高危人Bean
 * @author sunjd
 *
 */
public class HighriskpersonBean {

	private String id ;	
	private String idcard;
	private String name;	//姓名
	private String sex;		//性别
	private String qianKeType;	//前科类型
	private String peopleType;	//人员类别
	private String alertType;  //预警类别
	
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getQianKeType() {
		return qianKeType;
	}
	public void setQianKeType(String qianKeType) {
		this.qianKeType = qianKeType;
	}
	public String getPeopleType() {
		return peopleType;
	}
	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	
	
	
}
