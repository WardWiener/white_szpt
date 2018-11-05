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
 * 专案涉案人员
 * @author 
 */
@Entity
@Table(name="t_zagl_zasary")
public class SpecialCaseInvolvedPerson {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	/**
	 * 创建人
	 */
	@ManyToOne
	@JoinColumn(name = "ryid")
	private Person createPerson;
	/**
	 * 户籍地址
	 */
	@Column(name = "hjdz")
	private String householdAddress;
	/**
	 * 户籍
	 */
	@Column(name = "hj")
	private String householdRegister;
	
	/**
	 * 身份证号
	 */
	@Column(name = "sfzh")
	private String idcard;
	/**
	 * 姓名
	 */
	@Column(name = "xm")
	private String name;
	/**
	 * 绰号
	 */
	@Column(name = "ch")
	private String nick;
	/**
	 * 手机号
	 */
	@Column(name = "sjh")
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getHouseholdRegister() {
		return householdRegister;
	}

	public void setHouseholdRegister(String householdRegister) {
		this.householdRegister = householdRegister;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SpecialCaseInvolvedPerson(){

	}


	public SpecialCase getSpecialCase() {
		return specialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}

}