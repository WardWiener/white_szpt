package com.taiji.pubsec.szpt.service;


import java.util.List;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.common.model.CommunityNeighbor;

/**
 * 社区
 * @author sunjd
 *
 */
public interface ICommunityService {
	
	/**
	 * 创建社区
	 * @param community	社区model
	 */
	public void createCommunity(Community community);
	
	/**
	 * 删除社区
	 * @param communityId	社区id
	 */
	public void deleteCommunity(String communityId);
	
	/**
	 * 更新社区
	 * @param community	社区model
	 */
	public void updateCommunity(Community community);
	
	/**
	 * 根据id查找社区
	 * @param communityId
	 * @return 社区实体
	 */
	public Community findCommunityById(String communityId);
	
	/**
	 * 根据社区名称（模糊查询）、单位id分页查询社区
	 * @param name	社区名称
	 * @param unitId	单位id
	 * @param pageNo	页数
	 * @param pageSize	条数
	 * @return	满足条件的社区分页信息
	 */
	public Pager<Community> findAllCommunity(String name,String unitId,int pageNo, int pageSize);
	
	/**
	 * 根据社区名称查询社区实体
	 * @param name	社区名称
	 * @return	社区实体
	 */
	public Community findCommunityByName(String name);
	
	/**
	 * 根据社区编码查询社区
	 * @param code	社区编码
	 * @return	社区实体
	 */
	public Community findCommunityByCode(String code);
	
	/**
	 * 查询所有社区  返回List
	 * @return	所有社区实体List	没有返回null
	 */
	public List<Community> findAllCommunityList();
	
	/**
	 * 根据单位Id查询社区
	 * @param unitId	单位id
	 * @return	符合条件的社区实体List	没有返回null
	 */
	public List<Community> findCommunityByUnitId(String unitId);
	
	/**
	 * 根据主社区id查询相邻社区
	 * @param communityId 主社区id
	 * @return	相邻社区list
	 */
	public List<CommunityNeighbor> findCommunityNeighborByCommunityId(String communityId);
	
	/**
	 * 保存社区相邻关系
	 * @param communityNeighbor	相邻关系model
	 */
	public void saveCommunityNeighbor(CommunityNeighbor communityNeighbor);
	
	/**
	 * 判断两个村居是否相邻。
	 * @param fromCommunityId 第一个村居id
	 * @param toCommunityId 第二个村居id
	 * @return 相邻返回true，不相邻返回false。
	 */
	public boolean isNeighbourhood(String fromCommunityId, String toCommunityId);
	
}

