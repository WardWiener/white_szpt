package com.taiji.pubsec.szpt.score.util.hrp.service;

import com.taiji.pubsec.szpt.score.util.hrp.pojo.ScoreRule;

public interface HrpScoreComputeTaskUtilService {

	/**
	 * 删除并重新建立任务
	 * @param ruleInfo
	 */
	public void renewHrpComputeTask(ScoreRule ruleInfo) ;
}
