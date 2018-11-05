/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月28日 下午3:33:21
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.util.UUID;

/**
 * @author yucy
 *
 */
public class DefaultIdGenerator implements IdGenerate {

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.clientapi.IdGenerate#get()
	 */
	@Override
	public String get() {
		return UUID.randomUUID().toString().replace("-","");
	}

}
