package com.taiji.pubsec.szpt.bigdata.producer.testdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

import com.taiji.pubsec.bigdata.common.DataContants;
import com.taiji.pubsec.szpt.bigdata.kafka.producer.KafkaProducer;

/**
 * kafka 产生wifi数据的消息
 * @author genganpeng
 *
 */
public class WifiDataProducer extends Thread {

	private String topic;

	public WifiDataProducer(String topic) {
		super();
		this.topic = topic;
	}

	@Override
	public void run() {
		KafkaProducer kafkaProducer = new KafkaProducer(DataContants.BROKER);
		try {
			String templateStr = "1020002	${mac}	${place}	5	102	${mac}			723005104A4FB8D045DA7		${startTime}	${endTime}	167772196	30206396		9180	9435			${mac}	723005104A4FB8D045DA7	A4-FB-8D-04-5D-A7	${jd}	${wd}	52011325000344847303EC897D1468931213	-56								";
			List<String> macs = new ArrayList<String>();
			macs.add("54-AE-27-D6-E2-BC");
			macs.add("00-61-71-AD-7B-0E");
			macs.add("8C-34-FD-B2-C6-7D");
			macs.add("D0-A6-37-08-DC-92");
			macs.add("90-00-DB-98-E2-26");
			List<String> places = new ArrayList<String>();
			places.add("52010429001040");
			places.add("52011329000004");
			places.add("52011325000104");
			places.add("52011325000075");
			places.add("52010427000873");
			List<String> jds = new ArrayList<String>();
			List<String> wds = new ArrayList<String>();
			jds.add("106.720716");
			jds.add("106.633216");
			jds.add("106.632954");
			jds.add("106.635034");
			jds.add("106.708856");
			wds.add("26.512064");
			wds.add("26.695046");
			wds.add("26.689554");
			wds.add("26.685335");
			wds.add("26.530105");
			
			int placeSizes = places.size();
			for (int i = 0; i < 100; i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("mac", macs.get(i % placeSizes));
				map.put("place", places.get(i % placeSizes));
				map.put("startTime", String.valueOf(new Date().getTime() / 1000));
				map.put("endTime", "");
				map.put("jd", jds.get(i % placeSizes));
				map.put("wd", wds.get(i % placeSizes));
				StrSubstitutor strSubstitutor = new StrSubstitutor(map);
//				System.out.println(strSubstitutor.replace(templateStr));
				kafkaProducer.sendData(topic, strSubstitutor.replace(templateStr).getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			kafkaProducer.destroy();
		}
		
	}


	public static void main(String[] args) {
		new WifiDataProducer("wifidata").start();
	}

}