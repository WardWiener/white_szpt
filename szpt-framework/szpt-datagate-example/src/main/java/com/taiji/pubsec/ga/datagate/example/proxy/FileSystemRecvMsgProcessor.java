/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年6月30日 上午10:07:05
 */
package com.taiji.pubsec.ga.datagate.example.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.ga.datagate.Message;
import com.taiji.pubsec.ga.datagate.proxy.AbstractReceiveMsgProcessor;

/**
 * @author yucy
 *
 */
public class FileSystemRecvMsgProcessor extends AbstractReceiveMsgProcessor {
	private static final Logger logger = LoggerFactory.getLogger(FileSystemRecvMsgProcessor.class);
	
	private String reqRecvPath;
	private String rspRecvPath;
	
	public void scan(){
		scanReqRecv();
		scanRspRecv();
	}
	
	public void scanReqRecv() {
        logger.trace("开始扫描请求接收消息目录{}...", reqRecvPath);
        List<File> files = readfiles(reqRecvPath);
        for(File file : files){
            Message msg = parsefile(file);
            if(null == msg){
            	logger.error("从文件{}中解析消息失败。", file.getName());
            }else{
            	sendtoReqRecvQueue(msg);
            }
            logger.debug("删除请求接收消息文件{}。", file.getName());
        	file.delete();
        }
        logger.trace("扫描完成。");
    }

	public void scanRspRecv() {
        logger.trace("开始扫描响应接收消息目录{}...", rspRecvPath);
        List<File> files = readfiles(rspRecvPath);
        for(File file : files){
            Message msg = parsefile(file);
            if(null == msg){
            	logger.error("从文件{}中解析消息失败。", file.getName());
            }else{
            	sendtoRspRecvQueue(msg);
            }
        	logger.debug("删除响应接收消息文件{}。", file.getName());
        	file.delete();
        }
        logger.trace("扫描完成。");
    }

	private List<File> readfiles(String path) {
        File dir = new File(path);
        List<File> files = new ArrayList<File>();
        if (!(dir.exists() && dir.isDirectory())) {
        	logger.error("目录({})不存在或者不是一个目录。", dir.getAbsolutePath());
            return files;
        }

        for (File f : dir.listFiles()) {
            if ( f.isFile() && f.getName().endsWith(".obj") ) {
            	logger.debug("扫描到消息文件：{}", f.getAbsolutePath());
                files.add(f);
            }
        }
        logger.trace("扫描到文件数目：" + files.size());

        return files;
    }
	
	private Message parsefile(File f){
		//反序列化对象
        ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f.getAbsolutePath()));
			Message msg = (Message)in.readObject();
			logger.debug("从文件{}读取到消息对象{}", f.getName(), msg);
			return msg;
		} catch (FileNotFoundException e) {
			logger.error("文件没有找到", e);
		} catch (IOException e) {
			logger.error("读取文件失败", e);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
		}finally{
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("文件输入流关闭失败", e);
				}
			}
		}
        return null;
	}

	public void setReqRecvPath(String reqRecvPath) {
		this.reqRecvPath = reqRecvPath;
	}

	public void setRspRecvPath(String rspRecvPath) {
		this.rspRecvPath = rspRecvPath;
	}

}
