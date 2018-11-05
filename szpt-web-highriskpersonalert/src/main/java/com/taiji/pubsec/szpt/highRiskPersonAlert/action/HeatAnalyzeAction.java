package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;
import com.taiji.pubsec.szpt.ajgl.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.bean.AlarmPos;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceCountBean;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceMonitorService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService;
import com.taiji.pubsec.szpt.service.JqlxCommonService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.zhzats.model.FactJq;
import com.taiji.pubsec.szpt.zhzats.service.FactJqAnalyzeTsService;
import com.taiji.pubsec.szpt.zhzats.service.FactJqService;

import net.sf.json.JSONObject;

/**
 * 热点分析Action
 *
 */
@Controller("heatAnalyzeAction")
@Scope("prototype")
public class HeatAnalyzeAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;

	private List<PlaceCountBean> placeCountBeanLst ;
	
	private List<String> peopleTypeList;
	
	private List<String> qjTypeList;
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	private List<ZTreeBean> ztreeList = new ArrayList<ZTreeBean>() ;
	
	private List<FactJq> jqList;
	
	private List<AlarmPos> alarmPosLst;
	
	private String queryStr;
	
	private List<CriminalBasicCaseBean> ajList;
	
	private String personType;
	
	private String propleTypeName;
	
	private String ztreeParentNodeId;
	
	private String ztreeNodeId;
	
	private String diyMap;
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiPlaceInAndOutInfoQueryService;
	
	@Resource
	private PlaceMonitorService placeMonitorService;
	
	@Resource
	private JqlxCommonService jqlxCommonService ;
	
	@Resource
	private FactJqService factJqService ;
	
	@Resource 
	private WifiStateRecordService wifiStateRecordService;

	@Resource
	private FactJqAnalyzeTsService factJqAnalyzeTsService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	@Resource
	private ICriminalCaseService criminalCaseService;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;

	public String findHeatByPeopleType(){
		placeCountBeanLst = new ArrayList<PlaceCountBean>();
		if(peopleTypeList == null || peopleTypeList.isEmpty()){
			DictionaryType type = dictionaryTypeService.findDicTypeByCode(DICT.DICT_TYPE_RYLX.getValue());
			List<DictionaryItem> listCode=dictionaryItemService.findDicItemsByTypeCode(type.getCode(), DICT.DICT_ENABLED.getValue());
			peopleTypeList=changeListCode(listCode);
		}
		
		if(startTime == null || endTime == null || peopleTypeList == null){
			return SUCCESS;
		}
		placeCountBeanLst = wifiStateRecordService.findByPersonType(peopleTypeList, DateFmtUtil.longToDate(startTime), DateFmtUtil.longToDate(endTime));
//		List<WifiPlaceInAndOutInfo> infoList = wifiPlaceInAndOutInfoQueryService.findAllByMacs(paramMap);
//		for(WifiPlaceInAndOutInfo info : infoList){
//			WifiPlaceInAndOutInfoBean bean = new WifiPlaceInAndOutInfoBean();
//			BeanUtils.copyProperties(info, bean);
//			wifiPlaceInAndOutInfoBeanList.add(bean);
//		}
		for(PlaceCountBean pcBean : placeCountBeanLst){
			PlaceBasicInfo p = placeBasicInfoService.findByCode(pcBean.getPlaceCode());
			if(p != null){
				pcBean.setPlaceName(p.getInternetServicePlaceName());
				pcBean.setLongitude(Double.valueOf(p.getLongitude()));
				pcBean.setLatitude(Double.valueOf(p.getLatitude()));
			}
		}
		return SUCCESS;
//		WifiStateRecordService
//        findByPersonType
	}
	public String findHeatByJqlx(){
		if(qjTypeList == null){
			qjTypeList = new ArrayList<String>();
		}
		alarmPosLst = factJqAnalyzeTsService.findAlarmPos(DateFmtUtil.longToDate(startTime), DateFmtUtil.longToDate(endTime),qjTypeList.toArray(new String[qjTypeList.size()]),null);
		return SUCCESS;
	}
	public String findHeatByAjlb(){
		if(peopleTypeList==null){
			peopleTypeList = new ArrayList<String>();
		}
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(startTime!=null){
			xqlMap.put("startTime", DateFmtUtil.longToDate(startTime));
		}
		if(endTime!=null){
			xqlMap.put("endTime", DateFmtUtil.longToDate(endTime));
		}
		if(!peopleTypeList.isEmpty()) {
			xqlMap.put("caseSort", peopleTypeList);
		}
		List<CriminalBasicCase> caseList = criminalCaseService.findCriminalCaseByCaseSort(xqlMap);
		ajList = new ArrayList<CriminalBasicCaseBean>();
		for(CriminalBasicCase model : caseList){
			CriminalBasicCaseBean bean = new CriminalBasicCaseBean();
			BeanUtils.copyProperties(model, bean);
			ajList.add(bean);
		}
		return SUCCESS;
	}
	
	/**
	 * 组装人员类型树
	 * @param ztreeNodeId 父ID
	 * @return
	 */
	public String queryPersonTypeTree(){
		List<DictionaryItem> dis = new ArrayList<DictionaryItem>();
		if(StringUtils.isBlank(ztreeNodeId)){
			dis = dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_RYLX.getValue(), DICT.DICT_ENABLED.getValue());
		}else{
			DictionaryItem di = dictionaryItemService.findById(ztreeNodeId);
			if(di != null){
				dis = dictionaryItemService.findDicItemsByParentCode(di.getCode(), DICT.DICT_ENABLED.getValue());
			}
		}
		ztreeList = new ArrayList<ZTreeBean>() ;
		for(DictionaryItem entity : dis){
			ZTreeBean bean = new ZTreeBean();
			bean.setpId(entity.getParentItem() == null ? null : entity.getParentItem().getId());
			bean.setId(entity.getId());
			if(dictionaryItemService.findDicItemsByParentCode(entity.getCode(), DICT.DICT_ENABLED.getValue()).size()>0){
				bean.setIsParent("true");
			}else{
				bean.setIsParent("false");
			}
			bean.setName(entity.getName());
			Map<Object, Object> diyMap = new HashMap<Object, Object>(0) ;
			diyMap.put("code", entity.getCode());
			diyMap.put("id", entity.getId());
			bean.setDiyMap(diyMap);
			ztreeList.add(bean) ;
		}
		return SUCCESS ;
	}
	
	/**
	 * 组装警情类型树
	 * @param ztreeNodeId 父ID
	 * @return
	 */
    public String queryJqlxTree(){
//		String parentId = request.getParameter("ztreeNodeId") ;
//		List<Jqlx> dList = this.jqlxService.findJqlxByParentId(parentId);
		List<Jqlx> dList = this.jqlxCommonService.findAllJqlxs();
		ztreeList = new ArrayList<ZTreeBean>() ;
		for(Jqlx entity:dList){
			ZTreeBean bean = new ZTreeBean();
			bean.setpId(entity.getParentId());
			bean.setId(entity.getId());
			if(jqlxCommonService.findJqlxsByParentId(entity.getId()).size()>0){
				bean.setIsParent("true");
//				bean.setOpen("true");
			}else{
				bean.setIsParent("false");
			}
			bean.setName(entity.getName());
			Map<Object, Object> diyMap = new HashMap<Object, Object>(0) ;
			diyMap.put("code", entity.getCode());
			diyMap.put("level", entity.getLevel());
			diyMap.put("id", entity.getId());
			bean.setDiyMap(diyMap);
			ztreeList.add(bean) ;
		}
		return SUCCESS ;
	}
	/**
	 * 组装案件类别树
	 * @return
	 */
    public String queryAjlbTree(){
		List<DictionaryItem> dList = dictionaryItemService.findAllSubDictionaryItemsByTypeCode(Constant.AJLB);
		ztreeList = new ArrayList<ZTreeBean>() ;
		for(DictionaryItem entity:dList){
			ZTreeBean bean = new ZTreeBean();
			if(entity.getParentItem()!=null){
				bean.setpId(entity.getParentItem().getId());
			}
			bean.setId(entity.getId());
			if(entity.getSubItems().size()>0){
				bean.setIsParent("true");
			}else{
				bean.setIsParent("false");
			}
			bean.setName(entity.getName());
			Map<Object, Object> diyMap = new HashMap<Object, Object>(0) ;
			diyMap.put("code", entity.getCode());
			diyMap.put("id", entity.getId());
			bean.setDiyMap(diyMap);
			ztreeList.add(bean) ;
		}
		return SUCCESS ;
	}
    
    public String jumpHeatAnalyzePage(){
    	Map<String,String> map = JSONObject.fromObject(queryStr);
    	//paramsMap = JSONObject.fromObject(map);
    	String idCode = map.get("idCode");
    	propleTypeName = map.get("propleTypeName");
    	HighriskPerson hPerson = highriskPersonService.findByIdCode(idCode);
    	StringBuilder sb = new StringBuilder("");
		for( HighriskPeopleType type : hPerson.getHighriskPeopleTypes()){
			sb.append(type.getPeopleType()).append("、");
		}
		if(sb.length()>1){
			personType = sb.toString().substring(0, sb.length()-1);
		}
    	return SUCCESS ;
    }
    
    /**
	 * 
	 * @param listCode
	 * @return
	 */
	private List<String> changeListCode(List<DictionaryItem> listCode) {
		List<String>  list=new ArrayList<String>();
		for(int i=0;i<listCode.size();i++){
			list.add(listCode.get(i).getCode());
		}
		return list;
	}

	public List<String> getPeopleTypeList() {
		return peopleTypeList;
	}

	public void setPeopleTypeList(List<String> peopleTypeList) {
		this.peopleTypeList = peopleTypeList;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public List<ZTreeBean> getZtreeList() {
		return ztreeList;
	}
	public void setZtreeList(List<ZTreeBean> ztreeList) {
		this.ztreeList = ztreeList;
	}
	public List<FactJq> getJqList() {
		return jqList;
	}
	public void setJqList(List<FactJq> jqList) {
		this.jqList = jqList;
	}
	public List<CriminalBasicCaseBean> getAjList() {
		return ajList;
	}
	
	public void setAjList(List<CriminalBasicCaseBean> ajList) {
		this.ajList = ajList;
	}
	public List<AlarmPos> getAlarmPosLst() {
		return alarmPosLst;
	}
	public void setAlarmPosLst(List<AlarmPos> alarmPosLst) {
		this.alarmPosLst = alarmPosLst;
	}
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public List<String> getQjTypeList() {
		return qjTypeList;
	}
	public void setQjTypeList(List<String> qjTypeList) {
		this.qjTypeList = qjTypeList;
	}
	public List<PlaceCountBean> getPlaceCountBeanLst() {
		return placeCountBeanLst;
	}
	public void setPlaceCountBeanLst(List<PlaceCountBean> placeCountBeanLst) {
		this.placeCountBeanLst = placeCountBeanLst;
	}
	public String getPropleTypeName() {
		return propleTypeName;
	}
	public void setPropleTypeName(String propleTypeName) {
		this.propleTypeName = propleTypeName;
	}
	public String getZtreeParentNodeId() {
		return ztreeParentNodeId;
	}
	public void setZtreeParentNodeId(String ztreeParentNodeId) {
		this.ztreeParentNodeId = ztreeParentNodeId;
	}
	public String getZtreeNodeId() {
		return ztreeNodeId;
	}
	public void setZtreeNodeId(String ztreeNodeId) {
		this.ztreeNodeId = ztreeNodeId;
	}
	public String getDiyMap() {
		return diyMap;
	}
	public void setDiyMap(String diyMap) {
		this.diyMap = diyMap;
	}
	
	
	
}
