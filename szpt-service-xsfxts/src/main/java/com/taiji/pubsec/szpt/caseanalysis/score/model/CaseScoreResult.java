package com.taiji.pubsec.szpt.caseanalysis.score.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 串并案打分结果
 * 
 * @author dixiaofeng
 */
@Entity
@Table(name="t_xsajfx_cbafxjg")
public class CaseScoreResult {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 主案件编码
	 */
	@Column(name="zajbh", length = 50)
	@Index(name="index_t_xsajfx_cbafxjg_zajbh")
	private String mainCase;
	
	/**
	 * 比对案件编码
	 */
	@Column(name="bdajbh", length = 50)
	@Index(name="index_t_xsajfx_cbafxjg_dbajbh")
	private String subCase;
	
	/**
	 * 分值
	 */
	@Column(name = "fs", precision = 10, scale = 2)
	private double score;
	
	/**
	 * 计算时间
	 */
	@Column(name="jssj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scoreTime;
	
	/**
	 * 是否已破案件，根据案件状态设置
	 */
	@Column(name="sfpa")
	private boolean isFinished;
	
	/**
	 * 是否忽略，默认false
	 */
	@Column(name="sfhl")
	private boolean isIgnored;
	
	/**
	 * 使用积分模板id
	 * @return
	 */
	@Column(name="mbid")
	@Index(name="index_t_xsajfx_cbafxjg_mbid")
	private String templateId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainCase() {
		return mainCase;
	}

	public void setMainCase(String mainCase) {
		this.mainCase = mainCase;
	}

	public String getSubCase() {
		return subCase;
	}

	public void setSubCase(String subCase) {
		this.subCase = subCase;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Date getScoreTime() {
		return scoreTime;
	}

	public void setScoreTime(Date scoreTime) {
		this.scoreTime = scoreTime;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public boolean isIgnored() {
		return isIgnored;
	}

	public void setIgnored(boolean isIgnored) {
		this.isIgnored = isIgnored;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	
}