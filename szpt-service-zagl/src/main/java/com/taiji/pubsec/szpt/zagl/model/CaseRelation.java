package com.taiji.pubsec.szpt.zagl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;


/**
 * 子案件表
 * @author 
 */
@Entity
@Table(name="t_zagl_zazaj")
public class CaseRelation {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 子案件编码
	 */
	@Column(name = "zajbm")
	private String caseCode;
	
	/**
	 * 子案件名称
	 */
	@Column(name = "zajmc")
	private String caseName;
	
	/**
	 * 专案
	 */
	@ManyToOne
	@JoinColumn(name="zagl_za_id")
	private SpecialCase specialCase;
	/**
	 * 办案民警，多个用，隔开存储
	 */
	@Column(name = "bamj")
	private String workers;

	public CaseRelation(){

	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SpecialCase getSpecialCase() {
		return specialCase;
	}

	public void setSpecialCase(SpecialCase specialCase) {
		this.specialCase = specialCase;
	}

	public String getWorkers() {
		return workers;
	}

	public void setWorkers(String workers) {
		this.workers = workers;
	}

}