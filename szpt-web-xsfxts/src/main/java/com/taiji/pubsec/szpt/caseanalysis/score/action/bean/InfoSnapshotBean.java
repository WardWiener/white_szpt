package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

/**
 * 串并案打分结果快照Bean
 * 
 * @author WangLei
 *
 */
public class InfoSnapshotBean {
	
	private String id;
	
	private String code;// 快照对应的模块标识
	
	private String targetId;// 快照关联对象id
	
	private String type;// 快照关联对象的类型
	
	private String intro;// 简介
	
	private String snapshot;// 快照内容json值
	
	private String createPerson;// 创建人姓名
	
	private Long createdDate;// 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	
}
