package com.taiji.pubsec.szpt.instruction.action.bean;


/**
 * 操作记录
 *
 */
public class OperationRecordBean {
	
	private String id;
	
	private String targetType;	//业务对象类型，全类名
	
	private String targetId;	//业务对象id
	
	private String content;		//操作内容
	
	private String operator;	//操作人
	
	private String operatorName;	//操作人
	
	private String operateUnit;	//操作单位
	
	private String operateUnitName;	//操作单位
	
	private Long operateTime;	//操作时间

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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateUnit() {
		return operateUnit;
	}

	public void setOperateUnit(String operateUnit) {
		this.operateUnit = operateUnit;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperateUnitName() {
		return operateUnitName;
	}

	public void setOperateUnitName(String operateUnitName) {
		this.operateUnitName = operateUnitName;
	}

}
