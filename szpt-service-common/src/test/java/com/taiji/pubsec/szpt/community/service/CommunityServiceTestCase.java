package com.taiji.pubsec.szpt.community.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.service.ICommunityService;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CommunityServiceTestCase extends TestBase{

	@Resource
	private ICommunityService communityService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@DatabaseSetup("classpath:dataset/findCommunity-setup.xml")
	public void testIsNeighbourhood() {
		Assert.assertTrue(communityService.isNeighbourhood("c8299809-5c2e-4d76-a29c-5a354ee0afd7", "c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1"));
		Assert.assertFalse(communityService.isNeighbourhood("c8299809-5c2e-4d76-a29c-5a354ee0afd7", "3285bb5a-2336-4712-a0eb-cfc3c2dac77a"));
	}
	
}
