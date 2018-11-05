/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 下午3:40:56
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yucy
 *
 */
class DefaultMessageStage implements MessageStage {

	private static Map<String, Object> stage = new HashMap<>();
	
	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.MessageStage#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String msgid, Object msgAppendix) {
		stage.put(msgid, msgAppendix);
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.MessageStage#get(java.lang.String)
	 */
	@Override
	public Object get(String msgid) {
		return stage.get(msgid);
	}

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.MessageStage#remove(java.lang.String)
	 */
	@Override
	public void remove(String msgid) {
		stage.remove(msgid);
	}

}
