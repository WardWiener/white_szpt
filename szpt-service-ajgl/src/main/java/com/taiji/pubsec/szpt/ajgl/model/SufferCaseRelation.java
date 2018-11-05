package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalPersonService;

/**
 * 报案人/受害人人员案件关联表
 * @author wangfx
 * @version 1.0
 * @created 10-8月-2016 15:04:08
 */
@Entity
@Table(name = "t_ajgl_ajshrrygx")
public class SufferCaseRelation {
	
	@Id
	private String id;

	/**
	 * 备注
	 */
	@Column(length = 4000)
	private String annex;
	/**
	 * 录入人
	 */
	private String inputPerson;
	/**
	 * 录入时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
	/**
	 * 涉案类型
	 */
	private String relation;
	/**
	 * 受害程度
	 */
	private String receivePSN;
	/**
	 * 受害形式
	 */
	private String reportMode;
	/**
	 * 受害形式2
	 */
	private String reportMode2;
	/**
	 * 受侵害时间上限
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date aggrievedTimeLimit;
	/**
	 * 受侵害时间下限
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date aggrievedTimeLower;
	/**
	 * 损伤及病理特征
	 */
	private String damageFeature;
	/**
	 * 修改人
	 */
	private String modifiedPerson;
	/**
	 * 修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;
	/**
	 * 与受害人关系
	 */
	private String repnexusdis;
	/**
	 * 致死伤工具
	 */
	private String injuryTools;
	/**
	 * 致死伤原因
	 */
	private String injuryCause;
	/**
	 * 时间戳
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updateTime;
	
//	/**
//	 * 人员编号
//	 */
//	@ManyToOne
//	@JoinColumn(name = "person_id")
//	private CriminalPerson person;
	private String person_id;	//人员编号
	
//	/**
//	 * 案件编号
//	 */
//	@ManyToOne
//	@JoinColumn(name = "case_id")
//	private CriminalBasicCase basicCase;
	private String case_id;	//案件编号
	
	public String getAnnex() {
		return annex;
	}
	public String getInputPerson() {
		return inputPerson;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public String getRelation() {
		return relation;
	}
	public String getReceivePSN() {
		return receivePSN;
	}
	public String getReportMode() {
		return reportMode;
	}
	public String getReportMode2() {
		return reportMode2;
	}
	public Date getAggrievedTimeLimit() {
		return aggrievedTimeLimit;
	}
	public Date getAggrievedTimeLower() {
		return aggrievedTimeLower;
	}
	public String getDamageFeature() {
		return damageFeature;
	}
	public String getModifiedPerson() {
		return modifiedPerson;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public String getRepnexusdis() {
		return repnexusdis;
	}
	public String getInjuryTools() {
		return injuryTools;
	}
	public String getInjuryCause() {
		return injuryCause;
	}
	public void setAnnex(String annex) {
		this.annex = annex;
	}
	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public void setReceivePSN(String receivePSN) {
		this.receivePSN = receivePSN;
	}
	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}
	public void setReportMode2(String reportMode2) {
		this.reportMode2 = reportMode2;
	}
	public void setAggrievedTimeLimit(Date aggrievedTimeLimit) {
		this.aggrievedTimeLimit = aggrievedTimeLimit;
	}
	public void setAggrievedTimeLower(Date aggrievedTimeLower) {
		this.aggrievedTimeLower = aggrievedTimeLower;
	}
	public void setDamageFeature(String damageFeature) {
		this.damageFeature = damageFeature;
	}
	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public void setRepnexusdis(String repnexusdis) {
		this.repnexusdis = repnexusdis;
	}
	public void setInjuryTools(String injuryTools) {
		this.injuryTools = injuryTools;
	}
	public void setInjuryCause(String injuryCause) {
		this.injuryCause = injuryCause;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public CriminalPerson getPerson(){
		ICriminalPersonService criminalPersonService = (ICriminalPersonService) SpringContextUtil.getBean("criminalPersonService");
		CriminalPerson criminalPerson = criminalPersonService.findById(person_id);
		return criminalPerson;
	}
	
	public CriminalBasicCase getBasicCase(){
		ICriminalCaseService criminalCaseService = (ICriminalCaseService) SpringContextUtil.getBean("criminalCaseService");
		CriminalBasicCase basicCase = criminalCaseService.findCriminalCaseByCaseId(case_id);
		return basicCase;
	}
	
}