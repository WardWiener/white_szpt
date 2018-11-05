/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月29日 下午2:36:13
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.filereceive;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 侦听消息队列，删除已经处理完成的文件，并且从FTP上将文件移入bak目录。
 * 
 * @author yucy
 * 
 */
public class FileCleanListener implements MessageListener {
	private static final Logger logger = LoggerFactory
			.getLogger(FileCleanListener.class);

	private String ftpfolder;
	private String workfolder;
	private long timeout = 10000;
	private int retry = 3;
	private String operate;
	
	private FtpClientHolder ftpClientHolder;

	private String backupdir = "bak";
	
	private FTPClient ftpclient;
	
	private static final String OPERATE_DELETE = "delete";
	private static final String OPERATE_MOVE = "move";
	private static ExecutorService threadPool = Executors.newSingleThreadExecutor();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message msg) {
		if (!(msg instanceof TextMessage)) {
			return;
		}

		try {
			logger.debug("接收到消息：" + ((TextMessage) msg).getText());
			String filename = ((TextMessage) msg).getText();
			String filepath = workfolder + File.separator + FtpScanner.DOWNLOAD_DIR + File.separator + filename;
			File file = new File(filepath);
			if (!file.exists()) {
				logger.error("文件：" + filepath + "不存在。");
			} else if (file.isFile()) {
				try {
					while (!file.delete()) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					logger.debug("{}文件：" + filepath, operate);
					operateFileOnFTP(filename, operate);
					msg.acknowledge();
				} catch (SecurityException e) {
					logger.error("文件：" + filepath + "删除失败 ", e);
					// TODO 将该消息记录下来，以后通过其他方式处理（手工删除，或解决问题后再自动删除）
				}
			} else {
				logger.error(filepath + "不是一个文件。");
			}

		} catch (JMSException e) {
			logger.error("消息接收处理错误", e);
		}
	}
	
	private void operateFileOnFTP(String fname, String operate){
		for(int i=0; i<retry; i++){
			logger.trace("移动文件{}...第{}次", fname, i+1);
			ftpclient = ftpClientHolder.get();
			Boolean success = doOperate(fname, operate);
			if( success ){
				logger.trace("操作({})文件{}成功", operate, fname);
				return;
			}
		}
		logger.error("操作({})文件{}成功", operate, fname);
	}
	
	private Boolean doOperate(String fname, String operate){
		Future<Boolean> future = threadPool.submit( new OperateFileOnFTPCall(fname, operate) );
		
		try {
			return future.get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (ExecutionException e) {
			logger.error("ExecutionException", e);
		} catch (TimeoutException e) {
			//超时关闭ftp连接 释放文件和socket资源
			ftpClientHolder.destroy();
			logger.error("移动文件超时。", e);
		}
		
		return false;
	}

	public void setWorkfolder(String workfolder) {
		this.workfolder = workfolder;
	}

	public void setFtpClientHolder(FtpClientHolder ftpClientHolder) {
		this.ftpClientHolder = ftpClientHolder;
	}

	public void setFtpfolder(String ftpfolder) {
		this.ftpfolder = ftpfolder;
	}

	public void setBackupdir(String backupdir) {
		this.backupdir = backupdir;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	private class OperateFileOnFTPCall implements Callable<Boolean>{

		String fname;
		String operate;
		
		OperateFileOnFTPCall(String fname, String operate) {
			super();
			this.fname = fname;
			this.operate = operate;
		}

		@Override
		public Boolean call() throws Exception {
			String old = ftpfolder + File.separator + fname;
			String ne = backupdir + File.separator + fname;
			try {
				
				Boolean result = false;
				if(OPERATE_DELETE.equals(operate)){
					logger.trace("删除文件{}。", fname);
					result = ftpclient.deleteFile(fname); 
				}
				if(OPERATE_MOVE.equals(operate)){
					logger.trace("移动文件{}到FTP目录{}", fname, backupdir);
					result = ftpclient.rename(old, ne);
				}
				return result;
			} catch (IOException e) {
				ftpClientHolder.destroy();
				logger.error("在FTP上操作( " + operate + " )文件错误。", e);
			}
			return false;
		}
		
	}
}
