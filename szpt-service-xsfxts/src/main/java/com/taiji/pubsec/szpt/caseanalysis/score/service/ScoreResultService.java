package com.taiji.pubsec.szpt.caseanalysis.score.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResultDetail;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;

/**
 * 案件积分计算结果查询接口。
 * 
 * @author dixiaofeng
 */
public interface ScoreResultService {

	/**
	 * 查找主案件的已计算分数打分记录。
	 * 
	 * @param caseCode    案件编号
	 * @param conditions    查询条件
	 * 	"caseState", 是否显示已破案件，字典项是/否
	 * 	"showHide", 是否显示已忽略案件，字典项是/否
	 * 	"minScore", 最低分值
	 * 	"feature", 作案特点
	 * 	"occurPlace", 所选处所
	 * 	"community", 发案社区List
	 * 	"period", 作案时段
	 * 	"peopleNum", 作案人数
	 * 	"entrance", 作案进口
	 * 	"exit", 作案出口
	 */
	public List<CaseScoreResult> findScoredCases(String caseCode, Map<String,Object> conditions);

	/**
	 * 创建(或更新)积分计算结果。
	 * 
	 * @param result
	 */
	public boolean createOrUpdateScoreResult(CaseScoreResult result);
	
	/**
	 * 创建(或更新)串并案打分结果明细
	 * @param detail 串并案打分结果明细
	 * @return 成功，返回true；失败，返回false
	 */
	public boolean createOrUpdateCaseScoreResultDetail(CaseScoreResultDetail detail);

	/**
	 * 按主案件、比对案件查找串并案计算结果，只有唯一一条。
	 * 查到则返回结果对象，否则返回null。
	 * 
	 * @param mainCaseCode    主案件编码
	 * @param subCaseCode    比对案件编码
	 */
	public CaseScoreResult findScoreResult(String mainCaseCode, String subCaseCode);
	
	/**
	 * 查找对主案件忽略的案件集合。
	 * @param mainCaseCode 主案件编码
	 * @return 忽略的案件列表
	 */
	public List<CriminalBasicCase> findIgnoredCases(String mainCaseCode);
	
	/**
	 * 通过结果id和规则名称查询串并案打分结果明细
	 * @param resultId 结果id
	 * @param ruleName 规则名称
	 * @return 返回串并案打分结果明细
	 */
	public CaseScoreResultDetail findDetailByResultIdAndRuleName(String resultId, String ruleName);

	public void refresh(Object obj);
}