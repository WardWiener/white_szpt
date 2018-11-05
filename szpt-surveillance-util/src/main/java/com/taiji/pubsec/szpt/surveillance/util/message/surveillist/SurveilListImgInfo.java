package com.taiji.pubsec.szpt.surveillance.util.message.surveillist;

import java.io.Serializable;

public class SurveilListImgInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6057449889394151656L;
	
	/**
	 * 布控单多端布控图片实体的id
	 */
	private String imgClueId ;
	/**
	 * 图片的base64字符串
	 */
	private String imgBase64 ;
	
	public SurveilListImgInfo(String imgClueId, String imgBase64){
		this.imgClueId = imgClueId ;
		this.imgBase64 = imgBase64 ;
	}

	public String getImgBase64() {
		return imgBase64;
	}

	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}

	public String getImgClueId() {
		return imgClueId;
	}

	public void setImgClueId(String imgClueId) {
		this.imgClueId = imgClueId;
	}
	
}
