package com.taiji.pubsec.szpt.zhzats.bean;

/**
 * 处警反馈Bean
 * 
 * @author WangLei
 *
 */
public class CjFeedbackBean {

	private String id;
	
	private String fkOrderCellId ;// 反馈的指挥单元id
	
	private String fkOrderCellCode ;// 反馈的指挥单元编码
	
	private String fkOrderCellName ;// 反馈的指挥单元名称
	
	private String fkPerson ;// 反馈人姓名
	
	private Long feedbackTime ;// 反馈时间
	
	private String content ;// 反馈内容

	private String factCjId ;// 处警信息ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkOrderCellId() {
		return fkOrderCellId;
	}

	public void setFkOrderCellId(String fkOrderCellId) {
		this.fkOrderCellId = fkOrderCellId;
	}

	public String getFkOrderCellCode() {
		return fkOrderCellCode;
	}

	public void setFkOrderCellCode(String fkOrderCellCode) {
		this.fkOrderCellCode = fkOrderCellCode;
	}

	public String getFkOrderCellName() {
		return fkOrderCellName;
	}

	public void setFkOrderCellName(String fkOrderCellName) {
		this.fkOrderCellName = fkOrderCellName;
	}

	public String getFkPerson() {
		return fkPerson;
	}

	public void setFkPerson(String fkPerson) {
		this.fkPerson = fkPerson;
	}

	public Long getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Long feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFactCjId() {
		return factCjId;
	}

	public void setFactCjId(String factCjId) {
		this.factCjId = factCjId;
	}
	
	
}
