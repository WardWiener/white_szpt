package com.taiji.pubsec.szpt.score.compute.caseanalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreResultService;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreTemplateService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.listener.ScoreComputeTaskForCaseListener;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.listener.ScorePointConfigForCaseListener;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.service.CaseScoreService;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.strategy.ComputeStrategyForCase;
import com.taiji.pubsec.szpt.test.TestBase;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class CaseScoreServiceTestCase extends TestBase {
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseScoreServiceTestCase.class);
	@Resource
	protected CaseScoreService caseScoreService;
	@Resource
	private ScoreTemplateService scoreTemplateService;
	@Resource
	private CaseService caseService;
	@Resource
	private CaseTagService caseTagService;
	@Resource
	protected ScoreComputeTaskService taskService;
	@Resource
	private ScoreResultService scoreResultService;

	@Resource
	private ScoreComputeTaskService scoreTaskService;
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	
	@Test
	@DatabaseSetup("classpath:case/caseTagSet-setup.xml")
	public void testProduceRelatedCaseTagSet() {
		List<CriminalBasicCase> cases = new ArrayList<CriminalBasicCase>();
		cases.add((CriminalBasicCase) dao.findById(CriminalBasicCase.class, "ajbh1"));
		cases.add((CriminalBasicCase) dao.findById(CriminalBasicCase.class, "ajbh2"));
		cases.add((CriminalBasicCase) dao.findById(CriminalBasicCase.class, "ajbh3"));
		Map<String,Set<String>> result = caseScoreService.produceRelatedCaseTagSet(cases);
		Assert.assertEquals(4, result.get(CaseTag.KEY_CASEFEATURE).size());
		Assert.assertEquals(3, result.get(CaseTag.KEY_CASECOMMUNITY).size());
		Assert.assertEquals(2, result.get(CaseTag.KEY_CASEENTRANCE).size());
		Assert.assertEquals(3, result.get(CaseTag.KEY_CASEEXIT).size());
		Assert.assertEquals(2, result.get(CaseTag.KEY_CASEPERIOD).size());
		Assert.assertEquals(2, result.get(CaseTag.KEY_CASEPERSONCOUNT).size());
		Assert.assertEquals(1, result.get(CaseTag.KEY_CASEPLACE).size());

	}
	
	@Test
	public void testSetIntersaction() {
		Set<String> main = new HashSet<String>();
		main.add("a");
		main.add("b");
		Set<String> other = new HashSet<String>();
		other.add("b");
		other.add("c");
		Assert.assertTrue(main.retainAll(other));
		Assert.assertEquals(1, main.size());
	}
	
	@Test
	@DatabaseSetup("classpath:case/caseCompute-setup.xml")
	public void testCaseCompute() {
		RobberyTheftCaseScoreTemplate template = scoreTemplateService.findTemplate("1");
		Assert.assertEquals("jsrw1", template.getComputeTaskId());
		ScoreComputeTaskImpl task = (ScoreComputeTaskImpl) scoreTaskService.findById("jsrw1");
		Assert.assertEquals("task", task.getDescription());
		CriminalBasicCase mainCase = caseService.findCaseByCode("ajbh1");
		Assert.assertEquals("name1", mainCase.getCaseName());
		CriminalBasicCase comparedCase = caseService.findCaseByCode("ajbh2");
		Assert.assertEquals("name2", comparedCase.getCaseName());
		Map<String,Set<String>> tagSet = caseScoreService.produceRelatedCaseTagSet(Arrays.asList(mainCase));
		Assert.assertEquals(2, tagSet.get(CaseTag.KEY_CASEFEATURE).size());
		Assert.assertTrue(tagSet.get(CaseTag.KEY_CASEFEATURE).contains("zatd1"));
		Assert.assertTrue(tagSet.get(CaseTag.KEY_CASEFEATURE).contains("zatd2"));
		Assert.assertEquals(1, tagSet.get(CaseTag.KEY_CASECOMMUNITY).size());
		Assert.assertEquals("facq1", tagSet.get(CaseTag.KEY_CASECOMMUNITY).toArray()[0].toString());
		Assert.assertEquals(1, tagSet.get(CaseTag.KEY_CASEENTRANCE).size());
		Assert.assertEquals("zark", tagSet.get(CaseTag.KEY_CASEENTRANCE).toArray()[0].toString());
		Assert.assertEquals(1, tagSet.get(CaseTag.KEY_CASEEXIT).size());
		Assert.assertEquals("zack", tagSet.get(CaseTag.KEY_CASEEXIT).toArray()[0].toString());
		Assert.assertEquals(1, tagSet.get(CaseTag.KEY_CASEPERIOD).size());
		Assert.assertEquals("zasd", tagSet.get(CaseTag.KEY_CASEPERIOD).toArray()[0].toString());
		Assert.assertEquals(1, tagSet.get(CaseTag.KEY_CASEPERSONCOUNT).size());
		Assert.assertEquals("zars1", tagSet.get(CaseTag.KEY_CASEPERSONCOUNT).toArray()[0].toString());
		Assert.assertEquals(1, tagSet.get(CaseTag.KEY_CASEPLACE).size());
		Assert.assertEquals("facs", tagSet.get(CaseTag.KEY_CASEPLACE).toArray()[0].toString());


		
		Parameter paramOne = new DefaultParameter(mainCase.getCaseCode(), "param1");
		Parameter paramTwo = new DefaultParameter(tagSet, "param2");
		Parameter paramThree = new DefaultParameter(comparedCase.getCaseCode(), "param3");
		Parameter paramFour = new DefaultParameter(template.getId(), "param4");
		ScoreComputeTaskForCaseListener taskListener = new ScoreComputeTaskForCaseListener(scoreResultService,caseService);
		ScorePointConfigForCaseListener pointListener = new ScorePointConfigForCaseListener(scoreResultService);
		task.setListener(taskListener);
		for(ScoreComputePointConfig scpc : task.getScorePointConfigs()){
			scpc.setListener(pointListener);
		}
		task.setComputeStrategy(new ComputeStrategyForCase());
		task.run(paramOne, paramTwo, paramThree,paramFour);
		
		
//		if(template != null) {
//			//查主案件串并案
//			List<CriminalBasicCase> relatedCases = caseService.findRelatedCase(mainCaseCode);
//			relatedCases.add(caseService.findCaseByCode(mainCaseCode));
//			//计算标签值集合
//			
//			//TODO 查比对案件集合
//			List<CriminalBasicCase> comparedCases = caseScoreService.findComparedCases(mainCaseCode, excludedCases);
//			ScoreComputeTaskImpl task = (ScoreComputeTaskImpl) scoreTaskService.findById(template
//					.getComputeTaskId());
//			for(CriminalBasicCase comparedCase : comparedCases) {
//				Parameter mainCase = new DefaultParameter(mainCaseCode, TASK_PARAMETER_MAINCASECODE);
//				Parameter tagset = new DefaultParameter(tagSet, TASK_PARAMETER_MAINCASETAGSET);
//				Parameter comparedCaseCode = new DefaultParameter(comparedCase.getCaseCode(), TASK_PARAMETER_COMPAREDCASECODE);
//				task.run(mainCase, tagset, comparedCaseCode);
//			}
//		}
//		Assert.assertEquals("name2", task.getDescription());
//		Assert.assertEquals("name2", task.getDescription());
//		Assert.assertEquals("name2", task.getDescription());
//		Assert.assertEquals("name2", task.getDescription());
//		Assert.assertEquals("name2", task.getDescription());
//		Assert.assertEquals("name2", task.getDescription());
//		Assert.assertEquals("name2", task.getDescription());
	}
}
