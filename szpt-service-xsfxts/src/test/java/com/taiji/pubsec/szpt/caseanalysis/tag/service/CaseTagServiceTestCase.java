package com.taiji.pubsec.szpt.caseanalysis.tag.service;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.test.TestBase;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CaseTagServiceTestCase extends TestBase {

	@Resource
	private CaseTagService caseTagService;
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	//创建打标测试
	@Test
	@DatabaseSetup("classpath:dataset/caseTag/tagCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/caseTag/tagCase-expected.xml", table = "t_xsajfx_dbjc",  assertionMode = DatabaseAssertionMode.NON_STRICT),
		@ExpectedDatabase(value = "classpath:dataset/caseTag/tagCase-expected.xml", table = "t_xsajfx_dbzatd", query="select zatd from t_xsajfx_dbzatd", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void tagCase() throws ParseException {
		CaseTag caseTag = new CaseTag();
		caseTag.setAddress("fadz");
		caseTag.setCommunity("facq");
		caseTag.setCreatePersonId("ryId");
		caseTag.setCreateTime(DateUtils.parseDate("2016-10-10 10:10:10", "yyyy-MM-dd HH:mm:ss"));
		caseTag.setEntrance("zark");
		caseTag.setExit("zack");
		caseTag.setFirstSort("ajxzyj");
		caseTag.setLatitude("fawd");
		caseTag.setLevel("ajjb");
		caseTag.setLongitude("fajd");
		caseTag.setOccurPlace("facs");
		caseTag.setPeopleNum("zars");
		caseTag.setPeriod("zasd");
		caseTag.setPlaceName("sacsmc");
		caseTag.setPlaceType("sacslx");
		caseTag.setSecondSort("ajxzej");
		caseTag.setType("ajlb");
		caseTag.setUpdatePersonId("ryId");
		caseTag.setUpdateTime(DateUtils.parseDate("2016-10-10 10:10:10", "yyyy-MM-dd HH:mm:ss"));
		
		List<String> featureCodes = new ArrayList<String>();
		featureCodes.add("zatd1");
		featureCodes.add("zatd2");
		caseTagService.tagCase(caseTag, featureCodes, "ajbh");
		dao.flush();
	}
	
	//更新打标测试
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:dataset/caseTag/updatetTagCase-setup.xml")
	@ExpectedDatabases({ 
		@ExpectedDatabase(value = "classpath:dataset/caseTag/updatetTagCase-expected.xml", table = "t_xsajfx_dbjc",  assertionMode = DatabaseAssertionMode.NON_STRICT),
//			@ExpectedDatabase(value = "classpath:dataset/caseTag/updatetTagCase-expected.xml", table = "t_xsajfx_dbzatd", query="select zatd, xsajfx_dbjc_id from t_xsajfx_dbzatd", assertionMode = DatabaseAssertionMode.NON_STRICT),
	})
	public void updatetTagCase() throws ParseException {
		CaseTag caseTag = (CaseTag) dao.findById(CaseTag.class, "dbId");
		caseTag.setAddress("fadz2");
		List<String> featureCodes = new ArrayList<String>();
		featureCodes.add("zatd1");
		featureCodes.add("zatd3");
		caseTagService.tagCase(caseTag, featureCodes, "ajbh");
		dao.flush();
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseTag/findCaseTag-setup.xml")
	public void findCaseTag() {
		CaseTag caseTag = caseTagService.findCaseTag("ajbh");
		Assert.assertEquals("fadz", caseTag.getAddress());
		Assert.assertEquals("ajlb", caseTag.getType());
		Assert.assertEquals("ajxzyj", caseTag.getFirstSort());
		Assert.assertEquals("ajxzej", caseTag.getSecondSort());
		Assert.assertEquals(2, caseTag.getFeatures().size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseTag/findTagOperation-setup.xml")
	public void findTagOperation() {
		List<OperationRecord> records = caseTagService.findTagOperation("ajbh");
		Assert.assertEquals(2, records.size());
	}
	
	@Test
	@DatabaseSetup("classpath:dataset/caseTag/findCaseByConditions-setup.xml")
	public void findCaseByConditions() {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("caseCode", "ajbh3");
		conditions.put("type", "ajlb");
		List<String> firstSorts = new ArrayList<String>();
		firstSorts.add("ajxzyj");
		firstSorts.add("ajxzyj2");
		conditions.put("firstSorts", firstSorts);
		List<String> secondSorts = new ArrayList<String>();
		secondSorts.add("ajxzej");
		secondSorts.add("ajxzej1");
		conditions.put("secondSorts", secondSorts);
		List<String> features = new ArrayList<String>();
		features.add("zatd");
		features.add("zatd2");
		conditions.put("features", features);
		conditions.put("occurPlace", "facs");
		conditions.put("period", "zasd");
		conditions.put("peopleNum", "zars");
		conditions.put("entrance", "zark");
		conditions.put("exit", "zack");
		List<String> communitys = new ArrayList<String>();
		communitys.add("facq");
		communitys.add("facq2");
		conditions.put("communitys", communitys);
		Pager<CriminalBasicCase> casePager = caseTagService.findCaseByConditions(conditions, 0, 3);
		Assert.assertEquals(1, casePager.getTotalNum().intValue());
	}
}
