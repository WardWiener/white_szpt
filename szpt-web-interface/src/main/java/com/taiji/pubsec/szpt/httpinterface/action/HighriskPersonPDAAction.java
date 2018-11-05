package com.taiji.pubsec.szpt.httpinterface.action;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.collect.TreeMultiset;
import com.opensymphony.xwork2.Result;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.httpinterface.action.bean.DictionaryItemInterfaceBean;
import com.taiji.pubsec.szpt.httpinterface.action.bean.HighriskPeopleTypeBean;
import com.taiji.pubsec.szpt.httpinterface.action.bean.HighriskPersonBean;
import com.taiji.pubsec.szpt.httpinterface.action.bean.MobilePhoneInfoBean;
import com.taiji.pubsec.szpt.util.PageCommonAction;



@Controller
@Scope("prototype")
public class HighriskPersonPDAAction extends PageCommonAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;	//id
	
	private HighriskPersonBean highriskPersonBean = new HighriskPersonBean();	//高危人员
	
	private List<HighriskPersonBean> highriskPersonBeanList = new ArrayList<>();	//高危人员列表
	
	private Map<String, Object> highriskPersonBeanListMap = new HashMap<String, Object>();	//高危人员列表
	
	private List<MobilePhoneInfoBean> mobilePhoneInfoBeanList = new ArrayList<>();	//终端设备信息
	
	private List<HighriskPeopleTypeBean> highriskPeopleTypeBeanList = new ArrayList<HighriskPeopleTypeBean>();	//高危人员类型
	
	private List<String> warningTypeList = new ArrayList<>();	//预警类型列表
	
	private List<String> personTypeList = new ArrayList<>();	//人员类型列表
	
	private Map<String, String> flag = new HashMap<>();
	
	private Map<String, List<DictionaryItemInterfaceBean>> dictionaryItemLstMap = new HashMap<String, List<DictionaryItemInterfaceBean>>();	//字典项
	
	private String dictionaryType;	//字典类型code
	
	private List<String> dictionaryTypes = new ArrayList<String>(); //字典类型codeList
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	/**
	 * 创建高危人员				-----ok
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String saveHighriskPerson () throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		prepareTestData();
		Map<String, Object> map = new HashMap<String, Object>();
		HighriskPerson highriskPerson = new HighriskPerson();
		BeanUtils.copyProperties(highriskPerson, highriskPersonBean);
		map.put("highriskPerson", highriskPerson);
		List<MobilePhoneInfo> mobilePhoneInfoList = new ArrayList<>();
		for (MobilePhoneInfoBean mobilePhoneInfoBean : highriskPersonBean.getMobilePhoneInfoBeans()) {
			MobilePhoneInfo mobilePhoneInfo = new MobilePhoneInfo();
			PropertyUtils.copyProperties(mobilePhoneInfo, mobilePhoneInfoBean);
			mobilePhoneInfoList.add(mobilePhoneInfo);
		}
		map.put("mobilePhoneInfoList", mobilePhoneInfoList);
		List<HighriskPeopleType> highriskPeopleTypeList = new ArrayList<>();
		for (HighriskPeopleTypeBean highriskPeopleTypeBean : highriskPersonBean.getHighriskPeopleTypeBeans()) {
			HighriskPeopleType highriskPeopleType = new HighriskPeopleType();
			BeanUtils.copyProperties(highriskPeopleType, highriskPeopleTypeBean);
			List<HighriskCriminalRecord> highriskCriminalRecordList = new ArrayList<>();
			for (String str : highriskPeopleTypeBean.getCriminalRecords()) {
				HighriskCriminalRecord highriskCriminalRecord = new HighriskCriminalRecord();
				highriskCriminalRecord.setCriminalRecord(str);
				highriskCriminalRecordList.add(highriskCriminalRecord);
			}
			Set<HighriskCriminalRecord> set = new HashSet<HighriskCriminalRecord>();
			for(HighriskCriminalRecord record: highriskCriminalRecordList){
				set.add(record);
			}
			highriskPeopleType.setHighriskCriminalRecords(set);
			highriskPeopleTypeList.add(highriskPeopleType);
		}
		map.put("highriskPeopleTypeList", highriskPeopleTypeList);
//		HighriskPerson person = highriskPersonService.findHighriskPersonByIdCardNo(highriskPersonBean.getIdcode());
//		if(person == null){
//			highriskPersonService.createHighriskPersonForMobile(map);
//		}else{
//			highriskPersonService.updateHighriskPersonForMobile(map);
//		}
		highriskPersonService.createHighriskPersonForMobile(map);
		this.setResult(highriskPerson.getId());
		flag.put("result", getResult());
		return SUCCESS;
	}
	
	/**
	 * 更新高危人员				----ok
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public String updateHighriskPerson() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		prepareUpdateTestData();
		Map<String, Object> map = new HashMap<String, Object>();
		HighriskPerson highriskPerson = highriskPersonService.findHighriskPersonById(highriskPersonBean.getId());
		BeanUtils.copyProperties(highriskPerson, highriskPersonBean);
		map.put("highriskPerson", highriskPerson);
		List<MobilePhoneInfo> mobilePhoneInfoList = new ArrayList<>();
		for (MobilePhoneInfoBean mobilePhoneInfoBean : highriskPersonBean.getMobilePhoneInfoBeans()) {
			MobilePhoneInfo mobilePhoneInfo = new MobilePhoneInfo();
			PropertyUtils.copyProperties(mobilePhoneInfo, mobilePhoneInfoBean);
			mobilePhoneInfoList.add(mobilePhoneInfo);
		}
		map.put("mobilePhoneInfoList", mobilePhoneInfoList);
		List<HighriskPeopleType> highriskPeopleTypeList = new ArrayList<>();
		for (HighriskPeopleTypeBean highriskPeopleTypeBean : highriskPersonBean.getHighriskPeopleTypeBeans()) {
			HighriskPeopleType highriskPeopleType = new HighriskPeopleType();
			BeanUtils.copyProperties(highriskPeopleType, highriskPeopleTypeBean);
			List<HighriskCriminalRecord> highriskCriminalRecordList = new ArrayList<>();
			for (String str : highriskPeopleTypeBean.getCriminalRecords()) {
				HighriskCriminalRecord highriskCriminalRecord = new HighriskCriminalRecord();
				highriskCriminalRecord.setCriminalRecord(str);
				highriskCriminalRecordList.add(highriskCriminalRecord);
			}
			Set<HighriskCriminalRecord> set = new HashSet<HighriskCriminalRecord>();
			for(HighriskCriminalRecord record: highriskCriminalRecordList){
				set.add(record);
			}
			highriskPeopleType.setHighriskCriminalRecords(set);
			highriskPeopleTypeList.add(highriskPeopleType);
		}
		List<String> attachLstId = highriskPersonBean.getAttachList();
		if(attachLstId != null && attachLstId.size() > 0){
			map.put("attachId", attachLstId.get(0));
		}
		map.put("highriskPeopleTypeList", highriskPeopleTypeList);
		highriskPersonService.updateHighriskPersonForMobile(map);;
		this.setResult(SUCCESS);
		flag.put("result", getResult());
		return SUCCESS;
	}
	
	/**
	 * 按条件查询五色重点人信息				---ok
	 * <br>：name 姓名（模糊匹配）
	 * <br>：idcode 身份证号（模糊匹配）
	 * <br>：createTime 创建时间
	 * <br>：warningTypeList 预警类型列表
	 * <br>：personTypeList 人员类型列表
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ParseException 
	 * @throws NoSuchMethodException 
	 */
	public String searchAllHighriskPersonListByConditionPhone () throws IllegalAccessException, InvocationTargetException, ParseException, NoSuchMethodException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", highriskPersonBean.getName());
		map.put("idcode", highriskPersonBean.getIdcode());
		map.put("createTime", highriskPersonBean.getCreateTime());
		map.put("personTypeList", highriskPersonBean.getPeopleType());
		List<String> warningTypeLists = new ArrayList<String>();
		for(String str: warningTypeList){
			if(str != null && !str.equals("")){
				warningTypeLists.add(str);
			}
		}
		map.put("warningTypeList", warningTypeLists);
