package com.taiji.pubsec.szpt.caseanalysis.score.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointConfigImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.rules.groovyrule.BasedGroovyRuleService;
import com.taiji.pubsec.scoreframework.rules.groovyrule.model.BasedGroovyRule;
import com.taiji.pubsec.scoreframework.service.ScoreComputePointConfigService;
import com.taiji.pubsec.scoreframework.service.ScoreComputePointService;
import com.taiji.pubsec.scoreframework.service.ScoreComputeRuleService;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.RobberyTheftCaseScoreTemplateRule;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarCommunityExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarEntranceExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarExitExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarFeatureExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarPeriodExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarPersonExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.executor.CaseScoreSimilarPlaceExecutor;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreTemplateService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.util.Constant;

@Service("scoreTemplateService")
public class ScoreTemplateServiceImpl implements ScoreTemplateService {

	private static Logger log = LoggerFactory
			.getLogger(ScoreTemplateServiceImpl.class);

	private static Map<String,Class> executorMap = new HashMap<String,Class>();
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	@Resource
	private ScoreComputeTaskService scoreTaskService;
	@Resource
	private ScoreComputePointService scorePointService;
	@Resource
	private ScoreComputePointConfigService scorePointConfigService;
	@Resource
	private BasedGroovyRuleService basedGroovyRuleService;
	@Resource
	private ScoreComputeRuleService scoreRuleService;

	@Resource
	private CaseTagService caseTagService;

	static {
		executorMap.put(CaseTag.KEY_CASECOMMUNITY, CaseScoreSimilarCommunityExecutor.class);
		executorMap.put(CaseTag.KEY_CASEENTRANCE, CaseScoreSimilarEntranceExecutor.class);
		executorMap.put(CaseTag.KEY_CASEEXIT, CaseScoreSimilarExitExecutor.class);
		executorMap.put(CaseTag.KEY_CASEFEATURE, CaseScoreSimilarFeatureExecutor.class);
		executorMap.put(CaseTag.KEY_CASEPERIOD, CaseScoreSimilarPeriodExecutor.class);
		executorMap.put(CaseTag.KEY_CASEPERSONCOUNT, CaseScoreSimilarPersonExecutor.class);
		executorMap.put(CaseTag.KEY_CASEPLACE, CaseScoreSimilarPlaceExecutor.class);
		
	}
	@Override
	public List<RobberyTheftCaseScoreTemplate> findAllTemplate() {
		return dao.findAll(RobberyTheftCaseScoreTemplate.class);
	}

	@Override
	public boolean createTemplate(RobberyTheftCaseScoreTemplate template,
			List<RobberyTheftCaseScoreTemplateRule> rules) {
		try {
			
			dao.save(template);
			// create task
			ScoreComputeTaskImpl task = new ScoreComputeTaskImpl();
			task.setId(template.getId());
			task.setDescription(template.getName());
			task.setWarnScore((double) template.getMinScore());
			scoreTaskService.save(task);

			for (RobberyTheftCaseScoreTemplateRule ruleConfig : rules) {
				//groovy rule
				BasedGroovyRule rule = produceGroovyRule(null, ruleConfig);
				rule.setValue(ruleConfig.getRule());
				basedGroovyRuleService.save(rule);
				log.debug("groovyrule id = {}", rule.getId());

				// point
				ScoreComputePointImpl scp = new ScoreComputePointImpl();
				scp.setDescription(ruleConfig.getItem());
				scp.setScoreExecutorType(executorMap.get(ruleConfig.getItem()).getName());
				scp.setScoreExecutorId(ruleConfig.getItem());
				scorePointService.save(scp);
				log.debug("scorepoint id = {}", scp.getId());
				//rule
				ScoreComputeRuleImpl scr = new ScoreComputeRuleImpl();
				scr.setType(BasedGroovyRule.class.getName());
				scr.setRuleId(rule.getId());
				scr.setScorePoint(scp);
				scr.setDescription(ruleConfig.getItem());
				scoreRuleService.save(scr);
				log.debug("proxyrule id = {}", scr.getId());
				//point config
				ScoreComputePointConfigImpl scpCfg = new ScoreComputePointConfigImpl();
				scpCfg.setScorePoint(scp);
				scpCfg.setWeight(Double.parseDouble(ruleConfig.getWeight()));
				scpCfg.setScoreTask(task);
				scpCfg.setDescription(ruleConfig.getItem());
				scorePointConfigService.save(scpCfg);
				log.debug("taskconfig id = {}", scpCfg.getId());
			}
			template.setComputeTaskId(task.getId());
			dao.update(template);
			return true;
		} catch (Exception e) {
			log.error("", e);
			return false;
		}

	}

