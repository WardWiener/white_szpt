package com.taiji.pubsec.szpt.placemonitor.action.bean;

public class PlaceSumInfoBean {
	
	private String groupName;	//分组名称
	
	private String name;
	
	private int count;	//命中次数
	
	private int y;
	
	private int totalInterval;	//驻留时长总和

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTotalInterval() {
		return totalInterval;
	}

	public void setTotalInterval(int totalInterval) {
		this.totalInterval = totalInterval;
	}
	

}
