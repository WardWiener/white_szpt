package com.taiji.pubsec.szpt.caseanalysis.score.bean;

/**
 * 串并案分析模板规则，非映射类，只用于传递参数。
 * 
 * @author dixiaofeng
 */
public class RobberyTheftCaseScoreTemplateRule {

	private String item;// 评分项
	
	private String weight;// 权重
	
	private String rule;// 计算规则，多个值，隔开

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}