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
 * 处警
 * @author 
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_cj")
public class FactCj {

	@Id
	private String id ;
	/**
	 * 警情Id
	 * 索引
	 */
	@Column(name="fact_jq_id")
	@Index(name="index_t_zhzats_jwzh_cj_fact_jq_id")
	private String factJqId;
	
	/**
	 * 处警指挥单元id
	 * 索引
	 */
	@Column(name="ty_zhdy_id")
	@Index(name="index_t_zhzats_jwzh_cj_ty_zhdy_id")
	private String cjOrderCellId ;
	
	/**
	 * 处警指挥单元编码
	 * 索引
	 */
	@Column(name="ty_zhdy_bm")
	@Index(name="index_t_zhzats_jwzh_cj_ty_zhdy_bm")
	private String cjOrderCellCode ;
	
	/**
	 * 处警指挥单元名称
	 * 索引
	 */
	@Column(name="ty_zhdy_mc")
	private String orderCellName ;
	
	/**
	 * 处警时间
	 * 索引
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cjsj")
	@Index(name="index_t_zhzats_jwzh_cj_cjsj")
	private Date cjTime;	
	
	/**
	 * 签收人
	 */
	@Column(name="qsr")
	private String signPerson ;
	
	/**
	 * 签收时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "qssj")
	private Date qsTime;	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFactJqId() {
		return factJqId;
	}

	public void setFactJqId(String factJqId) {
		this.factJqId = factJqId;
	}

	public Date getCjTime() {
		return cjTime;
	}

	public void setCjTime(Date cjTime) {
		this.cjTime = cjTime;
	}

	public String getCjOrderCellId() {
		return cjOrderCellId;
	}

	public void setCjOrderCellId(String cjOrderCellId) {
		this.cjOrderCellId = cjOrderCellId;
	}

	public String getOrderCellName() {
		return orderCellName;
	}

	public void setOrderCellName(String orderCellName) {
		this.orderCellName = orderCellName;
	}

	public String getSignPerson() {
		return signPerson;
	}

	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
	}

	public Date getQsTime() {
		return qsTime;
	}

	public void setQsTime(Date qsTime) {
		this.qsTime = qsTime;
	}

	public String getCjOrderCellCode() {
		return cjOrderCellCode;
	}

	public void setCjOrderCellCode(String cjOrderCellCode) {
		this.cjOrderCellCode = cjOrderCellCode;
	}
	
}
