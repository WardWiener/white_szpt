/**
 * 
 */
package com.taiji.pubsec.szpt.dpp.wifi.ew.basedplace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;

/**
 * @author yucy
 *
 */
public class PlaceStatistics implements Serializable {

	private static final long serialVersionUID = 5943002826149479913L;

	//用于迭代计算的数据项
	private WifiData wifiData;
	// 停留超过五分钟的人
	List<WifiData> stayOver5Min = new ArrayList<>();
	// 橙色人且停留超过五分钟
	List<WifiData> stayOver5MinOfOrange = new ArrayList<>();
	// 红色人出现
	List<WifiData> redColorPeoples = new ArrayList<>();
	// 停留超过15分钟的人
	List<WifiData> stayOver15Min = new ArrayList<>();
	// 有盗窃前科人员，驻留时间超过30分钟
	List<WifiData> stayOver30MinOfTheft = new ArrayList<>();
	
	public WifiData getWifiData() {
		return wifiData;
	}
	public void setWifiData(WifiData wifiData) {
		this.wifiData = wifiData;
	}
	public List<WifiData> getStayOver5Min() {
		return stayOver5Min;
	}
	public void setStayOver5Min(List<WifiData> stayOver5Min) {
		this.stayOver5Min = stayOver5Min;
	}
	public List<WifiData> getStayOver5MinOfOrange() {
		return stayOver5MinOfOrange;
	}
	public void setStayOver5MinOfOrange(List<WifiData> stayOver5MinOfOrange) {
		this.stayOver5MinOfOrange = stayOver5MinOfOrange;
	}
	public List<WifiData> getRedColorPeoples() {
		return redColorPeoples;
	}
	public void setRedColorPeoples(List<WifiData> redColorPeoples) {
		this.redColorPeoples = redColorPeoples;
	}
	public List<WifiData> getStayOver15Min() {
		return stayOver15Min;
	}
	public void setStayOver15Min(List<WifiData> stayOver15Min) {
		this.stayOver15Min = stayOver15Min;
	}
	public List<WifiData> getStayOver30MinOfTheft() {
		return stayOver30MinOfTheft;
	}
	public void setStayOver30MinOfTheft(List<WifiData> stayOver30MinOfTheft) {
		this.stayOver30MinOfTheft = stayOver30MinOfTheft;
	}
	@Override
	public String toString() {
		return "PlaceStatistics [wifiData=" + wifiData + ", stayOver5Min="
				+ stayOver5Min + ", stayOver5MinOfOrange="
				+ stayOver5MinOfOrange + ", redColorPeoples=" + redColorPeoples
				+ ", stayOver15Min=" + stayOver15Min
				+ ", stayOver30MinOfTheft=" + stayOver30MinOfTheft + "]";
	}

}
