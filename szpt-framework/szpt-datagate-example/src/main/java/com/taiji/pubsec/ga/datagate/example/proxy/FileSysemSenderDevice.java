/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月30日 上午9:46:47
 */
package com.taiji.pubsec.ga.datagate.example.proxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.ga.datagate.Message;
import com.taiji.pubsec.ga.datagate.proxy.SenderDevice;

/**
 * @author yucy
 *
 */
public class FileSysemSenderDevice implements SenderDevice {
	private static final Logger logger = LoggerFactory.getLogger(FileSysemSenderDevice.class);

	private String reqSendPath;
	private String rspSendPath;

	@Override
	public boolean isSupport(Message msg) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.taiji.pubsec.ga.datagate.proxy.SenderDevice#send(com.taiji.pubsec.ga.datagate.Message)
	 */
	@Override
	public void send(Message msg) {
		String filename = null;
		if(msg.isRequest()){
			filename = reqSendPath + File.separator + msg.getId() + ".obj";
		}else{
			filename = rspSendPath + File.separator + msg.getId() + ".obj";
		}
		File file = new File(filename);
		if(file.exists()){
			logger.error("消息文件{}已经存在，准备删除！", filename);
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			logger.error("创建消息文件" + filename + "失败。", e);
		}
		
		logger.debug("将消息{}写入文件{}", msg, file);
		msgToFile(msg, file);
	}
	
	private void msgToFile(Message msg, File file){
		//序列化对象
        ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(msg);
		} catch (FileNotFoundException e) {
			logger.error("输出文件没有找到", e);
		} catch (IOException e) {
			logger.error("写文件IO错误，删除文件！", e);
			file.delete();
		}finally{
			if(null != out)
				try {
					out.close();
				} catch (IOException e) {
					logger.error("文件输出流关闭失败", e);
				}
		}
	}

	public void setReqSendPath(String reqSendPath) {
		this.reqSendPath = reqSendPath;
	}

	public void setRspSendPath(String rspSendPath) {
		this.rspSendPath = rspSendPath;
	}

}
