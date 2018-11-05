package com.taiji.pubsec.szpt.zagl.action.bean;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;

public class RoleAndPersonLstBean {
	
	private SpecialCaseRole role;
	
	private List<Person> personLst = new ArrayList<Person>();
	
	public SpecialCaseRole getRole() {
		return role;
	}

	public void setRole(SpecialCaseRole role) {
		this.role = role;
	}

	public List<Person> getPersonLst() {
		return personLst;
	}

	public void setPersonLst(List<Person> personLst) {
		this.personLst = personLst;
	}



}
