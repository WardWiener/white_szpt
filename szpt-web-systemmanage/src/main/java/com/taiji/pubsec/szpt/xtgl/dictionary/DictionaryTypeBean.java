package com.taiji.pubsec.szpt.xtgl.dictionary;


import java.util.Date;
import java.util.List;

/**
 * 字典类型Bean
 * 
 * @author xinfan
 *
 */
public class DictionaryTypeBean {
	private String id;
	private String code;
	private String name;
	private String classifier;
	private String description;
	private String state;
	private Date updatedTime;
	private List<DictionaryItemBean> dictionaryItemBeanLists;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public List<DictionaryItemBean> getDictionaryItemBeanLists() {
		return dictionaryItemBeanLists;
	}

	public void setDictionaryItemBeanLists(
			List<DictionaryItemBean> dictionaryItemBeanLists) {
		this.dictionaryItemBeanLists = dictionaryItemBeanLists;
	}

}
