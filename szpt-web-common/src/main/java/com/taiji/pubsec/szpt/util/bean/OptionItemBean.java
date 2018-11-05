package com.taiji.pubsec.szpt.util.bean;

/**
 * 字典项Bean
 * 
 * @author WL-PC
 *
 */
public class OptionItemBean {

	private String code;// 编码

	private String id;// id

	private String name;// 名称
	
	private String parentTypeId;// 父级字典类型id

	private String parentItemId;// 父级字典项id

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(String parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}
}
