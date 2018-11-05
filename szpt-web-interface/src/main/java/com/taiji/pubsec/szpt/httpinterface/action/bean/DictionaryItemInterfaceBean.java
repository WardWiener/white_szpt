package com.taiji.pubsec.szpt.httpinterface.action.bean;
/**
 * 字典项bean
 * @author chengkai
 *
 */
public class DictionaryItemInterfaceBean {
	
	private String id;	//字典项id
	
	private String code;	//字典项编码
	
	private String name;	//字典项名称
	
	private String parentId;	//上级字典项id

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
