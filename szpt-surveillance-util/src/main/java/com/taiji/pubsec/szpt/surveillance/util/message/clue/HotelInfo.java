package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class HotelInfo implements Serializable,ClueInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -299657658917659767L;
	/**
	 * 身份证号
	 */
	private String idCard ;
	/**
	 * 旅馆名称
	 */
	private String hotelName;
	/**
	 * 旅馆编码
	 */
	private String hotelCode;
	/**
	 * 旅馆地址
	 */
	private String hotelAddress;
	/**
	 * 进入时间
	 */
	private Long enterTime ;
	/**
	 * 离开时间
	 */
	private Long leaveTime ;
	
	@Override
	public String sketch() {
		return "旅馆：" + hotelName;
	}
	@Override
	public String detailDescription() {
		JSONObject obj = JSONObject.fromObject(this) ;
		return obj.toString();
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
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

	
}
