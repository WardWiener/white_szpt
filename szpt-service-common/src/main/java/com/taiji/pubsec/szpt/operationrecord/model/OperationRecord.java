package com.taiji.pubsec.szpt.operationrecord.model;

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
 * 操作记录
 * @author wangfx
 *
 */
@Entity
@Table(name = "t_czjl")
public class OperationRecord {
	private final static Logger logger = LoggerFactory.getLogger(OperationRecord.class);
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	@Column(name = "ywdxlx")
	private String targetType;	//业务对象类型，全类名
	
	@Column(name = "ywdxid")
	private String targetId;	//业务对象id
	
	@Column(name = "cznr")
	private String content;		//操作内容
	
	@Column(name = "czjg")
	private String result;		//操作结果
	
	@Column(name = "czr")
	private String operator;	//操作人姓名
	
	@Column(name = "czdw")
	private String operateUnit;	//操作单位名称
	
	@Column(name = "czsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date operateTime;	//操作时间

	public OperationRecord(Object obj) {
		if (null != obj) {
			this.setTargetType(obj.getClass().getName());
			try {
				Method method = obj.getClass().getMethod("getId");
				this.setTargetId((String)method.invoke(obj, null));
			} catch (Exception e) {
				logger.error("找不到此业务对象的getId方法", e);
			}
		}
	}
	
	private OperationRecord() {
		super();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetType() {
		return targetType;
	}

	private void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetId() {
		return targetId;
	}

	private void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateUnit() {
		return operateUnit;
	}

	public void setOperateUnit(String operateUnit) {
		this.operateUnit = operateUnit;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
