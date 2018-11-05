package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;

/**
 * 案件打标对比Bean
 * 
 * @author WangLei
 *
 */
public class CaseTagCompareBean extends CaseTagBean{

	private String handlePolice;// 办案人
	
	private String caseStateName;// 案件状态名称
	
	private String suspectName;// 嫌疑人名称
	
	

	public String getHandlePolice() {
		return handlePolice;
	}

	public void setHandlePolice(String handlePolice) {
		this.handlePolice = handlePolice;
	}

	public String getCaseStateName() {
		return caseStateName;
	}

	public void setCaseStateName(String caseStateName) {
		this.caseStateName = caseStateName;
	}

	public String getSuspectName() {
		return suspectName;
	}

	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}

	
	
}
