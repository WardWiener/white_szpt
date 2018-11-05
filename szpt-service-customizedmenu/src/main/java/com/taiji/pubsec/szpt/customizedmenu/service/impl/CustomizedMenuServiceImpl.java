package com.taiji.pubsec.szpt.customizedmenu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.authority.model.AuthorityResource;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu;
import com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu;
import com.taiji.pubsec.szpt.customizedmenu.service.CustomizedMenuService;
import com.taiji.pubsec.szpt.customizedmenu.service.SystemMenuService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("customizedMenuService")
public class CustomizedMenuServiceImpl implements CustomizedMenuService {
	
	@Resource
	private SystemMenuService systemMenuService ;
	
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	@Override
	public List<CustomizedMenu> findAllModuleUrlByUserId(String accountId, String type) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select c from " + CustomizedMenu.class.getName() + " as c where 1 = 1 ");
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		if(ParamMapUtil.isNotBlank(accountId)){
			hql.append(" and c.targetId = :accountId ");
			hqlMap.put("accountId", accountId);
		}
		if(ParamMapUtil.isNotBlank(type)){
			hql.append(" and c.type = :type ");
			hqlMap.put("type", type);
		}
		return (List<CustomizedMenu>) this.dao.findAllByParams(CustomizedMenu.class, hql.toString(), hqlMap) ;
	}
	@Override
	public void deleteCustomizedMenu(String UserId,String type) {
		List<CustomizedMenu> cms = findAllModuleUrlByUserId(UserId,type);
		if(cms.size() > 0){
			for(CustomizedMenu cm : cms){
				dao.delete(cm);
			}
		}
	}
	
	@Override
	public void saveCustomizedMenu(CustomizedMenu cm){
		dao.save(cm);
	}
}
