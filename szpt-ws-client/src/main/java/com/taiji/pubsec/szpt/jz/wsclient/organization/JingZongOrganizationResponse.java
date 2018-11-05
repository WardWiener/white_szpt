package com.taiji.pubsec.szpt.jz.wsclient.organization;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.jz.wsclient.ResponseHeader;
import com.taiji.pubsec.szpt.jz.wsclient.ResponsePageInfo;
/**
 * 警综组织机构返回结构
 * @author Administrator
 *
 */
public class JingZongOrganizationResponse {
	
	private ResponseHeader header;
	
	private ResponsePageInfo pageInfo;
	
	private List<JingZongOrganization> data = new ArrayList<>();

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
	public List<JingZongOrganization> getData() {
		return data;
	}
	
	

}
