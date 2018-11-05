package com.taiji.pubsec.szpt.caseanalysis.score.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.caseanalysis.score.bean.RobberyTheftCaseScoreTemplateRule;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;

/**
 * 案件积分模板维护服务接口。
 * 
 * @author dixiaofeng
 */
public interface ScoreTemplateService {

	/**
	 * 查询所有积分模板。
	 * @return 模板列表
	 */
	public List<RobberyTheftCaseScoreTemplate> findAllTemplate();

	/**
	 * 添加积分模板及规则。调用积分计算框架中的接口创建关联的ScoreComputeTask。
	 * 
	 * @param template 模板基本信息
	 * @param rules 规则信息列表
	 * @return true表示创建成功，false表示失败。
	 */
	public boolean createTemplate(RobberyTheftCaseScoreTemplate template, List<RobberyTheftCaseScoreTemplateRule> rules);

	/**
	 * 更新积分模板及规则。调用积分计算框架中的接口更新关联的ScoreComputeTask。
	 * 
	 * @param template 模板基本信息
	 * @param rules 规则信息map，key是串并案分析模板规则item评分项值
	 * @return true表示更新成功，false表示失败。
	 */
	public boolean updateTemplate(RobberyTheftCaseScoreTemplate template, Map<String,RobberyTheftCaseScoreTemplateRule> rules);
	
	/**
	 * 更新积分模板状态
	 * 
	 * @param id 模版id
	 * @param state 状态编码
	 * @return true表示更新成功，false表示失败。
	 */
	public boolean updateTemplateState(String id, String state);

	/**
	 * 查询积发模板及规则。
	 * 
	 * @param id    模板id
	 * @return 模板信息
	 */
	public RobberyTheftCaseScoreTemplate findTemplate(String id);

	/**
	 * 查询启用状态的积分模板及规则。同样类别、一级性质、二级性质的启用状态模板唯一。
	 * 
	 * @param type    类别
	 * @param firstSort    一级性质
	 * @param secondSort    二级性质
	 * @return 模板信息
	 */
	public RobberyTheftCaseScoreTemplate findTemplate(String type, String firstSort, String secondSort);

	/**
	 * 判断是否有相同编码的模板。
	 * 
	 * @param code    模板编码
	 * @return true表示有相同编码模板，false表示没有。
	 */
	public boolean hasSameCodeTemplate(String code);

	/**
	 * 是否有相同名称的模板。
	 * 
	 * @param name    模板名称
	 * @return true表示有同名模板，false表示没有。
	 */
	public boolean hasSameNameTemplate(String name);

	/**
	 * 判断是否可删除模板。根据积分计算结果表（t_xsajfx_cbafxjg）中是否引用了模板关联的计算任务id判断，如果有引用则不能删除，返回false，否则返回
	 * true。
	 * 
	 * @param id    模板id
	 * @return true表示可删除，false表示不可删除。
	 */
	public boolean enableDeleted(String id);

	/**
	 * 删除模板。
	 * 
	 * @param id    模板id
	 */
	public void deleteTemplete(String id);
	
	/**
	 * 查找和案件打标类型匹配的模板.
	 * @param caseCode 案件编码
	 * @return 模板对象,如果没有匹配的模板返回null.
	 */
	public RobberyTheftCaseScoreTemplate findMatchedTemplate(String caseCode);


}