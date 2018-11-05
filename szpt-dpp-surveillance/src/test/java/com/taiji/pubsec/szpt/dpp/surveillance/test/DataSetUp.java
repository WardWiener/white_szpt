package com.taiji.pubsec.szpt.dpp.surveillance.test;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.complex.tools.attachment.databaseimpl.DatabaseAttachment;

public class DataSetUp{
	
	private static Logger LOGGER = LoggerFactory.getLogger(AbstractTransactionalJUnit4SpringContextTests.class);
	
	@Autowired
	@SuppressWarnings("rawtypes")
	protected Dao dao ;
	
	@Autowired
	protected SqlDao sqlDao ;

	@SuppressWarnings("unchecked")
	protected void saveDatabaseAttachments(){
		LOGGER.debug("---------------------------------start:生成测试数据：数据库类型的图片数据------------------------------------------------");
		String imgBase64 = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAAANSURBVBhXY/j///9/AAn7A/0FQ0XKAAAAAElFTkSuQmCC" ;
		
		DatabaseAttachment attach1 = new DatabaseAttachment() ;
		attach1.setId("copy1");
		attach1.setAttachment(getImgByte(imgBase64));
		this.dao.save(attach1);
		
		DatabaseAttachment attach2 = new DatabaseAttachment() ;
		attach2.setId("copy2");
		attach2.setAttachment(getImgByte(imgBase64));
		this.dao.save(attach2);
		
		DatabaseAttachment attach3 = new DatabaseAttachment() ;
		attach3.setId("copy3");
		attach3.setAttachment(getImgByte(imgBase64));
		this.dao.save(attach3);
		
		DatabaseAttachment attach4 = new DatabaseAttachment() ;
		attach4.setId("copy4");
		attach4.setAttachment(getImgByte(imgBase64));
		this.dao.save(attach4);
		
		DatabaseAttachment attach5 = new DatabaseAttachment() ;
		attach5.setId("copy5");
		attach5.setAttachment(getImgByte(imgBase64));
		this.dao.save(attach5);
		LOGGER.debug("---------------------------------end:生成测试数据：数据库类型的图片数据------------------------------------------------");
	}
	
	private byte[] getImgByte(String imgBase64){
		InputStream in = null;
		try{
			in = FileFmtUtils.stringToInputStream(imgBase64);
			return FileFmtUtils.readByBytes(in);
			
		}catch(Exception e){
			throw new IllegalArgumentException(e) ;
		}finally{
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
