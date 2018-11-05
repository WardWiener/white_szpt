/**
 * Copyright 2017 Taiji
 * All right reserved.
 * Created on 2017年1月5日 下午2:40:42
 */
package com.taiji.pubsec.szpt.dpp.wifidataprocess;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;

import com.google.common.base.Optional;
import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.common.DataContants;
import com.taiji.pubsec.szpt.dpp.common.PropertiesAnalysis;
import com.taiji.pubsec.szpt.dpp.jdbc.JDBCWrapper;
import com.taiji.pubsec.szpt.dpp.wifidataprocess.bean.PlaceBasicInfo;
import com.taiji.pubsec.szpt.hbase.HBaseHelper;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.redisson.RedissonHelper;
import com.taiji.pubsec.szpt.solr.SolrHelper;

import kafka.serializer.StringDecoder;
import net.sf.json.JSONObject;
import scala.Tuple2;

/**
 * 从Kafka接收WiFi原始轨迹数据，将wifi采集数据合并成wifi轨迹数据，存入HBase中，同时在solr中做索引，同时存入redis中
 * hbase:将wifi轨迹中离开的数据保存到HBase的wifitrack表中并在solr中添加索引
 * solr：将wifi轨迹中离开的数据保存到solr的wifitrack的collection中
 * redis ： 存放两部分缓存数据，一部分（current_point_）是以场所code为key的数据，用于监控当前场所的人数，还有一部分（current_mac_wifitrack）是以mac为key，用于查询当前人员。
 * 			这两部分数据都是默认的超时自动删除时间。
 * @author yucy
 *
 */
public class WifitrackPersistToHbase implements Serializable {

	private static final long serialVersionUID = -676834209677763679L;

	public final static Pattern TAB = Pattern.compile("\t");
	
	private final static String HBASE_TABLENAME = "wifitrack";
	private final static String HBASE_FAMLIY = "stay";
	private final static String SOLR_COLLECTION = "wifitrack";
	
	// 用于记录当前批次的所有wifi轨迹数据的缓存
	private final static String REDIS_KEY_CURRENT_MAC_WIFITRACK = "current_mac_wifitrack";
	// 用于记录当前批次中每个wifi监控点的所有mac的缓存key的前缀
	private final static String REDIS_KEY_CURRENT_POINT_MACS_PREFIX = "current_point_";
	//放入Redis中数据过期时间（秒）
	private final static long REDIS_EVICTION_TIME = 3600;
	
	//wifi采集原始数据Topic名称
	private final static String topicWifidata = "topic-wifidata";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 第一步 从kafka接收数据
		// 设置匹配模式，以TAB分隔
		// 提交的参数冲突以程序设置的为准
		SparkConf sparkConf = new SparkConf().setAppName("WifitrackPersistToHbase");
		// 批处理时间为10s
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(Long.valueOf(new PropertiesAnalysis().analyze("SPARKDURATIONFORWIFIDATA"))));
		// 设置检查点 hdfs目录
		jssc.checkpoint("wifiBasicdata_checkpoint"); 
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", DataContants.BROKER);
		kafkaParams.put("group.id", "WifitrackPersistToHbase");

		String[] topictopicWifidata = new String[] { topicWifidata };
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topictopicWifidata));
		JavaPairInputDStream<String, String> messageswifidata = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);

//此步骤计算监控人数已经转到redis中，故代码后续需要删除
//		computeMacsOnPlaces(messageswifidata);
		persist(messageswifidata);
		
		jssc.start();
		jssc.awaitTermination();
	}

