package com.taiji.pubsec.scoreframework;

import java.util.Set;

public interface ScoreComputeTask extends ScoreComputeObject {
	String getDescription();
	ScoreComputeTask getParent();
	Set<ScoreComputeTask> getChildren();
	ScoreComputeResult<?> run(Parameter...params);
	
	public void setListener(Listener listener);
	public Set<ScoreComputePointConfig> getScorePointConfigs() ;
	
	public interface Listener{
		void afterRun(ScoreComputeTask task, ScoreComputeResult<Double> result, Parameter...params);
	}
}
