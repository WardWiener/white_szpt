package com.taiji.pubsec.szpt.caseanalysis.tag.action.bean;

/**
 * 问询笔录Bean
 * 
 * @author WangLei
 *
 */
public class CrimialCaseNoteBean {
	
	private String id;// 笔录id
	
	private String code;// 笔录编号
	
	private String name;// 笔录名称
	
	private String sort;// 序号
	
	private String url;// 外部系统访问地址
	
	private String type;// 笔录类型
	
	private String source;// 笔录来源
	
	private String inputPerson;// 录入人
	
	private Long inputTime;// 录入时间
	
	private String modifiedPerson;// 修改人
	
	private Long modifiedTime;// 修改时间
	
	private String caseCode;// 案件编号

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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getInputPerson() {
		return inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public Long getInputTime() {
		return inputTime;
	}

	public void setInputTime(Long inputTime) {
		this.inputTime = inputTime;
	}

	public String getModifiedPerson() {
		return modifiedPerson;
	}

	public void setModifiedPerson(String modifiedPerson) {
		this.modifiedPerson = modifiedPerson;
	}

	public Long getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
}
