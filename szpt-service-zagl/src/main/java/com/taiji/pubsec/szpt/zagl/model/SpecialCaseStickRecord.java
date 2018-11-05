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
 * 专案置顶记录
 * @author chengkai
 */
@Entity
@Table(name="t_zagl_zxzdjl")
public class SpecialCaseStickRecord {
	
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
	 * 置顶人
	 */
	@ManyToOne
	@JoinColumn(name = "zdr")
	private Person stickPerson;
	/**
	 * 置顶时间
	 */
	@Column(name = "cjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date stickTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public SpecialCase getSpecialCase() {
		return specialCase;
	}
	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}
	public Person getStickPerson() {
		return stickPerson;
	}
	public void setStickPerson(Person stickPerson) {
		this.stickPerson = stickPerson;
	}
	public Date getStickTime() {
		return stickTime;
	}
	public void setStickTime(Date stickTime) {
		this.stickTime = stickTime;
	}

	
}