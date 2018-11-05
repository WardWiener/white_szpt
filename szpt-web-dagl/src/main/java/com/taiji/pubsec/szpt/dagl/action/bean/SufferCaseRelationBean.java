package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 报案人/受害人信息
 */
public class SufferCaseRelationBean {
	
	private String id;

	/**
	 * 备注
	 */
	private String annex;
	/**
	 * 录入人
	 */
	private String inputPerson;
	/**
	 * 录入时间
	 */
	private String inputTime;
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
	private String aggrievedTimeLimit;
	/**
	 * 受侵害时间下限
	 */
	private String aggrievedTimeLower;
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
	private String modifiedTime;
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
	private String updateTime;
	/**
	 * 人员编号
	 */
	private CriminalPersonBean criminalPerson = new CriminalPersonBean();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getInputTime() {
		return inputTime;
	}
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getReceivePSN() {
		return receivePSN;
	}
	public void setReceivePSN(String receivePSN) {
		this.receivePSN = receivePSN;
	}
	public String getReportMode() {
		return reportMode;
	}
	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}
	public String getReportMode2() {
		return reportMode2;
	}
	public void setReportMode2(String reportMode2) {
		this.reportMode2 = reportMode2;
	}
	public String getAggrievedTimeLimit() {
		return aggrievedTimeLimit;
	}
	public void setAggrievedTimeLimit(String aggrievedTimeLimit) {
		this.aggrievedTimeLimit = aggrievedTimeLimit;
	}
	public String getAggrievedTimeLower() {
		return aggrievedTimeLower;
	}
	public void setAggrievedTimeLower(String aggrievedTimeLower) {
		this.aggrievedTimeLower = aggrievedTimeLower;
	}
	public String getDamageFeature() {
		return damageFeature;
	}
	public void setDamageFeature(String damageFeature) {
		this.damageFeature = damageFeature;
	}
	public String getModifiedPerson() {
		return modifiedPerson;
	}
	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getRepnexusdis() {
		return repnexusdis;
	}
	public void setRepnexusdis(String repnexusdis) {
		this.repnexusdis = repnexusdis;
	}
	public String getInjuryTools() {
		return injuryTools;
	}
	public void setInjuryTools(String injuryTools) {
		this.injuryTools = injuryTools;
	}
	public String getInjuryCause() {
		return injuryCause;
	}
	public void setInjuryCause(String injuryCause) {
		this.injuryCause = injuryCause;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public CriminalPersonBean getCriminalPerson() {
		return criminalPerson;
	}
	public void setCriminalPerson(CriminalPersonBean criminalPerson) {
		this.criminalPerson = criminalPerson;
	}
	
}