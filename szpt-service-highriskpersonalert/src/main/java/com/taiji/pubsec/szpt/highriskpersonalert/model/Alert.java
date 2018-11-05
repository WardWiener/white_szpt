package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 预警记录
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_yjjl")
public class Alert {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 判重标识
	 */
	@Column(name="keyz")
	private String key;
	
	/**
	 * 重点人姓名，逗号隔开
	 */
	@Column(name = "zdrxm")
	private String personNames;
	
	/**
	 * 场所名称
	 */
	@Column(name = "csmc")
	private String place;
	
	/**
	 * 状态，0：未处理，1：忽略，2：已处理
	 */
	@Column(name = "zt")
	private String state;
	
	/**
	 * 预警内容
	 */
	@Column(name = "yjnr")
	private String warning;
	
	/**
	 * 预警时间
	 */
	@Column(name = "yjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/**
	 * 预警信息
	 */
	@OrderBy("targetType")
	@OneToMany(mappedBy = "alert", fetch=FetchType.EAGER)
	private Set<AlertInfo> alertInfos = new HashSet<AlertInfo>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonNames() {
		return personNames;
	}

	public void setPersonNames(String personNames) {
		this.personNames = personNames;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<AlertInfo> getAlertInfos() {
		return alertInfos;
	}

	public void setAlertInfos(Set<AlertInfo> alertInfos) {
		this.alertInfos = alertInfos;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

}
