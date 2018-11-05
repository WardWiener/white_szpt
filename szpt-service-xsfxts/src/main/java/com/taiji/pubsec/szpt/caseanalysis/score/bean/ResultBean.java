package com.taiji.pubsec.szpt.caseanalysis.score.bean;

public class ResultBean {
	
	private String tagType;	//打标类型
	
	private String tagTypeCode;	//打标类型code
	
	private String tagValue;// 打标值
	
	private int number;// 数量

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTagTypeCode() {
		return tagTypeCode;
	}

	public void setTagTypeCode(String tagTypeCode) {
		this.tagTypeCode = tagTypeCode;
	}
	
	
}
