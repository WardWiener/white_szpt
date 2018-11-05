/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.meets;

import java.util.List;

import org.apache.spark.api.java.function.Function2;

import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnPlacesInfo;

/**
 * 聚合统计每个mac组合相同的相遇在各个场所出现次数
 * 
 * @author yucy
 *
 */
public class MeetsOnPlacesReduceFunction implements Function2<MeetsOnPlacesInfo, MeetsOnPlacesInfo, MeetsOnPlacesInfo> {
	private static final long serialVersionUID = 166905261595360967L;

	@Override
	public MeetsOnPlacesInfo call(MeetsOnPlacesInfo a, MeetsOnPlacesInfo b) throws Exception {
//		System.out.println("MeetsOverTwoPlaceReduceFunction.call : a = " + a + ", b = " + b);
		
		MeetsOnPlacesInfo holder = new MeetsOnPlacesInfo();
		
		//先将a中的数据设置到holder中
		List<MeetRecord> meets = a.getMeets();
		holder.getMeets().addAll(meets);
//		holder.setMeetCount(a.getMeetCount());
		holder.setMeetCountOnDiffrentPlace(a.getMeetCountOnDiffrentPlace());
		
		//遍历b中的meet，判断是否是与a中的场所不同，如果不同则加入不同场所相遇的数量统计
		for(MeetRecord m : b.getMeets()){
			if( meetInDifferentPlace(meets, m) ){
				Integer meetCountInDiffrentPlace = holder.getMeetCountOnDiffrentPlace();
				holder.setMeetCountOnDiffrentPlace(meetCountInDiffrentPlace + 1);
				holder.getMeets().add(m);
			}
		}
		
		return holder;
	}
	
	/*
	 * 判断某个相遇的场所是否与列表中相遇的所有场所都不同
	 */
	private boolean meetInDifferentPlace(List<MeetRecord> meets, MeetRecord meet){
		for(MeetRecord m : meets){
			//只要与其中一个相同，则返回false
			if( m.getPlaceCode().equals(meet.getPlaceCode()) ){
				return false;
			}
		}
		return true;
	}
}
