package com.taiji.pubsec.scoreframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;

@Service("scoreRuleService")
//@Transactional(rollbackFor = Throwable.class)
public class ScoreComputeRuleServiceImpl implements ScoreComputeRuleService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public void save(ScoreComputeRule entity) {
		this.dao.save(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(ScoreComputeRule entity) {
		this.dao.save(entity);
	}
	
	

}
