/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 上午9:48:49
 */
package com.taiji.pubsec.ga.datagate.clientapi;

/**
 * 请求消息记录器，用于记录同步请求消息或者异步回调消息 ，以便返回响应消息给同步请求或者调用异步回调。
 * 
 * @author yucy
 *
 */
interface MessageStage {

	void put(String msgid, Object msgAppendix);
	Object get(String msgid);
	void remove(String msgid);
}
