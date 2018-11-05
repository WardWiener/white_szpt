package com.taiji.pubsec.szpt.caseanalysis.tag.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.bean.DictionaryItemBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.caseanalysis.util.CommonBeanModelConverterUtil;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.operationrecord.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
//import com.taiji.pubsec.szpt.score.compute.caseanalysis.service.CaseScoreService;
import com.taiji.pubsec.szpt.service.ICommunityService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/**
 * 刑事分析打标
 *
 */
@SuppressWarnings("serial")
@Controller("caseTagAction")
@Scope("prototype")
public class CaseTagAction extends PageCommonAction{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseTagAction.class);
	
	@Resource
	private IUnitService unitService;// 单位接口
	
	@Resource
	private CaseTagService caseTagService;// 案件打标接口
	
	@Resource
	private CaseService caseService;// 案件接口
	
//	@Resource
//	private CaseScoreService caseScoreService;// 计算接口
	
	@Resource
	private CommonBeanModelConverterUtil commonBeanModelConverterUtil;// 公用的Bean和Model互转工具类
	
	@Resource
	private ICommunityService  communityService;// 村居接口
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	@Resource
	private IOperationRecordService operationRecordService;// 操作记录接口

	@Resource
	KafkaProducer caseComputeProducer;
	
	private String queryStr;// table表查询参数
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();// 返回前台用
	
	
	/**
	 * 查询案件列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCaseTagList(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		Map<String, Object> xqlMap = dataMapToXqlMap(data);
		Pager<CriminalBasicCase> cbcPager = caseService.findCaseByConditions(xqlMap, this.getStart()/this.getLength(), this.getLength());
		List<CriminalBasicCaseBean> cbcbs = new ArrayList<CriminalBasicCaseBean>();
		if(cbcPager == null || cbcPager.getPageList().isEmpty()){
			resultMap.put("totalNum", 0);
			resultMap.put("cbcbs", cbcbs);
			return SUCCESS;
		}
		for(CriminalBasicCase cbc : cbcPager.getPageList()){
			CriminalBasicCaseBean cbcb = commonBeanModelConverterUtil.criminalBasicCaseToCriminalBasicCaseBean(cbc);
			if(cbcb == null){
				continue;
			}
			cbcbs.add(cbcb);
		}
		resultMap.put("totalNum", cbcPager.getTotalNum());
		resultMap.put("cbcbs", cbcbs);
		return SUCCESS;
	}
	
	/**
	 * 初始化打标页面字典数据
	 * 
	 * @return
	 */
	public String initCaseTagDict(){
		List<DictionaryItem> sacs = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_SACS.getValue(), DICT.DICT_ENABLED.getValue());// 涉案场所
		List<DictionaryItem> zars = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZARS.getValue(), DICT.DICT_ENABLED.getValue());//人数 
		List<DictionaryItem> time = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZATIME.getValue(), DICT.DICT_ENABLED.getValue());//时间段
		List<DictionaryItem> ted = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZATD.getValue(), DICT.DICT_ENABLED.getValue());//作案特点
		resultMap.put("sacs", dictionaryItemLstToDictionaryItemBeanLst(sacs));
		resultMap.put("zars", dictionaryItemLstToDictionaryItemBeanLst(zars));
		resultMap.put("time", dictionaryItemLstToDictionaryItemBeanLst(time));
		resultMap.put("ted", dictionaryItemLstToDictionaryItemBeanLst(ted));
		return SUCCESS;
	}
	
	/**
	 * 根据派出所code查询村居
	 * 
	 * @return
	 */ 
	public String findCommunityByPcs(){
		JSONObject rqst = JSONObject.fromObject(read());
		String pcsCode = (String)rqst.get("pcsCode");
		Unit unit = unitService.findUnitByCode(pcsCode);
		List<Community> cs = null;
		if(unit != null){
			cs = communityService.findCommunityByUnitId(unit.getId());
		}
		if(cs == null){
			cs = new ArrayList<Community>();
		}
		resultMap.put("countrys", cs);
		return SUCCESS;
	}
	
	/**
	 * 保存/修改打标
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveOrUpdateCaseTag(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		JSONObject jsonCtb = JSONObject.fromObject(rqst.get("ctb"));
		CaseTagBean ctb = (CaseTagBean)JSONObject.toBean(jsonCtb, CaseTagBean.class);
		
		JSONArray jsonFcs = JSONArray.fromObject(rqst.get("fcs"));
		List<String> featureCodes = Arrays.asList((String[])JSONArray.toArray(jsonFcs,String.class));
		CaseTag ct = commonBeanModelConverterUtil.caseTagBeanToCaseTag(ctb);
		boolean flag = caseTagService.tagCase(ct, featureCodes, ctb.getCaseCode());
//		caseTagService.refresh(ct);
//		caseScoreService.compute(ctb.getCaseCode());
		if(flag) {
			LOGGER.debug("send case code for score compute to kafka:{}", ctb.getCaseCode());
			caseComputeProducer.sendData("topic-scorecompute-case", ctb.getCaseCode().getBytes());
		}
		OperationRecord or = new OperationRecord(ct);
		if(StringUtils.isBlank(ctb.getId())){
			or.setContent("保存打标");
		}else{
			or.setContent("更新打标");
		}
		or.setOperateTime(new Date());
		or.setOperateUnit(this.findCurrentOrganization().getShortName());
		or.setOperator(this.findCurrentPerson().getName());
		operationRecordService.saveOperationRecord(or);
		resultMap.put("flag", flag);
		
		return SUCCESS;
	}
	
	/**
	 * 根据案件code查询打标数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCaseTagByCaseCode(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseCode = (String)rqst.get("caseCode");
		CaseTag  ct = caseTagService.findCaseTag(caseCode);
		resultMap.put("ctb", commonBeanModelConverterUtil.caseTagToCaseTagBean(ct));
		return SUCCESS;
	}
	
	/**
	 * 根据Code查询打标操作记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryCaseTagOpertionRecordByCaseCode(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseCode = (String)rqst.get("caseCode");
		
		List<OperationRecord> ors = caseTagService.findTagOperation(caseCode);
		List<OperationRecordBean> orbs = new ArrayList<OperationRecordBean>();
		
		for(OperationRecord or : ors){
			OperationRecordBean orb = commonBeanModelConverterUtil.operationRecordToOperationRecordBean(or);
			if(orb == null){
				continue;
			}
			orbs.add(orb);
		}
		resultMap.put("orbs", orbs);
		return SUCCESS;
	}
	
	
	/**
	 * 字典项  转换成Bean
	 * 
	 * @param list
	 * @return
	 */
	private List<DictionaryItemBean> dictionaryItemLstToDictionaryItemBeanLst(List<DictionaryItem> list){
		List<DictionaryItemBean> beanList = new ArrayList<DictionaryItemBean>();
		for(int i=0; i<=list.size()-1;i++){
			beanList.add(new DictionaryItemBean(((DictionaryItem) list.get(i)).getId(), ((DictionaryItem) list.get(i)).getCode(), ((DictionaryItem) list.get(i)).getName()));
		}
		return beanList;
	}
	
	/**
	 * 装配Map查询条件
	 * 
	 * @param data
	 * @return
	 */
	private Map<String, Object> dataMapToXqlMap(Map<String,Object> data){
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("caseCode",  data.get("caseCode"));
		xqlMap.put("caseName",  data.get("caseName"));
		xqlMap.put("caseAddress", data.get("caseAddress"));
		xqlMap.put("caseSort",  data.get("caseSort"));
		xqlMap.put("caseKind",    data.get("caseKind"));
		xqlMap.put("regionCode",     data.get("regionCode"));
		xqlMap.put("communityCode", data.get("communityCode"));
		if(!JSONNull.getInstance().equals(data.get("discoverTimeStart"))){
			xqlMap.put("discoverTimeStart", new Date((Long)data.get("discoverTimeStart")));
		}else{
			xqlMap.put("discoverTimeStart", null);
		}
		if(!JSONNull.getInstance().equals(data.get("discoverTimeEnd"))){
			xqlMap.put("discoverTimeEnd",  new Date((Long)data.get("discoverTimeEnd")));
		}else{
			xqlMap.put("discoverTimeEnd", null);
		}
		if(JSONNull.getInstance().equals(data.get("tagState"))){
			xqlMap.put("tagState", null);
		}else{
			xqlMap.put("tagState", data.get("tagState"));
		}
		if(JSONNull.getInstance().equals(data.get("ifSolved"))){
			xqlMap.put("ifSolved", null);
		}else{
			xqlMap.put("ifSolved", data.get("ifSolved"));
		}
		return xqlMap;
	}
	
	
	
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
