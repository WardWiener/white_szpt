package com.taiji.pubsec.szpt.caseanalysis.score.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseTagCompareBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseTagCompareExcelBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.CaseTagExcelBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.PossibleAlikeCaseBean;
import com.taiji.pubsec.szpt.caseanalysis.score.action.bean.PossibleAlikeCaseExcelBean;
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
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.ExcelUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * 串并案分析Action
 * 
 * @author WangLei
 *
 */
@Controller("caseAnalysisAction")
@Scope("prototype")
public class CaseAnalysisAction extends PageCommonAction{

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
	
	private String queryStr;// 查询条件JSON字符串
	
	private List<String> caseCodes = new ArrayList<String>();// 案件编号集合

	private Map<String, Object> resultMap = new HashMap<String, Object>(); // 返回前台用
	
	private ExportInfoReq exportInfoReq = new ExportInfoReq();// 导出Excel用
	
	/**
	 * 查询打标集合（分页）
	 * 
	 * @return
	 */
	public String queryCaseTagListByCondition(){
		Pager<CriminalBasicCase> pager = caseTagService.findCaseByConditions(setQueryCaseTagListConditionMap(), this.getStart()/this.getLength(), this.getLength());
		List<CaseTagBean> ctbs = new ArrayList<CaseTagBean>();
		if(pager == null || pager.getPageList().isEmpty()){
			resultMap.put("ctbs", ctbs);
			resultMap.put("totalNum", 0);
			return SUCCESS;
		}
		for(CriminalBasicCase cbc : pager.getPageList()){
			CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(cbc.getCaseTag());
			if(ctb == null){
				continue;
			}
			ctbs.add(ctb);
		}
		resultMap.put("ctbs", ctbs);
		resultMap.put("totalNum", pager.getTotalNum());
		return SUCCESS;
	}
	
	/**
	 * 转发到对比页面
	 * 
	 * @return
	 */
	public String transmitCaseTagComparePage(){
		JSONArray caseCodeJsons = JSONArray.fromObject(caseCodes);
		resultMap.put("caseCodes", caseCodeJsons);
		return SUCCESS;
	}
	
	/**
	 * 修改分析结果是否忽略状态
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateCaseScoreResultIgnored(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String mainCaseCode = (String)rqst.get("mainCaseCode");
		String possibleCaseCode = (String)rqst.get("possibleCaseCode");
		boolean ignored = (boolean)rqst.get("ignored");
		CaseScoreResult csr = scoreResultService.findScoreResult(mainCaseCode, possibleCaseCode);
		if(csr == null){
			resultMap.put("flag", false);
			return SUCCESS;
		}
		csr.setIgnored(ignored);
		boolean flag = scoreResultService.createOrUpdateScoreResult(csr);
		resultMap.put("flag", flag);
		return SUCCESS;
	}
	
	/**
	 * 根据案件编号集合查询案件打标集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCaseTagsByCaseCodes(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		JSONArray caseCodeJsons = JSONArray.fromObject(rqst.get("caseCodes"));
		String[] caseCodes = (String[])JSONArray.toArray(caseCodeJsons,String.class);
		resultMap.put("ctcbs", findCaseTagsByCaseCodes(Arrays.asList(caseCodes)));
		return SUCCESS;
	}
	
	/**
	 * 查询可能的串并案集合
	 * @return
	 */
	public String queryPossibleCaseList(){
		Map<String,Object> conditionMap = setqueryPossibleCaseListConditionMap();
		List<CaseScoreResult> csrs = scoreResultService.findScoredCases((String)conditionMap.get("caseCode"), conditionMap);
		List<PossibleAlikeCaseBean> pacbs = new ArrayList<PossibleAlikeCaseBean>();
		
		for(CaseScoreResult csr : csrs){
			PossibleAlikeCaseBean pacb = caseScoreResultToPossibleAlikeCaseBean(csr);
			if(pacb == null){
				continue;
			}
			pacbs.add(pacb);
		}
		resultMap.put("pacbs", pacbs);
		return SUCCESS;
	}
	
