/**
 * 
 */
package com.taiji.pubsec.szpt.bigdata.wifi.ew.meets.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yucy
 *
 */
public class MeetsOnOnePlaceHolder implements Serializable {
	private static final long serialVersionUID = -7085014378497444667L;

	private String placeCode;
	private Map<String, MeetRecord> meets = new HashMap<String, MeetRecord>();
	
	public MeetsOnOnePlaceHolder(String placeCode) {
		super();
		this.placeCode = placeCode;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public Map<String, MeetRecord> getMeets() {
		return meets;
	}

	@Override
	public String toString() {
		return "MeetsOnOnePlaceHolder [placeCode=" + placeCode + ", meets="
				+ meets + "]";
	}
	
}
