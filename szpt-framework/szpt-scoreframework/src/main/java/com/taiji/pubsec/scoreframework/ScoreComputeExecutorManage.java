package com.taiji.pubsec.scoreframework;

/**
 * {@link ScoreComputeExecutor}管理器。通过类型type和标识id获得{@link ScoreComputeExecutor}的实例对象。
 * 也见{@link ScoreComputeExecutorManageImpl}
 * @author yucy
 *
 */
public interface ScoreComputeExecutorManage {
	ScoreComputeExecutor get(String type, String id);
}
