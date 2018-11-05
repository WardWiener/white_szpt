package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 高危人员人员类型
 *
 */
@Entity
@Table(name = "t_gwry_gwrylx")
public class HighriskPeopleTypeBean {
	
	private String id;
	
	/**
	 * 人员类型
	 */
	private String peopleType;
	
	private String peopleTypeName;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 高危人员的前科类型
	 */
	private List<HighriskCriminalRecordBean> highriskCriminalRecord;

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

	public String getPeopleTypeName() {
		return peopleTypeName;
	}

	public void setPeopleTypeName(String peopleTypeName) {
		this.peopleTypeName = peopleTypeName;
	}

	public List<HighriskCriminalRecordBean> getHighriskCriminalRecord() {
		return highriskCriminalRecord;
	}

	public void setHighriskCriminalRecord(
			List<HighriskCriminalRecordBean> highriskCriminalRecord) {
		this.highriskCriminalRecord = highriskCriminalRecord;
	}

}
