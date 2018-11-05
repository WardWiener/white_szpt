/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月23日 下午1:56:30
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.filereceive;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yucy
 *
 */
public class FtpClientHolder {
	private static final Logger logger = LoggerFactory.getLogger(FtpClientHolder.class);

	// FTP服务器地址
	private String server;
	private Integer port;
	// 用户名
	private String username;
	// 密码
	private String password;
	
	private FTPClient ftp;
	
	private int dataTimeout =  300000;//5分钟

	
	public synchronized FTPClient get(){
		if (null != ftp && ftp.isConnected()) {
			return ftp;
		}
		return getNew();
	}
	
	private synchronized FTPClient getNew(){
		try {
			if(null!=ftp){
				ftp.disconnect();
			}
			logger.debug("获得一个新的FTPClient对象");
			ftp = new FTPClient();
			logger.debug("连接服务器{}...", server);
			ftp.setDataTimeout(dataTimeout);
			ftp.setConnectTimeout(dataTimeout);
			ftp.connect(server, port);
			int reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				logger.error("FTP 服务器拒绝连接。");
				ftp = null;
				return null;
			}
			
			if (!ftp.login(username, password)) {
				logger.debug("登录{}失败。", server);
				ftp = null;
				return null;
			}
			logger.debug("响应：{}，服务器状态：{}", ftp.getReplyString(), ftp.getStatus());
			logger.debug("localport：{}", ftp.getLocalPort());
			return ftp;
		} catch (SocketException e) {
			logger.error("SocketException", e);
			ftp = null;
		} catch (IOException e) {
			logger.error("IOException", e);
			ftp = null;
		}

		return null;
	}
	
	public void destroy(){
		try {
			logger.info("关闭FTP连接。");
			ftp.disconnect();
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			ftp = null;
		}
	}
	
	public void setServer(String server) {
		this.server = server;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	public long getDataTimeout() {
		return dataTimeout;
	}

	public void setDataTimeout(int dataTimeout) {
		this.dataTimeout = dataTimeout;
	}

	
}
