package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 预警相关信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_yjxx")
public class AlertInfo {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AlertInfo.class);

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 目标id
	 */
	@Column(name = "mbid")
	private String targetId;
	
	/**
	 * 目标类型
	 */
	@Column(name = "mblx")
	private String targetType;
	
	/**
	 * 预警记录id
	 */
	@ManyToOne
	@JoinColumn(name = "yj_id")
	private Alert alert;
	
	public AlertInfo() {
		super();
	}
	
	public AlertInfo(Object obj) {
		if (null != obj) {
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

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
}
