package StayOnPlaceReduceFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

import com.taiji.pubsec.szpt.bigdata.wifi.ew.bean.StayRecord;
import com.taiji.pubsec.szpt.bigdata.wifi.ew.bean.StaysOnOnePlaceHolder;

/**
 * 将每个mac地址的wifi数据合并成几条条出入记录
 * @author genganpeng
 *
 */
public class StayRecordMapFunction implements PairFunction<Tuple2<String,StaysOnOnePlaceHolder>, String,StaysOnOnePlaceHolder>{

	private static final long serialVersionUID = 5822281871740213382L;

	@Override
	public Tuple2<String, StaysOnOnePlaceHolder> call(
			Tuple2<String, StaysOnOnePlaceHolder> t) throws Exception {
		String placeCode = t._1;
		StaysOnOnePlaceHolder staysOnOnePlaceHolder = t._2;
		StaysOnOnePlaceHolder staysOnOnePlaceHolderTogether = new StaysOnOnePlaceHolder(placeCode);
		Map<String, List<StayRecord>> staysOnOnePlaceHolderTogetherMap = staysOnOnePlaceHolderTogether.getStays();
		//遍历每个mac对应的wifi数据
		for(Entry<String, List<StayRecord>> entry : staysOnOnePlaceHolder.getStays().entrySet()){
			String mac = entry.getKey();
			List<StayRecord> stays = entry.getValue();
			List<StayRecord> staytogether = new ArrayList<StayRecord>();
			Collections.sort(stays, new Comparator<StayRecord>() {
				@Override
				public int compare(StayRecord arg0, StayRecord arg1) {
					return arg0.compareTo(arg1);
				}
			});
			
			long enterTime = 0L;
			for (StayRecord stayRecord : stays) {
				if (enterTime == 0) {
					enterTime =  stayRecord.getInout().getIn();
				}
				//有离开时间 形成一条完整的出入记录
				if (stayRecord.getInout().getOut() != -1) {
					stayRecord.getInout().setIn(enterTime);
					staytogether.add(stayRecord);
					enterTime = 0L;
				}
			}
			staysOnOnePlaceHolderTogetherMap.put(mac, staytogether);
		}
		return new Tuple2<String, StaysOnOnePlaceHolder>(placeCode, staysOnOnePlaceHolderTogether);
	}

}
