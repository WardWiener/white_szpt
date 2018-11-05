package com.taiji.pubsec.szpt.highriskpersonalert.kafka.wifi;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;
import com.taiji.pubsec.szpt.highriskpersonalert.model.AlertInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.AlertService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IAlertInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.placemonitor.model.PeopleType;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * kafka alert消费者公共类。
 * 
 * @author dixf
 *
 */
public abstract class AbstractKafkaConsumer extends KafkaReceiver {
	public static final String NODEAL_STATUS = "0";
	@Resource
	WifiPlaceInAndOutInfoQueryService wifiPlaceInAndOutInfoQueryService;
	@Resource
	AlertService alertService;
	@Resource
	IAlertInfoService alertInfoService;
	@Resource
	IHighriskPersonService highriskPersonService;
//	@Resource
//	private ThreadPoolTaskExecutor taskExecutor;
//	
//	private ConsumerConnector consumer = null;
//	private String zookeeper;
//	private String groupId;
//	private String topic;
//	private String zookeeper_sync_time_ms;
//	private String auto_commit_interval_ms;
//	private int thread_num;

//	public String getZookeeper() {
//		return zookeeper;
//	}
//
//	public void setZookeeper(String zookeeper) {
//		this.zookeeper = zookeeper;
//	}
//
//	public String getGroupId() {
//		return groupId;
//	}
//
//	public void setGroupId(String groupId) {
//		this.groupId = groupId;
//	}
//
//	public String getTopic() {
//		return topic;
//	}
//
//	public void setTopic(String topic) {
//		this.topic = topic;
//	}
//
//	public String getZookeeper_sync_time_ms() {
//		return zookeeper_sync_time_ms;
//	}
//
//	public void setZookeeper_sync_time_ms(String zookeeper_sync_time_ms) {
//		this.zookeeper_sync_time_ms = zookeeper_sync_time_ms;
//	}
//
//	public String getAuto_commit_interval_ms() {
//		return auto_commit_interval_ms;
//	}
//
//	public void setAuto_commit_interval_ms(String auto_commit_interval_ms) {
//		this.auto_commit_interval_ms = auto_commit_interval_ms;
//	}
//
//	public int getThread_num() {
//		return thread_num;
//	}
//
//	public void setThread_num(int thread_num) {
//		this.thread_num = thread_num;
//	}
//	public AbstractKafkaConsumer() {
//
//	}
//
//	private ConsumerConfig createConsumerConfig() {
//		Properties props = new Properties();
//		props.put("zookeeper.connect", zookeeper);
//		props.put("group.id", groupId);
//		props.put("zookeeper.sync.time.ms", zookeeper_sync_time_ms);
//		props.put("auto.commit.interval.ms", auto_commit_interval_ms);
////		props.put("zookeeper.connect", zookeeper);
////	    props.put("group.id", groupId);
////	    props.put("zookeeper.session.timeout.ms", "400");
////	    props.put("zookeeper.sync.time.ms", "200");
////	    props.put("auto.commit.interval.ms", "1000");
//
//		return new ConsumerConfig(props);
//	}
//
//	@PostConstruct
//	public void run() {
//		if (consumer == null) {
//			consumer = kafka.consumer.Consumer
//					.createJavaConsumerConnector(createConsumerConfig());
//		}
//		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//		topicCountMap.put(topic, new Integer(thread_num));
//		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
//				.createMessageStreams(topicCountMap);
//		List<KafkaStream<byte[], byte[]>> DataStreams = consumerMap.get(topic);
//		
//		for (final KafkaStream<byte[], byte[]> stream : DataStreams) {
//			taskExecutor.execute(new Runnable() {
//				@Override
//				public void run() {
//					ConsumerIterator<byte[], byte[]> it = stream.iterator();
//					while (it.hasNext()) {
//						String message;
//						try {
//							message = new String(it.next().message(), "utf-8");
//						} catch (UnsupportedEncodingException e) {
//							e.printStackTrace();
//							continue;
//						}
//						processData(message);
//					}
//					
//				}
//			});
//		}
//	}
//
//	abstract protected void processData(String message);
	
	protected String generateValues(List<String> values) {
		StringBuffer valuesStr = new StringBuffer();
		for(String value : values) {
			valuesStr.append(value).append(",");
		}
		if (!StringUtils.isEmpty(valuesStr))
			valuesStr.deleteCharAt(valuesStr.length() - 1);
		return valuesStr.toString();
	}
	
	protected String generateWarningStr(String warningTemplate, Map<String, String> values) {
		 StrSubstitutor sub = new StrSubstitutor(values);
		 return sub.replace(warningTemplate);
	}
	
