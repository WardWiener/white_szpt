package com.taiji.pubsec.szpt.bean;

import java.util.ArrayList;
import java.util.List;

public class TopTimeBean {
	
	/**
	 * 场所名称
	 */
	private String placeName ;
	
	/**
	 * 场所编码
	 */
	private String placeCode ;
	
	/**
	 * 统计
	 */
	private Integer count ;
	
	/**
	 * 停留时间
	 */
	private Long stayInterval ;
	
	private List<TimeIntervalBean> timeIntervalList = new ArrayList<TimeIntervalBean>();
	
	
	public TopTimeBean(String placeName, String placeCode, Integer count, Long stayInterval) {
		super();
		this.placeName = placeName;
		this.placeCode = placeCode;
		this.count = count;
		this.stayInterval = stayInterval;
	}
	
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceCode() {
		return placeCode;
	}

	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getStayInterval() {
		return stayInterval;
	}

	public void setStayInterval(Long stayInterval) {
		this.stayInterval = stayInterval;
	}

	public List<TimeIntervalBean> getTimeIntervalList() {
		return timeIntervalList;
	}

	public void setTimeIntervalList(List<TimeIntervalBean> timeIntervalList) {
		this.timeIntervalList = timeIntervalList;
	}
}
