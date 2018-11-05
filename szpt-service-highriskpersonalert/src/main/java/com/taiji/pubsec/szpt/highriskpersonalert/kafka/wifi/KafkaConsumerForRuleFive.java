package com.taiji.pubsec.szpt.highriskpersonalert.kafka.wifi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.dpp.wifi.bean.WarningMessageInfo;
import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.InOutTime;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnPlacesInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;

/**
 * 针对规则5的kafka消费处理类。 规则5：任意两个以上WIFI点，在2小时内，相同的2个以上同前科重点人聚集。
 * 
 * @author dixf
 *
 */
public class KafkaConsumerForRuleFive extends AbstractKafkaConsumer {

	static final Logger log = LoggerFactory
			.getLogger(KafkaConsumerForRuleFive.class);

	@Override
	protected void processData(byte[] message) {
		try {
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("MeetsOnPlacesInfo", MeetsOnPlacesInfo.class);
			classMap.put("meets", MeetRecord.class);
			classMap.put("inout", InOutTime.class);
			classMap.put("stays", StayRecord.class);
			classMap.put("wifidata", WifiData.class);
			JSONObject jsonObject = JSONObject.fromObject(new String(message, "utf-8"));
			MeetsOnPlacesInfo info = (MeetsOnPlacesInfo) JSONObject
					.toBean(jsonObject, MeetsOnPlacesInfo.class, classMap);
			log.debug("MeetsOnPlacesInfo is {}", info.toString());
			List<WifiData> wifiDatas = new ArrayList<WifiData>();
			Set<String> personNamesSet = new HashSet<String>();
			Set<String> placeSet = new HashSet<String>();
			for(int i = 0; i < info.getMeets().size(); i++){
				MeetRecord mr = info.getMeets().get(i);
				for (StayRecord sr : mr.getStays()) {
					wifiDatas.add(sr.getWifidata());
					personNamesSet.add(sr.getWifidata().getFiveColorPersonName());
					placeSet.add(sr.getWifidata().getPlaceName());
				}
			}
			
			Alert alert = new Alert();
			List<String> personNames = new ArrayList<>(personNamesSet);
			Collections.sort(personNames, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			List<String> places = new ArrayList<>(placeSet);
			Collections.sort(places, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			alert.setPlace(generateValues(places));
			alert.setPersonNames(generateValues(personNames));
			Map<String,String> warningTemplateValues = new HashMap<String,String>();
			warningTemplateValues.put(WarningMessageInfo.RULE_5_VARIABLE_PLACECOUNT, String.valueOf(info.getMeetCountOnDiffrentPlace()));
			alert.setWarning(generateWarningStr(WarningMessageInfo.RULE_5, warningTemplateValues));
			alert.setState(NODEAL_STATUS);
			alert.setKey(info.gotKey());
			
			saveAlert(wifiDatas, alert);
		} catch (Exception e) {
			log.error("发生错误", e);
			
		}

	}
}