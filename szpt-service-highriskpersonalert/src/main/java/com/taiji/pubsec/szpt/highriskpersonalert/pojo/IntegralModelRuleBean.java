package com.taiji.pubsec.szpt.highriskpersonalert.pojo;



public class IntegralModelRuleBean {

	private String id;
	
	/**
	 * key的规则见szpt-score-util子模块下的com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail的注释
	 */
	private String key;
	
	/**
	 * 数值
	 * 数值为两位数字
	 * 例： 20
	 */
	private String value;
	
	/**
	 * 人员积分模型_id
	 */
	private IntegralModelBean integarlModel;

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

	public IntegralModelBean getIntegarlModel() {
		return integarlModel;
	}

	public void setIntegarlModel(IntegralModelBean integarlModel) {
		this.integarlModel = integarlModel;
	}
	
	
	
}
