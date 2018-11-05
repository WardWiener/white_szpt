/**
 * Copyright 2016 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2016年6月27日 上午11:06:17
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * 响应消息接收处理器：侦听响应消息接收队列，将响应消息返回同步等待的请求方法或者调用异步请求的回调方法
 * 
 * @author yucy
 *
 */
public class ResponseMessageProcessor implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(ResponseMessageProcessor.class);
	
	private MessageStage msgStage;

	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

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
			logger.error("响应消息不是{}类型", Message.class.toString());
			return;
		}
		
		Message rspmsg = (Message)msgobj;
		logger.debug("接收到响应消息{}。", rspmsg);
		
		Object msgAppendix = msgStage.get(rspmsg.getId());
		if( null == msgAppendix ){
			logger.error("不能找到响应消息{}的请求记录。", rspmsg);
		}
		
		if(msgAppendix instanceof SyncMessageHolder){ //同步消息
			logger.trace("响应消息（{}）对应的请求是同步请求。", rspmsg);
			cachedThreadPool.execute( new SyncRspRunner(((SyncMessageHolder)msgAppendix), rspmsg) );
		}else if(msgAppendix instanceof Callback){ //异步消息
			logger.trace("响应消息（{}）对应的请求是异步请求。", rspmsg);
			cachedThreadPool.execute( new AsynRspRunner((Callback)msgAppendix, rspmsg) );
		}else{
			logger.error("不认识的请求消息{}关联对象类型{}", rspmsg.getId(), msgAppendix.getClass().toString());
			throw new RuntimeException("不认识的请求消息关联对象类型.");
		}
	}
	
	public void setMsgStage(MessageStage msgStage) {
		this.msgStage = msgStage;
	}
	
	private class SyncRspRunner implements Runnable{
		private SyncMessageHolder syncMsgHolder;
		private Message msg;

		protected SyncRspRunner(SyncMessageHolder syncMsgHolder, Message msg) {
			super();
			this.syncMsgHolder = syncMsgHolder;
			this.msg = msg;
		}
		
		public void run(){
			synchronized (syncMsgHolder) {
				logger.trace("为同步请求设置响应消息{}", msg);
				syncMsgHolder.setRspmsg(msg);
				syncMsgHolder.notifyAll();
			}
		}
	}

	private class AsynRspRunner implements Runnable{
		private Callback cbobj;
		private Message msg;

		protected AsynRspRunner(Callback cbobj, Message msg) {
			super();
			this.cbobj = cbobj;
			this.msg = msg;
		}
		
		public void run(){
			logger.debug("对消息{}调用回调...", msg);
			this.cbobj.call(msg.getBody());
			logger.debug("回调完成，将消息({})记录移除。", msg);
			msgStage.remove(msg.getId());
		}
	}
}
