package com.taiji.pubsec.szpt.bean;

public class AlarmInfo {
	
	private String name ;
	private String nameCode ;
	
	private String nameAdd1 ;

	private Integer count ;
	
	private Integer hbCount ;
	
	private Integer tbCount ;
	
	public AlarmInfo(String name, Integer count){
		this.name = name ;
		this.count = count ;
	}
	
	public AlarmInfo(String name, String nameCode, String nameAdd1, Integer count){
		this.name = name ;
		this.nameCode = nameCode ;
		this.nameAdd1 = nameAdd1 ;
		this.count = count ;
	}
	
	public AlarmInfo(String name, String nameCode, Integer count, Integer hbCount){
		this.name = name ;
		this.nameCode = nameCode ;
		this.count = count ;
		this.hbCount = hbCount ;
	}
	
	public AlarmInfo(String name, String nameAdd1, Integer count){
		this.name = name ;
		this.nameAdd1 = nameAdd1 ;
		this.count = count ;
	}
	
	public AlarmInfo(String name, Integer count, Integer hbCount){
		this.name = name ;
		this.count = count ;
		this.hbCount = hbCount ;
	}
	
	public AlarmInfo(String name, Integer count, Integer hbCount, Integer tbCount){
		this.name = name ;
		this.count = count ;
		this.hbCount = hbCount ;
		this.tbCount = tbCount ;
	}
	
	public AlarmInfo(Integer count){
		this.count = count ;
	}
	
	public AlarmInfo(Integer count, Integer hbCount){
		this.count = count ;
		this.hbCount = hbCount ;
	}
	
	public AlarmInfo(Integer count, Integer hbCount, Integer tbCount){
		this.count = count ;
		this.hbCount = hbCount ;
		this.tbCount = tbCount ;
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

	public String getNameAdd1() {
		return nameAdd1;
	}

	public void setNameAdd1(String nameAdd1) {
		this.nameAdd1 = nameAdd1;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	
	 
}
