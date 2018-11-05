package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;
import net.sf.json.JSONObject;

public class WifiInfo implements Serializable,ClueInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7180925449436216086L;
	
	/**
	 * mac地址
	 */
	private String mac ;
	/**
	 * mac场所编码
	 */
	private String placeCode ;
	/**
	 * mac场所名称
	 */
	private String placeName ;
	/**
	 * 经度
	 */
	private Double longitude ;
	/**
	 * 纬度
	 */
	private Double latitude ; 
	/**
	 * 进入时间
	 */
	private Long enterTime ;
	/**
	 * 离开时间
	 */
	private Long leaveTime ;
	/**
	 * 手机号
	 */
	private String phone ;
	
	@Override
	public String sketch() {
		return "mac地址：" + mac ;
	}
	@Override
	public String detailDescription() {
		JSONObject obj = JSONObject.fromObject(this) ;
		return obj.toString();
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Long getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}
	public Long getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
}
