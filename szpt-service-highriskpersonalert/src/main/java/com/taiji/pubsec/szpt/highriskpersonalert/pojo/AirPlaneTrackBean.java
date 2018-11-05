package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

public class AirPlaneTrackBean extends AbstractPersonnelTrackInfoBean{

	/**
	 * 航班号
	 */
	private String flightNo;

	/**
	 * 起飞机场
	 */
	private String originLocation;

	/**
	 * 到达机场
	 */
	private String destinationLocation;
	
	/**
	 * 到达时间
	 */
	private String arriveattime;
	
	/**
	 * 起飞时间
	 */
	private Date setoutTime;

	
	private Date reachTime;
	
	/**
	 * 座位号
	 */
	private String seatnum;
	
	

	public String getArriveattime() {
		return arriveattime;
	}

	public void setArriveattime(String arriveattime) {
		this.arriveattime = arriveattime;
	}

	public String getSeatnum() {
		return seatnum;
	}

	public void setSeatnum(String seatnum) {
		this.seatnum = seatnum;
	}

	public String getFlightNo() {
		return null == flightNo ? "" : flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getOriginLocation() {
		return null == originLocation ? "" : originLocation;
	}

	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}

	public String getDestinationLocation() {
		return null == destinationLocation ? "" : destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public Date getSetoutTime() {
		return setoutTime;
	}

	public void setSetoutTime(Date setoutTime) {
		this.setoutTime = setoutTime;
	}

	public Date getReachTime() {
		return reachTime;
	}

	public void setReachTime(Date reachTime) {
		this.reachTime = reachTime;
	}

	@Override
	public String trackDescription() {
		
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append((null == setoutTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(setoutTime)));
		trackDescription.append("从" + getOriginLocation() + "到" +
				getDestinationLocation() + "，" + getFlightNo() + "于");
		String reachTimeStr = "";
		if(null == reachTime) {
			reachTimeStr = "";
		} else if(null == setoutTime) {
			reachTimeStr = HighriskPersonUtil.highriskPersonTrackDateFormat(reachTime);
		} else {
			reachTimeStr = (HighriskPersonUtil.isSameDate(setoutTime, reachTime) ? 
					HighriskPersonUtil.highriskPersonTrackTimeFormat(reachTime) :HighriskPersonUtil.highriskPersonTrackDateFormat(reachTime));
		}
		trackDescription.append(reachTimeStr)
						.append("到达。");
		
		return trackDescription.toString();
	}

	@Override
	public String trackType() {
		return HighriskPersonConstant.PERSON_TRACK_TYPE_AIRPLANE;
	}

	@Override
	public String trackTypeDescription() {
		if(originLocation.contains(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY)) {
			return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_OUT;		
		}else if(destinationLocation.contains(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY)) {
			return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_ENTER;
		}else
			return "";
	}

	@Override
	public Object toObject() {
		return this;
	}

	@Override
	public String getAddress() {
		if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(originLocation)) {
			return destinationLocation;		
		}else if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(destinationLocation)) {
			return originLocation;
		}else
			return "";
	}

	@Override
	public Date getAppearTime() {
		if(StringUtils.isBlank(originLocation)){
			return null;
		}else if(originLocation.contains(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY)) {
			return setoutTime;		
		}else if(destinationLocation.contains(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY)) {
			return reachTime;
		}else
			return null;
	}
	
}
