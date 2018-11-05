/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月23日 上午11:54:42
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv.fileprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 侦听jsm消息队列，对下载的不需要处理的其他文件发送其文件删除信息到jms队列中
 * 
 * @author yucy
 *
 */
public class OtherFileProcessor extends AbstractMsgListener {
	private static final Logger logger = LoggerFactory.getLogger(OtherFileProcessor.class);

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.bigdata.wififilerecv.fileprocess.AbstractMsgListener#doProcess(java.lang.String)
	 */
	@Override
	void doProcess(String filename) {
		logger.debug("对文件{}什么也不做。", filename);
	}

}
