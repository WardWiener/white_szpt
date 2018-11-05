package com.taiji.pubsec.scoreframework;

/**
 * 检查点配置接口。一个{@link ScoreComputeTask}可以有多个{@link ScoreComputePoint}，针对每个{@link ScoreComputePoint}都有不同的配置，如系数（weight）
 * @author yucy
 *
 */
public interface ScoreComputePointConfig extends ScoreComputeObject {

	ScoreComputePoint getScorePoint();
	
	public Listener getListener() ;
	public void setListener(Listener listener);
	
	public String getDescription() ;
	
	public Double getWeight() ;
	
	public interface Listener{
		void afterCompute(ScoreComputeTask task, ScoreComputePointConfig scorePointConfig, ScoreComputeResult<Double> resultBeforeWeight, ScoreComputeResult<Double> resultAfterWeight, Parameter...params);
	}
}
