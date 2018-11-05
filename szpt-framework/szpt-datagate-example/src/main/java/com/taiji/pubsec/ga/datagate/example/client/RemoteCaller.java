/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月30日 下午2:23:39
 */
package com.taiji.pubsec.ga.datagate.example.client;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.ga.datagate.clientapi.Callback;
import com.taiji.pubsec.ga.datagate.clientapi.SendClient;
import com.taiji.pubsec.ga.datagate.clientapi.SendClientFactory;

/**
 * @author yucy
 *
 */
public class RemoteCaller {
	private static final Logger logger = LoggerFactory.getLogger(RemoteCaller.class);
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	SendClient sc = SendClientFactory.get();
	Callback cb = new RogerthatCb();
	Random r = new Random();

	public void call(){
		if(r.nextBoolean()){
			callAsyn();
		}else{
			callSync();
		}
	}

	public void callAsyn(){
		Serializable[] objs = new Serializable[2];
		objs[0] = HelloMsgExecutor.TAG_HELLO;
		objs[1] = String.valueOf( r.nextInt() );
		logger.info("发起异步调用{}", objs);
		sc.asyncSendMessage(objs, cb, 5000);
	}

	public void callSync(){
		final Serializable[] objs = new Serializable[2];
		objs[0] = HelloMsgExecutor.TAG_HELLO;
		objs[1] = String.valueOf( r.nextInt() );
		
		cachedThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					logger.info("发起同步调用{}", objs);
				Serializable[] result = sc.syncSendMessage(objs);
				logger.info("同步调用返回：{}", result);
				} catch (Exception e) {
					logger.error("同步调用失败", e);
				}
			}
		});
		
	}
	
	private class RogerthatCb implements Callback{

		@Override
		public void call(Serializable[] objs) {
			logger.info("Roger that : {}", objs);
		}
		
	}
}
