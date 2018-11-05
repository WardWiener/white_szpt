package com.taiji.pubsec.szpt.bigdata.wifibasicdatadeal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import kafka.serializer.StringDecoder;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import scala.Tuple2;

import com.taiji.pubsec.bigdata.bean.PlaceBasicInfo;
import com.taiji.pubsec.bigdata.bean.WifiData;
import com.taiji.pubsec.bigdata.common.DataContants;
import com.taiji.pubsec.szpt.bigdata.jdbc.JDBCWrapper;
import com.taiji.pubsec.szpt.bigdata.jdbc.JDBCWrapper.ExecuteCallBack;
import com.taiji.pubsec.szpt.bigdata.kafka.producer.KafkaProducer;
import com.taiji.pubsec.szpt.bigdata.wifidatabackup.SyncDFS;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;

public class WifiBasicdataDeal implements Serializable {
	private static final long serialVersionUID = -7030535756389151458L;
	public final static Pattern TAB = Pattern.compile("\t");
	private final static String topicTerminalgatherequipment = "terminalgatherequipment";
	private final static String topicPlaceinfo = "placeinfo";
	private final static String topicWifidata = "wifidata";
	// 盗窃案的前缀编码
	private final static String daoqiePrefix = "017004001";

	public static void main(String[] args) {
		// 第一步 从kafka接收数据
		// 设置匹配模式，以TAB分隔
		// 提交的参数冲突以程序设置的为准
		SparkConf sparkConf = new SparkConf().setAppName("WifiBasicdataDeal");
		// 批处理时间为10s
		JavaStreamingContext jssc = new JavaStreamingContext(sparkConf,
				new Duration(10000));
		// 设置检查点 hdfs目录
		jssc.checkpoint("wifiBasicdata_checkpoint"); // 设置检查点
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", DataContants.BROKER);
		kafkaParams.put("group.id", "WifiBasicdataDeal");

		String[] topictopicTerminalgatherequipment = new String[] { topicTerminalgatherequipment};
		String[] topictopicPlaceinfo = new String[] {topicPlaceinfo };
		String[] topictopicWifidata = new String[] { topicWifidata };
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topictopicTerminalgatherequipment));
		// Create direct kafka stream with brokers and topics
		JavaPairInputDStream<String, String> messagesterminalGatherEquipment = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);
		topicsSet = new HashSet<>(Arrays.asList(topictopicPlaceinfo));
		JavaPairInputDStream<String, String> messagesplace = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);
		topicsSet = new HashSet<>(Arrays.asList(topictopicWifidata));
		JavaPairInputDStream<String, String> messageswifidata = KafkaUtils
				.createDirectStream(jssc, String.class, String.class,
						StringDecoder.class, StringDecoder.class, kafkaParams,
						topicsSet);

		terminalGatherEquipmentParse(messagesterminalGatherEquipment);
		placeParse(messagesplace);
		messageswifidata.persist();
		wifidatabackup(messageswifidata);
		generateFiveColorPeopleData(messageswifidata);

		jssc.start();
		jssc.awaitTermination();
	}

	private static void terminalGatherEquipmentParse(
			JavaPairInputDStream<String, String> messages) {
//		JavaPairDStream<String, String> terminalGatherEquipmentMessages = messages
//				.filter(new Function<Tuple2<String, String>, Boolean>() {
//					@Override
//					public Boolean call(Tuple2<String, String> v1)
//							throws Exception {
////						System.out.println("接收的数据：" + v1._1 + " " + v1._2);
////						return topicTerminalgatherequipment.equals(v1._1);
//						return true;
//					}
//				});
//
//		// 第二步 按格式解析数据
//		JavaDStream<TerminalGatherEquipmentBasicInfo> terminalGatherEquipmentBasicInfos = terminalGatherEquipmentMessages
//				.map(new Function<Tuple2<String, String>, TerminalGatherEquipmentBasicInfo>() {
//					@Override
//					public TerminalGatherEquipmentBasicInfo call(
//							Tuple2<String, String> tuple2) {
//						String[] strs = WifiBasicdataDeal.TAB.split(tuple2._2);
//						TerminalGatherEquipmentBasicInfo terminalGatherEquipmentBasicInfo = null;
//						// 数据有问题
//						if (strs.length < 11) {
//							terminalGatherEquipmentBasicInfo = new TerminalGatherEquipmentBasicInfo(
//									"", "", "", "");
//						} else {
//							// ftp中数据格式
//							terminalGatherEquipmentBasicInfo = new TerminalGatherEquipmentBasicInfo(
//									strs[1], strs[8], strs[9], strs[0]);
//						}
//						// //样本数据中数据格式
//						// TerminalGatherEquipmentBasicInfo
//						// terminalGatherEquipmentBasicInfo = new
//						// TerminalGatherEquipmentBasicInfo(strs[0], strs[3],
//						// strs[4], strs[2]);
//						return terminalGatherEquipmentBasicInfo;
//					}
//				});
//
//		// 调用jdbc链接，保存入库
//		terminalGatherEquipmentBasicInfos
//				.foreachRDD(new VoidFunction<JavaRDD<TerminalGatherEquipmentBasicInfo>>() {
//					@Override
//					public void call(
//							JavaRDD<TerminalGatherEquipmentBasicInfo> rdd)
//							throws Exception {
//						rdd.foreach(new VoidFunction<TerminalGatherEquipmentBasicInfo>() {
//							@Override
//							public void call(
//									final TerminalGatherEquipmentBasicInfo terminalGatherEquipmentBasicInfo)
//									throws Exception {
//								List<Object[]> lists = new ArrayList<Object[]>();
//								lists.add(new Object[] { terminalGatherEquipmentBasicInfo
//										.getAcquisitionEquipmentNumber() });
//								JDBCWrapper
//										.getJDBCInstance()
//										.doQuery(
//												"select * from t_csjk_zdtzcjsbjcxx where cjsbbh=?",
//												lists, new ExecuteCallBack() {
//													@Override
//													public void resultCallBack(
//															ResultSet result)
//															throws Exception {
//														int rowCount = 0;
//														String id = "";
//														while (result.next()) {
//															rowCount++;
//															id = result
//																	.getString("id");
//														}
//														// add
//														if (rowCount == 0) {
//															String sql = "insert into t_csjk_zdtzcjsbjcxx(id, cjsbbh, cjsbjd, cjsbwd, csbh) values("
//																	+ "?, ?, ?, ?, ?)";
//															List<Object[]> lists = new ArrayList<Object[]>();
//															lists.add(new Object[] {
//																	terminalGatherEquipmentBasicInfo
//																			.getId(),
//																	terminalGatherEquipmentBasicInfo
//																			.getAcquisitionEquipmentNumber(),
//																	terminalGatherEquipmentBasicInfo
//																			.getAcquisitionEquipmentLongitude(),
//																	terminalGatherEquipmentBasicInfo
//																			.getAcquisitionEquipmentLatitude(),
//																	terminalGatherEquipmentBasicInfo
//																			.getPlaceNumber() });
//															JDBCWrapper
//																	.getJDBCInstance()
//																	.doBatch(
//																			sql,
//																			lists);
//														}
//														// update
//														else {
//															String sql = "update t_csjk_zdtzcjsbjcxx set cjsbbh = ?, cjsbjd = ?, cjsbwd=?, csbh = ? where id = ?";
//															List<Object[]> lists = new ArrayList<Object[]>();
//															lists.add(new Object[] {
//																	terminalGatherEquipmentBasicInfo
//																			.getAcquisitionEquipmentNumber(),
//																	terminalGatherEquipmentBasicInfo
//																			.getAcquisitionEquipmentLongitude(),
//																	terminalGatherEquipmentBasicInfo
//																			.getAcquisitionEquipmentLatitude(),
//																	terminalGatherEquipmentBasicInfo
//																			.getPlaceNumber(),
//																	id });
//															JDBCWrapper
//																	.getJDBCInstance()
//																	.doBatch(
//																			sql,
//																			lists);
//														}
//
//													}
//												});
//
//							}
//						});
//					}
//				});

	}

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
						String[] strs = WifiBasicdataDeal.TAB.split(tuple2._2);
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
								"select * from t_csjk_csjcxx where swfwcsbm=?",
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
											String sql = "insert into t_csjk_csjcxx(id, swfwcsbm, swfwcsmc, csxxdz, csjd, cswd,"
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
											sql = "insert into t_csjk_csjkrs(id, csrs, cs_id) values("
													+ "?, ?, ?)";
											lists = new ArrayList<Object[]>();
											lists.add(new Object[] {
													UUID.randomUUID()
															.toString(),
													0,
													placeBasicInfo.getId() });
											JDBCWrapper.getJDBCInstance()
													.doBatch(sql, lists);
										}
										// update
										else {
											String sql = "update t_csjk_csjcxx set swfwcsbm = ?, swfwcsmc = ?, csxxdz = ?, csjd = ?, cswd = ?,"
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

	private static void wifidatabackup(
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

		// 将输入的数据转化为<csid, Integer> Integer每个场所的监控的人数
		JavaPairDStream<String, Integer> wifiDatas = wifidatamessages
				.mapToPair(
						new PairFunction<Tuple2<String, String>, String, Integer>() {
							@Override
							public Tuple2<String, Integer> call(
									Tuple2<String, String> tuple2)
									throws Exception {
								String[] strs = WifiBasicdataDeal.TAB
										.split(tuple2._2);
								return new Tuple2(strs[2], 1);
							}
						}).reduceByKey(
						new Function2<Integer, Integer, Integer>() {
							@Override
							public Integer call(Integer x1, Integer x2)
									throws Exception {
								return x1 + x2;
							}
						});

		// 更新每个场所监控的人数
		wifiDatas.foreachRDD(new VoidFunction<JavaPairRDD<String, Integer>>() {
			@Override
			public void call(JavaPairRDD<String, Integer> rdd) throws Exception {
				rdd.foreach(new VoidFunction<Tuple2<String, Integer>>() {
					@Override
					public void call(Tuple2<String, Integer> tuple2)
							throws Exception {
						String sql = "update t_csjk_csjkrs t1, t_csjk_csjcxx t2 set csrs = ? where t1.cs_id=t2.id and t2.swfwcsbm=?";
						List<Object[]> lists = new ArrayList<Object[]>();
						lists.add(new Object[] { tuple2._2, tuple2._1 });
						JDBCWrapper.getJDBCInstance().doBatch(sql, lists);
					}
				});
			}
		});

		final String dir = "wifidata";
		// 新建目录
		new SyncDFS().mkdir(dir);
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		wifidatamessages
				.foreachRDD(new VoidFunction<JavaPairRDD<String, String>>() {
					@Override
					public void call(JavaPairRDD<String, String> javaPairRDDs)
							throws Exception {
						StringBuffer sb = new StringBuffer();
						List<Tuple2<String, String>> list = javaPairRDDs
								.collect();
						for (Tuple2<String, String> tuple2 : list) {
							sb.append(tuple2._2 + "\n");
						}
						InputStream in = new ByteArrayInputStream(sb.toString()
								.getBytes());
						new SyncDFS().appendFile(in,
								dir + File.separator + sdf.format(new Date()));
					}
				});

	}

	public static void generateFiveColorPeopleData(
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
								strs[23]);
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
							
							System.out.println("场所数据数量：" + placeBasicInfos.size());
							
							System.out.println("场所数据  " + placeBasicInfos.get(
										data._2.getPlaceId())
										.getInternetServicePlaceName());
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
							String sql = "select * from t_csjk_csjcxx t1, t_csjk_csjkrs t2 where t1.id=t2.cs_id and t1.swfwcsbm = ? and t2.sfjzxq = 1";
							// 拼接前科类型用于查询
							for (String str : data._2.getPeopleType()) {
								// 有过盗窃前科 014009001是盗窃案的前缀编码
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
						final KafkaProducer kafkaProducer = new KafkaProducer(
								DataContants.BROKER);
						javaPairRDD
								.foreach(new VoidFunction<Tuple2<String, WifiData>>() {
									@Override
									public void call(
											Tuple2<String, WifiData> arg0)
											throws Exception {
										kafkaProducer.sendData(
												DataContants.FIVECOLORDATA,
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
			String sqlText = "select * from t_csjk_csjcxx";
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
			String sqlText = "select * from t_gwry_yddhxx t1, t_gwry_gwry t2 where t1.gwry_id = t2.id";
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
					String sqlText1 = "select * from t_gwry_gwrylx t1, t_gwry_gwry t2 where t1.gwry_id = t2.id and t2.id='"
							+ result.getString("gwry_id") + "'";
					preparedStatement1 = conn.prepareStatement(sqlText1);
					result1 = preparedStatement1.executeQuery();
					List<HighriskPeopleType> peopleTypes = new ArrayList<HighriskPeopleType>();
					while (result1.next()) {
						HighriskPeopleType peopleType = new HighriskPeopleType();
						peopleType.setPeopleType(result1.getString("rylx"));
						peopleTypes.add(peopleType);
					}
					Set<HighriskPeopleType> set = new HashSet<HighriskPeopleType>();
					for(HighriskPeopleType type: peopleTypes){
						set.add(type);
					}
					fiveColorPerson.setHighriskPeopleTypes(set);
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
