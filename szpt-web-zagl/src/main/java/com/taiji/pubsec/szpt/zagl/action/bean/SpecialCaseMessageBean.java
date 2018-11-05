package com.taiji.pubsec.szpt.zagl.action.bean;

public class SpecialCaseMessageBean {
	
	private String id;
	private String top;
	private String personName;
	private String personId;
	private String department;
	private String context;
	private String creatTime;
	private String unitName;
	
	
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	
	  @Override  
	    public boolean equals(Object obj) {  
	        if(obj == null) return false;  
	        if(this == obj) return true;  
	        if(obj instanceof SpecialCaseMessageBean){   
	        	SpecialCaseMessageBean message =(SpecialCaseMessageBean)obj;   
	            if(message.id.equals(this.id)) return true;  
	          }  
	        return false;  
	    }  
	  

}
