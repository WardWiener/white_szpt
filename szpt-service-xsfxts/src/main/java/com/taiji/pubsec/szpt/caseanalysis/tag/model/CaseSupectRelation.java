package com.taiji.pubsec.szpt.caseanalysis.tag.model;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 涉案嫌疑人关联表
 * 
 * @author wangfx
 */
@Entity
@Table(name="t_xsajfx_xyrgx")
public class CaseSupectRelation {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 案件编号
	 */
	@ManyToOne
	@JoinColumn(name="ajbh")
	@Index(name="index_t_xsajfx_xyrgx_ajbh")
	private CriminalBasicCase basicCase;
	
	/**
	 * 人员编号
	 */
	@ManyToOne
	@JoinColumn(name="rybh")
	@Index(name="index_t_xsajfx_xyrgx_rybh")
	private CriminalPerson criminalPerson;
	
	/**
	 * 嫌疑人类型名称
	 */
	@Column(name="xyrlx", length = 50)
	private String suspectType;
	
	/**
	 * 案件角色名称
	 */
	@Column(name="ajjs", length = 50)
	private String crimeRole;
	
	/**
	 * 嫌疑依据
	 */
	@Column(name="xyyj", length = 50)
	private String suspiciongist;
	
	/**
	 * 违法事实及依据
	 */
	@Column(name="wfssyj", length = 4000)
	private String criRecord;
	
	/**
	 * 处理方式名称
	 */
	@Column(name="clfs", length = 50)
	private String approach;
	
	/**
	 * 人员状态名称
	 */
	@Column(name="ryzt", length = 50)
	private String personState;
	
	/**
	 * 抓获日期
	 */
	@Column(name="zhrq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date captureDate;
	
	/**
	 * 抓获过程
	 */
	@Column(name="zhjg", length = 4000)
	private String captureProcess;
	
	/**
	 * 录入人
	 */
	@Column(name="lrr", length = 50)
	private String inputPerson;
	
	/**
	 * 录入时间
	 */
	@Column(name="lrsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
	
	/**
	 * 修改人
	 */
	@Column(name="xgr", length = 50)
	private String modifiedPerson;
	
	/**
	 * 修改时间
	 */
	@Column(name="xgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;
	

	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}

	public CriminalPerson getCriminalPerson() {
		return criminalPerson;
	}

	public void setCriminalPerson(CriminalPerson criminalPerson) {
		this.criminalPerson = criminalPerson;
	}

	public String getSuspectType() {
		return suspectType;
	}

	public void setSuspectType(String suspectType) {
		this.suspectType = suspectType;
	}

	public String getCrimeRole() {
		return crimeRole;
	}

	public void setCrimeRole(String crimeRole) {
		this.crimeRole = crimeRole;
	}

	public String getCriRecord() {
		return criRecord;
	}

	public void setCriRecord(String criRecord) {
		this.criRecord = criRecord;
	}

	public String getSuspiciongist() {
		return suspiciongist;
	}

	public void setSuspiciongist(String suspiciongist) {
		this.suspiciongist = suspiciongist;
	}

	public String getApproach() {
		return approach;
	}

	public void setApproach(String approach) {
		this.approach = approach;
	}

	public String getPersonState() {
		return personState;
	}

	public void setPersonState(String personState) {
		this.personState = personState;
	}

	public Date getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}

	public String getCaptureProcess() {
		return captureProcess;
	}

	public void setCaptureProcess(String captureProcess) {
		this.captureProcess = captureProcess;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}