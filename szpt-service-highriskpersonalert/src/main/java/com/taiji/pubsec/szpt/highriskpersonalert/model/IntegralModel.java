package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员积分模型
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_ryjfmx")
public class IntegralModel {
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * 最低预警分数值
	 */
	@Column(name="zdyjfsz")
	private int alertPoint;
	
	/**
	 * 模型名称
	 */
	@Column(name="mc")
	private String name ;
	
	/**
	 * 修改人
	 */
	@Column(name="xgr")
	private String modifyPeople;
	
	/**
	 * 修改时间
	 */
	@Column(name="xgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime;
	
	/**
	 * 备注
	 */
	@Column(name="bz")
	private String note;
	
	/**
	 * 编号
	 */
	@Column(name="bh")
	private String num;
	
	/**
	 * 状态
	 */
	@Column(name="zt")
	private String status;
	
	/**
	 * 人员积分模型规则
	 */
	@OneToMany(mappedBy="integarlModel")
	private Set<IntegralModelRule> integralModelRule = new HashSet<IntegralModelRule>();

	public Set<IntegralModelRule> getIntegralModelRule() {
		return integralModelRule;
	}
	public void setIntegralModelRule(Set<IntegralModelRule> integralModelRule) {
		this.integralModelRule = integralModelRule;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAlertPoint() {
		return alertPoint;
	}
	public void setAlertPoint(int alertPoint) {
		this.alertPoint = alertPoint;
	}
	public String getModifyPeople() {
		return modifyPeople;
	}
	public void setModifyPeople(String modifyPeople) {
		this.modifyPeople = modifyPeople;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
