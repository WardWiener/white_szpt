/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月2日 下午8:59:12
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yucy
 *
 */
public class AsyncTimeoutManagerImpl implements AsyncTimeoutManager {
	private static final Logger logger = LoggerFactory.getLogger(AsyncTimeoutManagerImpl.class);
	
	private MessageStage msgStage;
	private Timer timer = new Timer();

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.AsyncTimeoutManager#put(java.lang.String, long)
	 */
	@Override
	public void put(final String msgid, long timeout) {
		timer.schedule( new TimerTask() {
			
			@Override
			public void run() {
				if( null != msgStage.get(msgid) ){
					logger.debug("异步请求消息{}超时！", msgid);
					msgStage.remove(msgid);
				}
			}
		}, timeout);
	}

	public void setMsgStage(MessageStage msgStage) {
		this.msgStage = msgStage;
	}

}
