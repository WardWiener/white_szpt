package com.taiji.pubsec.szpt.caseanalysis.tag.service;

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
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.bean.CaseInfoServiceBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ArchivedFile;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CrimialCaseNote;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.test.TestBase;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CaseServiceTestCase extends TestBase {

	@Resource
	private CaseService caseService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findCaseByConditions-setup.xml")
	public void findCaseByConditions() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		Pager<CriminalBasicCase> casePager = caseService.findCaseByConditions(conditions, 0, 3);
		Assert.assertEquals(3, casePager.getTotalNum().intValue());
		Map<String, Object> conditions1 = new HashMap<String, Object>();
		conditions1.put("tagState", "002");
		Pager<CriminalBasicCase> casePager1 = caseService.findCaseByConditions(conditions1, 0, 3);
		Assert.assertEquals(1, casePager1.getTotalNum().intValue());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findCaseByCode-setup.xml")
	public void findCaseByCode() {
		CriminalBasicCase basicCase = caseService.findCaseByCode("ajbh");
		Assert.assertEquals("ajmc", basicCase.getCaseName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findAlarmByCase-setup.xml")
	public void findAlarmByCase() {
		//FactJq factJq = caseService.findAlarmByCase("ajbh");
		//Assert.assertEquals("jqmc", factJq.getName());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findNotesByCase-setup.xml")
	public void findNotesByCase() {
		List<CrimialCaseNote> notes = caseService.findNotesByCase("ajbh");
		Assert.assertEquals(3, notes.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findSuspectsByCase-setup.xml")
	public void findSuspectsByCase() {
		List<CriminalPerson> persons = caseService.findSuspectsByCase("ajbh");
		Assert.assertEquals(2, persons.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findObjectsByCase-setup.xml")
	public void findObjectsByCase() {
		List<CriminalObject> objects = caseService.findObjectsByCase("ajbh");
		Assert.assertEquals(2, objects.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findCaseBySuspect-setup.xml")
	public void findCaseBySuspect() {
		List<CriminalBasicCase> cases = caseService.findCaseBySuspect("personId1");
		Assert.assertEquals(2, cases.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findArchivedFileCase-setup.xml")
	public void findArchivedFileCase() {
		List<ArchivedFile> files = caseService.findArchivedFileCase("ajbh");
		Assert.assertEquals(2, files.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findRelatedCase-setup.xml")
	public void findRelatedCase() {
		List<CriminalBasicCase> cases = caseService.findRelatedCase("ajbh");
		Assert.assertEquals(7, cases.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/case/findCaseInfoByConditions-setup.xml")
	public void findCaseInfoByConditions() {
		List<CaseInfoServiceBean> caseBeans = caseService.findCaseInfoByConditions(null, null, new String[]{});
		Assert.assertEquals(0, caseBeans.size());
	}
}
