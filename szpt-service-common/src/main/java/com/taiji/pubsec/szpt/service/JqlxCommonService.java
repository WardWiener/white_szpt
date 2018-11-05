package com.taiji.pubsec.szpt.service;

import java.util.List;

import com.taiji.pubsec.szpt.common.model.Jqlx;

public interface JqlxCommonService {

	/**
	 * 查询警情类型集合
	 * @param level 层级 
	 * @return Jqlx的集合
	 */
	public List<Jqlx> findJqlxsByLevel(Integer level) ;
	
	/**
	 * 查询警情类型集合
	 * @param parentCode 父jqlx的code
	 * @return Jqlx的集合
	 */
	public List<Jqlx> findJqlxsByParentCode(String parentCode) ;
	
	/**
	 * 查询警情类型集合
	 * @param parentCode jqlx上级code
	 * @return Jqlx的集合
	 */
	public List<Jqlx> findJqlxsByChildCode(String parentCode) ;
	
	/**
	 * 查询警情类型集合
	 * @param parentCode jqlx父id
	 * @return Jqlx的集合
	 */
	public List<Jqlx> findJqlxsByParentId(String parentId) ;
	
	/**
	 * 查询警情类型
	 * @param name 警情类型的名称
	 * @return Jqlx
	 */
	public Jqlx findJqlxByName(String name) ;
	
	/**
	 * 查询警情类型
	 * @param name 警情类型的id
	 * @return Jqlx
	 */
	public Jqlx findJqlxById(String id) ;
	
	/**
	 * 查询警情类型
	 * @param name 警情类型编码
	 * @return Jqlx
	 */
	public Jqlx findJqlxByCode(String code) ;
	
	/**
	 *查询警情类型集合
	 * @param
	 * @return Jqlx集合
	 */
	public List<Jqlx> findAllJqlxs() ;
}
