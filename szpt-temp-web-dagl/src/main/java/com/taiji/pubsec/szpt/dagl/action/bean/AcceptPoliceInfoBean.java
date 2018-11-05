package com.taiji.pubsec.szpt.dagl.action.bean;

public class AcceptPoliceInfoBean {
	public String accContent;// 接警内容
	public String accOrderCell;// 指挥单元
	public String accTime;// 接警时间
    public String id;
	public String getAccContent() {
		return accContent;
	}

	public void setAccContent(String accContent) {
		this.accContent = accContent;
	}

	public String getAccOrderCell() {
		return accOrderCell;
	}

	public void setAccOrderCell(String accOrderCell) {
		this.accOrderCell = accOrderCell;
	}

	public String getAccTime() {
		return accTime;
	}

	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
