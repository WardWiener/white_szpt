package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;


/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:54:59
 */
public class AlarmBean {

	private String id;
	/**
	 * 警情名称
	 */
	private String name;
	/**
	 * 警情内容
	 */
	private String content;
	/**
	 * 接警时间
	 */
	private Date answerTime;
	/**
	 * 发生时间
	 */
	private Date occurTime;
	/**
	 * 警情类别
	 */
	private String type;
	/**
	 * 辖区
	 */
	private String region;
	/**
	 * 村居
	 */
	private String community;
	/**
	 * 发生地址
	 */
	private String occurAddress;
	/**
	 * 紧急程度
	 */
	private String level;
	/**
	 * 警情来源
	 */
	private String source;
	/**
	 * 状态
	 */
	private String state;

	public AlarmBean(){

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getOccurAddress() {
		return occurAddress;
	}

	public void setOccurAddress(String occurAddress) {
		this.occurAddress = occurAddress;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void finalize() throws Throwable {

	}

}