package com.taiji.pubsec.szpt.zagl.action.bean;

public class BackStorageFormExcelBean {
	
	/**
	 * 序号
	 */
	private String serialNum; 
	
	/**
	 * 留言内容
	 */
	private String messageContent; 
	
	/**
	 * 留言时间
	 */
	private String creatTime; 
	
	/**
	 * 留言人
	 */
	private String messagePerson; 
	
	/**
	 * 所属部门
	 */
	private String messageUnit;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getMessagePerson() {
		return messagePerson;
	}

	public void setMessagePerson(String messagePerson) {
		this.messagePerson = messagePerson;
	}

	public String getMessageUnit() {
		return messageUnit;
	}

	public void setMessageUnit(String messageUnit) {
		this.messageUnit = messageUnit;
	} 
	
	
}
