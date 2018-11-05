/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yucy
 *
 */
public class StaysOnOnePlaceHolder implements Serializable {
	private static final long serialVersionUID = 4394104482461995041L;
	
	private String placeCode;
	//在一个场所内，同一个人可能多次出入，使用mac作为key标识
	private Map<String, List<StayRecord>> stays = new HashMap<String, List<StayRecord>>();
	
	public StaysOnOnePlaceHolder(String placeCode) {
		super();
		this.placeCode = placeCode;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public Map<String, List<StayRecord>> getStays() {
		return stays;
	}

	@Override
	public String toString() {
		return "StaysOnOnePlaceHolder [placeCode=" + placeCode + ", stays="
				+ stays + "]";
	}

	
}
