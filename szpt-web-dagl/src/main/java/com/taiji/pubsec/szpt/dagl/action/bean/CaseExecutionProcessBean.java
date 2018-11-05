package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 案件执行过程Bean
 *
 */
public class CaseExecutionProcessBean{
	
	private String id;
	
	private String stepName;	//步骤名称
	
	private String stepExecuteTime;	//步骤执行时间
	
	private String isApproved;	//是否批准 ,1批准0不批准
	
	private String approver;	//批准人
	
	private String approveTime;	//批准时间 
	
	private String auditor;	//审核人
	
	private String auditTime;	//审核时间
	
	private String transactor;	//办理人
	
	private String transactTime;	//办理时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getStepExecuteTime() {
		return stepExecuteTime;
	}

	public void setStepExecuteTime(String stepExecuteTime) {
		this.stepExecuteTime = stepExecuteTime;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

}
