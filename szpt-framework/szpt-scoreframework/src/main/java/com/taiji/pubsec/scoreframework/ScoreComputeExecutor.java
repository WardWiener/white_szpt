package com.taiji.pubsec.scoreframework;

import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;

/**
 * 实现这个接口，用于处理业务相关逻辑，将结果返回给{@link ScoreComputePoint}}处理.
 * 见{@link ScoreComputePointImpl}以及{@link ScoreComputeTaskImpl}
 * @author yucy
 *
 */
public interface ScoreComputeExecutor extends ScoreComputeObject {
	Object[] excute(Parameter...params) ;
}
