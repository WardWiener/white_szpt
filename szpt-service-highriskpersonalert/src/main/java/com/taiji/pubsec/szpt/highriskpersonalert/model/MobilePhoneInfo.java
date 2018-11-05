package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.io.Serializable;
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
import javax.sql.rowset.serial.SerialArray;

import org.hibernate.annotations.GenericGenerator;

/**
 * 终端设备信息 
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_yddhxx")
public class MobilePhoneInfo implements Serializable{
	
	private static final long serialVersionUID = -2244824054071083101L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 电话号码
	 */
	@Column(name = "dhhm")
	private String number;
	
	/**
	 * IMEI号
	 */
	@Column(name = "imeih")
	private String imei;
	
	/**
	 * MAC地址
	 */
	@Column(name = "macdh")
	private String mac;
	
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	/**
	 * 高危人员
	 */
	@ManyToOne
	@JoinColumn(name = "gwry_id")
	private HighriskPerson highriskPerson;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public HighriskPerson getHighriskPerson() {
		return highriskPerson;
	}

	public void setHighriskPerson(HighriskPerson highriskPerson) {
		this.highriskPerson = highriskPerson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imei == null) ? 0 : imei.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		MobilePhoneInfo other = (MobilePhoneInfo) obj;
		if (imei == null) {
			if (other.imei != null)
				return false;
		} else if (!imei.equals(other.imei))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	

}
