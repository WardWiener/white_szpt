package com.taiji.pubsec.szpt.jz.wsclient;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext-datasync.xml"})
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:applicationContext-datasync.xml"})
public class ReceiveDataServiceTest implements ApplicationContextAware{
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.setApplicationContextManually(applicationContext);
	}
	
	@Test
	public void testReceiveDataService() throws Exception {
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.add("testbatchno");
		parameterValue.add("testidentify");
		parameterValue.add("testdatajson");
		String result = WebServiceClient.getDateFromWs("http://10.161.172.189:9003/ajgl-web-all/webService/receiveDataFromBusService?wsdl", 
				"receiveDataFromBusService", "http://service.datasynchronizate.pubsec.taiji.com/", parameterValue);
		System.out.println(result);
	}

}
