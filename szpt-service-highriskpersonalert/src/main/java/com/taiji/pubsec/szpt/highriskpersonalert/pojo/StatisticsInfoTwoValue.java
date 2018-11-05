package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

public class StatisticsInfoTwoValue extends StatisticsInfo{
	private String value_two;

	public String getValue_two() {
		return value_two;
	}

	public void setValue_two(String value_two) {
		this.value_two = value_two;
	}

	public StatisticsInfoTwoValue() {
		super();
	}

	public StatisticsInfoTwoValue(String name, Number value, Number value_two) {
		super(name, value);
		this.value_two = null == value_two ? "" : value_two.toString();
	}

	public StatisticsInfoTwoValue(String name, String value, String value_two) {
		super(name, value);
		this.value_two = value_two;
	}

	
}
