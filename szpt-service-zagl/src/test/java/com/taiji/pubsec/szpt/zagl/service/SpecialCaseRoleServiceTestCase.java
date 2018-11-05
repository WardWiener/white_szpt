package com.taiji.pubsec.szpt.zagl.service;

import java.util.ArrayList;
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
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class SpecialCaseRoleServiceTestCase extends TestBase{
	
	@Resource
	private SpecialCaseRoleService specialCaseRoleService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRole/findOnloadData-setup.xml")
	public void findOnloadData() {
		List<SpecialCaseRole> roleList = specialCaseRoleService.findOnloadData();
		Assert.assertEquals(5, roleList.size());
	}
	
	@Test 
	@DatabaseSetup("classpath:dataset/specialCaseRole/findById-setup.xml")
	public void findById() {
		SpecialCaseRole role = specialCaseRoleService.findById("zajsId5");
		Assert.assertEquals("bm5", role.getCode());
		Assert.assertEquals("mc5", role.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRole/saveSpecialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRole/saveSpecialCaseRole-expected.xml", table = "t_zagl_zajs",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void saveSpecialCaseRole() {
		specialCaseRoleService.saveSpecialCaseRole("mc");
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRole/updateSpecialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRole/updateSpecialCaseRole-expected.xml", table = "t_zagl_zajs",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateSpecialCaseRole() {
		specialCaseRoleService.updateSpecialCaseRole("mc1", "0001");
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRole/deleteSpecialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRole/deleteSpecialCaseRole-expected.xml", table = "t_zagl_zajs",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void deleteSpecialCaseRole() {
		specialCaseRoleService.deleteSpecialCaseRole("0001");
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRole/updateStopSpecialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRole/updateStopSpecialCaseRole-expected.xml", table = "t_zagl_zajs",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateStopSpecialCaseRole() {
		List<String> strList = new ArrayList<String>();
		strList.add("0001");
		specialCaseRoleService.stopSpecialCaseRole(strList);;
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRole/updateStartSpecialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRole/updateStartSpecialCaseRole-expected.xml", table = "t_zagl_zajs",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateStartSpecialCaseRole() {
		List<String> strList = new ArrayList<String>();
		strList.add("0001");
		specialCaseRoleService.startSpecialCaseRole(strList);;
		this.dao.flush();
	}
	
	@Test 
	@DatabaseSetup("classpath:dataset/specialCaseRole/findRolesByStater-setup.xml")
	public void findRolesByStater() {
		List<SpecialCaseRole>  roles = specialCaseRoleService.findRolesByState("zt");
		Assert.assertEquals(2, roles.size());
		
		List<SpecialCaseRole>  roles2 = specialCaseRoleService.findRolesByState("zt2");
		Assert.assertEquals(1, roles2.size());
	}
}
