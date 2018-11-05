package com.taiji.pubsec.szpt.httpinterface.action.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

/**
 * 高危人员
 * @author wangfx
 *
 */
public class HighriskPersonBean {
	
	/**
	 * 高危人员姓名
	 */
	private String name;
	
	/**
	 * 高危人员对象id
	 */
	private String id;
	
	/**
	 * 高危人员身份证号
	 */
	private String idcode;
	
	/**
	 * 高危人员性别，字典项编码
	 */
	private String sex;
	
	/**
	 * 高危人员性别，字典项名称
	 */
	private String sexName;
	
	/**
	 * 高危人员居住地址
	 */
	private String liveAddress;
	
	/**
	 * 高危人员户籍地址
	 */
	private String registerAddress;
	
	/**
	 * 高危人员工作地址
	 */
	private String workAddress;
	
	/**
	 * 高危人员职业
	 */
	private String profession;
	
	/**
	 * 高危人员收入
	 */
	private Integer income;
	
	/**
	 * 预警类型，字典项id
	 */
	private String warnType;
	
	/**
	 * 预警类型名称
	 */
	private String warnTypeName;
	
	/**
	 * 日常状况，字典项编码
	 */
	private String status;
	
	/**
	 * 日常状况，字典项名称
	 */
	private String statusName;
	
	/**
	 * 尿检结果，字典项编码
	 */
	private String urineTest;
	
	/**
	 * 尿检结果，字典项名称
	 */
	private String urineTestName;
	
	/**
	 * 处置结果，字典项编码
	 */
	private String handleResult;
	
	/**
	 * 处置结果，字典项名称
	 */
	private String handleResultName;
	
	/**
	 * 采集人姓名
	 */
	private String creator;
	
	/**
	 * 电话号码
	 */
	private String number;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	private String createTimeStr;
	
	private Date updateTime;	//更新时间
	
	/**
	 * 人员类型编码list
	 */
	private List<String> peopleType = new ArrayList<String>();
	
	/**
	 * 终端设备信息
	 */
	private List<MobilePhoneInfoBean> mobilePhoneInfoBeans = new ArrayList<>();
	
	/**
	 * 人员类型信息
	 */
	private List<HighriskPeopleTypeBean> highriskPeopleTypeBeans = new ArrayList<>();
	
	private List<String> attachList = new ArrayList<String>();//附件id集合

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public String getWarnType() {
		return warnType;
	}

	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrineTest() {
		return urineTest;
	}

	public void setUrineTest(String urineTest) {
		this.urineTest = urineTest;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		
		return (null == createTime ? "" : HighriskPersonUtil.date2str(createTime));
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public List<MobilePhoneInfoBean> getMobilePhoneInfoBeans() {
		return mobilePhoneInfoBeans;
	}

	public void setMobilePhoneInfoBeans(List<MobilePhoneInfoBean> mobilePhoneInfoBeans) {
		this.mobilePhoneInfoBeans = mobilePhoneInfoBeans;
	}

	public List<HighriskPeopleTypeBean> getHighriskPeopleTypeBeans() {
		return highriskPeopleTypeBeans;
	}

	public void setHighriskPeopleTypeBeans(List<HighriskPeopleTypeBean> highriskPeopleTypeBeans) {
		this.highriskPeopleTypeBeans = highriskPeopleTypeBeans;
	}

	public String getWarnTypeName() {
		IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
		return dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_WARNTYPE_CODE, warnType);
	}

	public String getSexName() {
		IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
		return dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_SEX_DIC_TYPE_CODE, sex);
	}

	public String getStatusName() {
		IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
		return dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_STATUS_DIC_TYPE_CODE, status);
	}

	public String getUrineTestName() {
		IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
		return dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_URINETEST_DIC_TYPE_CODE, urineTest);
	}

	public String getHandleResultName() {
		IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
		return dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_HANDLERESULT_DIC_TYPE_CODE, handleResult);
	}

	public List<String> getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(List<String> peopleType) {
		this.peopleType = peopleType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<String> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<String> attachList) {
		this.attachList = attachList;
	}
	
}
