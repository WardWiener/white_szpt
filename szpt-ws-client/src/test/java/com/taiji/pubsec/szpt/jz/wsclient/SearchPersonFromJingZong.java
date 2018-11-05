package com.taiji.pubsec.szpt.jz.wsclient;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:applicationContext-datasync.xml"})
@Transactional(rollbackFor=Exception.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class SearchPersonFromJingZong implements ApplicationContextAware{
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.setApplicationContextManually(applicationContext);
	}
	
	@Test
	public void testGetPersonInfoFromJingZingongByIdNumber() throws Exception {
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.add("520103196411130031");
		String result = WebServiceClient.getDateFromWs("http://10.160.25.92:9001/basic/services/standardSinoService?wsdl", 
				"queryIdentifiNumberService", "http://sinoService.sjfwpt.jzpt.sinobest.cn/", parameterValue);
		System.out.println(result);
	}

}
