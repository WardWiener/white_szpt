/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月2日 下午8:32:27
 */
package com.taiji.pubsec.ga.datagate.clientapi;

/**
 * 异步调用超时管理器接口
 * 
 * @author yucy
 *
 */
public interface AsyncTimeoutManager {

	void put(String msgid, long timeout);
}
