/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月27日 上午11:12:17
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * 同步请求时，请求消息被放入此类的一个实例，并用于同步请求使用的对象锁；
 * 当响应消息到达时，被放入此类相应的实例对象，以便SendClient获取并返回给客户端。
 * 
 * @author yucy
 *
 */
class SyncMessageHolder {

	private Message reqmsg;
	private Message rspmsg;

	public SyncMessageHolder(Message reqmsg) {
		super();
		this.reqmsg = reqmsg;
	}

	public Message getRspmsg() {
		return rspmsg;
	}

	public void setRspmsg(Message rspmsg) {
		this.rspmsg = rspmsg;
	}
	
}
