package com.taiji.pubsec.szpt.score.util.hrp.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointConfigImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.hrp.busdata.HrpRuleParser;
import com.taiji.pubsec.szpt.score.util.hrp.busdata.executor.BasicHrpScoreExecutor;
import com.taiji.pubsec.szpt.score.util.hrp.busdata.executor.HrpPersonTypeScoreExecutor;
import com.taiji.pubsec.szpt.score.util.hrp.busdata.executor.HrpPlaceRecordScoreExecutor;
import com.taiji.pubsec.szpt.score.util.hrp.busdata.executor.HrpTripRecordScoreExecutor;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.ScorePointInfo;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.ScoreRule;
import com.taiji.pubsec.szpt.score.util.hrp.service.HrpScoreComputeTaskUtilService;
import com.taiji.pubsec.szpt.score.util.rules.groovy.model.SzptScoreGroovyRule;
import net.sf.json.JSONObject;

@Service
public class HrpScoreComputeTaskUtilServiceImpl implements HrpScoreComputeTaskUtilService{
	
	private static Logger LOGGER = LoggerFactory.getLogger(HrpScoreComputeTaskUtilServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;
	
	@Resource
	private ScoreComputeTaskService scoreComputeTaskService ;

	@SuppressWarnings("unchecked")
	@Override
	public void renewHrpComputeTask(ScoreRule ruleInfo) {
		
		this.scoreComputeTaskService.destroyComputeTask(com.taiji.pubsec.szpt.score.util.Constant.SC_TASK_ID_HRP);

		ScoreComputeTaskImpl task = new ScoreComputeTaskImpl() ;
		task.setId(Constant.SC_TASK_ID_HRP);
		task.setWarnScore(Double.valueOf(ruleInfo.getAlertPoint().toString()));
		
		this.dao.save(task);
		
		saveAllPoints(task, ruleInfo) ;
		
		this.dao.flush();
		
		/**
		 * 需要刷新hibernate缓存里的状态
		 * 也可以
		 * this.dao.flush() ;
		 * this.dao.refresh(task) ;
		 * 业务端一般都是直接findById去查task所以task的状态不需要刷新，但是point和config没刷新的话，刷新的task里get出来的point和config还是没刷新状态的，所以在下面的private方法里要刷新一下
		 */
	}

	protected void saveAllPoints(ScoreComputeTaskImpl task, ScoreRule ruleInfo){
		List<ScorePointInfo> cpInfos = HrpRuleParser.parser(ruleInfo.getRuleDetails()) ;
		for(ScorePointInfo cpInfo:cpInfos){
			ScoreComputePointImpl point = saveScoreComputePoint(cpInfo, task) ;
			savePointConfig(cpInfo, point, task) ;
			saveRules(cpInfo.getRuleDetails(), point) ;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void saveRules(List<RuleDetail> ruleDetails, ScoreComputePointImpl point){
		String script = getGroovyRuleStr(ruleDetails, point) ;
		SzptScoreGroovyRule rule = new SzptScoreGroovyRule() ;
		rule.setScript(script);
		this.dao.save(rule);
		rule.setDescription(point.getDescription());
		
		ScoreComputeRuleImpl sr = new ScoreComputeRuleImpl() ;
		sr.setRuleId(rule.getId());
		sr.setDescription(point.getDescription());
		sr.setType(SzptScoreGroovyRule.class.getName());
		sr.setScorePoint(point);
		
		this.dao.save(sr);
		
		/**
		 * 需要刷新hibernate缓存里的状态
		 * 也可以
		 * this.dao.flush() ;
		 * this.dao.refresh(point) ;
		 */
		if(sr.getId()!=null){
			point.getScoreRules().add(sr);
		}
	}
	
	protected String getGroovyRuleStr(List<RuleDetail> ruleDetails, ScoreComputePointImpl point){
		
		Map<String, RuleDetail> map = new HashMap<String, RuleDetail>();
		
		for(RuleDetail rd:ruleDetails){
			map.put(rd.getKey(), rd) ;
		}
		
		JSONObject obj = JSONObject.fromObject(map) ;
		String rdstr = "def rulesMap = "+obj.toString()+" ;" ;
		rdstr = rdstr.replaceAll("[{]", "[");
		rdstr = rdstr.replaceAll("[}]", "]");
		StringBuffer sb = new StringBuffer(rdstr) ;

		sb.append("\r\n") ;
		sb.append("\r\n") ;
		
		String script = getGroovyScriptString(point);
		
		String[] scriptArr = script.split("//规则变量和计算脚本的分割线") ;
		
		sb.append("//规则变量和计算脚本的分割线\r\n") ;
		
		sb.append(scriptArr[scriptArr.length-1]) ;
		
		String result = sb.toString();
		
		LOGGER.debug("生成规则脚本:\r\n" + result);
		
		return result;
	}
	
	protected String getGroovyScriptString(ScoreComputePointImpl point){

		String path = determineScriptPath(point) ;
		InputStream in = HrpScoreComputeTaskUtilServiceImpl.class.getClassLoader().getResourceAsStream(path);
		
		try{
			String script = FileFmtUtils.readByBufferedLinesToStr(in); 
		
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
	
	protected String determineScriptPath(ScoreComputePointImpl point){
		String path = "" ;
		if(point.getDescription().equals(RuleDetail.SCORE_POINT_PERSON_TYPE)){
			path = "groovyrule/hrp/hrpPersonTypeTemplate.groovy" ;
		}else if(point.getDescription().contains(RuleDetail.SCORE_POINT_PLACE_RECORD)){
			path = "groovyrule/hrp/hrpPlaceRecordTemplate.groovy" ;
		}else if(point.getDescription().contains(RuleDetail.SCORE_POINT_TRIP_RECORD)){
			path = "groovyrule/hrp/hrpTripRecordTemplate.groovy" ;
		}else if(point.getDescription().contains(RuleDetail.SCORE_POINT_INPORTANT_CONTROL_TYPE)){
			path = "groovyrule/hrp/hrpImportantControlTypeTemplate.groovy" ;
		}else if(point.getDescription().contains(RuleDetail.SCORE_POINT_EMPLOYMENT_STATUS)){
			path = "groovyrule/hrp/hrpEmploymentStatusTemplate.groovy" ;
		}else if(point.getDescription().contains(RuleDetail.SCORE_POINT_MARRIAGE_STATUS)){
			path = "groovyrule/hrp/hrpMarriageStatusTemplate.groovy" ;
		}else if(point.getDescription().contains(RuleDetail.SCORE_POINT_INCOME)){
			path = "groovyrule/hrp/hrpIncomeTemplate.groovy" ;
		}
		
		if(StringUtils.isBlank(path)){
			LOGGER.error("描述为：{}的ScoreComputePointImpl找不到对应的groovy脚本路径", point.getDescription());
			throw new RuntimeException("描述为："+point.getDescription()+"的ScoreComputePointImpl找不到对应的groovy脚本路径") ;
		}
		
		return path ;
	}
	
	@SuppressWarnings("unchecked")
	protected ScoreComputePointImpl saveScoreComputePoint(ScorePointInfo cpInfo, ScoreComputeTaskImpl task){
		
		ScoreComputePointImpl point = new ScoreComputePointImpl() ;
		point.setDescription(cpInfo.getKey());
		
		if(cpInfo.getKey().contains(RuleDetail.SCORE_POINT_PERSON_TYPE)){
			point.setScoreExecutorType(HrpPersonTypeScoreExecutor.class.getName());
		}else if(cpInfo.getKey().contains(RuleDetail.SCORE_POINT_PLACE_RECORD)){
			point.setScoreExecutorType(HrpPlaceRecordScoreExecutor.class.getName());
		}else if(cpInfo.getKey().contains(RuleDetail.SCORE_POINT_TRIP_RECORD)){
			point.setScoreExecutorType(HrpTripRecordScoreExecutor.class.getName());
		}else{
			point.setScoreExecutorType(BasicHrpScoreExecutor.class.getName());
		}
		
		this.dao.save(point);
		
		return point ;
	}
	
	@SuppressWarnings("unchecked")
	protected ScoreComputePointConfigImpl savePointConfig(ScorePointInfo cpInfo, ScoreComputePointImpl point, ScoreComputeTaskImpl task){
		
		ScoreComputePointConfigImpl config = new ScoreComputePointConfigImpl() ;
		config.setScorePoint(point);
		config.setScoreTask(task);
		config.setWeight(cpInfo.getWeight());
		config.setDescription(cpInfo.getKey());

		this.dao.save(config);
		
		/**
		 * 需要刷新hibernate缓存里的状态
		 * 也可以
		 * this.dao.flush() ;
		 * this.dao.refresh(config) ;
		 */
		if(config.getId()!=null){
			task.getScorePointConfigs().add(config);
		}
		
		return config ;
	}
}
