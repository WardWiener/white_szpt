/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.inoutnumber;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.spark.api.java.function.Function2;

import com.taiji.pubsec.szpt.bigdata.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.bigdata.wifi.ew.inoutnumber.bean.InoutNumOnEveryPlaceInfo;

/**
 * 迭代统计出每个mac在所有场所中的停留（出入）记录
 * 
 * @author yucy
 *
 */
public class InoutRecordReduceFunction implements Function2<InoutNumOnEveryPlaceInfo, InoutNumOnEveryPlaceInfo, InoutNumOnEveryPlaceInfo>  {
	
	private static final long serialVersionUID = 290638171219753878L;

	@Override
	public InoutNumOnEveryPlaceInfo call(InoutNumOnEveryPlaceInfo a, InoutNumOnEveryPlaceInfo b) throws Exception {
		String mac = a.getMac();
		InoutNumOnEveryPlaceInfo result = new InoutNumOnEveryPlaceInfo(mac);
		//先将a中的记录放到result中
		result.getDetails().putAll(a.getDetails());
		
		Map<String, List<StayRecord>> detailsb = b.getDetails();
		//遍历b中的记录，将该mac的出入停留记录添加到结果中
		for(Entry<String, List<StayRecord>> entry : detailsb.entrySet()){
			String placecode = entry.getKey();
			if(result.getDetails().containsKey(placecode)){
				//如果已经有该场所的记录，则添加
				result.getDetails().get(placecode).addAll(b.getDetails().get(placecode));
			}else{
				//如果还没有该场所的记录，则新增
				result.getDetails().put(placecode, b.getDetails().get(placecode));
			}
		}
		return result;
	}


}
