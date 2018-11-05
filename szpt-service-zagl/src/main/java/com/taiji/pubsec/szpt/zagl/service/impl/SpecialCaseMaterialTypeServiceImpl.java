package com.taiji.pubsec.szpt.zagl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialTypeService;

@Service("specialCaseMaterialTypeService")
public class SpecialCaseMaterialTypeServiceImpl implements SpecialCaseMaterialTypeService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Override
	public List<DictionaryItem> findAllTypes() {
		List<DictionaryItem> list = this.dictionaryItemService.findAllSubDictionaryItemsByTypeCode(Constant.DICT.DICT_TYPE_ZAZLLX.getValue());
		return list;
	}

	@Override
	public List<DictionaryItem> findTypesByState(String state) {
		List<DictionaryItem> listZllx = this.dictionaryItemService.findAllSubDictionaryItemsByTypeCode(state);
		List<DictionaryItem> list = new ArrayList<DictionaryItem>();
		for (DictionaryItem dictionaryItem : listZllx) {
			if(dictionaryItem.getState().equals(Constant.DICT.DICT_YES.getValue())){
				list.add(dictionaryItem);
			}
		}
		return list;
	}

	@Override
	public boolean addMaterialType(String typeName) {
		List<DictionaryItem> list = this.findAllTypes();
		int code = 0;
		for(DictionaryItem di : list){
			if(typeName == di.getName()){
				return false;
			}
			if(Integer.parseInt(di.getCode()) >= code){
				code = Integer.parseInt(di.getCode())+1;
			}
		}
		DictionaryItem dictionaryItem = new DictionaryItem();
		dictionaryItem.setCode(code+"");
		dictionaryItem.setName(typeName);
		DictionaryType dictionaryType = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_ZAZLLX.getValue());
		dictionaryItem.setDicType(dictionaryType);
		dictionaryItem.setState(Constant.DICT.DICT_NO.getValue());
		dictionaryItem.setUpdatedTime(new Date());
		dictionaryItemService.save(dictionaryItem);
		return true;
	}

	@Override
	public void deleteMaterialType(String typeId) {
		dictionaryItemService.delete(typeId);
		
	}

	@Override
	public void updateMaterialType(String typeId, String typeName) {
		List<DictionaryItem> list = this.dictionaryItemService.findAllSubDictionaryItemsByTypeCode(Constant.DICT.DICT_TYPE_ZAZLLX.getValue());
		for(DictionaryItem di : list){
			if(typeId != di.getId() && typeName == di.getName()){
				return;
			}
		}	
		DictionaryItem dictionaryItem = dictionaryItemService.findById(typeId);
		dictionaryItem.setName(typeName);
		dictionaryItemService.update(dictionaryItem);
		
	}

	@Override
	public void updateMaterialTypeState(String typeId, String state) {
		DictionaryItem dictionaryItem = dictionaryItemService.findById(typeId);
		dictionaryItem.setState(state);
		dictionaryItemService.update(dictionaryItem);
		
	}


	@Override
	public DictionaryItem findByItemId(String itemId) {
		return  dictionaryItemService.findById(itemId);
	}

	@Override
	public List<String> findUsedTypes() {
		String xql = "select distinct m.type from SpecialCaseMaterial as m where 1 = ?";
		return dao.findAllByParams(String.class, xql, new Object[]{1});
	}

}
