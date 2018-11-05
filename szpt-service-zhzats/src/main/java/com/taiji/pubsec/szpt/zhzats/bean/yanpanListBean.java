package com.taiji.pubsec.szpt.zhzats.bean;

import java.util.Date;

public class yanpanListBean {
	
	private String id ; //警情id
	
	private String code ; //警情编码
	
	private String name ; //警情名称
	
	private String jqlxName ; //警情类型名称
	
	 private String urgencyLevel  ; //紧急程度
	
	private String addr ; //警情地址
	
	private String countryCode ; // 村区编码
	
	private String countryName ; //村区名称
	
	private String jqSummary ; //警情概要
	
	private Date occurrenceTime  ; //发生时间
	
	private String jqSource ; //警情来源
	
	private String yanpanState ; //研判状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJqlxName() {
		return jqlxName;
	}

	public void setJqlxName(String jqlxName) {
		this.jqlxName = jqlxName;
	}

	public String getUrgencyLevel() {
		return urgencyLevel;
	}

	public void setUrgencyLevel(String urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getJqSummary() {
		return jqSummary;
	}

	public void setJqSummary(String jqSummary) {
		this.jqSummary = jqSummary;
	}

	public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public String getJqSource() {
		return jqSource;
	}

	public void setJqSource(String jqSource) {
		this.jqSource = jqSource;
	}

	public String getYanpanState() {
		return yanpanState;
	}

	public void setYanpanState(String yanpanState) {
		this.yanpanState = yanpanState;
	}
}
