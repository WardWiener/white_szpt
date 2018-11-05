package com.taiji.pubsec.szpt.zagl.action.bean;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;


public class ZaxqBean {
	
	private String id ;
	
	/**
	 * 资料表格td集合
	 */
	private List<ZaxqTableTdBean>  tableInfoLst = new ArrayList<ZaxqTableTdBean>();
	
	/**
	 * 资料类型的集合
	 */
	private List<DictionaryItem>  zazllx = new ArrayList<DictionaryItem>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ZaxqTableTdBean> getTableInfoLst() {
		return tableInfoLst;
	}

	public void setTableInfoLst(List<ZaxqTableTdBean> tableInfoLst) {
		this.tableInfoLst = tableInfoLst;
	}

	public List<DictionaryItem> getZazllx() {
		return zazllx;
	}

	public void setZazllx(List<DictionaryItem> zazllx) {
		this.zazllx = zazllx;
	}

	
}
