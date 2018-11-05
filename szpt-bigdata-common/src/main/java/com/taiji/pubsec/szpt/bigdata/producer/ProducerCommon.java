package com.taiji.pubsec.szpt.bigdata.producer;


import java.util.Properties;

import com.taiji.pubsec.bigdata.common.DataContants;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class ProducerCommon {
	public static Producer<Integer, String> createProducer() {
		Properties properties = new Properties();
		properties.put("serializer.class", StringEncoder.class.getName());
		//kafka broker
		properties.put("metadata.broker.list",
				DataContants.BROKER);
		return new Producer<Integer, String>(new ProducerConfig(properties));
	}
}
