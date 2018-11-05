/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月23日 上午10:52:18
 */
package com.taiji.pubsec.szpt.bigdata.wififilerecv.classfier;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;

import com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive.AbstractZipClassfier;

/**
 * 对设备终端文件进行分类，并将分类成功的文件名称放入jms队列中
 * 
 * @author yucy
 *
 */
public class EquipmentinfoFileClassfier extends AbstractZipClassfier {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentinfoFileClassfier.class);

	public static final String SURPPORTED_FILENAME = "WA_BASIC_FJ_0003_1";
	
	private Destination equipmentFileMsgDestination;
	
    @Override
    protected boolean isSurpport(File file) {
        if( !super.isSurpport(file)){
            return false;
        }
        
        try {
            List<String> zipinfo = getZipInfo(file);
            for(String zipfilename : zipinfo){
                if(zipfilename.contains(SURPPORTED_FILENAME)){
                    return true;
                }
            }
            logger.trace("{}不是支持的文件，因为zip里面的所有文件中没有文件名包含{}的文件。", file.getName(), SURPPORTED_FILENAME);
        } catch (ZipException e) {
        	logger.error("zip文件例外", e);
            return false;
        } catch (IOException e) {
            logger.error("文件IO错误", e);
            return false;
        }
        
        return false;
    }

	/* (non-Javadoc)
	 * @see com.taiji.pubsec.szpt.bigdata.wififilerecv.filereceive.Classfier#classfy(java.io.File)
	 */
	@Override
	public boolean classfy(File file) {
		if(!isSurpport(file)){
            return false;
        }
        
        final String filename = file.getName();
        logger.debug("对文件[ " + filename + " ]进行分类处理。");
        
        // 发送文件处理指令信息到消息队列
        jmsTemplate.send(equipmentFileMsgDestination, new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(filename);
            }
        });
        
        return true;

	}

	public void setEquipmentFileMsgDestination(
			Destination equipmentFileMsgDestination) {
		this.equipmentFileMsgDestination = equipmentFileMsgDestination;
	}

}
