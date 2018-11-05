package com.taiji.pubsec.szpt.score.compute.highriskperson.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_scorecompute_hrpscore_scorepointconfig_result")
public class ResultOfScorePointConfigForHrp {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "hrpId", length = 64, nullable = false)
	private String hrpId;
	
	@Column(name = "scorePointConfigId", length = 64, nullable = false)
	private String scorePointConfigId;
	
	@Column(name = "scoreTaskId", length = 64, nullable = false)
	private String scoreTaskId ;
	
	@Column(name = "scorePointId", length = 64, nullable = false)
	private String scorePointId ;
	
	@Column(name = "description")
	private String description ;
	
	@Column(name = "weight", nullable = false)
	private Double weight;
	
	@Column(name = "scoreBeforeWeight", nullable = false)
	private Double scoreBeforeWeight;
	
	@Column(name = "scoreAfterWeight", nullable = false)
	private Double scoreAfterWeight;

	public String getHrpId() {
		return hrpId;
	}

	public void setHrpId(String hrpId) {
		this.hrpId = hrpId;
	}

	public String getScorePointConfigId() {
		return scorePointConfigId;
	}

	public void setScorePointConfigId(String scorePointConfigId) {
		this.scorePointConfigId = scorePointConfigId;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getScoreBeforeWeight() {
		return scoreBeforeWeight;
	}

	public void setScoreBeforeWeight(Double scoreBeforeWeight) {
		this.scoreBeforeWeight = scoreBeforeWeight;
	}

	public Double getScoreAfterWeight() {
		return scoreAfterWeight;
	}

	public void setScoreAfterWeight(Double scoreAfterWeight) {
		this.scoreAfterWeight = scoreAfterWeight;
	}

	public String getScoreTaskId() {
		return scoreTaskId;
	}

	public void setScoreTaskId(String scoreTaskId) {
		this.scoreTaskId = scoreTaskId;
	}

	public String getScorePointId() {
		return scorePointId;
	}

	public void setScorePointId(String scorePointId) {
		this.scorePointId = scorePointId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
