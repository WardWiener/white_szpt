package com.taiji.pubsec.szpt.highriskpersonalert.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.taiji.pubsec.szpt.highriskpersonalert.pojo.AbstractPersonnelTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

/**
 * 宾馆酒店轨迹信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_lgjd")
public class HotelTrack extends AbstractPersonnelTrackInfo{
	
	/**
	 * 旅馆酒店名称
	 */
	@Column(name = "lgjdmc")
	private String hotelName;
	
	/**
	 * 旅馆酒店编码
	 */
	@Column(name = "lgjdbm")
	private String hotelCode;
	
	/**
	 * 入住时间
	 */
	@Column(name = "rzsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date enterTime;
	
	/**
	 * 退房时间
	 */
	@Column(name = "tfsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveTime;
	
	/**
	 * 房间号
	 */
	@Column(name = "fjh")
	private String roomNo;
	
	/**
	 * 入住天数
	 */
	@Column(name = "rzts")
	private Integer stayDays;
	
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

	public String getRoomNo() {
		return null == roomNo ? "" : roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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

}
