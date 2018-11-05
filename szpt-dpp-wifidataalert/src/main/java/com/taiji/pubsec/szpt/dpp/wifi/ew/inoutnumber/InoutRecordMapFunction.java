/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.inoutnumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.spark.api.java.function.PairFlatMapFunction;

import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StaysOnOnePlaceHolder;
import com.taiji.pubsec.szpt.dpp.wifi.ew.inoutnumber.bean.InoutNumOnEveryPlaceInfo;

import scala.Tuple2;

/**
 * 将每个场所所有人的停留记录映射成<mac，InoutNumOnEveryPlaceInfo>的形式，为后面的迭代运算做准备
 * 
 * @author yucy
 *
 */
public class InoutRecordMapFunction implements PairFlatMapFunction<Tuple2<String, StaysOnOnePlaceHolder>, String, InoutNumOnEveryPlaceInfo> {

	private static final long serialVersionUID = -3372499943970027276L;

	@Override
	public Iterable<Tuple2<String, InoutNumOnEveryPlaceInfo>> call(Tuple2<String, StaysOnOnePlaceHolder> tuple2) throws Exception {
		StaysOnOnePlaceHolder holder = tuple2._2;
		Map<String, List<StayRecord>> stays = holder.getStays();
		
		List<Tuple2<String, InoutNumOnEveryPlaceInfo>> result = new ArrayList<>();
		for(Entry<String, List<StayRecord>> entry : stays.entrySet()){
			List<StayRecord> StayRecords = entry.getValue();
			
			for (StayRecord starRecord : StayRecords) {
				InoutNumOnEveryPlaceInfo inout = new InoutNumOnEveryPlaceInfo(starRecord.getMac());
				List<StayRecord> staylist = new ArrayList<>();
				staylist.add(starRecord);
				inout.getDetails().put(starRecord.getPlacecode(), staylist);
				
				Tuple2<String, InoutNumOnEveryPlaceInfo> t = new Tuple2<String, InoutNumOnEveryPlaceInfo>(inout.getMac(), inout);
				result.add(t);
				
			}
		}
		
		return result;
	}

}
