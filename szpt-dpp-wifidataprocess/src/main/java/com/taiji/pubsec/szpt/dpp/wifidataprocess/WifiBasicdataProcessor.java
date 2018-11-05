package com.taiji.pubsec.szpt.dpp.wifidataprocess;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.dpp.common.DataContants;
import com.taiji.pubsec.szpt.dpp.common.PropertiesAnalysis;
import com.taiji.pubsec.szpt.dpp.jdbc.JDBCWrapper;
import com.taiji.pubsec.szpt.dpp.jdbc.JDBCWrapper.ExecuteCallBack;
import com.taiji.pubsec.szpt.dpp.wifidataprocess.bean.PlaceBasicInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.solr.SolrHelper;

import kafka.serializer.StringDecoder;
import net.sf.json.JSONObject;
import scala.Tuple2;

/**
 * 1、从kakfka的topic-wifipoint接收场所原始数据中，然后存入关系数据库中，同时保存到solr的collection为wifipoint中
 * 2、从kafka的topic-wifidata接收wifi轨迹数据，根据业务数据库中高危重点人将wifi轨迹数据中高危人轨迹（补充一些高危人相关数据）找出，发送到Kafka的topic-wifihptrack中，
 * 			为后续的预警程序szpt-dpp-wifidataalert模块提供数据源。
 * 
 * @author yucy
 *
 */
public class WifiBasicdataProcessor implements Serializable {
	private static final long serialVersionUID = -7030535756389151458L;
	
	public final static Pattern TAB = Pattern.compile("\t");
	//终端设备原始数据Topic名称
//	private final static String topicTerminalgatherequipment = "terminalgatherequipment";
	//wifi监控点场所原始数据Topic名称
	private final static String topicPlaceinfo = "topic-wifipoint";
	//wifi采集原始数据Topic名称
	private final static String topicWifidata = "topic-wifidata";
	
	// 盗窃案的前缀编码
	private final static String daoqiePrefix = "017004001";

	private final static String SOLR_COLLECTION_WIFIPOINT = "wifipoint";
	
	public static void main(String[] args) {
		// 第一步 从kafka接收数据
		// 设置匹配模式，以TAB分隔
		// 提交的参数冲突以程序设置的为准
		SparkConf sparkConf = new SparkConf().setAppName("WifiBasicdataProcessor");
		// 批处理时间为10s
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(Long.valueOf(new PropertiesAnalysis().analyze("SPARKDURATIONFORWIFIDATA"))));
		// 设置检查点 hdfs目录
		jssc.checkpoint("wifiBasicdata_checkpoint"); 
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", DataContants.BROKER);
		kafkaParams.put("group.id", "WifiBasicdataProcess");

