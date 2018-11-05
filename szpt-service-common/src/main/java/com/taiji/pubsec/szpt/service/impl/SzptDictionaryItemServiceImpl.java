package com.taiji.pubsec.szpt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.service.SzptDictionaryItemService;

/**
 * 字典项
 * @author zhangjn
 *
 */
@Service("szptDictionaryItemService")
public class SzptDictionaryItemServiceImpl implements SzptDictionaryItemService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DictionaryItem> findItemsByParentCode(String parentCode, String typeCode) {
		String xql = "select d from DictionaryItem as d where d.parentItem.code = ? and d.dicType.code = ?";
		return dao.findAllByParams(DictionaryItem.class, xql, new Object[]{parentCode, typeCode});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllDictCodeIncludedAllChildrenAndSelfByCodeLike(String dictCode, String typeCode) {
		StringBuilder xql = new StringBuilder("select d.code from DictionaryItem as d where d.parentItem.code like :dictCode and d.dicType.code=:typeCode");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("dictCode", SQLTool.SQLSpecialChTranfer(dictCode) + "%");
		xqlMap.put("typeCode", typeCode);
		List<String> list = this.dao.findAllByParams(String.class, xql.toString(), xqlMap) ;
		list.add(dictCode) ;
		return list ;
	}

}
