package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员轨迹
 * @author wangfx
 *
 */
public abstract class AbstractPersonnelTrackInfoBean implements PersonTrackInfo {

	private String id;
	
	/**
	 * 人员名称
	 */
	private String personName;

	/**
	 * 人员身份账号
	 */
	private String personIdcode;

	/**
	 * 户籍地址
	 */
	private String personRegistryAddress;

	/**
	 * 时间戳
	 */
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonIdcode() {
		return personIdcode;
	}

	public void setPersonIdcode(String personIdcode) {
		this.personIdcode = personIdcode;
	}

	public String getPersonRegistryAddress() {
		return personRegistryAddress;
	}

	public void setPersonRegistryAddress(String personRegistryAddress) {
		this.personRegistryAddress = personRegistryAddress;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
