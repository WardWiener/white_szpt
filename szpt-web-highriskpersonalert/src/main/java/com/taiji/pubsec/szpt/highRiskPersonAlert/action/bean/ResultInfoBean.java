package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.ArrayList;
import java.util.List;

public class ResultInfoBean {

	
	private List<String> hitInfos = new ArrayList<String>();
	
	private String result;

	public List<String> getHitInfos() {
		return hitInfos;
	}

	public void setHitInfos(List<String> hitInfos) {
		this.hitInfos = hitInfos;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	
	
}
