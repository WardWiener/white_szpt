package com.taiji.pubsec.szpt.zagl.model;

import javax.persistence.Entity;
import org.hibernate.annotations.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;




/**
 * 局领导角色分配人员，该角色人员自动添加到每一个专案的角色人员分配记录中
 * @author 
 */
@Entity
@Table(name="t_zagl_ryjsfp")
public class LeaderRoleAssignment {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	/**
	 * 人员
	 */
	@ManyToOne
	@JoinColumn(name = "ryid")
	private Person person;
	
	/**
	 * 专案角色
	 */
	@ManyToOne
	@JoinColumn(name="zagl_zajs_id")
	private SpecialCaseRole specialCaseRole;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SpecialCaseRole getSpecialCaseRole() {
		return specialCaseRole;
	}

	public void setSpecialCaseRole(SpecialCaseRole specialCaseRole) {
		this.specialCaseRole = specialCaseRole;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}