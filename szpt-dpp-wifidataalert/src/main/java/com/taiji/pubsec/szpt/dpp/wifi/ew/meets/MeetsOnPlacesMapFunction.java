/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.meets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.spark.api.java.function.PairFlatMapFunction;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnOnePlaceHolder;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnPlacesInfo;

import scala.Tuple2;

/**
 * 将每个相遇映射成<gotIdentityForMac(), MeetsOnPlacesInfo>，
 * 为统计每个mac组合相同的相遇在各个场所出现次数做准备。
 * 
 * @author yucy
 *
 */
public class MeetsOnPlacesMapFunction implements PairFlatMapFunction<Tuple2<String,MeetsOnOnePlaceHolder>, String, MeetsOnPlacesInfo> {
	private static final long serialVersionUID = -5122881429920685244L;

	@Override
	public Iterable<Tuple2<String, MeetsOnPlacesInfo>> call(Tuple2<String, MeetsOnOnePlaceHolder> tuple2) throws Exception {
//		System.out.println("MeetsOverTwoPlacesMapFunction.call : " + tuple2._2);
		
//		String placeCode = tuple2._1;
		MeetsOnOnePlaceHolder meetholder = tuple2._2;
		List<Tuple2<String, MeetsOnPlacesInfo>> list = new ArrayList<>();
		for(Entry<String, MeetRecord> entry : meetholder.getMeets().entrySet()){
			MeetsOnPlacesInfo holder = new MeetsOnPlacesInfo();
//			holder.setMeetCount(1);
			holder.getMeets().add(entry.getValue());
			holder.setMeetCountOnDiffrentPlace(1);
			
			//将返回的值（两个mac）作为key，针对同样的两个人相遇做迭代计算统计相遇的场所
			Tuple2<String, MeetsOnPlacesInfo> tuple = new Tuple2<>(entry.getValue().gotIdentityForMac(), holder);
			list.add(tuple);
		}
		return list;
	}
}