	@Override
	public boolean updateTemplate(RobberyTheftCaseScoreTemplate template,
			Map<String, RobberyTheftCaseScoreTemplateRule> rules) {
		try {
			ScoreComputeTaskImpl task = (ScoreComputeTaskImpl) scoreTaskService.findById(template
					.getComputeTaskId());
			task.setDescription(template.getName());
			task.setWarnScore(new Double(template.getMinScore()));
			dao.update(task);
			// update point
			for (ScoreComputePointConfig taskConfig : task
					.getScorePointConfigs()) {
				ScoreComputePointImpl scp = (ScoreComputePointImpl) taskConfig
						.getScorePoint();
				RobberyTheftCaseScoreTemplateRule ruleConfig = rules.get(scp
						.getDescription());
				ScoreComputePointConfigImpl tcf = (ScoreComputePointConfigImpl) taskConfig;
				tcf.setWeight(Double.parseDouble(ruleConfig.getWeight()));
				scorePointConfigService.update(tcf);

				ScoreComputeRuleImpl scr = (ScoreComputeRuleImpl) scp
						.getScoreRules().toArray()[0];
				
				scoreRuleService.update(scr);
				BasedGroovyRule rule = produceGroovyRule(scr.getRuleId(),
						ruleConfig);
				rule.setValue(ruleConfig.getRule());
				basedGroovyRuleService.update(rule);
				log.debug("groovyrule id = {}", rule.getId());
			}
			dao.update(template);
			return true;
		} catch (Exception e) {
			log.error("", e);
			return false;
		}

	}

	private BasedGroovyRule produceGroovyRule(String id,
			RobberyTheftCaseScoreTemplateRule ruleConfig) {
		BasedGroovyRule rule;
		if (id == null) {
			rule = new BasedGroovyRule();
		} else {
			rule = basedGroovyRuleService.findById(id);
		}
		String[] values = ruleConfig.getRule().split(",");
		String scriptTemplate = "if(x0 >= 0 && x0 <= 0.25) return ${v0} else if(x0 > 0.25 && x0 <= 0.5 ) return ${v1} else if(x0 > 0.5 && x0 <= 0.75) return ${v2} else return ${v3}";
		String scriptTemplateForSimilarCommunity = "if(\"不相邻社区\"==x0) return ${v0} else if(\"相邻社区\"==x0) return ${v1} else if(\"相同社区\"==x0) return ${v2}";
		String script;
		if (ruleConfig.getItem().equals(CaseTag.KEY_CASECOMMUNITY)) {
			script = scriptTemplateForSimilarCommunity;
		} else {
			script = scriptTemplate;
		}
		for (int i = 0; i < values.length; i++) { // replace to produce groovy
													// script
			String key = "${v" + i + "}";
			script = script.replace(key, values[i]);
		}
		log.debug("script = {}", script);
		rule.setScript(script);
		return rule;
	}

	@Override
	public RobberyTheftCaseScoreTemplate findTemplate(String id) {
		RobberyTheftCaseScoreTemplate template = (RobberyTheftCaseScoreTemplate) dao
				.findById(RobberyTheftCaseScoreTemplate.class, id);
		template.setRules(getTemplateRules(template.getComputeTaskId()));
		return template;
	}

