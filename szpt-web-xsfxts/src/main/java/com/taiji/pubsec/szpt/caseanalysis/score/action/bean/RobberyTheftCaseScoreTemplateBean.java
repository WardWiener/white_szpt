package com.taiji.pubsec.szpt.caseanalysis.score.action.bean;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.RobberyTheftCaseScoreTemplateRule;

/**
 * 串并案分析模板Bean
 * 
 * @author WangLei
 *
 */
public class RobberyTheftCaseScoreTemplateBean {

	private String id;
	
	private String code;// 编码
	
	private String name;// 名称
	
	private String type;// 案件打标类别编码
	
	private String typeName;// 案件打标类别名称
	
	private String firstSort;// 打标一级性质
	
	private String firstSortName;// 打标一级性质名称
	
	private String secondSort;// 打标二级性质
	
	private String secondSortName;// 打标二级性质名称
	
	private String state;// 状态编码
	
	private String stateName;// 状态编码名称
	
	private String remarks;// 备注
	
	private int minScore;// 最低分数
	
	private String createPerson;// 创建人姓名
	
	private Long createdTime;// 创建时间
	
	private String updatePerson;// 更新人姓名
	
	private Long updateTime;// 更新时间
	
	private String computeTaskId;// 积分框架中计算任务id
	
	private List<RobberyTheftCaseScoreTemplateRule> rules = new ArrayList<RobberyTheftCaseScoreTemplateRule>();

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFirstSort() {
		return firstSort;
	}

	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}

	public String getFirstSortName() {
		return firstSortName;
	}

	public void setFirstSortName(String firstSortName) {
		this.firstSortName = firstSortName;
	}

	public String getSecondSort() {
		return secondSort;
	}

	public void setSecondSort(String secondSort) {
		this.secondSort = secondSort;
	}

	public String getSecondSortName() {
		return secondSortName;
	}

	public void setSecondSortName(String secondSortName) {
		this.secondSortName = secondSortName;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getComputeTaskId() {
		return computeTaskId;
	}

	public void setComputeTaskId(String computeTaskId) {
		this.computeTaskId = computeTaskId;
	}

	public List<RobberyTheftCaseScoreTemplateRule> getRules() {
		return rules;
	}

	public void setRules(List<RobberyTheftCaseScoreTemplateRule> rules) {
		this.rules = rules;
	}
	
	
}
