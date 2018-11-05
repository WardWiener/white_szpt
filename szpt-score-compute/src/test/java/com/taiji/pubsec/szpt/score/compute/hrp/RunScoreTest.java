package com.taiji.pubsec.szpt.score.compute.hrp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScoreForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScorePointConfigForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScorePointForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScoreRuleForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.compute.hrp.listener.MockSaveOrUpdateResultOfScoreForHrpListener;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.ScoreRule;
import com.taiji.pubsec.szpt.score.util.hrp.service.HrpScoreComputeTaskUtilService;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hrp.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
/*@Sql(value = {"classpath:hrpTableSetUp.sql"},  
config =  
@SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--",  
        dataSource = "dataSource", transactionManager = "transactionManager"))  */
public class RunScoreTest{
	
	private static Logger LOGGER = LoggerFactory.getLogger(RunScoreTest.class);
	
	@Resource
	private HrpScoreService hrpScoreService;
	@Resource
	private ComputeProcessService computeProcessService;
	@Resource
	private ScoreComputeTaskService taskService;
	@Resource
	private ScoreComputeTaskService scoreComputeTaskService ;
	@Resource
	private HrpScoreComputeTaskUtilService hrpScoreComputeTaskUtilService;
	
	@Autowired
	private SqlDao sqlDao ;
	
	@Autowired
	private Dao dao ;
	
	@Before
	public void before(){
		//this.executeSqlScript("classpath:hrp/testdata/hrpTableSetUp.sql", false);
	}

