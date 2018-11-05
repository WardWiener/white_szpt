package com.taiji.pubsec.szpt.zagl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
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
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zagl.model.CaseRelation;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPerson;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPersonRelation;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class SpecialCaseServiceTestCase extends TestBase {
	
	@Resource
	private SpecialCaseService specialCaseService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/createSpecialCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/createSpecialCase-expected.xml", table = "t_zagl_za", query="select bm, cjrq, aqjj, ryid, mc, xz, xbjh, zywt, jz, zhgxsj, gxry_id from t_zagl_za", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void createSpecialCase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Person person = (Person) this.dao.findById(Person.class, "ryId");
		SpecialCase specialCase = new SpecialCase();
		specialCase.setCode("bm");
		specialCase.setContent("aqjj");
		specialCase.setCreatedTime(sdf.parse("2016-10-10 10:10:10"));
		specialCase.setCreatePerson(person);
		specialCase.setName("mc");
		specialCase.setNature("xz");
		specialCase.setPlan("xbjh");
		specialCase.setProblem("zywt");
		specialCase.setProgress("jz");
		specialCase.setUpdatedTime(sdf.parse("2016-10-10 10:10:10"));
		specialCase.setUpdatePerson(person);
		boolean flag = specialCaseService.createSpecialCase(specialCase);
		this.dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/delete-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/delete-expected.xml", table = "t_zagl_za", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void delete() {
		specialCaseService.delete("zaId");
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/findSpecialCaseById-setup.xml")
	public void findSpecialCaseById() {
		SpecialCase specialCase = specialCaseService.findSpecialCaseById("zaId");
		Assert.assertEquals("mc", specialCase.getName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/updateSpecialCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/updateSpecialCase-expected.xml", table = "t_zagl_za", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateSpecialCase() {
		SpecialCase specialCase = (SpecialCase) this.dao.findById(SpecialCase.class, "zaId");
		specialCase.setCode("bm2");
		specialCase.setContent("aqjj2");
		specialCase.setName("mc2");
		specialCase.setNature("xz2");
		specialCase.setPlan("xbjh2");
		specialCase.setProblem("zywt2");
		specialCase.setProgress("jz2");
		specialCaseService.updateSpecialCase(specialCase);
		this.dao.flush();
	}

	@Test
	@DatabaseSetup("classpath:dataset/specialCase/testAcquireNum-setup.xml")
	public void testAcquireNum () {
		String str = specialCaseService.acquireNum();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
        String formatDate = sdf.format(date);
		Assert.assertEquals("" + formatDate + "0001", str);
		
		
		String str1 = specialCaseService.acquireNum();
		Date date1 = new Date();
        String formatDate1 = sdf.format(date1);
		Assert.assertEquals("" + formatDate1 + "0002", str1);
		System.out.println(str);
		System.out.println(str1);
		
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/findSpecialCaseByConditions-setup.xml")
	public void findSpecialCaseByConditions() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("length", 5);
		map.put("zabh", "bm5");
		map.put("zamc", "mc5");
		map.put("ajxz", "xz5");
		map.put("jyaq", "aqjj5");
		Pager<SpecialCase> casePager = specialCaseService.findSpecialCaseByConditions(map, "", 0, 5);
		Assert.assertEquals(1, casePager.getTotalNum().intValue());
		Assert.assertEquals(1, casePager.getPageList().size());
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("start", 0);
		map1.put("length", 5);
		map1.put("sjzaj", "zajbm");
		Pager<SpecialCase> casePager1 = specialCaseService.findSpecialCaseByConditions(map1, "", 0, 5);
		Assert.assertEquals(1, casePager1.getTotalNum().intValue());
		Assert.assertEquals(1, casePager1.getPageList().size());
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("start", 0);
		map2.put("length", 5);
		map2.put("zazcy", "ryId");
		Pager<SpecialCase> casePager2 = specialCaseService.findSpecialCaseByConditions(map2, "", 0, 5);
		Assert.assertEquals(1, casePager2.getTotalNum().intValue());
		Assert.assertEquals(1, casePager2.getPageList().size());
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("start", 0);
		map3.put("length", 5);
		map3.put("personId", "ryId");
		Pager<SpecialCase> casePager3 = specialCaseService.findSpecialCaseByConditions(map3, "ryId", 0, 5);
		Assert.assertEquals(1, casePager3.getTotalNum().intValue());
		Assert.assertEquals(1, casePager3.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/findInvolvedPersonByCase-setup.xml")
	public void findInvolvedPersonByCase() {
		Pager<SpecialCaseInvolvedPerson	> personPager = specialCaseService.findInvolvedPersonByCase("zaId", 0, 5);
		Assert.assertEquals(8, personPager.getTotalNum().intValue());
		Assert.assertEquals(5, personPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/findInvolvedPersonRelationsByCase-setup.xml")
	public void findInvolvedPersonRelationsByCase() {
		List<SpecialCaseInvolvedPersonRelation> relationList = specialCaseService.findInvolvedPersonRelationsByCase("zaId");
		Assert.assertEquals(5, relationList.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/updateInvolvedPersons-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/updateInvolvedPersons-expected.xml", table = "t_zagl_zasary", query="select zagl_za_id, cjsj, ryid, hjdz, hj, sfzh, xm, ch, sjh from t_zagl_zasary order by id desc", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateInvolvedPersons() throws ParseException {
		List<SpecialCaseInvolvedPerson> persons = new ArrayList<SpecialCaseInvolvedPerson>();
		Person person = (Person) this.dao.findById(Person.class, "ryId");
		SpecialCase specialCase = (SpecialCase) this.dao.findById(SpecialCase.class, "zaId");
		SpecialCaseInvolvedPerson person1 = (SpecialCaseInvolvedPerson) this.dao.findById(SpecialCaseInvolvedPerson.class, "ryId2");
		SpecialCaseInvolvedPerson person2 = new SpecialCaseInvolvedPerson();
		person2.setCreatedTime(DateUtils.parseDate("2016-10-10 10:10:10", "yy-MM-dd HH:mm:ss"));
		person2.setCreatePerson(person);
		person2.setHouseholdAddress("hjdz");
		person2.setHouseholdRegister("hj");
		person2.setIdcard("sfzh");
		person2.setName("xm");
		person2.setNick("ch");
		person2.setPhone("sjh");
		person2.setSpecialCase(specialCase);
	
		persons.add(person1);
		persons.add(person2);
		List<String> ids = new ArrayList<String>();
		ids.add("ryId");
		
		boolean flag = specialCaseService.updateInvolvedPersons("zaId", persons, ids);
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/updateInvolvedPersonRelations-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/updateInvolvedPersonRelations-expected.xml", table = "t_zagl_zasarygx", query="select zagl_za_id from t_zagl_zasarygx order by id desc", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateInvolvedPersonRelations() throws ParseException {
		List<SpecialCaseInvolvedPersonRelation> relations = new ArrayList<SpecialCaseInvolvedPersonRelation>();
		SpecialCase specialCase = (SpecialCase) this.dao.findById(SpecialCase.class, "zaId");
		SpecialCaseInvolvedPersonRelation relation1 = (SpecialCaseInvolvedPersonRelation) this.dao.findById(SpecialCaseInvolvedPersonRelation.class, "gxId2");
		SpecialCaseInvolvedPersonRelation relation2 = new SpecialCaseInvolvedPersonRelation();
		relation2.setSpecialCase(specialCase);
	
		relations.add(relation1);
		relations.add(relation2);
		List<String> ids = new ArrayList<String>();
		ids.add("gxId");
		
		boolean flag = specialCaseService.updateInvolvedPersonRelations("zaId", relations, ids);
		dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/saveStickSpecialCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/saveStickSpecialCase-expected.xml", table = "t_zagl_zxzdjl", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void saveStickSpecialCase() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		specialCaseService.stickSpecialCase("zaId", "ryId", sdf.parse("2016-10-10 10:10:10"));
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/saveUnstickSpecialCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/saveUnstickSpecialCase-expected.xml", table = "t_zagl_zxzdjl", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void saveUnstickSpecialCase() throws ParseException {
		specialCaseService.unstickSpecialCase("zaId", "ryId");
		dao.flush();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/addSonCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/addSonCase-expected.xml", table = "t_zagl_zazaj", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void addSonCase() {
		CaseRelation caseRelation = new CaseRelation();
		caseRelation.setCaseCode("zajbm");
		caseRelation.setCaseName("zajmc");
		caseRelation.setWorkers("bamj");
		caseRelation.setSpecialCase((SpecialCase) dao.findById(SpecialCase.class, "zaId"));
		specialCaseService.addSonCase(caseRelation);
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCase/deleteSonCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCase/deleteSonCase-expected.xml", table = "t_zagl_zazaj", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void deleteSonCase() {
		specialCaseService.deleteSonCase("zaId", "zajbm");
		dao.flush();
	}
}
