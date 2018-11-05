/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.google.common.base.Optional;
import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.common.DataContants;
import com.taiji.pubsec.szpt.dpp.common.PropertiesAnalysis;
import com.taiji.pubsec.szpt.dpp.wifi.ew.basedplace.BasedPlaceCalcHelper;
import com.taiji.pubsec.szpt.dpp.wifi.ew.basedplace.PlaceStatistics;
import com.taiji.pubsec.szpt.dpp.wifi.ew.bean.StaysOnOnePlaceHolder;
import com.taiji.pubsec.szpt.dpp.wifi.ew.common.StayOnPlaceMapFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.common.StayOnPlaceReduceFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.common.StayRecordMapFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.inoutnumber.InoutRecordMapFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.inoutnumber.InoutRecordReduceFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.inoutnumber.bean.InoutNumOnEveryPlaceInfo;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.MeetsOnOnePlaceMapFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.MeetsOnPlacesMapFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.MeetsOnPlacesReduceFunction;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnOnePlaceHolder;
import com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean.MeetsOnPlacesInfo;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;

import kafka.serializer.StringDecoder;
import net.sf.json.JSONObject;
import scala.Tuple2;

/**
 * 七种预警规则的计算
 * @author yucy
 *
 */
public class EarlyWarn {
	private final static String checkpointDir = "earlywarncalc_checkpoint";
	private final static String topic_hprwifidata = DataContants.TOPIC_HPRDATA;
	public final static String TOPIC_ALERT_RULE5 = "topic-wifialert-five";
	public final static String TOPIC_ALERT_RULE6 = "topic-wifialert-six";
	
	private final static long BATCHDURATION = 10;
	//两小时
	private final static long WINDOWDURATION_MEETS = BATCHDURATION * 6 * 60 * 2;
	private final static long SLIDEDURATION_MEETS = 10;

	//两周
	private final static long WINDOWDURATION_INOUTS = BATCHDURATION * 6 * 60 * 24 * 7 * 2;
	private final static long SLIDEDURATION_INOUTS = 10;
	
	private final static Integer EW_INOUT_NUM = 100;

