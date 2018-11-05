package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

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
 *
 */
public class HighriskCriminalRecordBean {
	
	private String id;
	
	private String criminalRecord;

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

}
