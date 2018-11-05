/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.meets.bean;

import java.io.Serializable;

/**
 * 暂时不用此类
 * 
 * @author yucy
 *
 */
public class MeetsGroup implements Serializable {

	private static final long serialVersionUID = -1599675185020390235L;
	
	public static final Integer STATE_NEW = 0;
	public static final Integer STATE_SENDED = 1;
	public static final Integer STATE_UPDATED = 2;

	private MeetsOnPlacesInfo meetsInfo;
	private Integer state;
	
	public MeetsGroup(MeetsOnPlacesInfo meetsInfo) {
		super();
		this.meetsInfo = meetsInfo;
		this.state = STATE_NEW;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public MeetsOnPlacesInfo getMeetsInfo() {
		return meetsInfo;
	}
	
}