	private static JavaPairDStream<String, WifiData> wifiDatasForFiveColorPersons;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SparkConf sparkConf = new SparkConf().setAppName("EarlyWarn");
		// 批处理时间为10s
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(BATCHDURATION));
		// 设置检查点 hdfs目录
		jssc.checkpoint(checkpointDir);
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topic_hprwifidata));
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", DataContants.BROKER);
		kafkaParams.put("group.id", "EarlyWarn");
		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(
				jssc, String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);

		// 将输入的数据转化为<mac, wifiData>
		wifiDatasForFiveColorPersons = messages.mapToPair(new PairFunction<Tuple2<String, String>, String, WifiData>() {

			private static final long serialVersionUID = 8683009478634824399L;

					@Override
					public Tuple2<String, WifiData> call( Tuple2<String, String> tuple2 ) throws Exception {
						System.out.println("接收数据current ：" + tuple2._2);
						JSONObject jsonObj = JSONObject.fromObject(tuple2._2);
						WifiData wifiData = (WifiData) JSONObject.toBean(jsonObj, WifiData.class);
						return new Tuple2<String, WifiData>(wifiData.getMac(), wifiData);
					}
				});
		
		wifiDatasForFiveColorPersons.cache();
		
		letBasedPlaceCalcUnitGo();
		letMeetEwCalcUnitGo();
		letInoutOverOneHundredEwUnitGo();

		jssc.start();
		jssc.awaitTermination();
	}

	/**
	 * 算法6：距离当前时间的一周内，出入任意WIFI点超过100次。
	 */
	private static void letInoutOverOneHundredEwUnitGo(){
		// 映射成<场所, 重点人出入列表>结构，计算每个场所中每个人的出入记录
		JavaPairDStream<String, StaysOnOnePlaceHolder> stayRecordsInTwoweeks = wifiDatasForFiveColorPersons
				.mapToPair(new StayOnPlaceMapFunction())
				.reduceByKeyAndWindow(new StayOnPlaceReduceFunction(), 
						Durations.seconds(WINDOWDURATION_INOUTS), Durations.seconds(SLIDEDURATION_INOUTS)).mapToPair(new StayRecordMapFunction());

		JavaPairDStream<String, InoutNumOnEveryPlaceInfo> inoutOnEveryPlace = stayRecordsInTwoweeks
				.flatMapToPair(new InoutRecordMapFunction())
				.reduceByKey(new InoutRecordReduceFunction());
		
		inoutOnEveryPlace.foreachRDD(new VoidFunction<JavaPairRDD<String, InoutNumOnEveryPlaceInfo>>() {

			private static final long serialVersionUID = -1510931461861370096L;

			@Override
			public void call(JavaPairRDD<String, InoutNumOnEveryPlaceInfo> rdd) throws Exception {
				if(rdd.isEmpty()){
//					Date now = new Date(System.currentTimeMillis());
//					System.out.println(now + " : 本次计算结果为空(算法6)");
					return;
				}
				
				final KafkaProducer kafkaProducer = new KafkaProducer(DataContants.BROKER);
				rdd.foreach(new VoidFunction<Tuple2<String, InoutNumOnEveryPlaceInfo>>(){

					private static final long serialVersionUID = 4070218891666132763L;

					@Override
					public void call(Tuple2<String, InoutNumOnEveryPlaceInfo> tuple2) throws Exception {
						String mac = tuple2._1;
						InoutNumOnEveryPlaceInfo inout = tuple2._2;
						
						Date now = new Date(System.currentTimeMillis());
						System.out.println(now + " : mac(" + mac + "), count( " + inout.getCount() + " )");
						
						if(inout.getCount() >= EW_INOUT_NUM){
							System.out.println(inout);
							kafkaProducer.sendData(TOPIC_ALERT_RULE6, JSONObject.fromObject(inout).toString().getBytes());
						}
					}
					
				});
				kafkaProducer.destroy();
			}
		});
	}

	/**
	 * 算法5：任意两个以上WIFI点，在2小时内，相同的2个以上同前科重点人聚集。例：张三，李四两个重点人，2个小时内，在A点聚集过，也在B点聚集过。
	 */
	private static void letMeetEwCalcUnitGo() {
		
		// 映射成<场所, 重点人出入列表>结构，计算每个场中每个人的出入记录
		JavaPairDStream<String, StaysOnOnePlaceHolder> stayRecordsInTwohours = wifiDatasForFiveColorPersons.
				mapToPair(new StayOnPlaceMapFunction()).reduceByKeyAndWindow(new StayOnPlaceReduceFunction(), 
						Durations.seconds(WINDOWDURATION_MEETS), Durations.seconds(SLIDEDURATION_MEETS)).mapToPair(new StayRecordMapFunction());
		
		// 统计每个场所的相遇记录
		JavaPairDStream<String, MeetsOnOnePlaceHolder> meetRecordsInTwohours = stayRecordsInTwohours.mapToPair(new MeetsOnOnePlaceMapFunction());
		
		//统计两个重点人在不同场所相遇的次数
		JavaPairDStream<String, MeetsOnPlacesInfo> meetsOnPlaces = meetRecordsInTwohours.
				flatMapToPair(new MeetsOnPlacesMapFunction()).reduceByKey(new MeetsOnPlacesReduceFunction());
/*		
		//映射成<各组相遇的key（用各meet的stay的mac+intime联合），各组相遇记录以及相关状态>
		JavaPairDStream<String, MeetsGroup> meetsGroup = 
				meetsOnPlaces.mapToPair(new MeetsGroupMapFunction());
		
		//将相遇组保存到状态中，记录各组是否发出过预警、停留信息是否有更新等状态
		JavaPairDStream<String, MeetsGroup> meetsGroupForState = 
				meetsGroup.updateStateByKey(updateFunc);
*/		
		//将场所数超过两个的结果发送到kafka
		meetsOnPlaces.foreachRDD(new VoidFunction<JavaPairRDD<String, MeetsOnPlacesInfo>>() {
			
			private static final long serialVersionUID = 5987746160936803407L;

			@Override
			public void call(JavaPairRDD<String, MeetsOnPlacesInfo> rdd) throws Exception {
				if(rdd.isEmpty()){
					Date now = new Date(System.currentTimeMillis());
					System.out.println(now + " : 本次计算结果为空");
					return;
				}
				
				final KafkaProducer kafkaProducer = new KafkaProducer(DataContants.BROKER);
				rdd.foreach(new VoidFunction<Tuple2<String,MeetsOnPlacesInfo>>() {

					private static final long serialVersionUID = -8909993960984104970L;

					@Override
					public void call(Tuple2<String, MeetsOnPlacesInfo> tuple2) throws Exception {
						System.out.println("处理统计结果：" + tuple2._2);
						
						MeetsOnPlacesInfo holder = tuple2._2;
						if(holder.getMeetCountOnDiffrentPlace() >= 2){
							//发送到kafka
							kafkaProducer.sendData(TOPIC_ALERT_RULE5, JSONObject.fromObject(holder).toString().getBytes());
						}
						
					}
				});
				kafkaProducer.destroy();
				
			}
		});
		
	}
	
	/**
	 * <p>算法1：任意WIFI点，有超过3个重点人聚集，且3个重点人每人的驻留时间都超过5分钟。
	 * <p>算法2：对于橙色人，在任意WIFI点，与其他任意重点人聚集，且驻留时间超过5分钟。
	 * <p>算法3：当红色重点人出现在任意WIFI点。
	 * <p>算法4：任意WIFI点，有超过2个同前科重点人聚集，且驻留时间超过15分钟。
	 * <p>算法7：位于居住小区的WIFI点，有盗窃前科人员，驻留时间超过30分钟。
	 */
	private static void letBasedPlaceCalcUnitGo(){ 
		/*
		 * 将当前正在各场所出现的重点人找出来，并更新在状态变量里
		 */
		JavaPairDStream<String, WifiData> wifiDatasForState = wifiDatasForFiveColorPersons.updateStateByKey(
			new Function2<List<WifiData>, Optional<WifiData>, Optional<WifiData>>() {

				private static final long serialVersionUID = 1478005085719860756L;

				@Override
				public Optional<WifiData> call( List<WifiData> wifidatas, Optional<WifiData> oldState) throws Exception {
					WifiData old = oldState.orNull();
					//本次该key（mac地址）新的状态
					WifiData current = old;
					
					// 如果旧的状态已经是离开了，则设置新的为空。清理离开的数据
					if (old != null && !StringUtils.isEmpty(old.getLeaveTime())) {
						current = null;
					}
					
					//数据是否更新的标识
					boolean updateFlag = false;
					//针对本次该key（mac地址），遍历其wifi行为数据，找到离开的时间并记录到状态里（如果有）
					for (WifiData wifidata : wifidatas) {
						//沒有进入时间则跳过, 无效数据
						if (StringUtils.isEmpty(wifidata.getEnterTime())) {
							continue;
						}
						
						if (current == null || StringUtils.isEmpty(current.getMac())) {
							// current是无效数据，采用当前遍历的这个
							current = wifidata;
						} else {
							// 只与进入时间在状态记录之后的记录比较，寻找有离开时间的记录
							if (Long.valueOf(current.getEnterTime()) <= Long.valueOf(wifidata.getEnterTime())) {
								if (!StringUtils.isEmpty(wifidata.getLeaveTime())) {
									//如果有离开时间的值，则设定新的状态对象的离开时间
									current.setLeaveTime(wifidata.getLeaveTime());
								}
							}
						}
						
						updateFlag = true;
						
						//更新最新的采集时间 和 更新最新的时间,数据上传的最新时间（服务器时间）
						current.setGatherTime(wifidata.getGatherTime());
						current.setCreateTime(new Date());
					}
					
					//不能是空的current，比如new WifiData()
					if(current == null ||  StringUtils.isEmpty(current.getMac()) )
						return current == null ? Optional.of(new WifiData()) : Optional.of(current);
					
					//数据未更新
					if (updateFlag == false) {
						current.setNoupdateCount(current.getNoupdateCount() + 1);
					}
					//数据更新，计数归0
					else {
						current.setNoupdateCount(0);
					}
					
					System.out.println("updateStateByKey " + current);
					
					//如果没有离开时间，检测该记录是否已经超时，达到设定的离开超时时间
					if  (current != null  && StringUtils.isEmpty(current.getLeaveTime())) {
						//是否已经超过了规定的超时次数(使用跟时间窗口匹配的超时次数，所以这里做一下换算)
						if (current.getNoupdateCount() >= (Long.valueOf(new PropertiesAnalysis().analyze("TIMEOUTFORCOUNT")) * (Long.valueOf(new PropertiesAnalysis().analyze("SPARKDURATIONFORWIFIDATA")) / 1000 / BATCHDURATION))) {
							//超过规定的时间设置离开的时间
							//设置离开时间为上次该数据的更新时间+超时时间
							current.setLeaveTime(String.valueOf(Long.valueOf(current.getGatherTime()) + current.getNoupdateCount() * BATCHDURATION * 1000L));
						}
						//大概计算最新的采集时间：上次采集时间 + 时间窗口的时间
						else {
							current.setGatherTime(String.valueOf(Long.valueOf(current.getGatherTime()) + BATCHDURATION * 1000L));
						}
					}
					
					// 返回新的状态
					return current == null ? Optional.of(new WifiData()) : Optional.of(current);
				}

			});
		
		//因为状态记录中，离开的mac的值被置为空，所以要过滤空对象得到实际当前在各场所出现的人的mac记录
		JavaPairDStream<String, WifiData> presentWifidata = wifiDatasForState.filter(new Function<Tuple2<String,WifiData>, Boolean>() {

			private static final long serialVersionUID = -3969660413277253739L;

			@Override
			public Boolean call(Tuple2<String, WifiData> tuple2) throws Exception {
				if( StringUtils.isEmpty(tuple2._1) )
					return false;
				if( tuple2._2 == null )
					return false;
				if( StringUtils.isEmpty(tuple2._2.getMac()) )
					return false;
				if( StringUtils.isEmpty(tuple2._2.getPlaceName()) )
					return false;
				
				return true;
			}
			
		});
		
		// 映射成<场所名称, 场所各项统计>的数据结构，以便下一步进行迭代计算
		JavaPairDStream<String, PlaceStatistics> wifiDatasForCal = presentWifidata.mapToPair(
				new PairFunction<Tuple2<String, WifiData>, String, PlaceStatistics>() {
					private static final long serialVersionUID = 4251197836341572287L;

			@Override
			public Tuple2<String, PlaceStatistics> call( Tuple2<String, WifiData> tuple2 ) throws Exception {
				WifiData wifiData = tuple2._2;
				PlaceStatistics statistics = new PlaceStatistics();
				statistics.setWifiData(wifiData);
				// 根据规则生成预警相关的统计数据结构
				BasedPlaceCalcHelper.generateData(wifiData, statistics);
				System.out.println("生成统计数据 结构:" + statistics);
				return new Tuple2<String, PlaceStatistics>(wifiData.getPlaceName(), statistics);
			}
		});
		
		/*
		 * 迭代计算，统计出每个场所的统计数据
		 * 
		 * 算法1：任意WIFI点，有超过3个重点人聚集，且3个重点人每人的驻留时间都超过5分钟。
		 * 算法2：对于橙色人，在任意WIFI点，与其他任意重点人聚集，且驻留时间超过5分钟。
		 * 算法3：当红色重点人出现在任意WIFI点。
		 * 算法4：任意WIFI点，有超过2个同前科重点人聚集，且驻留时间超过15分钟
		 * 算法7:位于居住小区的WIFI点，有盗窃前科人员，驻留时间超过30分钟。
		 */
		JavaPairDStream<String, PlaceStatistics> calRuleDStream = wifiDatasForCal.reduceByKey(new Function2<PlaceStatistics, PlaceStatistics, PlaceStatistics>() {
			private static final long serialVersionUID = -6025008469853128534L;

			@Override
			public PlaceStatistics call(PlaceStatistics a, PlaceStatistics b) throws Exception {
//				System.out.println("参数一的值:" + a);
//				System.out.println("参数二的值:" + b);
				
				PlaceStatistics result = new PlaceStatistics();
				
				BasedPlaceCalcHelper.accumulatPlaceStatistics(result, a);
				BasedPlaceCalcHelper.accumulatPlaceStatistics(result, b);

//				System.out.println("reduce计算结果：" + result);
				return result;
			}

		});

		/*
		 * 针对每个预警规则发送预警信息
		 *  算法1：任意WIFI点，有超过3个重点人聚集，且3个重点人每人的驻留时间都超过5分钟。
		 *  算法2：对于橙色人，在任意WIFI点，与其他任意重点人聚集，且驻留时间超过5分钟。
		 *  算法3：当红色重点人出现在任意WIFI点。
		 *  算法5：任意WIFI点，有超过2个同前科重点人聚集，且驻留时间超过15分钟
		 *  算法7:位于居住小区的WIFI点，有盗窃前科人员，驻留时间超过30分钟。
		 */
		calRuleDStream.foreachRDD(new VoidFunction<JavaPairRDD<String, PlaceStatistics>>() {
			private static final long serialVersionUID = -6327423836209535872L;
			
			@Override
			public void call(JavaPairRDD<String, PlaceStatistics> rdd) throws Exception {

				if(rdd.isEmpty())
					return;
				
				
				rdd.foreach(new VoidFunction<Tuple2<String, PlaceStatistics>>() {
					private static final long serialVersionUID = -1825056484141358177L;

					
					@Override
					public void call(Tuple2<String, PlaceStatistics> tuple2) throws Exception {
						if (StringUtils.isEmpty( tuple2._1)) {
							return ;
						}
						KafkaProducer kafkaProducer = new KafkaProducer(DataContants.BROKER);
						
						List<String> places = new ArrayList<String>();
						places.add(tuple2._1);
						PlaceStatistics statistics = tuple2._2;
						System.out.println("场所名称:" + tuple2._1 + " " + statistics);
						// 分别调用五种规则实现预警
						BasedPlaceCalcHelper.over3PeopleStayOver5Min( statistics.getStayOver5Min(), statistics.getStayOver5MinOfOrange(), places, kafkaProducer);
						BasedPlaceCalcHelper.orangePeopleAndOtherStayOver5Min( statistics.getStayOver5Min(), statistics.getStayOver5MinOfOrange(), places, kafkaProducer);
						BasedPlaceCalcHelper.redColorPeopleFound( statistics.getRedColorPeoples(), places, kafkaProducer);
						BasedPlaceCalcHelper.sameCrinimalStayOver15MinRule( statistics.getStayOver15Min(), places, kafkaProducer);
						BasedPlaceCalcHelper.theftStayOver30MinRule( statistics.getStayOver30MinOfTheft(), places, kafkaProducer);
						
						kafkaProducer.destroy();
					}

				});
			}
		});
	}

	
}
