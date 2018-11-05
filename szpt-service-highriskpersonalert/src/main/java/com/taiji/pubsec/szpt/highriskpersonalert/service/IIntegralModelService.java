package com.taiji.pubsec.szpt.highriskpersonalert.service;


import java.util.List;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModel;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModelRule;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.IntegralModelBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.IntegralModelRuleBean;


/**
 * 人员积分模型
 * @author sunjd
 *
 */
public interface IIntegralModelService {
	
	/**
	 * 查询当前启用的模板
	 * @return	人员积分模型
	 */
	public IntegralModel findIntegralModelStatusOn() ;
	
	/**
	 * 新增人员积分模型，同时新增积分规则
	 * @param integarlModelBean	人员积分模型Bean
	 * @param integralModelRuleBeanList 积分规则list
	 */
	public IntegralModel createIntegralModel(IntegralModelBean integarlModelBean , List<IntegralModelRuleBean> integralModelRuleBeanList);
	
	/**
	 * 修改人员积分模型，同时修改积分规则
	 * @param integarlModelBean	人员积分模型Bean
	 * @param integralModelRuleBeanList 积分规则list
	 */
	public IntegralModel updateIntegralModel(IntegralModelBean integarlModelBean , List<IntegralModelRuleBean> integralModelRuleBeanList);
	
	/**
	 * 更新积分模型状态
	 * @param id
	 * @param status
	 */
	public void updateModelStatus(String id, String status) ;
	
	/**
	 * 根据id查询积分模型
	 * @param id	id
	 * @return	模型
	 */
	public IntegralModel findIntegarlModelById(String id);
	
	/**
	 * 删除积分模型
	 * @param id
	 */
	public void deleteIntegarlModel(String id);
	
	/**
	 * 分页积分模型
	 * @return
	 */
	public Pager<IntegralModel> findAllIntegarlModel(int pageNo, int pageSize);
	
	/**
	 * 保存人员积分模型规则
	 * @param integralModelRule
	 */
	public void saveIntegarlModelRule(IntegralModelRule integralModelRule);
	
	/**
	 * 根据状态查询模板
	 * @param state	状态
	 * @return	人员积分模型List
	 */
	public List<IntegralModel> findIntegarlModelByState(String state);
	
	/**
	 * 删除人员积分模型规则
	 * @param id
	 */
	public void deleteIntegralModelRule(String id);
	
	/**
	 * 根据人员积分模型规则id查询规则
	 * @param integralModelId	人员积分模型规则id
	 * @return 人员积分模型规则集合
	 */
	public List<IntegralModelRule> findIntegralModelRuleByIntegralModelId(String integralModelId);
	
	/**
	 * 查询最大编号
	 * @return
	 */
	public String findMaxNum();
}