//	/*
//	 * 计算每个场所的监控的人数，并更新计算结果到数据库中
//	 */
//	private static void computeMacsOnPlaces( JavaPairInputDStream<String, String> messages ) {
//		// 将输入的数据转化为<csid, Integer> Integer每个场所的监控的人数
//		JavaPairDStream<String, Integer> wifiDatas = messages
//				.mapToPair( new PairFunction<Tuple2<String, String>, String, Integer>() {
//
//					private static final long serialVersionUID = 4417606109736893255L;
//
//						@Override
//						public Tuple2<String, Integer> call(
//								Tuple2<String, String> tuple2)
//								throws Exception {
//							String[] strs = TAB.split(tuple2._2);
//							return new Tuple2<String, Integer>(strs[2], 1);
//						}
//					}).reduceByKey( new Function2<Integer, Integer, Integer>() {
//						
//						private static final long serialVersionUID = -5547350492046420564L;
//
//						@Override
//						public Integer call(Integer x1, Integer x2)
//								throws Exception {
//							return x1 + x2;
//						}
//					});
//
//		// 更新每个场所监控的人数
//		wifiDatas.foreachRDD(new VoidFunction<JavaPairRDD<String, Integer>>() {
//			
//			private static final long serialVersionUID = -6468697471110987787L;
//
//			@Override
//			public void call(JavaPairRDD<String, Integer> rdd) throws Exception {
//				rdd.foreach(new VoidFunction<Tuple2<String, Integer>>() {
//					
//					private static final long serialVersionUID = -2145684912708597503L;
//
//					@Override
//					public void call(Tuple2<String, Integer> tuple2)
//							throws Exception {
//						String sql = "update t_csjk_wificsxx t2, t_csjk_wificsjkrs t1 set jkrs = ? where t1.cs_id=t2.id and t2.swfwcsbm=?";
//						List<Object[]> lists = new ArrayList<Object[]>();
//						lists.add(new Object[] { tuple2._2, tuple2._1 });
//						JDBCWrapper.getJDBCInstance().doBatch(sql, lists);
//					}
//				});
//			}
//		});
//	}
	
	private static void persist(JavaPairInputDStream<String, String> messages){
		// 将输入的数据转化为<mac, WifiData>
		JavaPairDStream<String, WifiData> rawwifiDatas = messages.mapToPair(new PairFunction<Tuple2<String, String>, String, WifiData>() {
			private static final long serialVersionUID = 7611517406456172724L;
				@Override
				public Tuple2<String, WifiData> call( Tuple2<String, String> tuple2 ) throws Exception {
					String[] strs = tuple2._2.split("\t");
					// 由于过来的wifi数据的时间是s所以需要转换下
					// 将strs转成WifiData
					String entertimestr = strs[10];
					String leavetimestr = strs[11];
					// 将秒的时间字符转换成毫秒的时间字符
					if (null != entertimestr
							&& !StringUtils.isEmpty(entertimestr)) {
						entertimestr = entertimestr + "000";
					}
					if (null != leavetimestr
							&& !StringUtils.isEmpty(leavetimestr)) {
						leavetimestr = leavetimestr + "000";
					}
					WifiData wifiData = new WifiData(strs[19],
							entertimestr, leavetimestr, strs[2], strs[22],
							strs[23], strs[34]+ "000");
					// 输出WifiFenceHit
					return new Tuple2(strs[19], wifiData);
				}
			});
				
		JavaPairDStream<String, WifiData> wifiDatasForState = rawwifiDatas.updateStateByKey(
				new Function2<List<WifiData>, Optional<WifiData>, Optional<WifiData>>() {

					private static final long serialVersionUID = 1131649436572099066L;
					
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
						
						//如果没有离开时间，检测该记录是否已经超时，达到设定的离开超时时间
						if  (current != null  && StringUtils.isEmpty(current.getLeaveTime())) {
							//是否已经超过了规定的超时次数
							if (current.getNoupdateCount() >= Long.valueOf(new PropertiesAnalysis().analyze("TIMEOUTFORCOUNT"))) {
								//超过规定的时间设置离开的时间
								//设置离开时间为上次该数据的更新时间+超时时间
								current.setLeaveTime(String.valueOf(Long.valueOf(current.getGatherTime()) + current.getNoupdateCount() * Long.valueOf(new PropertiesAnalysis().analyze("SPARKDURATIONFORWIFIDATA")) * 1000L));
							
							}
						}
						
						// 返回新的状态
						return current == null ? Optional.of(new WifiData()) : Optional.of(current);
					}

				});
		
		
		/**
		 * updateStateByKey没有插入数据，foreach中插入数据了
		 */
		wifiDatasForState.foreachRDD(new VoidFunction<JavaPairRDD<String, WifiData>>() {
			private static final long serialVersionUID = -2198782719080745046L;

			@Override
			public void call(JavaPairRDD<String, WifiData> rdd) throws Exception {
				// 查询场所的信息
				// 场所编码-》场所信息
				Map<String, PlaceBasicInfo> placeBasicInfos = queryPlaceBasicInfo();
				// 这里查询所有的五色人员信息，查询数据库返回所有的mac对应的无色人员信息
				// mac->信息
				Map<String, MobilePhoneInfo> mobilePhoneInfos = queryMobilePhoneTerminalDeviceInfo();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
				
				rdd.foreach(new VoidFunction<Tuple2<String, WifiData>>() {
					private static final long serialVersionUID = 1066781088745234638L;

					@Override
					public void call(Tuple2<String, WifiData> t) throws Exception {
						if (t._2 == null || StringUtils.isEmpty(t._2.getMac())) {
							return ;
						}
						// 如果状态已经是离开了，将离开的数据保存到HBase中并在solr中添加索引
						if (t._2.getLeaveTime()!= null && !"".equals(t._2.getLeaveTime())) {
							
							WifiData data = t._2;
							//保存已经离开的wifi轨迹数据到HBase中
							String mac = data.getMac();
							String entertime = data.getEnterTime();
							String leavetime = data.getLeaveTime();
							String placecode = data.getPlaceId();
							String placename = null;
							String lat = null;
							String lot = null;
							String placeposition = null;
							
							PlaceBasicInfo place = placeBasicInfos.get(placecode);
							if( null != place ){
								placename = place.getInternetServicePlaceName();
								lat = place.getLatitude();
								lot = place.getLongitude();
								placeposition = lat + "," + lot;
							}
							System.out.println("保存mac" + mac);
							
							//由于HBase基本平台尚不稳定，暂时不存入其中
//							saveStayRecord(mac, entertime, leavetime, placecode, placename, placeposition);
							
							//为wifi轨迹建立索引
							Map<String, Object> map = new HashMap<>();

							map.put("mac", mac);
							map.put("placecode", placecode);
							map.put("placename", placename);
							long enter = Long.valueOf(entertime);
							long leave = Long.valueOf(leavetime);
							map.put("entertime", sdf.format(new Date(enter)));
							map.put("leavetime", sdf.format(new Date(leave)));
							map.put("period", leave - enter);
							map.put("placeposition", placeposition);
							map.put("id", mac + entertime + leavetime + placecode);
							
							// 判断wifi围栏获取的数据是否在重点人监控的mac地址中
							// mac地址相同
							if (mobilePhoneInfos.containsKey(mac)) {
								MobilePhoneInfo mobilePhoneInfo = mobilePhoneInfos
										.get(mac);
								List<String> list = new ArrayList<String>();
								List<String> list1 = new ArrayList<String>();
								for (HighriskPeopleType type : mobilePhoneInfo.getHighriskPerson().getHighriskPeopleTypes()) {
									list.add(type.getPeopleType());
									for (HighriskCriminalRecord record : type.getHighriskCriminalRecords()) {
										list1.add(record.getCriminalRecord());
									}
								}
								map.put("persontypecode", list.toArray());
								map.put("criminaltypecode", list1.toArray());
								if (!StringUtils.isEmpty(mobilePhoneInfo.getHighriskPerson().getWarnType())) {
									map.put("tag", "fivecolor");
								}
							}
							SolrHelper.getInstance(new PropertiesAnalysis().analyze("ZKHOSTSOLR")).addIndex(SOLR_COLLECTION, "id", map);
						}
						WifiData wifidata = t._2;
						
//						RSet<String> macs = RedissonHelper.getClient().getSet(REDIS_KEY_CURRENT_WIFITRACK_MAC);
//						macs.add(wifidata.getMac());
						
						// 更新redis中的当前场所中的wifi轨迹数据
						updateRedis(wifidata);
					}
					
				});
				
				// 清除redis中本次已经没有的mac
//				cleanRedis();
			}
			
		});
		
	}
	
	private static void updateRedis(WifiData wifidata){
		//获得redis中存放的map
		RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache(REDIS_KEY_CURRENT_MAC_WIFITRACK);
		
		String mac = wifidata.getMac();
		String pointcode = wifidata.getPlaceId();
		
		//将wifi轨迹放入缓存
		maptrack.fastPut(mac, JSONObject.fromObject(wifidata).toString(), REDIS_EVICTION_TIME, TimeUnit.SECONDS);
		
		//获得该场所所有mac地址的缓存
		RSetCache<String> macs = RedissonHelper.getClient().getSetCache(REDIS_KEY_CURRENT_POINT_MACS_PREFIX + pointcode);
		//将mac放入缓存
		macs.add(mac, REDIS_EVICTION_TIME, TimeUnit.SECONDS);
	}
