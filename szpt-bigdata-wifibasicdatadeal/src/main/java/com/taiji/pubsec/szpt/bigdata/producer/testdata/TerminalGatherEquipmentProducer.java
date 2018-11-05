package com.taiji.pubsec.szpt.bigdata.producer.testdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.taiji.pubsec.bigdata.common.DataContants;
import com.taiji.pubsec.szpt.bigdata.kafka.producer.KafkaProducer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

/**
 * kafka 产生采集设备的消息
 * @author genganpeng
 * 
 */
public class TerminalGatherEquipmentProducer extends Thread {

	private String topic;

	public TerminalGatherEquipmentProducer(String topic) {
		super();
		this.topic = topic;
	}

	@Override
	public void run() {
		KafkaProducer kafkaProducer = new KafkaProducer(DataContants.BROKER);
		int i = 0;
		try {
			FileReader fr = new FileReader(new File("139-520100-1468931356-94054-WA_BASIC_FJ_0003-0.bcp"));
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			while ((str = br.readLine()) != null) {
				kafkaProducer.sendData(topic, str.getBytes());
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			kafkaProducer.destroy();
		}
		
	}


	public static void main(String[] args) {
		new TerminalGatherEquipmentProducer("terminalgatherequipment").start();
	}

}