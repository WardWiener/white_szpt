/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.basedplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.taiji.pubsec.bigdata.bean.WarningMessageInfo;
import com.taiji.pubsec.bigdata.bean.WifiData;
import com.taiji.pubsec.bigdata.common.DataContants;
import com.taiji.pubsec.szpt.bigdata.kafka.producer.KafkaProducer;

/**
 * @author yucy
 *
 */
public class BasedPlaceCalcHelper {
	
	/**
	 * 根据一条wifidata数据，构建出场所统计的数据结构，以便下一步的迭代计算
	 */
	public static void generateData(WifiData wifiData, PlaceStatistics result) {
		if (wifiData == null || StringUtils.isEmpty(wifiData.getMac()))
			return;
		// 停留超过五分钟的人
		List<WifiData> stayOver5Min = new ArrayList<>();
		// 橙色人且停留超过五分钟
		List<WifiData> stayOver5MinOfOrange = new ArrayList<>();
		// 红色人出现
		List<WifiData> redColorPeoples = new ArrayList<>();
		// 停留超过15分钟的人
		List<WifiData> stayOver15Min = new ArrayList<>();
		// 有盗窃前科人员，驻留时间超过30分钟
		List<WifiData> stayOver30MinOfTheft = new ArrayList<>();
		//表示当前时间
		Long now =  Long.valueOf(wifiData.getGatherTime());
		
		if (!StringUtils.isEmpty(wifiData.getLeaveTime())) {
			Long leaveTime = Long.valueOf(wifiData.getLeaveTime());
			//如果离开时间大于当前采集时间则设置离开时间
			if (now - leaveTime <= 0) {
				now = leaveTime;
			}
		}
		
		// 停留超过五分钟
		boolean over5minu = (now - Long.valueOf(wifiData.getEnterTime())) - (5 * 60 * 1000) > 0;
		if (over5minu) {
			if (wifiData.getWarnType().equals(DataContants.ORANGE)) {
				// 橙色人
				stayOver5MinOfOrange.add(wifiData);
			}else{
				stayOver5Min.add(wifiData);
			}
		}

		if (wifiData.getWarnType().equals(DataContants.RED)) {
			// 红色人出现
			redColorPeoples.add(wifiData);
		}

		// 停留超过15分钟
		boolean over15minu = (now - Long.valueOf(wifiData.getEnterTime())) - (15 * 60 * 1000) > 0;
		if (over15minu) {
			stayOver15Min.add(wifiData);
		}

		// 是居住小区，并且之前有过盗窃前科
		boolean over30minu = (now - Long.valueOf(wifiData.getEnterTime())) - (30 * 60 * 1000) > 0;
		if (wifiData.isResidentialArea() && wifiData.isTheft() && over30minu) {
			stayOver30MinOfTheft.add(wifiData);
		}
		
		/*
		 * 更新数据统计的结果
		 */
		if(!stayOver5Min.isEmpty()){
			result.setStayOver5Min(stayOver5Min);
		}
		if(!stayOver5MinOfOrange.isEmpty()){
			result.setStayOver5MinOfOrange(stayOver5MinOfOrange);
		}
		if(!redColorPeoples.isEmpty()){
			result.setRedColorPeoples(redColorPeoples);
		}
		if(!stayOver15Min.isEmpty()){
			result.setStayOver15Min(stayOver15Min);
		}
		if(!stayOver30MinOfTheft.isEmpty()){
			result.setStayOver30MinOfTheft(stayOver30MinOfTheft);
		}
	}

	/**
	 * 超过3个重点人停留超过五分钟
	 * 
	 */
	public static void over3PeopleStayOver5Min(List<WifiData> stayOver5Min, List<WifiData> stayOver5MinOfOrange, List<String> places, KafkaProducer producer) {
		// 超过3个重点人停留超过五分钟
		if (stayOver5Min.size() + stayOver5MinOfOrange.size() >= 3) {
			List<WifiData> list = new ArrayList<WifiData>();
			list.addAll(stayOver5MinOfOrange);
			list.addAll(stayOver5Min);
			WarningMessageInfo messageInfo = new WarningMessageInfo(places, list, WarningMessageInfo.RULE_1);
			// 向kafka发送消息
			producer.sendData(WarningMessageInfo.topic_alert, JSONObject.fromObject(messageInfo).toString().getBytes());
		}
	}
	