/*	
	private static void cleanRedis(){
		//获得redis中存放的map
		RMap<String, Set<String>> mappoint = RedissonHelper.getClient().getMap(REDIS_KEY_CURRENT_POINT_WIFITRACKS);
		RMap<String, WifiData> maptrack = RedissonHelper.getClient().getMap(REDIS_KEY_CURRENT_MAC_WIFITRACK);
		//本次所有mac
		RSet<String> macs = RedissonHelper.getClient().getSet(REDIS_KEY_CURRENT_WIFITRACK_MAC);
		Set<String> todeletedmacfromredis = maptrack.readAllKeySet();
		todeletedmacfromredis.removeAll(macs);
		
		for(String mac : todeletedmacfromredis){
			//从wifi轨迹中清除该mac
			maptrack.remove(mac);
			//从所有的场所中清除该mac
			for(String str : mappoint.readAllKeySet()){
				Set<String> set = mappoint.get(str);
				set.remove(mac);
				mappoint.put(str, set);
			}
		}
		
		//清空mac
		macs.clear();
	}
*/	
	private static void saveStayRecord(String mac, String entertime, String leavetime, String placecode, String placename, String placeposition){
		String rowKey = mac + entertime + leavetime + placecode;
		
		List<Put> puts = new ArrayList<>();
		
		Put put_mac = new Put(Bytes.toBytes(rowKey));
		put_mac.addColumn(Bytes.toBytes(HBASE_FAMLIY), Bytes.toBytes("mac"), Bytes.toBytes(mac));
		puts.add(put_mac);
		
		Put put_placecode = new Put(Bytes.toBytes(rowKey));
		put_placecode.addColumn(Bytes.toBytes(HBASE_FAMLIY), Bytes.toBytes("placecode"), Bytes.toBytes(placecode));
		puts.add(put_placecode);
		
		Put put_entertime = new Put(Bytes.toBytes(rowKey));
		put_entertime.addColumn(Bytes.toBytes(HBASE_FAMLIY), Bytes.toBytes("entertime"), Bytes.toBytes(entertime));
		puts.add(put_entertime);
		
		Put put_leavetime = new Put(Bytes.toBytes(rowKey));
		put_leavetime.addColumn(Bytes.toBytes(HBASE_FAMLIY), Bytes.toBytes("leavetime"), Bytes.toBytes(leavetime));
		puts.add(put_leavetime);
		
		if( null != placename ){
			Put put_placename = new Put(Bytes.toBytes(rowKey));
			put_placename.addColumn(Bytes.toBytes(HBASE_FAMLIY), Bytes.toBytes("placename"), Bytes.toBytes(placename));
			puts.add(put_placename);
		}
		
		if( null != placeposition ){
			Put put_placeposition = new Put(Bytes.toBytes(rowKey));
			put_placeposition.addColumn(Bytes.toBytes(HBASE_FAMLIY), Bytes.toBytes("placeposition"), Bytes.toBytes(placeposition));
			puts.add(put_placeposition);
		}
		
		HBaseHelper.addDatas(HBASE_TABLENAME, puts);
	}
	
	/**
	 * 从数据库中查询场所的相关信息
	 * 
	 * @return
	 */
	public static Map<String, PlaceBasicInfo> queryPlaceBasicInfo() {
		try {
			String sqlText = "select * from t_csjk_wificsxx";
			Map<String, PlaceBasicInfo> placeBasicInfos = new HashMap<String, PlaceBasicInfo>();
			Connection conn = JDBCWrapper.getJDBCInstance().getConnetion();
			ResultSet result = null;
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = conn.prepareStatement(sqlText);
				result = preparedStatement.executeQuery();
				while (result.next()) {
					PlaceBasicInfo placeBasicInfo = new PlaceBasicInfo();
					placeBasicInfo.setInternetServicePlaceCode(result
							.getString("swfwcsbm"));
					placeBasicInfo.setInternetServicePlaceName(result
							.getString("swfwcsmc"));
					placeBasicInfo.setLongitude(result.getString("csjd"));
					placeBasicInfo.setLatitude(result.getString("cswd"));
					placeBasicInfos.put(
							placeBasicInfo.getInternetServicePlaceCode(),
							placeBasicInfo);
				}
				return placeBasicInfos;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (result != null) {
					try {
						result.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (conn != null) {
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 从数据库中查询手机终端相关的信息
	 * 
	 * @return
	 */
	public static Map<String, MobilePhoneInfo> queryMobilePhoneTerminalDeviceInfo() {
		try {
			// t_gwry_yddhxx 移动电话信息 t_gwry_gwry五色人员的信息
			String sqlText = "select * from t_gwry_yddhxx t1, t_gwry_ryxx t2 where t1.gwry_id = t2.id";
			Map<String, MobilePhoneInfo> mobilePhoneInfos = new HashMap<String, MobilePhoneInfo>();
			Connection conn = JDBCWrapper.getJDBCInstance().getConnetion();
			ResultSet result = null;
			PreparedStatement preparedStatement = null;
			ResultSet result1 = null;
			PreparedStatement preparedStatement1 = null;
			ResultSet result2 = null;
			PreparedStatement preparedStatement2 = null;
			try {
				preparedStatement = conn.prepareStatement(sqlText);
				result = preparedStatement.executeQuery();
				while (result.next()) {
					MobilePhoneInfo mobilePhoneInfo = new MobilePhoneInfo();
					mobilePhoneInfo.setMac(result.getString("macdh"));
					HighriskPerson fiveColorPerson = new HighriskPerson();
					fiveColorPerson.setIdcode(result.getString("sfzh"));
					fiveColorPerson.setWarnType(result.getString("yjlx"));
					fiveColorPerson.setName(result.getString("xm"));
					mobilePhoneInfo.setHighriskPerson(fiveColorPerson);

					// t_gwry_gwrylx 五色人员的人员类型 t_gwry_yddhxx 五色人员
					String sqlText1 = "select * from t_gwry_gwrylx t1, t_gwry_ryxx t2 where t1.gwry_id = t2.id and t2.id='"
							+ result.getString("gwry_id") + "'";
					preparedStatement1 = conn.prepareStatement(sqlText1);
					result1 = preparedStatement1.executeQuery();
					Set<HighriskPeopleType> peopleTypes = new HashSet<HighriskPeopleType>();
					while (result1.next()) {
						HighriskPeopleType peopleType = new HighriskPeopleType();
						peopleType.setPeopleType(result1.getString("rylx"));
						peopleTypes.add(peopleType);
						
						//人员类型对应的前科类型
						String sqlText2 = "select * from t_gwry_gwrylx t1,t_gwry_gwryqklx t2 where t1.id=t2.gwrylx_id and t1.id='"
							+ result1.getString("id") + "'";
						preparedStatement2 = conn.prepareStatement(sqlText2);
						result2 = preparedStatement2.executeQuery();
						while (result2.next()) {
							String qklx = result2.getString("qklx");
							HighriskCriminalRecord record = new HighriskCriminalRecord();
							record.setCriminalRecord(qklx);
							peopleType.getHighriskCriminalRecords().add(record);
						}
						
					}
					fiveColorPerson.setHighriskPeopleTypes(peopleTypes);
					mobilePhoneInfos.put(mobilePhoneInfo.getMac(),
							mobilePhoneInfo);
				}

				return mobilePhoneInfos;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (result != null) {
					try {
						result.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (result1 != null) {
					try {
						result1.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (preparedStatement1 != null) {
					try {
						preparedStatement1.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (conn != null) {
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
