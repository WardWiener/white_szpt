package com.taiji.pubsec.szpt.service;

import java.util.List;

import com.taiji.pubsec.szpt.common.model.Country;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.SzptUnit;

public interface SzptUnitCommonService {

	/**
	 * 根据type查询单位
	 * @param type 类型
	 * @return
	 */
	public List<OrderCell> findOrderCellByType(String type);

	/**
	 * 根据单位code查询下一级
	 * @param code 编码
	 * @return
	 */
	public List<OrderCell> findOrderCellByCode(String code);
	
	/**
	 * 根据父id查询单位
	 * @param parentId 父id
	 * @return
	 */
	public List<OrderCell> findOrderCellByparentId(String parentId);
	
	
	/**
	 * 按照类型查询单位
	 * @param type 单位的类型，SzptUnit有常量
	 * @return 单位的集合
	 */
	@Deprecated
	public List<SzptUnit> findUnitByType(String type) ;
	
	/**
	 * 查询所有单位
	 * @return
	 */
	@Deprecated
	public List<SzptUnit> findAllSzptUnit() ;
	
	/**
	 * 根据编码查询单位
	 * @param code 单位编码
	 * @return
	 */
	public OrderCell findSzptUnitByCode(String code);
	
	/**
	 * 根据id查询单位
	 * @param id
	 * @return
	 */
	@Deprecated
	public SzptUnit findSzptUnitById(String id);
	
	/**
	 * 根据单位code查询村区
	 * @param code 单位code
	 * @return
	 */
	@Deprecated
	public List<Country> findCountryByUnitCode(String code);
}
