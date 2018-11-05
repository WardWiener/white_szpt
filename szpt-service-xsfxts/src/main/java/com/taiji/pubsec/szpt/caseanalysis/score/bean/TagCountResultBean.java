package com.taiji.pubsec.szpt.caseanalysis.score.bean;

import java.io.Serializable;

/**
 * 刑事案件分析首页发案处所、作案特点、作案时段统计结果Bean
 * 
 * @author WangLei
 *
 */
public class TagCountResultBean implements Serializable,Comparable<TagCountResultBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tagValue;// 打标值
	
	private int number;// 数量
	
	private String proportion;// 环比

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

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	@Override
	public int compareTo(TagCountResultBean o) {
		if(this.number > o.getNumber()){
			return -1;
		}else if(this.number < o.getNumber()){
			return 1;
		}
		return 0;
	}

	
}
