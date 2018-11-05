package com.taiji.pubsec.szpt.placemonitor.pojo;


/**
 * 统计结果
 * @author wangfx
 *
 */
public class PlaceSumInfo implements Comparable<PlaceSumInfo>{

	private String groupName;	//场所名称
	
	private String groupCode;  //场所编码
	
	private int count;	//命中次数
	
	private int totalInterval;	//驻留时长总和
	
	private String longitude;//经度
	
	private String latitude;
	
	public PlaceSumInfo() {
		super();
	}
	
	public PlaceSumInfo(String groupName, Number count) {
		super();
		this.groupName = groupName;
		this.count = (null == count ? 0 :count.intValue());
	}
	
	public PlaceSumInfo(String groupName, String groupCode, String longitude,String latitude) {
		super();
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.longitude = (null == longitude ? null :longitude);
		this.latitude = (null == latitude ? null :latitude);
	}
	
	public PlaceSumInfo(String groupName, Number count,String longitude,String latitude) {
		super();
		this.groupName = groupName;
		this.count = (null == count ? 0 :count.intValue());
		this.longitude = (null == longitude ? null :longitude);
		this.latitude = (null == latitude ? null :latitude);
	}
	
	
	public PlaceSumInfo(String groupName, Number count, Number totalInterval) {
		super();
		this.groupName = groupName;
		this.count = (null == count ? 0 :count.intValue());
		this.totalInterval = (null == totalInterval ? 0 :totalInterval.intValue());
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalInterval() {
		return totalInterval;
	}

	public void setTotalInterval(int totalInterval) {
		this.totalInterval = totalInterval;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public int compareTo(PlaceSumInfo o) {
		return -(this.count - o.count) ;
	}
	
	
}
