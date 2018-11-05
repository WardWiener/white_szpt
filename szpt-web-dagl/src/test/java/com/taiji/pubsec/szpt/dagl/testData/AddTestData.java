package com.taiji.pubsec.szpt.dagl.testData;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseSupectRelation;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CollectInfoSituation;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalPersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(false)
public class AddTestData {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;
	
	@Resource
	private CriminalPersonService cps;

	/**
	 * 添加嫌疑人相关的案件，前科案件  130412199001233418
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void addCriminalBasicCaseAndCriminalPerson(){
		//嫌疑人信息
		CriminalPerson cp = new CriminalPerson();
		cp.setPersonId("130412199001233418");
		cp.setName("张三丰");
		cp.setAliasName("疯子");
		cp.setSex("男");
		cp.setBirthday(new Date());
		cp.setNation("汉族");
		cp.setIdcardNo("130412199001233418");
		cp.setTelephone("18612906849");
		cp.setCulture("本科");
		cp.setPolitics("党员");
		cp.setIfMarry("否");
		cp.setJob("掌门");
		cp.setSpecialIdentity("武林高手");
		cp.setFaith("道教");
		cp.setTone("十堰话");
		cp.setNativePlace("中国");
		cp.setBirthDistrict("湖北省十堰市武当山");
		cp.setDoor("湖北十堰");
		dao.save(cp);
		
		for(int i=0;i<5;i++){
			//案件信息
			CriminalBasicCase cbc = new CriminalBasicCase();
			cbc.setCaseCode("A520114030000201703000"+ i);
			cbc.setCaseName("案件名称" + i);
			cbc.setCaseTypeName("刑事");
			cbc.setCaseTimeStart(new Date());
			dao.save(cbc);
			
			//嫌疑人-案件关系
			CaseSupectRelation csr = new CaseSupectRelation();
			csr.setBasicCase(cbc);
			csr.setCriminalPerson(cp);
			dao.save(csr);
		}
	}
	
	/**
	 * 添加采集信息假数据
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void addCollectInfoSituation(){
		CollectInfoSituation cis = new CollectInfoSituation();
		cis.setIdenty("130412199001233418");
		cis.setCollectProject("指纹，尿液");
		cis.setCollectProjectOther("就是没有其它的");
		cis.setInfoIntoString("0");
		cis.setInspectComparison("0");
		cis.setIsCollect("0");
		cis.setModifyPeopleName("系统管理员");
		cis.setUpdatedTime(new Date());
		cis.setQqNum("296831180，469271078，123456789，4561235456，15478945648，21315544");
		cis.setWeixinNum("wx_18612906840，wx_13511054124，wx_45784575，wx_16sd1611，wx_654622332，wx_793354556");
		cis.setPhoneInfo("18612904578，867905024592703，68:3E:34:2D:5A:91；18312904575，367905024592705，66:3E:34:2D:5A:21；15346454254，120505024595487，A4:3E:34:2E:5A:12");
		dao.save(cis);
	}
	
}
