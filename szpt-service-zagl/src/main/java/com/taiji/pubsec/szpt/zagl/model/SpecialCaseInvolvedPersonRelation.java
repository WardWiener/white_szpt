package com.taiji.pubsec.szpt.zagl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;

import org.hibernate.annotations.Index;
/**
 * 专案涉案人员关系
 * @author 
 */
@Entity
@Table(name = "t_zagl_zasarygx")
public class SpecialCaseInvolvedPersonRelation {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id ;

	/**
	 * 专案
	 */
	@ManyToOne
	@JoinColumn(name="zagl_za_id")
	private SpecialCase SpecialCase;
	/**
	 * 关系线的起始人员
	 */
	@ManyToOne
	@JoinColumn(name="agl_zasary_id")
	private SpecialCaseInvolvedPerson fromPerson;
	/**
	 * 关系线的结束人员
	 */
	@ManyToOne
	@JoinColumn(name="agl_zasary_id2")
	private SpecialCaseInvolvedPerson toPerson;
	/**
	 * 关系类型
	 */
	@Column(name = "gxlx")
	private String type;
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	/**
	 * 更新人
	 */
	@ManyToOne
	@JoinColumn(name = "ryid")
	private Person updatePerson;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SpecialCase getSpecialCase() {
		return SpecialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		SpecialCase = specialCase;
	}

	public SpecialCaseInvolvedPerson getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(SpecialCaseInvolvedPerson fromPerson) {
		this.fromPerson = fromPerson;
	}

	public SpecialCaseInvolvedPerson getToPerson() {
		return toPerson;
	}

	public void setToPerson(SpecialCaseInvolvedPerson toPerson) {
		this.toPerson = toPerson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Person getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Person updatePerson) {
		this.updatePerson = updatePerson;
	}

}