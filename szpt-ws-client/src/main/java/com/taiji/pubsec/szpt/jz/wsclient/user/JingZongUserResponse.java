package com.taiji.pubsec.szpt.jz.wsclient.user;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.jz.wsclient.ResponseHeader;
import com.taiji.pubsec.szpt.jz.wsclient.ResponsePageInfo;

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
