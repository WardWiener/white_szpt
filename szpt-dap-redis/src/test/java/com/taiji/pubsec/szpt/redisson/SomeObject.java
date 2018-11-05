package com.taiji.pubsec.szpt.redisson;

public class SomeObject {

	private String name;
	private Long value;
	
	public SomeObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SomeObject(String name, Long value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SomeObject [name=" + name + ", value=" + value + "]";
	}
	
}
