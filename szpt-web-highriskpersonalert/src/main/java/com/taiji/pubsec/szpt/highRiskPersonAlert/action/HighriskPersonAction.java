package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HighriskPersonBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HrpScoreResultBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HrpScoreResultDetailBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonCheckStatisticsInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonIntegralBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.ResultInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.ModelBeanTransformUtilInHighRiskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModel;
import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HrpScoreResult;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HrpScoreResultDetail;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.HighriskPersonAnalyzeService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.HighriskPersonScoreAnalyzeService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPeopleStatisticsService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonHistoryInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IIntegralModelService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

/**
 * 高危人群分析预警人员Action
 * 
 *
 */
@Controller("highriskPersonAction")
@Scope("prototype")
public class HighriskPersonAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;
	
	private String idcode;
	
	private String id;
	
	private HighriskPersonBean highriskPersonBean;

	private List<HighriskPersonBean> highriskPersonBeanList;

	private Pager<HighriskPersonBean> highriskPersonBeanPager;
	
	private List<StatisticsInfo> statisticsInfoList ;
	
	private List<PersonCheckStatisticsInfoBean> personCheckStatisticsInfoBeanList;
	
	private Long startTime;
	
	private Long endTime;
	
	private String newAddHighriskPerson;
	
	private String warnTypeStatistic;
	
	private String peopleTypeStatistic;
	
	private String approveContent;
	
	private boolean isPass;
	
	private List<String> peopleTypeCodes = new ArrayList<String>();
	
	@Resource
	private IIntegralModelService integralModelService ;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IUnitService unitService;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private HighriskPersonAnalyzeService highriskPersonAnalyzeService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 重点人接口
	
	@Resource
	private IHighriskPersonHistoryInfoService highriskPersonHistoryInfoService;
	
	@Resource(name="highriskPersonScoreAnalyzeService")
	private HighriskPersonScoreAnalyzeService highriskPersonScoreAnalyzeService;
	
	@Resource
	private IHighriskPeopleStatisticsService highriskPeopleStatisticsService;
	
	@Resource
	private ModelBeanTransformUtilInHighRiskPerson modelBeanTransformUtil; //action model和bean对象转换工具

	@Resource
	private IOperationRecordService operationRecordService;
	
	private List<OperationRecordBean> operationRecordBeanList;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	private String queryStr;
	
	private HrpScoreResult hrpScoreResult;
	
	private HrpScoreResultBean hrpScoreResultBean;
	
	private IntegralModel integralModel ;
	
	/**
	 * 查询人员积分详情
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryIntegralInfo(){
		integralModel = integralModelService.findIntegralModelStatusOn();
		HrpScoreResult hSResult = highriskPersonScoreAnalyzeService.findHPersonScoreDetail(id);
		hrpScoreResultBean = new HrpScoreResultBean();
		hrpScoreResultBean.setHrpId(hSResult.getHrpId());
		hrpScoreResultBean.setTotalScore(hSResult.getTotalScore());
		List<HrpScoreResultDetail> hrdl = hSResult.getHrpScoreResultDetails();
		List<HrpScoreResultDetailBean> hrpScoreResultDetails = new ArrayList<HrpScoreResultDetailBean>();
		for(int i = 0 ;i<hrdl.size();i++){
			HrpScoreResultDetailBean hsrdb = new HrpScoreResultDetailBean();
			hsrdb.setScore(hrdl.get(i).getScore());
			ResultInfoBean rib = new ResultInfoBean();
			Map<String, Object> rqst =	JSONObject.fromObject(hrdl.get(i).getOtherResults());
			rqst.get("resultInfo");
			Map<String, Object> rqst2 =	JSONObject.fromObject(rqst.get("resultInfo"));
			Object o = rqst2.get("hitInfos");
			rib.setHitInfos((List<String>) o);
			System.out.println(rqst.get("result"));
			Object o2 = rqst.get("result");
			rib.setResult(o2.toString());
			hsrdb.setResultInfoBean(rib);
			hrpScoreResultDetails.add(hsrdb);
		}
		hrpScoreResultBean.setHrpScoreResultDetails(hrpScoreResultDetails);
		return SUCCESS;
	}
	
	
	/**
	 * 按条件查询高危预警人员列表（分页查询）
	 * 
	 * paramMap 查询条件</br>:name 姓名</br>:idcode 身份证号</br>:warnType 预警类型
	 * this.getStart() / this.getLength() 页数
	 * this.getLength() 条数
	 * "success",成功返回fiveColorPersonBeanList:高危预警人员列表
	 */
	public String queryPersonList() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("name", highriskPersonBean.getName());
		paramMap.put("idcode", highriskPersonBean.getIdcode());
		if(!StringUtils.isEmpty(highriskPersonBean.getWarnType())){
			String[] warnType = highriskPersonBean.getWarnType().split(",");
			List<String> warningTypeList = Arrays.asList(warnType);
			paramMap.put("warningTypeList", new ArrayList(warningTypeList));
		}
		if(!StringUtils.isEmpty(highriskPersonBean.getOperateStatus())){
			String[] operateStatus = highriskPersonBean.getOperateStatus().split(",");
			List<String> operateStatusList = Arrays.asList(operateStatus);
			paramMap.put("operateStatusList", new ArrayList(operateStatusList));
		}
		if(!StringUtils.isEmpty(highriskPersonBean.getPeopleType())){
			String[] personType = highriskPersonBean.getPeopleType().split(",");
			List<String> strlist = Arrays.asList(personType);
			List<String> personTypeList = new ArrayList(strlist);
			if(personTypeList.contains(Constant.RYLX)){
				personTypeList.remove(Constant.RYLX);
			}
			if(personTypeList.contains(Constant.RYLX_QJ)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_QJ);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_QD)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_QD);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_DQ)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_DQ);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_ZP)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_ZP);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_QT)){
				personTypeList.remove(Constant.RYLX_QT);
				List<DictionaryItem> list =  dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_XSQK);
				List<DictionaryItem> qiangjie = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLXID, Constant.RYLX_QJ);
				List<DictionaryItem> qiangduo = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLXID, Constant.RYLX_QD);
				List<DictionaryItem> daoqie = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLXID, Constant.RYLX_DQ);
				List<DictionaryItem> zhapian = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLXID, Constant.RYLX_ZP);
				for(DictionaryItem item : list){
					if(qiangjie.indexOf(item)==-1&&qiangduo.indexOf(item)==-1&&daoqie.indexOf(item)==-1&&zhapian.indexOf(item)==-1&&!item.getCode().equals(Constant.RYLX_QJ)&&!item.getCode().equals(Constant.RYLX_QD)&&!item.getCode().equals(Constant.RYLX_DQ)&&!item.getCode().equals(Constant.RYLX_ZP)){
						personTypeList.add(item.getCode());
					}
				}
			}
			if(personTypeList.contains(Constant.RYLX_SK)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_SK);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_SD)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_SD);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_JSB)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_JSB);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_ZDSF)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_ZDSF);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_ZT)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_ZT);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_QSN)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_QSN);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(personTypeList.contains(Constant.RYLX_AZBR)){
				List<DictionaryItem> list = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, Constant.RYLX_AZBR);
				for(DictionaryItem item : list){
					personTypeList.add(item.getCode());
				}
			}
			if(!personTypeList.isEmpty()){
				paramMap.put("personTypeList", personTypeList);
			}
		}
		highriskPersonBeanPager = new Pager<HighriskPersonBean>();
		List<HighriskPersonBean> list = new ArrayList<HighriskPersonBean>();
		Pager<HighriskPerson> pager = highriskPersonService.findByCondition(paramMap, this.getStart() / this.getLength(), this.getLength());
		for (HighriskPerson person : pager.getPageList()) {
			list.add(modelBeanTransformUtil.transformHighriskPersonBean(person));
		}
		highriskPersonBeanPager.setPageList(list);
		highriskPersonBeanPager.setTotalNum(pager.getTotalNum());
		return SUCCESS;
	}
	/**
	 * 查找最新创建的5个重点人信息
	 * 
	 * @return "success",成功返回fiveColorPersonBeanList:高危预警人员Bean集合
	 */
	public String findCreatedPersonLately(){
		highriskPersonBeanList = new ArrayList<HighriskPersonBean>();
		List<HighriskPerson> persons = highriskPersonService.findCreatedHighriskLately();
		for(HighriskPerson person : persons){
			highriskPersonBeanList.add(modelBeanTransformUtil.transformHighriskPersonBean(person));
		}
		return SUCCESS;
	}
	/**
	 * 按预警类型统计重点人员数量
	 * @return "success",成功返回fiveColorPersonBeanList:高危预警人员Bean集合
	 */
	public String sumupByWarnType(){
		statisticsInfoList = highriskPersonService.sumupHighriskPersonByWarnType();
		List<String> colors = Arrays.asList("红色","橙色","黄色","白色","蓝色","其他");
		for(String color : colors){
			boolean flag=false;
			for(StatisticsInfo info : statisticsInfoList){
				if(info.getName()==null){
					continue;
				}
				if(info.getName().equals(color)){
					flag=true;
					break;
				}
			}
			if(!flag){
				StatisticsInfo info = new StatisticsInfo();
				info.setName(color);
				info.setValue("0");
				statisticsInfoList.add(info);
			}
		}
		Collections.sort(statisticsInfoList, new Comparator<StatisticsInfo>() {
			List<String> colors = Arrays.asList("红色","橙色","黄色","白色","蓝色","其他",null);
			public int compare(StatisticsInfo obj1, StatisticsInfo obj2) {
				return colors.lastIndexOf(obj1.getName())-colors.lastIndexOf(obj2.getName());
			}
		});
		return SUCCESS;
	}
	/**
	 * 按人员类型统计重点人员数量
	 * @return "success",成功返回statisticsInfoList:高危预警人员Bean集合
	 */
	public String sumupByPepleType(){
		statisticsInfoList = highriskPeopleStatisticsService.highriskPeopleTypeStatistics();
		return SUCCESS;
	}
	
	/**
	 * 人员时段统计
	 * @return
	 */
	public String highriskPeopleShiduan(){
		Date startDay=DateFmtUtil.longToDate(startTime);
		Date endDay=DateFmtUtil.longToDate(endTime);
		List<AlarmInfo> list = highriskPersonAnalyzeService.findHPersonsCountByPeopleTypeByDayPart(startDay, endDay, peopleTypeCodes);
		if(list == null){
			list = new ArrayList<AlarmInfo>();
		}
		//TODO 以下为假数据
//		List<AlarmInfo> list = new ArrayList<AlarmInfo>();
//		list.add(new AlarmInfo("0-6","123","涉恐人员",32));
//		list.add(new AlarmInfo("6-12","124","涉稳人员",8));
//		list.add(new AlarmInfo("12-18","125","在逃人员",20));
//		list.add(new AlarmInfo("18-14","126","涉毒人员",5));
		resultMap.put("list", list);
		return SUCCESS;
	}
	
	/**
	 * 人员处所统计
	 * @return
	 */
	public String highriskPeopleChusuo(){
		
		String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();		
		
		Date startDay=DateFmtUtil.longToDate(startTime);
		Date endDay=DateFmtUtil.longToDate(endTime);
//		List<String> placeCodes=new ArrayList<String>();
		List<AlarmInfo> resultForHotel = highriskPersonAnalyzeService.findHPersonsCountByPeopleTypeForHotel(startDay, endDay, peopleTypeCodes);
		for(AlarmInfo hr : resultForHotel) {
			hr.setNameAdd1("旅馆");
			hr.setName(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, hr.getNameCode(), null).getName());
		}

		List<AlarmInfo> resultForBar = highriskPersonAnalyzeService.findHPersonsCountByPeopleTypeForInternetBar(startDay, endDay, peopleTypeCodes);
		for(AlarmInfo br : resultForBar) {
			br.setNameAdd1("网吧");
			br.setName(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, br.getNameCode(), null).getName());
		}
		List<AlarmInfo> list = new ArrayList<AlarmInfo>();
		list.addAll(resultForHotel);
		list.addAll(resultForBar);