	/**
	 * 串并案分析--案件打标列表导出EXCEL
	 * 
	 * @param
	 * @return
	 */
	public String exportExcelByCaseTagList() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Pager<CriminalBasicCase> pager = caseTagService.findCaseByConditions(setQueryCaseTagListConditionMap(), 0, Integer.MAX_VALUE);
		List<CaseTagExcelBean> ctebs = new ArrayList<CaseTagExcelBean>();// 导出ExcelBean集合
		int i = 1;
		for (CriminalBasicCase cbc : pager.getPageList()) {
			CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(cbc.getCaseTag());
			if(ctb == null){
				continue;
			}
			CaseTagExcelBean cteb = new CaseTagExcelBean();
			BeanCopier copier = BeanCopier.create(ctb.getClass(), cteb.getClass(), false);
			copier.copy(ctb, cteb, null);
			cteb.setNo(String.valueOf(i));
			cteb.setCaseTimeStartAndEnd(sdf.format(cbc.getCaseTimeStart()));
			i++;
			ctebs.add(cteb);
		}
		
		ExcelUtil<CaseTagExcelBean> ex = new ExcelUtil<CaseTagExcelBean>();
		String[] headers = { "序号", "案件编号", "案件名称", "案件类别", "案件性质", "二级案件性质",
				"发案时间", "作案特点", "选择处所", "作案时段", "作案人数", "作案进口", "作案出口", "发案社区"};
		SimpleDateFormat sdfExcel = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = sdfExcel.format(new Date());
		name = name + ".xls";
		
		// 创建ByteArrayOutputStream字节流
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ex.exportExcel(headers, ctebs, bos);
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
	 * 案件打标对比列表导出EXCEL
	 * 
	 * @param
	 * @return
	 */
	public String exportExcelByCaseTagCompareList() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<CaseTagCompareExcelBean> ctcebs = new ArrayList<CaseTagCompareExcelBean>();// 导出ExcelBean集合
		List<CaseTagCompareBean> ctcbs = findCaseTagsByCaseCodes(caseCodes);
		int i = 1;
		for(CaseTagCompareBean ctcb : ctcbs) {
			CaseTagCompareExcelBean ctceb = new CaseTagCompareExcelBean();
			BeanCopier copier = BeanCopier.create(ctcb.getClass(), ctceb.getClass(), false);
			copier.copy(ctcb, ctceb, null);
			ctceb.setNo(String.valueOf(i));
			String caseBasicInfo = ctcb.getCaseCode() + "；";
			caseBasicInfo += ctcb.getCaseName() + "；";
			caseBasicInfo += "办案民警：" + ctcb.getHandlePolice() + "；";
			caseBasicInfo += "发案时间：" + sdf.format(ctcb.getCaseTimeStart()) + "；";
			caseBasicInfo += "犯罪嫌疑人：" + ctcb.getSuspectName() + "；";
			caseBasicInfo += "案件当前状态：" + ctcb.getCaseStateName() + "；";
			ctceb.setCaseBasicInfo(caseBasicInfo);
			i++;
			ctcebs.add(ctceb);
		}
		
		ExcelUtil<CaseTagCompareExcelBean> ex = new ExcelUtil<CaseTagCompareExcelBean>();
		String[] headers = { "序号", "案件基本信息", "案件类别", "案件性质", "二级案件性质", "犯罪嫌疑人",
				"案发地点", "案发社区", "作案特点", "选择处所", "作案时段", "作案人数", "作案进口", "作案出口"};
		SimpleDateFormat sdfExcel = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = sdfExcel.format(new Date());
		name = name + ".xls";
		
		// 创建ByteArrayOutputStream字节流
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ex.exportExcel(headers, ctcebs, bos);
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
	 * 可能的串并案列表导出EXCEL
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportExcelByPossibleCaseList() throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		List<PossibleAlikeCaseExcelBean> pacebs = new ArrayList<PossibleAlikeCaseExcelBean>();// 导出ExcelBean集合
		queryPossibleCaseList();
		int i = 1;
		for(Object obj : (List<Object>)resultMap.get("pacbs")) {
			PossibleAlikeCaseBean pacb = (PossibleAlikeCaseBean)obj;
			PossibleAlikeCaseExcelBean paceb = new PossibleAlikeCaseExcelBean();
			BeanCopier copier = BeanCopier.create(pacb.getClass(), paceb.getClass(), false);
			copier.copy(pacb, paceb, null);
			paceb.setNo(String.valueOf(i));
			paceb.setCaseTimeStart(sdf.format(pacb.getCaseTimeStart()));
			paceb.setIsIgnored(pacb.isIgnored()? "是": "否");
			i++;
			pacebs.add(paceb);
		}
		
