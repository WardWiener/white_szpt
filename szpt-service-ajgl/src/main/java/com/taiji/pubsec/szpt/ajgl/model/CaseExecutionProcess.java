package com.taiji.pubsec.szpt.ajgl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 案件执行过程
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_ajgl_ajzxgc")
public class CaseExecutionProcess implements Serializable{
	
	private String caseId;	//案件id
	
	private String personId;	//嫌疑人
	@Id
	private String stepName;	//步骤名称
	
	private String stepNum;	//步骤序号
	
	private String paperNumber;	//文书文号
	
	@Id
	private String paperId;	//文书id	
	
	@Id
	private String paperType;	//文书类型
	
	private String issuedPerson;	//填发人
	
	private String issuedUnit;	//填发单位
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date issuedTime;	///填发时间
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date stepExecuteTime;	//步骤执行时间
	
	@Column(nullable = true)
	private Integer isApproved;	//是否批准 ,1批准0不批准
	
	private String approver;	//批准人
	
	@Column(name = "appoved_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approveTime;	//批准时间 
	
	private String auditor;	//审核人
	
	@Column(name = "audited_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;	//审核时间
	
	private String transactor;	//办理人
	
	@Column(name = "transacted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactTime;	//办理时间               
	
	/**
	 * 嫌疑人姓名
	 */
	private String suspectName;

	public Date getStepExecuteTime() {
		return stepExecuteTime;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public String getApprover() {
		return approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public String getAuditor() {
		return auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public String getTransactor() {
		return transactor;
	}

	public Date getTransactTime() {
		return transactTime;
	}

	public void setStepExecuteTime(Date stepExecuteTime) {
		this.stepExecuteTime = stepExecuteTime;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public void setTransactTime(Date transactTime) {
		this.transactTime = transactTime;
	}

//	public CriminalBasicCase getBasicCase() {
//		ICriminalCaseService criminalCaseService = (ICriminalCaseService) SpringContextUtil.getBean("criminalCaseService");
//		CriminalBasicCase basicCase = criminalCaseService.findCriminalCaseByCaseId(caseId);
//		return basicCase;
//	}

	public String getSuspectName() {
		return suspectName;
	}

	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}

	public String getCaseId() {
		return caseId;
	}

	public String getPersonId() {
		return personId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getPaperNumber() {
		return paperNumber;
	}

	public void setPaperNumber(String paperNumber) {
		this.paperNumber = paperNumber;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getIssuedPerson() {
		return issuedPerson;
	}

	public void setIssuedPerson(String issuedPerson) {
		this.issuedPerson = issuedPerson;
	}

	public String getIssuedUnit() {
		return issuedUnit;
	}

	public void setIssuedUnit(String issuedUnit) {
		this.issuedUnit = issuedUnit;
	}

	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public String getStepNum() {
		return stepNum;
	}

	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	
	
}
