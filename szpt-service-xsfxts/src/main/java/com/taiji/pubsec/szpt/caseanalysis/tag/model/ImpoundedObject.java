package com.taiji.pubsec.szpt.caseanalysis.tag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;


/**
 * 案管涉案物品
 * @author dixiaofeng
 * @version 1.0
 * @created 20-二月-2017 10:24:49
 */
@Entity
@Table(name="t_xsajfx_sawp_ajgl")
public class ImpoundedObject {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 基本案件信息
	 */
	@ManyToOne
	@JoinColumn(name = "ajbh")
	private CriminalBasicCase basicCase;
	
	/**
	 * 物品编号
	 */
	@Column(name="wpbh")
	private String code;
	
	/**
	 * 物品特征
	 */
	@Column(name="wptz")
	private String feature;

	
	/**
	 * 物品名称
	 */
	@Column(name="wpmc")
	private String name;
	

	/**
	 * 文书文号
	 */
	@Column(name="wswh")
	private String paperCode;
	/**
	 * 文书id
	 */
	@Column(name="wsbh")
	private String paperId;

	/**
	 * 嫌疑人姓名 
	 */
	@Column(name="xyrxm")
	private String suspectName;

	/**
	 * 保管区名称
	 */
	@Column(name="bgq")
	private String area;
	
	/**
	 * 保管柜
	 */
	@Column(name="bgg")
	private String locker;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPaperCode() {
		return paperCode;
	}
	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getSuspectName() {
		return suspectName;
	}
	public void setSuspectName(String suspectName) {
		this.suspectName = suspectName;
	}
	public CriminalBasicCase getBasicCase() {
		return basicCase;
	}
	public void setBasicCase(CriminalBasicCase basicCase) {
		this.basicCase = basicCase;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocker() {
		return locker;
	}
	public void setLocker(String locker) {
		this.locker = locker;
	}

	
}