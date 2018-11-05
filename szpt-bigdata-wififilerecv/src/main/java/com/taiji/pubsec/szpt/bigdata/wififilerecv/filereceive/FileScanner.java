/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月27日 下午5:36:42
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive;

/**
 * 文件扫描器，扫描文件系统（本地或者远程FTP等）的文件，并完成如下功能：
 * 1.对扫描目录中存在的没有处理过的文件进行初步的分类处理，分类后的文件消息被发送到不同的消息队列
 * 2.对已经分类处理过的文件记录，避免重复处理
 * @author yucy
 *
 */
public interface FileScanner {

    void scan();
}
