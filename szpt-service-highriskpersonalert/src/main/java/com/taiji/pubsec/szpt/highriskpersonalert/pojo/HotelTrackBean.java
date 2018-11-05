package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

public class HotelTrackBean extends AbstractPersonnelTrackInfoBean{
	
	/**
	 * 旅馆酒店名称
	 */
	private String hotelName;
	
	/**
	 * 旅馆酒店编码
	 */
	private String hotelCode;
	
	/**
	 * 入住时间
	 */
	private Date enterTime;
	
	/**
	 * 退房时间
	 */
	private Date leaveTime;
	
	/**
	 * 入住天数
	 */
	private Integer stayDays;
	
	/**
	 * 旅馆地址
	 */
	private String hotelAddr ;
	
	/**
	 * 房间号
	 */
	private String roomnum;
	
	public String getHotelName() {
		return null == hotelName ? "" : hotelName;
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

	public String getStayDays() {
		return null == stayDays ? "" : stayDays.toString();
	}

	public void setStayDays(Integer stayDays) {
		this.stayDays = stayDays;
	}

	@Override
	public String trackDescription() {
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append(null == enterTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(enterTime))
						.append("入住" + getHotelName() + "酒店，续住" + getStayDays() + "天，");
		String leaveTimeStr = "";
		if (null == leaveTime) {
			leaveTimeStr = "";
		} else if (null == enterTime) {
			leaveTimeStr = HighriskPersonUtil.highriskPersonTrackDateFormat(leaveTime);
		} else {
			leaveTimeStr = (HighriskPersonUtil.isSameDate(enterTime, leaveTime) ? HighriskPersonUtil.highriskPersonTrackTimeFormat(leaveTime) :
				HighriskPersonUtil.highriskPersonTrackDateFormat(leaveTime));
		}
		trackDescription.append(leaveTimeStr)
						.append("离开。");
		
		return trackDescription.toString();
	}

	@Override
	public String trackType() {
		return HighriskPersonConstant.PERSON_TRACK_TYPE_HOTEL;
	}

	@Override
	public String trackTypeDescription() {
		return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_HOTEL;
	}

	@Override
	public Object toObject() {
		return this;
	}

	@Override
	public String getAddress() {
		return this.getHotelName();
	}

	@Override
	public Date getAppearTime() {
		return this.getEnterTime();
	}

	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

}
