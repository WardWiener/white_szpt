package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 报备警力
 * @author XIEHF
 * 
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_bbjl")
public class DailyReportPerson {
	
	@Id
	private String id ;

	
	/**
	 * 警号
	 * 索引
	 */
	@Column(name="ryjh")
	private String personJh ;
	
	/**
	 * 姓名
	 */
	@Column(name="xm")
	private String personName ;
	
	/**
	 * 报备日期
	 * 索引
	 */
	@Column(name="bbrq")
	@Index(name="index_t_zhzats_bbjl_bbrq")
	private Date reportDate ;
	
	/**
	 * 指挥单元id
	 * 索引
	 */
	@Column(name="ty_zhdy_id")
	@Index(name="index_t_zhzats_bbjl_ty_zhdy_id")
	private String orderCellId ;
	
	/**
	 * 指挥单元编码
	 * 索引
	 */
	@Column(name="ty_zhdy_bm")
	@Index(name="index_t_zhzats_bbjl_ty_zhdy_bm")
	private String orderCellCode ;
	
	/**
	 * 指挥单元名称
	 * 索引
	 */
	@Column(name="ty_zhdy_mc")
	private String orderCellName ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonJh() {
		return personJh;
	}

	public void setPersonJh(String personJh) {
		this.personJh = personJh;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getOrderCellId() {
		return orderCellId;
	}

	public void setOrderCellId(String orderCellId) {
		this.orderCellId = orderCellId;
	}

	public String getOrderCellCode() {
		return orderCellCode;
	}

	public void setOrderCellCode(String orderCellCode) {
		this.orderCellCode = orderCellCode;
	}

	public String getOrderCellName() {
		return orderCellName;
	}

	public void setOrderCellName(String orderCellName) {
		this.orderCellName = orderCellName;
	}
	


}
