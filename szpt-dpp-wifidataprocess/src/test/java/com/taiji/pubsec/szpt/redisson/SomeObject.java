package com.taiji.pubsec.szpt.redisson;

import java.util.ArrayList;
import java.util.List;

public class SomeObject {

	private String name;
	private Long value;
	
	private List<String> list =  new ArrayList<>();
	
	public SomeObject() {
	}

	public SomeObject(String name, Long value) {
		super();
		list.add("1");
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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}
