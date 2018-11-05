package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

/**
 * 涉案团伙
 * 
 * @author wangfx
 */
@Entity
@Table(name="t_xsajfx_sath")
public class CriminalFord {

	/**
	 * 团伙编号
	 */
	@Id
	@Column(name="thbh", length = 50)
	private String code;
	
	/**
	 * 团伙类型名称
	 */
	@Column(name="thlx", length = 50)
	private String type;
	
	/**
	 * 团伙名称
	 */
	@Column(name="thmc", length = 135)
	private String name;
	
	/**
	 * 团伙人数
	 */
	@Column(name="thrs")
	private Integer count;
	
	/**
	 * 主犯名称
	 */
	@Column(name="zfmc", length = 50)
	private String mainPeople;
	
	/**
	 * 组合形式
	 */
	@Column(name="zhxs", length = 50)
	private String form;
	
	/**
	 * 作案手段
	 */
	@Column(name="zasd", length = 50)
	private String resort;
	
	/**
	 * 作案特点
	 */
	@Column(name="zatd", length = 50)
	private String peculiarity;
	
	/**
	 * 形成时间
	 */
	@Column(name="xcsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date poseDate;
	
	/**
	 * 组织所在地
	 */
	@Column(name="zzszd", length = 450)
	private String histogenically;
	
	/**
	 * 成员组成特点
	 */
	@Column(name="cyzctd", length = 450)
	private String membertrait;
	
	/**
	 * 活动范围
	 */
	@Column(name="hdfw", length = 450)
	private String territory;
	
	/**
	 * 内部联系方法
	 */
	@Column(name="nblxfs", length = 450)
	private String incontact;
	
	/**
	 * 备注
	 */
	@Column(name="bz", length = 1500)
	private String annex;
	
	/**
	 * 录入人
	 */
	@Column(name="lrr", length = 50)
	private String inputPerson;
	
	/**
	 * 录入时间
	 */
	@Column(name="lrsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inputTime;
	
	/**
	 * 修改人
	 */
	@Column(name="xgr", length = 50)
	private String modifyPerson;
	
	/**
	 * 修改时间
	 */
	@Column(name="xgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	
	/**
	 * 案件编号
	 */
	@ManyToOne
	@JoinColumn(name="ajbh")
	@Index(name="index_t_xsajfx_sathx_ajbh")
	private CriminalBasicCase basicCase;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMainPeople() {
		return mainPeople;
	}

	public void setMainPeople(String mainPeople) {
		this.mainPeople = mainPeople;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getResort() {
		return resort;
	}

	public void setResort(String resort) {
		this.resort = resort;
	}

	public String getPeculiarity() {
		return peculiarity;
	}

	public void setPeculiarity(String peculiarity) {
		this.peculiarity = peculiarity;
	}

	public Date getPoseDate() {
		return poseDate;
	}

	public void setPoseDate(Date poseDate) {
		this.poseDate = poseDate;
	}

	public String getHistogenically() {
		return histogenically;
	}

	public void setHistogenically(String histogenically) {
		this.histogenically = histogenically;
	}

	public String getMembertrait() {
		return membertrait;
	}

	public void setMembertrait(String membertrait) {
		this.membertrait = membertrait;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getIncontact() {
		return incontact;
	}

	public void setIncontact(String incontact) {
		this.incontact = incontact;
	}

	public String getAnnex() {
		return annex;
	}

	public void setAnnex(String annex) {
		this.annex = annex;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}

	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}

}