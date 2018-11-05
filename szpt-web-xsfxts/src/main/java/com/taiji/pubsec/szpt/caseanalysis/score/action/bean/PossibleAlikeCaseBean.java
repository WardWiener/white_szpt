package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;

/**
 * 可能的串并案Bean
 * 
 * @author WangLei
 *
 */
public class PossibleAlikeCaseBean extends CaseTagBean{

	private String minScore;// 结果分值
	
	private String scoreTemplateName;// 模版名称
	
	private String scoreTemplateMinScore;// 模版最低分数
	
	private boolean isIgnored;// 是否忽略

	public String getMinScore() {
		return minScore;
	}

	public void setMinScore(String minScore) {
		this.minScore = minScore;
	}

	public String getScoreTemplateName() {
		return scoreTemplateName;
	}

	public void setScoreTemplateName(String scoreTemplateName) {
		this.scoreTemplateName = scoreTemplateName;
	}

	public boolean isIgnored() {
		return isIgnored;
	}

	public void setIgnored(boolean isIgnored) {
		this.isIgnored = isIgnored;
	}

	public String getScoreTemplateMinScore() {
		return scoreTemplateMinScore;
	}

	public void setScoreTemplateMinScore(String scoreTemplateMinScore) {
		this.scoreTemplateMinScore = scoreTemplateMinScore;
	}
	
	
}
