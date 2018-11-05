package com.taiji.pubsec.szpt.ptjc.service;

import java.util.List;



import com.taiji.pubsec.szpt.ptjc.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.ptjc.model.DistributionRule;
import com.taiji.pubsec.szpt.ptjc.model.PenalConstant;

public interface DistributionRuleService {
	
	/**
	 * 查询所有派出所的分布常量预警值
	 * @param type 警情类别
	 * @return 封装后的AlertThresholdInfo集合
	 */
	public List<AlertThresholdInfo> findPcsFenbuAlertThresholdInfosByType(String type) ;
	
	
	/**
	 * 通过target和警情类型查询分布规则
	 * @param targetId 目标id
	 * @param targetType 目标类型
	 * @param alarmTypeCode 警情类型code
	 * @return
	 */
	public List<DistributionRule> findDistributionRuleByTargetAndAlarmTypeCode(List<String> targetIdLst, String targetType, String alarmTypeCode);
	
	/**
	 * 通过target和警情类型删除分布规则
	 * @param targetId 目标id
	 * @param targetType 目标类型
	 * @param alarmTypeCode 警情类型code
	 * @return
	 */
	public void deleteDistributionRuleByTargetAndAlarmTypeCode(List<String> targetIdLst, String targetType, String alarmTypeCode);
	
	/**
	 * 保存分布规则
	 * @return
	 */
	public void saveDistributionRule(DistributionRule entity);
}
