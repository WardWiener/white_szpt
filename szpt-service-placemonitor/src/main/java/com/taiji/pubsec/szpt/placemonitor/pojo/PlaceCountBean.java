package com.taiji.pubsec.szpt.placemonitor.pojo;

import java.util.HashSet;
import java.util.Set;

public class PlaceCountBean {

	/**
	 * 场所名称
	 */
	private String placeName ;
	
	/**
	 * 场所编码
	 */
	private String placeCode ;
	
	/**
	 * 出入记录总次数
	 */
	private Integer count ;
	
	/**
	 * 纬度
	 */
	private Double latitude ;
	
	/**
	 * 经度
	 */
	private Double longitude ;

	
	/**
	 * 统计总时间长度
	 */
	private Long stayTime ;
	
	/**
	 * mac地址集合
	 */
	private Set<String> macs = new HashSet<String>();

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getStayTime() {
		return stayTime;
	}

	public void setStayTime(Long stayTime) {
		this.stayTime = stayTime;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public Set<String> getMacs() {
		return macs;
	}

	public void setMacs(Set<String> macs) {
		this.macs = macs;
	}
	
	
}
