package com.taiji.pubsec.fullsearch.action.bean;

/**
 * 指令Bean
 * @author sunjd
 *
 */
public class CommandBean {

	private String id;
	private String type;	//指令类型
	private String content;	//	指令内容
	private String createtime;	//创建时间
	private String receiveunit;  //接收单位
	private String askfeedbacktime; //要求反馈时间
	private String  reletedcontent;//关联数据描述
	private String  sendunit;//关联数据描述
	
	public String getSendunit() {
		return sendunit;
	}
	public void setSendunit(String sendunit) {
		this.sendunit = sendunit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getReceiveunit() {
		return receiveunit;
	}
	public void setReceiveunit(String receiveunit) {
		this.receiveunit = receiveunit;
	}
	
	public String getReletedcontent() {
		return reletedcontent;
	}
	public void setReletedcontent(String reletedcontent) {
		this.reletedcontent = reletedcontent;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getAskfeedbacktime() {
		return askfeedbacktime;
	}
	public void setAskfeedbacktime(String askfeedbacktime) {
		this.askfeedbacktime = askfeedbacktime;
	}
	
}
