package com.taiji.pubsec.szpt.dagl.action.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class SnapshotBean {
	
	
	private String id ;
	
	/**
	 * 编码
	 */

	private String code ;
	
	/**
	 * 快照关联对象id
	 */
	
	private String targetId ;
	
	/**
	 * 快照关联对象的类型
	 */
	private String type ;
	
	/**
	 * 简介
	 */
	private String intro ;
	
	/**
	 * 创建人姓名
	 */
	private String createPerson;
	
	/**
	 * 创建时间
	 */
	private String createdDate ;
	
	/**
	 * 快照完整信息，json字符串
	 */
	private String snapshot ;
	

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

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
	
}
