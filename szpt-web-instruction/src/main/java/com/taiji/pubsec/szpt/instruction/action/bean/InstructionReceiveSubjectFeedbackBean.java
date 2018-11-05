package com.taiji.pubsec.szpt.instruction.action.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 指令接收主题反馈
 *
 */
public class InstructionReceiveSubjectFeedbackBean {

	private String id;

	private String feedbackContent;// 反馈内容

	private Long feedbackTime;// 反馈时间

	private String feedbackPeopleId;// 反馈人id

	private String feedbackPeopleName;// 反馈人姓名

	private String relateObjectType;// 操作对象类型

	private String relateObjectId;// 操作对象ID

	private String otherFeedbackContent;// 额外的反馈内容

	private String receiveSubjectType;// 接收主体类型

	private String receiveSubjectId;// 接收主体id
	
	private String receiveSubjectName;// 接收主体名称

	private Long receiveTime;// 接收时间

	private String status;// 状态

	private String instructionId;// 指令id
	
	private List<Map<String, String>> resultSendUnits = new ArrayList<Map<String, String>>();// 研判结果下发单位列表，Map包含“code”:单位编码，“name”:单位名称

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

	public Long getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getOtherFeedbackContent() {
		return otherFeedbackContent;
	}

	public void setOtherFeedbackContent(String otherFeedbackContent) {
		this.otherFeedbackContent = otherFeedbackContent;
	}

	public String getRelateObjectType() {
		return relateObjectType;
	}

	public void setRelateObjectType(String relateObjectType) {
		this.relateObjectType = relateObjectType;
	}

	public String getRelateObjectId() {
		return relateObjectId;
	}

	public void setRelateObjectId(String relateObjectId) {
		this.relateObjectId = relateObjectId;
	}

	public String getReceiveSubjectType() {
		return receiveSubjectType;
	}

	public void setReceiveSubjectType(String receiveSubjectType) {
		this.receiveSubjectType = receiveSubjectType;
	}

	public String getReceiveSubjectId() {
		return receiveSubjectId;
	}

	public void setReceiveSubjectId(String receiveSubjectId) {
		this.receiveSubjectId = receiveSubjectId;
	}

	public Long getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Long receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstructionId() {
		return instructionId;
	}

	public void setInstructionId(String instructionId) {
		this.instructionId = instructionId;
	}

	public String getReceiveSubjectName() {
		return receiveSubjectName;
	}

	public void setReceiveSubjectName(String receiveSubjectName) {
		this.receiveSubjectName = receiveSubjectName;
	}

	public List<Map<String, String>> getResultSendUnits() {
		return resultSendUnits;
	}

	public void setResultSendUnits(List<Map<String, String>> resultSendUnits) {
		this.resultSendUnits = resultSendUnits;
	}
	
}
