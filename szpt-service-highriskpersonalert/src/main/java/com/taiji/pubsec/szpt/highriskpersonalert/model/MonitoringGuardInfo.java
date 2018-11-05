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
 * 监守信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_jsxx")
public class MonitoringGuardInfo {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 被监控人员姓名
	 */
	@Column(name = "bjkryxm")
	private String monitoredPersonName;
	
	/**
	 * 被监控人员身份证号
	 */
	@Column(name = "bjkrysfzh")
	private String monitoredPersonCode;
	
	/**
	 * 被监时间
	 */
	@Column(name = "bjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date monitoredTime;
	
	/**
	 * 被监类型
	 */
	@Column(name = "bjlx")
	private String monitoredType;
	
	/**
	 * 被监地点
	 */
	@Column(name = "bjdd")
	private String monitoredSite;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonitoredPersonName() {
		return monitoredPersonName;
	}

	public void setMonitoredPersonName(String monitoredPersonName) {
		this.monitoredPersonName = monitoredPersonName;
	}

	public String getMonitoredPersonCode() {
		return monitoredPersonCode;
	}

	public void setMonitoredPersonCode(String monitoredPersonCode) {
		this.monitoredPersonCode = monitoredPersonCode;
	}

	public Date getMonitoredTime() {
		return monitoredTime;
	}

	public void setMonitoredTime(Date monitoredTime) {
		this.monitoredTime = monitoredTime;
	}

	public String getMonitoredType() {
		return monitoredType;
	}

	public void setMonitoredType(String monitoredType) {
		this.monitoredType = monitoredType;
	}

	public String getMonitoredSite() {
		return monitoredSite;
	}

	public void setMonitoredSite(String monitoredSite) {
		this.monitoredSite = monitoredSite;
	}
	
	

}
