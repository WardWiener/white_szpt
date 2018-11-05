package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分组统计结果Bean
 * 
 * @author WL-PC
 *
 */
public class HitSumBean {

	private int count; // 命中次数

	private String groupName; // 分组名称

	private int totalInterval; // 驻留时长总和

	private List<BaseBean> timeIntervalList = new ArrayList<BaseBean>(); // 时间列表

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getTotalInterval() {
		return totalInterval;
	}

	public void setTotalInterval(int totalInterval) {
		this.totalInterval = totalInterval;
	}

	public List<BaseBean> getTimeIntervalList() {
		return timeIntervalList;
	}

	public void setTimeIntervalList(List<BaseBean> timeIntervalList) {
		this.timeIntervalList = timeIntervalList;
	}

}
