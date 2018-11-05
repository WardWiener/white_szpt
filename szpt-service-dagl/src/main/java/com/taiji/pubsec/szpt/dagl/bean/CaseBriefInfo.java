package com.taiji.pubsec.szpt.dagl.bean;

import java.util.Date;

/**
 * 案件检索结果
 * @author dixiaofeng
 * @version 1.0
 * @created 21-二月-2017 9:58:12
 */
public class CaseBriefInfo {

	/**
	 * 案件编号
	 */
	private String code;
	/**
	 * 性质
	 */
	private String kind;
	/**
	 * 案件名称
	 */
	private String name;
	/**
	 * 涉案物品数量
	 */
	private int objectCount;
	/**
	 * 发案时间
	 */
	private Date occurTime;
	/**
	 * 辖区
	 */
	private String region;
	/**
	 * 分析快照数量
	 */
	private int snapshotCount;
	/**
	 * 案件状态
	 */
	private String state;
	/**
	 * 嫌疑人数量
	 */
	private int suspectCount;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getObjectCount() {
		return objectCount;
	}
	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getSnapshotCount() {
		return snapshotCount;
	}
	public void setSnapshotCount(int snapshotCount) {
		this.snapshotCount = snapshotCount;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getSuspectCount() {
		return suspectCount;
	}
	public void setSuspectCount(int suspectCount) {
		this.suspectCount = suspectCount;
	}
}