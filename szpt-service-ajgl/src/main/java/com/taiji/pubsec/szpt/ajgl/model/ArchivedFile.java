package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 卷宗文书
 * @author dixiaofeng
 * @version 1.0
 * @created 08-9月-2016 10:25:07
 */
@Entity
@Table(name = "t_ajgl_jzws")
public class ArchivedFile {
	
	@Id
	private String id;	//id
	
	@ManyToOne
	@JoinColumn(name = "caseId")
	private CriminalBasicCase criminalBasicCase;	//案件
	
	private String code;	//编码
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date excuteTime;	//执行时间
	
	private String name;	//名称
	
	private String sourceId;	//源库表中id
	
	private String type;	//类型

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

	public Date getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Date excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CriminalBasicCase getCriminalBasicCase() {
		return criminalBasicCase;
	}

	public void setCriminalBasicCase(CriminalBasicCase criminalBasicCase) {
		this.criminalBasicCase = criminalBasicCase;
	}

	

}