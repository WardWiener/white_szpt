package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.ArrayList;
import java.util.List;

public class HrpScoreResultBean {

	/**
	 * 高危人id
	 */
	private String hrpId ;

	/**
	 * 总分
	 */
	private Double totalScore ;
	
	/**
	 * 命中的属性
	 */
	private List<HrpScoreResultDetailBean> hrpScoreResultDetails = new ArrayList<HrpScoreResultDetailBean>();

	public String getHrpId() {
		return hrpId;
	}

	public void setHrpId(String hrpId) {
		this.hrpId = hrpId;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public List<HrpScoreResultDetailBean> getHrpScoreResultDetails() {
		return hrpScoreResultDetails;
	}

	public void setHrpScoreResultDetails(
			List<HrpScoreResultDetailBean> hrpScoreResultDetails) {
		this.hrpScoreResultDetails = hrpScoreResultDetails;
	}
	
	
	
}
