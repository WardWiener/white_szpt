/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 上午11:47:41
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author yucy
 *
 */
public final class SendClientFactory {
	private static final Logger logger = LoggerFactory.getLogger(SendClientFactory.class);
	
	private static IdGenerate idGen = new DefaultIdGenerator();
	private static JmsTemplate jmsTemplate;
	private static MessageStage msgStage;
	private static Destination reqSendDestination;
	private static AsyncTimeoutManager asyncTimeoutManager;
	
	public static SendClient get(){
		SendClientImpl client = new SendClientImpl(idGen, jmsTemplate, msgStage, reqSendDestination, asyncTimeoutManager);
		return client;
	}

	public SendClientFactory(IdGenerate idGen, JmsTemplate jmsTemplate, MessageStage msgStage, Destination reqSendDestination, AsyncTimeoutManager asyncTimeoutManager,long timeout) {
		Object[] params = {idGen, jmsTemplate, msgStage, reqSendDestination, timeout};
		logger.debug("设置属性：{}", params);
		SendClientFactory.idGen = idGen;
		SendClientFactory.jmsTemplate = jmsTemplate;
		SendClientFactory.msgStage = msgStage;
		SendClientFactory.reqSendDestination = reqSendDestination;
		SendClientFactory.asyncTimeoutManager = asyncTimeoutManager;
		SendClientImpl.TIMEOUT = timeout;
	}

}
