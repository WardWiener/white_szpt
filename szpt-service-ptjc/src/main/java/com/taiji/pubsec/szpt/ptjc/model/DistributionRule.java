package com.taiji.pubsec.szpt.ptjc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 分布规则
 * @author huangda
 *
 */
@Entity(name="com.taiji.pubsec.szpt.ptjc.model.DistributionRule")
@Table(name="t_ptjc_fbgz")
public class DistributionRule {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id ;

	/**
	 * 警情类别(字典项)
	 */
	@Column(name="jqlb")
	private String alarmTypeCode ;
	
	/**
	 * 目标编码
	 */
	@Column(name="mbbm")
	private String targetCode ;

	/**
	 * 目标名称
	 */
	@Column(name="mbmc")
	private String targetName ;
	
	/**
	 * 目标id
	 */
	@Column(name="mbid")
	private String targetId ;

	/**
	 * 目标类型
	 */
	@Column(name="mblx")
	private String targetType ;
	
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

	public String getAlarmTypeCode() {
		return alarmTypeCode;
	}

	public void setAlarmTypeCode(String alarmTypeCode) {
		this.alarmTypeCode = alarmTypeCode;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
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
