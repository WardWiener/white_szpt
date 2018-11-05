package com.taiji.pubsec.szpt.dagl.action.bean;

public class FeedbackBean {
	private String feedbackType;// 反馈类型
	private String feedbackPerson;// 反馈人
	private String feedbackTime;// 反馈时间
	private String feedbackContent;// 反馈内容
	private String feedbackOrderCell;// 反馈的指挥单面名称

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getFeedbackPerson() {
		return feedbackPerson;
	}

	public void setFeedbackPerson(String feedbackPerson) {
		this.feedbackPerson = feedbackPerson;
	}

	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getFeedbackOrderCell() {
		return feedbackOrderCell;
	}

	public void setFeedbackOrderCell(String feedbackOrderCell) {
		this.feedbackOrderCell = feedbackOrderCell;
	}
}
