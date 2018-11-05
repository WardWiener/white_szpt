/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.InOutTime;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StaysOnOnePlaceHolder;

/**
 * 将原始的wifi数据映射成停留记录，为后面的迭代计算各个场所的停留（出入）记录做准备
 * 
 * @author yucy
 *
 */
public class StayOnPlaceMapFunction implements PairFunction<Tuple2<String,WifiData>, String, StaysOnOnePlaceHolder> {
	private static final long serialVersionUID = -1400241267192829493L;

	@Override
	public Tuple2<String, StaysOnOnePlaceHolder> call(Tuple2<String, WifiData> tuple2) throws Exception {
//		System.out.println("StayOnPlaceMapFunction.call : " + tuple2._2);
		
		WifiData wifidata = tuple2._2;
		String placecode = wifidata.getPlaceId();
		if( null == placecode || "".equals(placecode)){
			return null;
		}
		if( StringUtils.isEmpty(wifidata.getEnterTime()) ){
			return null;
		}
		Long in = Long.valueOf(wifidata.getEnterTime());
		Long out = StringUtils.isEmpty(wifidata.getLeaveTime()) ? -1 : Long.valueOf(wifidata.getLeaveTime());
		InOutTime inout = new InOutTime( in, out );
		StayRecord stay = new StayRecord(wifidata.getMac(), placecode, inout, wifidata);
		
		StaysOnOnePlaceHolder smholder = new StaysOnOnePlaceHolder(placecode);
		List<StayRecord> stays = new ArrayList<StayRecord>();
		stays.add(stay);
		//使用mac作为标识
		smholder.getStays().put(wifidata.getMac(), stays);
		
		return new Tuple2<String, StaysOnOnePlaceHolder>(placecode, smholder);
	}
}
