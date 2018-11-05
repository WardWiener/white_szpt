package com.taiji.pubsec.szpt.zagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;

import org.hibernate.annotations.Index;
/**
 * 专案报告
 * @author 
 */
@Entity
@Table(name="t_zagl_zabg")
public class SpecialCaseReport {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	/**
	 * 附件id
	 */
	private String attachmentId;
	/**
	 * 专案
	 */
	@ManyToOne
	@JoinColumn(name="zagl_za_id")
	private SpecialCase specialCase;
	/**
	 * 创建时间
	 */
	@Column(name = "cjsj")
	private Date createdTime;
	/**
	 * 创建人
	 */
	@ManyToOne
	@JoinColumn(name="cjr_id")
	private Person createPerson;
	
	/**
	 * 名称
	 */
	@Column(name = "mc")
	private String name;
	/**
	 * 类型
	 */
	@Column(name = "lx")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public SpecialCase getSpecialCase() {
		return specialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Person getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Person createPerson) {
		this.createPerson = createPerson;
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

}