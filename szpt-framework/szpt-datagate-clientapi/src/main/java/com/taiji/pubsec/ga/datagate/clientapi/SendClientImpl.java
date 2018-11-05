/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月27日 下午3:49:47
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.taiji.pubsec.ga.datagate.DefaultMessageImpl;
import com.taiji.pubsec.ga.datagate.Message;
import com.taiji.pubsec.ga.datagate.MessageType;

/**
 * SDKAPI的实现类。此类的对象实例通过SendClientFactory生成并返回给客户端。
 * 
 * @author yucy
 *
 */
class SendClientImpl implements SendClient {
	private static final Logger logger = LoggerFactory.getLogger(SendClientImpl.class);
	
	static long TIMEOUT = 2000;
	private Destination reqSendDestination;
	
	private IdGenerate idGen;
	private JmsTemplate jmsTemplate;
	private MessageStage msgStage;
	private AsyncTimeoutManager asyncTimeoutManager;

	@SuppressWarnings("unused")
	private SendClientImpl() {
	}

	SendClientImpl(IdGenerate idGen, JmsTemplate jmsTemplate, MessageStage msgStage, Destination reqSendDestination, AsyncTimeoutManager asyncTimeoutManager) {
		super();
		this.idGen = idGen;
		this.jmsTemplate = jmsTemplate;
		this.msgStage = msgStage;
		this.reqSendDestination = reqSendDestination;
		this.asyncTimeoutManager = asyncTimeoutManager;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.SendClient#asyncSendMessage(java.io.Serializable[], com.taiji.pubsec.ga.datagate.clientapi.Callback)
	 */
	@Override
	public void asyncSendMessage(Serializable[] objs, Callback cbobj, long timeout) {
		Message msg = null;
		if(null != cbobj){
			msg = new DefaultMessageImpl(idGen.get(), MessageType.INOUT, objs, true);
			logger.debug("将异步消息({})放入记录。", msg.getId());
			msgStage.put(msg.getId(), cbobj);
			asyncTimeoutManager.put(msg.getId(), timeout);
		}else{
			msg = new DefaultMessageImpl(idGen.get(), MessageType.ONLYIN, objs, true);
		}
		
		// 向jms消息队列发送请求消息
		doSend(msg);
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.SendClient#syncSendMessage(java.io.Serializable[])
	 */
	@Override
	public Serializable[] syncSendMessage(Serializable[] objs) throws Exception {
		Message msg = new DefaultMessageImpl(idGen.get(), MessageType.INOUT, objs, true);
		SyncMessageHolder msgHolder = new SyncMessageHolder(msg);
		logger.debug("将同步消息({})放入记录。", msg);
		Object[] tos = {msgStage, msg.getId(), msgHolder};
		logger.trace("[ msgStage, msgid, msgHolder ] = {}", tos);
		msgStage.put(msg.getId(), msgHolder);
		
		// 向jms消息队列发送请求消息
		doSend(msg);
		
		synchronized (msgHolder) {
			try {
				msgHolder.wait(TIMEOUT);
			} catch (InterruptedException e) {
				logger.error("同步请求超时。", e);
				logger.debug("将超时消息({})记录移除。", msg);
				msgStage.remove(msg.getId());
			}
		}
		logger.debug("同步请求返回，将消息({})记录移除。", msg);
		msgStage.remove(msg.getId());
		
		Message rspmsg = msgHolder.getRspmsg();
		if(null == rspmsg){
			throw new RuntimeException("同步请求返回的消息为空。");
		}
		return rspmsg.getBody();
	}

	private void doSend(final Message msg){
		logger.debug("向 ‘请求发送消息队列({})’发送请求消息。", reqSendDestination);
		jmsTemplate.send(reqSendDestination, new MessageCreator() {
        	@Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(msg);
            }
        });
	}
}
