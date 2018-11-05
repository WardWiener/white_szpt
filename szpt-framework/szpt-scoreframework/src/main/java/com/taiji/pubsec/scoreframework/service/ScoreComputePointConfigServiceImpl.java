package com.taiji.pubsec.scoreframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointConfigImpl;

@Service("scorePointConfigService")
//@Transactional(rollbackFor = Throwable.class)
public class ScoreComputePointConfigServiceImpl implements ScoreComputePointConfigService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public ScoreComputePointConfig findById(String id) {
		return (ScoreComputePointConfigImpl)this.dao.findById(ScoreComputePointConfigImpl.class, id) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(ScoreComputePointConfig entity) {
		this.dao.save(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(ScoreComputePointConfig entity) {
		this.dao.save(entity);
	}
	
	
}
