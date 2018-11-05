package com.taiji.pubsec.scoreframework;
/**
 * 检查规则，通过process方法获得规则处理之后的检查结果（如得分）
 * @author yucy
 *
 */
public interface ScoreComputeRule extends ScoreComputeObject {
	
	public String getId();
	
	String getDescription();
	ScoreComputeResult<?> process(Object... params);

}
