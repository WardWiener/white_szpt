package com.taiji.pubsec.szpt.attachment.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.taiji.pubsec.szpt.attachment.model.Attachment;

public interface IAttachmentCustomizedService {
	
	/**
	 * save方法
	 * @param entity
	 */
	public void save(Attachment entity) ;
	
	/**
	 * 按照id查询附件
	 * @param id
	 * @return
	 */
	public Attachment findById(String id) ;
	/**
	 * 按id删除
	 * @param id
	 */
	public void delete(String id) ;
	/**
	 * 按实体删除
	 * @param entity
	 */
	public void delete(Attachment entity) ;
	
	/**
	 * 创建附件
	 * @param entity  新new的实例，将对应的ConcreteEvent或Approve或Feedback对象set进去
	 * @param fileName 要保存的文件名称，由客户端传入
	 * @param file 要保存的文件
	 * @return
	 */
	public boolean create(Attachment entity, String fileName, File file, String targetId, String type) ;
	
	/**
	 * 创建附件
	 * @param entity  新new的实例，将对应的ConcreteEvent或Approve或Feedback对象set进去
	 * @param fileName 要保存的文件名称，由客户端传入
	 * @param file 要保存的文件
	 * @return
	 */
	public boolean create(Attachment entity, String fileName, InputStream fileStream, String targetId, String type) ;

	public List<Attachment> findByTargetIdAndType(String targetId, String type);
	
	public void deleteByTargetIdAndType(String targetId, String type); 
}
