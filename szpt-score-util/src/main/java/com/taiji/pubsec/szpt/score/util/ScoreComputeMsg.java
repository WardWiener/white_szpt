package com.taiji.pubsec.szpt.score.util;

import java.io.Serializable;

/**
 * @author yucy
 *
 */
public class ScoreComputeMsg implements Serializable {

	private static final long serialVersionUID = 3474717183216859269L;

	private String computeProcessId;
	
	private Serializable scoreComputeObj;

	public String getComputeProcessId() {
		return computeProcessId;
	}

	public void setComputeProcessId(String computeProcessId) {
		this.computeProcessId = computeProcessId;
	}

	public Serializable getScoreComputeObj() {
		return scoreComputeObj;
	}

	public void setScoreComputeObj(Serializable scoreComputeObj) {
		this.scoreComputeObj = scoreComputeObj;
	}
	
}
