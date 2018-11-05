/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年11月14日 下午4:13:42
 */
package com.taiji.pubsec.scoreframework.monitor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yucy
 *
 */
@Entity
@Table(name = "t_score_monitor_process")
public class ComputeProcess implements Serializable {

	private static final long serialVersionUID = 4098676985551355979L;
	
	public static final String COMPUTE_PROCESS_STATUS_START = "start" ;
	public static final String COMPUTE_PROCESS_STATUS_FINISH = "finish" ;
	public static final String COMPUTE_PROCESS_STATUS_ERROR = "error" ;
	
	public static final String COMPUTE_PROCESS_TYPE_HRP = "hrp" ;
	public static final String COMPUTE_PROCESS_TYPE_CASE = "case" ;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(name = "startTime", nullable = false)
	private Date startTime;
	
	@Column(name = "endTime")
	private Date endTime;
	
	@Column(name = "completedNum", nullable = false)
	private Long completedNum;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "totalNum", nullable = false)
	private Long totalNum;
	
	@Column(name = "computeType")
	private String computeType;
	
	@Column(name = "targetId")
	private String targetId ;
	
	@Column(name = "targetType")
	private String targetType ;
	
	@SuppressWarnings("unused")
	private ComputeProcess(){
		
	}
	
	public ComputeProcess(String status, Date startTime, Long totalNum, String computeType){
		this.status = status ;
		this.startTime = startTime ;
		this.totalNum = totalNum ;
		this.computeType = computeType ;
		this.completedNum = 0L ;
	}
	
	public ComputeProcess(String status, Date startTime, Long totalNum, String computeType, String targetId, String targetType){
		this.status = status ;
		this.startTime = startTime ;
		this.totalNum = totalNum ;
		this.computeType = computeType ;
		this.targetId = targetId ;
		this.targetType = targetType ;
		this.completedNum = 0L ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getCompletedNum() {
		return completedNum;
	}

	public void setCompletedNum(Long completedNum) {
		this.completedNum = completedNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public String getComputeType() {
		return computeType;
	}

	public void setComputeType(String computeType) {
		this.computeType = computeType;
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
}
