package com.taiji.pubsec.szpt.dagl.bean;

public class AttentionPersonBean {

	private String id;
	
	/**
	 * 身份证号
	 */
	private String idNum;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 预警类型
	 */
	private String warningType;
	
	/**
	 * 人员类別
	 */
	private String personClass;
	
	/**
	 * 分数
	 */
	private String score;
	
	/**
	 * 关注时间
	 */
	private String attentionTime;
	
	/**
	 * 是否置顶
	 */
	private String top;
	
	/**
	 * 关注人
	 */
	private String observer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getPersonClass() {
		return personClass;
	}

	public void setPersonClass(String personClass) {
		this.personClass = personClass;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAttentionTime() {
		return attentionTime;
	}

	public void setAttentionTime(String attentionTime) {
		this.attentionTime = attentionTime;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}
	
}
