package com.taiji.pubsec.szpt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.common.model.Country;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.SzptUnit;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service
public class SzptUnitCommonServiceImpl implements SzptUnitCommonService{
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao ;

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderCell> findOrderCellByType(String type) {
		String xql = " select s from "+OrderCell.class.getName()+" s where 1=1 "
				+ " and s.type=:type order by s.code  asc " ;
		
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("type", type);
		
		List<OrderCell> list = this.dao.findAllByParams(OrderCell.class, xql, xqlMap) ;
		return list;
	}
	
	/**
	 * 根据编码查询单位
	 * @param code 派出所编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OrderCell findSzptUnitByCode(String code) {
		Map<String, Object> xql = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select s from OrderCell as s where 1 = 1");
		if(ParamMapUtil.isNotBlank(code)){
			hql.append(" and s.code = :code");
			xql.put("code", code);
		}
		List<OrderCell> list = this.dao.findAllByParams(OrderCell.class, hql.toString(), xql);
		
		if(ParamMapUtil.isNotBlank(list)){
			return list.get(0);
		}else{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderCell> findOrderCellByparentId(String parentId) {
		Map<String, Object> xql = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select s from OrderCell as s where 1 = 1");
		if(ParamMapUtil.isNotBlank(parentId)){
			hql.append(" and s.parentId = :parentId order by s.code asc ");
			xql.put("parentId", parentId);
		}
		List<OrderCell> list = this.dao.findAllByParams(OrderCell.class, hql.toString(), xql);
		
		return list;
	}
	
	@Override
	public List<OrderCell> findOrderCellByCode(String code) {
		List<OrderCell> list= new ArrayList<OrderCell>();
		if(ParamMapUtil.isNotBlank(code)){
			OrderCell orderCell=this.findSzptUnitByCode(code);
			if(orderCell != null){
				list=this.findOrderCellByparentId(orderCell.getId());
			}
		}
		return list;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<SzptUnit> findUnitByType(String type) {
		String xql = " select s from "+SzptUnit.class.getName()+" s where 1=1"
				+ " and s.type=:type" ;
		
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("type", type);
		
		List<SzptUnit> list = this.dao.findAllByParams(SzptUnit.class, xql, xqlMap) ;
		return list;
	}
	
	/**
	 * 查询所有派出所
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SzptUnit> findAllSzptUnit() {
		return (List<SzptUnit>)this.dao.findAll(SzptUnit.class);
	}

	@SuppressWarnings("unchecked")
	public SzptUnit findSzptUnitById(String id){
		return (SzptUnit)this.dao.findById(SzptUnit.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> findCountryByUnitCode(String code) {
		Map<String, Object> xql = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select c from Country as c where 1 = 1");
		if(ParamMapUtil.isNotBlank(code)){
			hql.append(" and c.pcsCode = :code");
			xql.put("code", code);
		}
		return this.dao.findAllByParams(Country.class, hql.toString(), xql);
	}


}
