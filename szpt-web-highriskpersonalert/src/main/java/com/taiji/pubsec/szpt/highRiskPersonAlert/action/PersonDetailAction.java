package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.bean.TimeIntervalBean;
import com.taiji.pubsec.szpt.bean.TopTimeBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.BaseBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HighriskCriminalRecordBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HighriskPeopleTypeBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HighriskPersonBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HitSumBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.MobilePhoneInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonCheckInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.WifiPlaceInAndOutInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.ModelBeanTransformUtilInHighRiskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.PersonCheckInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPeopleStatisticsService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonHistoryInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceCountBean;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStateRecord;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.ExcelUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.util.bean.FileObjectBean;

import net.sf.json.JSONObject;


/**
 * 人员档案
 * 
 */
@Controller("personDetailAction")
@Scope("prototype")
public class PersonDetailAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	private List<String> macList = new ArrayList<String>(); // mac地址集合

	private List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList = new ArrayList<WifiPlaceInAndOutInfoBean>(); // wifi围栏命中信息Bean集合

	private Map<String,Object> paramMap = new HashMap<String,Object>(); // 查询参数
	
	private List<HitSumBean> hitSumBeanList = new ArrayList<HitSumBean>(); // 分组统计结果Bean集合
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	private List<ZTreeBean> ztreeList;
	
	private String id;
	
	private String code;
	
	private String personId;
	
	private String idcode;
	
	private String queryStr;
	
	private Integer start;
	
	private Integer length;
	
	private HighriskPersonBean personBean;
	
	private List<MobilePhoneInfoBean> mobilePhoneInfoBeanList;
	
	private List<HighriskPeopleTypeBean> highriskPeopleTypeBeanList;
	
	private List<FileObjectBean> fileBeanList;
	
	//文件对象
	private File file ;
	//文件名
	private String fileName;
	//状态标识
	private String returnCode;
	
	@Resource
	private ModelBeanTransformUtilInHighRiskPerson modelBeanTransformUtil; //action model和bean对象转换工具
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiPlaceInAndOutInfoQueryService; // wifi围栏命中信息接口
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private IHighriskPersonHistoryInfoService highriskPersonHistoryInfoService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;
	
	@Resource
	private IOperationRecordService operationRecordService;
	
	@Resource
	private WifiStateRecordService wifiStateRecordService;
	
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	
	@Resource
	private IHighriskPeopleStatisticsService highriskPeopleStatisticsService;
	
	/**
	 * 根据高危人身份证查询查控情况
	 * @return
	 */
	public String findPersonCheckInfoByPersonIdcode(){
		List<PersonCheckInfo> pcis = highriskPeopleStatisticsService.statisticsCheckPeopleInfoByPsersonIdcode(idcode);
		
		List<PersonCheckInfoBean> pcibs = new ArrayList<PersonCheckInfoBean>();
		if(pcis == null || pcis.isEmpty()){
			resultMap.put("pcibs", pcibs);
			return SUCCESS;
		}
		for(PersonCheckInfo pci : pcis){
			PersonCheckInfoBean  pcib = new PersonCheckInfoBean();
			BeanCopier copier = BeanCopier.create(pci.getClass(), pcib.getClass(), false);
			copier.copy(pci, pcib, null);
			pcib.setInterrogatDate(DateFmtUtil.dateToLong(pci.getInterrogatDate()));
			pcib.setInvolveDrugName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_SF.getValue(), pci.getInvolveDrug()));
			pcib.setInvolveCriminalRecordName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_SF.getValue(), pci.getInvolveCriminalRecord()));
			pcib.setColorTypeName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_YJLX.getValue(), pci.getColorType()));
			pcibs.add(pcib);
		}
		resultMap.put("pcibs", pcibs);
		return SUCCESS;
	}
		
	/**
	 * 上传excel
	 * @return
	 */
	public String uploadExcel(){
		log.debug("receive uploaded file: {}", fileName);
		if(file == null) {
			log.debug("receive uploaded file is null");
		}
		List<Object[]> lst = new ArrayList<Object[]>();
		/**
		 * 按行读取Excel中数据，将每行组装成Object数组(Object[])放入list中返回。
		 * 
		 * @param file Excel文件。
		 * @param list 该list用来装从Excel中读取的Object[]。
		 * @param tableSheetIndex sheet页码。
		 * @param rowStart 从第几行开始
		 * 
		 * @param colStart 从第几列开始
		 * 
		 * @param rank 从第一列算起，需要读取的列数。
		 * @return 成功返回true;失败返回false。
		 */
		
		try {
			if(ExcelUtil.readExcel2Obj(file, lst, 0, 1, 0, 18)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				HighriskPerson hPerson = null;
				for (Object[] objects : lst) {
					this.excelLstTurnHighriskPerson(objects);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setReturnCode("ERROR");
			return SUCCESS;
		}
		setReturnCode("SUCCESS");
		return SUCCESS;
	}
	
	/**
	 * 根据mac地址查询轨迹列表（分页查询）
	 * 
	 * @param macList mac地址集合
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	public String findLocusByMacs() {
		
		Map<String, Object> data = JSONObject.fromObject(queryStr);
		String idcode =  String.valueOf(data.get("idcode"));
		if(null == idcode){
			return SUCCESS;
		}
		
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(data.get("startTime").toString())) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(data.get("endTime").toString())) ;
		macList =  (List<String>)data.get("macList");
		Integer pageNo=(Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("pageSize")));
		data.put("pageNo",pageNo);
		
		if(macList.isEmpty() || startDay == null || endDay == null){
			resultMap.put("totalNum", 0);
			resultMap.put("list", new ArrayList<WifiStateRecord>());
			return SUCCESS;
		}
		Pager<WifiStateRecord> pager = wifiStateRecordService.findByMacs(macList, startDay,endDay,pageNo ,Integer.valueOf(data.get("pageSize").toString()));
		resultMap.put("list", pager.getPageList());
		resultMap.put("totalNum", pager.getTotalNumber().intValue());
		
		return SUCCESS;
	}
	
	/**
	 * 根据mac地址查询轨迹列表（分页查询）
	 * 
	 * @param macList mac地址集合
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	public String findLocusMapByMacs() {
		Date startDay = DateFmtUtil.longToDate(startTime) ;
		Date endDay = DateFmtUtil.longToDate(endTime) ;
		if(macList.isEmpty() || startDay == null || endDay == null){
			resultMap.put("totalNum", 0);
			resultMap.put("list", new ArrayList<WifiStateRecord>());
			return SUCCESS;
		}
		List<WifiStateRecord> list = wifiStateRecordService.findByMacs(macList, startDay, endDay);
		resultMap.put("list", list);
		resultMap.put("totalNum", list.size());
		
		return SUCCESS;
	}
	
	
	/**
	 * 查询某人员某时间段经过次数最多的场所
	 * 
	 * @param paramMap 查询条件</br>条件包括mac地址列表、起始时间、结束时间。
	 * macList mac地址列表
	 * startTime 开始时间
	 * endTime 结束时间
	 * @return "success"，成功返回hitSumBeanList:分组统计结果bean集合
	 */
	public String findPlaceSumByMacAndTime(){
		if(macList==null||macList.isEmpty()){
			List<TopTimeBean> list= new ArrayList<TopTimeBean>();
			resultMap.put("list", list) ;
			return SUCCESS;
		}
		Integer descNum=3;
		Date startDay = DateFmtUtil.longToDate(startTime) ;
		Date endDay = DateFmtUtil.longToDate(endTime) ;
		List<PlaceCountBean> placeCountBean = wifiStateRecordService.findByMacsByTopPlaceAndTimes(macList, startDay, endDay, descNum);
		if(ParamMapUtil.isNotBlank(placeCountBean)){
			List<String> placeCodes = new ArrayList<String>();
			List<TopTimeBean> list= new ArrayList<TopTimeBean>();
			for (PlaceCountBean bean : placeCountBean) {
				PlaceBasicInfo p = placeBasicInfoService.findByCode(bean.getPlaceCode());
				if(p != null){
					bean.setPlaceName(p.getInternetServicePlaceName());
				}
				placeCodes.add(bean.getPlaceCode());
				list.add(new TopTimeBean(bean.getPlaceName(),bean.getPlaceCode(),bean.getCount(),bean.getStayTime()));
			}
			
			List<WifiStateRecord> WifiStateRecord=wifiStateRecordService.findDetailByPlaceByMacs(macList, placeCodes, startDay, endDay);
			for (TopTimeBean topTimeBean : list) {
				for (WifiStateRecord wifiStateRecordBean : WifiStateRecord) {
					if(topTimeBean.getPlaceCode().equals(wifiStateRecordBean.getPlaceCode())){
						TimeIntervalBean bean = new TimeIntervalBean();
						bean.setEnterTime(wifiStateRecordBean.getEnterTime());
						bean.setLeaveTime(wifiStateRecordBean.getLeaveTime());
						bean.setMacAddress(wifiStateRecordBean.getMac());
						topTimeBean.getTimeIntervalList().add(bean);
					}
					
				}	
			}
			resultMap.put("list", list) ;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询某人员某时间段驻留时间最长的场所
	 * 
	 * @param paramMap 查询条件</br>条件包括mac地址列表、起始时间、结束时间。
	 * macList mac地址列表
	 * startTime 开始时间
	 * endTime 结束时间
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	public String findMaxTimePlaceByMacAndTime(){
		if(macList==null||macList.isEmpty()){
			List<TopTimeBean> list= new ArrayList<TopTimeBean>();
			resultMap.put("list", list) ;
			return SUCCESS;
		}
		Date startDay = DateFmtUtil.longToDate(startTime) ;
		Date endDay = DateFmtUtil.longToDate(endTime) ;
		
		Integer descNum=3;
		
		List<PlaceCountBean> placeCountBean = wifiStateRecordService. findByMacsByTopAndStayeTime(macList, startDay, endDay, descNum);
		
		if(ParamMapUtil.isNotBlank(placeCountBean)){
			List<String> placeCodes = new ArrayList<String>();
			List<TopTimeBean> list= new ArrayList<TopTimeBean>();
			for (PlaceCountBean bean : placeCountBean) {
				PlaceBasicInfo p = placeBasicInfoService.findByCode(bean.getPlaceCode());
				if(p != null){
					bean.setPlaceName(p.getInternetServicePlaceName());
				}
				placeCodes.add(bean.getPlaceCode());
				list.add(new TopTimeBean(bean.getPlaceName(),bean.getPlaceCode(),bean.getCount(),bean.getStayTime()));
			}
			List<WifiStateRecord> WifiStateRecord=wifiStateRecordService.findDetailByPlaceByMacs(macList, placeCodes, startDay, endDay);
			for (TopTimeBean topTimeBean : list) {
				for (WifiStateRecord wifiStateRecordBean : WifiStateRecord) {
					if(topTimeBean.getPlaceCode().equals(wifiStateRecordBean.getPlaceCode())){
						TimeIntervalBean bean = new TimeIntervalBean();
						bean.setEnterTime(wifiStateRecordBean.getEnterTime());
						bean.setLeaveTime(wifiStateRecordBean.getLeaveTime());
						bean.setMacAddress(wifiStateRecordBean.getMac());
						topTimeBean.getTimeIntervalList().add(bean);
					}
				}	
			}
			resultMap.put("list", list) ;
		}
		return SUCCESS;
	}
	/**
	 * 查看下级字典项
	 * @return
	 */
    public String findSubDics(){
    	List<DictionaryItem> dics = new ArrayList<DictionaryItem>();
    	if(code.equals(Constant.RYLX_QD)||code.equals(Constant.RYLX_QJ)||code.equals(Constant.RYLX_ZP)||code.equals(Constant.RYLX_DQ)){
    		dics = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.QKLX, code);
    	}else{
    		dics = dictionaryItemService.findAllSubDictionaryItemsByDicTypeAndParent(Constant.RYLX, code);
    	}
		ztreeList = new ArrayList<ZTreeBean>() ;
		for(DictionaryItem dic:dics){
			ZTreeBean bean = new ZTreeBean();
			if(dic.getCode().equals(code)){
				continue;
			}
			bean.setpId(dic.getParentItem()!=null?dic.getParentItem().getCode():"");
			bean.setId(dic.getCode());
			
			if(dic.getSubItems().size()>0){
				bean.setIsParent("true");
//				bean.setOpen("true");
			}else{
				bean.setIsParent("false");
			}
			bean.setName(dic.getName());
			
			Map<Object, Object> diyMap = new HashMap<Object, Object>(0) ;
//			diyMap.put("code", entity.getCode());
//			diyMap.put("level", entity.getLevel());
			diyMap.put("code", dic.getCode());
			
			bean.setDiyMap(diyMap);
			
			ztreeList.add(bean) ;
		}
		return SUCCESS ;
	}
    /**
	 * 查看人员类型跟电话
	 * @return
	 */
    public String findPersonTypeAndMobile(){
    	HighriskPerson person = new HighriskPerson();
    	if(personId==null){
    		person = highriskPersonService.findByIdCode(code);
    	}else{
    		person = highriskPersonService.findHighriskPersonById(personId);
    	}
    	if(person==null){
    		return SUCCESS ;
    	}
    	personBean = modelBeanTransformUtil.transformHighriskPersonBean(person);
    	if(person.getOperateStatus()!=null&&person.getOperateStatus().equals(Constant.CZZT_SPBH)){
    		List<OperationRecord> records = highriskPersonService.findOperationRecordByHighrishPeople(person.getId());
    		if(records.size()>0){
    			OperationRecordBean bean = modelBeanTransformUtil.transformOperationRecordBean(records.get(0));
    			personBean.setApproveContent(bean.getContent());
    			personBean.setApprovePeople(bean.getOperatorName());
    			personBean.setApproveTime(bean.getOperateTime());
    		}
    	}
//    	BeanUtils.copyProperties(person, personBean);
    	mobilePhoneInfoBeanList = new ArrayList<MobilePhoneInfoBean>();
    	for(MobilePhoneInfo mobile : person.getMobilePhoneInfos()){
    		MobilePhoneInfoBean bean = new MobilePhoneInfoBean();
    		BeanUtils.copyProperties(mobile, bean);
    		mobilePhoneInfoBeanList.add(bean);
    	}
    	highriskPeopleTypeBeanList = new ArrayList<HighriskPeopleTypeBean>();
    	for(HighriskPeopleType type : person.getHighriskPeopleTypes()){
    		HighriskPeopleTypeBean bean = new HighriskPeopleTypeBean();
    		BeanUtils.copyProperties(type, bean);
    		bean.setPeopleTypeName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.RYLX, type.getPeopleType()));
    		List<HighriskCriminalRecordBean> records = new ArrayList<HighriskCriminalRecordBean>();
    		for(HighriskCriminalRecord record : type.getHighriskCriminalRecords()){
    			HighriskCriminalRecordBean recordbean = new HighriskCriminalRecordBean();
    			BeanUtils.copyProperties(record, recordbean);
    			records.add(recordbean);
    		}
    		bean.setHighriskCriminalRecord(records);
    		highriskPeopleTypeBeanList.add(bean);
    	}
    	
    	List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(person.getId(), HighriskPerson.class.getName());
    	fileBeanList = new ArrayList<FileObjectBean>();
		for (Attachment att : attLst) {
			FileObjectBean fBeam = new FileObjectBean();
			fBeam.setId(att.getId());
			fBeam.setName(att.getName());
			fBeam.setSize(String.valueOf(att.getSize()));
			fileBeanList.add(fBeam);
		}
    	return SUCCESS ;
    }
    /**
	 * 人员打标
	 * @return
	 */
    public String updateHighriskPerson(){
    	if(StringUtils.isEmpty(personBean.getId())){
    		HighriskPerson newPerson = new HighriskPerson();
    		newPerson.setIdcode(personBean.getIdcode());
    		newPerson.setWarnType(personBean.getWarnType());
    		Organization oz = this.findCurrentOrganization();
    		if(oz != null){
    			newPerson.setCreatorDepartmentId(oz.getId());
    		}
    		highriskPersonService.createHighriskPerson(newPerson);
    		personBean.setId(newPerson.getId());
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("highriskPersonId", personBean.getId());
    	map.put("highriskPersonNickname", personBean.getNickname());
    	map.put("personInControlType", personBean.getPersonInControlType());
    	map.put("liveAddress", personBean.getLiveAddress());
    	map.put("marriageStatus", personBean.getMarriageStatus());
    	map.put("employmentStatus", personBean.getEmploymentStatus());
    	map.put("workAddress", personBean.getWorkAddress());
    	map.put("profession", personBean.getProfession());
    	map.put("income", personBean.getIncome());
    	if(personBean.getCreatorDepartmentId() != null){
    		map.put("creatorDepartmentId", personBean.getCreatorDepartmentId());
    	}
    	if(personBean.getName()!=null){
    		map.put("name", personBean.getName());
    	}
    	if(personBean.getRegisterAddress()!=null){
    		map.put("registerAddress", personBean.getRegisterAddress());
    	}
    	if(personBean.getSex()!=null){
    		map.put("sex", personBean.getSex());
    	}
    	if(personBean.getWarnType()!=null){
    		map.put("warnType", personBean.getWarnType());
    	}
    	if(personBean.getUsedName()!=null){
    		map.put("usedName", personBean.getUsedName());
    	}
    	if(personBean.getEthnicGroup()!=null){
    		map.put("ethnicGroup", personBean.getEthnicGroup());
    	}
    	if(personBean.getEducation()!=null){
    		map.put("education", personBean.getEducation());
    	}
    	if(personBean.getBirthday()!=null){
    		map.put("birthday", DateFmtUtil.longToDate(personBean.getBirthday()));
    	}
    	if(personBean.getOperateStatus()!=null){
    		map.put("operateStatus", personBean.getOperateStatus());
    	}
    	if(personBean.getAddReason()!=null){
    		map.put("addReason", personBean.getAddReason());
    	}
    	HighriskPerson person = highriskPersonService.findHighriskPersonById(personBean.getId());
    	if(highriskPeopleTypeBeanList!=null){
    		List<HighriskPeopleType> typeList = new ArrayList<HighriskPeopleType>();
    		for(HighriskPeopleTypeBean typeBean : highriskPeopleTypeBeanList){
    			HighriskPeopleType type = new HighriskPeopleType();
    			BeanUtils.copyProperties(typeBean, type);
    			type.setHighriskPerson(person);
    			if(typeBean.getHighriskCriminalRecord()!=null&&!typeBean.getHighriskCriminalRecord().isEmpty()){
    				List<HighriskCriminalRecord> recordList = new ArrayList<HighriskCriminalRecord>();
    				for(HighriskCriminalRecordBean recordBean : typeBean.getHighriskCriminalRecord()){
    					HighriskCriminalRecord record = new HighriskCriminalRecord();
    					BeanUtils.copyProperties(recordBean, record);
    					recordList.add(record);
    				}
    				Set<HighriskCriminalRecord> set = new java.util.HashSet<HighriskCriminalRecord>();
    				for(HighriskCriminalRecord record: recordList){
    					set.add(record);
    				}
    				type.setHighriskCriminalRecords(set);
    			}
    			typeList.add(type);
    		}
    		map.put("highriskPersonTypeList", typeList);
    	}else{
    		map.put("highriskPersonTypeList", new ArrayList());
    	}
    	if(mobilePhoneInfoBeanList!=null){
    		List<MobilePhoneInfo> phoneList = new ArrayList<MobilePhoneInfo>();
    		for(MobilePhoneInfoBean phoneBean : mobilePhoneInfoBeanList){
    			MobilePhoneInfo phone = new MobilePhoneInfo();
    			BeanUtils.copyProperties(phoneBean, phone);
    			phone.setHighriskPerson(person);
    			phoneList.add(phone);
    		}
    		map.put("mobilePhoneInfoList", phoneList);
    	}
    	highriskPersonService.createHighriskPersonMark(map);
    	if(fileBeanList!=null){
    		for (FileObjectBean fob : fileBeanList) {
    			Attachment att = attachmentCustomizedService.findById(fob.getId());
    			att.setTargetId(personBean.getId());
    			att.setType(HighriskPerson.class.getName());
    			attachmentCustomizedService.save(att);
    		}
    	}
    	if(personBean.getOperateStatus()!=null){
    		if(personBean.getOperateStatus().equals(Constant.CZZT_DSP)){
    			saveOperationRecord(person,"","提交审批");
        	}else if(personBean.getOperateStatus().equals(Constant.CZZT_XZ)){
        		saveOperationRecord(person,"","新增");
        	}
    	}
    	return SUCCESS ;
    }
    /**
     * 删除附件
     * @return
     */
    public String deleteAttachment(){
    	attachmentCustomizedService.delete(id);
    	return SUCCESS ;
    }
	/**
	 * 分组统计结果model转bean
	 * 
	 * @param hs 分组统计结果model
	 * @param paramMap 查询条件</br>条件包括mac地址列表、起始时间、结束时间。
	 * macList mac地址列表
	 * startTime 开始时间
	 * endTime 结束时间
	 * placeName 场所名称
	 * @return hsb 分组统计结果bean
	 */
	public HitSumBean hitSumToHitSumBean(PlaceSumInfo hs, Map<String, Object> paramMap){
		if(hs == null){
			return null;
		}
		HitSumBean hsb = new HitSumBean();
		hsb.setCount(hs.getCount());
		hsb.setGroupName(hs.getGroupName());
		hsb.setTotalInterval(hs.getTotalInterval());
		//查询时间列表
		paramMap.put("placeName", hs.getGroupName());
		List<WifiPlaceInAndOutInfo> wfhList = wifiPlaceInAndOutInfoQueryService.findAllByMacs(paramMap);
		List<BaseBean> timeIntervalList = new ArrayList<BaseBean>();
		for(WifiPlaceInAndOutInfo wfh : wfhList){
			if(wfh.getEnterTime() == null || wfh.getLeaveTime() == null){
				continue;
			}
			BaseBean bb = new BaseBean();
			bb.setStartTime(DateFmtUtil.dateToLong(wfh.getEnterTime()));
			bb.setEndTime(DateFmtUtil.dateToLong(wfh.getLeaveTime()));
			timeIntervalList.add(bb);
		}
		hsb.setTimeIntervalList(timeIntervalList);
		return hsb;
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
	
	//excle文件转高危 人员,然后保存或更新
	private void excelLstTurnHighriskPerson(Object[]  objects){
		HighriskPerson hPerson = null;
		boolean flag = true;
		if(ParamMapUtil.isNotBlank(objects[8])){
			String idcode = objects[8].toString();
			hPerson = highriskPersonService.findByIdCode(idcode);
		}
		
		if(null == hPerson){
			hPerson = new HighriskPerson();
			hPerson.setOperateStatus(Constant.YJJLZT_WCL);
			flag = false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				if(ParamMapUtil.isNotBlank(objects[0])){
					//出生日期
					hPerson.setBirthday(sdf.parse(objects[0].toString()));
					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//国籍
			hPerson.setNationality(objects[1].toString());
			//户籍地派出所
			hPerson.setRegisterAddressPoliceStation(String.valueOf(objects[2]));
			//户籍地
			hPerson.setRegisterAddress(String.valueOf(objects[3]));
			//户籍地详址
			hPerson.setRegisterAddressDetail(String.valueOf(objects[4]));
			//籍贯
			hPerson.setOrigo(String.valueOf(objects[5]));
			//民族
			hPerson.setEthnicGroup(String.valueOf(objects[6]));
			//身份证号
			hPerson.setIdcode(String.valueOf(objects[8]));
			
			if(null != String.valueOf(objects[9])){
				for(int i = 0 ; i < Constant.XB_XB.values().length ; i++){
					String key = Constant.XB_XB.values()[i].getKey().toString();
					if(String.valueOf(objects[9]).contains(key)){
						//性别
						hPerson.setSex( Constant.XB_XB.values()[i].getValue().toString());
					}
				}
			}else{
				hPerson.setSex( Constant.XB_XB.WEIZHI.getValue().toString());
			}
			//姓名
			hPerson.setName(String.valueOf(objects[10]));
			//现住地详址
			hPerson.setLocalAddressDetail(String.valueOf(objects[12]));
			//在控类型
			hPerson.setPersonInControlType(String.valueOf(objects[16]));
			try {
				if(ParamMapUtil.isNotBlank(objects[17])){
					//在控登记时间 
					hPerson.setInControlaRegisterTime(sdf.parse(objects[17].toString()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			/* 
			 * 其中标中第H列"前科记录",
			 * L列 "现住地派出所",
			 * N列"重点人员类别标记",
			 * O列"重点人员细类",
			 * P列"问题数据" 
			 * 没有对应的字段
			 */
			if(flag){
				highriskPersonService.updateHighriskPerson(hPerson);
			}else{
				highriskPersonService.createHighriskPerson(hPerson);
			}
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public List<String> getMacList() {
		return macList;
	}

	public void setMacList(List<String> macList) {
		this.macList = macList;
	}

	public List<WifiPlaceInAndOutInfoBean> getWifiPlaceInAndOutInfoBeanList() {
		return wifiPlaceInAndOutInfoBeanList;
	}

	public void setWifiPlaceInAndOutInfoBeanList(
			List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList) {
		this.wifiPlaceInAndOutInfoBeanList = wifiPlaceInAndOutInfoBeanList;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<HitSumBean> getHitSumBeanList() {
		return hitSumBeanList;
	}

	public void setHitSumBeanList(List<HitSumBean> hitSumBeanList) {
		this.hitSumBeanList = hitSumBeanList;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public HighriskPersonBean getPersonBean() {
		return personBean;
	}

	public void setPersonBean(HighriskPersonBean personBean) {
		this.personBean = personBean;
	}

	public List<MobilePhoneInfoBean> getMobilePhoneInfoBeanList() {
		return mobilePhoneInfoBeanList;
	}

	public void setMobilePhoneInfoBeanList(
			List<MobilePhoneInfoBean> mobilePhoneInfoBeanList) {
		this.mobilePhoneInfoBeanList = mobilePhoneInfoBeanList;
	}

	public List<HighriskPeopleTypeBean> getHighriskPeopleTypeBeanList() {
		return highriskPeopleTypeBeanList;
	}

	public void setHighriskPeopleTypeBeanList(
			List<HighriskPeopleTypeBean> highriskPeopleTypeBeanList) {
		this.highriskPeopleTypeBeanList = highriskPeopleTypeBeanList;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public List<FileObjectBean> getFileBeanList() {
		return fileBeanList;
	}

	public void setFileBeanList(List<FileObjectBean> fileBeanList) {
		this.fileBeanList = fileBeanList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
}
