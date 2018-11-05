/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 下午4:09:57
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import org.springframework.jms.core.JmsTemplate;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * @author yucy
 *
 */
public interface RequestMessageExecute {

	Message execute(Message msg);
	boolean isSupport(Message msg);
}
