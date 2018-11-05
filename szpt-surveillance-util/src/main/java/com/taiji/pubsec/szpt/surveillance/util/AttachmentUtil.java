package com.taiji.pubsec.szpt.surveillance.util;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.complex.tools.attachment.AttachmentService;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentCopyImpl;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentMetaImpl;
import com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment;

public class AttachmentUtil {

	public static AttachmentMeta getInstanceOfAttachmentMeta(InputStream in){
		DefaultAttachmentMetaImpl am = new DefaultAttachmentMetaImpl();
		
		am.setName(UUID.randomUUID().toString());
		DefaultAttachmentCopyImpl ac = new DefaultAttachmentCopyImpl();
		ac.setCreateTime(new Date());
        //按数据库系统的格式存
        ac.setStorageType(DatabaseAttachment.class.getName());
        am.getAttachmentCopies().add(ac);
        
        AttachmentService attachmentService = SpringContextUtil.getApplicationContext().getBean(AttachmentService.class);
        attachmentService.create(am, in);
        
        return am;
	} 
}
