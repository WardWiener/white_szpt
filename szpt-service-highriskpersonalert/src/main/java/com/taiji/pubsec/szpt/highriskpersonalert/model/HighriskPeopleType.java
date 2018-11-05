package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.io.Serializable;
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

/**
 * 高危人员类型
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_gwrylx")
public class HighriskPeopleType implements Serializable{	

	private static final long serialVersionUID = 8397035508985320653L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 人员类型编码
	 */
	@Column(name = "rylx")
	private String peopleType;
	
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/**
	 * 高危人员id
	 */
	@ManyToOne
	@JoinColumn(name = "gwry_id")
	private HighriskPerson highriskPerson;
	
	/**
	 * 高危人员的前科类型
	 */
	@OneToMany(mappedBy = "highriskPeopleType")
	private Set<HighriskCriminalRecord> highriskCriminalRecords = new HashSet<HighriskCriminalRecord>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public HighriskPerson getHighriskPerson() {
		return highriskPerson;
	}

	public void setHighriskPerson(HighriskPerson highriskPerson) {
		this.highriskPerson = highriskPerson;
	}

	public Set<HighriskCriminalRecord> getHighriskCriminalRecords() {
		return highriskCriminalRecords;
	}

	public void setHighriskCriminalRecords(Set<HighriskCriminalRecord> highriskCriminalRecords) {
		this.highriskCriminalRecords = highriskCriminalRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((peopleType == null) ? 0 : peopleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighriskPeopleType other = (HighriskPeopleType) obj;
		if (peopleType == null) {
			if (other.peopleType != null)
				return false;
		} else if (!peopleType.equals(other.peopleType))
			return false;
		return true;
	}



	
}
