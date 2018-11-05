package com.taiji.pubsec.szpt.caseanalysis.tag.action.bean;

/**
 * 卷宗文书Bean
 * 
 * @author WangLei
 *
 */
public class ArchivedFileBean {

	private String id;// 文号编号
	
	private String type;// 文书类型，标识从哪个文书表中查询
	
	private String name;// 文书名称

	private String code;// 文号
	
	private String caseCode;// 案件编号
	
	private Long excuteTime;// 执行时间
	
	private String url;// 案管系统详情查看页面访问地址url

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Long getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Long excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
