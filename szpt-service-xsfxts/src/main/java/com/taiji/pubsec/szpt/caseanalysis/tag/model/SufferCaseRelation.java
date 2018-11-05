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
 * 报案人/受害人关联表
 * 
 * @author wangfx
 */
@Entity
@Table(name="t_xsajfx_shrgx")
public class SufferCaseRelation {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	/**
	 * 人员编号
	 */
	@ManyToOne
	@JoinColumn(name="rybh")
	@Index(name="index_t_xsajfx_shrgx_rybh")
	private CriminalPerson criminalPerson;
	
	/**
	 * 案件编号
	 */
	@ManyToOne
	@JoinColumn(name="ajbh")
	@Index(name="index_t_xsajfx_shrgx_ajbh")
	private CriminalBasicCase basicCase;
	
	/**
	 * 与受害人关系名称
	 */
	@Column(name="yshrgx", length = 50)
	private String repnexusdis;
	
	/**
	 * 涉案类型名称
	 */
	@Column(name="salx", length = 50)
	private String relation;
	
	/**
	 * 受害形式名称
	 */
	@Column(name="shxs", length = 50)
	private String reportMode;
	
	/**
	 * 受害形式2
	 */
	@Column(name="shxs2", length = 50)
	private String reportMode2;
	
	/**
	 * 受害程度名称
	 */
	@Column(name="shcd", length = 4000)
	private String receivePSN;
	
	/**
	 * 受侵害时间上限
	 */
	@Column(name="shsjsx")
	@Temporal(TemporalType.TIMESTAMP)
	private Date aggrievedTimeLimit;
	
	/**
	 * 受侵害时间下限
	 */
	@Column(name="shsjxx")
	@Temporal(TemporalType.TIMESTAMP)
	private Date aggrievedTimeLower;
	
	/**
	 * 致死伤原因名称
	 */
	@Column(name="zssyy", length = 50)
	private String injuryCause;
	
	/**
	 * 致死伤工具名称
	 */
	@Column(name="zssgj", length = 50)
	private String injuryTools;
	
	/**
	 * 损伤及病理特征
	 */
	@Column(name="ssjbltz", length = 3000)
	private String damageFeature;
	
	/**
	 * 备注
	 */
	@Column(name="bz", length = 750)
	private String annex;
	
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
	

	public CriminalPerson getCriminalPerson() {
		return criminalPerson;
	}

	public void setCriminalPerson(CriminalPerson criminalPerson) {
		this.criminalPerson = criminalPerson;
	}

	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}

	public String getRepnexusdis() {
		return repnexusdis;
	}

	public void setRepnexusdis(String repnexusdis) {
		this.repnexusdis = repnexusdis;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}

	public String getReceivePSN() {
		return receivePSN;
	}

	public void setReceivePSN(String receivePSN) {
		this.receivePSN = receivePSN;
	}

	public Date getAggrievedTimeLimit() {
		return aggrievedTimeLimit;
	}

	public void setAggrievedTimeLimit(Date aggrievedTimeLimit) {
		this.aggrievedTimeLimit = aggrievedTimeLimit;
	}

	public Date getAggrievedTimeLower() {
		return aggrievedTimeLower;
	}

	public void setAggrievedTimeLower(Date aggrievedTimeLower) {
		this.aggrievedTimeLower = aggrievedTimeLower;
	}

	public String getInjuryCause() {
		return injuryCause;
	}

	public void setInjuryCause(String injuryCause) {
		this.injuryCause = injuryCause;
	}

	public String getInjuryTools() {
		return injuryTools;
	}

	public void setInjuryTools(String injuryTools) {
		this.injuryTools = injuryTools;
	}

	public String getDamageFeature() {
		return damageFeature;
	}

	public void setDamageFeature(String damageFeature) {
		this.damageFeature = damageFeature;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
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

	public String getReportMode2() {
		return reportMode2;
	}

	public void setReportMode2(String reportMode2) {
		this.reportMode2 = reportMode2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}