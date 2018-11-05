package com.taiji.pubsec.szpt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.service.SzptCommonService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service
public class SzptCommonServiceImpl implements SzptCommonService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@Override
	public List<AlertThresholdInfo> findPcsFenbuAlertThresholdInfosByType(
			String type) {
		List<AlertThresholdInfo> list = new ArrayList<AlertThresholdInfo>();
		list.add(new AlertThresholdInfo("平桥派出所", "520199610000", 5, 8, 10, 15)) ;
		list.add(new AlertThresholdInfo("长江派出所", "520199650000", 6, 9, 20, 25)) ;
		list.add(new AlertThresholdInfo("黄河派出所", "520199600000", 7, 9, 20, 25)) ;
		list.add(new AlertThresholdInfo("金竹派出所", "520199620000", 8, 9, 20, 25)) ;
		list.add(new AlertThresholdInfo("三江派出所", "520199630000", 9, 9, 20, 25)) ;
		list.add(new AlertThresholdInfo("大兴派出所", "520199640000", 9, 9, 20, 25)) ;
		list.add(new AlertThresholdInfo("主格A01", "JA01", 9, 9, 20, 25)) ;
		return list;
	}

	/**
	 * 查询村区集合
	 */
	@SuppressWarnings("unchecked")
	public List<Community> findAllCommunity() {
		
		return (List<Community>)this.dao.findAll(Community.class);
	}
	

	/**
	 * 根据编码查询村区
	 * @param code 村区编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Community findCommunityByCode(String code) {
		Map<String, Object> xql = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select c from Community as c where 1 = 1");
		if(ParamMapUtil.isNotBlank(code)){
			hql.append(" and c.code = :code");
			xql.put("code", code);
		}
		List<Community> list = this.dao.findAllByParams(Community.class, hql.toString(), xql);
		if(ParamMapUtil.isNotBlank(list)){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Community findCommunityById(String id){
		return (Community)this.dao.findById(Community.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Community> findCommunityByPcsId(String id) {
		Map<String, Object> xql = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select c from Community as c where 1 = 1");
		if(ParamMapUtil.isNotBlank(id)){
			hql.append(" and c.unitId = :id");
			xql.put("id", id);
		}
		List<Community> list = this.dao.findAllByParams(Community.class, hql.toString(), xql);
		return list;
	}

	@Override
	public List<Community> findCommunityByPcsCode(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
