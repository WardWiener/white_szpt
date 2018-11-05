package com.taiji.pubsec.szpt.xtgl.user;

import java.util.Date;

public class TestReq {
	private String sname;
	private String sdanwei;
	private String szhuangtai;
	private boolean sguanli;
	private Date startDate;
	private Date endDate;
	private String scode;
	private int start;
	private int length;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getScode() {
		return scode;
	} 
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSdanwei() {
		return sdanwei;
	}
	public void setSdanwei(String sdanwei) {
		this.sdanwei = sdanwei;
	}
	public String getSzhuangtai() {
		return szhuangtai;
	}
	public void setSzhuangtai(String szhuangtai) {
		this.szhuangtai = szhuangtai;
	}
	public boolean isSguanli() {
		return sguanli;
	}
	public void setSguanli(boolean sguanli) {
		this.sguanli = sguanli;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
