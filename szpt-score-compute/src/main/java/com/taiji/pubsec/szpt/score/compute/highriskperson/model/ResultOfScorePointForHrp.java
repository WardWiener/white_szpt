/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年11月14日 下午7:43:01
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
@Table(name = "t_scorecompute_hrpscore_scorepoint_result")
public class ResultOfScorePointForHrp {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "hrpId", length = 64, nullable = false)
	private String hrpId;
	
	@Column(name = "scorePointId", length = 64, nullable = false)
	private String scorePointId;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
