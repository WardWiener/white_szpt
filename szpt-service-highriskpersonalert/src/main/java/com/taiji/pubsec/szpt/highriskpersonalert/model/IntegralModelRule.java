package com.taiji.pubsec.szpt.highriskpersonalert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员积分模型规则
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_ryjfmxgz")
public class IntegralModelRule {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String id;
	
	/**
	 * key的规则见szpt-score-util子模块下的com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail的注释
	 */
	@Column(name="gjz")
	private String key;
	
	/**
	 * 数值
	 * 数值为两位数字
	 * 例： 20
	 */
	@Column(name="sz")
	private String value;
	
	/**
	 * 人员积分模型_id
	 */
	@ManyToOne
	@JoinColumn(name = "ryjfmx_id")
	private IntegralModel integarlModel;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public IntegralModel getIntegarlModel() {
		return integarlModel;
	}

	public void setIntegarlModel(IntegralModel integarlModel) {
		this.integarlModel = integarlModel;
	}
	
	
}
