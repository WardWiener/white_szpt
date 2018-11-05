/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.meets;

import org.apache.spark.api.java.function.PairFunction;

import com.taiji.pubsec.szpt.bigdata.wifi.ew.meets.bean.MeetsGroup;
import com.taiji.pubsec.szpt.bigdata.wifi.ew.meets.bean.MeetsOnPlacesInfo;

import scala.Tuple2;

/**
 * 映射成<各组相遇的key（用各meet的stay的mac+intime联合），各组相遇记录以及相关状态>
 * 
 * @author yucy
 *
 */
public class MeetsGroupMapFunction implements PairFunction<Tuple2<String, MeetsOnPlacesInfo>, String, MeetsGroup> {

	private static final long serialVersionUID = 7568425472228518120L;

	@Override
	public Tuple2<String, MeetsGroup> call(Tuple2<String, MeetsOnPlacesInfo> tuple2) throws Exception {
		MeetsOnPlacesInfo meetgroup = tuple2._2;
		String key = meetgroup.gotKey();
		return new Tuple2<String, MeetsGroup>(key, new MeetsGroup(meetgroup));
	}

}
