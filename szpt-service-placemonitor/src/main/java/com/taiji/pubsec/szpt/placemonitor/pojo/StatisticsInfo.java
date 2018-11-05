package com.taiji.pubsec.szpt.placemonitor.pojo;

/**
 * 统计信息
 * @author wangfx
 *
 */
public class StatisticsInfo {
	
	/**
	 * key
	 */
	private String name;
	
	/**
	 * value
	 */
	private String value;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public StatisticsInfo() {
		super();
	}

	public StatisticsInfo(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public StatisticsInfo(String name, Number value) {
		super();
		this.name = name;
		this.value = null == value ? "" : value.toString();
	}

}
