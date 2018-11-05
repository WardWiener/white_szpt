package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;


public class HrpScoreResultDetailBean {

	
	private Double score;	//得分
	
	private ResultInfoBean ResultInfoBean ;	//命中属性

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public ResultInfoBean getResultInfoBean() {
		return ResultInfoBean;
	}

	public void setResultInfoBean(ResultInfoBean resultInfoBean) {
		ResultInfoBean = resultInfoBean;
	}


	
	
}
