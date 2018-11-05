package com.taiji.pubsec.szpt.attachment.action;

import java.io.InputStream;

import com.taiji.pubsec.complex.tools.attachment.AttachmentCopy;
import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.web.action.ExportInfoReq;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;

public class AttachmentHandler {
	
	public static ExportInfoReq makeExportInfoReqByAttachmentId(String attachmentId){
		IAttachmentCustomizedService service = (IAttachmentCustomizedService)SpringContextUtil.getBean("attachmentCustomizedService") ;
		Attachment entity = service.findById(attachmentId) ;
		
		AttachmentMeta attaMeta = entity.getAttachmentMeta() ;
		
		InputStream in = null;
		
		ExportInfoReq req = new ExportInfoReq();
		
		if(attaMeta.getAttachmentCopies().size()>0){
			AttachmentCopy ac = attaMeta.getAttachmentCopies().get(0) ;
			in = ac.getInputStream();
			req.setIn(in);
			req.setName(attaMeta.getName());
			req.setLength(Long.valueOf(ac.getProperty("filelenth").toString()));
		}
		return req ;
	}

}
