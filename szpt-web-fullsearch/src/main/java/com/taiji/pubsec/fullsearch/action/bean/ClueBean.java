package com.taiji.pubsec.fullsearch.action.bean;

/**
 * 线索bean
 * @author sunjd
 *
 */
public class ClueBean {

	private String id;
	private String name;	//事件标题
	private String state;	//状态
	private long date;	//填报时间
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	
	
	
}
