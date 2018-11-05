package com.taiji.pubsec.szpt.score.compute.highriskperson.service;

import java.util.List;

import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScoreForHrp;
import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScorePointForHrp;
import com.taiji.pubsec.szpt.score.compute.highriskperson.model.ResultOfScoreRuleForHrp;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;

/**
 * @author yucy
 *
 */
public interface HrpScoreService {
	
	public void processHrpScoreComputeReceiverMsg(ScoreComputeMsg msg) ;

	/**
	 * 保存或更新一个高危人的得分
	 * 
	 * @param hrpid 高危人id
	 * @param score 高危人得分
	 */
	String saveOrUpdateResultOfScoreForHrp(String hrpId, String scoreTaskId, Double score);
	
	/**
	 * 保存或更新一个高危人得分点的详情
	 * 
	 * @param hrpid 高危人id
	 * @param scorepointid 得分点id
	 * @param score 得分点得分
	 */
	void saveOrUpdateResultOfScorePointForHrp(String hrpId, String scorePointId, String description, Double score);

	/**
	 * 保存或更新一个高危人得分点配置计算的详情
	 * @param hrpId 高危人id
	 * @param scorePointConfigId 积分点配置id
	 * @param weight 权重
	 * @param scoreBeforeWeight 乘以权重之前的得分
	 * @param scoreAfterWeight 乘以权重之后的得分
	 */
	void saveOrUpdateResultOfScorePointConfigForHrp(String hrpId, String scorePointConfigId, String scoreTaskId, String scorePointId, String description, Double weight, Double scoreBeforeWeight, Double scoreAfterWeight);
	
	/**
	 * 保存或更新一个高危人一个规则得分详情
	 * 
	 * @param hrpid 高危人id
	 * @param scorepointid 得分点id
	 * @param scoreruleid 规则id
	 * @param input 参与规则计算的输入
	 * @param score 规则得分
	 */
	void saveOrUpdateResultOfScoreRuleForHrp(String hrpId, String scorePointId, String scoreRuleId, String input, String otherResults, String description, Double score);

}
