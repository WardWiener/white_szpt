package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 地缘性区域
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_gwry_dyxqyryxx")
public class GeographicalZonePeopleInfo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 地缘性区域的相关人员
	 */
	@Column(name = "xm")
	private String personName;
	
	/**
	 * 身份证号
	 */
	@Column(name = "sfzh")
	private String idCode;
	
	/**
	 * 户籍地
	 */
	@Column(name = "hjd")
	private String nationalAddress;
	
	/**
	 * 现住地
	 */
	@Column(name = "xzd")
	private String address;
	
	/**
	 * 手机号
	 */
	@Column(name = "sjh")
	private String telephoneNumber;
	
	/**
	 * 最后进入时间
	 */
	@Column(name = "zhjrsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastEntertime;
	
	/**
	 * 是否查控
	 */
	@Column(name = "sfck")
	private int isCheck;
	
	/**
	 * 查控时间
	 */
	@Column(name = "cksj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkTime;
	
	/**
	 * 地缘性地区
	 */
	@Column(name = "dyxdq")
	private String geographicalZones;

	/**
	 * 该区域常见作案类型
	 */
	@Column(name = "cjzalx")
	private String commomCaseType;
	
	/**
	 * 时间戳
	 */
	@Column(name = "updated_at")
	private Date updatedTime;
	
	/**
	 * 数据来源:流动人口，旅馆住宿，网吧
	 */
	@Column(name = "sjly")
	private String dataSource;
	
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

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getNationalAddress() {
		return nationalAddress;
	}

	public void setNationalAddress(String nationalAddress) {
		this.nationalAddress = nationalAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Date getLastEntertime() {
		return lastEntertime;
	}

	public void setLastEntertime(Date lastEntertime) {
		this.lastEntertime = lastEntertime;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getGeographicalZones() {
		return geographicalZones;
	}

	public void setGeographicalZones(String geographicalZones) {
		this.geographicalZones = geographicalZones;
	}

	public String getCommomCaseType() {
		return commomCaseType;
	}

	public void setCommomCaseType(String commomCaseType) {
		this.commomCaseType = commomCaseType;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
}
