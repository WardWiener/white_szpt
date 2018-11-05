package com.taiji.pubsec.szpt.ajgl.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 案件附加信息
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_ajgl_ajfjxx")
public class CaseAttachedInfo{
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	String id;
	
	/**
	 * 包办人
	 */
	private String doPerson;
	
	/**
	 * 一次退侦时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date oneRefundInvestigationTime;
	
	/**
	 * 二次退侦时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date twoRefundInvestigationTime;
	
	/**
	 * 案件编号
	 */
	private String caseCode;
	
	/**
	 * 立案时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date filingTime;
	
	/**
	 * 受案时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptingTime;
	
	/**
	 * 是否破案
	 */
	private String ifSolved;
	
	/**
	 * 破案时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date solvedTime;
	
	/**
	 * 归档时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date archiveTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDoPerson() {
		return doPerson;
	}

	public void setDoPerson(String doPerson) {
		this.doPerson = doPerson;
	}

	public Date getOneRefundInvestigationTime() {
		return oneRefundInvestigationTime;
	}

	public void setOneRefundInvestigationTime(Date oneRefundInvestigationTime) {
		this.oneRefundInvestigationTime = oneRefundInvestigationTime;
	}

	public Date getTwoRefundInvestigationTime() {
		return twoRefundInvestigationTime;
	}

	public void setTwoRefundInvestigationTime(Date twoRefundInvestigationTime) {
		this.twoRefundInvestigationTime = twoRefundInvestigationTime;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Date getFilingTime() {
		return filingTime;
	}

	public void setFilingTime(Date filingTime) {
		this.filingTime = filingTime;
	}

	public Date getAcceptingTime() {
		return acceptingTime;
	}

	public void setAcceptingTime(Date acceptingTime) {
		this.acceptingTime = acceptingTime;
	}

	public String getIfSolved() {
		return ifSolved;
	}

	public void setIfSolved(String ifSolved) {
		this.ifSolved = ifSolved;
	}

	public Date getSolvedTime() {
		return solvedTime;
	}

	public void setSolvedTime(Date solvedTime) {
		this.solvedTime = solvedTime;
	}

	public Date getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(Date archiveTime) {
		this.archiveTime = archiveTime;
	}
	
	
}
