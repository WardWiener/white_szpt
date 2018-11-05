package com.taiji.pubsec.szpt.zagl.action.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LiuYanFanKuiBean {
	
    private List<DepartmentBean> departmentLst = new ArrayList<DepartmentBean>();
    
    private List<RoleAndPersonLstBean> roleAndPersonsLst = new ArrayList<RoleAndPersonLstBean>();
    
	public List<DepartmentBean> getDepartmentLst() {
		return departmentLst;
	}

	public void setDepartmentLst(List<DepartmentBean> departmentLst) {
		this.departmentLst = departmentLst;
	}

	public List<RoleAndPersonLstBean> getRoleAndPersonsLst() {
		return roleAndPersonsLst;
	}

	public void setRoleAndPersonsLst(List<RoleAndPersonLstBean> roleAndPersonsLst) {
		this.roleAndPersonsLst = roleAndPersonsLst;
	}

}
