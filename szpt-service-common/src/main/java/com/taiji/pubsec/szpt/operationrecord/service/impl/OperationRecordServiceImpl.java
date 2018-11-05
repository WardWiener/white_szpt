package com.taiji.pubsec.szpt.operationrecord.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;

@Service("operationRecordService")
public class OperationRecordServiceImpl implements IOperationRecordService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public String saveOperationRecord(OperationRecord operationRecord) {
		dao.save(operationRecord);
		return operationRecord.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationRecord> findOperationRecordByTarget(String targetId, String targetType, List<String> unitIds) {
		StringBuilder xql = new StringBuilder("select o from OperationRecord as o where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(StringUtils.isNotBlank(targetId)) {
			xql.append(" and o.targetId = :targetId");
			xqlMap.put("targetId", targetId);
		}
		if(StringUtils.isNotBlank(targetType)) {
			xql.append(" and o.targetType = :targetType");
			xqlMap.put("targetType", targetType);
		}
		if(unitIds != null && !unitIds.isEmpty()) {
			xql.append(" and o.operateUnit in (:unitIds)");
			xqlMap.put("unitIds", unitIds);
		}
		xql.append(" order by o.operateTime desc ");
		return dao.findAllByParams(OperationRecord.class, xql.toString(), xqlMap);
	}

}
