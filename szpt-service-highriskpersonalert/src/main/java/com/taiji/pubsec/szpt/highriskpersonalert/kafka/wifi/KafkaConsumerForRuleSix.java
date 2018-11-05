package com.taiji.pubsec.szpt.highriskpersonalert.kafka.wifi;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.dpp.wifi.bean.WarningMessageInfo;
import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.inoutnumber.bean.InoutNumOnEveryPlaceInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;

/**
 * 针对规则6的kafka消费处理类。 规则6：距离当前时间的一周内，出入任意WIFI点超过100次。
 * 
 * @author dixf
 *
 */
public class KafkaConsumerForRuleSix extends AbstractKafkaConsumer {

	static final Logger log = LoggerFactory
			.getLogger(KafkaConsumerForRuleSix.class);

	@Override
	protected void processData(byte[] message) {
		try {
			InoutNumOnEveryPlaceInfo info = fromJsonData(new String(message, "utf-8"));
			
			String personName = highriskPersonService.findByMac(info.getMac()).getName();
			List<WifiData> wifiDatas = new ArrayList<WifiData>();
			Set<String> placeSet = new HashSet<String>();
			for(Entry<String, List<StayRecord>>  entry : info.getDetails().entrySet()){
				for(StayRecord sr : entry.getValue()) {
					wifiDatas.add(sr.getWifidata());
					placeSet.add(sr.getWifidata().getPlaceName());
				}
			}
			List<String> places = new ArrayList<>(placeSet);
			Collections.sort(places, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			Alert alert = new Alert();
			Map<String,String> warningTemplateValues = new HashMap<String,String>();
			warningTemplateValues.put(WarningMessageInfo.RULE_6_VARIABLE_TOTALCOUNT, String.valueOf(info.getCount()));
			alert.setWarning(generateWarningStr(WarningMessageInfo.RULE_6, warningTemplateValues));
			alert.setState(NODEAL_STATUS);
			alert.setPlace(generateValues(places));
			alert.setPersonNames(personName);
			
			saveAlert(wifiDatas, alert);
		} catch (Exception e) {
			log.error("发生错误", e);
			
		}

	}
	
	private InoutNumOnEveryPlaceInfo fromJsonData(String jsonStr){
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		String mac = jsonObject.getString("mac");
		JSONObject detailJson = jsonObject.getJSONObject("details");
		Map<String,List<StayRecord>> details = new HashMap<String,List<StayRecord>>();
		for(Object entry  : detailJson.keySet()) {
			JSONArray list  = detailJson.getJSONArray(entry.toString());
			List<StayRecord> records = new ArrayList<StayRecord>();
			for(int i = 0 ; i< list.size(); i++) {
				JSONObject jo = list.getJSONObject(i);
				Map<String, Class> classMap = new HashMap<String, Class>();
				classMap.put("wifidata", WifiData.class);
				StayRecord sr = (StayRecord) JSONObject.toBean(jo, StayRecord.class, classMap);
				records.add(sr);
			}
			details.put(entry.toString(), records);
		}
		InoutNumOnEveryPlaceInfo info = new InoutNumOnEveryPlaceInfo();
		info.setMac(mac);
		info.setDetails(details);
		return info;
	}
}