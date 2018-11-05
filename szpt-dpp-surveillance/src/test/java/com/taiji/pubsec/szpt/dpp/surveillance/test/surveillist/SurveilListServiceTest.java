package com.taiji.pubsec.szpt.dpp.surveillance.test.surveillist;

import javax.annotation.Resource;
import org.junit.Before;
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
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.dpp.surveillance.test.DataSetUp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public class SurveilListServiceTest extends DataSetUp{
	
	private static Logger LOGGER = LoggerFactory.getLogger(SurveilListServiceTest.class);

	@Resource
	private SurveilListService surveilListService ;
	
	@Before
	public void before(){
		saveDatabaseAttachments();
	}
	
	@Test
	@DatabaseSetups(value = { @DatabaseSetup(value = { "classpath:testdata/data-setup.xml" }) })
	public void testLoadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate(){
		try{
			surveilListService.loadAllSurveilListStatusOnAndOperateStatusPassAndNotOutOfDate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
