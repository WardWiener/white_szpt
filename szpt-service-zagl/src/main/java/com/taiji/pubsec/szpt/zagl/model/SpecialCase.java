package com.taiji.pubsec.szpt.zagl.model;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.businesscomponents.organization.model.Person;

import org.hibernate.annotations.Index;
/**
 * 专案
 * @author 
 */
@Entity
@Table(name="t_zagl_za")
public class SpecialCase {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	/**
	 * 编码
	 */
	@Column(name = "bm")
	private String code;
	
	/**
	 * 案情简介
	 */
	@Column(name = "aqjj")
	private String content;
	
	/**
	 * 创建日期
	 */
	@Column(name = "cjrq")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	/**
	 * 创建人
	 */
	@ManyToOne
	@JoinColumn(name = "ryid")
	private Person createPerson;
	/**
	 * 名称
	 */
	@Column(name = "mc")
	private String name;
	/**
	 * 性质
	 */
	@Column(name = "xz")
	private String nature;
	/**
	 * 下步计划
	 */
	@Column(name = "xbjh")
	private String plan;
	/**
	 * 主要问题
	 */
	@Column(name = "zywt")
	private String problem;
	/**
	 * 进展
	 */
	@Column(name = "jz")
	private String progress;
	/**
	 * 最后更新时间
	 */
	@Column(name = "zhgxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	/**
	 * 最后更新人
	 */
	@ManyToOne
	@JoinColumn(name = "gxry_id")
	private Person updatePerson;
	
	/**
	 * 专案报告
	 */
	@OneToMany(mappedBy="specialCase")	
	private Set<SpecialCaseReport> specialCaseReport = new HashSet<SpecialCaseReport>();
	/**
	 * 专案涉案人员
	 */
	@OneToMany(mappedBy="specialCase")
	private Set<SpecialCaseInvolvedPerson> specialCaseInvolvedPerson=new HashSet<SpecialCaseInvolvedPerson>();
	/**
	 * 专案资料
	 */
	@OneToMany(mappedBy="specialCase")
	private Set<SpecialCaseMaterial> specialCaseMaterial=new HashSet<SpecialCaseMaterial>();
	
	/**
	 * 留言
	 */
	@OneToMany(mappedBy="specialCase")
	private Set<SpecialCaseMessage> specialCaseMessage= new HashSet<SpecialCaseMessage>();
	
	/**
	 * 专案人员角色分配
	 */
	@OneToMany(mappedBy="specialCase")
	private Set<SpecialCaseRoleAssignment> specialCaseRoleAssignment=new HashSet<SpecialCaseRoleAssignment>();
	
	@OneToMany(mappedBy="SpecialCase")
	private Set<SpecialCaseInvolvedPersonRelation> specialCaseInvolvedPersonRelations = new HashSet<SpecialCaseInvolvedPersonRelation>();
	
	/**
	 * 子案件
	 */
	@OneToMany(mappedBy="specialCase")
	private Set<CaseRelation> caseRelation =new HashSet<CaseRelation>();
	
	@OneToMany(mappedBy="specialCase")
	private Set<SpecialCaseStickRecord> specialCaseStickRecords = new HashSet<SpecialCaseStickRecord>();

	public SpecialCase(){

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Person getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Person createPerson) {
		this.createPerson = createPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Person getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Person updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Set<SpecialCaseReport> getSpecialCaseReport() {
		return specialCaseReport;
	}

	public void setSpecialCaseReport(Set<SpecialCaseReport> specialCaseReport) {
		this.specialCaseReport = specialCaseReport;
	}

	public Set<SpecialCaseInvolvedPerson> getSpecialCaseInvolvedPerson() {
		return specialCaseInvolvedPerson;
	}

	public void setSpecialCaseInvolvedPerson(
			Set<SpecialCaseInvolvedPerson> specialCaseInvolvedPerson) {
		this.specialCaseInvolvedPerson = specialCaseInvolvedPerson;
	}

	public Set<SpecialCaseMaterial> getSpecialCaseMaterial() {
		return specialCaseMaterial;
	}

	public void setSpecialCaseMaterial(Set<SpecialCaseMaterial> specialCaseMaterial) {
		this.specialCaseMaterial = specialCaseMaterial;
	}

	public Set<SpecialCaseRoleAssignment> getSpecialCaseRoleAssignment() {
		return specialCaseRoleAssignment;
	}

	public void setSpecialCaseRoleAssignment(
			Set<SpecialCaseRoleAssignment> specialCaseRoleAssignment) {
		this.specialCaseRoleAssignment = specialCaseRoleAssignment;
	}

	public Set<CaseRelation> getCaseRelation() {
		return caseRelation;
	}

	public void setCaseRelation(Set<CaseRelation> caseRelation) {
		this.caseRelation = caseRelation;
	}

	public Set<SpecialCaseInvolvedPersonRelation> getSpecialCaseInvolvedPersonRelations() {
		return specialCaseInvolvedPersonRelations;
	}

	public void setSpecialCaseInvolvedPersonRelations(
			Set<SpecialCaseInvolvedPersonRelation> specialCaseInvolvedPersonRelations) {
		this.specialCaseInvolvedPersonRelations = specialCaseInvolvedPersonRelations;
	}

	public Set<SpecialCaseMessage> getSpecialCaseMessage() {
		return specialCaseMessage;
	}

	public void setSpecialCaseMessage(Set<SpecialCaseMessage> specialCaseMessage) {
		this.specialCaseMessage = specialCaseMessage;
	}

	public Set<SpecialCaseStickRecord> getSpecialCaseStickRecords() {
		return specialCaseStickRecords;
	}

	public void setSpecialCaseStickRecords(Set<SpecialCaseStickRecord> specialCaseStickRecords) {
		this.specialCaseStickRecords = specialCaseStickRecords;
	}
	
}