package com.taiji.pubsec.fullsearch.action.bean;

/**
 * 案件bean
 * @author sunjd
 *
 */
public class CasesBean {

	private String id;
	private String name;	//案件名称
	private String type;	//案件类型
	private String date;	//发安时间
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
