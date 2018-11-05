package com.taiji.pubsec.szpt.bigdata.kafka.producer;

import java.io.Serializable;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer implements Serializable{
	private static final long serialVersionUID = -6050536355804664912L;

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	
	private String brokerList;
	
	private Producer<String, byte[]> producer;
	
	public KafkaProducer(String brokerList) {
		this.brokerList = brokerList;
	}

	public KafkaProducer() {
	}

	private Producer<String, byte[]> getProducer(){
		if(null!=producer){
			return producer;
		}
		
		Properties props = new Properties();
		props.setProperty("metadata.broker.list", brokerList);
		ProducerConfig config = new ProducerConfig(props);
		producer = new Producer<String, byte[]>(config);
		
		return producer;
	}
	
	public void destroy(){
		if( null!=producer ){
			logger.info("关闭kafka producer。");
			producer.close();
		}
	}

	public void sendData(KeyedMessage<String, byte[]> message) {
		getProducer().send(message);
	}
	
	public void sendData(String topic, String key,Object partKey, byte[] message){
		KeyedMessage<String, byte[]> data = new KeyedMessage<String, byte[]>(topic, key, partKey, message);
		getProducer().send(data);
	}
	
	public void sendData(String topic, byte[] message){
		KeyedMessage<String, byte[]> data = new KeyedMessage<String, byte[]>(topic, message);
		getProducer().send(data);
	}

	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

}
