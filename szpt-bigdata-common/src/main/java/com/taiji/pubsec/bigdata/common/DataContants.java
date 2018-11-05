package com.taiji.pubsec.bigdata.common;

import java.io.Serializable;

import com.taiji.pubsec.szpt.bigdata.jdbc.PropertiesAnalysis;

public class DataContants implements Serializable{
	//kafka的集群
	public final static String BROKER = new PropertiesAnalysis().analyze("BROKER");
	//zookeeper的集群
	public final static String ZOOKEEPER = new PropertiesAnalysis().analyze("ZOOKEEPER");
	
	public final static String FIVECOLORDATA = "fivecolordata";

	//预警类型红色人
	public final static String  RED = "0";
	//预警类型橙色人
	public final static String  ORANGE = "1";
}
