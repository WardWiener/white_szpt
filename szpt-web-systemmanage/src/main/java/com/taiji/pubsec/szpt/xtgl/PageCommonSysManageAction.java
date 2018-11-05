package com.taiji.pubsec.szpt.xtgl;





import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;


/**
 * 系统管理公共的Action
 * 
 * @author xinfan
 *
 */
public class PageCommonSysManageAction extends PageCommonAction {
	private List<PublicBean> idList;
	private List<DictionaryItem> propertyList;
	private List<DictionaryItem> stateList;
	private String flag;
	private String msg;


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DictionaryItem> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<DictionaryItem> propertyList) {
		this.propertyList = propertyList;
	}

	public List<DictionaryItem> getStateList() {
		return stateList;
	}

	public void setStateList(List<DictionaryItem> stateList) {
		this.stateList = stateList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<PublicBean> getIdList() {
		return idList;
	}

	public void setIdList(List<PublicBean> idList) {
		this.idList = idList;
	}

}
