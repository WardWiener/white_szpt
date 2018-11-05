package com.taiji.pubsec.szpt.bean;

public class ThInfo {

	private String ysName ;
	private String ysCode ;
	private String xsName ;
	private String xsCode ;
	private Integer count ;

	public ThInfo(String ysName, String ysCode, String xsName, Integer count){
		this.ysName = ysName ;
		this.ysCode = ysCode ;
		this.xsName = xsName ;
		this.count = count ;
	}
	
	public ThInfo(String ysName, String ysCode, String xsName, String xsCode, Integer count){
		this.ysName = ysName ;
		this.ysCode = ysCode ;
		this.xsName = xsName ;
		this.xsCode = xsCode ;
		this.count = count ;
	}

	public String getYsName() {
		return ysName;
	}

	public void setYsName(String ysName) {
		this.ysName = ysName;
	}

	public String getYsCode() {
		return ysCode;
	}

	public void setYsCode(String ysCode) {
		this.ysCode = ysCode;
	}

	public String getXsName() {
		return xsName;
	}

	public void setXsName(String xsName) {
		this.xsName = xsName;
	}

	public String getXsCode() {
		return xsCode;
	}

	public void setXsCode(String xsCode) {
		this.xsCode = xsCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
