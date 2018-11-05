package com.taiji.pubsec.szpt.customizedmenu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu;
import com.taiji.pubsec.szpt.customizedmenu.service.SystemMenuService;

@Service("systemMenuService")
public class SystemMenuServiceImpl implements SystemMenuService {
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	@Override
	public void saveSystemMenu(SystemMenu sm) {
		dao.save(sm);
	}

	@Override
	public void deleteSystemMenu(SystemMenu sm) {
		List<SystemMenu> smLst = this.findAllSystemMenuByParentId(sm.getId());
		if(null != smLst && smLst.size() > 0){
			for(SystemMenu sys : smLst){
				dao.delete(sys);
			}
		}
		dao.delete(sm);
	}
	
	@Override
	public List<SystemMenu> findAllSystemMenuByParentId(String parentId){
		String[] arr = {parentId};
		return dao.findAllByParams(SystemMenu.class, "select a from " + SystemMenu.class.getName() + " as s where s.superSystemMenu = ?", arr);
	}

	@Override
	public List<SystemMenu> findAllSystemMenuByType(String type) {
		String[] arr = {type};
		return dao.findAllByParams(SystemMenu.class, "select a from " + SystemMenu.class.getName() + " as s where and s.type = ?", arr);
	}
	
	@Override
	public List<SystemMenu> findAll() {
		return (List<SystemMenu>)dao.findAll(SystemMenu.class);
	}
	
	@Override
	public SystemMenu findById(String id) {
		return (SystemMenu)dao.findById(SystemMenu.class, id);
	}

}
