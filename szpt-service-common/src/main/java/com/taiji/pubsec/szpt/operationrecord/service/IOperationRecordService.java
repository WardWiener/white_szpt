package com.taiji.pubsec.szpt.operationrecord.service;

import java.util.List;

import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;

/**
 * 操作记录接口
 * @author wangfx
 *
 */
public interface IOperationRecordService {
	
	/**
	 * 保存操作记录
	 * @param operationRecord
	 * @return
	 */
	String saveOperationRecord(OperationRecord operationRecord);
	
	/**
	 * 根据主键和操作单位查找相关的操作记录，unitIds为空时查询所有相关操作记录，按操作时间倒序
	 * @param targetId 业务对象id
	 * @param targetType 业务对象类型（className）
	 * @param unitIds 操作单位列表
	 */
	List<OperationRecord> findOperationRecordByTarget(String targetId, String targetType, List<String> unitIds);

}
