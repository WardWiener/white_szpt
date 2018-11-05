package com.taiji.pubsec.szpt.zagl.service;

import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;



/**
 * 专案资料类型服务接口，增删该查启用停用
 * @author dixiaofeng
 * @version 1.0
 * @created 21-11月-2016 19:24:11
 */
public interface SpecialCaseMaterialTypeService {
	
	/**
	 * 查询所有资料类型
	 * @return 返回所有的资料类型list
	 */
	List<DictionaryItem> findAllTypes();
	
	/**
	 * 通过id查询字典项
	 * @param itemId 字典项id
	 * @return 返回字典项信息
	 */
	DictionaryItem findByItemId(String itemId);
	
	/**
	 * 通过资料类型状态查询相应的资料类型
	 * @param state 资料类型状态
	 * @return 返回资料类型list
	 */
	List<DictionaryItem> findTypesByState(String state);
	
	/**
	 * 新增专案资料类型
	 * @param typeName 专案资料类型名称
	 * @param 成功返回true；失败返回false
	 */
	boolean addMaterialType(String typeName);
	
	/**
	 * 通过专案资料类型id删除专案资料类型
	 * @param typeId 专案资料类型id
	 */
	void deleteMaterialType(String typeId);
	
	/**
	 * 修改专案资料类型名称
	 * @param typeId 专案资料类型id
	 * @param typeName 专案资料类型名称
	 */
	void updateMaterialType(String typeId, String typeName);

	/**
	 * 更新专案资料类型的状态，启用/停用
	 * @param typeId 专案资料类型id
	 * @param state 状态
	 */
	void updateMaterialTypeState(String typeId, String state);
	
	/**
	 * 查找所有在用的专案资料类型code
	 * @return 返回资料类型code的list
	 */
	List<String> findUsedTypes();
}