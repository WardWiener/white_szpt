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
import com.taiji.pubsec.szpt.common.model.Country;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.SzptUnit;
import com.taiji.pubsec.szpt.test.TestBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class SzptUnitCommonServiceTestCase extends TestBase{

	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findOrderCellByType-setup.xml")
	public void testFindOrderCellByType() {
		List<OrderCell> orderCellLst = szptUnitCommonService.findOrderCellByType("pcs");
		Assert.assertEquals(3, orderCellLst.size());
		Assert.assertEquals("003", orderCellLst.get(0).getId());
		Assert.assertEquals("pcs", orderCellLst.get(0).getType());
		Assert.assertEquals("pcs001", orderCellLst.get(0).getCode());
		Assert.assertEquals("长江分局", orderCellLst.get(0).getName());
		Assert.assertEquals("经开分局", orderCellLst.get(1).getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findOrderCellByCode-setup.xml")
	public void testFindOrderCellByCode() {
		List<OrderCell> orderCellLst = szptUnitCommonService.findOrderCellByCode("zhuge002");
		Assert.assertEquals(2, orderCellLst.size());
		Assert.assertEquals("003", orderCellLst.get(0).getId());
		Assert.assertEquals("pcs", orderCellLst.get(0).getType());
		Assert.assertEquals("pcs001", orderCellLst.get(0).getCode());
		Assert.assertEquals("长江分局", orderCellLst.get(0).getName());
		Assert.assertEquals("经开分局", orderCellLst.get(1).getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findOrderCellByparentId-setup.xml")
	public void testFindOrderCellByparentId() {
		List<OrderCell> orderCellLst = szptUnitCommonService.findOrderCellByparentId("002");
		Assert.assertEquals(2, orderCellLst.size());
		Assert.assertEquals("003", orderCellLst.get(0).getId());
		Assert.assertEquals("pcs", orderCellLst.get(0).getType());
		Assert.assertEquals("pcs001", orderCellLst.get(0).getCode());
		Assert.assertEquals("长江分局", orderCellLst.get(0).getName());
		Assert.assertEquals("经开分局", orderCellLst.get(1).getName());
	}
	
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findUnitByType-setup.xml")
	public void testFindUnitByType() {
		List<SzptUnit> szptUnitLst = szptUnitCommonService.findUnitByType("zg");
		Assert.assertEquals(2, szptUnitLst.size());
		Assert.assertEquals("001", szptUnitLst.get(0).getId());
		Assert.assertEquals("zg", szptUnitLst.get(0).getType());
		Assert.assertEquals("zhuge001", szptUnitLst.get(0).getCode());
		Assert.assertEquals("主格1", szptUnitLst.get(0).getName());
		Assert.assertEquals("主格2", szptUnitLst.get(1).getName());
	}
	
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findAllSzptUnit-setup.xml")
	public void testFindAllSzptUnit() {
		List<SzptUnit> szptUnitLst = szptUnitCommonService.findAllSzptUnit();
		Assert.assertEquals(5, szptUnitLst.size());
		Assert.assertEquals("001", szptUnitLst.get(0).getId());
		Assert.assertEquals("zg", szptUnitLst.get(0).getType());
		Assert.assertEquals("zhuge001", szptUnitLst.get(0).getCode());
		Assert.assertEquals("主格1", szptUnitLst.get(0).getName());
		Assert.assertEquals("主格2", szptUnitLst.get(1).getName());
		Assert.assertEquals("长江分局", szptUnitLst.get(2).getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findSzptUnitByCode-setup.xml")
	public void testFindSzptUnitByCode() {
		OrderCell orderCell = szptUnitCommonService.findSzptUnitByCode("zhuge002");
		Assert.assertEquals("002", orderCell.getId());
		Assert.assertEquals("zg", orderCell.getType());
		Assert.assertEquals("zhuge002", orderCell.getCode());
		Assert.assertEquals("主格2", orderCell.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findSzptUnitById-setup.xml")
	public void testfindSzptUnitById() {
		SzptUnit szptUnit = szptUnitCommonService.findSzptUnitById("001");
		Assert.assertEquals("001", szptUnit.getId());
		Assert.assertEquals("zg", szptUnit.getType());
		Assert.assertEquals("zhuge001", szptUnit.getCode());
		Assert.assertEquals("主格1", szptUnit.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/service/szptUnitCommon/findCountryByUnitCode-setup.xml")
	public void testFindCountryByUnitCode() {
		List<Country> countryLst = szptUnitCommonService.findCountryByUnitCode("pcsbm2");
		Assert.assertEquals(3, countryLst.size());
		Assert.assertEquals("sq003", countryLst.get(0).getCode());
		Assert.assertEquals("小区3", countryLst.get(0).getName());
		Assert.assertEquals("小区4", countryLst.get(1).getName());
	}
}
