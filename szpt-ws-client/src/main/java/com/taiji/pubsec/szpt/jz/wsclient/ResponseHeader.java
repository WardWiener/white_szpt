package com.taiji.pubsec.szpt.jz.wsclient;

/**
 * webService返回数据头部信息
 * @author Administrator
 *
 */
public class ResponseHeader {
	
	/**
	 * 请求状态标识
	 */
	private String success;
	
	/**
	 * 错误代码
	 */
	private String errorcode;

	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}

	/**
	 * @return the errorcode
	 */
	public String getErrorcode() {
		return errorcode;
	}

	

}
