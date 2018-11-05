package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;

/**
 * 案件分析对比结果Bean
 * 
 * @author WangLei
 *
 */
public class CaseAnalysisCompareBean extends CaseTagBean{

	private String handlePolice;// 办案人
	
	private String caseStateName;// 案件状态名称
	
	private String suspectName;// 嫌疑人名称
	
	private String minScore;// 串并案分值
	
	private List<String> knownCaseCodes = new ArrayList<String>();// 已知串并案编号集合
	
	private String scoreTemplateName;// 模版名称

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

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public List<String> getKnownCaseCodes() {
		return knownCaseCodes;
	}

	public void setKnownCaseCodes(List<String> knownCaseCodes) {
		this.knownCaseCodes = knownCaseCodes;
	}

	public String getScoreTemplateName() {
		return scoreTemplateName;
	}

	public void setScoreTemplateName(String scoreTemplateName) {
		this.scoreTemplateName = scoreTemplateName;
	}
	
	
}
