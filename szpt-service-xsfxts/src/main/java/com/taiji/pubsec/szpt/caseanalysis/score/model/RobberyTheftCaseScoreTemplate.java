package com.taiji.pubsec.szpt.caseanalysis.score.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.RobberyTheftCaseScoreTemplateRule;

/**
 * 串并案分析模板
 * 
 * @author dixiaofeng
 */
@Entity
@Table(name="t_xsajfx_cbajfmb")
public class RobberyTheftCaseScoreTemplate {
	
//	public static final String RULE_ITEM_SIMILARFEATURE = "作案特点（手段）相同";
//	public static final String RULE_ITEM_SIMILARPLACE = "选择处所相同或相似";
//	public static final String RULE_ITEM_SIMILARPERIOD = "作案时段相同或相似";
//	public static final String RULE_ITEM_SIMILARPEOPLENUM = "作案人数";
//	public static final String RULE_ITEM_SIMILARENTRY = "作案进口";
//	public static final String RULE_ITEM_SIMILAREXIT = "作案出口";
//	public static final String RULE_ITEM_SIMILARCOMMUNITY = "发案社区";

	public static final String COMMUNITYRULE_SAME = "相同社区";
	public static final String COMMUNITYRULE_NEIGHBOR = "相邻社区";
	public static final String COMMUNITYRULE_NONNEIGHBOR = "不相邻社区";

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 编码
	 */
	@Column(name="bm", length = 50)
	private String code;
	
	/**
	 * 名称
	 */
	@Column(name="mc", length = 100)
	private String name;
	
	/**
	 * 案件打标类别编码
	 */
	@Column(name="ajlx", length = 50)
	private String type;
	
	/**
	 * 打标一级性质
	 */
	@Column(name="ajxzyj", length = 50)
	private String firstSort;
	
	/**
	 * 打标二级性质
	 */
	@Column(name="ajxzej", nullable = true, length = 50)
	private String secondSort;
	
	/**
	 * 状态编码
	 */
	@Column(name="zt", length = 50)
	private String state;
	
	/**
	 * 备注
	 */
	@Column(name="bz", length = 500)
	private String remarks;
	
	/**
	 * 最低分数
	 */
	@Column(name="zxfs")
	private int minScore;
	
	/**
	 * 创建人姓名
	 */
	@Column(name="cjr", length = 50)
	private String createPerson;
	
	/**
	 * 创建时间
	 */
	@Column(name="cjsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	/**
	 * 更新人姓名
	 */
	@Column(name="gxr", length = 50)
	private String updatePerson;
	
	/**
	 * 更新时间
	 */
	@Column(name="gxsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/**
	 * 积分框架中计算任务id
	 */
	@Column(name="jsrw_id", length = 50)
	private String computeTaskId;

	@Transient
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

	public String getFirstSort() {
		return firstSort;
	}

	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}

	public String getSecondSort() {
		return secondSort;
	}

	public void setSecondSort(String secondSort) {
		this.secondSort = secondSort;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
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