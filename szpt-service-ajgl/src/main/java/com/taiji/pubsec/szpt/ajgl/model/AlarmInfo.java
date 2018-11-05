package com.taiji.pubsec.szpt.ajgl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 接处警信息
 * @author wangfx
 * @version 1.0
 * @created 10-8月-2016 15:03:19
 */
@Entity
@Table(name = "t_ajgl_jcj")
public class AlarmInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@OneToOne
	@JoinColumn(name = "caseId")
	private CriminalBasicCase basicCase;
	
	/**
	 * 报警电话
	 */
	private String rvcallNo;
	/**
	 * 出动船支（次）
	 */
	@Column(nullable = true)
	private Integer boatNum;
	/**
	 * 出动机动车（次）
	 */
	@Column(nullable = true)
	private Integer voitureNum;
	/**
	 * 出动警力数
	 */
	@Column(nullable = true)
	private Integer policeNum;
	/**
	 * 出动协勤数
	 */
	@Column(nullable = true)
	private Integer policeAssNum;
	/**
	 * 处警单位
	 */
	private String disposeUnit;
	/**
	 * 处警简要情况(4000)
	 */
	@Column(length = 4000)
	private String disposeDetails;
	/**
	 * 处警结果
	 */
	@Column(length = 10)
	private String disposeResult;
	/**
	 * 处警人1
	 */
	private String disposePerson;
	/**
	 * 处警人2
	 */
	private String disposePerson2;
	/**
	 * 处警意见
	 */
	private String disposeOpinion;
	/**
	 * 到达现场时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date arriveTime;
	/**
	 * 接警单位
	 */
	private String receiveUnit;
	/**
	 * 接警内容(1500)
	 */
	@Column(length = 1500)
	private String reportDetails;
	/**
	 * 接警人
	 */
	private String receivePerson;
	/**
	 * 接警时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date reportTime;
	/**
	 * 接警形式
	 */
	private String reportMode;
	/**
	 * 解救被拐卖儿童数
	 */
	@Column(nullable = true)
	private Integer rescueyNum;
	/**
	 * 解救被拐卖妇女数
	 */
	@Column(nullable = true)
	private Integer rescuewNum;
	/**
	 * 解救人质数
	 */
	@Column(nullable = true)
	private Integer rescurhNum;
	/**
	 * 警情类别
	 */
	private String alarmType;
	/**
	 * 救助群众
	 */
	@Column(nullable = true)
	private Integer salvationPeople;
	/**
	 * 救助伤员
	 */
	@Column(nullable = true)
	private Integer salvationWou;
	/**
	 * 留置审查人数
	 */
	@Column(nullable = true)
	private Integer censorNum;
	
	/**
	 * 是否现场处罚
	 */
	private String ifSpotfine;
	/**
	 * 是否现场调解
	 */
	private String ifSpotReconcile;
	
	/**
	 * 辖区
	 */
	@Column(name = "xq")
	private String popedom;
	
	/**
	 * 辖区编码
	 */
	private String popedomCode;
	
	
	public String getRvcallNo() {
		return rvcallNo;
	}
	public Integer getBoatNum() {
		return boatNum;
	}
	public Integer getVoitureNum() {
		return voitureNum;
	}
	public Integer getPoliceNum() {
		return policeNum;
	}
	public Integer getPoliceAssNum() {
		return policeAssNum;
	}
	public String getDisposeUnit() {
		return disposeUnit;
	}
	public String getDisposeDetails() {
		return disposeDetails;
	}
	public String getDisposePerson() {
		return disposePerson;
	}
	public String getDisposePerson2() {
		return disposePerson2;
	}
	public String getDisposeOpinion() {
		return disposeOpinion;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public String getReceiveUnit() {
		return receiveUnit;
	}
	public String getReportDetails() {
		return reportDetails;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public String getReportMode() {
		return reportMode;
	}
	public Integer getRescueyNum() {
		return rescueyNum;
	}
	public Integer getRescuewNum() {
		return rescuewNum;
	}
	public Integer getRescurhNum() {
		return rescurhNum;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public Integer getSalvationPeople() {
		return salvationPeople;
	}
	public Integer getSalvationWou() {
		return salvationWou;
	}
	public Integer getCensorNum() {
		return censorNum;
	}
	public String getIfSpotfine() {
		return ifSpotfine;
	}
	public String getIfSpotReconcile() {
		return ifSpotReconcile;
	}
	public String getPopedom() {
		return popedom;
	}
	public void setRvcallNo(String rvcallNo) {
		this.rvcallNo = rvcallNo;
	}
	public void setBoatNum(Integer boatNum) {
		this.boatNum = boatNum;
	}
	public void setVoitureNum(Integer voitureNum) {
		this.voitureNum = voitureNum;
	}
	public void setPoliceNum(Integer policeNum) {
		this.policeNum = policeNum;
	}
	public void setPoliceAssNum(Integer policeAssNum) {
		this.policeAssNum = policeAssNum;
	}
	public void setDisposeUnit(String disposeUnit) {
		this.disposeUnit = disposeUnit;
	}
	public void setDisposeDetails(String disposeDetails) {
		this.disposeDetails = disposeDetails;
	}
	public void setDisposePerson(String disposePerson) {
		this.disposePerson = disposePerson;
	}
	public void setDisposePerson2(String disposePerson2) {
		this.disposePerson2 = disposePerson2;
	}
	public void setDisposeOpinion(String disposeOpinion) {
		this.disposeOpinion = disposeOpinion;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
	}
	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}
	public void setRescueyNum(Integer rescueyNum) {
		this.rescueyNum = rescueyNum;
	}
	public void setRescuewNum(Integer rescuewNum) {
		this.rescuewNum = rescuewNum;
	}
	public void setRescurhNum(Integer rescurhNum) {
		this.rescurhNum = rescurhNum;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public void setSalvationPeople(Integer salvationPeople) {
		this.salvationPeople = salvationPeople;
	}
	public void setSalvationWou(Integer salvationWou) {
		this.salvationWou = salvationWou;
	}
	public void setCensorNum(Integer censorNum) {
		this.censorNum = censorNum;
	}
	public void setIfSpotfine(String ifSpotfine) {
		this.ifSpotfine = ifSpotfine;
	}
	public void setIfSpotReconcile(String ifSpotReconcile) {
		this.ifSpotReconcile = ifSpotReconcile;
	}
	public void setPopedom(String popedom) {
		this.popedom = popedom;
	}
	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}
	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}
	public String getDisposeResult() {
		return disposeResult;
	}
	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}
	public String getPopedomCode() {
		return popedomCode;
	}
	public void setPopedomCode(String popedomCode) {
		this.popedomCode = popedomCode;
	}
	

}