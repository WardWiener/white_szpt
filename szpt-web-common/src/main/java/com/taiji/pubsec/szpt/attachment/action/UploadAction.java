package com.taiji.pubsec.szpt.attachment.action;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;

@SuppressWarnings("serial")
@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends BaseAction{
	
	//文件对象
	private File file ;
	//文件名
	private String fileName;
	//附件标识
	private String uploadFileId;
	//状态标识
	private String returnCode;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService ;
	
	/**
	 * 上传
	 * @return
	 */
	public String uploadFile(){
		log.debug("receive uploaded file: {}", fileName);
		if(file == null) {
			log.debug("receive uploaded file is null");
		}
		Attachment attachment = new Attachment();
		attachmentCustomizedService.create(attachment, fileName, file, null, null);
		uploadFileId = attachment.getId();
		log.debug("saved uploaded file: {}", uploadFileId);
		setReturnCode("SUCCESS") ;
		return SUCCESS;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadFileId() {
		return uploadFileId;
	}

	public void setUploadFileId(String uploadFileId) {
		this.uploadFileId = uploadFileId;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
}
