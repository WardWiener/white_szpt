package com.taiji.pubsec.scoreframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;

@Service("scorePointService")
//@Transactional(rollbackFor = Throwable.class)
public class ScoreComputePointServiceImpl implements ScoreComputePointService {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public ScoreComputePoint findById(String id) {
		return (ScoreComputePointImpl)this.dao.findById(ScoreComputePointImpl.class, id) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(ScoreComputePoint entity) {
		this.dao.save(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(ScoreComputePoint entity) {
		this.dao.save(entity);
	}
}
