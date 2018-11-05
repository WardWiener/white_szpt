package com.taiji.pubsec.szpt.zagl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

/**
 * 专案角色
 * @author 
 */
@Entity
@Table(name="t_zagl_zajs")
public class SpecialCaseRole {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 编码
	 */
	@Column(name = "bm")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "mc")
	private String name;
	/**
	 * 状态：启用(1)、停用(0)
	 */    
	@Column(name = "zt")
	private String state;
	/**
	 * 类型：预置(0)、自定义(1)
	 */
	@Column(name = "lx")
	private String type;

	public SpecialCaseRole(){

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}