	private List<RobberyTheftCaseScoreTemplateRule> getTemplateRules(
			String taskId) {
		List<RobberyTheftCaseScoreTemplateRule> rules = new ArrayList<RobberyTheftCaseScoreTemplateRule>();
		ScoreComputeTaskImpl task = (ScoreComputeTaskImpl) scoreTaskService.findById(taskId);
		if (task != null) {
			for (ScoreComputePointConfig taskConfig : task
					.getScorePointConfigs()) {
				ScoreComputePointImpl scp = (ScoreComputePointImpl) taskConfig
						.getScorePoint();
				RobberyTheftCaseScoreTemplateRule rule = new RobberyTheftCaseScoreTemplateRule();
				rule.setItem(scp.getDescription());
				rule.setWeight(((ScoreComputePointConfigImpl) taskConfig)
						.getWeight().toString());
				ScoreComputeRuleImpl scr = (ScoreComputeRuleImpl) scp
						.getScoreRules().toArray()[0];
				if(scr.getType().equals(BasedGroovyRule.class.getName())) {
				    rule.setRule(basedGroovyRuleService.findById(scr.getRuleId()).getValue());
				}
				rules.add(rule);
			}
		}
		return rules;
	}

	@Override
	public RobberyTheftCaseScoreTemplate findTemplate(String type,
			String firstSort, String secondSort) {
		StringBuffer hql = new StringBuffer(
				"from RobberyTheftCaseScoreTemplate where type = ? and firstSort = ? and state = ?");
		List params = new ArrayList();
		params.add(type);
		params.add(firstSort);
		params.add(Constant.DICT.DICT_ENABLED.getValue());
		if (StringUtils.isBlank(secondSort)) {
			hql.append(" and secondSort is null");
		} else {
			hql.append(" and secondSort = ?");
			params.add(secondSort);
		}
		return (RobberyTheftCaseScoreTemplate) dao.findByParams(
				RobberyTheftCaseScoreTemplate.class, hql.toString(),
				params.toArray());

	}

	@Override
	public boolean hasSameCodeTemplate(String code) {
		String hql = "from RobberyTheftCaseScoreTemplate where code = ?";
		RobberyTheftCaseScoreTemplate template = (RobberyTheftCaseScoreTemplate) dao
				.findByParams(RobberyTheftCaseScoreTemplate.class, hql,
						new Object[] { code });
		if (template == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean hasSameNameTemplate(String name) {
		String hql = "from RobberyTheftCaseScoreTemplate where name = ?";
		RobberyTheftCaseScoreTemplate template = (RobberyTheftCaseScoreTemplate) dao
				.findByParams(RobberyTheftCaseScoreTemplate.class, hql,
						new Object[] { name });
		if (template == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean enableDeleted(String id) {
		String hql = "from CaseScoreResult where templateId = ?";
		CaseScoreResult result = (CaseScoreResult) dao.findByParams(
				RobberyTheftCaseScoreTemplate.class, hql, new Object[] { id });
		if (result == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteTemplete(String id) {
		RobberyTheftCaseScoreTemplate template = (RobberyTheftCaseScoreTemplate) dao.findById(RobberyTheftCaseScoreTemplate.class, id);
		scoreTaskService.destroyComputeTask(template.getComputeTaskId());
		dao.delete(RobberyTheftCaseScoreTemplate.class, id);
//		dao.flush();
	}

	@Override
	public boolean updateTemplateState(String id, String state) {
		RobberyTheftCaseScoreTemplate template = (RobberyTheftCaseScoreTemplate) dao.findById(RobberyTheftCaseScoreTemplate.class, id);
		if(template != null){
			template.setState(state);
			dao.update(template);
			return true;
		}
		return false;
	}

	public RobberyTheftCaseScoreTemplate findMatchedTemplate(String caseCode) {
		CaseTag tag = caseTagService.findCaseTag(caseCode);
		if(tag == null){
			return null;
		}
		String caseType = tag.getType();
		String caseFirstSort = tag.getFirstSort();
		String caseSecondSort = tag.getSecondSort();
		RobberyTheftCaseScoreTemplate template = this.findTemplate(caseType, caseFirstSort, caseSecondSort);
		if(template != null) {
			return template;
		} else {
			if(caseSecondSort != null) {
				template = this.findTemplate(caseType, caseFirstSort, null);
			}
			return template;
		}
	}
}
