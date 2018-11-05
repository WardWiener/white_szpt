package com.taiji.pubsec.szpt.caseanalysis.score.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 串并案打发结果消息
 * 
 * @author dixiaofeng
 */
@Entity
@Table(name="t_xsajfx_cbafxjgxx")
public class CaseScoreResultMessage {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 案件编号
	 */
	@Index(name="index_t_xsajfx_cbafxjgxx_ajbh")
	@Column(name="ajbh", length = 50)
	private String caseCode;
	
	/**
	 * 消息类容
	 */
	@Column(name="xxnr", length = 200)
	private String content;
	
	/**
	 * 创建时间
	 */
	@Column(name="cjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	/**
	 * 是否已读，默认false
	 */
	@Column(name="sfyd")
	private boolean isRead;
	
	/**
	 * 接收人id
	 */
	@Column(name="jsr_id", length = 50)
	private String personId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}