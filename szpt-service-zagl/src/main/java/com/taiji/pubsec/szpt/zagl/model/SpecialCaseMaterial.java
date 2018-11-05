package com.taiji.pubsec.szpt.zagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.szpt.attachment.model.Attachment;

import org.hibernate.annotations.Index;
/**
 * 专案资料
 * @author 
 */
@Entity
@Table(name="t_zagl_zazl")
public class SpecialCaseMaterial {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	/**
	 * 附件
	 */
//	@OneToOne
//	@JoinColumn(name="zagl_fj_id")
//	private Attachment attachment;
	
	/**
	 * 专案资料名称
	 */
	@Column(name = "zazlmc")
	private String createdName;
	
	/**
	 * 专案
	 */
	@ManyToOne
	@JoinColumn(name="zagl_zabg_id")
	private SpecialCase specialCase;
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cjsj")
	private Date createdTime;
	/**
	 * 创建人
	 */
	@ManyToOne
	@JoinColumn(name = "ryid")
	private Person createPerson;
	
	/**
	 * 类型
	 */
	@Column(name = "lx")
	private String type;

	public SpecialCaseMaterial(){

	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

//	public Attachment getAttachment() {
//		return attachment;
//	}
//
//	public void setAttachment(Attachment attachment) {
//		this.attachment = attachment;
//	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SpecialCase getSpecialCase() {
		return specialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}

}