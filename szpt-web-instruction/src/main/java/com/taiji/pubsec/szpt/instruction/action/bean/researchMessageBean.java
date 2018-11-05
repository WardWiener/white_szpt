package com.taiji.pubsec.szpt.instruction.action.bean;

/**
 * 
 *
 */
public class researchMessageBean {

	private String id;  //反馈id

	private String  instructionType; // 研判类型

	private String instructionContent; // 推送原因

	private String instructionTime; // 推送时间

    private String personNum ; //身份证号
    
    private String snapshotId ;
    private String snapshotCode ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

	public String getInstructionContent() {
		return instructionContent;
	}

	public void setInstructionContent(String instructionContent) {
		this.instructionContent = instructionContent;
	}

	public String getInstructionTime() {
		return instructionTime;
	}

	public void setInstructionTime(String instructionTime) {
		this.instructionTime = instructionTime;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(String snapshotId) {
		this.snapshotId = snapshotId;
	}

	public String getSnapshotCode() {
		return snapshotCode;
	}

	public void setSnapshotCode(String snapshotCode) {
		this.snapshotCode = snapshotCode;
	}
	
	
}
