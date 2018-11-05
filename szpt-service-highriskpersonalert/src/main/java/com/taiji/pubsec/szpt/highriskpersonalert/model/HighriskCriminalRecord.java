package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 高危人员的前科类型
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_gwryqklx")
public class HighriskCriminalRecord  implements Serializable{
	
	private static final long serialVersionUID = 7475137494534271871L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 前科类型编码
	 */
	@Column(name = "qklx")
	private String criminalRecord;
	
	/**
	 * 高危人员类型id
	 */
	@ManyToOne
	@JoinColumn(name = "gwrylx_id")
	private HighriskPeopleType highriskPeopleType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCriminalRecord() {
		return criminalRecord;
	}

	public void setCriminalRecord(String criminalRecord) {
		this.criminalRecord = criminalRecord;
	}

	public HighriskPeopleType getHighriskPeopleType() {
		return highriskPeopleType;
	}

	public void setHighriskPeopleType(HighriskPeopleType highriskPeopleType) {
		this.highriskPeopleType = highriskPeopleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((criminalRecord == null) ? 0 : criminalRecord.hashCode());
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
		HighriskCriminalRecord other = (HighriskCriminalRecord) obj;
		if (criminalRecord == null) {
			if (other.criminalRecord != null)
				return false;
		} else if (!criminalRecord.equals(other.criminalRecord))
			return false;
		return true;
	}



	
}
