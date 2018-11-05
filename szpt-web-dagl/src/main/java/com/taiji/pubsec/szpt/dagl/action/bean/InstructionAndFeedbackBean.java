package com.taiji.pubsec.szpt.dagl.action.bean;

import java.util.Set;


public class InstructionAndFeedbackBean {
	
	private String id; //指令下发或指令反馈的id
	
	private Long createTimeLong; //指令下发创建时间
	
	private String createDate; //创建时间 -- 日期
	
	private String createTime; //创建时间 -- 时分秒
	
	private String content; //指令下发内容
	
	private String  relatedObjectId;//关联主题id
	
	private String relateObjectContent;	//关联主题内容
	
	private String createPeopleId;	//创建人id
	
	private String createDepartment;//下发单位
	
	private String  acceptDepartments;//接受单位   ---- 这个字段没有
	
	private String feedbackDepartment;//反馈单位
	
	private String feedbackContent;	//反馈内容
	
	private String feedbackTime;//反馈时间
	
	private String feedbackGiveDepartment;	//反馈给的单位
	
	private String acceptObjectId;//接收主体id
	
	private Boolean isFeedBack;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCreateTimeLong() {
		return createTimeLong;
	}

	public void setCreateTimeLong(Long createTimeLong) {
		this.createTimeLong = createTimeLong;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRelatedObjectId() {
		return relatedObjectId;
	}

	public void setRelatedObjectId(String relatedObjectId) {
		this.relatedObjectId = relatedObjectId;
	}

	public String getRelateObjectContent() {
		return relateObjectContent;
	}

	public void setRelateObjectContent(String relateObjectContent) {
		this.relateObjectContent = relateObjectContent;
	}

	public String getCreatePeopleId() {
		return createPeopleId;
	}

	public void setCreatePeopleId(String createPeopleId) {
		this.createPeopleId = createPeopleId;
	}

	public String getCreateDepartment() {
		return createDepartment;
	}

	public void setCreateDepartment(String createDepartment) {
		this.createDepartment = createDepartment;
	}



	public String getAcceptDepartments() {
		return acceptDepartments;
	}

	public void setAcceptDepartments(String acceptDepartments) {
		this.acceptDepartments = acceptDepartments;
	}

	public String getFeedbackDepartment() {
		return feedbackDepartment;
	}

	public void setFeedbackDepartment(String feedbackDepartment) {
		this.feedbackDepartment = feedbackDepartment;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getFeedbackGiveDepartment() {
		return feedbackGiveDepartment;
	}

	public void setFeedbackGiveDepartment(String feedbackGiveDepartment) {
		this.feedbackGiveDepartment = feedbackGiveDepartment;
	}

	public Boolean getIsFeedBack() {
		return isFeedBack;
	}

	public void setIsFeedBack(Boolean isFeedBack) {
		this.isFeedBack = isFeedBack;
	}

	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getAcceptObjectId() {
		return acceptObjectId;
	}

	public void setAcceptObjectId(String acceptObjectId) {
		this.acceptObjectId = acceptObjectId;
	}
	
	
}
