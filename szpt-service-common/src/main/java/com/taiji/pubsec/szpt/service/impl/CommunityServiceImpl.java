package com.taiji.pubsec.szpt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.common.model.CommunityNeighbor;
import com.taiji.pubsec.szpt.service.ICommunityService;

/**
 * 社区
 * @author sunjd
 *
 */
@Service("communityService")
public class CommunityServiceImpl implements ICommunityService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public void createCommunity(Community community) {
		this.dao.save(community);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteCommunity(String communityId) {
		this.dao.delete(Community.class,communityId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateCommunity(Community community) {
		this.dao.update(community);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Community findCommunityById(String communityId) {
		return (Community)this.dao.findById(Community.class,communityId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Community> findAllCommunity(String name, String unitId,
			int pageNo, int pageSize) {
		String xql = "select c from Community as c where 1=1 ";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(StringUtils.isNotEmpty(name)){
		xql += " and c.name like :name";
		xql = SQLTool.SQLAddEscape(xql);
		xqlMap.put("name","%" + SQLTool.SQLSpecialChTranfer(name) + "%");
		}
		if (StringUtils.isNotEmpty(unitId)) {
			xqlMap.put("unitId", unitId);
			xql += " and c.unitId = :unitId";
		}
		xql += " order by c.updateTime";
//		return this.dao.findByPage(Community.class, xql, xqlMap, pageNo,
//				pageSize);
		return dao.findByPage(Community.class, xql, xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Community findCommunityByName(String name) {
		String xql = "select c from Community as c where c.name = :name";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("name", name);
		List<Community> list = this.dao.findAllByParams(Community.class, xql, xqlMap);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Community findCommunityByCode(String code) {
		String xql = "select c from Community as c where c.code = :code";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("code", code);
		List<Community> list = this.dao.findAllByParams(Community.class, xql, xqlMap);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Community> findAllCommunityList() {
		List<Community> communityList = new ArrayList<Community>();
		String xql = "select c from Community as c order by c.updateTime desc";
		communityList.addAll(this.dao.findAllByParams(Community.class,xql, new Object[]{}));
		return communityList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Community> findCommunityByUnitId(String unitId) {
		List<Community> communityList = new ArrayList<Community>();
		String xql = "select c from Community as c where c.unitId = :unitId";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("unitId", unitId);
		communityList.addAll(this.dao.findAllByParams(Community.class, xql, xqlMap));
		return communityList ; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommunityNeighbor> findCommunityNeighborByCommunityId(
			String communityId) {
		List<CommunityNeighbor> communityNeighborList = new ArrayList<CommunityNeighbor>();
		String xql = "select c from CommunityNeighbor as c where c.from = :communityId";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("communityId", communityId);
		communityNeighborList.addAll(this.dao.findAllByParams(CommunityNeighbor.class, xql, xqlMap));
		return communityNeighborList ; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveCommunityNeighbor(CommunityNeighbor communityNeighbor) {
		this.dao.save(communityNeighbor);
	}

	@Override
	public boolean isNeighbourhood(String fromCommunityId, String toCommunityId) {
		List<CommunityNeighbor> communityNeighborList = new ArrayList<CommunityNeighbor>() ;
		String xql = "select c from CommunityNeighbor as c where c.from = :fromId and c.to = :toId";
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		xqlMap.put("fromId", fromCommunityId);
		xqlMap.put("toId", toCommunityId);
		List<CommunityNeighbor> result = dao.findAllByParams(CommunityNeighbor.class, xql, xqlMap);
		if(result.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
	
}

