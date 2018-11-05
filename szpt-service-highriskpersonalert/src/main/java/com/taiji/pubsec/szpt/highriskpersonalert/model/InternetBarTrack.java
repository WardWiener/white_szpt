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
 * 网吧轨迹信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_wb")
public class InternetBarTrack extends AbstractPersonnelTrackInfo {
	
	/**
	 * 网吧名称
	 */
	@Column(name = "wbmc")
	private String barName;
	
	/**
	 * 网吧地址
	 */
	@Column(name = "wbdz")
	private String barAddress;
	
	/**
	 * 座位号
	 */
	@Column(name = "zwh")
	private String seatNumber;
	
	/**
	 * 上网时间
	 */
	@Column(name = "swsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date onlineTime;
	
	/**
	 * 下网时间
	 */
	@Column(name = "xwsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date offineTime;
	
	public String getBarName() {
		return null == barName ? "" : barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}

	public String getBarAddress() {
		return null == barAddress ? "" : barAddress;
	}

	public void setBarAddress(String barAddress) {
		this.barAddress = barAddress;
	}

	public String getSeatNumber() {
		return null == seatNumber ? "" : seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOffineTime() {
		return offineTime;
	}

	public void setOffineTime(Date offineTime) {
		this.offineTime = offineTime;
	}

	@Override
	public String trackDescription() {
		
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append(null == onlineTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(onlineTime))
						.append("在" + getBarAddress() + getBarName() + "上网，座位" 
								+ seatNumber + "，" + "于");
		String offineTimeStr = "";
		if (null == offineTime) {
			offineTimeStr = "";
		} else if (null == onlineTime) {
			offineTimeStr = HighriskPersonUtil.highriskPersonTrackDateFormat(offineTime);
		} else {
			offineTimeStr = (HighriskPersonUtil.isSameDate(onlineTime, offineTime) ? 
					HighriskPersonUtil.highriskPersonTrackTimeFormat(offineTime) : 
						HighriskPersonUtil.highriskPersonTrackDateFormat(offineTime));
		}
		trackDescription.append(offineTimeStr)
						.append("离开。");
		return  trackDescription.toString();
	}

	@Override
	public String trackType() {
		return HighriskPersonConstant.PERSON_TRACK_TYPE_INTERNETBAR;
	}

	@Override
	public String trackTypeDescription() {
		return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_INTERNETBAR;
	}

	@Override
	public Object toObject() {
		return this;
	}

	@Override
	public String getAddress() {
		return this.barAddress;
	}

	@Override
	public Date getAppearTime() {
		return this.onlineTime;
	}

}
