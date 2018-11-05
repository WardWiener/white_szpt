package com.taiji.pubsec.szpt.zagl.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialService;
@Service("specialCaseMaterialService")
public class SpecialCaseMaterialServiceImpl implements
		SpecialCaseMaterialService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpecialCaseMaterialServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao ;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addMaterial(SpecialCaseMaterial material) {
		try {
			dao.save(material);
		} catch(Exception e) {
			LOGGER.debug("添加专案资料失败", e);
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteMaterial(String materialId) {
		try{
			dao.delete(SpecialCaseMaterial.class, materialId);
		}catch(Exception e){
			LOGGER.debug("删除专案资料失败",e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCaseMaterial> findMaterialByCase(String caseId,
			Map<String, Object> paramsMap, int pageNo, int pageSize) {
		
		Map<String, Object> hqlMap = new HashMap<String, Object>() ;
		StringBuilder hql = new StringBuilder("select  scm  from "
				+ SpecialCaseMaterial.class.getName() + " as scm where 1 = 1");
		
		if(null != caseId){
			hql.append(" and scm.specialCase.id = :caseId ");
			hqlMap.put("caseId", caseId);  
		}
	
		if(ParamMapUtil.isNotBlank(paramsMap.get("uploadPenson"))){
			hql.append(" and scm.createPerson.id = :createPerson ");
			hqlMap.put("createPerson", (String)paramsMap.get("uploadPenson"));  
		}
		
		if(ParamMapUtil.isNotBlank(paramsMap.get("fileName"))){
			hql.append(" and scm.createdName like :createdName ");
			SQLTool.SQLAddEscape(hql);
			hqlMap.put("createdName",  "%" + SQLTool.SQLSpecialChTranfer((String)paramsMap.get("fileName")) + "%"  );  
		}
		
		if(ParamMapUtil.isNotBlank(paramsMap.get("startDate"))){
			hql.append(" and scm.createdTime >= :startDate ");
			Date startDay = DateFmtUtil.longToDate(Long.parseLong(paramsMap.get("startDate").toString())) ;
			hqlMap.put("startDate", startDay);  
		}
		
		if(ParamMapUtil.isNotBlank(paramsMap.get("endDate"))){
			Date endDate = DateFmtUtil.longToDate(Long.parseLong(paramsMap.get("endDate").toString())) ;
			hql.append(" and scm.createdTime < :endDate ");
			hqlMap.put("endDate", endDate);  
		}
		
		if(ParamMapUtil.isNotBlank(paramsMap.get("zazllxCode"))){
			hql.append(" and scm.type = :type ");
			hqlMap.put("type", (String)paramsMap.get("zazllxCode"));  
		}
		hql.append(" order by scm.createdTime desc");
		
		return dao.findByPage(SpecialCaseMaterial.class, hql.toString(), hqlMap, pageNo, pageSize);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCaseMaterial findMaterialById(String materialId) {
		return (SpecialCaseMaterial)dao.findById(SpecialCaseMaterial.class, materialId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateMaterial(SpecialCaseMaterial material) {
		try{
			dao.update(material);
		}catch(Exception e){
			LOGGER.debug("更新专案资料失败",e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCaseMaterial findMaterialByConditions(String fileName, String fileType, String caseId) {
		String xql = "select m from SpecialCaseMaterial as m where m.createdName = ? and m.type = ? and m.specialCase.id = ?";
		return (SpecialCaseMaterial) dao.findByParams(SpecialCaseMaterial.class, xql, new Object[]{fileName, fileType, caseId});
	}

}
