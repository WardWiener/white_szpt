package com.taiji.pubsec.szpt.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yucy
 *
 */
public abstract class KafkaReceiver {
	private static Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);
	
	protected ConsumerConnector consumer = null;
	protected String zookeeper;
	protected String groupId;
	protected String topic;
	protected String zookeeperSyncTimeMs = "200";
	protected String autoCommitIntervalMs = "1000";
	protected String autoOffsetReset = "largest";
	protected int threadNum = 3;
	protected ExecutorService taskExecutor;

	
	public void letitgo() {
		LOGGER.debug("初始化线程池...");
		taskExecutor = Executors.newFixedThreadPool(threadNum);
		Properties props = new Properties();
		props.put("zookeeper.connect", zookeeper);
		props.put("group.id", groupId);
		props.put("zookeeper.sync.time.ms", zookeeperSyncTimeMs);
		props.put("auto.commit.interval.ms", autoCommitIntervalMs);
		props.put("auto.offset.reset", autoOffsetReset);
		props.put("rebalance.backoff.ms", "2000");
		props.put("rebalance.max.retries", "10");

		LOGGER.debug("创建ConsumerConnector");
		ConsumerConfig conf = new ConsumerConfig(props);
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(conf);

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(threadNum));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		LOGGER.debug("获取kafkaStream...");
		List<KafkaStream<byte[], byte[]>> DataStreams = consumerMap.get(topic);

		for (final KafkaStream<byte[], byte[]> stream : DataStreams) {

			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					ConsumerIterator<byte[], byte[]> it = stream.iterator();
					LOGGER.debug("循环stream.iterator..");
					while (it.hasNext()) {
						final byte[] message = it.next().message();
						LOGGER.trace("消费消息:{}", SerializeUtils.unserialize(message));
						processData(message);
					}
				}
			});
		}
	}

	protected abstract void processData(byte[] message);

	public void setZookeeper(String zookeeper) {
		this.zookeeper = zookeeper;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setZookeeperSyncTimeMs(String zookeeperSyncTimeMs) {
		this.zookeeperSyncTimeMs = zookeeperSyncTimeMs;
	}

	public void setAutoCommitIntervalMs(String autoCommitIntervalMs) {
		this.autoCommitIntervalMs = autoCommitIntervalMs;
	}

	public void setAutoOffsetReset(String autoOffsetReset) {
		this.autoOffsetReset = autoOffsetReset;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public void setTaskExecutor(ExecutorService taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public ConsumerConnector getConsumer() {
		return consumer;
	}

	public void setConsumer(ConsumerConnector consumer) {
		this.consumer = consumer;
	}

}