		ExcelUtil<PossibleAlikeCaseExcelBean> ex = new ExcelUtil<PossibleAlikeCaseExcelBean>();
		String[] headers = { "序号", "分值", "评分模版", "案件编号", "案件名称", "案发时间", "案件类别", "案件性质", "二级案件性质",
				"作案特点", "选择处所", "作案时段", "作案人数", "作案进口", "作案出口", "案发社区", "是否忽略"};
		SimpleDateFormat sdfExcel = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = sdfExcel.format(new Date());
		name = name + ".xls";
		
		// 创建ByteArrayOutputStream字节流
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ex.exportExcel(headers, pacebs, bos);
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
	 * 根据案件编号集合查询案件打标集合
	 * 
	 * @param caseCodes
	 * @return
	 */
	private List<CaseTagCompareBean> findCaseTagsByCaseCodes(List<String> caseCodes){
		List<CaseTagCompareBean> ctcbs = new ArrayList<CaseTagCompareBean>();
		
		if(caseCodes == null || caseCodes.isEmpty()){
			return ctcbs;
		}
		
		for(String caseCode : caseCodes){
			CaseTag ct = caseTagService.findCaseTag(caseCode);
			CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(ct);
			CaseTagCompareBean ctcb = new CaseTagCompareBean();
			BeanCopier copier = BeanCopier.create(ctb.getClass(), ctcb.getClass(), false);
			copier.copy(ctb, ctcb, null);
			String handlePolice = ct.getBasicCase().getHandlePolice();
			ctcb.setHandlePolice(StringUtils.isBlank(handlePolice) || "null".equals(handlePolice)?"" : handlePolice);
			ctcb.setCaseStateName(ct.getBasicCase().getCaseStateName());
			ctcb.setSuspectName(commonBeanModelConverterUtil.findSuspectNameByCaseCode(caseCode));
			ctcbs.add(ctcb);
		}
		return ctcbs;
	}
	
