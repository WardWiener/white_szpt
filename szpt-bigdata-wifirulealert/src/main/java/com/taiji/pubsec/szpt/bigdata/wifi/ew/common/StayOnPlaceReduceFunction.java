/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.spark.api.java.function.Function2;

import com.taiji.pubsec.szpt.bigdata.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.bigdata.wifi.ew.bean.StaysOnOnePlaceHolder;

/**
 * 合并原始的wifi数据，将每个场所中mac的wifi数据合并
 * 
 * @author yucy
 *
 */
public class StayOnPlaceReduceFunction implements Function2<StaysOnOnePlaceHolder, StaysOnOnePlaceHolder, StaysOnOnePlaceHolder> {

	private static final long serialVersionUID = -5442355736262145335L;

	@Override
	public StaysOnOnePlaceHolder call(StaysOnOnePlaceHolder a, StaysOnOnePlaceHolder b) throws Exception {
		
		StaysOnOnePlaceHolder staysholder = new StaysOnOnePlaceHolder(a.getPlaceCode());
		
		Map<String, List<StayRecord>> stays = new HashMap<String, List<StayRecord>>();
		
		for(Entry<String, List<StayRecord>> entry : a.getStays().entrySet()){
			String mac = entry.getKey();
			List<StayRecord> stayas = entry.getValue();
			if(b.getStays().containsKey(mac)){
				List<StayRecord> staysb = b.getStays().get(mac);
				stayas.addAll(staysb);
			}
			
			stays.put(mac, stayas);
		}
		
		//将a中未包含b的出入记录也加入返回结果中
		for(Entry<String, List<StayRecord>> entry : b.getStays().entrySet()){
			if( !stays.containsKey(entry.getKey()) ){
				stays.put(entry.getKey(), entry.getValue());
			}
		}
		staysholder.getStays().putAll(stays);
		
		return staysholder;
	}

}
