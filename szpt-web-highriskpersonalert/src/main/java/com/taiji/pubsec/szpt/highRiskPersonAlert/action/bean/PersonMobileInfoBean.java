package com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean;


/**
 * 人员布控的移动端信息
 */
public class PersonMobileInfoBean {

    private String id;

    private String number;    //手机号码

    private String mac; //MAC地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
