package com.taiji.pubsec.szpt.jz.ws.pojo;

/**
 * 警综推送数据结构
 * @author Administrator
 *
 * @param <T>
 */
public class JingZongDataPush<T> {
	
	/**
	 * 方法标识，用于区分人员数据还是组织机构数据
	 */
	private String method;
	
	/**
	 * 人员或组织机构的数据体
	 */
	private T data;

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	
	
	
	

}
