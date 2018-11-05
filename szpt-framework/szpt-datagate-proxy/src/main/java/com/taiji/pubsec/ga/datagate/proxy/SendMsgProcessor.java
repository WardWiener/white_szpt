/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月29日 下午1:34:32
 */
package com.taiji.pubsec.ga.datagate.proxy;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * 发送消息（请求消息发送、响应消息发送）处理器，用于将JMS中消息发送到跨网交换设备。
 * 可配置两个侦听器对象，
 * 一个用于侦听请求消息发送队列，将请求消息发送到跨网交换设备；
 * 另一个用于侦听响应消息发送队列，将响应消息发送到跨网交换设备。
 * 
 * @author yucy
 *
 */
public class SendMsgProcessor implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(SendMsgProcessor.class);
	
	private String name;
	private List<SenderDevice> senderDevices;

	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(javax.jms.Message msg) {
		if( !(msg instanceof ObjectMessage) ){
			return;
		}

		Serializable msgobj = null;
		try {
			msgobj = ((ObjectMessage)msg).getObject();
		} catch (JMSException e) {
			logger.error("", e);
		}
		
		if( !(msgobj instanceof Message) ){
			return;
		}
		Message reqmsg = (Message)msgobj;
		logger.debug("从{}接收到消息{}", name, reqmsg);
		
		for(SenderDevice senderDevice : senderDevices){
			if(senderDevice.isSupport(reqmsg)){
				cachedThreadPool.execute( new SenderRunner(senderDevice, reqmsg) );
				break;
			}
		}
		
	}

	public void setSenderDevices(List<SenderDevice> senderDevices) {
		this.senderDevices = senderDevices;
	}

	public void setName(String name) {
		this.name = name;
	}

	private class SenderRunner implements Runnable{
		SenderDevice sender;
		Message msg;
		
		protected SenderRunner(SenderDevice senderDevice, Message msg) {
			super();
			this.sender = senderDevice;
			this.msg = msg;
		}
		
		public void run(){
			logger.debug("通过跨网设备发送请求消息{}", msg);
			sender.send(msg);
		}
	}
}
