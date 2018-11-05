package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class InternetBarInfo implements Serializable,ClueInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2893597621654251810L;

	/**
	 * 身份证号
	 */
	private String idCard ;
	/**
	 * 网吧名称
	 */
	private String cyberCafeName ;
	/**
	 * 网吧编码
	 */
	private String cyberCafeCode ;
	/**
	 * 网吧地址
	 */
	private String cyberCafeAddress ;
	/**
	 * 进入时间
	 */
	private Long enterTime ;
	/**
	 * 离开时间
	 */
	private Long leaveTime ;
	
	@Override
	public String sketch() {
		return "网吧：" + cyberCafeName ;
	}
	@Override
	public String detailDescription() {
		JSONObject obj = JSONObject.fromObject(this) ;
		return obj.toString();
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCyberCafeName() {
		return cyberCafeName;
	}
	public void setCyberCafeName(String cyberCafeName) {
		this.cyberCafeName = cyberCafeName;
	}
	public String getCyberCafeCode() {
		return cyberCafeCode;
	}
	public void setCyberCafeCode(String cyberCafeCode) {
		this.cyberCafeCode = cyberCafeCode;
	}
	public String getCyberCafeAddress() {
		return cyberCafeAddress;
	}
	public void setCyberCafeAddress(String cyberCafeAddress) {
		this.cyberCafeAddress = cyberCafeAddress;
	}
	public Long getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}
	public Long getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}

}
