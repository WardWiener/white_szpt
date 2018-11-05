package com.taiji.pubsec.szpt.caseanalysis.score.model;

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
 * 串并案打分结果明细
 * 
 * @author dixiaofeng
 */
@Entity
@Table(name="t_xsajfx_cbafxjgmx")
public class CaseScoreResultDetail {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 规则名称
	 */
	@Column(name="gzmc", length = 50)
	private String scoreRuleName;
	
	/**
	 * 分值
	 */
	@Column(name = "fs", precision = 10, scale = 2)
	private double score;
	
	/**
	 * 评分结果id
	 */
	@ManyToOne
	@JoinColumn(name="xsajfx_cbafxjg_id")
	@Index(name="index_xsajfx_cbafxjg_id")
	private CaseScoreResult result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScoreRuleName() {
		return scoreRuleName;
	}

	public void setScoreRuleName(String scoreRuleName) {
		this.scoreRuleName = scoreRuleName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public CaseScoreResult getResult() {
		return result;
	}

	public void setResult(CaseScoreResult result) {
		this.result = result;
	}

}