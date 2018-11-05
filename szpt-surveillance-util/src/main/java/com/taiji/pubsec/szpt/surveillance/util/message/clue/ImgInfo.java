package com.taiji.pubsec.szpt.surveillance.util.message.clue;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class ImgInfo implements Serializable,ClueInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3112482459088565688L;
	
	/**
	 * 摄像头编码
	 */
	private String cameraCode; 	
	
	/**
	 * 摄像头地址
	 */
	private String cameraAddr ;
	
	/**
	 * 置信度
	 */
	private Double confidenceLevel ;
	
	/**
	 * 预警时间
	 */
	private Long catchDate ;
	
	/**
	 * 摄像头经度
	 */
    private Double longitude;
	/**
	 * 摄像头纬度
	 */
    private Double latitude; 
	
	/**
	 * 布控单编码
	 */
	private String surveilListCode ;

	/**
	 * base64的图片字符串
	 */
	private String imgBase64 ;
	
	@Override
	public String sketch() {
		return "摄像头地址：" + cameraAddr;
	}
	@Override
	public String detailDescription() {
		/**
		 * 图片字符串不放入json字符串所以手动生成一个json字符串
		 */
		JSONObject obj = new JSONObject() ;
		obj.put("cameraCode", cameraCode);
		obj.put("longitude", longitude);
		obj.put("latitude", latitude);
		obj.put("surveilListCode", surveilListCode);
		obj.put("cameraAddr", cameraAddr);
		obj.put("confidenceLevel", confidenceLevel);
		obj.put("catchDate", catchDate);
		return obj.toString();
	}
	
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public String getSurveilListCode() {
		return surveilListCode;
	}
	public void setSurveilListCode(String surveilListCode) {
		this.surveilListCode = surveilListCode;
	}
	public String getCameraCode() {
		return cameraCode;
	}
	public void setCameraCode(String cameraCode) {
		this.cameraCode = cameraCode;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getCameraAddr() {
		return cameraAddr;
	}
	public void setCameraAddr(String cameraAddr) {
		this.cameraAddr = cameraAddr;
	}
	public Double getConfidenceLevel() {
		return confidenceLevel;
	}
	public void setConfidenceLevel(Double confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}
	public Long getCatchDate() {
		return catchDate;
	}
	public void setCatchDate(Long catchDate) {
		this.catchDate = catchDate;
	}
	
}
