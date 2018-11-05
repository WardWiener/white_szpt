/**
 * Copyright 2015 Taiji
 * 
 * All right reserved.
 * 
 * Created on 2015年11月2日 上午11:28:38
 */
package com.taiji.pubsec.szpt.dpp.wififilerecv.fileprocess;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.taiji.pubsec.szpt.kafka.KafkaProducer;

/**
 * @author yucy
 *
 */
public abstract class AbstractMsgListener implements MessageListener {
	private static final Logger logger = LoggerFactory.getLogger(AbstractMsgListener.class);
	
	protected Destination filecleanDestination;

	protected JmsTemplate jmsTemplate;
	
	protected KafkaProducer kafkaProducer;

	//本地工作目录
	protected String workfolder;
	
	/* (non-Javadoc)
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void onMessage(Message msg) {
        try {
            if (msg instanceof TextMessage) {
                logger.debug("接收到消息：" + ((TextMessage) msg).getText());
                final String filename = ((TextMessage) msg).getText();
                
                //进行消息的处理
                doProcess(filename);
                
                //发布消息给文件清除器
                jmsTemplate.send(filecleanDestination, new MessageCreator() {
                	@Override
                    public Message createMessage(Session session) throws JMSException {
                        logger.debug("发送清除文件( " + filename + " )消息.");
                        return session.createTextMessage(filename);
                    }
                });
            }
            msg.acknowledge();
        } catch (JMSException e) {
            logger.error("处理消息错误", e);
        }
    }
    
    abstract void doProcess(String filename);

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

    public void setFilecleanDestination(Destination filecleanDestination) {
        this.filecleanDestination = filecleanDestination;
    }

	public void setWorkfolder(String workfolder) {
		this.workfolder = workfolder;
	}

	public void setKafkaProducer(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

}