	protected void saveWifidata(WifiData wifiData) {
		//对于没有保存过的wifi命中率数据保存
		WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = wifiDataToWifiPlaceInAndOutInfo(wifiData);
		List<WifiPlaceInAndOutInfo> wifiPlaceInAndOutInfos = wifiPlaceInAndOutInfoQueryService.queryWifiPlaceInAndOutInfo(wifiPlaceInAndOutInfo);
		//此wifidata数据不存在则保存
		if (wifiPlaceInAndOutInfos == null || wifiPlaceInAndOutInfos.size() == 0) {
			wifiPlaceInAndOutInfoQueryService.saveWifiPlaceInAndOutInfo(wifiPlaceInAndOutInfo);
			wifiData.setId(wifiPlaceInAndOutInfo.getId());
		}
		//有两种情况：一是有进入时间没有离开过，存在于已经进入和离开的某段时间之间
		else {
			WifiPlaceInAndOutInfo tmp = wifiPlaceInAndOutInfos.get(0);
			wifiData.setId(tmp.getId());
			if (tmp.getLeaveTime() == null) {
				wifiPlaceInAndOutInfo.setId(tmp.getId());
				wifiPlaceInAndOutInfoQueryService.updateWifiPlaceInAndOutInfo(wifiPlaceInAndOutInfo);
			}
		}
		
	}
	
	protected WifiPlaceInAndOutInfo wifiDataToWifiPlaceInAndOutInfo(WifiData wifidata) {
		try {
			WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = new WifiPlaceInAndOutInfo();
			// 下面转换时间格式为了和mysql数据库中数据比较对应上，否则无法匹配
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(new Date(Long.valueOf(wifidata.getEnterTime())));
			wifiPlaceInAndOutInfo.setEnterTime(sdf.parse(str));
			if(wifidata.getPeopleType()!= null && wifidata.getPeopleType().size() > 0) {
				List<PeopleType> peopleTypes = new ArrayList<PeopleType>();
				for (String peopleTypeStr : wifidata.getPeopleType()) {
					PeopleType peopleType = new PeopleType();
					peopleType.setPeopleType(peopleTypeStr);
					peopleTypes.add(peopleType);
				}
				Set<PeopleType> set = new HashSet<PeopleType>();
				for(PeopleType type: peopleTypes){
					set.add(type);
				}
				wifiPlaceInAndOutInfo.setPeopleTypes(set);
			}
			wifiPlaceInAndOutInfo.setIdCode(wifidata.getIdcode());
			wifiPlaceInAndOutInfo.setLatitude(wifidata.getLatitude());
			wifiPlaceInAndOutInfo.setLongitude(wifidata.getLongitude());
			wifiPlaceInAndOutInfo.setMac(wifidata.getMac());
			wifiPlaceInAndOutInfo.setPersonName(wifidata.getFiveColorPersonName());
			wifiPlaceInAndOutInfo.setPlace(wifidata.getPlaceName());
			if (!StringUtils.isEmpty(wifidata.getLeaveTime())) {
				str = sdf.format(new Date(Long.valueOf(wifidata.getLeaveTime())));
				wifiPlaceInAndOutInfo.setLeaveTime(sdf.parse(str));
			}
			return wifiPlaceInAndOutInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 保存预警数据 
	 * @param wifiDatas wifi轨迹数据  
	 * @param alert
	 */
	public void saveAlert(List<WifiData> wifiDatas, Alert alert) {
		//对于没有保存过的wifi命中率数据保存
		for (WifiData wifiData : wifiDatas) {
			saveWifidata(wifiData);
		}
		//根据条件查询之前是否已经生成过此预警
		List<Alert> list = alertService.findByAlert(alert);
		
		// 表示此预警是否已经生成过
		boolean flag = false;
		for (Alert tmpAlert : list) {
			Set<AlertInfo> alertInfos = tmpAlert.getAlertInfos();
			if (wifiDatas.size() != alertInfos.size()) {
				continue;
			}
			int count = 0;
			for (AlertInfo alertInfo : alertInfos) {
				WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = wifiPlaceInAndOutInfoQueryService.findById(alertInfo.getTargetId());
				for (WifiData wifiData : wifiDatas) {
					if (wifiPlaceInAndOutInfo.getId()
							.equals(wifiData.getId())) {
						count++;
						break;
					}
				}
			}

			// 此预警之前已经保存过不再保存
			if (wifiDatas.size() == count) {
				flag = true;
			}
		}

		// 此预警已经生成过了则退出
		if (flag == true) {
			return;
		}

		// 保存预警消息
		alertService.saveAlert(alert);
		for (WifiData wifiData : wifiDatas) {
			WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = (WifiPlaceInAndOutInfo) wifiPlaceInAndOutInfoQueryService.findById(wifiData.getId());
			AlertInfo alertInfo = new AlertInfo(wifiPlaceInAndOutInfo);
			alertInfo.setAlert(alert);
			alertInfoService.saveAlertInfo(alertInfo);
		}
	}

}