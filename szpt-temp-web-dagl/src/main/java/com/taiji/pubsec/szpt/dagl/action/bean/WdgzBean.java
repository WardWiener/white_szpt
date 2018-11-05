package com.taiji.pubsec.szpt.dagl.action.bean;

public class WdgzBean {

	/**
	 * 关注时间
	 */
	private String followTime;

	private String id;
	/**
	 * 被关注人身份证号
	 */
	private String name;
	/**
	 * 被关注人身份证号
	 */
	
	private String idCard;
	/**
	 * 是否置顶
	 */
	private boolean isStick;

	/**
	 * 是否是嫌疑人
	 */
	private boolean isXyr;
	
	/**
	 * 是否是高危人或嫌疑人
	 */
	private boolean isGwr;
	
	//人员类别
	private String rylb;

	//积分
	private String jf;
	
	private String yjlx;
	
	public String getRylb() {
		return rylb;
	}

	public void setRylb(String rylb) {
		this.rylb = rylb;
	}

	public String getJf() {
		return jf;
	}

	public void setJf(String jf) {
		this.jf = jf;
	}

	public String getYjlx() {
		return yjlx;
	}

	public void setYjlx(String yjlx) {
		this.yjlx = yjlx;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public boolean isStick() {
		return isStick;
	}

	public void setStick(boolean isStick) {
		this.isStick = isStick;
	}

	public boolean isXyr() {
		return isXyr;
	}

	public void setXyr(boolean isXyr) {
		this.isXyr = isXyr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGwr() {
		return isGwr;
	}

	public void setGwr(boolean isGwr) {
		this.isGwr = isGwr;
	}
}