//		map.put("personTypeList", personTypeList);
		
		/*--------------test------------
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("name", "王");
		testMap.put("idcode", "52011119");
		testMap.put("createTime", DateUtils.parseDate("2016-09-19 12:00:00", "yyyy-MM-dd HH:mm:ss"));
		List<String> warningTypeList = new ArrayList<String>();
		warningTypeList.add("1");
		warningTypeList.add("2");
		testMap.put("warningTypeList", warningTypeList);
		List<String> personTypeList = new ArrayList<String>();
		personTypeList.add("017004000");
		personTypeList.add("017005006001");
		personTypeList.add("017004000");
		personTypeList.add("004002");
		personTypeList.add("003000");
		personTypeList.add("017004000");
		personTypeList.add("003001");
		personTypeList.add("017003000002");
		personTypeList.add("017003000007");
		personTypeList.add("017004002");
		personTypeList.add("017003000004");
		testMap.put("personTypeList", personTypeList);
		*/
		
		List<HighriskPerson> list = highriskPersonService.findByCondition(map, 0, 6).getPageList(); //每次查询6条记录
		List<HighriskPerson> listMore = highriskPersonService.findByCondition(map, 0, 7).getPageList(); //每次查询7条记录，看是否还有更多记录
		if(listMore != null && listMore.size() > 6){
			highriskPersonBeanListMap.put("hasMore", "true");
		}else{
			highriskPersonBeanListMap.put("hasMore", "false");
		}
		for (HighriskPerson highriskPerson : list) {
			HighriskPersonBean highriskPersonBean = new HighriskPersonBean();
			BeanUtils.copyProperties(highriskPersonBean, highriskPerson);
			highriskPersonBean.setCreateTime(highriskPerson.getCreatedTime());
			for (MobilePhoneInfo mobilePhoneInfo : highriskPerson.getMobilePhoneInfos()) {
				MobilePhoneInfoBean mobilePhoneInfoBean = new MobilePhoneInfoBean();
				PropertyUtils.copyProperties(mobilePhoneInfoBean, mobilePhoneInfo);
				mobilePhoneInfoBean.setUpdatedTime(mobilePhoneInfo.getUpdatedTime());
				highriskPersonBean.getMobilePhoneInfoBeans().add(mobilePhoneInfoBean);
			}
			for (HighriskPeopleType highriskPeopleType : highriskPerson.getHighriskPeopleTypes()) {
				HighriskPeopleTypeBean highriskPeopleTypeBean = new HighriskPeopleTypeBean();
				BeanUtils.copyProperties(highriskPeopleTypeBean, highriskPeopleType);
				highriskPeopleTypeBean.setUpdatedTime(highriskPeopleType.getUpdateTime());
				for(HighriskCriminalRecord highriskCriminalRecord : highriskPeopleType.getHighriskCriminalRecords()) {
					String str = highriskCriminalRecord.getCriminalRecord();
					highriskPeopleTypeBean.getCriminalRecords().add(str);
				}
				highriskPersonBean.getHighriskPeopleTypeBeans().add(highriskPeopleTypeBean);
			}
			
			List<Attachment> attachments = highriskPerson.getAttachs();
			List<String> attachList = new ArrayList<String>();
			for(Attachment attachment: attachments){
				attachList.add(attachment.getId());
			}
			highriskPersonBean.setAttachList(attachList);
			
			highriskPersonBeanList.add(highriskPersonBean);
			highriskPersonBeanListMap.put("highriskPersonBeanList", highriskPersonBeanList);
		}
		return SUCCESS;
	}
	
	/**
	 * 按id查询五色重点人信息      ----ok
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public String searchHighriskPersonById () throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		HighriskPerson highriskPerson = highriskPersonService.findHighriskPersonById(id);
		highriskPersonBean = new HighriskPersonBean();
		BeanCopier copier = BeanCopier.create(HighriskPerson.class, HighriskPersonBean.class, false);
		copier.copy(highriskPerson, highriskPersonBean, null);
//		PropertyUtils.copyProperties(highriskPersonBean, highriskPerson);
		highriskPersonBean.setCreateTime(highriskPerson.getCreatedTime());
		for (MobilePhoneInfo mobilePhoneInfo : highriskPerson.getMobilePhoneInfos()) {
			MobilePhoneInfoBean mobilePhoneInfoBean = new MobilePhoneInfoBean();
			BeanUtils.copyProperties(mobilePhoneInfoBean, mobilePhoneInfo);
			mobilePhoneInfoBean.setUpdatedTime(mobilePhoneInfo.getUpdatedTime());
			highriskPersonBean.getMobilePhoneInfoBeans().add(mobilePhoneInfoBean);
		}
		for (HighriskPeopleType highriskPeopleType : highriskPerson.getHighriskPeopleTypes()) {
			HighriskPeopleTypeBean highriskPeopleTypeBean = new HighriskPeopleTypeBean();
			BeanUtils.copyProperties(highriskPeopleTypeBean, highriskPeopleType);
			highriskPeopleTypeBean.setUpdatedTime(highriskPeopleType.getUpdateTime());
			for(HighriskCriminalRecord highriskCriminalRecord : highriskPeopleType.getHighriskCriminalRecords()) {
				String str = highriskCriminalRecord.getCriminalRecord();
				highriskPeopleTypeBean.getCriminalRecords().add(str);
			}
			highriskPersonBean.getHighriskPeopleTypeBeans().add(highriskPeopleTypeBean);
		}
		
		List<Attachment> attachments = highriskPerson.getAttachs();
		List<String> attachList = new ArrayList<String>();
		for(Attachment attachment: attachments){
			attachList.add(attachment.getId());
		}
		highriskPersonBean.setAttachList(attachList);
		
		return SUCCESS;
	}
	
	/**
	 * 根据字典类型code查找字典类型对应的所有字典项 
	 * @return //TODO
	 */
	public String findDictionaryItemByType(){
		for(String dictionaryType: dictionaryTypes){
			List<DictionaryItem> items = dictionaryItemService.findAllSubDictionaryItemsByTypeCode(dictionaryType);
			List<DictionaryItemInterfaceBean> beanItems = new ArrayList<DictionaryItemInterfaceBean>();
			for(DictionaryItem item: items){
				DictionaryItemInterfaceBean itemBean = new DictionaryItemInterfaceBean();
				itemBean.setCode(item.getCode());
				itemBean.setId(item.getId());
				itemBean.setName(item.getName());
				if(item.getParentItem() == null){
					itemBean.setParentId(null);
				}else{
					itemBean.setParentId(item.getParentItem().getId());
				}
				
				beanItems.add(itemBean);
			}
			dictionaryItemLstMap.put(dictionaryType, beanItems);
		}
		
		return SUCCESS;
	}

	public HighriskPersonBean getHighriskPersonBean() {
		return highriskPersonBean;
	}

	public void setHighriskPersonBean(HighriskPersonBean highriskPersonBean) {
		this.highriskPersonBean = highriskPersonBean;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<HighriskPersonBean> getHighriskPersonBeanList() {
		return highriskPersonBeanList;
	}

	public void setHighriskPersonBeanList(List<HighriskPersonBean> highriskPersonBeanList) {
		this.highriskPersonBeanList = highriskPersonBeanList;
	}

	public List<MobilePhoneInfoBean> getMobilePhoneInfoBeanList() {
		return mobilePhoneInfoBeanList;
	}

	public void setMobilePhoneInfoBeanList(List<MobilePhoneInfoBean> mobilePhoneInfoBeanList) {
		this.mobilePhoneInfoBeanList = mobilePhoneInfoBeanList;
	}

	public List<HighriskPeopleTypeBean> getHighriskPeopleTypeBeanList() {
		return highriskPeopleTypeBeanList;
	}

	public void setHighriskPeopleTypeBeanList(List<HighriskPeopleTypeBean> highriskPeopleTypeBeanList) {
		this.highriskPeopleTypeBeanList = highriskPeopleTypeBeanList;
	}

	public List<String> getWarningTypeList() {
		return warningTypeList;
	}

	public void setWarningTypeList(List<String> warningTypeList) {
		this.warningTypeList = warningTypeList;
	}

	public List<String> getPersonTypeList() {
		return personTypeList;
	}

	public void setPersonTypeList(List<String> personTypeList) {
		this.personTypeList = personTypeList;
	}

	public void prepareUpdateTestData() {
		highriskPersonBean = new HighriskPersonBean();
		highriskPersonBean.setId("12fe20d3-4f7a-4462-8237-c3700cf640f1");
		highriskPersonBean.setName("newtestStephenName");
		highriskPersonBean.setIdcode("testStephenIdcode");
		highriskPersonBean.setSex("newtestStephenSex");
		highriskPersonBean.setLiveAddress("newtestStephenAddress");
		highriskPersonBean.setRegisterAddress("newtestStephenResignAddress");
		highriskPersonBean.setWorkAddress("newtestStephenWorkAddress");
		highriskPersonBean.setProfession("newtestStephenProfession");
		highriskPersonBean.setIncome(22);
		highriskPersonBean.setWarnType("newtestStephenWarnType");
		highriskPersonBean.setStatus("newtestTestStatus");
		highriskPersonBean.setUrineTest("newtestStephenUrineTest");
		highriskPersonBean.setHandleResult("newtestStephenHandleResult");
		highriskPersonBean.setCreator("newtestStephenCreator");
		MobilePhoneInfoBean mobilePhoneInfoBeana = new MobilePhoneInfoBean();
		mobilePhoneInfoBeana.setImei("testStephenImeia");
		mobilePhoneInfoBeana.setMac("testStephenMaca");
		mobilePhoneInfoBeana.setNumber("testStephenNumbera");
		mobilePhoneInfoBeanList.add(mobilePhoneInfoBeana);
		MobilePhoneInfoBean mobilePhoneInfoBeanb = new MobilePhoneInfoBean();
		mobilePhoneInfoBeanb.setImei("newtestStephenImeic");
		mobilePhoneInfoBeanb.setMac("newtestStephenMacc");
		mobilePhoneInfoBeanb.setNumber("newtestStephenNumberc");
		mobilePhoneInfoBeanList.add(mobilePhoneInfoBeanb);
		HighriskPeopleTypeBean highriskPeopleTypeBeana = new HighriskPeopleTypeBean();
		highriskPeopleTypeBeana.setPeopleType("testStephenPeopleTypea");
		List<String> criminalRecordsa = new ArrayList<String>();
		criminalRecordsa.add("testStephenCriminala1");
		criminalRecordsa.add("newtestStephenCriminala3");
		highriskPeopleTypeBeana.setCriminalRecords(criminalRecordsa);
		highriskPeopleTypeBeanList.add(highriskPeopleTypeBeana);
		HighriskPeopleTypeBean highriskPeopleTypeBeanb = new HighriskPeopleTypeBean();
		highriskPeopleTypeBeanb.setPeopleType("newtestStephenPeopleTypec");
		List<String> criminalRecordsb = new ArrayList<String>();
		criminalRecordsb.add("newtestStephenCriminalc1");
		criminalRecordsb.add("newtestStephenCriminalc2");
		highriskPeopleTypeBeanb.setCriminalRecords(criminalRecordsb);
		highriskPeopleTypeBeanList.add(highriskPeopleTypeBeanb);
	}
	
	
	public void prepareTestData() {
		highriskPersonBean = new HighriskPersonBean();
		highriskPersonBean.setName("testStephenName");
		highriskPersonBean.setIdcode("testStephenIdcode");
		highriskPersonBean.setSex("testStephenSex");
		highriskPersonBean.setLiveAddress("testStephenAddress");
		highriskPersonBean.setRegisterAddress("testStephenResignAddress");
		highriskPersonBean.setWorkAddress("testStephenWorkAddress");
		highriskPersonBean.setProfession("testStephenProfession");
		highriskPersonBean.setIncome(22);
		highriskPersonBean.setWarnType("testStephenWarnType");
		highriskPersonBean.setStatus("testTestStatus");
		highriskPersonBean.setUrineTest("testStephenUrineTest");
		highriskPersonBean.setHandleResult("testStephenHandleResult");
		highriskPersonBean.setCreator("testStephenCreator");
		MobilePhoneInfoBean mobilePhoneInfoBeana = new MobilePhoneInfoBean();
		mobilePhoneInfoBeana.setImei("testStephenImeia");
		mobilePhoneInfoBeana.setMac("testStephenMaca");
		mobilePhoneInfoBeana.setNumber("testStephenNumbera");
		mobilePhoneInfoBeanList.add(mobilePhoneInfoBeana);
		MobilePhoneInfoBean mobilePhoneInfoBeanb = new MobilePhoneInfoBean();
		mobilePhoneInfoBeanb.setImei("testStephenImeib");
		mobilePhoneInfoBeanb.setMac("testStephenMacb");
		mobilePhoneInfoBeanb.setNumber("testStephenNumberb");
		mobilePhoneInfoBeanList.add(mobilePhoneInfoBeanb);
		HighriskPeopleTypeBean highriskPeopleTypeBeana = new HighriskPeopleTypeBean();
		highriskPeopleTypeBeana.setPeopleType("testStephenPeopleTypea");
		List<String> criminalRecordsa = new ArrayList<String>();
		criminalRecordsa.add("testStephenCriminala1");
		criminalRecordsa.add("testStephenCriminala2");
		highriskPeopleTypeBeana.setCriminalRecords(criminalRecordsa);
		highriskPeopleTypeBeanList.add(highriskPeopleTypeBeana);
		HighriskPeopleTypeBean highriskPeopleTypeBeanb = new HighriskPeopleTypeBean();
		highriskPeopleTypeBeanb.setPeopleType("testStephenPeopleTypeb");
		List<String> criminalRecordsb = new ArrayList<String>();
		criminalRecordsb.add("testStephenCriminalb1");
		criminalRecordsb.add("testStephenCriminalb2");
		highriskPeopleTypeBeanb.setCriminalRecords(criminalRecordsb);
		highriskPeopleTypeBeanList.add(highriskPeopleTypeBeanb);
	}

	public Map<String, Object> getHighriskPersonBeanListMap() {
		return highriskPersonBeanListMap;
	}

	public void setHighriskPersonBeanListMap(Map<String, Object> highriskPersonBeanListMap) {
		this.highriskPersonBeanListMap = highriskPersonBeanListMap;
	}

	public Map<String, List<DictionaryItemInterfaceBean>> getDictionaryItemLstMap() {
		return dictionaryItemLstMap;
	}

	public void setDictionaryItemLstMap(Map<String, List<DictionaryItemInterfaceBean>> dictionaryItemLstMap) {
		this.dictionaryItemLstMap = dictionaryItemLstMap;
	}

	public String getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public Map<String, String> getFlag() {
		return flag;
	}

	public void setFlag(Map<String, String> flag) {
		this.flag = flag;
	}

	public List<String> getDictionaryTypes() {
		return dictionaryTypes;
	}

	public void setDictionaryTypes(List<String> dictionaryTypes) {
		this.dictionaryTypes = dictionaryTypes;
	}
	
}
