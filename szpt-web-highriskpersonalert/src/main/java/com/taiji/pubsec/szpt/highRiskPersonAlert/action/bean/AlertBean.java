package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 预警记录Bean
 * 
 * @author wangl
 *
 */
public class AlertBean {

	private String id;

	private String place;// 场所名称

	private String warning;// 预警类容

	private String personNames;// 重点人员姓名，逗号隔开

	private String state;// 状态，字典项已处理、 未处理、 已忽略

	private String stateName;// 状态字典项名称

	private List<BaseBean> personLocationList = new ArrayList<BaseBean>();// 聚集重点人位置信息集合（经纬度）
	
	private List<BaseBean> placeLocationList = new ArrayList<BaseBean>();// 监控场所位置集合

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public List<BaseBean> getPersonLocationList() {
		return personLocationList;
	}

	public void setPersonLocationList(List<BaseBean> personLocationList) {
		this.personLocationList = personLocationList;
	}

	public String getPersonNames() {
		return personNames;
	}

	public void setPersonNames(String personNames) {
		this.personNames = personNames;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<BaseBean> getPlaceLocationList() {
		return placeLocationList;
	}

	public void setPlaceLocationList(List<BaseBean> placeLocationList) {
		this.placeLocationList = placeLocationList;
	}

}
