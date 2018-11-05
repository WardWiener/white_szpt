package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

public class WifiTrackBean extends AbstractPersonnelTrackInfoBean{

	/**
	 * 进入时间
	 */
	private Date enterTime ;
	
	/**
	 * 离开时间
	 */
	private Date leaveTime ;
	
	/**
	 * 场所编码
	 */
	private String placeCode ;
	
	/**
	 * 场所名称
	 */
	private String placeName ;
	
	/**
	 * mac地址名称
	 */
	private String mac ;
	
	/**
	 * 手机号
	 */
	private String phone ;
	
	/**
	 * 场所经度
	 */
	private Double longitude;
	
	/**
	 * 场所纬度
	 */
	private Double latitude;
	
	/**
	 * 停留时间
	 */
	private Long stayInterval ;

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Long getStayInterval() {
		return stayInterval;
	}

	public void setStayInterval(Long stayInterval) {
		this.stayInterval = stayInterval;
	}
	
	@Override
	public String trackDescription() {
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append((null == enterTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(enterTime)));

		String reachTimeStr = "";
		if(null == leaveTime) {
			reachTimeStr = "";
		} else if(null == enterTime) {
			reachTimeStr = HighriskPersonUtil.highriskPersonTrackDateFormat(leaveTime);
		} else {
			reachTimeStr = (HighriskPersonUtil.isSameDate(enterTime, leaveTime) ? 
					HighriskPersonUtil.highriskPersonTrackTimeFormat(leaveTime) :HighriskPersonUtil.highriskPersonTrackDateFormat(leaveTime));
		}
		trackDescription.append(reachTimeStr)
						.append("离开。");
		
		return trackDescription.toString();
	}

	@Override
	public String trackType() {
		return HighriskPersonConstant.PERSON_TRACK_TYPE_WIFI;
	}

	@Override
	public String trackTypeDescription() {
		return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_WIFI;
		
	}

	@Override
	public Object toObject() {
		return this;
	}

	@Override
	public String getAddress() {
		return placeName;
	}

	@Override
	public Date getAppearTime() {
		return enterTime;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	
}
