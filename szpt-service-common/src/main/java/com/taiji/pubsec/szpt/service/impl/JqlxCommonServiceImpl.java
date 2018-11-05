package com.taiji.pubsec.szpt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.service.JqlxCommonService;

@Service
public class JqlxCommonServiceImpl implements JqlxCommonService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public List<Jqlx> findJqlxsByLevel(Integer level) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select jq from "+Jqlx.class.getName()+" as jq where jq.level = :level order by jq.code");
		
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		hqlMap.put("level", level);
		List<Jqlx> list = this.dao.findAllByParams(Jqlx.class, hql.toString(), hqlMap);
		return list;
	}
	
	@Override
	public List<Jqlx> findJqlxsByChildCode(String parentCode) {
		Jqlx jqlx =this.findJqlxByCode(parentCode);
		List<Jqlx> list=new ArrayList<Jqlx>();
		if(jqlx.getId() != null){
			list = this.findJqlxsByParentId(jqlx.getId());
		}
		return list;
	}
	
	@Override
	public List<Jqlx> findJqlxsByParentCode(String parentCode) {
		Jqlx jqlx =this.findJqlxByCode(parentCode);
		List<Jqlx> list=new ArrayList<Jqlx>();
		if(jqlx.getParentId() != null){
			list = this.findJqlxsByParentId(jqlx.getParentId());
		}
		else if(jqlx.getParentId()==null){
			list = this.findJqlxsByParentIdIsNull();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Jqlx findJqlxByName(String name) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select jq from "+Jqlx.class.getName()+" as jq where jq.name = :name ");
		
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		hqlMap.put("name", name);
		List<Jqlx> list = this.dao.findAllByParams(Jqlx.class, hql.toString(), hqlMap);
		if(!list.isEmpty()){
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Jqlx findJqlxById(String id) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select jq from "+Jqlx.class.getName()+" as jq where jq.id = :id ");
		
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		hqlMap.put("id", id);
		@SuppressWarnings("unchecked")
		List<Jqlx> list = this.dao.findAllByParams(Jqlx.class, hql.toString(), hqlMap);
		if(!list.isEmpty()){
			return list.get(0);
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jqlx> findJqlxsByParentId(String parentId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select jq from "+Jqlx.class.getName()+" as jq where jq.parentId = :parentId order by jq.code");
		
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		hqlMap.put("parentId", parentId);
		List<Jqlx> list = this.dao.findAllByParams(Jqlx.class, hql.toString(), hqlMap);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Jqlx> findJqlxsByParentIdIsNull() {
		StringBuilder hql = new StringBuilder();
		hql.append(" select jq from "+Jqlx.class.getName()+" as jq where jq.parentId is null order by jq.code");
		
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		List<Jqlx> list = this.dao.findAllByParams(Jqlx.class, hql.toString(), hqlMap);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Jqlx findJqlxByCode(String code) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select jq from "+Jqlx.class.getName()+" as jq where jq.code = :code ");
		
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		hqlMap.put("code", code);
		List<Jqlx> list = this.dao.findAllByParams(Jqlx.class, hql.toString(), hqlMap);
		if(!list.isEmpty()){
			return list.get(0);
		}else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jqlx> findAllJqlxs() {
		String xql = "select jq from "+Jqlx.class.getName()+" as jq" ;
		Map<String, Object> hqlMap = new HashMap<String, Object>(0) ;
		return this.dao.findAllByParams(Jqlx.class, xql.toString(), hqlMap);
	}

}
