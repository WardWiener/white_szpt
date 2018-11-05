/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年11月15日 上午10:10:54
 */
package com.taiji.pubsec.szpt.score.compute.highriskperson.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yucy
 *
 */
@Entity
@Table(name = "t_scorecompute_hrpscore_rule_result")
public class ResultOfScoreRuleForHrp {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "hrpid", length = 64, nullable = false)
	private String hrpId;
	
	@Column(name = "scorepointid", length = 64, nullable = false)
	private String scorePointId;
	
	@Column(name = "scoreruleid", length = 64, nullable = false)
	private String scoreRuleId;
	
	@Column(name = "input", length = 1024)
	private String input ;
	
	@Column(name = "otherResults", length = 1024)
	private String otherResults ;
	
	@Column(name = "description")
	private String description ;
	
	@Column(name = "score", nullable = false)
	private Double score;

	public String getHrpId() {
		return hrpId;
	}

	public void setHrpId(String hrpId) {
		this.hrpId = hrpId;
	}

	public String getScorePointId() {
		return scorePointId;
	}

	public void setScorePointId(String scorePointId) {
		this.scorePointId = scorePointId;
	}

	public String getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(String scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOtherResults() {
		return otherResults;
	}

	public void setOtherResults(String otherResults) {
		this.otherResults = otherResults;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	
}
