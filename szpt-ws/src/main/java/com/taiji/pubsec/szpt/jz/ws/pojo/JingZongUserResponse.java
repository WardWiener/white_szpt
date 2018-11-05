package com.taiji.pubsec.szpt.jz.ws.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 警综人员返回数据结构
 * @author Administrator
 *
 */
public class JingZongUserResponse {
	
	private ResponseHeader header;
	
	private ResponsePageInfo pageInfo;
	
	private List<JingZongUser> data = new ArrayList<>();

	/**
	 * @return the header
	 */
	public ResponseHeader getHeader() {
		return header;
	}

	/**
	 * @return the pageInfo
	 */
	public ResponsePageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * @return the data
	 */
	public List<JingZongUser> getData() {
		return data;
	}
	
	

}
