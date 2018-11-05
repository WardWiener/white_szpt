package com.taiji.pubsec.szpt.service;

import java.util.List;

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
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class SzptCommonServiceTestCase extends TestBase{
	
	@Resource
	private SzptCommonService szptCommonService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptCommon/findAllCommunity-setup.xml")
	public void testFindAllCommunity() {
		List<Community> communityLst = szptCommonService.findAllCommunity();
		Assert.assertEquals(4, communityLst.size());
		Assert.assertEquals("001", communityLst.get(0).getId());
		Assert.assertEquals("sq001", communityLst.get(0).getCode());
		Assert.assertEquals("小区1", communityLst.get(0).getName());
		Assert.assertEquals("002", communityLst.get(1).getId());
		Assert.assertEquals("小区2", communityLst.get(1).getName());
	}
	
	
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptCommon/findCommunityByCode-setup.xml")
	public void testFindCommunityByCode() {
		Community community = szptCommonService.findCommunityByCode("sq001");
		Assert.assertEquals("001", community.getId());
		Assert.assertEquals("sq001", community.getCode());
		Assert.assertEquals("小区1", community.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptCommon/findCommunityById-setup.xml")
	public void testFindCommunityById() {
		Community community = szptCommonService.findCommunityById("001");
		Assert.assertEquals("001", community.getId());
		Assert.assertEquals("sq001", community.getCode());
		Assert.assertEquals("小区1", community.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptCommon/findCommunityByPcsId-setup.xml")
	public void testFindCommunityByPcsId() {
		List<Community> communityLst = szptCommonService.findCommunityByPcsId("pcsbm1");
		Assert.assertEquals(2, communityLst.size());
		Assert.assertEquals("001", communityLst.get(0).getId());
		Assert.assertEquals("sq001", communityLst.get(0).getCode());
		Assert.assertEquals("小区1", communityLst.get(0).getName());
		Assert.assertEquals("002", communityLst.get(1).getId());
		Assert.assertEquals("小区2", communityLst.get(1).getName());
	}
	
}
