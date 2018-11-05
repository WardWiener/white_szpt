package com.taiji.pubsec.szpt.zagl.service;

import java.text.ParseException;

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
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseReport;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
//@Transactional(rollbackFor=Exception.class)
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,TransactionDbUnitTestExecutionListener.class })
public class SpecialCaseReportServiceTestCase extends TestBase {

	@Resource
	private SpecialCaseReportService specialCaseReportService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseReport/addReport-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseReport/addReport-expected.xml", table = "t_zagl_zabg",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void addReport() throws ParseException {
		SpecialCaseReport report = new SpecialCaseReport();
		Person person = (Person) this.dao.findById(Person.class, "ryId");
		SpecialCase specialCase = (SpecialCase) this.dao.findById(SpecialCase.class, "zaId");
		report.setAttachmentId("attachmentId");
		report.setCreatedTime(DateUtils.parseDate("2016-10-10 10:10:10", "yy-MM-dd HH:mm:ss"));
		report.setCreatePerson(person);
		report.setName("mc");
		report.setType("lx");
		report.setSpecialCase(specialCase);
		
		boolean flag = specialCaseReportService.addReport(report);
		this.dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseReport/deleteReport-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseReport/deleteReport-expected.xml", table = "t_zagl_zabg",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void deleteReport() throws ParseException {
		boolean flag = specialCaseReportService.deleteReport("bgId");
		this.dao.flush();
		Assert.assertEquals(true, flag);
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseReport/findReportByCase-setup.xml")
	public void findReportByCase() {
		Pager<SpecialCaseReport> reportPager = specialCaseReportService.findReportByCase("zaId", 0, 5);
		Assert.assertEquals(8, reportPager.getTotalNum().intValue());
		Assert.assertEquals(5, reportPager.getPageList().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseReport/findReportId-setup.xml")
	public void findReportId() {
		SpecialCaseReport report = specialCaseReportService.findReportId("bgId8");
		Assert.assertEquals("mc8", report.getName());
		Assert.assertEquals("lx5", report.getType());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/specialCaseReport/updateReport-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/specialCaseReport/updateReport-expected.xml", table = "t_zagl_zabg",  assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updateReport() {
		SpecialCaseReport report = (SpecialCaseReport) this.dao.findById(SpecialCaseReport.class, "bgId");
		report.setAttachmentId("attachmentId2");
		report.setName("mc2");
		report.setType("lx2");
		boolean flag = specialCaseReportService.updateReport(report);
		this.dao.flush();
		Assert.assertEquals(true, flag);
	}
}
