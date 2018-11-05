package com.taiji.pubsec.szpt.snapshot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_snapshot")
public class InfoSnapshot {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
	
	/**
	 * 编码
	 */
	@Column(name="code")
	private String code ;
	
	/**
	 * 快照关联对象id
	 */
	@Column(name="targetId")
	private String targetId ;
	
	/**
	 * 快照关联对象的类型
	 */
	@Column(name="type")
	private String type ;
	
	/**
	 * 简介
	 */
	@Column(name="intro")
	private String intro ;
	
	/**
	 * 快照完整信息，json字符串
	 */
	@Column(name="snapshot", length=10000)
	private String snapshot ;
	
	/**
	 * 创建人姓名
	 */
	@Column(name="cjr", length = 50)
	private String createPerson;
	
	/**
	 * 创建时间
	 */
	@Column(name="createdDate")
	private Date createdDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	
}
