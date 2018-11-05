package com.taiji.pubsec.szpt.caseanalysis.judge.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.util.CommonBeanModelConverterUtil;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionBean;
import com.taiji.pubsec.szpt.instruction.action.util.InsturctionCommonUtil;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.zhzats.model.JqAnalyze;
import com.taiji.pubsec.szpt.zhzats.service.JqAnalyzeService;

import net.sf.json.JSONObject;

/**
 * 案件一案一研判Action
 * 
 * @author WangLei
 *
 */
@Controller("caseJudgeAction")
@Scope("prototype")
public class CaseJudgeAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CaseService caseService;// 案件接口
	
	@Resource
	private IInstructionService instructionService;// 指令接口
	
	@Resource
	private JqAnalyzeService jqAnalyzeService;// 警情分析接口
	
	@Resource
	private CommonBeanModelConverterUtil commonBeanModelConverterUtil;// action公用方法
	
	@Resource
	private InsturctionCommonUtil insturctionCommonUtil;// 指令公用方法
	
	private String caseName;// 案件名称
	
	private String caseCode;// 案件编号
	
	private String tagState;// 打标状态
	
	private List<String> caseSortList = new ArrayList<String>();// 案件类别
	
	private List<String> caseKindList = new ArrayList<String>();// 案件性质
	
	private InstructionBean instructionBean;// 指令Bean
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	/**
	 * 根据条件查询所有未破案的案件(分页查询)
	 * 
	 * @return
	 */
	public String queryCaseListByCondition(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("caseName", caseName);
		map.put("caseCode", caseCode);
		map.put("tagState", tagState);
		map.put("caseSort", caseSortList);
		map.put("caseKind", caseKindList);
//		map.put("ifSolved", DICT.DICT_NO.getValue());
		Pager<CriminalBasicCase> pager = caseService.findCaseByConditions(map, this.getStart()/this.getLength(), this.getLength());
		
		List<CriminalBasicCaseBean> cbcbs = new ArrayList<CriminalBasicCaseBean>();
		if(pager == null){
			resultMap.put("cbcbs", cbcbs);
			resultMap.put("totalNum", 0);
			return SUCCESS;
		}
		
		for(CriminalBasicCase cbc : pager.getPageList()){
			CriminalBasicCaseBean cbcb = commonBeanModelConverterUtil.criminalBasicCaseToCriminalBasicCaseBean(cbc);
			if(cbcb == null){
				continue;
			}
			cbcbs.add(cbcb);
		}
		resultMap.put("cbcbs", cbcbs);
		resultMap.put("totalNum", pager.getTotalNum());
		return SUCCESS;
	}
	
	/**
	 * 根据警情id查询警情研判结果
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryAlarmJudgeResultByAlarmId(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String jqId = (String)rqst.get("jqId");
		JqAnalyze ja = jqAnalyzeService.findJqAnalyzeById(jqId);
		resultMap.put("ja", ja);
		return SUCCESS;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getTagState() {
		return tagState;
	}

	public void setTagState(String tagState) {
		this.tagState = tagState;
	}

	public InstructionBean getInstructionBean() {
		return instructionBean;
	}

	public void setInstructionBean(InstructionBean instructionBean) {
		this.instructionBean = instructionBean;
	}

	public List<String> getCaseSortList() {
		return caseSortList;
	}

	public void setCaseSortList(List<String> caseSortList) {
		this.caseSortList = caseSortList;
	}

	public List<String> getCaseKindList() {
		return caseKindList;
	}

	public void setCaseKindList(List<String> caseKindList) {
		this.caseKindList = caseKindList;
	}

	
}
