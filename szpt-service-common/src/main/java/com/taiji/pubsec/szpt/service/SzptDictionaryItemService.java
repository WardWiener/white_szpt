package com.taiji.pubsec.szpt.service;

import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;

public interface SzptDictionaryItemService {

	/**
	 * 通过父级字典项code查询所有子字典项list
	 * @param parentCode 父级字典项code
	 * @param typeCode 字典类型code
 	 * @return 返回字典项list
	 */
	List<DictionaryItem> findItemsByParentCode(String parentCode, String typeCode);
	
	/**
	 * 通过like左匹配查询字典项包含的所有子集
	 * @param dictCode
	 * @return 字典项code的集合，包含自己
	 */
	public List<String> findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(String dictCode, String typeCode) ;
}
