package com.taiji.pubsec.szpt.util;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.szpt.util.bean.OptionItemBean;

public class SzptFmtUtil {
	
	public static OptionItemBean dictionaryItemToOptionItemBean(DictionaryItem di){
		if(di == null){
			return null;
		}
		OptionItemBean bean = new OptionItemBean();
		bean.setId(di.getId());
		bean.setCode(di.getCode());
		bean.setName(di.getName());
		bean.setParentTypeId(di.getDicType() == null?null:di.getDicType().getId());
		bean.setParentItemId(di.getParentItem() == null?null:di.getParentItem().getId());
		return bean;
	}
	
	public static List<OptionItemBean> dictionaryItemToOptionItemBean(List<DictionaryItem> dis){
		List<OptionItemBean> list = new ArrayList<OptionItemBean>() ;
		for(DictionaryItem di:dis){
			list.add(dictionaryItemToOptionItemBean(di)) ;
		}
		return list;
	}

}
