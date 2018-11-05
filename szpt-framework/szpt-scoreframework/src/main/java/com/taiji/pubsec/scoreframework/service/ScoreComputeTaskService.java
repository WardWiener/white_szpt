package com.taiji.pubsec.scoreframework.service;

import com.taiji.pubsec.scoreframework.ScoreComputeTask;

public interface ScoreComputeTaskService{
	
	/**
	 * 删除任务和任务相关的所有point、pointconfig、rule
	 * @param taskId 任务id
	 */
	public void destroyComputeTask(String taskId) ;

	public ScoreComputeTask findById(String id) ;
	
	public void save(ScoreComputeTask entity) ;
	
	public void saveOrUpdate(ScoreComputeTask entity) ;
}
