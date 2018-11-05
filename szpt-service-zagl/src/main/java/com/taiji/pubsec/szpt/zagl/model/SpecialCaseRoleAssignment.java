package com.taiji.pubsec.szpt.zagl.model;

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
 * 专案人员角色分配
 * @author 
 */
@Entity
@Table(name="t_zagl_zaryjsfp")
public class SpecialCaseRoleAssignment {

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
	 * 是否置顶(1 置顶  0  不置顶)
	 */
	@Column(name = "sfzd")
	private String isStick;
	/**
	 * 人员
	 */
	@ManyToOne
	@JoinColumn(name = "ryid")
	private Person person ;
	/**
	 * 专案角色
	 */
	@ManyToOne
	@JoinColumn(name="zagl_zajs_id")
	public SpecialCaseRole role;
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SpecialCaseRole getRole() {
		return role;
	}

	public void setRole(SpecialCaseRole role) {
		this.role = role;
	}

	public SpecialCase getSpecialCase() {
		return specialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getIsStick() {
		return isStick;
	}

	public void setIsStick(String isStick) {
		this.isStick = isStick;
	}

}