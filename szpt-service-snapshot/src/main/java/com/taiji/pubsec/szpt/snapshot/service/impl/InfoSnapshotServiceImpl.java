package com.taiji.pubsec.szpt.snapshot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;

@Service
public class InfoSnapshotServiceImpl implements InfoSnapshotService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(InfoSnapshotServiceImpl.class);
	@Override
	public Boolean saveInfoSnapshot(InfoSnapshot infoSnapshot) {
		//保持快照记录始终都是10条
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", infoSnapshot.getCode());
			String xql = "select i from InfoSnapshot as i where i.code =:code order by i.createdDate desc";
			List<InfoSnapshot> isList = dao.findAllByParams(InfoSnapshot.class, xql, paramMap);
			if(isList == null || isList.isEmpty() || isList.size() < 10){
				dao.save(infoSnapshot);
			}else{
				for(int i=9;i<isList.size();i++){
					dao.delete(isList.get(i));
				}
				dao.save(infoSnapshot);
			}
		} catch (Exception e) {
			LOGGER.debug("创建打发分析结果快照失败", e);
			return false;
		}
		return true;
	}

	@Override
	public Boolean deleteInfoSnapshot(InfoSnapshot infoSnapshot) {
		try {
			dao.delete(infoSnapshot);
		} catch (Exception e) {
			LOGGER.debug("创建打发分析结果快照失败", e);
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public InfoSnapshot findInfoSnapshotById(String id) {
		return (InfoSnapshot)dao.findById(InfoSnapshot.class, id);
	}

	@Override
	public List<InfoSnapshot> findInfoSnapshotByTypeAndTargetId(String code, String targetId) {
		String[] paramValues = {code,targetId};
		return dao.findAllByParams(InfoSnapshot.class, "select i from " +InfoSnapshot.class.getName()+" as i where "
				+ " i.code = ? and i.type = ? order by i.createdDate desc", paramValues);
	}
	
	@Override
	public InfoSnapshot findInfoSnapshotListOneByTypeAndTargetId(String code, String targetId) {
		String[] paramValues = {code,targetId};
		List<InfoSnapshot> infoSnapshots =  dao.findAllByParams(InfoSnapshot.class, "select i from " +InfoSnapshot.class.getName()+" as i where "
				+ " i.code = ? and i.type = ? order by i.createdDate desc", paramValues);
		if(null != infoSnapshots && infoSnapshots.size() > 0){
			return infoSnapshots.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoSnapshot> findInfoSnapshotByTargetIdAndType(String targetId, String type) {
		String[] paramValues = {targetId, type};
		return dao.findAllByParams(InfoSnapshot.class, "select i from " +InfoSnapshot.class.getName()+" as i where "
				+ " i.targetId = ? and i.type = ? order by i.createdDate desc", paramValues);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InfoSnapshot> findInfoSnapshotByTargetIdAndTypeAndCode(String targetId, String type, String code) {
		String[] paramValues = {targetId, type, code};
		return dao.findAllByParams(InfoSnapshot.class, "select i from " +InfoSnapshot.class.getName()+" as i where "
				+ " i.targetId = ? and i.type = ? and i.code = ? order by i.createdDate desc", paramValues);
	}
	@Override
	public List<InfoSnapshot> findInfoSnapshotByCodeAndType(String code, String type) {
		String[] paramValues = {code,type};
		List<InfoSnapshot> infoSnapshots =  dao.findAllByParams(InfoSnapshot.class, "select i from " +InfoSnapshot.class.getName()+" as i where "
				+ " i.code = ? and i.type = ? order by i.createdDate desc", paramValues);
		return infoSnapshots;
	}


}