	@Test
	@DatabaseSetup("classpath:hrp/testdata/hrp-setup.xml")
	public void scoreComputeTaskRunTest(){
		try{
			
			generateComputeTask() ;
			
			MockSaveOrUpdateResultOfScoreForHrpListener saveOrUpdateResultOfScoreForHrpListener = new MockSaveOrUpdateResultOfScoreForHrpListener(hrpScoreService, computeProcessService);
			SaveOrUpdateResultOfScorePointForHrpListener saveOrUpdateResultOfScorePointForHrpListener = new SaveOrUpdateResultOfScorePointForHrpListener(hrpScoreService);
			SaveOrUpdateResultOfScoreRuleForHrpListener saveOrUpdateResultOfScoreRuleForHrpListener = new SaveOrUpdateResultOfScoreRuleForHrpListener(hrpScoreService);
			SaveOrUpdateResultOfScorePointConfigForHrpListener saveOrUpdateResultOfScorePointConfigForHrpListener = new SaveOrUpdateResultOfScorePointConfigForHrpListener(hrpScoreService);	
			
			ScoreComputeTask task = taskService.findById(Constant.SC_TASK_ID_HRP);
			
			LOGGER.debug("得到高危人积分任务，id为:"+task.getId());
			
			//设置任务执行后侦听器，保存计算结果
			task.setListener(saveOrUpdateResultOfScoreForHrpListener);
			
			//设置得分点执行后侦听器，保存计算结果
			for(ScoreComputePointConfig scpc : task.getScorePointConfigs()){
				scpc.setListener(saveOrUpdateResultOfScorePointConfigForHrpListener);
				ScoreComputePoint point = scpc.getScorePoint();
				point.setListener(saveOrUpdateResultOfScorePointForHrpListener);
				//设置规则执行后侦听器，保存计算结果
				for(ScoreComputeRule scr : point.getScoreRules()){
					ScoreComputeRuleImpl scrImpl = (ScoreComputeRuleImpl)scr;
					scrImpl.setListener(saveOrUpdateResultOfScoreRuleForHrpListener);
				}
			}

			String hrpid = "testHrpId1";
			task.run(new DefaultParameter(hrpid, Constant.PARAMETER_TAG_HRP_ID));
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgSaveComputeTask(){
		try{
			generateComputeTask();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHrpTripRecordTemplateIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentTripRecordTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	@Test
	public void testHrpPlaceRecordTemplateIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentPlaceRecordTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	@Test
	public void testHrpIncomeTemplateIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentIncomeTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	@Test
	public void testHrpMarriageStatusTemplateIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentMarriageStatusTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	@Test
	public void testHrpEmploymentStatusIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentEmploymentStatusTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	@Test
	public void testHrpPersonTypeTemplateIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentPersonTypeTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	@Test
	public void testHrpImportantControlTypeTemplateIndependent(){
		
		String url = "groovyrule/hrp/independent/hrpIndependentImportantControlTypeTemplate.groovy" ;
		String script = getGroovyScript(url);
		
		Binding bind = new Binding();
		GroovyShell shell = new GroovyShell(bind);
		Object result = shell.evaluate(script);
		LOGGER.debug("输出结果:" + result);
		
	}
	
	private String getGroovyScript(String url){
		InputStream in = GroovyScriptTest.class.getClassLoader().getResourceAsStream(url);
		try{
			String script = FileFmtUtils.readByBufferedLinesToStr(in); 
			LOGGER.debug(script);
			return script ;
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void generateComputeTask(){
		hrpScoreComputeTaskUtilService.renewHrpComputeTask(generateRuleInfo());
	}
	
	private ScoreRule generateRuleInfo(){
		ScoreRule sr = new ScoreRule(Double.valueOf(60));
		
		List<RuleDetail> rds = new ArrayList<RuleDetail>() ;
		sr.setRuleDetails(rds);
		
		rds.add(new RuleDetail("在控类型-评分项权重", String.valueOf(20))) ;
		rds.add(new RuleDetail("在控类型-高危", String.valueOf(110))) ;
		rds.add(new RuleDetail("在控类型-关注", String.valueOf(80))) ;
		rds.add(new RuleDetail("在控类型-一般", String.valueOf(60))) ;
		rds.add(new RuleDetail("在控类型-无", String.valueOf(0))) ;
		
		rds.add(new RuleDetail("人员类别-评分项权重", String.valueOf(20))) ;
		rds.add(new RuleDetail("人员类别-权重封顶", String.valueOf(40))) ;
		rds.add(new RuleDetail("人员类别-在逃人员", String.valueOf(120))) ;
		rds.add(new RuleDetail("人员类别-涉稳人员", String.valueOf(80))) ;
		rds.add(new RuleDetail("人员类别-涉恐人员", String.valueOf(90))) ;
		rds.add(new RuleDetail("人员类别-肇事肇祸精神病人", String.valueOf(40))) ;
		rds.add(new RuleDetail("人员类别-重点上访人员", String.valueOf(50))) ;
		rds.add(new RuleDetail("人员类别-违法犯罪青少年", String.valueOf(20))) ;
		rds.add(new RuleDetail("人员类别-艾滋病人", String.valueOf(20))) ;
		rds.add(new RuleDetail("人员类别-涉毒人员@吸毒人员", String.valueOf(20))) ;
		rds.add(new RuleDetail("人员类别-涉毒人员@制贩毒人员", String.valueOf(20))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@危害国家安全案", String.valueOf(120))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@危害公共安全案", String.valueOf(100))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@破坏社会主义市场经济秩序案", String.valueOf(60))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@侵犯公民人身权利、民主权利案", String.valueOf(100))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@侵犯财产案", String.valueOf(80))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@妨害社会管理案", String.valueOf(60))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@危害国防利益案", String.valueOf(80))) ;
		rds.add(new RuleDetail("人员类别-刑事前科@渎职案", String.valueOf(20))) ;
		
		rds.add(new RuleDetail("就业情况-评分项权重", String.valueOf(15))) ;
		rds.add(new RuleDetail("就业情况-无业", String.valueOf(100))) ;
		rds.add(new RuleDetail("就业情况-待业", String.valueOf(80))) ;
		rds.add(new RuleDetail("就业情况-失业", String.valueOf(80))) ;
		rds.add(new RuleDetail("就业情况-就业", String.valueOf(40))) ;
		
		rds.add(new RuleDetail("婚姻情况-评分项权重", String.valueOf(5))) ;
		rds.add(new RuleDetail("婚姻情况-已婚", String.valueOf(50))) ;
		rds.add(new RuleDetail("婚姻情况-再婚", String.valueOf(60))) ;
		rds.add(new RuleDetail("婚姻情况-丧偶", String.valueOf(100))) ;
		rds.add(new RuleDetail("婚姻情况-未婚", String.valueOf(100))) ;
		rds.add(new RuleDetail("婚姻情况-离婚", String.valueOf(100))) ;
		rds.add(new RuleDetail("婚姻情况-未知", String.valueOf(0))) ;
		
		rds.add(new RuleDetail("经济收入（月）-评分项权重", String.valueOf(5))) ;
		rds.add(new RuleDetail("经济收入（月）-少于1000元", String.valueOf(100))) ;
		rds.add(new RuleDetail("经济收入（月）-1000~2000", String.valueOf(90))) ;
		rds.add(new RuleDetail("经济收入（月）-2000~5000", String.valueOf(70))) ;
		rds.add(new RuleDetail("经济收入（月）-5000以上", String.valueOf(50))) ;
		rds.add(new RuleDetail("经济收入（月）-空", String.valueOf(0))) ;
		
		rds.add(new RuleDetail("近一月出行次数-评分项权重", String.valueOf(15))) ;
		rds.add(new RuleDetail("近一月出行次数-=6次", String.valueOf(100))) ;
		rds.add(new RuleDetail("近一月出行次数-=5次", String.valueOf(90))) ;
		rds.add(new RuleDetail("近一月出行次数-=4次", String.valueOf(70))) ;
		rds.add(new RuleDetail("近一月出行次数-=3次", String.valueOf(60))) ;
		rds.add(new RuleDetail("近一月出行次数-=2次", String.valueOf(50))) ;
		rds.add(new RuleDetail("近一月出行次数-<=1次", String.valueOf(0))) ;
		
		rds.add(new RuleDetail("场所属性-评分项权重", String.valueOf(20))) ;
		rds.add(new RuleDetail("场所属性-权重封顶", String.valueOf(40))) ;
		rds.add(new RuleDetail("场所属性-娱乐场所权重（近一年）@>=20次", String.valueOf(120))) ;
		rds.add(new RuleDetail("场所属性-娱乐场所权重（近一年）@>2次，且<20次", String.valueOf(60))) ;
		rds.add(new RuleDetail("场所属性-娱乐场所权重（近一年）@<=2次", String.valueOf(0))) ;
		rds.add(new RuleDetail("场所属性-网吧权重（近一年）@>=240小时", String.valueOf(90))) ;
		rds.add(new RuleDetail("场所属性-网吧权重（近一年）@>56小时，且<240小时", String.valueOf(50))) ;
		rds.add(new RuleDetail("场所属性-网吧权重（近一年）@<=56小时", String.valueOf(0))) ;
		rds.add(new RuleDetail("场所属性-酒店宾馆权重（近一年）@>=20天", String.valueOf(90))) ;
		rds.add(new RuleDetail("场所属性-酒店宾馆权重（近一年）@>2天，且<20天", String.valueOf(50))) ;
		rds.add(new RuleDetail("场所属性-酒店宾馆权重（近一年）@<=2天", String.valueOf(0))) ;
		
		return sr ;
	}
}
