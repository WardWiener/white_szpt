package com.taiji.pubsec.szpt.instruction.model;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 指令
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_zlgl_zl")
public class Instruction {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(Instruction.class);
	
	/**
	 * 指令状态-待签收
	 */
	public static final String ZLZT_DQS = "0000000012001";
	/**
	 * 指令状态-已签收
	 */
	public static final String ZLZT_YQS = "0000000012002";
	/**
	 * 指令状态-已反馈
	 */
	public static final String ZLZT_YFK = "0000000012003"; 
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;	
	
	@Column(name = "cjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;	//创建时间
	
	@Column(name = "zlnr", length = 1000)
	private String content;	//指令内容
	
	@Column(name = "glztlx")
	private String relatedObjectType;	//关联主题类型
	
	@Column(name = "glztid")
	private String relatedObjectId;	//关联主题id

	@Column(name = "glztnr")
	private String relateObjectContent;	//关联主题内容
	
	@Column(name = "zllx")
	private String type;	//指令类型
	
	@Column(name = "yqfksj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requireFeedbackTime;	//要求反馈时间
	
	@Column(name = "sftzbdwfzr")
	private int isNofityDepartmentLeader;	//是否通知本单位负责人
	
	@Column(name = "cjrid")
	private String createPeopleId;	//创建人id
	
	@Column(name = "cjrbmid")
	private String createPeopleDepartmentId;	//创建人单位id
	
	@OneToMany(mappedBy = "instruction")
	private Set<InstructionReceiveSubject> instructionReceiveSubjects = new HashSet<InstructionReceiveSubject>();	//接收单位

	@Column(name = "zllxxgnr", length = 1000)
	private String typeContent;	//指令类型相关的内容
	
	public Instruction() {
		super();
	}
	
	public Instruction(Object obj, String relateObjectContent, String relatedObjectId) {
		this.setRelateObjectContent(relateObjectContent);
		this.setRelatedObjectId(relatedObjectId);;
		if(null != obj) {
			this.setRelatedObjectType(obj.getClass().getName());
			try {
				Method method = obj.getClass().getMethod("getId");
				this.setRelatedObjectId((String)method.invoke(obj, null));
			} catch (Exception e) {
				LOGGER.error("找不到业务对象的getId方法", e);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Date getRequireFeedbackTime() {
		return requireFeedbackTime;
	}

	public void setRequireFeedbackTime(Date requireFeedbackTime) {
		this.requireFeedbackTime = requireFeedbackTime;
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

	public Set<InstructionReceiveSubject> getInstructionReceiveSubjects() {
		return instructionReceiveSubjects;
	}

	public void setInstructionReceiveSubjects(Set<InstructionReceiveSubject> instructionReceiveSubjects) {
		this.instructionReceiveSubjects = instructionReceiveSubjects;
	}

	public String getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}
}
