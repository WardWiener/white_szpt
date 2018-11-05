package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

public class HrpScoreResultDetail {

	/**
	 * 每一项的分数
	 */
	private Double score ;
	
	/**
	 * 其他类型的结果，json字符串
	 * 例：{"resultInfo":{"hitInfos":["刑事前科@危害国家安全案","刑事前科@危害公共安全案","刑事前科@危害国家安全案"]},"result":0.4}
	 */
	private String otherResults ;

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getOtherResults() {
		return otherResults;
	}

	public void setOtherResults(String otherResults) {
		this.otherResults = otherResults;
	}
	
	
}
