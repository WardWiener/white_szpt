package com.taiji.pubsec.szpt.instruction.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 指令接收主题
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_zlgl_zljszt")
public class InstructionReceiveSubject {
	private final static Logger LOGGER = LoggerFactory.getLogger(InstructionReceiveSubject.class);
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	@Column(name = "jsztlx")
	private String receiveSubjectType;	//接收主题类型
	
	@Column(name = "jsztid")
	private String receiveSubjectId;	//接收主题id
	
	@Column(name = "jssj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receiveTime;	//接收时间
	
	@Column(name = "zt")
	private String status;	//状态
	
	@JoinColumn(name = "zl_id")
	@ManyToOne
	private Instruction instruction;	//指令
	
	@OrderBy("feedbackTime desc")
	@OneToMany(mappedBy = "instructionReceiveSubject")
	private Set<InstructionReceiveSubjectFeedback> instructionReceiveSubjectFeedbacks = new HashSet<InstructionReceiveSubjectFeedback>();	//指令反馈
	
	@OneToOne(mappedBy = "instructionReceiveSubject")
	private InstructionReceiveSubjectSign instructionReceiveSubjectSign;	//指令签收

	
	public InstructionReceiveSubject() {
		super();
	}
	
	public InstructionReceiveSubject(Object obj) {
		if(null != obj) {
			this.setReceiveSubjectType(obj.getClass().getName());
			try {
				Method method = obj.getClass().getMethod("getId");
				this.setReceiveSubjectId((String)method.invoke(obj, null));
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Set<InstructionReceiveSubjectFeedback> getInstructionReceiveSubjectFeedbacks() {
		return instructionReceiveSubjectFeedbacks;
	}

	public void setInstructionReceiveSubjectFeedbacks(
			Set<InstructionReceiveSubjectFeedback> instructionReceiveSubjectFeedbacks) {
		this.instructionReceiveSubjectFeedbacks = instructionReceiveSubjectFeedbacks;
	}

	public InstructionReceiveSubjectSign getInstructionReceiveSubjectSign() {
		return instructionReceiveSubjectSign;
	}

	public void setInstructionReceiveSubjectSign(InstructionReceiveSubjectSign instructionReceiveSubjectSign) {
		this.instructionReceiveSubjectSign = instructionReceiveSubjectSign;
	}

	public Date getLatestFeedbackTime() {
		return instructionReceiveSubjectFeedbacks.isEmpty() ? null : ((InstructionReceiveSubjectFeedback) new ArrayList(instructionReceiveSubjectFeedbacks).get(0)).getFeedbackTime();
	}
}
