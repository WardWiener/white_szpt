package com.taiji.pubsec.szpt.zagl.action.bean;

public class ZaxqTableTdBean {
	
	/**
	 * 资料id
	 */
	private String id ;
	
	/**
	 * 资料名称
	 */
	private String fileName;

	/**
	 * 资料上传时间
	 */
	private String uploadTime;
	
	/**
	 * 资料类型
	 */
	private String fileType;
	
	/**
	 * 资料大小
	 */
	private String fileSize;
	
	/**
	 * 资料上传人
	 */
	private String fileUploadPerson;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileUploadPerson() {
		return fileUploadPerson;
	}

	public void setFileUploadPerson(String fileUploadPerson) {
		this.fileUploadPerson = fileUploadPerson;
	}
	
}
