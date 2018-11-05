package com.taiji.pubsec.szpt.dpp.wifi.ew.bean;

import java.io.Serializable;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;

public class StayRecord implements Serializable, Comparable<StayRecord> {
	private static final long serialVersionUID = 543688931835605824L;
	
	private String mac;//mac地址
	private String placecode;
	private InOutTime inout;
	
	private WifiData wifidata;
	
	public StayRecord() {
		super();
	}
	
	public StayRecord(String mac, String placecode, InOutTime inout, WifiData wifidata) {
		super();
		this.mac = mac;
		this.placecode = placecode;
		this.inout = inout;
		this.wifidata = wifidata;
	}
	
	public InOutTime getInout() {
		return inout;
	}
	public void setInout(InOutTime inout) {
		this.inout = inout;
	}
	public String getMac() {
		return mac;
	}
	public String getPlacecode() {
		return placecode;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setPlacecode(String placecode) {
		this.placecode = placecode;
	}
	
	@Override
	public int compareTo(StayRecord o) {
		if(inout.getIn() < o.getInout().getIn()){
			return -1;
		}
		if(inout.getIn() == o.getInout().getIn()){
			return 0;
		}
		return 1;
	}

	public WifiData getWifidata() {
		return wifidata;
	}

	public void setWifidata(WifiData wifidata) {
		this.wifidata = wifidata;
	}
	
}
