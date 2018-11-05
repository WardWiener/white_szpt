package com.taiji.pubsec.szpt.instruction.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 指令
 *
 */
public class InstructionBean {

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

	private String createPeopleId; // 创建人id
	
	private String createPeopleName; // 创建人姓名

	private String createPeopleDepartmentId; // 创建人单位id

	private String createPeopleDepartmentName; // 创建人单位名称

	private String typeContent; // 指令类型相关的内容

	private Long requireFeedbackTimeStart;

	private Long requireFeedbackTimeEnd;

	private Long createTimeStart;

	private Long createTimeEnd;

	private String unitName;

	private String unitId;

	private String ids;

	private String names;

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

	public String getCreatePeopleId() {
		return createPeopleId;
	}

	public void setCreatePeopleId(String createPeopleId) {
		this.createPeopleId = createPeopleId;
	}

	public String getCreatePeopleDepartmentId() {
		return createPeopleDepartmentId;
	}

	public void setCreatePeopleDepartmentId(String createPeopleDepartmentId) {
		this.createPeopleDepartmentId = createPeopleDepartmentId;
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

	public String getCreatePeopleDepartmentName() {
		return createPeopleDepartmentName;
	}

	public void setCreatePeopleDepartmentName(String createPeopleDepartmentName) {
		this.createPeopleDepartmentName = createPeopleDepartmentName;
	}

	public String getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public String getCreatePeopleName() {
		return createPeopleName;
	}

	public void setCreatePeopleName(String createPeopleName) {
		this.createPeopleName = createPeopleName;
	}

	public Long getRequireFeedbackTimeStart() {
		return requireFeedbackTimeStart;
	}

	public void setRequireFeedbackTimeStart(Long requireFeedbackTimeStart) {
		this.requireFeedbackTimeStart = requireFeedbackTimeStart;
	}

	public Long getRequireFeedbackTimeEnd() {
		return requireFeedbackTimeEnd;
	}

	public void setRequireFeedbackTimeEnd(Long requireFeedbackTimeEnd) {
		this.requireFeedbackTimeEnd = requireFeedbackTimeEnd;
	}

	public Long getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Long createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	
}
