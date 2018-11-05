package com.taiji.pubsec.fullsearch.action.bean;

/**
 * 场所bean
 * @author sunjd
 *
 */
public class SiteBean {
	private String id ;	
	private String name ;	//场所名称
	private String siteType;	//场所类型
	private String alertLevel; //预警等级
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
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getAlertLevel() {
		return alertLevel;
	}
	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}
	
	
}
