package com.taiji.pubsec.szpt.xtgl.dictionary;


import java.util.Date;
import java.util.List;

/**
 * 字典项bean
 * 
 * @author xinfan
 *
 */
public class DictionaryItemBean {
	private List<DictionaryItemBean> subItemBeans;
	private DictionaryTypeBean dictionaryTypeBean;
	private String id;
	private String name;
	private String code;
	private String state;
	private String description;
	private Integer number;// 顺序号
	private String dicTypeId;
	private String parentItemId;
	private Date updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDicTypeId() {
		return dicTypeId;
	}

	public void setDicTypeId(String dicTypeId) {
		this.dicTypeId = dicTypeId;
	}

	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}

	public List<DictionaryItemBean> getSubItemBeans() {
		return subItemBeans;
	}

	public void setSubItemBeans(List<DictionaryItemBean> subItemBeans) {
		this.subItemBeans = subItemBeans;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public DictionaryTypeBean getDictionaryTypeBean() {
		return dictionaryTypeBean;
	}

	public void setDictionaryTypeBean(DictionaryTypeBean dictionaryTypeBean) {
		this.dictionaryTypeBean = dictionaryTypeBean;
	}

}