	/**
	 * 对于橙色人，在任意WIFI点，与其他任意重点人聚集，且驻留时间超过5分钟。
	 * 
	 */
	public static void orangePeopleAndOtherStayOver5Min( List<WifiData> stayOver5Min, List<WifiData> stayOver5MinOfOrange, List<String> places, KafkaProducer producer) {
		// 对于橙色人，在任意WIFI点，与其他任意重点人聚集，且驻留时间超过5分钟。
		if ((stayOver5Min.size() > 0 && stayOver5MinOfOrange.size() > 0) || stayOver5MinOfOrange.size() > 1) {
			List<WifiData> list = new ArrayList<WifiData>();
			list.addAll(stayOver5MinOfOrange);
			list.addAll(stayOver5Min);
			WarningMessageInfo messageInfo = new WarningMessageInfo(places, list, WarningMessageInfo.RULE_2);
			// 向kafka发送消息
			producer.sendData(WarningMessageInfo.topic_alert, JSONObject.fromObject(messageInfo).toString().getBytes());
		}
	}

	/**
	 * 当红色重点人出现在任意WIFI点。
	 * 
	 */
	public static void redColorPeopleFound(List<WifiData> redColorPeoples, List<String> places, KafkaProducer producer) {
		// 当红色重点人出现在任意WIFI点。
		if (redColorPeoples.size() > 0) {
			WarningMessageInfo messageInfo = new WarningMessageInfo(places, redColorPeoples, WarningMessageInfo.RULE_3);
			// 向kafka发送消息
			producer.sendData(WarningMessageInfo.topic_alert, JSONObject.fromObject(messageInfo).toString().getBytes());
		}
	}
	
	/**
	 * 有盗窃前科人员，驻留时间超过30分钟
	 * 
	 */
	public static void theftStayOver30MinRule( List<WifiData> stayOver30MinOfTheft, List<String> places, KafkaProducer producer) {
		if (stayOver30MinOfTheft != null && stayOver30MinOfTheft.size() > 0) {
			WarningMessageInfo messageInfo = new WarningMessageInfo(places, stayOver30MinOfTheft, WarningMessageInfo.RULE_7);
			// 向kafka发送消息
			producer.sendData(WarningMessageInfo.topic_alert, JSONObject.fromObject(messageInfo).toString().getBytes());
		}
	}

	/**
	 * 任意WIFI点，有超过2个同前科重点人聚集，且驻留时间超过15分钟
	 * 
	 */
	public static void sameCrinimalStayOver15MinRule( List<WifiData> stayOver15Min, List<String> places, KafkaProducer producer) {
		// 任意WIFI点，有超过2个同前科重点人聚集，且驻留时间超过15分钟。
		if (stayOver15Min.size() >= 2) {
			Map<String, List<WifiData>> map = new HashMap<String, List<WifiData>>();
			for (WifiData data : stayOver15Min) {
				for (String record : data.getPeopleType()) {
					// map中key为前科类型，value为对应的由此前科类型的人
					if (map.get(record) == null) {
						List<WifiData> list = new ArrayList<WifiData>();
						list.add(data);
						map.put(record, list);
					} else {
						List<WifiData> list = map.get(record);
						list.add(data);
						map.put(record, list);
					}
				}
			}

			Set<String> set = map.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				List<WifiData> list = map.get(it.next());
				if (list.size() >= 2) {
					WarningMessageInfo messageInfo = new WarningMessageInfo( places, list, WarningMessageInfo.RULE_4);
					// 向kafka发送消息
					producer.sendData(WarningMessageInfo.topic_alert, JSONObject.fromObject(messageInfo).toString().getBytes());
				}
			}
		}
	}

	/*
	 * 将added的值累加到result中
	 */
	public static void accumulatPlaceStatistics(PlaceStatistics result, PlaceStatistics added) {
		if (added == null)
			return;
		result.getRedColorPeoples().addAll(added.getRedColorPeoples());
		result.getStayOver15Min().addAll(added.getStayOver15Min());
		result.getStayOver30MinOfTheft().addAll(added.getStayOver30MinOfTheft());
		result.getStayOver5Min().addAll(added.getStayOver5Min());
		result.getStayOver5MinOfOrange().addAll(added.getStayOver5MinOfOrange());
	}
}
