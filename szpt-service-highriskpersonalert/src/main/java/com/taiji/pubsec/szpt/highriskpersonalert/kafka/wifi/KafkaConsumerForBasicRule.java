package com.taiji.pubsec.szpt.highriskpersonalert.kafka.wifi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.dpp.wifi.bean.WarningMessageInfo;
import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.common.DataContants;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;
import com.taiji.pubsec.szpt.highriskpersonalert.model.AlertInfo;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;

/**
 * kafka消费的服务类
 * 
 * @author genganpeng
 *
 */
public class KafkaConsumerForBasicRule extends AbstractKafkaConsumer {
	static final Logger logger = LoggerFactory
			.getLogger(KafkaConsumerForBasicRule.class);

	/**
	 * 处理预警的消息
	 * @param message
	 */
	protected void processData(byte[] message) {
		try {
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("wifiDatas", WifiData.class);
			JSONObject jsonObject = JSONObject.fromObject(new String(message, "utf-8"));
			WarningMessageInfo info = (WarningMessageInfo) JSONObject.toBean(
					jsonObject, WarningMessageInfo.class, classMap);
			
			Collections.sort(info.getPlaces(), new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			Collections.sort(info.getWifiDatas(), new Comparator<WifiData>() {
				@Override
				public int compare(WifiData o1, WifiData o2) {
					return o1.getFiveColorPersonName().compareTo(o2.getFiveColorPersonName());
				}
			});

			Alert alert = new Alert();
			String strPlaces = generateValues(info.getPlaces());
			List<String> personNames = new ArrayList<String>();
			for (WifiData wifiData : info.getWifiDatas()) {
				personNames.add(wifiData.getFiveColorPersonName());
			}
			String strPrsonName = generateValues(personNames);

			StringBuilder sbWarncontent = new StringBuilder();
			switch (info.getRule()) {
			case WarningMessageInfo.RULE_1:
				sbWarncontent.append("有超过3个重点人聚集，驻留超过5分钟");
				break;
			case WarningMessageInfo.RULE_2:
				// 找到橙色人员和其他人员
				StringBuilder sbOrange = new StringBuilder();
				StringBuilder sbOther = new StringBuilder();
				for (WifiData wifiData : info.getWifiDatas()) {
					if (wifiData.getWarnType().equals(DataContants.ORANGE)) {
						if (sbOrange.length() > 0) {
							sbOrange.append(","
									+ wifiData.getFiveColorPersonName());
						} else {
							sbOrange.append(wifiData.getFiveColorPersonName());
						}
					} else {
						if (sbOther.length() > 0) {
							sbOther.append(","
									+ wifiData.getFiveColorPersonName());
						} else {
							sbOther.append(wifiData.getFiveColorPersonName());
						}
					}
				}
				sbWarncontent.append("橙色重点人" + sbOrange.toString() + "与其他重点人"
						+ sbOther.toString() + "聚集，驻留超过5分钟");
				break;
			case WarningMessageInfo.RULE_3:
				sbWarncontent.append("红色重点人出现");
				break;
			case WarningMessageInfo.RULE_4:
				sbWarncontent.append("超过2人同前科重点人聚集，驻留时间超过15分钟");
				break;
			case WarningMessageInfo.RULE_7:
				sbWarncontent.append("此点为居住小区，盗窃前科人员，驻留超过30分钟");
				break;
			default:
				break;
			}

			alert.setPlace(strPlaces);
			alert.setWarning(sbWarncontent.toString());
			alert.setPersonNames(strPrsonName);
			alert.setState(NODEAL_STATUS);
			//对于没有保存过的wifi命中率数据保存
			for (WifiData wifiData : info.getWifiDatas()) {
				saveWifidata(wifiData);
			}

			//根据条件查询之前是否已经生成过此预警
			List<Alert> list = alertService.findByAlert(alert);
			
			// 表示此预警是否已经生成过
			boolean flag = false;
			for (Alert tmpAlert : list) {
				Set<AlertInfo> alertInfos = tmpAlert.getAlertInfos();
				if (info.getWifiDatas().size() != alertInfos.size()) {
					continue;
				}
				int count = 0;
				for (AlertInfo alertInfo : alertInfos) {
					WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = wifiPlaceInAndOutInfoQueryService.findById(alertInfo.getTargetId());
					for (WifiData wifiData : info.getWifiDatas()) {
						if (wifiPlaceInAndOutInfo.getId()
								.equals(wifiData.getId())) {
							count++;
							break;
						}
					}
				}

				// 此预警之前已经保存过不再保存
				if (info.getWifiDatas().size() == count) {
					flag = true;
				}
			}

			// 此预警已经生成过了则退出
			if (flag == true) {
				return;
			}

			// 保存预警消息
			alertService.saveAlert(alert);
			for (WifiData wifiData : info.getWifiDatas()) {
				WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = (WifiPlaceInAndOutInfo) wifiPlaceInAndOutInfoQueryService.findById(wifiData.getId());
				AlertInfo alertInfo = new AlertInfo(wifiPlaceInAndOutInfo);
				alertInfo.setAlert(alert);
				alertInfoService.saveAlertInfo(alertInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}