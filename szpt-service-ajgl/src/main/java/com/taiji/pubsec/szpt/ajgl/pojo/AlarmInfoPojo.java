package com.taiji.pubsec.szpt.ajgl.pojo;

public class AlarmInfoPojo {
	
	private String name ;
	
	private Integer count ;
	
	private Integer hbCount ;
	
	private Integer tbCount ;
	
	public AlarmInfoPojo(String name, Number count){
		this.name = name ;
		this.count = (null == count ? 0 : count.intValue()) ;
	}
	
	public AlarmInfoPojo(String name, Number count, Number hbCount){
		this.name = name ;
		this.count = (null == count ? 0 : count.intValue()) ;
		this.hbCount = (null == hbCount ? 0 : hbCount.intValue()) ;
	}
	
	public AlarmInfoPojo(String name, Number count, Number hbCount, Number tbCount){
		this.name = name ;
		this.count = (null == count ? 0 : count.intValue()) ;
		this.hbCount = (null == hbCount ? 0 : hbCount.intValue()) ;
		this.tbCount = (null == tbCount ? 0 : tbCount.intValue()) ;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getHbCount() {
		return hbCount;
	}

	public void setHbCount(Integer hbCount) {
		this.hbCount = hbCount;
	}

	public Integer getTbCount() {
		return tbCount;
	}

	public void setTbCount(Integer tbCount) {
		this.tbCount = tbCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
