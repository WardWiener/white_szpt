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
 * 飞机轨迹信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_fj")
public class AirPlaneTrack extends AbstractPersonnelTrackInfo {
	
	/**
	 * 航班号
	 */
	@Column(name = "hbh")
	private String flightNo;
	
	/**
	 * 座位号
	 */
	@Column(name = "zwh")
	private String seatNo;
	
	/**
	 * 离港地点
	 */
	@Column(name = "lgdd")
	private String originLocation;
	
	/**
	 * 到港地点
	 */
	@Column(name = "dgdd")
	private String destinationLocation;
	
	/**
	 * 离港时间
	 */
	@Column(name = "lgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date setoutTime;
	
	/**
	 * 到港时间
	 */
	@Column(name = "dgsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reachTime;
	
	/**
	 * 目的地经度
	 */
	@Column(name = "mddjd")
	private String destinationLongitude;
	
	/**
	 * 目的地纬度
	 */
	@Column(name = "mddwd")
	private String destinationLatitude;
	

	public String getFlightNo() {
		return null == flightNo ? "" : flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getSeatNo() {
		return null == seatNo ? "" : seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
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

	public String getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(String destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public String getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(String destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	@Override
	public String trackDescription() {
		
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append((null == setoutTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(setoutTime)));
		trackDescription.append("从" + getOriginLocation() + "到" +
				getDestinationLocation() + "，" + getFlightNo() + "，" + getSeatNo() + "，" + "于");
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
		if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(originLocation)) {
			return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_OUT;		
		}else if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(destinationLocation)) {
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
		if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(originLocation)) {
			return setoutTime;		
		}else if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(destinationLocation)) {
			return reachTime;
		}else
			return null;
	}

}
