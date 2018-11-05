package com.taiji.pubsec.szpt.score.util.hrp.pojo;

import java.util.ArrayList;
import java.util.List;

public class ScorePointInfo {

	private String key ;
	
	private Double weight ;
	
	private List<RuleDetail> ruleDetails = new ArrayList<RuleDetail>() ;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<RuleDetail> getRuleDetails() {
		return ruleDetails;
	}

	public void setRuleDetails(List<RuleDetail> ruleDetails) {
		this.ruleDetails = ruleDetails;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	
	
}
