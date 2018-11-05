package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 戒毒所记录
 * @author huangcheng
 *
 */
@Entity
@Table(name = "t_gwry_jdjl")
public class DrugRehabilitationInfo {

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
	 * 戒毒所编码
	 */
	@Column(name="jdsbm") 
	private String rehabilitationCode ;
	
	/**
	 * 戒毒室编号
	 */
	@Column(name="jdsfjbh") 
	private String rehabilitationRoomNum ;
	
	/**
	 * 强制戒毒文书号
	 */
	@Column(name="qzjdwsh") 
	private String rehabilitatePaperCode ;
	
	/**
	 * 开始时间
	 */
	@Column(name="kssj") 
	private Date startDate ;
	
	/**
	 * 结束时间
	 */
	@Column(name="jssj") 
	private Date endDate ;
	
	/**
	 * 入所类别
	 */
	@Column(name="rslb") 
	private String cameInType ;
	
	/**
	 * 入所日期
	 */
	@Column(name="rsrq") 
	private Date cameInDate ;
	
	/**
	 * 状态（人员标记）
	 */
	@Column(name="rybj") 
	private String personMark ;
	
	/**
	 * 送戒单位
	 */
	@Column(name="sjdw") 
	private String unitSendBy ;
	
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

	public String getRehabilitationCode() {
		return rehabilitationCode;
	}

	public void setRehabilitationCode(String rehabilitationCode) {
		this.rehabilitationCode = rehabilitationCode;
	}

	public String getRehabilitationRoomNum() {
		return rehabilitationRoomNum;
	}

	public void setRehabilitationRoomNum(String rehabilitationRoomNum) {
		this.rehabilitationRoomNum = rehabilitationRoomNum;
	}

	public String getRehabilitatePaperCode() {
		return rehabilitatePaperCode;
	}

	public void setRehabilitatePaperCode(String rehabilitatePaperCode) {
		this.rehabilitatePaperCode = rehabilitatePaperCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCameInType() {
		return cameInType;
	}

	public void setCameInType(String cameInType) {
		this.cameInType = cameInType;
	}

	public Date getCameInDate() {
		return cameInDate;
	}

	public void setCameInDate(Date cameInDate) {
		this.cameInDate = cameInDate;
	}

	public String getPersonMark() {
		return personMark;
	}

	public void setPersonMark(String personMark) {
		this.personMark = personMark;
	}

	public String getUnitSendBy() {
		return unitSendBy;
	}

	public void setUnitSendBy(String unitSendBy) {
		this.unitSendBy = unitSendBy;
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
