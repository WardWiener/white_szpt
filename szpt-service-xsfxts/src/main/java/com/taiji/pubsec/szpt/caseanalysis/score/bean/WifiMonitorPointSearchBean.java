package com.taiji.pubsec.szpt.caseanalysis.score.bean;

import java.util.Date;

/**
 * wifi监控点查询Bean
 * 
 * @author WangLei
 *
 */
public class WifiMonitorPointSearchBean {

	private String code; // wifi监控点编码

	private String caseCode; // 案件编码

	private Date startTime; // 查询起始时间

	private Date endTime; // 查询结束时间

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
