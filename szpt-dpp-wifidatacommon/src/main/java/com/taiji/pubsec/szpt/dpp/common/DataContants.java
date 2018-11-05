package com.taiji.pubsec.szpt.dpp.common;

import java.io.Serializable;

public class DataContants implements Serializable{
	private static final long serialVersionUID = 4579765909910546999L;
	
	//kafka的集群
	public final static String BROKER = new PropertiesAnalysis().analyze("BROKER");
	//zookeeper的集群
	public final static String ZOOKEEPER = new PropertiesAnalysis().analyze("ZOOKEEPER");
	
	public final static String TOPIC_HPRDATA = "topic-wifihptrack";

	//预警类型红色人
	public final static String  RED = "0";
	//预警类型橙色人
	public final static String  ORANGE = "1";
}
