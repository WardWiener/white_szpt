package com.taiji.pubsec.szpt.ptjc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 刑事常量
 * @author huangda
 *
 */
@Entity(name="com.taiji.pubsec.szpt.ptjc.model.PenalConstant")
@Table(name="t_ptjc_xscl")
public class PenalConstant {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id ;

	/**
	 * 时间类型(字典项  日/周/月/年)
	 */
	@Column(name="sjlx")
	private String type;
	
	/**
	 * 单位编码
	 */
	@Column(name="dwbm")
	private String unitCode;

	/**
	 * 单位名称
	 */
	@Column(name="dwmc")
	private String unitName ;
	
	/**
	 * 颜色(字典项 蓝/黄/橙/红)
	 */
	@Column(name="ys")
	private String color ;
	
	/**
	 * 范围
	 */
	@Column(name="fw")
	private String range ;

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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
}
