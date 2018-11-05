package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 拘留记录
 * @author huangcheng
 *
 */
@Entity
@Table(name = "t_gwry_jljl")
public class DetentionInfo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id ;
	
	/**
	 * 人员姓名
	 */
	@Column(name="ryxm")
	private String personName ;
	
	/**
	 * 身份证号
	 */
	@Column(name="sfzh")
	private String identy ;
	
	/**
	 * 案由
	 */
	@Column(name="ay") 
	private String caseReason ;
	
	/**
	 * 拘送单位
	 */
	@Column(name="jsdw") 
	private String detainUnit ;
	
	/**
	 * 拘留日期
	 */
	@Column(name="jlrq") 
	private String detainDate ;
	
	/**
	 * 拘留期限
	 */
	@Column(name="jlqx") 
	private Date detainDeadline ;
	
	/**
	 * 拘留所编码
	 */
	@Column(name="jlsbm") 
	private String detentionCode ;
	
	/**
	 * 拘留室bh
	 */
	@Column(name="jlsbh") 
	private String detainRoomNum ;
	
	/**
	 * 入所类别
	 */
	@Column(name="rslb") 
	private String cameInType ;
	
	/**
	 * 出所类别
	 */
	@Column(name="cslb") 
	private String leaveType ;
	
	/**
	 * 出所去向
	 */
	@Column(name="csqx") 
	private String statusAfterLeave ;
	
	/**
	 * 状态
	 */
	@Column(name="zt") 
	private String status ;
	
	/**
	 * 处罚闻书号
	 */
	@Column(name="cfwsh") 
	private String punishPaperNum ;
	
	/**
	 * 入库时间
	 */
	@Column(name="rksj") 
	private String storageDate ;
	
	/**
	 * 更新时间
	 */
	@Column(name="gxsj") 
	private String updateDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIdenty() {
		return identy;
	}

	public void setIdenty(String identy) {
		this.identy = identy;
	}

	public String getCaseReason() {
		return caseReason;
	}

	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}

	public String getDetainUnit() {
		return detainUnit;
	}

	public void setDetainUnit(String detainUnit) {
		this.detainUnit = detainUnit;
	}

	public String getDetainDate() {
		return detainDate;
	}

	public void setDetainDate(String detainDate) {
		this.detainDate = detainDate;
	}

	public Date getDetainDeadline() {
		return detainDeadline;
	}

	public void setDetainDeadline(Date detainDeadline) {
		this.detainDeadline = detainDeadline;
	}

	public String getDetentionCode() {
		return detentionCode;
	}

	public void setDetentionCode(String detentionCode) {
		this.detentionCode = detentionCode;
	}

	public String getDetainRoomNum() {
		return detainRoomNum;
	}

	public void setDetainRoomNum(String detainRoomNum) {
		this.detainRoomNum = detainRoomNum;
	}

	public String getCameInType() {
		return cameInType;
	}

	public void setCameInType(String cameInType) {
		this.cameInType = cameInType;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getStatusAfterLeave() {
		return statusAfterLeave;
	}

	public void setStatusAfterLeave(String statusAfterLeave) {
		this.statusAfterLeave = statusAfterLeave;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPunishPaperNum() {
		return punishPaperNum;
	}

	public void setPunishPaperNum(String punishPaperNum) {
		this.punishPaperNum = punishPaperNum;
	}

	public String getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
