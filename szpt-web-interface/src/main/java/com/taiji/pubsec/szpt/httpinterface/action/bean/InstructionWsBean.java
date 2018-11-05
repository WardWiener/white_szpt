package com.taiji.pubsec.szpt.httpinterface.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 指令
 *
 */
public class InstructionWsBean {

	private String id;

	private Long createTime; // 创建时间

	private String content; // 指令内容

	private String relatedObjectType; // 关联主体类型

	private String relatedObjectId; // 关联主体id

	private String relateObjectContent; // 关联主体内容

	private String type; // 指令类型

	private String typeName; // 指令类型名称

	private Long requireFeedbackTime; // 要求反馈时间

	private int isNofityDepartmentLeader; // 是否通知本单位负责人

	private String createPeopleCode; // 创建人code
	
	private String createPeopleDepartmentCode; // 创建人单位code

	private String typeContent; // 指令类型相关的内容

	private List<InstructionReceiveSubjectBean> subs = new ArrayList<InstructionReceiveSubjectBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRelatedObjectType() {
		return relatedObjectType;
	}

	public void setRelatedObjectType(String relatedObjectType) {
		this.relatedObjectType = relatedObjectType;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIsNofityDepartmentLeader() {
		return isNofityDepartmentLeader;
	}

	public void setIsNofityDepartmentLeader(int isNofityDepartmentLeader) {
		this.isNofityDepartmentLeader = isNofityDepartmentLeader;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getRequireFeedbackTime() {
		return requireFeedbackTime;
	}

	public void setRequireFeedbackTime(Long requireFeedbackTime) {
		this.requireFeedbackTime = requireFeedbackTime;
	}

	public String getCreatePeopleCode() {
		return createPeopleCode;
	}

	public void setCreatePeopleCode(String createPeopleCode) {
		this.createPeopleCode = createPeopleCode;
	}

	public String getCreatePeopleDepartmentCode() {
		return createPeopleDepartmentCode;
	}

	public void setCreatePeopleDepartmentCode(String createPeopleDepartmentCode) {
		this.createPeopleDepartmentCode = createPeopleDepartmentCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<InstructionReceiveSubjectBean> getSubs() {
		return subs;
	}

	public void setSubs(List<InstructionReceiveSubjectBean> subs) {
		this.subs = subs;
	}

	public String getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}
}
