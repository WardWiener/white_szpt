/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月29日 下午2:43:22
 */
package com.taiji.pubsec.ga.datagate.proxy;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * 接收消息（请求消息接收、响应消息接收）处理器，用于将跨网交换设备传递过来的消息发送到JMS。
 * 子类可根据网络交换设备的通信方式来实现将数据从设备中取出，并构造成本框架的消息格式，调用抽象类中的发送方法发送消息到JMS中
 * 
 * @author yucy
 *
 */
public abstract class AbstractReceiveMsgProcessor {
	private static final Logger logger = LoggerFactory.getLogger(AbstractReceiveMsgProcessor.class);

	//请求消息接收队列
	private Destination reqRecvDestination;
	//响应消息接收队列
	private Destination rspRecvDestination;
	
	private JmsTemplate jmsTemplate;
	
	/**
	 * 将网络交换设备的数据发送到请求接收队列中
	 * @param msg
	 */
	protected void sendtoReqRecvQueue(Message msg){
		logger.debug("发送消息{}到请求接收队列{}.", msg, reqRecvDestination);
		doSend(msg, reqRecvDestination);
	}
	
	/**
	 * 将网络交换设备的数据发送到响应接收队列中
	 * @param msg
	 */
	protected void sendtoRspRecvQueue(Message msg){
		logger.debug("发送消息{}到响应接收队列{}.", msg, rspRecvDestination);
		doSend(msg, rspRecvDestination);
	}
	
	private void doSend(final Message msg, final Destination dest){
		jmsTemplate.send(dest, new MessageCreator() {
        	@Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(msg);
            }
        });
	}
	
	public void setReqRecvDestination(Destination reqRecvDestination) {
		this.reqRecvDestination = reqRecvDestination;
	}
	public void setRspRecvDestination(Destination rspRecvDestination) {
		this.rspRecvDestination = rspRecvDestination;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
