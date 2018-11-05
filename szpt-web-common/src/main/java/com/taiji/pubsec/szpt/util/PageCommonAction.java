package com.taiji.pubsec.szpt.util;

/**
 * 公共的成员变量Bean 用于分页
 * 
 * @author xinfan
 *
 */
public class PageCommonAction extends LoginInfoAction {
	private static final long serialVersionUID = 1L;
	private Integer start;
	private Integer length;
	private Integer totalNum;

	private String result = ERROR;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
