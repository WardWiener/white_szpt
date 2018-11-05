package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 流动人口
 * @author huangcheng
 *
 */
@Entity
@Table(name="t_zhzats_sqjw_ldrk")
public class FloatingPop {

	@Id
	private String id ;
	
	/**
	 * 单位名称
	 */
	@Column(name="dwmc")
	private String unitName ;
	
	/**
	 * 单位编码
	 */
	@Column(name="dwbm")
	@Index(name="index_t_zhzats_sqjw_ldrk_dwbm")
	private String unitCode ;
	
	/**
	 * 流动人口数量
	 */
	@Column(name="sl")
	private Integer floatingNum ;
	
	/**
	 * 流动时间
	 */
	@Column(name="ldsj")
	@Index(name="index_t_zhzats_sqjw_ldrk_ldsj")
	private Date floatingTime ;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getFloatingNum() {
		return floatingNum;
	}

	public void setFloatingNum(Integer floatingNum) {
		this.floatingNum = floatingNum;
	}

	public Date getFloatingTime() {
		return floatingTime;
	}

	public void setFloatingTime(Date floatingTime) {
		this.floatingTime = floatingTime;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

}
