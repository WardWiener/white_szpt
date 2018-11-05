package com.taiji.pubsec.szpt.zhzats.bean;

public class FloatingPopInfo {
	
	private String name ;
	
	private Integer floatingNumSum ;
	
	private Integer hbFloatingNumSum ;
	
	private Integer tbFloatingNumSum ;
	
	public FloatingPopInfo(String name, Integer floatingNumSum){
		this.name = name ;
		this.floatingNumSum = floatingNumSum ;
	}
	
	public FloatingPopInfo(String name, Integer floatingNumSum, Integer hbFloatingNumSum){
		this.name = name ;
		this.floatingNumSum = floatingNumSum ;
		this.hbFloatingNumSum = hbFloatingNumSum ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFloatingNumSum() {
		return floatingNumSum;
	}

	public void setFloatingNumSum(Integer floatingNumSum) {
		this.floatingNumSum = floatingNumSum;
	}

	public Integer getHbFloatingNumSum() {
		return hbFloatingNumSum;
	}

	public void setHbFloatingNumSum(Integer hbFloatingNumSum) {
		this.hbFloatingNumSum = hbFloatingNumSum;
	}

	public Integer getTbFloatingNumSum() {
		return tbFloatingNumSum;
	}

	public void setTbFloatingNumSum(Integer tbFloatingNumSum) {
		this.tbFloatingNumSum = tbFloatingNumSum;
	}
	
	
}
