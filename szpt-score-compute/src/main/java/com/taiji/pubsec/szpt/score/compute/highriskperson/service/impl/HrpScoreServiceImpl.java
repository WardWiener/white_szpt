package com.taiji.pubsec.szpt.score.compute.highriskperson.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.annotation.AopAnno;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;
import com.taiji.pubsec.szpt.score.compute.highriskperson.aop.HrpScoreProcessMonitorHandler;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScoreForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScorePointConfigForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScorePointForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScoreRuleForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScoreForHrp;
import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScorePointConfigForHrp;
import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScorePointForHrp;
import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScoreRuleForHrp;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;

@Service
public class HrpScoreServiceImpl implements HrpScoreService{
	
	private SaveOrUpdateResultOfScoreForHrpListener saveOrUpdateResultOfScoreForHrpListener = null;
	private SaveOrUpdateResultOfScorePointForHrpListener saveOrUpdateResultOfScorePointForHrpListener = null;
	private SaveOrUpdateResultOfScoreRuleForHrpListener saveOrUpdateResultOfScoreRuleForHrpListener = null;
	private SaveOrUpdateResultOfScorePointConfigForHrpListener saveOrUpdateResultOfScorePointConfigForHrpListener = null;
	
	@Resource
	private HrpScoreService hrpScoreService;
	@Resource
	private ComputeProcessService computeProcessService;
	@Resource
	protected ScoreComputeTaskService taskService;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public String saveOrUpdateResultOfScoreForHrp(String hrpId, String scoreTaskId, Double score) {
		ResultOfScoreForHrp entity = findResultOfScoreForHrpByHrpId(hrpId) ;
		if(entity==null){
			entity = new ResultOfScoreForHrp() ;
		}
		entity.setHrpId(hrpId);
		entity.setScoreTaskId(scoreTaskId);
		entity.setScore(score);
		this.dao.saveOrUpdate(entity);
		
		return entity.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateResultOfScorePointForHrp(String hrpId, String scorePointId, String description, Double score) {
		ResultOfScorePointForHrp entity = findResultOfScorePointForHrpByHrpId(hrpId, scorePointId) ;
		if(entity==null){
			entity = new ResultOfScorePointForHrp() ;
		}
		entity.setHrpId(hrpId);
		entity.setScore(score);
		entity.setScorePointId(scorePointId);
		entity.setDescription(description);
		this.dao.saveOrUpdate(entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateResultOfScorePointConfigForHrp(String hrpId, String scorePointConfigId, String scoreTaskId,
			String scorePointId, String description, Double weight, Double scoreBeforeWeight,
			Double scoreAfterWeight) {
		ResultOfScorePointConfigForHrp entity = findResultOfScorePointConfigForHrpByHrpId(hrpId, scorePointConfigId) ;
		if(entity==null){
			entity = new ResultOfScorePointConfigForHrp() ;
		}
		entity.setHrpId(hrpId);
		entity.setScorePointConfigId(scorePointConfigId);
		entity.setScoreTaskId(scoreTaskId);
		entity.setScorePointId(scorePointId);
		entity.setDescription(description);
		entity.setScoreAfterWeight(scoreAfterWeight);
		entity.setScoreBeforeWeight(scoreBeforeWeight);
		entity.setWeight(weight);
		this.dao.saveOrUpdate(entity);
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateResultOfScoreRuleForHrp(String hrpId, String scorePointId, String scoreRuleId, String input, String otherResults, String description,
			Double score) {
		ResultOfScoreRuleForHrp entity = findResultOfScoreRuleForHrpByHrpId(hrpId, scoreRuleId) ;
		if(entity==null){
			entity = new ResultOfScoreRuleForHrp() ;
		}
		entity.setHrpId(hrpId);
		entity.setScore(score);
		entity.setScorePointId(scorePointId);
		entity.setScoreRuleId(scoreRuleId);
		entity.setDescription(description);
		entity.setInput(input);
		entity.setOtherResults(otherResults);
		this.dao.saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	private ResultOfScoreForHrp findResultOfScoreForHrpByHrpId(String hrpId){
		String xql = "select h from ResultOfScoreForHrp as h where h.hrpId=:hrpId" ;
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("hrpId", hrpId);
		List<ResultOfScoreForHrp> list = this.dao.findAllByParams(ResultOfScoreForHrp.class, xql, xqlMap) ;
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private ResultOfScorePointConfigForHrp findResultOfScorePointConfigForHrpByHrpId(String hrpId, String scorePointConfigId){
		String xql = "select h from ResultOfScorePointConfigForHrp as h where h.hrpId=:hrpId and h.scorePointConfigId=:scorePointConfigId" ;
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("hrpId", hrpId);
		xqlMap.put("scorePointConfigId", scorePointConfigId);
		List<ResultOfScorePointConfigForHrp> list = this.dao.findAllByParams(ResultOfScorePointConfigForHrp.class, xql, xqlMap) ;
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private ResultOfScorePointForHrp findResultOfScorePointForHrpByHrpId(String hrpId, String scorePointId){
		String xql = "select h from ResultOfScorePointForHrp as h where h.hrpId=:hrpId and h.scorePointId=:scorePointId" ;
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("hrpId", hrpId);
		xqlMap.put("scorePointId", scorePointId);
		List<ResultOfScorePointForHrp> list = this.dao.findAllByParams(ResultOfScorePointForHrp.class, xql, xqlMap) ;
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private ResultOfScoreRuleForHrp findResultOfScoreRuleForHrpByHrpId(String hrpId, String scoreRuleId){
		String xql = "select h from ResultOfScoreRuleForHrp as h where h.hrpId=:hrpId and h.scoreRuleId=:scoreRuleId" ;
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("hrpId", hrpId);
		xqlMap.put("scoreRuleId", scoreRuleId);
		List<ResultOfScoreRuleForHrp> list = this.dao.findAllByParams(ResultOfScoreRuleForHrp.class, xql, xqlMap) ;
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@AopAnno(mark=HrpScoreProcessMonitorHandler.MARK, aopAnnoObj="new Object[]{#arg0}")
	@Override
	public void processHrpScoreComputeReceiverMsg(ScoreComputeMsg msg) {
		// TODO Auto-generated method stub
		Serializable businessobj = msg.getScoreComputeObj();
		String computprocessid = msg.getComputeProcessId();
		
		ScoreComputeTask task = taskService.findById(Constant.SC_TASK_ID_HRP);
		
		ifNotThenInitListeners();
		
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
		
		String hrpid = (String)businessobj;
		task.run(new DefaultParameter(hrpid, Constant.PARAMETER_TAG_HRP_ID));
	}

	private void ifNotThenInitListeners(){
		if( null == saveOrUpdateResultOfScoreForHrpListener)
			saveOrUpdateResultOfScoreForHrpListener = new SaveOrUpdateResultOfScoreForHrpListener(hrpScoreService, computeProcessService);
		if( null == saveOrUpdateResultOfScorePointForHrpListener)
			saveOrUpdateResultOfScorePointForHrpListener = new SaveOrUpdateResultOfScorePointForHrpListener(hrpScoreService);
		if( null == saveOrUpdateResultOfScoreRuleForHrpListener)
			saveOrUpdateResultOfScoreRuleForHrpListener = new SaveOrUpdateResultOfScoreRuleForHrpListener(hrpScoreService);
		if( null == saveOrUpdateResultOfScorePointConfigForHrpListener)
			saveOrUpdateResultOfScorePointConfigForHrpListener = new SaveOrUpdateResultOfScorePointConfigForHrpListener(hrpScoreService);

	}

}