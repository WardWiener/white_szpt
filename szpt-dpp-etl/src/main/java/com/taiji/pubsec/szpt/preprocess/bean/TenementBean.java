package com.taiji.pubsec.szpt.preprocess.bean;

import java.util.Date;

/**
 * @author dixiaofeng
 * @version 1.0
 * @created 08-12月-2016 19:55:01
 */
public class TenementBean {

	private String id;
	/**
	 * 身份证号
	 */
	private String idcard;
	/**
	 * 出租人身份证号
	 */
	private String renterIdcard;
	/**
	 * 出租人姓名
	 */
	private String renterName;
	/**
	 * 停租时间
	 */
	private Date endTime;
	/**
	 * 起租时间
	 */
	private Date startTime;
	/**
	 * 承租情况
	 */
	private String remark;
	/**
	 * 房屋id
	 */
	private String houseId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getRenterIdcard() {
		return renterIdcard;
	}

	public void setRenterIdcard(String renterIdcard) {
		this.renterIdcard = renterIdcard;
	}

	public String getRenterName() {
		return renterName;
	}

	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

}