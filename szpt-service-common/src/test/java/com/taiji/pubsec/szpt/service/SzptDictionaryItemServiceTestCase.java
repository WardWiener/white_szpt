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
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class SzptDictionaryItemServiceTestCase extends TestBase{
	
	@Resource
	private SzptDictionaryItemService szptDictionaryItemService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptDictionaryItem/findItemsByParentCode-setup.xml")
	public void testFindOrderCellByType() {
		List<DictionaryItem> itemLst = szptDictionaryItemService.findItemsByParentCode("code03","lxcode02");
		Assert.assertEquals(2, itemLst.size());
		Assert.assertEquals("004", itemLst.get(0).getId());
		Assert.assertEquals("字典项4", itemLst.get(0).getName());
		Assert.assertEquals("code04", itemLst.get(0).getCode());
		Assert.assertEquals(new Integer(1), itemLst.get(0).getNumber());
		Assert.assertEquals("1", itemLst.get(0).getState());
		Assert.assertEquals("字典项5", itemLst.get(1).getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptDictionaryItem/findAllDictCodeIncludedAllChildrenAndSelfByCodeLike-setup.xml")
	public void testFindAllDictCodeIncludedAllChildrenAndSelfByCodeLike() {
		List<String> codes = szptDictionaryItemService.findAllDictCodeIncludedAllChildrenAndSelfByCodeLike("code03","lxcode02");
		codes.contains("code0300");
		Assert.assertEquals(8, codes.size());
	}
}
