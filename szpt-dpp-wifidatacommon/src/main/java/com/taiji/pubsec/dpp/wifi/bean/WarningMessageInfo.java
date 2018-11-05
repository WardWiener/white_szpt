package com.taiji.pubsec.dpp.wifi.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 预警消息内容
 * @author genganpeng
 *
 */
public class WarningMessageInfo implements Serializable{
	private static final long serialVersionUID = 555408089963874730L;
	
	public static final String RULE_5_VARIABLE_PLACECOUNT = "placeCount";
	public static final String RULE_6_VARIABLE_TOTALCOUNT = "totalCount";
	public static final String RULE_1 = "任意WIFI点，有超过3个重点人聚集，且3个重点人每人的驻留时间都超过5分钟。";
	public static final String RULE_2 = "对于橙色人，在任意WIFI点，与其他任意重点人聚集，且驻留时间超过5分钟。";
	public static final String RULE_3 = "当红色重点人出现在任意WIFI点。";
	public static final String RULE_4 = "任意WIFI点，有超过2个同前科重点人聚集，且驻留时间超过15分钟";
	public static final String RULE_5 = "在2小时内，相同的2个以上同前科重点人在${" + RULE_5_VARIABLE_PLACECOUNT + "}个不同场所聚集。";
	public static final String RULE_6 = "距离当前时间的一周内，出入任意WIFI监控场所${"+ RULE_6_VARIABLE_TOTALCOUNT + "}次。";
	public static final String RULE_7 = "位于居住小区的WIFI点，有盗窃前科人员，驻留时间超过30分钟。";
	
	public final static String topic_alert = "topic-wifialert";
	
	//场所的名称
	private List<String> places = new ArrayList<String>();
	private List<WifiData> wifiDatas = new ArrayList<WifiData>();
	private String rule;
	
	public WarningMessageInfo() {
		super();
	}
	public WarningMessageInfo(List<String> places, List<WifiData> wifiDatas,
			String rule) {
		super();
		this.places = places;
		this.wifiDatas = wifiDatas;
		this.rule = rule;
	}
	
	/**
	 * 场所名称
	 * @return
	 */
	public List<String> getPlaces() {
		return places;
	}
	public void setPlaces(List<String> places) {
		this.places = places;
	}
	
	/**
	 * 违反规则相关WifiData信息
	 * @return
	 */
	public List<WifiData> getWifiDatas() {
		return wifiDatas;
	}
	public void setWifiDatas(List<WifiData> wifiDatas) {
		this.wifiDatas = wifiDatas;
	}
	
	/**
	 * 违反的规则 RULE_1,RULE_2,....,RULE_7
	 * @return
	 */
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
}
