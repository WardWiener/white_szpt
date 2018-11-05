package com.taiji.pubsec.scoreframework.monitor.service;

import com.taiji.pubsec.scoreframework.monitor.model.ComputeProcess;
import com.taiji.pubsec.scoreframework.monitor.model.FailItem;

public interface ComputeProcessService {

	/**
	 * 创建一个计算过程记录
	 * 
	 * @param computeType 积分计算业务类型，如高危人、串并案、场所、嫌疑人、事件等
	 * @param totalnum 本次计算的总任务数
	 * @return 返回计算过程记录的id
	 */
	public String createComputeProcess(ComputeProcess computeProcess);
	
	public void updateComputeProcess(ComputeProcess computeProcess) ;
	
	/**
	 * 创建一条失败记录
	 * 
	 * @param computeprocessid 积分计算过程的id
	 * @param businessitem 失败的业务对象
	 */
	public String createFailItem(FailItem failItem);
	
	/**
	 * 给已经执行完成数加一
	 * 
	 * @param computeprocessid 积分计算过程的id
	 */
	public void increaseCompleteNumByOne(String computeprocessid);
	
	/**
	 * 通过id获得计算过程对象
	 * 
	 * @param id
	 * @return
	 */
	public ComputeProcess getComputeProcess(String id);
	
}
