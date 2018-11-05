package com.taiji.pubsec.szpt.zagl.action.bean;

import java.util.List;


public class SpecialCaseAndSonBean {
	
	private String id;

	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 案情简介
	 */
	private String content;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 性质
	 */
	private String nature;
	/**
	 * 下步计划
	 */
	private String plan;
	/**
	 * 主要问题
	 */
	private String problem;
	/**
	 * 进展
	 */
	private String progress;
	
	/**
	 * 子案件集合
	 */
	private List<CaseRelationBean> sonList;

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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public List<CaseRelationBean> getSonList() {
		return sonList;
	}
	public void setSonList(List<CaseRelationBean> sonList) {
		this.sonList = sonList;
	}
	

}