//		//TODO 以下为假数据
//		List<AlarmInfo> list = new ArrayList<AlarmInfo>();
//		list.add(new AlarmInfo("涉恐","123","旅馆",50));
//		list.add(new AlarmInfo("涉稳","124","网吧",30));
//		list.add(new AlarmInfo("在逃","125","市场",20));
//		list.add(new AlarmInfo("涉毒","126","市场",7));
		resultMap.put("list",list);
		return SUCCESS;
	}
	
	/**
	 * 统计核查相关的信息
	 * @return
	 */
	public String findPersonCheckStatistics(){
		List<PersonCheckInfo> personCheckInfoList = highriskPeopleStatisticsService.statisticsCheckPeopleInfo(DateFmtUtil.longToDate(startTime), DateFmtUtil.longToDate(endTime));
		personCheckStatisticsInfoBeanList = new ArrayList<PersonCheckStatisticsInfoBean>();
		boolean existFlag = false;
		for(PersonCheckInfo info : personCheckInfoList){
			existFlag = false;
			for(PersonCheckStatisticsInfoBean bean : personCheckStatisticsInfoBeanList){
				if(bean.getCheckUnitCode().equals(info.getUnitCode())){
					existFlag = true;
					incrementMachine(info, bean);
					break;
				}
			}
			if(!existFlag){
				PersonCheckStatisticsInfoBean bean = new PersonCheckStatisticsInfoBean(info.getUnitCode(), info.getUnitName());
				incrementMachine(info, bean);
				personCheckStatisticsInfoBeanList.add(bean);
			}
		}
		return SUCCESS;
	}
	
	private void incrementMachine(PersonCheckInfo info, PersonCheckStatisticsInfoBean bean){
		bean.sumIncrement();
		if(DICT.DICT_YES.getValue().equals(info.getInvolveDrug())){
			bean.drugRelatedPersonIncrement();
		}
		if(DICT.DICT_YES.getValue().equals(info.getInvolveCriminalRecord())){
			bean.criminalRecordIncrement();
		}
		if(DICT.DICT_YJLX_ORANGE.getValue().equals(info.getColorType())){
			bean.orangeIncrement();
		}
		if(DICT.DICT_YJLX_WHITE.getValue().equals(info.getColorType())){
			bean.whiteIncrement();
		}
	}
	
	public String findPersonChangeRecord(){
		Map<String, Object> recordMap = highriskPersonHistoryInfoService.statisticsHighPersonHistoryInfo(DateFmtUtil.longToDate(startTime), DateFmtUtil.longToDate(endTime));
		Map<String, Object> warnTypeMap = (HashMap<String, Object>)recordMap.get("highriskPersonAdjustment");
		if(warnTypeMap!=null){
			Iterator it = warnTypeMap.entrySet().iterator();
			StringBuffer warnStr = new StringBuffer();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				String key = entry.getKey().toString();
				DictionaryItem dic = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.YJLX, key, Constant.ENABLED);
				if(dic!=null){
					warnStr.append(dic.getName());
					Map<String, Object> value = (HashMap<String, Object>)entry.getValue();
					Iterator it2 = value.entrySet().iterator();
					while(it2.hasNext()){
						Map.Entry entry2 = (Map.Entry)it2.next();
						String key2 = entry2.getKey().toString();
						String value2 = entry2.getValue().toString();
						warnStr.append(key2).append(value2).append("人");
					}
					warnStr.append("，");
				}
			}
			if(warnStr.length()>0){
				warnTypeStatistic = warnStr.insert(0, "共调整"+warnTypeMap.get("totalNum").toString()+"人，").substring(0, warnStr.length()-1)+'。';
			}
		}
		Map<String, Object> peopleTypeMap = (HashMap<String, Object>)recordMap.get("personTypeAdjustment");
		if(peopleTypeMap!=null){
			Iterator it = peopleTypeMap.entrySet().iterator();
			StringBuffer peopleTypeStr = new StringBuffer();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				String key = entry.getKey().toString();
				DictionaryItem dic = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.RYLX, key, Constant.ENABLED);
				if(dic!=null){
					peopleTypeStr.append(dic.getName());
					Map<String, Object> value = (HashMap<String, Object>)entry.getValue();
					Iterator it2 = value.entrySet().iterator();
					while(it2.hasNext()){
						Map.Entry entry2 = (Map.Entry)it2.next();
						String key2 = entry2.getKey().toString();
						String value2 = entry2.getValue().toString();
						peopleTypeStr.append(key2).append(value2).append("人");
					}
					peopleTypeStr.append("，");
				}
			}
			if(peopleTypeStr.length()>0){
				peopleTypeStatistic = peopleTypeStr.insert(0, "共调整"+peopleTypeMap.get("totalNum")+"人，").substring(0, peopleTypeStr.length()-1)+'。';
			}
		}
		Map<String, Object> newAddHighriskPersonMap = (HashMap<String, Object>)recordMap.get("newAddHighriskPerson");
		if(null != newAddHighriskPersonMap &&  Integer.valueOf(newAddHighriskPersonMap.get("totalNum").toString()) > 0){
			Iterator it = newAddHighriskPersonMap.entrySet().iterator();
			StringBuffer peopleTypeStr = new StringBuffer("其中");
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				if(!entry.getKey().toString().equals("totalNum")){
					peopleTypeStr.append(entry.getKey()).append(entry.getValue().toString()).append("人,");
				}
			}
			if(peopleTypeStr.length()>2){
				newAddHighriskPerson = peopleTypeStr.insert(0, "共新增"+newAddHighriskPersonMap.get("totalNum")+"人，").substring(0, peopleTypeStr.length()-1)+'。';
			}else{
				newAddHighriskPerson = "共新增"+newAddHighriskPersonMap.get("totalNum")+"人。";
			}
		}
		return SUCCESS;
	}
	
	
	/**
	 * 根据身份证号查询人员
	 * @return
	 */
	public String findPersonByIdcode(){
		HighriskPerson person = highriskPersonService.findByIdCode(idcode);
		if(person!=null){
			highriskPersonBean = modelBeanTransformUtil.transformHighriskPersonBean(person);
		}
		return SUCCESS;
	}
	/**
	 * 根据人员Id查询人员
	 * @return
	 */
	public String findPersonByPersonId(){
		HighriskPerson person = highriskPersonService.findHighriskPersonById(id);
		if(person!=null){
			highriskPersonBean = modelBeanTransformUtil.transformHighriskPersonBean(person);
		}
		return SUCCESS;
	}
	
	public String applyApprove(){
		HighriskPerson person = highriskPersonService.findByIdCode(idcode);
		saveOperationRecord(person,"","提交审批");
		person.setOperateStatus(Constant.CZZT_DSP);
		highriskPersonService.updateHighriskPerson(person);
		return SUCCESS;
	}
	
	public String approvePerson(){
		HighriskPerson person = highriskPersonService.findByIdCode(idcode);
		saveOperationRecord(person,approveContent,isPass?"审批通过":"审批驳回");
		person.setOperateStatus(isPass?Constant.CZZT_SPTG:Constant.CZZT_SPBH);
		highriskPersonService.updateHighriskPerson(person);
		return SUCCESS;
	}
	public String findOperationRecord(){
		HighriskPerson person = highriskPersonService.findByIdCode(idcode);
		List<OperationRecord> records = highriskPersonService.findOperationRecordByHighrishPeople(person.getId());
		operationRecordBeanList = new ArrayList<OperationRecordBean>();
		for(OperationRecord record : records){
			OperationRecordBean bean = modelBeanTransformUtil.transformOperationRecordBean(record);
			operationRecordBeanList.add(bean);
		}
		return SUCCESS;
	}
	public void saveOperationRecord(HighriskPerson person,String content,String result){
		OperationRecord record = new OperationRecord(person);
		record.setOperateTime(new Date());
		record.setContent(content);
		record.setResult(result);
		record.setOperateUnit(findCurrentOrganization().getId());
		record.setOperator(findCurrentPerson().getId());
		operationRecordService.saveOperationRecord(record);
	}
	
	/**
	 * 积分预警人员载入
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findHPersonScoreByPage(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		IntegralModel model = integralModelService.findIntegralModelStatusOn() ;
		Pager<Map<HighriskPerson, Double>> pager=highriskPersonScoreAnalyzeService.findHPersonScoreByPage((Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("length"))),Integer.parseInt(String.valueOf( data.get("length"))), Double.valueOf(model!=null?model.getAlertPoint():0));
		List<PersonIntegralBean> list = new ArrayList<PersonIntegralBean>();
		if(pager == null || pager.getPageList().isEmpty()){
			resultMap.put("totalNum", 0);	
			resultMap.put("personBean", list);
			return SUCCESS;
		}
		for (Map<HighriskPerson, Double> peopleMap : pager.getPageList()) {
			HighriskPerson people = peopleMap.entrySet().iterator().next().getKey() ;
			Double score = peopleMap.get(people);
			
			PersonIntegralBean personBean =new PersonIntegralBean();
			personBean.setId(people.getId());
			personBean.setIdcode(people.getIdcode());
			personBean.setAccumulatePoints(Integer.valueOf(score.toString()));
			personBean.setName(people.getName());
			list.add(personBean);
		}
		
		resultMap.put("totalNum", pager.getTotalNum());	
		resultMap.put("personBean", list);
		return SUCCESS;
	}
	
	
	
	/**
	 * 高危人员model转bean
	 *
	 * @return fcpb 五色预警人员bean
	 */
