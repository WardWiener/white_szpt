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
import com.taiji.pubsec.persistence.dao.Dao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:applicationContext-datasync.xml"})
@Transactional(rollbackFor=Exception.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class DataSyncTest implements ApplicationContextAware{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
//	private JingZongBasicDataSynchronizationSearchService jingZongBasicDataSynchronizationSearchService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.setApplicationContextManually(applicationContext);
	}
	
	
	@Test
	public void testUnitDataSyns() throws Exception {
//		jingZongBasicDataSynchronizationSearchService.organizationDataSync();
		
	}
	
	@Test
	public void testPersonDataSync() throws Exception {
		
		System.out.println("org--data--sync--start");
//		jingZongBasicDataSynchronizationSearchService.organizationDataSync();
		System.out.println("org--data--sync--end");
		
		System.out.println("user&account--data--sync--start");
//		jingZongBasicDataSynchronizationSearchService.userDataSync();
		System.out.println("user&account--data--sync--end");
		
	}
	
	@Test
	public void testUserAuthorization() throws Exception {
		//葛伟：52011119750109063X     角色编码：1301   角色授权
//		jingZongBasicDataSynchronizationSearchService.userAuthorization("52011119750109063X", "1302");
	}
	
	@Test
	public void testRevokeUserAuthorization() throws Exception {
		//葛伟：52011119750109063X     角色编码：1301    取消授权
//		jingZongBasicDataSynchronizationSearchService.revokeUserAuthorization("52011119750109063X", "1302");
	}
}
