package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.ArrayList;
import java.util.List;

public class HrpScoreResult {
	
	/**
	 * 高危人id
	 */
	private String hrpId ;

	/**
	 * 总分
	 */
	private Double totalScore ;
	
	private List<HrpScoreResultDetail> hrpScoreResultDetails = new ArrayList<HrpScoreResultDetail>();

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public List<HrpScoreResultDetail> getHrpScoreResultDetails() {
		return hrpScoreResultDetails;
	}

	public void setHrpScoreResultDetails(List<HrpScoreResultDetail> hrpScoreResultDetails) {
		this.hrpScoreResultDetails = hrpScoreResultDetails;
	}

	public String getHrpId() {
		return hrpId;
	}

	public void setHrpId(String hrpId) {
		this.hrpId = hrpId;
	}
	
	
	
}