//	public HighriskPersonBean transformHighriskPersonBean(HighriskPerson model) {
//		if (model == null) {
//			return null;
//		}
//		HighriskPersonBean bean = new HighriskPersonBean();
//		BeanUtils.copyProperties(model, bean);
//		bean.setCreatedTime(DateFmtUtil.dateToLong(model.getCreatedTime()));
//		bean.setFeedbackTime(DateFmtUtil.dateToLong(model.getFeedbackTime()));
//		bean.setSexName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.XB,model.getSex()));
//		bean.setWarnTypeName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.YJLX,model.getWarnType()));
//
//		List<String> phone = new ArrayList<String>();
//		List<String> mac = new ArrayList<String>();
//		for(MobilePhoneInfo mobile : model.getMobilePhoneInfos()){
//			phone.add(StringUtils.isEmpty(mobile.getNumber())?"":mobile.getNumber());
//			mac.add(StringUtils.isEmpty(mobile.getMac())?"":mobile.getMac());
//		}
//		bean.setStatusName("0".equals(model.getStatus())?"正常":"0".equals(model.getStatus())?"异常":"");
//		bean.setMac(mac);
//		bean.setPhone(phone);
//		StringBuffer typeNames = new StringBuffer();
//		StringBuffer recordNames = new StringBuffer();
//		for(HighriskPeopleType type : model.getHighriskPeopleTypes()){
//			typeNames.append(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.RYLX, type.getPeopleType())).append("、");
//			for(HighriskCriminalRecord record : type.getHighriskCriminalRecords()){
//				recordNames.append(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.QKLX,record.getCriminalRecord())).append("、");
//			}
//		}
//		bean.setPeopleTypeName(typeNames.length()>0?typeNames.substring(0,typeNames.length()-1).toString():"");
//		bean.setPeopleType(recordNames.length()>0?recordNames.substring(0,recordNames.length()-1).toString():"");
//		return bean;
//	}
	
	public String getIdcode() {
		return idcode;
	}
	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}
	public HighriskPersonBean getHighriskPersonBean() {
		return highriskPersonBean;
	}
	public void setHighriskPersonBean(HighriskPersonBean highriskPersonBean) {
		this.highriskPersonBean = highriskPersonBean;
	}
	public List<HighriskPersonBean> getHighriskPersonBeanList() {
		return highriskPersonBeanList;
	}
	public void setHighriskPersonBeanList(
			List<HighriskPersonBean> highriskPersonBeanList) {
		this.highriskPersonBeanList = highriskPersonBeanList;
	}
	public List<StatisticsInfo> getStatisticsInfoList() {
		return statisticsInfoList;
	}
	public void setStatisticsInfoList(List<StatisticsInfo> statisticsInfoList) {
		this.statisticsInfoList = statisticsInfoList;
	}
	public List<PersonCheckStatisticsInfoBean> getPersonCheckStatisticsInfoBeanList() {
		return personCheckStatisticsInfoBeanList;
	}
	public void setPersonCheckStatisticsInfoBeanList(
			List<PersonCheckStatisticsInfoBean> personCheckStatisticsInfoBeanList) {
		this.personCheckStatisticsInfoBeanList = personCheckStatisticsInfoBeanList;
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
	public Pager<HighriskPersonBean> getHighriskPersonBeanPager() {
		return highriskPersonBeanPager;
	}
	public void setHighriskPersonBeanPager(
			Pager<HighriskPersonBean> highriskPersonBeanPager) {
		this.highriskPersonBeanPager = highriskPersonBeanPager;
	}
	public String getWarnTypeStatistic() {
		return warnTypeStatistic;
	}
	public void setWarnTypeStatistic(String warnTypeStatistic) {
		this.warnTypeStatistic = warnTypeStatistic;
	}
	public String getPeopleTypeStatistic() {
		return peopleTypeStatistic;
	}
	public void setPeopleTypeStatistic(String peopleTypeStatistic) {
		this.peopleTypeStatistic = peopleTypeStatistic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApproveContent() {
		return approveContent;
	}
	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}
	public boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(boolean isPass) {
		this.isPass = isPass;
	}
	public List<OperationRecordBean> getOperationRecordBeanList() {
		return operationRecordBeanList;
	}
	public void setOperationRecordBeanList(
			List<OperationRecordBean> operationRecordBeanList) {
		this.operationRecordBeanList = operationRecordBeanList;
	}
	public Map<String,Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String,Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	public String getNewAddHighriskPerson() {
		return newAddHighriskPerson;
	}
	public void setNewAddHighriskPerson(String newAddHighriskPerson) {
		this.newAddHighriskPerson = newAddHighriskPerson;
	}
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}


	public HrpScoreResult getHrpScoreResult() {
		return hrpScoreResult;
	}


	public void setHrpScoreResult(HrpScoreResult hrpScoreResult) {
		this.hrpScoreResult = hrpScoreResult;
	}


	public HrpScoreResultBean getHrpScoreResultBean() {
		return hrpScoreResultBean;
	}


	public void setHrpScoreResultBean(HrpScoreResultBean hrpScoreResultBean) {
		this.hrpScoreResultBean = hrpScoreResultBean;
	}


	public IntegralModel getIntegralModel() {
		return integralModel;
	}


	public void setIntegralModel(IntegralModel integralModel) {
		this.integralModel = integralModel;
	}


	public List<String> getPeopleTypeCodes() {
		return peopleTypeCodes;
	}


	public void setPeopleTypeCodes(List<String> peopleTypeCodes) {
		this.peopleTypeCodes = peopleTypeCodes;
	}

	
}
