package com.taiji.pubsec.szpt.zagl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(rollbackFor = Exception.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class SpecialCaseMaterialServiceTestCase extends TestBase {

	@Resource
	private SpecialCaseMaterialService specialCaseMaterialService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMaterial/addMaterial-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMaterial/addMaterial-expected.xml", table = "t_zagl_zazl",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void addMaterial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SpecialCaseMaterial specialCaseMaterial = new SpecialCaseMaterial();
		Person person  = (Person) this.dao.findById(Person.class, "ryId");
//		Attachment attachment = (Attachment) this.dao.findById(Attachment.class, "fjId");
		SpecialCase specialCase = (SpecialCase) this.dao.findById(SpecialCase.class, "zazlId");
		specialCaseMaterial.setCreatedName("zazlmc");
		specialCaseMaterial.setSpecialCase(specialCase);
		specialCaseMaterial.setType("lx");
		try {
			specialCaseMaterial.setCreatedTime(sdf.parse("2016-10-10 10:10:10"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		specialCaseMaterial.setCreatePerson(person);
		//specialCaseMaterial.setAttachment(attachment);
		boolean flag = specialCaseMaterialService.addMaterial(specialCaseMaterial);
		this.dao.flush();
		Assert.assertEquals(flag, true);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMaterial/deleteMaterial-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMaterial/deleteMaterial-expected.xml", table = "t_zagl_zazl",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void deleteMaterial() {
		specialCaseMaterialService.deleteMaterial("zazlId");
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMaterial/findMaterialByCase-setup.xml")
	public void findMaterialByCase() {
		Map<String,Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("start", 0);
		paramsMap.put("length", 5);
		paramsMap.put("fileName", "zazlmc6");
		Pager<SpecialCaseMaterial> scmPager = specialCaseMaterialService.findMaterialByCase("zaId", paramsMap, 0, 5);
		Assert.assertEquals(1, scmPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMaterial/findMaterialById-setup.xml")
	public void findMaterialById() {
		SpecialCaseMaterial specialCaseMaterial = specialCaseMaterialService.findMaterialById("zazlId6");
		Assert.assertEquals("zazlmc6", specialCaseMaterial.getCreatedName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMaterial/updateMaterial-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseMaterial/updateMaterial-expected.xml", table = "t_zagl_zazl",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateMaterial() {
		SpecialCaseMaterial specialCaseMaterial = specialCaseMaterialService.findMaterialById("zazlId");
		specialCaseMaterial.setCreatedName("zazlmc2");
		specialCaseMaterial.setType("lx2");
		specialCaseMaterialService.updateMaterial(specialCaseMaterial);
		this.dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseMaterial/findMaterialByConditions-setup.xml")
	public void findMaterialByConditions() {
		SpecialCaseMaterial specialCaseMaterial = specialCaseMaterialService.findMaterialByConditions("zazlmc6", "lx", "zaId");
		Assert.assertEquals("zazlId6", specialCaseMaterial.getId());
	}
}
