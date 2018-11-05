package com.taiji.pubsec.scoreframework.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeRuleManage;
import com.taiji.pubsec.scoreframework.ScoreComputeRuleManageImpl;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;

@Service("scoreTaskService")
public class ScoreComputeTaskServiceImpl implements ScoreComputeTaskService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public ScoreComputeTask findById(String id) {
		return (ScoreComputeTaskImpl)this.dao.findById(ScoreComputeTaskImpl.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(ScoreComputeTask entity) {
		this.dao.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdate(ScoreComputeTask entity) {
		this.dao.saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void destroyComputeTask(String taskId) {
		
		ScoreComputeTaskImpl task = (ScoreComputeTaskImpl)this.dao.findById(ScoreComputeTaskImpl.class, taskId) ;
		
		if(task==null){
			return ;
		}
		
		removeAllRules(task) ;
		removeAllPointsAndConfigs(task) ;
		
		this.dao.delete(task); 
	}

	
	@SuppressWarnings("unchecked")
	private void removeAllRules(ScoreComputeTaskImpl task){
		
		ScoreComputeRuleManage scoreRuleManage = ScoreComputeRuleManageImpl.getInstance();
		
		for(ScoreComputePointConfig config:task.getScorePointConfigsAsArrayList()){
			ScoreComputePoint point = config.getScorePoint() ;
			Set<ScoreComputeRule> rulesSet = point.getScoreRules() ;
			Iterator<ScoreComputeRule> it = rulesSet.iterator() ;
			while (it.hasNext()) {
				ScoreComputeRuleImpl rule = (ScoreComputeRuleImpl)it.next() ;
				ScoreComputeRule cr = scoreRuleManage.get(rule.getType(), rule.getRuleId());
				this.dao.delete(cr);
				this.dao.delete(rule);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void removeAllPointsAndConfigs(ScoreComputeTaskImpl task){
		
		for(ScoreComputePointConfig config:task.getScorePointConfigsAsArrayList()){
			ScoreComputePoint point = config.getScorePoint() ;
			this.dao.delete(config);
			this.dao.delete(point);
		}
	}

	
	
}
