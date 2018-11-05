/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月27日 下午7:01:58
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZIP文件的分类器
 * @author yucy
 *
 */
public abstract class AbstractZipClassfier extends AbstractClassfier {
    private static final Logger logger = LoggerFactory.getLogger(AbstractZipClassfier.class);
    
    protected boolean isSurpport(File file){
        String filename = file.getName();
        if(filename.endsWith(".zip")){
            return true;
        }
        logger.debug(filename + "不是支持的Zip格式。");
        return false;
    }

    /**
     * 获得Zip文件中所包含的文件的文件名
     * @param zipfile
     * @return 
     * @throws ZipException
     * @throws IOException
     */
    protected List<String> getZipInfo(File zipfile) throws ZipException, IOException{
        List<String> zipinfo = new ArrayList<String>();
        
        ZipFile zip = new ZipFile(zipfile);
        
        Enumeration<? extends ZipEntry> entries = zip.entries();
        
        while(entries.hasMoreElements())  
        {  
            ZipEntry entry = entries.nextElement();
            zipinfo.add(entry.getName());
        }
        zip.close();
        
        return zipinfo;
    }
}
