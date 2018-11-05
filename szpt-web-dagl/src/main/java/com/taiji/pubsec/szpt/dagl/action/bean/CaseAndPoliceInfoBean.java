package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 案件警情关联的Bean
 * 
 * @author xinfan
 *
 */
public class CaseAndPoliceInfoBean {
	private String caseCode; // 案件编号
	private String policeInfoId; // 警情Id

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getPoliceInfoId() {
		return policeInfoId;
	}

	public void setPoliceInfoId(String policeInfoId) {
		this.policeInfoId = policeInfoId;
	}
}
