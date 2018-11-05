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
 * 火车轨迹信息
 * @author huangda
 *
 */
@Entity
@Table(name = "t_gwry_hc")
public class TrainTrack extends AbstractPersonnelTrackInfo{
	
	/**
	 * 发车时间
	 */
	@Column(name = "fcsj")
	@Temporal(TemporalType.TIMESTAMP)
	private Date setoutTime;
	
	/**
	 * 车次
	 */
	@Column(name = "cc")
	private String trainNo;
	
	/**
	 * 终点站
	 */
	@Column(name = "zdz")
	private String destinationLocation;
	
	/**
	 * 始发站
	 */
	@Column(name = "sfz")
	private String originLocation;
	
	/**
	 * 座位号
	 */
	@Column(name = "zwh")
	private String seatNo;
	
	/**
	 * 到站时间
	 */
	@Column(name = "dzsj")
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

	
	public Date getSetoutTime() {
		return setoutTime;
	}

	public void setSetoutTime(Date setoutTime) {
		this.setoutTime = setoutTime;
	}

	public String getTrainNo() {
		return null == trainNo ? "" : trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getDestinationLocation() {
		return null == destinationLocation ? "" : destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public String getOriginLocation() {
		return null == originLocation ? "" : originLocation;
	}

	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}

	public String getSeatNo() {
		return null == seatNo ? "" : seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
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
				getDestinationLocation() + "，" + getTrainNo() + "，" + getSeatNo() + "，" + "于");
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
		return HighriskPersonConstant.PERSON_TRACK_TYPE_TRAIN;
	}

	@Override
	public String trackTypeDescription() {
		if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(originLocation)) {
			return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_OUT;
		}
		else if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(destinationLocation)) {
			return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_ENTER;
		}
		else
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
		}
		else if(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY.equals(destinationLocation)) {
			return originLocation;
		}
		else
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
