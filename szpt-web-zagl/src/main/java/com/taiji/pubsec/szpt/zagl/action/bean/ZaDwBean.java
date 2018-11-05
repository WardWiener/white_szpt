package com.taiji.pubsec.szpt.zagl.action.bean;

import java.util.ArrayList;
import java.util.List;


public class ZaDwBean {
	
	private String id ;
	
	private String code ;
	
	private String name ;
	
	private Integer lv;
	
	List<ZaDwBean> dwLst = new ArrayList<ZaDwBean>();
	
	public Integer getLv() {
		return lv;
	}

	public void setLv(Integer lv) {
		this.lv = lv;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<ZaDwBean> getDwLst() {
		return dwLst;
	}

	public void setDwLst(List<ZaDwBean> dwLst) {
		this.dwLst = dwLst;
	}
	
	
}
