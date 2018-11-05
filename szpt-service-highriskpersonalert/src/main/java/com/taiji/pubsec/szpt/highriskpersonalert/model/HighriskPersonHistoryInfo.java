package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 高危人员信息操作历史信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_czjl")
public class HighriskPersonHistoryInfo {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HighriskPersonHistoryInfo.class);
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 操作对象id
	 */
	@Column(name = "czdxId")
	private String targetId;
	
	/**
	 * 操作对象（类名）
	 */
	@Column(name = "czdxlx")
	private String targetType;
	
	/**
	 * 操作类型（常量值）
	 * 高危人群调整；人员类型调整
	 */
	@Column(name = "czlx")
	private String operateType;
	
	/**
	 * 操作方式（常量值）
	 * 高危人员调整-新增/删除；人员类型调整-新增/删除
	 */
	@Column(name = "czfs")
	private String operateMethod;
	
	/**
	 * 操作内容（编码）
	 * 高危人员调整:五色+其他；人员类型调整：人员类型
	 */
	@Column(name = "cznr")
	private String operateContent;
	
	/**
	 * 操作时间
	 */
	@Column(name = "czsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date operateTime;
	
	public HighriskPersonHistoryInfo() {
		super();
	}
	
	public HighriskPersonHistoryInfo(Object obj) {
		if(null != obj) {
			this.setTargetType(obj.getClass().getName());
			try {
				Method method = obj.getClass().getMethod("getId");
				this.setTargetId((String)method.invoke(obj, null));
			} catch (Exception e) {
				LOGGER.error("找不到此业务对象的getId方法", e);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetId() {
		return targetId;
	}

	private void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getTargetType() {
		return targetType;
	}

	private void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getOperateMethod() {
		return operateMethod;
	}

	public void setOperateMethod(String operateMethod) {
		this.operateMethod = operateMethod;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	

}
