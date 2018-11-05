/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月27日 下午6:30:24
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive;

import java.io.File;

/**
 * 文件分类器，将不同的文件进行分类，如按照类型记录到不同的消息队列里等待实际的解析处理
 * @author yucy
 *
 */
public interface Classfier {

	/**
	 * 对文件进行分类处理
	 * @param file
	 * @return 分类成功返回true；分类未成功（如不是支持的文件）返回false
	 */
    boolean classfy(File file);
}
