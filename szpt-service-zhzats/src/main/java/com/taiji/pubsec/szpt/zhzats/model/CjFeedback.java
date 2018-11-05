package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

/**
 * 处警反馈
 * @author 黄诚
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_fk")
public class CjFeedback {
	
	@Id
	private String id ;

	/**
	 * 反馈的指挥单元id
	 * 索引
	 */

	@Column(name="ty_zhdy_id")
	@Index(name="index_t_zhzats_jwzh_fk_ty_zhdy_id")
	private String fkOrderCellId ;
	/**
	 * 反馈的指挥单元编码
	 * 索引
	 */
	@Column(name="ty_zhdy_bm")
	@Index(name="index_t_zhzats_jwzh_fk_ty_zhdy_bm")
	private String fkOrderCellCode ;
	/**
	 * 反馈的指挥单元名称
	 * 索引
	 */
	@Column(name="ty_zhdy_mc")
	private String fkOrderCellName ;
	
	/**
	 * 反馈人姓名
	 */
	@Column(name="fkrxm")
	private String fkPerson ;
	
	/**
	 * 反馈时间
	 * 索引
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fksj")
	@Index(name="index_t_zhzats_jwzh_fk_fksj")
	private Date feedbackTime ;
	
	/**
	 * 反馈内容
	 */
	@Column(name="fknr")
	private String content ;
	
	/**
	 * 反馈类型
	 */
	@Column(name="fklx")
	private String feedbackType ;

	/**
	 * 处警信息ID
	 * 索引
	 */
	@Column(name="fact_cj_id")
	@Index(name="index_t_zhzats_jwzh_cj_id")
	private String factCjId ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFactCjId() {
		return factCjId;
	}

	public void setFactCjId(String factCjId) {
		this.factCjId = factCjId;
	}

	public String getFkPerson() {
		return fkPerson;
	}

	public void setFkPerson(String fkPerson) {
		this.fkPerson = fkPerson;
	}

	public String getFkOrderCellId() {
		return fkOrderCellId;
	}

	public void setFkOrderCellId(String fkOrderCellId) {
		this.fkOrderCellId = fkOrderCellId;
	}

	public String getFkOrderCellName() {
		return fkOrderCellName;
	}

	public void setFkOrderCellName(String fkOrderCellName) {
		this.fkOrderCellName = fkOrderCellName;
	}

	public String getFkOrderCellCode() {
		return fkOrderCellCode;
	}

	public void setFkOrderCellCode(String fkOrderCellCode) {
		this.fkOrderCellCode = fkOrderCellCode;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	
}
