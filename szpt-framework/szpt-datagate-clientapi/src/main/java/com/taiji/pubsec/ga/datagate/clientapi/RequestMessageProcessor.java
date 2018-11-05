/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 下午4:04:09
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.taiji.pubsec.ga.datagate.Message;
import com.taiji.pubsec.ga.datagate.MessageType;

/**
 * 请求消息接收处理器：侦听请求消息接收队列，调用本地方法处理请求消息。
 * 
 * @author yucy
 *
 */
public class RequestMessageProcessor implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(RequestMessageProcessor.class);

	private JmsTemplate jmsTemplate;
	//响应消息发送队列
	private Destination rspSendDestination;
	
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	private static List<RequestMessageExecute> executors = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(javax.jms.Message msg) {
		if( !(msg instanceof ObjectMessage) ){
			logger.error("JMS消息不是{}类型", ObjectMessage.class.toString());
			return;
		}

		Serializable msgobj = null;
		try {
			msgobj = ((ObjectMessage)msg).getObject();
		} catch (JMSException e) {
			logger.error("", e);
		}
		
		if( !(msgobj instanceof Message) ){
			logger.error("请求消息不是{}类型", Message.class.toString());
			return;
		}
		Message reqmsg = (Message)msgobj;
		
		logger.debug("接收到请求消息{}，准备调用处理程序...", reqmsg);
		doProcess(reqmsg);
		logger.debug("对请求消息处理完成。");
	}

	private void doProcess(Message msg) {
		for(RequestMessageExecute executor : executors){
			if(executor.isSupport(msg)){
				cachedThreadPool.execute(new ReqMsgExecuteRunner(executor, msg));
				return;
			}
		}
		
		logger.error("请求消息{}没有可利用的执行器。", msg);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setRspSendDestination(Destination rspSendDestination) {
		this.rspSendDestination = rspSendDestination;
	}

	public static void setExecutors(List<RequestMessageExecute> executors) {
		RequestMessageProcessor.executors = executors;
	}

	private class ReqMsgExecuteRunner implements Runnable{
		private RequestMessageExecute executor;
		private Message msg;
		
		protected ReqMsgExecuteRunner(RequestMessageExecute processor, Message msg) {
			super();
			this.executor = processor;
			this.msg = msg;
		}

		public void run(){
			final Message rspmsg = executor.execute(msg);
			//如果是需要响应的请求，发送响应消息
			if( null != rspmsg && MessageType.INOUT == msg.getType() ){
				logger.debug("发送响应消息{}到响应发送队列{}", rspmsg, rspSendDestination);
				jmsTemplate.send(rspSendDestination, new MessageCreator() {
		        	@Override
		            public javax.jms.Message createMessage(Session session) throws JMSException {
		                return session.createObjectMessage(rspmsg);
		            }
		        });
			}
		}
	}
}
