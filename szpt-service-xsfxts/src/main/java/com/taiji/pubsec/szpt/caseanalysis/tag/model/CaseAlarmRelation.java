package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 案件警情关系表
 * 
 * @author WangLei
 *
 */
@Entity
@Table(name="t_xsajfx_aj_jq")
public class CaseAlarmRelation {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 案件编号
	 */
	@Column(name="ajbh", length = 50)
	@Index(name="index_t_xsajfx_aj_jq_ajbh")
	private String caseCode;
	
	/**
	 * 警情id
	 */
	@Column(name="jqid", length = 50)
	@Index(name="index_t_xsajfx_aj_jq_jqid")
	private String alarmId;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
