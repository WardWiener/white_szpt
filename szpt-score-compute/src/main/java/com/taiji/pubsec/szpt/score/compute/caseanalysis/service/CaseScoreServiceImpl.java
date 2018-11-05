package com.taiji.pubsec.szpt.score.compute.caseanalysis.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.annotation.AopAnno;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreResultService;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreTemplateService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseFeatureTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.aop.CaseScoreProcessMonitorHandler;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.aop.CaseScoreProcessStepMonitorHandler;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.listener.ScoreComputeTaskForCaseListener;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.listener.ScorePointConfigForCaseListener;
import com.taiji.pubsec.szpt.score.compute.caseanalysis.strategy.ComputeStrategyForCase;
import com.taiji.pubsec.szpt.score.compute.common.ComputeConstant;

@Service("caseScoreService")
public class CaseScoreServiceImpl implements CaseScoreService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseScoreServiceImpl.class);

	private static final String TASK_PARAMETER_MAINCASECODE = "parameter_maincasecode";
	private static final String TASK_PARAMETER_MAINCASETAGSET = "parameter_maincasetagset";
	private static final String TASK_PARAMETER_COMPAREDCASECODE = "parameter_comparedcasecode";
	private static final String TASK_PARAMETER_TEMPLATEID = "parameter_templateid";

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	@Resource
	private CaseService caseService;
	@Resource
	private CaseTagService caseTagService;
	@Resource
	private ScoreResultService scoreResultService;
	@Resource
	private ScoreTemplateService scoreTemplateService;
	@Resource
	private ScoreComputeTaskService scoreTaskService;

	public Map<String, Set<String>> produceRelatedCaseTagSet(List<CriminalBasicCase> cases) {
		Map<String, Set<String>> result = new HashMap<String, Set<String>>();
		result.put(CaseTag.KEY_CASEFEATURE, new HashSet<String>());
		result.put(CaseTag.KEY_CASECOMMUNITY, new HashSet<String>());
		result.put(CaseTag.KEY_CASEENTRANCE, new HashSet<String>());
		result.put(CaseTag.KEY_CASEEXIT, new HashSet<String>());
		result.put(CaseTag.KEY_CASEPERIOD, new HashSet<String>());
		result.put(CaseTag.KEY_CASEPERSONCOUNT, new HashSet<String>());
		result.put(CaseTag.KEY_CASEPLACE, new HashSet<String>());

		for (CriminalBasicCase bcase : cases) {
			if (bcase.getCaseTag() != null) {
				result.get(CaseTag.KEY_CASEPLACE).add(bcase.getCaseTag().getOccurPlace());
				result.get(CaseTag.KEY_CASEPERSONCOUNT).add(bcase.getCaseTag().getPeopleNum());
				result.get(CaseTag.KEY_CASEPERIOD).add(bcase.getCaseTag().getPeriod());
				result.get(CaseTag.KEY_CASEEXIT).add(bcase.getCaseTag().getExit());
				result.get(CaseTag.KEY_CASEENTRANCE).add(bcase.getCaseTag().getEntrance());
				result.get(CaseTag.KEY_CASECOMMUNITY).add(bcase.getCaseTag().getCommunity());
				for (CaseFeatureTag feature : bcase.getCaseTag().getFeatures()) {
					result.get(CaseTag.KEY_CASEFEATURE).add(feature.getFeature());
				}
			}
		}
		return result;
	}

	@Override
	public List<CriminalBasicCase> findComparedCases(String mainCaseCode, List<CriminalBasicCase> excludedCases) {
		CriminalBasicCase mainCase = caseService.findCaseByCode(mainCaseCode);
		String caseType = caseTagService.findCaseTag(mainCaseCode).getType();
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("type", caseType);
		List<CriminalBasicCase> comparedCases = caseTagService.findAllCaseByConditions(conditions);
		if (excludedCases != null) {
			comparedCases.removeAll(excludedCases);
		}
		comparedCases.removeAll(scoreResultService.findIgnoredCases(mainCaseCode));
		comparedCases.remove(mainCase);
		return comparedCases;
	}

	@Override
	//指定主案件，计算待比对的案件集合依次计算
	public void processScoreCompute(String mainCaseCode) {
		//catch异常，防止保存的内容回滚
		KafkaProducer kafkaProducer = (KafkaProducer)SpringContextUtil.getBean("kafkaProducer") ;
		try {
			LOGGER.debug("开始串并案积分计算:{}", mainCaseCode);
			List<CriminalBasicCase> excludedCases = null;  //用于页面忽略后执行比对分析传入忽略的case，暂不使用
			RobberyTheftCaseScoreTemplate template = scoreTemplateService.findMatchedTemplate(mainCaseCode);
			if (template != null) {
				// 查比对案件集合
				List<CriminalBasicCase> comparedCases = this.findComparedCases(mainCaseCode, excludedCases);
				
				// 如果希望调用的内部方法相互调用也被拦截，必须获取这个bean，否则无法aop无法拦截
				((CaseScoreService)SpringContextUtil.getBean("caseScoreService")).processScoreComputing(mainCaseCode, template, comparedCases);
				
				//发送完成积分计算的消息
				kafkaProducer.sendData(ComputeConstant.KAFKA_TOPIC_SCORE_RESULT_CASE(), mainCaseCode.toString().getBytes());
			}
			else {
				//发送此类案件无积分计算模板的消息
				kafkaProducer.sendData(ComputeConstant.KAFKA_TOPIC_SCORE_RESULT_CASE(), (mainCaseCode.toString() + "无积分计算模板！").getBytes());
			}
		} catch (Exception e) {
			LOGGER.error("案件积分处理异常", e);
			//发送此类案件无积分计算模板的消息
			kafkaProducer.sendData(ComputeConstant.KAFKA_TOPIC_SCORE_RESULT_CASE(), (mainCaseCode.toString() + "无积分计算模板！").getBytes());
		}

	}
	
	@AopAnno(mark=CaseScoreProcessMonitorHandler.MARK, aopAnnoObj="new Object[]{#arg0, #arg2}")
	@Override
	public void processScoreComputing(String mainCaseCode, RobberyTheftCaseScoreTemplate template,
			List<CriminalBasicCase> comparedCases) {
		// 查主案件串并案
		List<CriminalBasicCase> relatedCases = caseService.findRelatedCase(mainCaseCode);
		relatedCases.add(caseService.findCaseByCode(mainCaseCode));
		// 计算标签值集合
		Map<String, Set<String>> tagSet = this.produceRelatedCaseTagSet(relatedCases);
		ScoreComputeTaskImpl task = (ScoreComputeTaskImpl) scoreTaskService.findById(template.getComputeTaskId());
		ScoreComputeTaskForCaseListener taskListener = new ScoreComputeTaskForCaseListener(scoreResultService,
				caseService);
		ScorePointConfigForCaseListener pointListener = new ScorePointConfigForCaseListener(scoreResultService);
		task.setListener(taskListener);
		task.setComputeStrategy(new ComputeStrategyForCase());
		for (ScoreComputePointConfig scpc : task.getScorePointConfigs()) {
			scpc.setListener(pointListener);
		}

		for (CriminalBasicCase comparedCase : comparedCases) {
			Parameter pMainCase = new DefaultParameter(mainCaseCode, TASK_PARAMETER_MAINCASECODE);
			Parameter pTagset = new DefaultParameter(tagSet, TASK_PARAMETER_MAINCASETAGSET);
			Parameter pComparedCaseCode = new DefaultParameter(comparedCase.getCaseCode(),
					TASK_PARAMETER_COMPAREDCASECODE);
			Parameter pTemplate = new DefaultParameter(template.getId(), TASK_PARAMETER_TEMPLATEID);
			LOGGER.debug("开始计算主案件-比对案件:{}-{}", mainCaseCode, comparedCase.getCaseCode());
			task.run(pMainCase, pTagset, pComparedCaseCode, pTemplate);
			LOGGER.debug("结束计算主案件-比对案件:{}-{}", mainCaseCode, comparedCase.getCaseCode());
			
			// 如果希望调用的内部方法相互调用也被拦截，必须获取这个bean，否则无法aop无法拦截	 
			((CaseScoreService)SpringContextUtil.getBean("caseScoreService")).processScoreCompute(comparedCase.getCaseCode(), mainCaseCode);
			
		}
	}
	
	@AopAnno(mark=CaseScoreProcessStepMonitorHandler.MARK, aopAnnoObj="new Object[]{#arg0, #arg1}")
	@Override
	//指定主案件和比对案件，不需要计算待比对的案件集合
	public void processScoreCompute(String mainCaseCode, String comparedCaseCode) {
		try {
			RobberyTheftCaseScoreTemplate template = scoreTemplateService.findMatchedTemplate(mainCaseCode);
			if (template != null) {
				// 查主案件串并案
				List<CriminalBasicCase> relatedCases = caseService.findRelatedCase(mainCaseCode);
				relatedCases.add(caseService.findCaseByCode(mainCaseCode));
				// 计算标签值集合
				Map<String, Set<String>> tagSet = this.produceRelatedCaseTagSet(relatedCases);
				
				ScoreComputeTaskImpl task = (ScoreComputeTaskImpl) scoreTaskService.findById(template.getComputeTaskId());
				ScoreComputeTaskForCaseListener taskListener = new ScoreComputeTaskForCaseListener(scoreResultService,
						caseService);
				ScorePointConfigForCaseListener pointListener = new ScorePointConfigForCaseListener(scoreResultService);
				task.setListener(taskListener);
				task.setComputeStrategy(new ComputeStrategyForCase());
				for (ScoreComputePointConfig scpc : task.getScorePointConfigs()) {
					scpc.setListener(pointListener);
				}

				Parameter pMainCase = new DefaultParameter(mainCaseCode, TASK_PARAMETER_MAINCASECODE);
				Parameter pTagset = new DefaultParameter(tagSet, TASK_PARAMETER_MAINCASETAGSET);
				Parameter pComparedCaseCode = new DefaultParameter(comparedCaseCode, TASK_PARAMETER_COMPAREDCASECODE);
				Parameter pTemplate = new DefaultParameter(template.getId(), TASK_PARAMETER_TEMPLATEID);
				LOGGER.debug("开始计算主案件-比对案件:{}-{}", mainCaseCode, comparedCaseCode);
				task.run(pMainCase, pTagset, pComparedCaseCode, pTemplate);
				LOGGER.debug("开始计算主案件-比对案件:{}-{}", mainCaseCode, comparedCaseCode);
				
			}
		} catch (Exception e) {
			throw new RuntimeException("主案件和比对案件分别为：" + mainCaseCode + "\t" + comparedCaseCode, e);
		}
		
	}
}
