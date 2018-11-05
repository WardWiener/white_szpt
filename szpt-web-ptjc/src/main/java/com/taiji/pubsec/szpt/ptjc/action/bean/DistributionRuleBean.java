package com.taiji.pubsec.szpt.ptjc.action.bean;

public class DistributionRuleBean {

	private String alarmTypeCode;
	private String targetCode;
	private String targetName;
	private String targetId;
	private String targetType;
	private String blue;
	private String yellow;
	private String orange;
	private String red;
	private String advice;

	public DistributionRuleBean(){
		
	}
	
	public DistributionRuleBean(String alarmTypeCode, String targetCode,
			String targetName, String targetId, String targetType, String blue,
			String yellow, String orange, String red, String advice) {
		this.alarmTypeCode = alarmTypeCode;
		this.targetCode = targetCode;
		this.targetName = targetName;
		this.targetId = targetId;
		this.targetType = targetType;
		this.blue = blue;
		this.yellow = yellow;
		this.orange = orange;
		this.red = red;
		this.advice = advice;
	}

	public String getAlarmTypeCode() {
		return alarmTypeCode;
	}

	public void setAlarmTypeCode(String alarmTypeCode) {
		this.alarmTypeCode = alarmTypeCode;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getBlue() {
		return blue;
	}

	public void setBlue(String blue) {
		this.blue = blue;
	}

	public String getYellow() {
		return yellow;
	}

	public void setYellow(String yellow) {
		this.yellow = yellow;
	}

	public String getOrange() {
		return orange;
	}

	public void setOrange(String orange) {
		this.orange = orange;
	}

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
