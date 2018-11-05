/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年7月23日 上午10:58:22
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
 * 对目前没有处理的zip文件进行分类处理，并将分类成功的文件名称放入jms队列中
 * 
 * @author yucy
 *
 */
public class OtherFileClassfier extends AbstractZipClassfier {
	private static final Logger logger = LoggerFactory.getLogger(OtherFileClassfier.class);

	private Destination otherFileMsgDestination;
	
    @Override
    protected boolean isSurpport(File file) {
        if( !super.isSurpport(file)){
            return false;
        }
        
        return true;
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
        jmsTemplate.send(otherFileMsgDestination, new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(filename);
            }
        });
        
        return true;
	}

	public void setOtherFileMsgDestination(Destination otherFileMsgDestination) {
		this.otherFileMsgDestination = otherFileMsgDestination;
	}

}