		String[] topictopicPlaceinfo = new String[] {topicPlaceinfo };
		String[] topictopicWifidata = new String[] { topicWifidata };
		
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topictopicPlaceinfo));
		JavaPairInputDStream<String, String> messagesplace = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);
		
		topicsSet = new HashSet<>(Arrays.asList(topictopicWifidata));
		JavaPairInputDStream<String, String> messageswifidata = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);

		//处理wifi监控点数据
		placeParse(messagesplace);
		generateFiveColorPeopleData(messageswifidata);

		jssc.start();
		jssc.awaitTermination();
	}

	
	/**
	 * 场所数据解析-》保存入mysql数据库，添加到solr
	 * @param messages
	 */
	private static void placeParse(JavaPairInputDStream<String, String> messages) {

		JavaPairDStream<String, String> placeBasicInfosmessages = messages
				.filter(new Function<Tuple2<String, String>, Boolean>() {
					@Override
					public Boolean call(Tuple2<String, String> v1)
							throws Exception {
//						return topicPlaceinfo.equals(v1._1);
						return true;
					}
				});

		// 第二步 按格式解析数据
		JavaDStream<PlaceBasicInfo> placeBasicInfos = placeBasicInfosmessages
				.map(new Function<Tuple2<String, String>, PlaceBasicInfo>() {
					@Override
					public PlaceBasicInfo call(Tuple2<String, String> tuple2) {
						String[] strs = WifiBasicdataProcessor.TAB.split(tuple2._2);
						
						// //给的样例数据中的数据格式
						// PlaceBasicInfo placeBasicInfo = new
						// PlaceBasicInfo(strs[0], strs[1], strs[2], strs[3],
						// strs[4], strs[5],
						// strs[6], strs[7], strs[8], strs[9],strs[10],
						// strs[11], strs[12], strs[13], strs[14],strs[15],
						// strs[16]);
						// ftp中的数据格式
						PlaceBasicInfo placeBasicInfo = new PlaceBasicInfo(
								strs[0], strs[1], strs[2], strs[4], strs[5],
								strs[6], strs[7], strs[8], strs[9], strs[10],
								strs[11], strs[15], strs[16], strs[17],
								strs[18], strs[19], strs[20]);
						return placeBasicInfo;
					}
				});

		// 调用jdbc链接，保存入库
		placeBasicInfos.foreachRDD(new VoidFunction<JavaRDD<PlaceBasicInfo>>() {
			@Override
			public void call(JavaRDD<PlaceBasicInfo> rdd) throws Exception {
				rdd.foreach(new VoidFunction<PlaceBasicInfo>() {
					@Override
					public void call(final PlaceBasicInfo placeBasicInfo)
							throws Exception {
						List<Object[]> lists = new ArrayList<Object[]>();
						lists.add(new Object[] { placeBasicInfo
								.getInternetServicePlaceCode() });
						JDBCWrapper.getJDBCInstance().doQuery(
								"select * from t_csjk_wificsxx where swfwcsbm=?",
								lists, new ExecuteCallBack() {
									@Override
									public void resultCallBack(ResultSet result)
											throws Exception {
										int rowCount = 0;
										String id = "";
										while (result.next()) {
											rowCount++;
											id = result.getString("id");
										}
										// add
										if (rowCount == 0) {
											String sql = "insert into t_csjk_wificsxx(id, swfwcsbm, swfwcsmc, csxxdz, csjd, cswd,"
													+ "csfwlx,csjyxz,csjyfr,csjyfryxzjlx,csjyfryxzjhm,"
													+ "lxfs,yykssj,yyjssj, cszzjgdm)  values("
													+ "?, ?, ?, ?, ?,"
													+ "?,?,?,?,?,"
													+ "?,?,?,?,?)";
											List<Object[]> lists = new ArrayList<Object[]>();
											lists.add(new Object[] {
													placeBasicInfo.getId(),
													placeBasicInfo
															.getInternetServicePlaceCode(),
													placeBasicInfo
															.getInternetServicePlaceName(),
													placeBasicInfo
															.getDetailedAddress(),
													placeBasicInfo
															.getLongitude(),
													placeBasicInfo
															.getLatitude(),
													placeBasicInfo
															.getServiceType(),
													placeBasicInfo
															.getManagementNature(),
													placeBasicInfo
															.getBusinessLegalPerson(),
													placeBasicInfo
															.getValidDocumentTypeOfLegalPerson(),
													placeBasicInfo
															.getValidDocumentNumberOfLegalPerson(),
													placeBasicInfo
															.getContactInfomation(),
													placeBasicInfo
															.getBusinessStartTime(),
													placeBasicInfo
															.getBusinessEndTime(),
													placeBasicInfo
															.getVendorOrganizationCode() });
											JDBCWrapper.getJDBCInstance()
													.doBatch(sql, lists);

											// 更新场所对应的监控开始时间
											sql = "insert into t_csjk_wificsjkrs(id, jkrs,jksj, cs_id) values("
													+ "?, ?, ?, ?)";
											lists = new ArrayList<Object[]>();
											lists.add(new Object[] {
													UUID.randomUUID()
															.toString(),
													0,
													new Date(),
													placeBasicInfo.getId() });
											JDBCWrapper.getJDBCInstance()
													.doBatch(sql, lists);
											
											//添加索引
											importToSolr(placeBasicInfo);
										}
										// update
										else {
											String sql = "update t_csjk_wificsxx set swfwcsbm = ?, swfwcsmc = ?, csxxdz = ?, csjd = ?, cswd = ?,"
													+ "csfwlx = ?,csjyxz = ?,csjyfr = ?,csjyfryxzjlx = ?,csjyfryxzjhm = ?,"
													+ "lxfs = ?,yykssj = ?,yyjssj = ?,cszzjgdm = ? where id = ?";
											List<Object[]> lists = new ArrayList<Object[]>();
											lists.add(new Object[] {
													placeBasicInfo
															.getInternetServicePlaceCode(),
													placeBasicInfo
															.getInternetServicePlaceName(),
													placeBasicInfo
															.getDetailedAddress(),
													placeBasicInfo
															.getLongitude(),
													placeBasicInfo
															.getLatitude(),
													placeBasicInfo
															.getServiceType(),
													placeBasicInfo
															.getManagementNature(),
													placeBasicInfo
															.getBusinessLegalPerson(),
													placeBasicInfo
															.getValidDocumentTypeOfLegalPerson(),
													placeBasicInfo
															.getValidDocumentNumberOfLegalPerson(),
													placeBasicInfo
															.getContactInfomation(),
													placeBasicInfo
															.getBusinessStartTime(),
													placeBasicInfo
															.getBusinessEndTime(),
													placeBasicInfo
															.getVendorOrganizationCode(),
													id });
											JDBCWrapper.getJDBCInstance()
													.doBatch(sql, lists);
										}
									}
								});
					}
				});
			}
		});

	}
	
	private static void importToSolr(PlaceBasicInfo place){
		Map<String, Object> map = new HashMap<>();
		map.put("name", place.getInternetServicePlaceName());
		map.put("address", place.getDetailedAddress());
		map.put("position", place.getLatitude() + "," + place.getLongitude());
		map.put("id", place.getInternetServicePlaceCode());
		
		SolrHelper.getInstance(new PropertiesAnalysis().analyze("ZKHOSTSOLR")).addIndex(SOLR_COLLECTION_WIFIPOINT, "id", map);
	}

	static void generateFiveColorPeopleData(
			JavaPairInputDStream<String, String> messages) {
		JavaPairDStream<String, String> wifidatamessages = messages
				.filter(new Function<Tuple2<String, String>, Boolean>() {
					@Override
					public Boolean call(Tuple2<String, String> v1)
							throws Exception {
//						return topicWifidata.equals(v1._1);
						return true;
					}
				});

		// 将输入的数据转化为<csid, WifiData>
		JavaPairDStream<String, WifiData> wifiDatas = wifidatamessages
				.mapToPair(new PairFunction<Tuple2<String, String>, String, WifiData>() {
					@Override
					public Tuple2<String, WifiData> call(
							Tuple2<String, String> tuple2) throws Exception {
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
						return new Tuple2(strs[2], wifiData);
					}
				});

		// 根据重点人过滤信息
		JavaPairDStream<String, WifiData> wifiDatasForFiveColorPersons = wifiDatas
				.filter(new Function<Tuple2<String, WifiData>, Boolean>() {
					private static final long serialVersionUID = 1L;
					// 这里查询所有的五色人员信息，查询数据库返回所有的mac对应的无色人员信息
					// mac->信息
					Map<String, MobilePhoneInfo> mobilePhoneInfos = queryMobilePhoneTerminalDeviceInfo();
					// 查询场所的信息
					// 场所编码-》场所信息
					Map<String, PlaceBasicInfo> placeBasicInfos = queryPlaceBasicInfo();

					@SuppressWarnings("unchecked")
					@Override
					public Boolean call(Tuple2<String, WifiData> data)
							throws Exception {

						// 判断wifi围栏获取的数据是否在重点人监控的mac地址中
						// mac地址相同
						if (mobilePhoneInfos.containsKey(data._2.getMac())) {
							MobilePhoneInfo mobilePhoneInfo = mobilePhoneInfos
									.get(data._2.getMac());
							data._2.setIdcode(mobilePhoneInfo
									.getHighriskPerson().getIdcode());
							List<String> list = new ArrayList<String>();
							for (HighriskPeopleType highriskPeopleType : mobilePhoneInfo
									.getHighriskPerson()
									.getHighriskPeopleTypes()) {
								list.add(highriskPeopleType.getPeopleType());
							}
							data._2.setPeopleType(list);
							
//							System.out.println("场所数据数量：" + placeBasicInfos.size());
//							
//							System.out.println("场所数据  " + placeBasicInfos.get(
//										data._2.getPlaceId())
//										.getInternetServicePlaceName());
							// 查找场所名称System.out.println("场所数据  " + placeBasicInfos.get(
							
							if (placeBasicInfos.containsKey(data._2
									.getPlaceId())) {
								data._2.setPlaceName(placeBasicInfos.get(
										data._2.getPlaceId())
										.getInternetServicePlaceName());
							}
							data._2.setFiveColorPersonName(mobilePhoneInfo
									.getHighriskPerson().getName());
							data._2.setWarnType(mobilePhoneInfo
									.getHighriskPerson().getWarnType());

							// 根据场所编号查询是否是居住小区
							String sql = "select * from t_csjk_wificsxx t1 where swfwcsbm = ? and sfjjzxq = 1";
							// 拼接前科类型用于查询(指人员类型)
							for (String str : data._2.getPeopleType()) {
								// 有过盗窃前科 017004001是盗窃案的前缀编码
								if (str.startsWith(daoqiePrefix)) {
									data._2.setTheft(true);
								}
							}
							List<Object[]> lists = new ArrayList<Object[]>();
							lists.add(new Object[] { data._2.getPlaceId() });

							try {
								// 是居住小区
								if (JDBCWrapper.getJDBCInstance().doQueryCount(
										sql, lists) > 0) {
									data._2.setResidentialArea(true);
								}

							} catch (NumberFormatException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							return true;
						}
						return false;
					}
				});

		// 讲重点人数据发送到kafka中
		wifiDatasForFiveColorPersons
				.foreachRDD(new VoidFunction<JavaPairRDD<String, WifiData>>() {
					@Override
					public void call(JavaPairRDD<String, WifiData> javaPairRDD)
							throws Exception {
						final KafkaProducer kafkaProducer = new KafkaProducer(DataContants.BROKER);
						javaPairRDD
								.foreach(new VoidFunction<Tuple2<String, WifiData>>() {
									@Override
									public void call(
											Tuple2<String, WifiData> arg0)
											throws Exception {
										kafkaProducer.sendData(
												DataContants.TOPIC_HPRDATA,
												JSONObject.fromObject(arg0._2)
														.toString().getBytes());
									}

								});
						kafkaProducer.destroy();
					}
				});
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
