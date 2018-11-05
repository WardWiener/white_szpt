package com.taiji.pubsec.szpt.jz.httpclient;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResultBean {

	private boolean isSuccess;
	private String message;
	private Map<String, Object> results = new HashMap<String, Object>();

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResults() {
		return results;
	}

	public void setResults(Map<String, Object> results) {
		this.results = results;
	}

	public static ResultBean fromJson(JSONObject result) {
		ResultBean rb = new ResultBean();
		rb.setMessage(result.getString("code") + ""
				+ result.getString("message"));
		if (result.getString("code").equals("200")) {
			rb.setSuccess(true);
		} else {
			rb.setSuccess(false);
		}
		return rb;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
