package com.taiji.pubsec.szpt.score.util.hrp.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreRule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2950618594481521052L;
	
	public ScoreRule(Double alertPoint){
		this.alertPoint = alertPoint ;
	}

	/**
	 * 最低预警分数值
	 */
	private Double alertPoint ;
	
	/**
	 * 各项的规则
	 */
	private List<RuleDetail> ruleDetails = new ArrayList<RuleDetail>() ;



	public Double getAlertPoint() {
		return alertPoint;
	}

	public void setAlertPoint(Double alertPoint) {
		this.alertPoint = alertPoint;
	}

	public List<RuleDetail> getRuleDetails() {
		return ruleDetails;
	}

	public void setRuleDetails(List<RuleDetail> ruleDetails) {
		this.ruleDetails = ruleDetails;
	}

	
	
	
}
