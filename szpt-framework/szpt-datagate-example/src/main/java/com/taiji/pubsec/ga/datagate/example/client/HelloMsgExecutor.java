/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月30日 下午2:40:48
 */
package com.taiji.pubsec.ga.datagate.example.client;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.ga.datagate.DefaultMessageImpl;
import com.taiji.pubsec.ga.datagate.Message;
import com.taiji.pubsec.ga.datagate.MessageType;
import com.taiji.pubsec.ga.datagate.clientapi.RequestMessageExecute;

/**
 * @author yucy
 *
 */
public class HelloMsgExecutor implements RequestMessageExecute {
	private static final Logger logger = LoggerFactory.getLogger(HelloMsgExecutor.class);
	
	public final static String TAG_HELLO = "APPX:HELLO";
	
	private BusinessService businessService;

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.RequestMessageExecute#isSupport(com.taiji.pubsec.ga.datagate.Message)
	 */
	@Override
	public boolean isSupport(Message msg) {
		Serializable[] bodys = msg.getBody();
		if(bodys.length < 2)
			return false;
		if( !(bodys[0] instanceof String) )
			return false;
		String tag = (String)bodys[0];
		if( !TAG_HELLO.equals(tag) )
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.AbstractRequestMessageExecutor#doExecute(com.taiji.pubsec.ga.datagate.Message)
	 */
	@Override
	public Message execute(Message msg) {
		String name = (String)msg.getBody()[1];
		logger.debug("开始调用com.taiji.pubsec.ga.datagate.example.client.BusinessService.hello(String)处理请求消息{}", msg);
		String r = businessService.hello(name);
		if( MessageType.ONLYIN == msg.getType()){
			logger.debug("请求消息{}不需要响应，执行结束。", msg);
			return null;
		}
		
		Serializable[] body = new Serializable[1];
		body[0] = r;
		Message rsp = new DefaultMessageImpl(msg.getId(), MessageType.INOUT, body, false);
		logger.debug("生成响应消息{}.", rsp);
		return rsp;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

}
