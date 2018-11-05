package com.taiji.pubsec.scoreframework;

import java.util.Map;

import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;

/**
 * 计算策略接口。
 * {@link ScoreComputeTask}在对其子{@link ScoreComputeTask}、{@link ScoreComputePoint}的返回结果计算总分时的计算策略。
 * 也见{@link ScoreComputeTaskImpl}
 * @author yucy
 *
 */
public interface ComputeStrategy {

	ScoreComputeResult<?> compute(Map<ScoreComputeObject, ScoreComputeResult<?>> map, Parameter...params);
}
