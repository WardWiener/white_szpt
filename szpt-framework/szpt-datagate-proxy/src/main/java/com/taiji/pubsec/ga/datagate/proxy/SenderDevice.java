/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月29日 下午1:43:27
 */
package com.taiji.pubsec.ga.datagate.proxy;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * 数据交换设备接口，将请求消息发送到目的地。
 * 如通过网闸将数据跨网传递
 * 
 * @author yucy
 *
 */
public interface SenderDevice {

	boolean isSupport(Message msg);
	void send(Message msg);
}
