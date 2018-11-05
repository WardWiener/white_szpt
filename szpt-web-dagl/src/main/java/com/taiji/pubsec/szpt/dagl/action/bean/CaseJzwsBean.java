package com.taiji.pubsec.szpt.dagl.action.bean;

/**
 * 卷宗文书Bean
 * 
 * @author xinfan
 *
 */
public class CaseJzwsBean {
	private String askAddress;// 访问地址
	private String wsCode;// 文书编号
	private String caseCode;// 案件编号
	private String wsName;// 文书名称
	private String wsType;//文书类型
	private String lrsj;//录入时间

	public String getAskAddress() {
		return askAddress;
	}

	public void setAskAddress(String askAddress) {
		this.askAddress = askAddress;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public String getWsType() {
		return wsType;
	}

	public void setWsType(String wsType) {
		this.wsType = wsType;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

}
