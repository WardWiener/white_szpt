package com.taiji.pubsec.szpt.httpinterface.action.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

/**
 * 高危人员类型
 * @author wangfx
 *
 */
public class HighriskPeopleTypeBean {
	
	private String id;
	
	/**
	 * 人员类型（编码）
	 */
	private String peopleType;
	
	/**
	 * 人员类型名称
	 */
	private String peopleTypeName;
	
	/**
	 * 更新时间
	 */
	private Date updatedTime;
	
	/**
	 * 更新时间
	 */
	private String updatedTimeStr;
	
	/**
	 * 前科类型编码列表
	 */
	private List<String> criminalRecords = new ArrayList<>();
	
	/**
	 * 前科类型名称列表（与前科类型编码列表顺序对应）
	 */
	private List<String> criminalRecordNames = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public List<String> getCriminalRecords() {
		return criminalRecords;
	}

	public void setCriminalRecords(List<String> criminalRecords) {
		this.criminalRecords = criminalRecords;
	}

	public String getPeopleTypeName() {
		IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
		return dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_TYPE_DIC_TYPE_CODE, peopleType);
	}

	public List<String> getCriminalRecordNames() {
		List<String> strList = new ArrayList<String>();
		for (String criminalRecord : criminalRecords) {
			IDictionaryItemService dictionaryItemService = (IDictionaryItemService) SpringContextUtil.getBean("dictionaryItemService");
			strList.add(dictionaryItemService.dicItemcode2nameByDicTypeCodeAndItemCode(HighriskPersonConstant.HIGHRISK_PERSON_CRIMINALRECORD_DIC_TYPE_CODE, criminalRecord));
		}
		return strList;
	}

	public void setCriminalRecordNames(List<String> criminalRecordNames) {
		this.criminalRecordNames = criminalRecordNames;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedTimeStr() {
		return null == updatedTime ? "" : HighriskPersonUtil.date2str(updatedTime);
	}

	public void setUpdatedTimeStr(String updatedTimeStr) {
		this.updatedTimeStr = updatedTimeStr;
	}
	

}
