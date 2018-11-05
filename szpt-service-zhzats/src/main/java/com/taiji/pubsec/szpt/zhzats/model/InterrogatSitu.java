package com.taiji.pubsec.szpt.zhzats.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 盘查情况
 * @author huangcheng
 *
 */
@Entity
@Table(name="t_zhzats_hlck_pcqk")
public class InterrogatSitu {
	
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
	private String unitCode ;
	
	/**
	 * 盘查人员数量
	 */
	@Column(name="ry")
	private Integer newManpowerNum ;
	
	/**
	 * 盘查客运车辆数量
	 */
	@Column(name="kycl")
	private Integer newCarNum ;
	
	/**
	 * 盘查非客运车辆数量
	 */
	@Column(name="fkycl")
	private Integer newCarNotKyNum ;
	
	/**
	 * 盘查时间
	 */
	@Column(name="pcsj")
	@Index(name="index_t_zhzats_hlck_pcqk_pcsj")
	private Date interrogatTime ;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Integer getNewManpowerNum() {
		return newManpowerNum;
	}

	public void setNewManpowerNum(Integer newManpowerNum) {
		this.newManpowerNum = newManpowerNum;
	}

	public Integer getNewCarNum() {
		return newCarNum;
	}

	public void setNewCarNum(Integer newCarNum) {
		this.newCarNum = newCarNum;
	}

	public Date getInterrogatTime() {
		return interrogatTime;
	}

	public void setInterrogatTime(Date interrogatTime) {
		this.interrogatTime = interrogatTime;
	}


	public Integer getNewCarNotKyNum() {
		return newCarNotKyNum;
	}

	public void setNewCarNotKyNum(Integer newCarNotKyNum) {
		this.newCarNotKyNum = newCarNotKyNum;
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
