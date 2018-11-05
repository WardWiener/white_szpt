package com.taiji.pubsec.szpt.caseanalysis.score.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.complex.tools.web.action.ExportInfoReq;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseAnalysisCompareBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseAnalysisCompareExcelBean;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreResultService;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreTemplateService;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.caseanalysis.util.CommonBeanModelConverterUtil;
import com.taiji.pubsec.szpt.util.ExcelUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONArray;

/**
 * 案件分析对比结果Action
 * 
 * @author WangLei
 *
 */
@Controller("caseAnalysisCompareAction")
@Scope("prototype")
public class CaseAnalysisCompareAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CaseTagService caseTagService;// 案件打标接口
	
	@Resource
	private CaseService caseService;// 案件接口
	
	@Resource
	private ScoreResultService scoreResultService;// 案件积分计算结果查询接口
	
	@Resource
	private ScoreTemplateService scoreTemplateService;// 积分模版接口
	
	@Resource
	private CommonBeanModelConverterUtil commonBeanModelConverterUtil;// 公用的Bean和Model互转工具类
	
	Map<String, Object> resultMap = new HashMap<String, Object>();// 前台用
	
	private List<String> caseCodes = new ArrayList<String>();//案件编号集合
	
	private String mainCaseCode;// 主案件编号
	
	private ExportInfoReq exportInfoReq = new ExportInfoReq();// 导出Excel用
	
	/**
	 * 转发到案件分析对比页面
	 * 
	 * @return
	 */
	public String transmitCaseAnalysisComparePage(){
		JSONArray caseCodesJson = JSONArray.fromObject(caseCodes);
		resultMap.put("caseCodes", caseCodesJson);
		resultMap.put("mainCaseCode", mainCaseCode);
		return SUCCESS;
	}
	
	/**
	 * 查询主案件相关的信息
	 * 
	 * @return
	 */
	public String queryMainCaseCorrelationInfo(){
		List<CaseAnalysisCompareBean> cacbs = new ArrayList<CaseAnalysisCompareBean>();
		
		for(String code : caseCodes){
			CaseAnalysisCompareBean cacb = queryCaseAnalysisCompareBeanByCaseCode(code);
			if(cacb == null){
				continue;
			}
			cacbs.add(cacb);
		}
		resultMap.put("cacbs", cacbs);
		resultMap.put("mainCase", queryCaseAnalysisCompareBeanByCaseCode(mainCaseCode));
		return SUCCESS;
	}
	
	/**
	 * 案件分析对比结果列表导出EXCEL
	 * 
	 * @param
	 * @return
	 */
	public String exportExcel() throws IOException {
		List<CaseAnalysisCompareExcelBean> cacebs = new ArrayList<CaseAnalysisCompareExcelBean>();// 导出ExcelBean集合
		
		CaseAnalysisCompareExcelBean mainCaceb = queryCaseAnalysisCompareExcelBeanByCaseCode(mainCaseCode);
		mainCaceb.setNo(String.valueOf(1));
		mainCaceb.setType("主案件以及其已串并案信息");
		cacebs.add(mainCaceb);
		
		int i = 2;
		for(String code : caseCodes){
			CaseAnalysisCompareExcelBean caceb = queryCaseAnalysisCompareExcelBeanByCaseCode(code);
			if(caceb == null){
				continue;
			}
			
			caceb.setType("可能的串并案信息");
			caceb.setNo(String.valueOf(i));
			cacebs.add(caceb);
			i ++;
		}
		
		ExcelUtil<CaseAnalysisCompareExcelBean> ex = new ExcelUtil<CaseAnalysisCompareExcelBean>();
		String[] headers = { "序号", "", "案件基本信息", "串并案分值", "犯罪嫌疑人", "案发地点", "案发社区", "作案特点", 
				"选择处所", "作案时段", "作案人数", "作案进口", "作案出口"};
		SimpleDateFormat sdfExcel = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = sdfExcel.format(new Date());
		name = name + ".xls";
		
		// 创建ByteArrayOutputStream字节流
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ex.exportExcel(headers, cacebs, bos);
		byte[] bytes = bos.toByteArray();
		bos.close();
		ByteArrayInputStream ins=new ByteArrayInputStream(bytes);
		exportInfoReq.setIn(ins);
		exportInfoReq.setName(name);
		exportInfoReq.setLength(Long.valueOf(bytes.length));
		ins.close();
		return "done";
	}
	
	/**
	 * 根据案件编号查询出案件分析对比结果Bean
	 * 
	 * @param caseCode 可能的串并案
	 * @return
	 */
	private CaseAnalysisCompareBean queryCaseAnalysisCompareBeanByCaseCode(String caseCode){
		if(StringUtils.isBlank(caseCode)){
			return null;
		}
		CaseAnalysisCompareBean cacb = new CaseAnalysisCompareBean();
		CaseTag ct = caseTagService.findCaseTag(caseCode);
		CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(ct);
		BeanCopier copier = BeanCopier.create(ctb.getClass(), cacb.getClass(), false);
		copier.copy(ctb, cacb, null);
		
		String handlePolice = ct.getBasicCase().getHandlePolice();
		cacb.setHandlePolice(StringUtils.isBlank(handlePolice) || "null".equals(handlePolice) ? "" : handlePolice);
		cacb.setCaseStateName(ct.getBasicCase().getCaseStateName());
		cacb.setSuspectName(commonBeanModelConverterUtil.findSuspectNameByCaseCode(caseCode));

		//查询串并案分析结果
		if(StringUtils.isBlank(mainCaseCode)){
			cacb.setMinScore("");
			cacb.setScoreTemplateName("");
		}else{
			CaseScoreResult csr = scoreResultService.findScoreResult(mainCaseCode, caseCode);
			RobberyTheftCaseScoreTemplate rtcst = null;
			if(csr != null){
				cacb.setMinScore(String.valueOf(csr.getScore()));
				rtcst = scoreTemplateService.findTemplate(csr.getTemplateId());
			}
			if(rtcst != null){
				cacb.setScoreTemplateName(rtcst.getName());
			}
		}
		
		//查询主案件已知的串并案
		if(caseCode.equals(mainCaseCode)){
			List<CriminalBasicCase> cbcs = caseService.findRelatedCase(mainCaseCode);
			List<String> knownCaseCodes = new ArrayList<String>();
			if(cbcs != null && !cbcs.isEmpty()){
				for(CriminalBasicCase cbc : cbcs){
					knownCaseCodes.add(cbc.getCaseCode());
				}
			}
			cacb.setKnownCaseCodes(knownCaseCodes);
			cacb.setScoreTemplateName("");
			RobberyTheftCaseScoreTemplate rtcst = scoreTemplateService.findMatchedTemplate(mainCaseCode);
			if(rtcst != null){
				cacb.setScoreTemplateName(rtcst.getName());
			}
		}
		return cacb;
	}
	
	/**
	 * 根据案件编号查询出案件分析对比结果ExcelBean
	 * 
	 * @param caseCode
	 * @return
	 */
	private CaseAnalysisCompareExcelBean queryCaseAnalysisCompareExcelBeanByCaseCode(String caseCode){
		if(StringUtils.isBlank(caseCode)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		CaseAnalysisCompareExcelBean caceb = new CaseAnalysisCompareExcelBean();
		CaseTag ct = caseTagService.findCaseTag(caseCode);
		CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(ct);
		BeanCopier copier = BeanCopier.create(ctb.getClass(), caceb.getClass(), false);
		copier.copy(ctb, caceb, null);
		
		caceb.setSuspectName(commonBeanModelConverterUtil.findSuspectNameByCaseCode(caseCode));
		
		//查询主案件可能的串并案
		String knownCaseCodesStr = "";
		if(caseCode.equals(mainCaseCode)){
			List<CriminalBasicCase> cbcs = caseService.findRelatedCase(mainCaseCode);
			if(cbcs != null && !cbcs.isEmpty()){
				for(int i=0;i<cbcs.size();i++){
					knownCaseCodesStr += cbcs.get(i).getCaseCode();
					if(i < (cbcs.size() - 1)){
						knownCaseCodesStr += "、" ;
					}
				}
			}
		}
		
		String handlePolice = ct.getBasicCase().getHandlePolice();
		String caseBasicInfo = ctb.getCaseCode() + "；";
		caseBasicInfo += ctb.getCaseName() + "；";
		caseBasicInfo += "办案民警：" + (StringUtils.isBlank(handlePolice) || "null".equals(handlePolice) ? "" : handlePolice) + "；";
		caseBasicInfo += "发案时间：" + sdf.format(new Date(ctb.getCaseTimeStart())) + "；";
		caseBasicInfo += "犯罪嫌疑人：" + caceb.getSuspectName() + "；";
		caseBasicInfo += "案件当前状态：" + ct.getBasicCase().getCaseStateName() + "；";
		
		if(!StringUtils.isBlank(knownCaseCodesStr)){
			caseBasicInfo += "已串并案件：" + knownCaseCodesStr + "；";
		}
		caceb.setCaseBasisInfo(caseBasicInfo);
		
		//查询串并案分析结果
		if(StringUtils.isBlank(mainCaseCode)){
			caceb.setMinScore("");
		}else{
			CaseScoreResult csr = scoreResultService.findScoreResult(mainCaseCode, caseCode);
			if(csr != null){
				caceb.setMinScore(String.valueOf(csr.getScore()));
			}
		}
		return caceb;
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public List<String> getCaseCodes() {
		return caseCodes;
	}

	public void setCaseCodes(List<String> caseCodes) {
		this.caseCodes = caseCodes;
	}

	public String getMainCaseCode() {
		return mainCaseCode;
	}

	public void setMainCaseCode(String mainCaseCode) {
		this.mainCaseCode = mainCaseCode;
	}

	public ExportInfoReq getExportInfoReq() {
		return exportInfoReq;
	}

	public void setExportInfoReq(ExportInfoReq exportInfoReq) {
		this.exportInfoReq = exportInfoReq;
	}

	
}
