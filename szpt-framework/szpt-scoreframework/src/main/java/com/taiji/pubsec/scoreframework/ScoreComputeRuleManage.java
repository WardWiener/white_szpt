package com.taiji.pubsec.scoreframework;

/**
 * {@link ScoreComputeRule}管理器。通过类型type和标识id获得{@link ScoreComputeRule}的实例对象。
 * 也见{@link ScoreComputeRuleManageImpl}
 * @author yucy
 *
 */
public interface ScoreComputeRuleManage {
	ScoreComputeRule get(String type, String id);
}
