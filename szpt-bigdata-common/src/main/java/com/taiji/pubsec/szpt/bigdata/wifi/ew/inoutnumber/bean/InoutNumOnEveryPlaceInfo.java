/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.inoutnumber.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.taiji.pubsec.szpt.bigdata.wifi.ew.bean.StayRecord;

/**
 * 某个mac在各个场所的停留（出入）记录信息
 * 
 * @author yucy
 *
 */
public class InoutNumOnEveryPlaceInfo implements Serializable {
	private static final long serialVersionUID = 7171643744618892956L;
	
	private String mac;
	//<场所，停留记录列表>
	private Map<String, List<StayRecord>> details = new HashMap<String, List<StayRecord>>();
	
	public InoutNumOnEveryPlaceInfo() {
		super();
	}

	public InoutNumOnEveryPlaceInfo(String mac) {
		super();
		this.mac = mac;
	}
	
	public StayRecord lastStay(){
		List<StayRecord> all = new ArrayList<>();
		for(Entry<String, List<StayRecord>> entry : details.entrySet()){
			all.addAll(entry.getValue());
		}
		StayRecord[] stays = new StayRecord[all.size()];
		all.toArray(stays);
		Arrays.sort(stays);
		
		return stays[stays.length-1];
	}

	public String getMac() {
		return mac;
	}

	public Integer getCount() {
		Integer c = 0;
		for(Entry<String, List<StayRecord>> entry : details.entrySet()){
			c = c + entry.getValue().size();
		}
		return c;
	}

	public Map<String, List<StayRecord>> getDetails() {
		return details;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public void setDetails(Map<String, List<StayRecord>> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "InoutNumOnEveryPlaceInfo [mac=" + mac + ", count=" + getCount()
				+ ", details=" + details + "]";
	}
	
}
