package com.taiji.pubsec.szpt.xtgl;



/**
 * 组织机构
 * 
 * @author xinfan
 *
 */
public class OrganizationBean {

	private String shortName;// 简称
	private String tel;// 电话
	private String fax;// 传真
	private String addr;// 地址
	private String into;// 备注
	private String status;// 状态
	private String type;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getInto() {
		return into;
	}

	public void setInto(String into) {
		this.into = into;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
