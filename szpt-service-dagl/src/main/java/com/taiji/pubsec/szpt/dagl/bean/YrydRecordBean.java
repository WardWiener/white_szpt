package com.taiji.pubsec.szpt.dagl.bean;

//前科信息
public class YrydRecordBean {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 案件名称
	 */
	private String name;
	
	/**
	 * 案件编号
	 */
	private String num;
	
	/**
	 * 发案时间
	 */
	private String occurrenceTime;
	
	/**
	 * 发案地点
	 */
	private String place;
	
	/**
	 * 案件类别
	 */
	private String sort;
	
	/**
	 * 嫌疑人
	 */
	private String suspect;
	
	/**
	 * 物证
	 */
	private String wuZheng;
	
	/**
	 * 卷宗
	 */
	private String fileNum;
	
	/**
	 * 情报线索
	 */
	private String clue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSuspect() {
		return suspect;
	}

	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}

	public String getWuZheng() {
		return wuZheng;
	}

	public void setWuZheng(String wuZheng) {
		this.wuZheng = wuZheng;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getClue() {
		return clue;
	}

	public void setClue(String clue) {
		this.clue = clue;
	}
	
}
