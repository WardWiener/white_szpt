package com.taiji.pubsec.szpt.operationrecord.service;

import java.util.List;

import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;

/**
 * 警综日志记录接口
 * @author dixf
 *
 */
public interface StandardLogRecordService {
	
	/**
	 * 保存操作记录
	 * @param record
	 * 
	 */
	void save(StandardLogRecord record);
	

}
