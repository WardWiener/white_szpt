package com.taiji.pubsec.szpt.jz.wsclient;

import javax.annotation.Resource;

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
import com.taiji.pubsec.szpt.jz.wsclient.authority.AuthorityServiceClient;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional(rollbackFor=Exception.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class AuthorizationServiceClientTestCase implements ApplicationContextAware{
	
	@Resource
	private AuthorityServiceClient authServiceClient;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.setApplicationContextManually(applicationContext);
	}
	
	
	@Test
	public void testAuthorizeUser() throws Exception {
		authServiceClient.authorizeUser("", "");
		
	}
	
	@Test
	public void testRevokeAuthorizeUser() throws Exception {
		authServiceClient.revokeUserAuthorization("", "");
		
	}
	
	@Test
	public void testQueryAuthorizedUser() throws Exception {
		authServiceClient.queryAuthorizedUsers();
		
	}
	
}
