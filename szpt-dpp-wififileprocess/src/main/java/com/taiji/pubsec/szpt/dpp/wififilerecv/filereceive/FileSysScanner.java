/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月26日 下午3:01:54
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.filereceive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于本地文件系统的文件扫描器实现
 * 
 * @author yucy
 * 
 */
public class FileSysScanner extends AbstractFileScanner {
    private static final Logger logger = LoggerFactory.getLogger(FileScanner.class);

    private String path;

    public void scan() {
        logger.debug("开始扫描...");
        List<File> files = read();
        for(File file : files){
            //TODO 查找是否已经对该文件进行过分类处理，如果没有则分类
            
            doClassfy(file);
            
            //TODO 对分类完成的文件记录
            
        }
        logger.debug("扫描完成。");
    }
    
    private List<File> read() {
    	logger.debug("准备读取目录：" + path);
        File dir = new File(path);
        List<File> files = new ArrayList<File>();
        if (!(dir.exists() && dir.isDirectory())) {
        	logger.debug("目录不存在或者不是一个目录。");
            return files;
        }

        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                files.add(f);
            }
        }
        logger.debug("扫描到文件数目：" + files.size());

        return files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
