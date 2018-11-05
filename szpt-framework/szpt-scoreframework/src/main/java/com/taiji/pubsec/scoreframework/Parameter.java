package com.taiji.pubsec.scoreframework;

/**
 * {@link ScoreComputeTask}在run方法中传入的参数。
 * 其实现可以包装各种类型的参数，并通过support方法来测试是否支持某个任务、检查点使用，getTag方法可以用来区分同一种参数的不同业务意义，如：
 * getTag返回X或者Y
 * @author yucy
 *
 */
public interface Parameter {

	boolean support(ScoreComputeObject scoreObject);
	String getTag();
	Object getParameter();
}
