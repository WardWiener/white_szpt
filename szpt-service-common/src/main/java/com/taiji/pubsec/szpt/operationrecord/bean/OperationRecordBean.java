package com.taiji.pubsec.szpt.operationrecord.bean;

import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;

public class OperationRecordBean {
	
	private String id;

	private String targetType; // 业务对象类型，全类名

	private String targetId; // 业务对象id

	private String content; // 操作内容

	private String result; // 操作结果

	private String operator; // 操作人姓名

	private String operateUnit; // 操作单位名称

	private Long operateTime; // 操作时间

	public OperationRecordBean(OperationRecord or) {
		this.id = or.getId();
		this.targetType = or.getTargetType();
		this.targetId = or.getTargetId();
		this.content = or.getContent() == null ? "" : or.getContent();
		this.result = or.getResult() == null ? "" : or.getResult();
		this.operator = or.getOperator() == null ? "" : or.getOperator();
		this.operateUnit = or.getOperateUnit() == null ? "" : or
				.getOperateUnit();
		this.operateTime = or.getOperateTime() == null ? null : or
				.getOperateTime().getTime();
	}

	public OperationRecordBean() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperateUnit() {
		return operateUnit;
	}

	public void setOperateUnit(String operateUnit) {
		this.operateUnit = operateUnit;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

}
