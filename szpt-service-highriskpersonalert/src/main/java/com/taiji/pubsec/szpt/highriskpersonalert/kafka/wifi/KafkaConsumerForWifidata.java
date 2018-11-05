package com.taiji.pubsec.szpt.highriskpersonalert.kafka.wifi;


import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;

/**
 * wifidata的kafka消费服务类
 * 
 * @author genganpeng
 *
 */
@Deprecated
public class KafkaConsumerForWifidata extends AbstractKafkaConsumer {
	

	static final Logger logger = LoggerFactory
			.getLogger(KafkaConsumerForWifidata.class);

	public KafkaConsumerForWifidata() {

	}

	/**
	 * 处理wifi出入数据 
	 * @param message
	 */
	protected void processData(byte[] message) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(new String(message));
			WifiData wifidata = (WifiData) JSONObject.toBean(jsonObject, WifiData.class);
			saveWifidata(wifidata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}