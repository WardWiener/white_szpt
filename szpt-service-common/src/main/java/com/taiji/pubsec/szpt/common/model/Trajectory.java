package com.taiji.pubsec.szpt.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;


/** 
 * 警力轨迹   
 * @author wangfx
 *
 */
@Entity
@Table(name="t_zhzats_jwzh_gjxx")
public class Trajectory {
	
	/**
	 * id
	 */
	@Id
	private String id;
	
	/**
	 * 类型，区分设备
	 */
	@Column(name = "lx")
	private String type;
	 
	/**
	 * 定位设备标识，车 PDA的标示
	 */
	@Column(name = "dwsbbs")
	private String identifier;
	
	/**
	 * 指挥单元编码
	 */
	@Column(name = "zhdybm")
	private String orderCellCode;
	
	/**
	 * 更新时间
	 */
	@Column(name = "gxsj")
	@Index(name="index_t_zhzats_jwzh_gjxx_gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/**
	 * 经度
	 */
	@Column(name = "jd")
	private Double longitude;
	
	/**
	 * 纬度
	 */
	@Column(name = "wd")
	private Double latitude;
	
	public Trajectory() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getOrderCellCode() {
		return orderCellCode;
	}

	public void setOrderCellCode(String orderCellCode) {
		this.orderCellCode = orderCellCode;
	}

}
