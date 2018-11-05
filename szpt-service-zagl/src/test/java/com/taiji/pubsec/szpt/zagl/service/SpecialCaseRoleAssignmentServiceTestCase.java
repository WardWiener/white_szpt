package com.taiji.pubsec.szpt.zagl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class SpecialCaseRoleAssignmentServiceTestCase extends TestBase {

	@Resource
	private SpecialCaseRoleAssignmentService specialCaseRoleAssignmentService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/createAssignLeaderRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRoleAssignment/createAssignLeaderRole-expected.xml", table = "t_zagl_ryjsfp",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void createAssignLeaderRole() {
		boolean flag = specialCaseRoleAssignmentService.createAssignLeaderRole("jsId", "ryId");
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/createSpecialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRoleAssignment/createSpecialCaseRole-expected.xml", table = "t_zagl_zaryjsfp",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void createSpecialCaseRole() {
		boolean flag = specialCaseRoleAssignmentService.createSpecialCaseRole("jsId", "ryId", "zaId");
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/removeAssignedSpeccialCaseRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRoleAssignment/removeAssignedSpeccialCaseRole-expected.xml", table = "t_zagl_zaryjsfp",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void removeAssignedSpeccialCaseRole() {
		boolean flag = specialCaseRoleAssignmentService.removeAssignedSpeccialCaseRole("jsId", "ryId", "zaId");
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/removeAssignedLeaderRole-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseRoleAssignment/removeAssignedLeaderRole-expected.xml", table = "t_zagl_ryjsfp",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void removeAssignedLeaderRole() {
		boolean flag = specialCaseRoleAssignmentService.removeAssignedLeaderRole("jsId", "ryId");
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/findLeaderRolePersons-setup.xml")
	public void findLeaderRolePersons() {
		Pager<Person> pPager = specialCaseRoleAssignmentService.findLeaderRolePersons("jsId", "dwId", 0, 3);
		Assert.assertEquals(5, pPager.getTotalNum().intValue());
		Assert.assertEquals(3, pPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/findPersonsByConditions-setup.xml")
	public void findPersonsByConditions() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("caseId", "zaId");
		Pager<Person> pPager = specialCaseRoleAssignmentService.findPersonsByConditions(conditions, 0, 3);
		Assert.assertEquals(6, pPager.getTotalNum().intValue());
		Assert.assertEquals(3, pPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/findAssignedPersonsByConditions-setup.xml")
	public void findAssignedPersonsByConditions() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		Pager<SpecialCaseRoleAssignment> pPager = specialCaseRoleAssignmentService.findAssignedPersonsByConditions(conditions, 0, 3);
		Assert.assertEquals(5, pPager.getTotalNum().intValue());
		Assert.assertEquals(3, pPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/findLeaderPersonsByConditions-setup.xml")
	public void findLeaderPersonsByConditions() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		Pager<Person> pPager = specialCaseRoleAssignmentService.findLeaderPersonsByConditions(conditions, 0, 3);
		Assert.assertEquals(11, pPager.getTotalNum().intValue());
		Assert.assertEquals(3, pPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/findCaseUnitByCaseId-setup.xml")
	public void findCaseUnitByCaseId() {
		List<Organization> oList = specialCaseRoleAssignmentService.findCaseUnitByCaseId("zaId");
		Assert.assertEquals(2, oList.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseRoleAssignment/findRolesAndPersonsByCaseId-setup.xml")
	public void findRolesAndPersonsByCaseId() {
		Map<SpecialCaseRole, List<Person>> result = specialCaseRoleAssignmentService.findRolesAndPersonsByCaseId("zaId");
		Assert.assertEquals(3, result.size());
	}
}
