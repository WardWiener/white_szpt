package com.taiji.pubsec.scoreframework;

import java.util.Set;

public interface ScoreComputePoint extends ScoreComputeObject {

	ScoreComputeResult<?> compute(ScoreComputeTask task, Parameter...params);
	
	public void setListener(Listener listener) ;
	
	public Set<ScoreComputeRule> getScoreRules() ;
	
	public String getDescription() ;
	
	public interface Listener{
		void afterCompute(ScoreComputeTask task, ScoreComputePoint scorePoint, ScoreComputeResult<Double> result, Parameter...params);
	}
}
