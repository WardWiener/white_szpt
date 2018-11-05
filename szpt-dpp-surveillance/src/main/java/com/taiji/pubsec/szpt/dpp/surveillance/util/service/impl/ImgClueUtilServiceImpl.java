package com.taiji.pubsec.szpt.dpp.surveillance.util.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.complex.tools.attachment.DefaultAttachmentMetaImpl;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.dpp.surveillance.util.service.ImgClueUtilService;

@Service
public class ImgClueUtilServiceImpl implements ImgClueUtilService{
	
	private Logger LOGGER = LoggerFactory.getLogger(ImgClueUtilServiceImpl.class) ;
	
	@Autowired
	private SqlDao sqlDao ;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@Override
	public InputStream getSurveilListImgAsStream(String clueId){
		
		LOGGER.debug("查询线索(id:{})对应的照片输入流", clueId);
		
		String sql = "SELECT"
				+ " p.fjy_id"
				+ " FROM t_gwry_rybk_bkzp AS p"
				+ " WHERE"
				+ " p.id=:clueId" ;
		
		Map<String, Object> sqlMap = new HashMap<String, Object>() ;
		sqlMap.put("clueId", clueId);
		
		String metaId = (String)this.sqlDao.findUnique(sql, sqlMap) ;
		LOGGER.debug("查询线索(id:{})对应的照片输入流，metaId:{}", clueId, metaId);

		AttachmentMeta meta = findAttachmentMeta(metaId) ;
		
		if(meta==null){
			throw new IllegalArgumentException("AttachmentMeta(id"+metaId+")，对应的AttachmentMeta实例不存在") ;
		}
		
		LOGGER.debug("AttachmentCopies的个数：{}", meta.getAttachmentCopies().size());
		
		return meta.getAttachmentCopies().get(0).getInputStream() ;
	}
	
	@SuppressWarnings("unchecked")
	private AttachmentMeta findAttachmentMeta(String metaId){
		return (AttachmentMeta)this.dao.findById(DefaultAttachmentMetaImpl.class, metaId) ;
	}
}
