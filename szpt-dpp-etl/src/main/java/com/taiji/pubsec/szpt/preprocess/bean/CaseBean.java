package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;


/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:54:59
 */
public class CaseBean {

	private String id;
	/**
	 * 案件名称
	 */
	private String name;
	/**
	 * 简要案情
	 */
	private String content;
	/**
	 * 案发时间
	 */
	private Date crimeTime;
	/**
	 * 案件性质
	 */
	private String property;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCrimeTime() {
		return crimeTime;
	}

	public void setCrimeTime(Date crimeTime) {
		this.crimeTime = crimeTime;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}


}