package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.ArrayList;
import java.util.List;


/**
 * 人员分数模型bean
 * @author sunjd
 *
 */
public class IntegralModelBean {

	private String id;
	
	/**
	 * 最低预警分数值
	 */
	private String alertPoint;
	
	/**
	 * 模型名称
	 */
	private String name;
	
	/**
	 * 修改人
	 */
	private String modifyPeople;
	/**
	 * 修改人姓名
	 */
	private String modifyPeopleName;
	
	/**
	 * 修改时间
	 */
	private String modifyTime;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 编号
	 */
	private String num;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 状态名称
	 */
	private String statusName;
	
	/**
	 * 人员积分模型规则
	 */
	private List<IntegralModelRuleBean> integralModelRule = new ArrayList<IntegralModelRuleBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getAlertPoint() {
		return alertPoint;
	}

	public void setAlertPoint(String alertPoint) {
		this.alertPoint = alertPoint;
	}

	public String getModifyPeople() {
		return modifyPeople;
	}

	public void setModifyPeople(String modifyPeople) {
		this.modifyPeople = modifyPeople;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getModifyPeopleName() {
		return modifyPeopleName;
	}

	public void setModifyPeopleName(String modifyPeopleName) {
		this.modifyPeopleName = modifyPeopleName;
	}

	public List<IntegralModelRuleBean> getIntegralModelRule() {
		return integralModelRule;
	}

	public void setIntegralModelRule(List<IntegralModelRuleBean> integralModelRule) {
		this.integralModelRule = integralModelRule;
	}
	
	
}
