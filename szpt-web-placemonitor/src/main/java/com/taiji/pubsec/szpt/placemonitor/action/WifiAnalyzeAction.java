package com.taiji.pubsec.szpt.placemonitor.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.placemonitor.action.bean.HitSumBean;
import com.taiji.pubsec.szpt.placemonitor.action.util.ModelBeanTransformUtilInPlaceMonitor;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceCountBean;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStatInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

/**
 * WIFI分析Action
 * 
 * @author wangl
 *
 */
@Controller("wifiAnalyzeAction")
@Scope("prototype")
public class WifiAnalyzeAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private ModelBeanTransformUtilInPlaceMonitor modelBeanTransformUtil; //action model和bean对象转换工具
	
	@Resource
	private WifiStateRecordService wifiStateRecordService; //
	
	@Resource
	private IDictionaryItemService dictionaryItemService; //
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 高危人接口

	private List<String> criminalTypeList = new ArrayList<String>(); // 前科类型字典项id集合
	
	private List<String> macList = new ArrayList<String>(); // mac集合
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	private List<ZTreeBean> ztreeList = new ArrayList<ZTreeBean>() ;

	private Long startTime; // 开始时间

	private Long endTime; // 结束时间

	private List<String> criminalTypeCodeList = new ArrayList<String>();//人员类型code数组

	private List<HitSumBean> hitSumBeanList = new ArrayList<HitSumBean>(); // 分组统计结果Bean集合
	
	private List<WifiStatInfo> listWifi=new ArrayList<WifiStatInfo>(); //table 数据展示
	
	private String placeCode; // 场所code
	
	private String personSort; //涉案类型--向下钻取 code
	
	private String ztreeParentNodeId;
	
	private String ztreeNodeId;
	
	private String diyMap;
	
	/**
	 * 根据mac地址集合查询高危人
	 * 
	 * @return
	 */
	public String findHighriskPersonByMacList(){
		List<Map<String, String>> hpList = new ArrayList<Map<String, String>>();
		
		for(String mac : macList){
			HighriskPerson hp = highriskPersonService.findByMac(mac);
			if(hp == null){
				continue;
			}
			Map<String, String> hpMap = new HashMap<String, String>();
			hpMap.put("name", hp.getName());
			hpMap.put("idcode", hp.getIdcode());
			hpMap.put("warnType", hp.getWarnType());
			hpMap.put("warnTypeName", findDictionaryItemNameByCode(DICT.DICT_TYPE_YJLX.getValue(),hp.getWarnType()));
			
			StringBuffer typeNames = new StringBuffer();
			StringBuffer recordNames = new StringBuffer();
			for(HighriskPeopleType type : hp.getHighriskPeopleTypes()){
				typeNames.append(findDictionaryItemNameByCode(DICT.DICT_TYPE_RYLX.getValue(), type.getPeopleType())).append("、");
				for(HighriskCriminalRecord record : type.getHighriskCriminalRecords()){
					recordNames.append(findDictionaryItemNameByCode(DICT.DICT_TYPE_QKLX.getValue(),record.getCriminalRecord())).append("、");
				}
			}
			hpMap.put("peopleTypeName", typeNames.length()>0?typeNames.substring(0,typeNames.length()-1).toString():"");
			hpMap.put("peopleType", recordNames.length()>0?recordNames.substring(0,recordNames.length()-1).toString():"");
			hpMap.put("accumulatePoints", String.valueOf(hp.getAccumulatePoints()));
			hpList.add(hpMap);
		}
		resultMap.put("result", hpList);
		return SUCCESS;
	}
	
	/**
	 * 根据时间段和前科类型统计监测结果（分页查询）
	 * 
	 * @param paramMap 查询参数集合
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param this.getStart()/this.getLength() 开始页数
	 * @param this.getLength() 分页步长
	 * @return "success"，成功返回hitSumBeanList:分组统计结果bean集合
	 */
	public String countMonitorResultByTimeAndCriminalType() {
		if(startTime == null || endTime == null){
			List<PlaceCountBean> pcbs = new ArrayList<PlaceCountBean>();
			resultMap.put("list", pcbs);
			resultMap.put("totalNum", 0);
			return SUCCESS;
		}
		
		Date startDay = DateFmtUtil.longToDate(startTime) ;
		Date endDay = DateFmtUtil.longToDate(endTime) ;
		if(criminalTypeCodeList == null || criminalTypeCodeList.isEmpty()){
			DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_RYLX.getValue());
			List<DictionaryItem> listCode=dictionaryItemService.findDicItemsByTypeCode(type.getCode(), DICT.DICT_ENABLED.getValue());
			criminalTypeCodeList=changeListCode(listCode);
		}
		List<PlaceCountBean> list = wifiStateRecordService.findByPersonType(criminalTypeCodeList, startDay, endDay);
		
		List<PlaceCountBean> lst = new ArrayList<>();
		for(PlaceCountBean pcBean : list){
			if(pcBean.getStayTime()>0){
				PlaceBasicInfo p = placeBasicInfoService.findByCode(pcBean.getPlaceCode());
				if(p != null){
					pcBean.setPlaceName(p.getInternetServicePlaceName());
				}
				lst.add(pcBean);
			}
		}
		resultMap.put("list", lst);
		resultMap.put("totalNum", lst.size());
		
		return SUCCESS;
	}
	
	/**
	 * 根据条件查询监测点命中记录(统计前科类型百分比接口)--查询表格
	 * 
	 * @param paramMap 查询参数集合
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param personSort 向下钻取的人员类别(父级)code
	 * @param placeCode 场所code
	 * @param criminalTypeCodeList 人员类别(图表展示)code
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	public String findCriminalTypeScale(){
		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("timeStart", DateFmtUtil.longToDate(startTime));
//		paramMap.put("timeEnd", DateFmtUtil.longToDate(endTime));
//		paramMap.put("placeName", placeCode);
		List<DictionaryItem> listCode = new ArrayList<DictionaryItem>();
		if(personSort!=null){
			DictionaryItem dicItem = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.DICT.DICT_TYPE_RYLX.getValue(), personSort, null);
			if (null != dicItem) {
				listCode = dicItem.getSubItems();
				criminalTypeCodeList=changeListCode(listCode);
			}
		}else if(criminalTypeCodeList == null || criminalTypeCodeList.isEmpty()){
			DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_RYLX.getValue());
			listCode=dictionaryItemService.findDicItemsByTypeCode(type.getCode(), DICT.DICT_ENABLED.getValue());
			criminalTypeCodeList=changeListCode(listCode);
		}
//		paramMap.put("peopleTypeList", criminalTypeCodeList);
//		paramMap.put("personSort", personSort);
		
		if(startTime == null || endTime == null){
			return SUCCESS;
		}
		
		for(String personTypeCode : criminalTypeCodeList) {
			List<WifiStatInfo> statistics = wifiStateRecordService.findByPlaces(placeCode, Arrays.asList(personTypeCode), DateFmtUtil.longToDate(startTime), DateFmtUtil.longToDate(endTime));
			int sum = 0;
			for(WifiStatInfo stat : statistics) {
				sum = sum + stat.getCount();
			}
			if(sum == 0){
				continue;
			}
			WifiStatInfo personTypeStat = new WifiStatInfo();
			personTypeStat.setCode(personTypeCode);
			DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.DICT.DICT_TYPE_RYLX.getValue(), personTypeCode, Constant.DICT.DICT_ENABLED.getValue());
			personTypeStat.setKey(di.getName() + "(" + sum + "次)");
			personTypeStat.setCount(sum);
			listWifi.add(personTypeStat);
		}
		
		if(listWifi.isEmpty()){
			if(!listCode.isEmpty()){
				for(DictionaryItem di : listCode){
					if(di.getParentItem() == null){
						WifiStatInfo personTypeStat = new WifiStatInfo();
						personTypeStat.setCode(di.getCode());
						personTypeStat.setKey(di.getName() + "(" + 0 + "次)");
						personTypeStat.setCount(0);
						listWifi.add(personTypeStat);
					}
				}
			}else{
				for(String code : criminalTypeCodeList){
					DictionaryItem dicItem = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.DICT.DICT_TYPE_RYLX.getValue(), code, null);
					if (null != dicItem) {
						WifiStatInfo personTypeStat = new WifiStatInfo();
						personTypeStat.setCode(dicItem.getCode());
						personTypeStat.setKey(dicItem.getName() + "(" + 0 + "次)");
						personTypeStat.setCount(0);
						listWifi.add(personTypeStat);
					}
				}
			}
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
	
	public String findDictionaryItemNameByCode(String typeCode , String code) {
		if (code != null && !code.isEmpty()) {
			DictionaryItem item = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, code, DICT.DICT_ENABLED.getValue());
			if (item != null) {
				return item.getName();
			}
		}
		return "";
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public List<String> getCriminalTypeList() {
		return criminalTypeList;
	}

	public void setCriminalTypeList(List<String> criminalTypeList) {
		this.criminalTypeList = criminalTypeList;
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

	public List<HitSumBean> getHitSumBeanList() {
		return hitSumBeanList;
	}

	public void setHitSumBeanList(List<HitSumBean> hitSumBeanList) {
		this.hitSumBeanList = hitSumBeanList;
	}

	public String getPersonSort() {
		return personSort;
	}

	public void setPersonSort(String personSort) {
		this.personSort = personSort;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public List<WifiStatInfo> getListWifi() {
		return listWifi;
	}

	public void setListWifi(List<WifiStatInfo> listWifi) {
		this.listWifi = listWifi;
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

	public List<ZTreeBean> getZtreeList() {
		return ztreeList;
	}

	public void setZtreeList(List<ZTreeBean> ztreeList) {
		this.ztreeList = ztreeList;
	}

	public List<String> getCriminalTypeCodeList() {
		return criminalTypeCodeList;
	}

	public void setCriminalTypeCodeList(List<String> criminalTypeCodeList) {
		this.criminalTypeCodeList = criminalTypeCodeList;
	}

	public List<String> getMacList() {
		return macList;
	}

	public void setMacList(List<String> macList) {
		this.macList = macList;
	}

	
}
