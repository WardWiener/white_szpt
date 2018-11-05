/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 上午10:19:01
 */
package com.taiji.pubsec.ga.datagate;

import java.io.Serializable;

/**
 * @author yucy
 *
 */
public interface Message extends Serializable {

	String getId();
	MessageType getType();
	Serializable[] getBody();
	Boolean isRequest();
}
