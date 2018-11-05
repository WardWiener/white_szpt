/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年11月14日 下午7:34:16
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
@Table(name = "t_scorecompute_hrpscore_result")
public class ResultOfScoreForHrp {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "hrpId", length = 64, nullable = false)
	private String hrpId;
	
	@Column(name = "scoreTaskId", length = 64, nullable = false)
	private String scoreTaskId ;
	
	@Column(name = "score", nullable = false)
	private Double score;

	public String getHrpId() {
		return hrpId;
	}

	public String getScoreTaskId() {
		return scoreTaskId;
	}



	public void setScoreTaskId(String scoreTaskId) {
		this.scoreTaskId = scoreTaskId;
	}



	public void setHrpId(String hrpId) {
		this.hrpId = hrpId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
