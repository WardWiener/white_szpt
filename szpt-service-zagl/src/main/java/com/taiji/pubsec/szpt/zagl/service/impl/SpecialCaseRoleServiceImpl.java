package com.taiji.pubsec.szpt.zagl.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.Constant.ROLE_TYPE;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleService;

/**
 * 专案角色维护 --实现
 * @author liyanpu
 *
 */
@Service("specialCaseRoleService")
public class SpecialCaseRoleServiceImpl implements SpecialCaseRoleService{

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialCaseRole> findOnloadData() {
		String hql="select  s from  SpecialCaseRole s where 1=? order by s.code asc";
		return dao.findAllByParams(SpecialCaseRole.class, hql, new Object[] {1});
	}

	/**
	 * 添加
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void saveSpecialCaseRole(String name) {
		String code="";
		String hql="select  max(code) from  SpecialCaseRole  where 1=?";
		List<String> list=dao.findAllByParams(SpecialCaseRole.class, hql, new Object[] {1});
		code = String.valueOf(list.get(0));
		if("null".equals(code)){
			code="0";
		}
		code=String.valueOf(Integer.parseInt(code)+1);
		code=changeCode(code);
		SpecialCaseRole  spe=new SpecialCaseRole();
		spe.setCode(code);
		spe.setName(name);
		spe.setState(DICT.DICT_ENABLED.getValue());
		spe.setType(ROLE_TYPE.ROLE_TYPE_ZDY.getValue());
		this.dao.save(spe);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateSpecialCaseRole(String name, String code) {
		String hql="from  SpecialCaseRole  where code=?";
		List<SpecialCaseRole> list=dao.findAllByParams(SpecialCaseRole.class, hql, new Object[] {code});
		Iterator<SpecialCaseRole> it=list.iterator();
		while(it.hasNext()){
			SpecialCaseRole perInfo=(SpecialCaseRole)it.next();
			perInfo.setName(name);
			dao.update(perInfo);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteSpecialCaseRole(String code) {
		String hql="from  SpecialCaseRole where code=?";
		List<SpecialCaseRole> list=dao.findAllByParams(SpecialCaseRole.class, hql, new Object[] {code});
		Iterator<SpecialCaseRole> it=list.iterator();
		while(it.hasNext()){
			SpecialCaseRole perInfo=(SpecialCaseRole)it.next();
			dao.delete(perInfo);
		}
		
	}
	private String changeCode(String code){
		if(code.length()==1){
			code="000"+code;
		}else if(code.length()==2){
			code="00"+code;
		}else if(code.length()==3){
			code="0"+code;
		}
		return code;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void stopSpecialCaseRole(List<String> codeArr) {
		for(int i=0;i<codeArr.size();i++){
			String hql="from  SpecialCaseRole  where code=?";
			List<SpecialCaseRole> list=dao.findAllByParams(SpecialCaseRole.class, hql, new Object[] {codeArr.get(i)});
			Iterator<SpecialCaseRole> it=list.iterator();
			while(it.hasNext()){
				SpecialCaseRole perInfo=(SpecialCaseRole)it.next();
				perInfo.setState(DICT.DICT_DISENABLED.getValue());
				dao.update(perInfo);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void startSpecialCaseRole(List<String> codeArr) {
		for(int i=0;i<codeArr.size();i++){
			String hql="from  SpecialCaseRole  where code=?";
			List<SpecialCaseRole> list=dao.findAllByParams(SpecialCaseRole.class, hql, new Object[] {codeArr.get(i)});
			Iterator<SpecialCaseRole> it=list.iterator();
			while(it.hasNext()){
				SpecialCaseRole perInfo=(SpecialCaseRole)it.next();
				perInfo.setState(DICT.DICT_ENABLED.getValue());
				dao.update(perInfo);
			}
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCaseRole findById(String roleId) {
		return (SpecialCaseRole) dao.findById(SpecialCaseRole.class, roleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialCaseRole> findRolesByState(String state) {
		String xql = "select r from SpecialCaseRole as r where r.state = ?";
		return dao.findAllByParams(SpecialCaseRole.class, xql, new Object[]{state});
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCaseRole findRoleByPersonIdAndCaseId(String personId, String caseId) {
		String xql = "select a.role from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.person.id = ?";
		return (SpecialCaseRole) dao.findByParams(SpecialCaseRole.class, xql, new Object[]{caseId, personId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isRoleUsed(String roleId) {
		String xql = "select a from SpecialCaseRoleAssignment as a where a.role.id = ?";
		List<SpecialCaseRoleAssignment> assignments = dao.findAllByParams(SpecialCaseRoleAssignment.class, xql, new Object[]{roleId});
		if(assignments.size() > 0){
			return true;
		}else{
			return false;
		}
	}

}
