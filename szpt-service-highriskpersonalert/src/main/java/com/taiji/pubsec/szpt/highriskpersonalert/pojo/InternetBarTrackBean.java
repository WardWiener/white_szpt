package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

public class InternetBarTrackBean extends AbstractPersonnelTrackInfoBean{

	/**
	 * 网吧名称
	 */
	private String barName;
	
	/**
	 * 网吧地址
	 */
	private String barAddress;
	
	/**
	 * 网吧编码
	 */
	private String barCode ;
	
	/**
	 * 上网时间
	 */
	private Date onlineTime;
	
	/**
	 * 下网时间
	 */
	private Date offlineTime;
	
	/**
	 * 终端号
	 */
	private String terminalnum;
	
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
	
	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offineTime) {
		this.offlineTime = offineTime;
	}

	@Override
	public String trackDescription() {
		
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append(null == onlineTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(onlineTime))
						.append("在" + getBarAddress() + getBarName() + "上网" + "于");
		String offineTimeStr = "";
		if (null == offlineTime) {
			offineTimeStr = "";
		} else if (null == onlineTime) {
			offineTimeStr = HighriskPersonUtil.highriskPersonTrackDateFormat(offlineTime);
		} else {
			offineTimeStr = (HighriskPersonUtil.isSameDate(onlineTime, offlineTime) ? 
					HighriskPersonUtil.highriskPersonTrackTimeFormat(offlineTime) : 
						HighriskPersonUtil.highriskPersonTrackDateFormat(offlineTime));
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getTerminalnum() {
		return terminalnum;
	}

	public void setTerminalnum(String terminalnum) {
		this.terminalnum = terminalnum;
	}
	
	
	
}
