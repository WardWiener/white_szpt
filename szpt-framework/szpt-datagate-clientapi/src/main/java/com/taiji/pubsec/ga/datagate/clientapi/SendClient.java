/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月27日 下午1:40:02
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.io.Serializable;

/**
 * @author yucy
 *
 */
public interface SendClient {

	void asyncSendMessage(Serializable[] objs, Callback cbobj, long timeout);
	
	Serializable[] syncSendMessage(Serializable[] objs) throws Exception;
}
