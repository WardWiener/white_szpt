package com.taiji.pubsec.szpt.zhzats.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.complex.tools.doc.msoffice.impl.poicr.builder.PoiCrReportBuilder;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.complex.tools.web.action.ExportInfoReq;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseStatisticService;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.common.model.OrderCell.ORDERCELL_TYPE;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPeopleStatisticsService;
import com.taiji.pubsec.szpt.placemonitor.service.IPlaceStatisticsService;
import com.taiji.pubsec.szpt.service.JqlxCommonService;
import com.taiji.pubsec.szpt.service.LocationCommonService;
import com.taiji.pubsec.szpt.service.SzptDictionaryItemService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.zhzats.bean.FloatingPopInfo;
import com.taiji.pubsec.szpt.zhzats.bean.InterrogatSituInfo;
import com.taiji.pubsec.szpt.zhzats.service.DailyReportPersonTsService;
import com.taiji.pubsec.szpt.zhzats.service.FactJqAnalyzeTsService;
import com.taiji.pubsec.szpt.zhzats.service.FactJqService;
import com.taiji.pubsec.szpt.zhzats.service.FloatingPopAnalyzeTsService;
import com.taiji.pubsec.szpt.zhzats.service.InterrogatSituAnalyzeTsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("factJqtsAnalyzeAction")
@Scope("prototype")
public class FactJqtsAnalyzeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(FactJqtsAnalyzeAction.class);
	
	private String queryStr ;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	@Resource
	private FactJqAnalyzeTsService factJqAnalyzeTsService ;
	
	@Resource
	private FactJqService factJqService ;
	
	@Resource
	private InterrogatSituAnalyzeTsService interrogatSituAnalyzeService;
	
	@Resource
	private FloatingPopAnalyzeTsService floatingPopAnalyzeTsService;
	
	@Resource
	private CaseStatisticService caseStatisticService;

	@Resource
	private CaseService caseService;
	
	@Resource
	private SzptDictionaryItemService szptDictionaryItemService;
	
	@Resource
	private LocationCommonService locationCommonService;
	
	@Resource
	private JqlxCommonService jqlxCommonService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	
	@Resource
	private IPlaceStatisticsService placeStatisticsSerivice;
	
	@Resource
	private IHighriskPeopleStatisticsService highriskPeopleStatisticsService ;
	
	@Resource
	private PoiCrReportBuilder poiCrReportBuilder;
	
	@Resource
	private DailyReportPersonTsService dailyReportPersonTsService ;
	
	private ExportInfoReq exportInfoReq = new ExportInfoReq();
	
	/**
	 * wifi网络围栏统计
	 * @return 统计结果列表。StatisticsInfoTwoValue中name为派出所名称  value为采集数量 value_two为五色人员数量
	 */
	@SuppressWarnings("unchecked")
	public String wifiStatistics() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		resultMap.put("list", placeStatisticsSerivice.wifiStatistics(startDay, endDay, pcsCodes)) ;
		return SUCCESS;
	}
	
	/**
	 * 高危统计
	 * @return 统计结果列表。StatisticsInfoTwoValue中name为派出所名称  value为采集数量 value_two为五色人员数量
	 */
	public String gaoweiStatistics() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		resultMap.put("list", highriskPeopleStatisticsService.zaiKongPeopleStatistics(startDay, endDay, pcsCodes)) ;
		return SUCCESS;
	}
	
	/**
	 * 首页滚动条
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String gundongInfo(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		
		resultMap.put("jqList", gundongJqInfo(startDay, endDay, pcsCodes, jqlxCodes)) ;
		resultMap.put("panchaList", gundongInterrogatSituInfo(startDay, endDay, pcsCodes)) ;
		resultMap.put("liudongrenkouList", gundongFloatingPopInfo(startDay, endDay, pcsCodes)) ;
		
		return SUCCESS;
	}
	
	/**
	 * 今日警情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String jinriInfo(){
		Map<String, Object> rqst = JSONObject.fromObject(queryStr);
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		
		Date startDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTimeHB").toString())) ;
		Date endDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTimeHB").toString())) ;
		
		String searType = (String)rqst.get("searType") ;
		Integer level = (Integer)rqst.get("level") ;
		String parentJqlxCode = (String)rqst.get("parentJqlxCode") ;
		List<String> jqlxCodes = jinriInfoJqlxCodes(parentJqlxCode) ;
		if("child".equals(searType)){
			jqlxCodes = jinriInfoJqlxParentCodes(parentJqlxCode) ;
		}
		String method="welcome";
		List<AlarmInfo> listbq = this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodes(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()]), method) ;
		List<AlarmInfo> listHB = this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodes(startDayHB, endDayHB, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()]), method) ;

		if(StringUtils.isNotBlank(parentJqlxCode)){
			String grandparentJqlxCode="";
			Jqlx grandparentJqlx = this.jqlxCommonService.findJqlxByCode(parentJqlxCode);
			grandparentJqlxCode=grandparentJqlx!=null?grandparentJqlx.getCode():"";
			if("parent".equals(searType)){
				String id=grandparentJqlx.getParentId();
				String code="";
				if(id!=null && id.length()>0){
					code=this.jqlxCommonService.findJqlxById(id).getCode();
				} 
				if(code!=null && code.length()>0){
					List<Jqlx> list=this.jqlxCommonService.findJqlxsByParentCode(code);
					grandparentJqlxCode=list.get(0).getCode();
				}else{
					List<Jqlx> list=this.jqlxCommonService.findJqlxsByParentCode(parentJqlxCode);
					grandparentJqlxCode=list.get(0).getCode();
				}
			}
			resultMap.put("grandparentJqlxCode",grandparentJqlxCode ) ;
		}
		resultMap.put("level",level);
		resultMap.put("list", jinriAlarmInfoPack(listbq, listHB)) ;
 		return SUCCESS;
	}

	/**
	 * 常量预警
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String changliangInfo(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
//		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		
		List<AlarmInfo> list = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]), Constant.JQLX_CODE.XING_SHI.getValue().split(",")) ;
		sortAlarmInfoByCount(list, Constant.SORT_TYPE.DESC.getValue()) ;
		resultMap.put("list", list) ;
		return SUCCESS;
	}
	
	/**
	 * 警情坐标
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findJqPos(){	
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		resultMap.put("list", this.factJqAnalyzeTsService.findAlarmPos(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		return SUCCESS ;
	}
	
	/**
	 * 刑事案件坐标
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findXsAjPos(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
 		resultMap.put("list", this.caseService.findCaseInfoByConditions(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		return SUCCESS ;
	}
	
	/**
	 * 巡处力量坐标
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findXcllPos(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		resultMap.put("list", this.locationCommonService.findAllLocations(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		return SUCCESS ;
	}
	
	/**
	 * 发案处所
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findFaAnChuSuo(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		String region=null;
		if(null != pcsCodes && pcsCodes.size() == 1){
			region = pcsCodes.get(0);	
		}
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		Integer level=(Integer) rqst.get("level");
		String parentAjTypeCode = (String)rqst.get("parentAjTypeCode") ;
		List<String> ajTypeCodes = parentAjTypeCodeToAjTypeCodes(parentAjTypeCode) ;
		resultMap.put("list", this.caseStatisticService.countByTagOccurPlace(region,startDay, endDay, level, ajTypeCodes.toArray(new String[ajTypeCodes.size()])));
	
		if(StringUtils.isNotBlank(parentAjTypeCode)){
			DictionaryItem pdi = this.dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), parentAjTypeCode, null);
			resultMap.put("grandparentAjTypeCode", pdi.getParentItem()!=null?pdi.getParentItem().getCode():"") ;
			if(!"".equals((String)resultMap.get("grandparentAjTypeCode"))){
				DictionaryItem psdi = this.dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), (String)resultMap.get("grandparentAjTypeCode"), null);
				resultMap.put("grandparentsAjTypeCode", psdi.getParentItem()!=null?psdi.getParentItem().getCode():"");
			}else{
				resultMap.put("grandparentsAjTypeCode", null);
			}
		}
		return SUCCESS ;
	}
	
	/**
	 * 发案时段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findFaAnShiDuan(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		String region=null;
		if(null != pcsCodes && pcsCodes.size() == 1){
			region = pcsCodes.get(0);	
		}
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		Integer level=(Integer) rqst.get("level");
		String parentAjTypeCode = (String)rqst.get("parentAjTypeCode") ;
		List<String> ajTypeCodes = parentAjTypeCodeToAjTypeCodes(parentAjTypeCode) ;
		resultMap.put("list", this.caseStatisticService.countByTagCasePeriod( region, startDay, endDay, level, ajTypeCodes.toArray(new String[ajTypeCodes.size()])));
		
		if(StringUtils.isNotBlank(parentAjTypeCode)){
			DictionaryItem pdi = this.dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), parentAjTypeCode, null);
			resultMap.put("grandparentAjTypeCode", pdi.getParentItem()!=null?pdi.getParentItem().getCode():"") ;
			if(!"".equals((String)resultMap.get("grandparentAjTypeCode"))){
				DictionaryItem psdi = this.dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), (String)resultMap.get("grandparentAjTypeCode"), null);
				resultMap.put("grandparentsAjTypeCode", psdi.getParentItem()!=null?psdi.getParentItem().getCode():"");
			}else{
				resultMap.put("grandparentsAjTypeCode", null);
			}
		}
		return SUCCESS ;
	}
	
	/**
	 * 流动人口
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findFloatingPopInfo(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		
		Date startDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTimeHB").toString())) ;
		Date endDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTimeHB").toString())) ;
		
		List<FloatingPopInfo> list = this.floatingPopAnalyzeTsService.findFloatingPopInfosByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]));
		List<FloatingPopInfo> listHB = this.floatingPopAnalyzeTsService.findFloatingPopInfosByPcsCodes(startDayHB, endDayHB, pcsCodes.toArray(new String[pcsCodes.size()]));	
		resultMap.put("list", floatingPopInfoForPack(list, listHB)) ;	
		return SUCCESS ;
	}
	
	/**
	 * 盘查
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findPanCha(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		resultMap.put("list", this.interrogatSituAnalyzeService.findInterrogatSituByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()])));
		return SUCCESS ;
	}
	
	/**
	 * 打防管控
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findDaFangGuanKong(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		
		Date startDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTimeHB").toString())) ;
		Date endDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTimeHB").toString())) ;
		
		List<AlarmInfo> jqList = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]), jqlxCodes.toArray(new String[jqlxCodes.size()])) ;
		List<AlarmInfo> jqListHB = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodes(startDayHB, endDayHB, pcsCodes.toArray(new String[pcsCodes.size()]), jqlxCodes.toArray(new String[jqlxCodes.size()])) ;
		
		jinriAlarmInfoPack(jqList, jqListHB);
		
		sortAlarmInfoByCount(jqList, Constant.SORT_TYPE.DESC.getValue()) ;
		
		resultMap.put("jqList", jqList) ;
		
		resultMap.put("xingshiTotal", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDay, endDay, Constant.JQLX_CODE.XING_SHI.getValue().split(","), pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		resultMap.put("xingshiTotalHB", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDayHB, endDayHB, Constant.JQLX_CODE.XING_SHI.getValue().split(","), pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		
		resultMap.put("zhianTotal", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDay, endDay, Constant.JQLX_CODE.ZHI_AN.getValue().split(","), pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		resultMap.put("zhianTotalHB", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDayHB, endDayHB, Constant.JQLX_CODE.ZHI_AN.getValue().split(","), pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		
		resultMap.put("qitaTotal", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDay, endDay, Constant.JQLX_CODE.QI_TA.getValue().split(","), pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		resultMap.put("qitaTotalHB", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodesByTotal(startDayHB, endDayHB, Constant.JQLX_CODE.QI_TA.getValue().split(","), pcsCodes.toArray(new String[pcsCodes.size()]))) ;
		
		List<InterrogatSituInfo> panchaList = this.interrogatSituAnalyzeService.findInterrogatSituByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]));
		List<InterrogatSituInfo> panchaListHB = this.interrogatSituAnalyzeService.findInterrogatSituByPcsCodes(startDayHB, endDayHB, pcsCodes.toArray(new String[pcsCodes.size()]));
		interrogatSituInfoForPack(panchaList, panchaListHB) ;
		sortInterrogatSituInfoByAllNum(panchaList, Constant.SORT_TYPE.DESC.getValue()) ;
		
		
		resultMap.put("panchaList", panchaList);
		
		List<AlarmInfo> shiduanList = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodesBuShiDuan(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()])) ;
		sortAlarmInfoByCount(shiduanList, Constant.SORT_TYPE.DESC.getValue()) ;
		
		resultMap.put("shiduanList", shiduanList);
		
		List<String> levelOneJqlxCodes = jinriInfoJqlxCodes("") ;
		String method="welcome";
		List<AlarmInfo> jqlistByJqlx = this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodes(startDayHB, endDayHB, levelOneJqlxCodes.toArray(new String[levelOneJqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()]), method) ;
		sortAlarmInfoByCount(jqlistByJqlx, Constant.SORT_TYPE.DESC.getValue()) ;
		
		resultMap.put("jqlistByJqlx", jqlistByJqlx);
		
		return SUCCESS ;
	}
	
	/**
	 * 打防管控导出word
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String daFangGuanKongWordExport(){
		
		Map<String, Object> rqst = JSONObject.fromObject(queryStr);
		
		return "done";
	}
	
	/**
	 * 分布分析
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findFenbuPos(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		String analyseBaseType=(String) rqst.get("analyzeBase");
		jqlxCodes = jqlxCodesFmt(jqlxCodes) ;
		if(analyseBaseType.equals(ORDERCELL_TYPE.TYPE_ZHUGE.getValue())){
			resultMap.put("list", this.factJqAnalyzeTsService.findGriddJq(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]),ORDERCELL_TYPE.TYPE_ZHUGE.getValue()));
		}else{
			resultMap.put("list", this.factJqAnalyzeTsService.findAlarmPos(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])));
		}
		resultMap.put("baobeiList", this.dailyReportPersonTsService.findDailyReportPerson(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]),analyseBaseType));
		
		resultMap.put("shijiList", this.locationCommonService.findAllLocationsByCount(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]),analyseBaseType)) ;
		
		return SUCCESS ;
	}
	/**
	 * 分布分析-查看警情数量详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findJqslDetailList(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCode = JSONArray.fromObject(rqst.get("code")) ;
		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		String analyseBaseType=(String) rqst.get("analyzeBase");
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		jqlxCodes = jqlxCodesFmt(jqlxCodes) ;
		resultMap.put("list",this.factJqService.factJqList(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCode.toArray(new String[pcsCode.size()]) ,analyseBaseType));
		return SUCCESS;
	}
	/**
	 * 分布分析-查看报备警力详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findBbjlDetailList(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCode = JSONArray.fromObject(rqst.get("code")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		resultMap.put("list",this.dailyReportPersonTsService.dailyReportPersonTsList(startDay, endDay, pcsCode.toArray(new String[pcsCode.size()])));
		return SUCCESS;
	}
	/**
	 * 分布分析-查看实际警力详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findSjjlDetailList(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCode = JSONArray.fromObject(rqst.get("code")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		resultMap.put("list",this.locationCommonService.locationList(startDay, endDay, pcsCode.toArray(new String[pcsCode.size()])));
		return SUCCESS;
	}
	
	/**
	 * 高发时段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findGaofaJq(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		jqlxCodes = jqlxCodesFmt(jqlxCodes) ;
		Date startDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTimeHB").toString())) ;
		Date endDayHB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTimeHB").toString())) ;
		Date startDayTB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTimeTB").toString())) ;
		Date endDayTB = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTimeTB").toString())) ;
		List<AlarmInfo> list=new ArrayList<AlarmInfo>();
		if(pcsCodes!=null && pcsCodes.size()==1){
			 list = this.factJqAnalyzeTsService.findAlarmInfosByCommunityCodesBuShiDuanByJqlx(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])) ;
		}else{
			 list = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodesBuShiDuanByJqlx(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])) ;
		}
		List<AlarmInfo> listHB = new ArrayList<AlarmInfo>();
		if(pcsCodes!=null && pcsCodes.size()==1){
			listHB = this.factJqAnalyzeTsService.findAlarmInfosByCommunityCodesBuShiDuanByJqlx(startDayHB, endDayHB, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])) ;
		}else{
			listHB = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodesBuShiDuanByJqlx(startDayHB, endDayHB, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])) ;
		}
		List<AlarmInfo> listTB = new ArrayList<AlarmInfo>();
		if(pcsCodes!=null && pcsCodes.size()==1){
			listTB = this.factJqAnalyzeTsService.findAlarmInfosByCommunityCodesBuShiDuanByJqlx(startDayTB, endDayTB, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])) ;
		}else{
			listTB = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodesBuShiDuanByJqlx(startDayTB, endDayTB, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()])) ;
		}
		
		resultMap.put("list", gaofaJqingPack(list, listHB, listTB)) ;
		
		return SUCCESS ;
	}
	
	/**
	 * 高发类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findGaofaJqlx(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> pcsCodes = JSONArray.fromObject(rqst.get("pcsCodes")) ;
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(rqst.get("endTime").toString())) ;
		List<String> jqlxCodes = JSONArray.fromObject(rqst.get("jqlxCodes")) ;
		jqlxCodes = jqlxCodesFmt(jqlxCodes) ;
		
		String parentJqlxCode = rqst.get("parentJqlxCode").toString()=="null" ?"":rqst.get("parentJqlxCode").toString();
		
		String searchType = (String)rqst.get("searchType") ;
		Integer level = (Integer)rqst.get("level") ;
		
		jqlxCodes = gaofaJqlxCodesChildCode(searchType, jqlxCodes, parentJqlxCode) ;
		
		if(searchType.equals("drillChild")){
			jqlxCodes = gaofaJqlxCodesFmt(searchType, jqlxCodes, parentJqlxCode) ;
		}
		String method="gaofa";
		resultMap.put("list", this.factJqAnalyzeTsService.findAlarmInfosByJqlxCodesByPcsCodes(startDay, endDay, jqlxCodes.toArray(new String[jqlxCodes.size()]), pcsCodes.toArray(new String[pcsCodes.size()]), method)) ;
		resultMap.put("level",level);
		if(StringUtils.isNotBlank(parentJqlxCode)){	
			String grandparentJqlxCode="";
			Jqlx grandparentJqlx = this.jqlxCommonService.findJqlxByCode(parentJqlxCode);
			grandparentJqlxCode=grandparentJqlx!=null?grandparentJqlx.getCode():"";
			if("drillChild".equals(searchType)){
				String id=grandparentJqlx.getParentId();
				String code="";
				if(id!=null && id.length()>0){
					code=this.jqlxCommonService.findJqlxById(id).getCode();
				} 
				if(code!=null && code.length()>0){
					List<Jqlx> list=this.jqlxCommonService.findJqlxsByParentCode(code);
					grandparentJqlxCode=list.get(0).getCode();
				}else{
					List<Jqlx> list=this.jqlxCommonService.findJqlxsByParentCode(parentJqlxCode);
					grandparentJqlxCode=list.get(0).getCode();
				}
			}
			resultMap.put("grandparentJqlxCode",grandparentJqlxCode ) ;
		}
		
		return SUCCESS ;
	}
	
	private List<String> gaofaJqlxCodesChildCode(String searchType,
			List<String> jqlxCodes, String parentJqlxCode) {
		if(StringUtil.isNotBlank(searchType) && searchType.equals("drill") && StringUtil.isNotBlank(parentJqlxCode)){
			List<String> list = new ArrayList<String>();
			for(Jqlx lx:this.jqlxCommonService.findJqlxsByChildCode(parentJqlxCode)){
				list.add(lx.getCode()) ;
			}
				return list ;
		}
		return jqlxCodes ;
	}

	private List<String> gaofaJqlxCodesFmt(String searchType, List<String> jqlxCodes, String parentJqlxCode){
		if(StringUtil.isNotBlank(searchType) && searchType.equals("drillChild") && StringUtil.isNotBlank(parentJqlxCode)){
			List<String> list = new ArrayList<String>();
			for(Jqlx lx:this.jqlxCommonService.findJqlxsByParentCode(parentJqlxCode)){
				list.add(lx.getCode()) ;
			}
				return list ;
		}
		return jqlxCodes ;
	}
	
	private List<AlarmInfo> gaofaJqingPack(List<AlarmInfo> list, List<AlarmInfo> listHB, List<AlarmInfo> listTB){
		for(AlarmInfo info:list){
			for(AlarmInfo info1:listHB){
				if(info.getName().equals(info1.getName()) && info.getNameAdd1().equals(info1.getNameAdd1()) ){
					info.setHbCount(info1.getCount());
				}
			}
			for(AlarmInfo info1:listTB){
				if(info.getName().equals(info1.getName()) && info.getNameAdd1().equals(info1.getNameAdd1()) ){
					info.setTbCount(info1.getCount());
				}
			}
		}
		return list ;
	}
	
	private List<String> jqlxCodesFmt(List<String> jqlxCodes){
		if(jqlxCodes.size()==0){
			List<String> list = new ArrayList<String>();
			for(Jqlx jqlx:this.jqlxCommonService.findJqlxsByLevel(Jqlx.LEVEL.ONE.getValue())){
				list.add(jqlx.getCode()) ;
			}
			return list ;
		}
		return jqlxCodes ;
	}
	
	private List<InterrogatSituInfo> interrogatSituInfoForPack(List<InterrogatSituInfo> list, List<InterrogatSituInfo> listHB){
		for(InterrogatSituInfo info:list){
			for(InterrogatSituInfo infoHB:listHB){
				if(info.getName().equals(infoHB.getName())){
					info.setNewCarNotKySumHB(infoHB.getNewCarNotKySum());
					info.setNewCarSumHB(infoHB.getNewCarSum());
					info.setNewManpowerSumHB(infoHB.getNewManpowerSum());
				}
			}
		}
		return list ;
	}
	
	private List<FloatingPopInfo> floatingPopInfoForPack(List<FloatingPopInfo> list, List<FloatingPopInfo> listHB){
		for(FloatingPopInfo info:list){
			for(FloatingPopInfo infoHB:listHB){
				if(info.getName().equals(infoHB.getName())){
					info.setHbFloatingNumSum(infoHB.getFloatingNumSum());
				}
			}
		}
		return list ;
	}
	
	private List<String> parentAjTypeCodeToAjTypeCodes(String parentAjTypeCode){
		if(StringUtils.isBlank(parentAjTypeCode)){
			return dictItemsToCodes(dictionaryItemService.findDicItemsByTypeCode(Constant.DICT.DICT_TYPE_AJXZ.getValue(), Constant.DICT.DICT_ENABLED.getValue())) ;
		}
		
		return dictItemsToCodes(szptDictionaryItemService.findItemsByParentCode(parentAjTypeCode, Constant.DICT.DICT_TYPE_AJXZ.getValue())) ;
	}
	
	private List<String> dictItemsToCodes(List<DictionaryItem> list){
		List<String> rsList = new ArrayList<String>();
		for(DictionaryItem di:list){
			rsList.add(di.getCode()) ;
		}
		return rsList ;
	}
	
	private List<AlarmInfo> jinriAlarmInfoPack(List<AlarmInfo> list, List<AlarmInfo> listHB){
		for(AlarmInfo e:list){
			for(AlarmInfo e1:listHB){
				if(e1.getName().equals(e.getName())){
					e.setHbCount(e1.getCount());
				}
			}
		}
		return list ;
	}
	
	private List<String> jinriInfoJqlxCodes(String parentJqlxCode){
		List<String> jqlxCodes = new ArrayList<String>() ;
		List<Jqlx> list = new ArrayList<Jqlx>() ;
		if(StringUtils.isBlank(parentJqlxCode)){
			list = this.jqlxCommonService.findJqlxsByLevel(Jqlx.LEVEL.ONE.getValue()) ;
		}else{
			list = this.jqlxCommonService.findJqlxsByParentCode(parentJqlxCode) ;
		}
		for(Jqlx jqlx:list){
			jqlxCodes.add(jqlx.getCode()) ;
		}
		return jqlxCodes ;
	}
	
	
	private List<String> jinriInfoJqlxParentCodes(String parentJqlxCode) {
		List<String> jqlxCodes = new ArrayList<String>() ;
		List<Jqlx> list = new ArrayList<Jqlx>() ;
		if(StringUtils.isBlank(parentJqlxCode)){
			list = this.jqlxCommonService.findJqlxsByLevel(Jqlx.LEVEL.ONE.getValue()) ;
		}else{
			list = this.jqlxCommonService.findJqlxsByChildCode(parentJqlxCode) ;
		}
		for(Jqlx jqlx:list){
			jqlxCodes.add(jqlx.getCode()) ;
		}
		return jqlxCodes ;

	}

	
	private List<AlarmInfo> gundongJqInfo(Date startDay, Date endDay, List<String> pcsCodes, List<String> jqlxCodes){
		
		List<AlarmInfo> list = this.factJqAnalyzeTsService.findAlarmInfosByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()]), jqlxCodes.toArray(new String[jqlxCodes.size()])) ;
		sortAlarmInfoByCount(list, Constant.SORT_TYPE.DESC.getValue()) ;
		
		return list ;
	}
	
	private List<InterrogatSituInfo> gundongInterrogatSituInfo(Date startDay, Date endDay, List<String> pcsCodes){
		List<InterrogatSituInfo> list = this.interrogatSituAnalyzeService.findInterrogatSituByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()])) ;
		sortInterrogatSituInfoByAllNum(list, Constant.SORT_TYPE.DESC.getValue()) ;
		return list ;
	}
	
	private List<FloatingPopInfo> gundongFloatingPopInfo(Date startDay, Date endDay, List<String> pcsCodes){
		List<FloatingPopInfo> list = this.floatingPopAnalyzeTsService.findFloatingPopInfosByPcsCodes(startDay, endDay, pcsCodes.toArray(new String[pcsCodes.size()])) ;
		sortFloatingPopInfoByCount(list, Constant.SORT_TYPE.DESC.getValue()) ;
		return list ;
	}
	
	private void sortAlarmInfoByCount(List<AlarmInfo> list, final String ascOrDesc){
		Collections.sort(list, new Comparator<AlarmInfo>() {
			@Override
			public int compare(AlarmInfo o1, AlarmInfo o2) {
				if(ascOrDesc.equals(Constant.SORT_TYPE.DESC.getValue())){
					return o2.getCount() - o1.getCount();
				}
				
				return o1.getCount() - o2.getCount();
			}
		});
	}
	
	private void sortInterrogatSituInfoByAllNum(List<InterrogatSituInfo> list, final String ascOrDesc){
		Collections.sort(list, new Comparator<InterrogatSituInfo>() {
			@Override
			public int compare(InterrogatSituInfo o1, InterrogatSituInfo o2) {
				Integer num1 = o1.getNewManpowerSum() + o1.getNewCarSum() + o1.getNewCarNotKySum() ;
				Integer num2 = o2.getNewManpowerSum() + o2.getNewCarSum() + o2.getNewCarNotKySum() ;
				
				if(ascOrDesc.equals(Constant.SORT_TYPE.DESC.getValue())){
					return num2 - num1;
				}
				
				return num1 - num2;
			}
		});
	}
	
	private void sortFloatingPopInfoByCount(List<FloatingPopInfo> list, final String ascOrDesc){
		Collections.sort(list, new Comparator<FloatingPopInfo>() {
			@Override
			public int compare(FloatingPopInfo o1, FloatingPopInfo o2) {
				if(ascOrDesc.equals(Constant.SORT_TYPE.DESC.getValue())){
					return o2.getFloatingNumSum() - o1.getFloatingNumSum();
				}
				
				return o1.getFloatingNumSum() - o2.getFloatingNumSum();
			}
		});
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
	
}
