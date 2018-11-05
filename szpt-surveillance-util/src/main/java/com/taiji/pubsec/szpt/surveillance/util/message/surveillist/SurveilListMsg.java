package com.taiji.pubsec.szpt.surveillance.util.message.surveillist;

import java.io.Serializable;

public class SurveilListMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3023487689698863582L;
	
	private String surveilListCode ;
	
	private String operationType ;

	public String getSurveilListCode() {
		return surveilListCode;
	}

	public void setSurveilListCode(String surveilListCode) {
		this.surveilListCode = surveilListCode;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
}
