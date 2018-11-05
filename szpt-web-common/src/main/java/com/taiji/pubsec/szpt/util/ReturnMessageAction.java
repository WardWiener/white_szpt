package com.taiji.pubsec.szpt.util;


/**
 * 返回信息 用于返回操作结果或状态
 * @author sunjd
 *
 */
public class ReturnMessageAction extends PageCommonAction {
	private static final long serialVersionUID = 1L;
	
	protected static final String SUPER_ROLE_CODE= "role_police";
	private String msg;
	private String flag;
	private String modifyTime;		//最新修改时间
	private String modifyPerson;	//最新修改人
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

}
