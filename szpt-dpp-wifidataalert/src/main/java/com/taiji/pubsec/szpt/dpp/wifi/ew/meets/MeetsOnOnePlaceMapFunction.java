/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.meets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.spark.api.java.function.PairFunction;

import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.InOutTime;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StaysOnOnePlaceHolder;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnOnePlaceHolder;

import scala.Tuple2;

/**
 * 通过每个场所的停留记录，计算出并映射成相遇记录
 * 
 * @author yucy
 *
 */
public class MeetsOnOnePlaceMapFunction implements PairFunction<Tuple2<String, StaysOnOnePlaceHolder>, String, MeetsOnOnePlaceHolder> {
	private static final long serialVersionUID = -246555692933723994L;

	@Override
	public Tuple2<String, MeetsOnOnePlaceHolder> call( Tuple2<String, StaysOnOnePlaceHolder> tuple2 ) throws Exception {
		StaysOnOnePlaceHolder staysholder = tuple2._2;
		
		List<StayRecord> stays = new ArrayList<StayRecord>();
		for (Entry<String, List<StayRecord>> entry : staysholder.getStays().entrySet()) {
			stays.addAll(entry.getValue());
		}
		
		Map<String, MeetRecord> meets = new HashMap<>();
		
		for(int i=0; i<stays.size()-1; i++){
			for(int j=i; j<stays.size(); j++){
				//一个mac在一个场所中的stay不可能相遇，因此这里不用判断mac是否相等的条件
				if( !InOutTime.EMPTY_VALUE.equals( stays.get(i).getInout().intersect( stays.get(j).getInout() ) ) ){
					//判断这两个mac是否有同科类型，也即是否有相同的人员类型。计算同科类型才有效
					List<String> iPeopleTypes = stays.get(i).getWifidata().getPeopleType();
					List<String> jPeopleTypes = stays.get(j).getWifidata().getPeopleType();
					for (String peopleType : iPeopleTypes) {
						//如果有相同的人员类型则添加
						if (jPeopleTypes.contains(peopleType)) {
							MeetRecord meet = new MeetRecord(tuple2._1);
							meet.putStay(stays.get(i));
							meet.putStay(stays.get(j));
							meets.put(meet.gotIdentityForMac(), meet);
						}
					}
					
				}
			}
		}
		
		MeetsOnOnePlaceHolder meetsholder = new MeetsOnOnePlaceHolder(tuple2._1);
		meetsholder.getMeets().putAll(meets);
		
		return new Tuple2<String, MeetsOnOnePlaceHolder>(tuple2._1, meetsholder);
	}

}
