package com.taiji.pubsec.szpt.snapshot.service;

import java.util.List;

import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;

public interface InfoSnapshotService {
	
	/**
	 * 保存快照 
	 * @param 快照 信息
	 * @return true存储快照成功  false储存快照失败
	 */
	public Boolean saveInfoSnapshot(InfoSnapshot infoSnapshot);
	
	/**
	 * 删除快照 
	 * @param 快照 
	 * @return true删除快照成功  false删除快照失败
	 */
	public Boolean deleteInfoSnapshot(InfoSnapshot infoSnapshot);
	
	/**
	 * 根据快照id查询快照
	 * @param id 快照id
	 * @return 快照对象
	 */
	public InfoSnapshot findInfoSnapshotById(String id);
	
	/**
	 * 根据快照类型和对象Id查找快照结果集合
	 * @param type
	 * @return List<InfoSnapshot> 快照集合
	 */
	public List<InfoSnapshot> findInfoSnapshotByTypeAndTargetId(String code,String targetId);
	
	/**
	 * 根据快照类型和对象Id查找快照结果集中最后的一个快照
	 * @param type
	 * @return 符合要求的最后一个快照
	 */
	public InfoSnapshot findInfoSnapshotListOneByTypeAndTargetId(String code,String targetId);
	
	
	/**
	 * 根据快照关联对象类型和关联对象Id查找快照结果集合
	 * @param targetId 快照关联对象id
	 * @param type 快照关联对象类型
	 * @return List<InfoSnapshot> 快照集合
	 */
	public List<InfoSnapshot> findInfoSnapshotByTargetIdAndType(String targetId, String type);
	
	/**
	 * 根据快照关联对象类型和关联对象code查找快照结果集合
	 * @param code 快照关联对象code,关联人员  code 为  身份证号
	 * @param type 快照关联对象类型
	 * @return List<InfoSnapshot> 快照集合
	 */
	public List<InfoSnapshot> findInfoSnapshotByCodeAndType(String code, String type);
	
	
	/**
	 * 根据快照关联对象类型和关联对象Id查找快照结果集合
	 * @param targetId 快照关联对象id
	 * @param type 快照关联对象类型
	 * @param code 快照关联模块名称，常量值
	 * @return List<InfoSnapshot> 快照集合
	 */
	public List<InfoSnapshot> findInfoSnapshotByTargetIdAndTypeAndCode(String targetId, String type, String code);

}
