package com.taiji.pubsec.szpt.score.compute.caseanalysis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;

public interface CaseScoreService {

	/**
	 * 获取一组案件打标值集合。
	 * @param cases 案件列表
	 * @return 打标值集合，key为打标项标识，value为打标字典项值集合
	 */
	public Map<String,Set<String>> produceRelatedCaseTagSet(List<CriminalBasicCase> cases);
	
	/**
	 * 查询和主案件进行计算的比对案件列表。排除明确排除的案件和积分结果表中设为忽略的案件。
	 * @param mainCaseCode 主案件编码
	 * @param excludedCases 明确排除的案件列表
	 * @return 比对案件列表
	 */
	public List<CriminalBasicCase> findComparedCases(String mainCaseCode, List<CriminalBasicCase> excludedCases);
	
	/**
	 * 对案件进行串并案积分计算。
	 * @param mainCaseCode 待计算主案件编码
	 */
	public void processScoreCompute(String mainCaseCode);
	
	void processScoreComputing(String mainCaseCode, RobberyTheftCaseScoreTemplate template,
			List<CriminalBasicCase> comparedCases);
	
	void processScoreCompute(String mainCaseCode, String comparedCaseCode);
}
