package com.taiji.pubsec.szpt.instruction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 指令接收主题签收
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_zlgl_zljsztqs")
public class InstructionReceiveSubjectSign {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "qssj")
	private Date signTime;	//签收时间
	
	@Column(name = "qsrid")
	private String signPeopleId;	//签收人id
	
	@Column(name = "qsrxm")
	private String signPeopleName;	//签收人姓名
	
	@OneToOne
	@JoinColumn(name = "zljszt_id")
	private InstructionReceiveSubject instructionReceiveSubject;	//指令接收主题

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getSignPeopleId() {
		return signPeopleId;
	}

	public void setSignPeopleId(String signPeopleId) {
		this.signPeopleId = signPeopleId;
	}

	public String getSignPeopleName() {
		return signPeopleName;
	}

	public void setSignPeopleName(String signPeopleName) {
		this.signPeopleName = signPeopleName;
	}

	public InstructionReceiveSubject getInstructionReceiveSubject() {
		return instructionReceiveSubject;
	}

	public void setInstructionReceiveSubject(InstructionReceiveSubject instructionReceiveSubject) {
		this.instructionReceiveSubject = instructionReceiveSubject;
	}
	
	
	
}
