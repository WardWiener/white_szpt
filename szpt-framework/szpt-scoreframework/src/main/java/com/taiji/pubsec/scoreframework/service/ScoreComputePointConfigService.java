package com.taiji.pubsec.scoreframework.service;

import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;

public interface ScoreComputePointConfigService{

	public ScoreComputePointConfig findById(String id) ;
	
	public void save(ScoreComputePointConfig entity) ;
	
	public void update(ScoreComputePointConfig entity) ;
}
