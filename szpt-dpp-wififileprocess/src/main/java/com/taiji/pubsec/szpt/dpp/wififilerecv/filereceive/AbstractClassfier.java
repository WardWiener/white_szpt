/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年10月30日 下午1:49:24
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.filereceive;

import org.springframework.jms.core.JmsTemplate;

/**
 * 所有分类器的抽象类，封装了spring的JMSTemplate服务
 * @author yucy
 *
 */
public abstract class AbstractClassfier implements Classfier {

    protected JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
}
