package com.taiji.pubsec.scoreframework.service;

import com.taiji.pubsec.scoreframework.ScoreComputePoint;

public interface ScoreComputePointService{

	public ScoreComputePoint findById(String id) ;
	
	public void save(ScoreComputePoint entity) ;
	
	public void update(ScoreComputePoint entity) ;
}
