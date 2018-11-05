package com.taiji.pubsec.szpt.httpinterface.action.bean;

import java.util.Date;

public class FeedBackActionBean {

	private String id;
	
	private String feedbackContent;	//反馈内容
	
	private Date feedbackTime;	//反馈时间
	
	private String feedbackPeopleId;	//反馈人id
	
	private String feedbackPeopleName;	//反馈人姓名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackPeopleId() {
		return feedbackPeopleId;
	}

	public void setFeedbackPeopleId(String feedbackPeopleId) {
		this.feedbackPeopleId = feedbackPeopleId;
	}

	public String getFeedbackPeopleName() {
		return feedbackPeopleName;
	}

	public void setFeedbackPeopleName(String feedbackPeopleName) {
		this.feedbackPeopleName = feedbackPeopleName;
	}
	
	
}
