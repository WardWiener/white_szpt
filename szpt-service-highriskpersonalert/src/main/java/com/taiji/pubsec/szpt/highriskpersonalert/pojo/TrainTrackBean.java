package com.taiji.pubsec.szpt.highriskpersonalert.pojo;

import java.util.Date;

import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonUtil;

public class TrainTrackBean extends AbstractPersonnelTrackInfoBean{	

	/**
	 * 发车时间
	 */
	private Date setoutTime;
	
	/**
	 * 车次
	 */
	private String trainNo;
	
	/**
	 * 终点站
	 */
	private String destinationLocation;
	
	/**
	 * 始发站
	 */
	private String originLocation;

	
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

	@Override
	public String trackDescription() {
		StringBuilder trackDescription = new StringBuilder();
		trackDescription.append((null == setoutTime ? "" : HighriskPersonUtil.highriskPersonTrackDateFormat(setoutTime) + ","));
		trackDescription.append(null == getTrainNo() ? "" : "乘坐"+getTrainNo()+"次列车");
		trackDescription.append("从" + getOriginLocation() + "到" +
				getDestinationLocation() + "。");
		return trackDescription.toString();
	}

	@Override
	public String trackType() {
		return HighriskPersonConstant.PERSON_TRACK_TYPE_TRAIN;
	}

	@Override
	public String trackTypeDescription() {
		if(originLocation.contains(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY)) {
			return HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_OUT;
		}
		else if(destinationLocation.contains(HighriskPersonConstant.HIGHRISK_PERSON_TRACK_LOCALCITY)) {
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
		}else
			return null;
	}
	
}
