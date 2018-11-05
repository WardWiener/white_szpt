package com.taiji.pubsec.szpt.zagl.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import org.hibernate.annotations.Index;
/**
 * 留言
 * @author dixiaofeng
 */
@Entity
@Table(name="t_zagl_zaly")
public class SpecialCaseMessage {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 专案
	 */
	@ManyToOne
	@JoinColumn(name="zagl_zabg_id")
	private SpecialCase specialCase;
	/**
	 * 内容
	 */
	@Column(name = "nr")
	private String content;
	/**
	 * 留言时间
	 */
	@Column(name = "lysj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	/**
	 * 留言人
	 */
	@ManyToOne
	@JoinColumn(name = "lyr_id")
	private Person createPerson;
	
	@OneToMany(mappedBy = "message")
	private Set<SpecialCaseMessageStickRecord> specialCaseMessageStickRecords = new HashSet<SpecialCaseMessageStickRecord>();

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Set<SpecialCaseMessageStickRecord> getSpecialCaseMessageStickRecords() {
		return specialCaseMessageStickRecords;
	}

	public void setSpecialCaseMessageStickRecords(Set<SpecialCaseMessageStickRecord> specialCaseMessageStickRecords) {
		this.specialCaseMessageStickRecords = specialCaseMessageStickRecords;
	}
	

}