	/**
	 * 设置打标集合查询条件map
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> setQueryCaseTagListConditionMap(){
		Map<String, Object> queryMap = new HashMap<String,Object>();
		if(StringUtils.isBlank(queryStr)){
			return queryMap;
		}
		Map<String, Object> maps = JSONObject.fromObject(queryStr);
		//案件一级性质
		if(!JSONNull.getInstance().equals(maps.get("firstSorts"))){
			JSONArray jsonArray = JSONArray.fromObject(maps.get("firstSorts"));
			String[] firstSorts = (String[])JSONArray.toArray(jsonArray, String.class);
			queryMap.put("firstSorts",Arrays.asList(firstSorts));
		}else{
			queryMap.put("firstSorts", null);
		}
		//案件二级性质
		if(!JSONNull.getInstance().equals(maps.get("secondSorts"))){
			JSONArray jsonArray = JSONArray.fromObject(maps.get("secondSorts"));
			String[] secondSorts = (String[])JSONArray.toArray(jsonArray, String.class);
			queryMap.put("secondSorts",Arrays.asList(secondSorts));
		}else{
			queryMap.put("secondSorts", null);
		}
		//作案特点
		if(!JSONNull.getInstance().equals(maps.get("features"))){
			JSONArray jsonArray = JSONArray.fromObject(maps.get("features"));
			String[] features = (String[])JSONArray.toArray(jsonArray, String.class);
			queryMap.put("features",Arrays.asList(features));
		}else{
			queryMap.put("features", null);
		}
		//发案社区
		if(!JSONNull.getInstance().equals(maps.get("communitys"))){
			JSONArray jsonArray = JSONArray.fromObject(maps.get("communitys"));
			String[] communitys = (String[])JSONArray.toArray(jsonArray, String.class);
			queryMap.put("communitys",Arrays.asList(communitys));
		}else{
			queryMap.put("communitys", null);
		}
		queryMap.put("caseCode", maps.get("caseCode"));
		queryMap.put("type", maps.get("type"));
		queryMap.put("occurPlace", maps.get("occurPlace"));
		queryMap.put("period", maps.get("period"));
		queryMap.put("peopleNum", maps.get("peopleNum"));
		queryMap.put("entrance", maps.get("entrance"));
		queryMap.put("exit", maps.get("exit"));
		return queryMap;
	}
	
	/**
	 * 设置根据主案件查询可能的串并案集合的查询条件map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String,Object> setqueryPossibleCaseListConditionMap(){
		Map<String, Object> queryMap = new HashMap<String,Object>();
		Map<String, Object> rqst = new HashMap<String,Object>();
		if(StringUtils.isBlank(queryStr)){
			rqst = JSONObject.fromObject(read());
		}else{
			rqst = JSONObject.fromObject(queryStr);
		}
		queryMap.put("caseCode", (String)rqst.get("caseCode"));
		queryMap.put("caseState", "on".equals((String)rqst.get("caseState"))?DICT.DICT_YES.getValue():DICT.DICT_NO.getValue());
		queryMap.put("showHide", "on".equals((String)rqst.get("showHide"))?DICT.DICT_YES.getValue():DICT.DICT_NO.getValue());
		JSONArray communityJson = JSONArray.fromObject(rqst.get("community"));
		String[] communityArray = (String[])JSONArray.toArray(communityJson,String.class);
		queryMap.put("community", Arrays.asList(communityArray));
		queryMap.put("entrance", JSONNull.getInstance().equals(rqst.get("entrance"))?null:(String)rqst.get("entrance"));
		queryMap.put("exit", JSONNull.getInstance().equals(rqst.get("exit"))?null:(String)rqst.get("exit"));
		queryMap.put("feature", JSONNull.getInstance().equals(rqst.get("feature"))?null:(String)rqst.get("feature"));
		queryMap.put("minScore", JSONNull.getInstance().equals(rqst.get("minScore"))?null:(String)rqst.get("minScore"));
		queryMap.put("occurPlace", JSONNull.getInstance().equals(rqst.get("occurPlace"))?null:(String)rqst.get("occurPlace"));
		queryMap.put("peopleNum", JSONNull.getInstance().equals(rqst.get("peopleNum"))?null:(String)rqst.get("peopleNum"));
		queryMap.put("period", JSONNull.getInstance().equals(rqst.get("period"))?null:(String)rqst.get("period"));
		return queryMap;
	}
	
	/**
	 * 串并案打分结果Model 转 可能的串并案Bean
	 * 
	 * @param csr
	 * @return pacb
	 */
	private PossibleAlikeCaseBean caseScoreResultToPossibleAlikeCaseBean(CaseScoreResult csr){
		if(csr == null){
			return null;
		}
		PossibleAlikeCaseBean pacb = new PossibleAlikeCaseBean();
		//查询出可能的案件
		CaseTag ct = caseTagService.findCaseTag(csr.getSubCase());
		CaseTagBean ctb = commonBeanModelConverterUtil.caseTagToCaseTagBean(ct);
		BeanCopier copier = BeanCopier.create(ctb.getClass(), pacb.getClass(), false);
		copier.copy(ctb, pacb, null);
		pacb.setMinScore(String.valueOf(csr.getScore()));
		pacb.setIgnored(csr.isIgnored());
		//查询使用的积分模版
		RobberyTheftCaseScoreTemplate rtcst = scoreTemplateService.findTemplate(csr.getTemplateId());
		pacb.setScoreTemplateName("");
		pacb.setScoreTemplateMinScore(null);
		if(rtcst != null){
			pacb.setScoreTemplateName(rtcst.getName());
			pacb.setScoreTemplateMinScore(String.valueOf(rtcst.getMinScore()));
		}
		return pacb;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public ExportInfoReq getExportInfoReq() {
		return exportInfoReq;
	}

	public void setExportInfoReq(ExportInfoReq exportInfoReq) {
		this.exportInfoReq = exportInfoReq;
	}

	public List<String> getCaseCodes() {
		return caseCodes;
	}

	public void setCaseCodes(List<String> caseCodes) {
		this.caseCodes = caseCodes;
	}
	
	
}
