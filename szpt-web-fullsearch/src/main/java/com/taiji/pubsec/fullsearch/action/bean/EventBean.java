package com.taiji.pubsec.fullsearch.action.bean;

/**
 * 警情Bean
 * @author sunjd
 *
 */
public class EventBean {
	private String id;	
	private String name;	//警情名称
	private String type;	//警情类型
	private String answertime;	//接警时间
	private String level;//紧急程度
	private String occuraddress;//发生地点
	private String  occurtime;//发生时间
	private String source;  //警情来源
	private String state;  //警情状态
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getAnswertime() {
		return answertime;
	}
	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOccuraddress() {
		return occuraddress;
	}
	public void setOccuraddress(String occuraddress) {
		this.occuraddress = occuraddress;
	}
	
	public String getOccurtime() {
		return occurtime;
	}
	public void setOccurtime(String occurtime) {
		this.occurtime = occurtime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
