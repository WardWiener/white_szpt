/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年11月14日 下午4:18:32
 */
package com.taiji.pubsec.scoreframework.monitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yucy
 *
 */
@Entity
@Table(name = "t_score_monitor_fail")
public class FailItem {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "businessKey", length = 64)
	private String businessKey;
	
	@Column(name = "businessType")
	private String businessType;
	
	@Column(name = "recordTime")
	private Date recordTime;
	
	@Column(name = "errorMessage", length=1024)
	private String errorMessage ;
	
	@ManyToOne
	@JoinColumn(name = "cpid")
	private ComputeProcess computeProcess;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public ComputeProcess getComputeProcess() {
		return computeProcess;
	}

	public void setComputeProcess(ComputeProcess computeProcess) {
		this.computeProcess = computeProcess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	
}
