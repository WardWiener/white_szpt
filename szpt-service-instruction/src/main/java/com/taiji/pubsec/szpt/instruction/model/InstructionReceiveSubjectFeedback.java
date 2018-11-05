package com.taiji.pubsec.szpt.instruction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 指令接收主体反馈
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_zlgl_zljsztfk")
public class InstructionReceiveSubjectFeedback {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	@Column(name = "fknr", length = 1000)
	private String feedbackContent;	//反馈内容
	
	@Column(name = "fksj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date feedbackTime;	//反馈时间
	
	@Column(name = "fkrid")
	private String feedbackPeopleId;	//反馈人id
	
	@Column(name = "fkrxm")
	private String feedbackPeopleName;	//反馈人姓名
	
	@Column(name = "czdxlx")
	private String relateObjectType;	//操作对象类型
	
	@Column(name = "czdxid")
	private String relateObjectId;	//操作对象ID
	
	@Column(name = "ewfknr")
	private String otherFeedbackContent;	//额外的反馈内容
	
	@ManyToOne
	@JoinColumn(name = "zljszt_id")
	private InstructionReceiveSubject instructionReceiveSubject;	//指令接收主题

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

	public InstructionReceiveSubject getInstructionReceiveSubject() {
		return instructionReceiveSubject;
	}

	public void setInstructionReceiveSubject(InstructionReceiveSubject instructionReceiveSubject) {
		this.instructionReceiveSubject = instructionReceiveSubject;
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

	public String getOtherFeedbackContent() {
		return otherFeedbackContent;
	}

	public void setOtherFeedbackContent(String otherFeedbackContent) {
		this.otherFeedbackContent = otherFeedbackContent;
	}
}
