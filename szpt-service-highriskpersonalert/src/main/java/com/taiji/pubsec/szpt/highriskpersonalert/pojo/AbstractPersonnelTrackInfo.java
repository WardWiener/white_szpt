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
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractPersonnelTrackInfo implements PersonTrackInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 人员名称
	 */
	@Column(name = "rymc")
	private String personName;
	
	/**
	 * 人员身份证号
	 */
	@Column(name = "rysfzh")
	private String personIdcode;
	
	
	/**
	 * 户籍地址
	 */
	@Column(name = "hjdz")
	private String personRegistryAddress;
	
	/**
	 * 经度
	 */
	@Column(name = "jd")
	private String longitude;
	
	/**
	 * 纬度
	 */
	@Column(name = "wd")
	private String latitude;
	
	/**
	 * 时间戳
	 */
	@Column(name = "gxsj")
	@Temporal(TemporalType.TIMESTAMP)
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

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
