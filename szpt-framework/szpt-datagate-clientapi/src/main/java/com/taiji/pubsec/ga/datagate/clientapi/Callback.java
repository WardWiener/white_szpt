/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月27日 下午1:41:20
 */
package com.taiji.pubsec.ga.datagate.clientapi;

import java.io.Serializable;

import com.taiji.pubsec.ga.datagate.Message;

/**
 * 异步调用时的回调接口
 * 
 * @author yucy
 *
 */
public interface Callback {

	void call(Serializable[] objs);
}
