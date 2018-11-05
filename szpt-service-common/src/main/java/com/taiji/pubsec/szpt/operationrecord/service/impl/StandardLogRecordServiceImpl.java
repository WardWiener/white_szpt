package com.taiji.pubsec.szpt.operationrecord.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.operationrecord.service.StandardLogRecordService;

@Service("logRecordService")
public class StandardLogRecordServiceImpl implements StandardLogRecordService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public void save(StandardLogRecord record) {
		dao.save(record);
	}


}
