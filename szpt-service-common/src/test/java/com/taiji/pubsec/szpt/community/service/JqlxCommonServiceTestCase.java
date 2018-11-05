package com.taiji.pubsec.szpt.community.service;

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
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.service.JqlxCommonService;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class JqlxCommonServiceTestCase extends TestBase{

	@Resource
	private JqlxCommonService jqlxCommonService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByLevel-setup.xml")
	public void testFindJqlxsByLevel() {
		List<Jqlx> jqlxList= jqlxCommonService.findJqlxsByLevel(1);
		Assert.assertEquals(2, jqlxList.size());
		Assert.assertEquals("mc2", jqlxList.get(1).getName());		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByParentCode-setup.xml")
	public void testFindJqlxsByParentCode() {
		List<Jqlx> jqlxList = jqlxCommonService.findJqlxsByParentCode("bm03");
		Assert.assertEquals(2, jqlxList.size());
		Assert.assertEquals("mc3", jqlxList.get(1).getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByChildCode-setup.xml")
	public void testFindJqlxsByChildCode() {
		List<Jqlx> jqlxList = jqlxCommonService.findJqlxsByChildCode("bm01");
		Assert.assertEquals(1, jqlxList.size());
		Assert.assertEquals("mc2", jqlxList.get(0).getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByParentId-setup.xml")
	public void testFindJqlxsByParentId() {
		List<Jqlx> jqlxList = jqlxCommonService.findJqlxsByParentId("fid002");
		Assert.assertEquals(2, jqlxList.size());
		Assert.assertEquals("mc2", jqlxList.get(0).getName());
	}
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByParentId-setup.xml")
	public void testFindJqlxByName() {
		Jqlx jqlx = jqlxCommonService.findJqlxByName("mc2");
		Assert.assertEquals("2", jqlx.getId());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByParentId-setup.xml")
	public void testFindJqlxById() {
		Jqlx jqlx = jqlxCommonService.findJqlxById("1");
		Assert.assertEquals("mc1", jqlx.getName());
	}
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByParentId-setup.xml")
	public void testFindJqlxByCode() {
		Jqlx jqlx = jqlxCommonService.findJqlxByCode("bm02");
		Assert.assertEquals("mc2", jqlx.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/community/JqlxCommonService/findJqlxsByParentId-setup.xml")
	public void testFindAllJqlxs() {
		List<Jqlx> jqlxList = jqlxCommonService.findAllJqlxs();
		Assert.assertEquals(3, jqlxList.size());
		Assert.assertEquals("mc3", jqlxList.get(2).getName());
	}
}
