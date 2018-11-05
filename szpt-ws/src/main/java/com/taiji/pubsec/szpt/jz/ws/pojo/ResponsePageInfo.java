package com.taiji.pubsec.szpt.jz.ws.pojo;

/**
 * webService返回数据结构分页信息
 * @author Administrator
 *
 */
public class ResponsePageInfo {
	
	private int currentPage;
	
	private int totalPage;
	
	private int totalResult;

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @return the totalResult
	 */
	public int getTotalResult() {
		return totalResult;
	}
	
	

}
