package com.taiji.pubsec.szpt.dagl.service;

import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.dagl.model.InterestedPerson;

/**
 * 人员关注操作接口
 * @author dixiaofeng
 * @version 1.0
 * @created 20-二月-2017 15:11:42
 */
public interface PersonInterestedService {

	/**
	 * 取消关注人员。
	 * 成功返回true，失败返回false。
	 * 
	 * @param id    关注记录id
	 */
	public boolean updateCancelPerson(String id);
	
	/**
	 * 查看此人是否被关注。
	 * 已关注返回true，未关注返回false。
	 * 
	 * @param personId 关注人id, bgzPersonIdcard 查询人身份证号
	 */
	public InterestedPerson personIfOrNotInterested(String gzPersonId,String bgzPersonIdcard);

	/**
	 * 分页查询关注的人员信息。sql查询实现，需关联查询关注人员表、高危人员表、人员类别和刑事前科表。
	 * 
	 * @param conditions    查询条件值对
	 * @param pageNo    页号
	 * @param pageSize    每页数量
	 */
	public Pager<InterestedPerson> findPersonByConditions(Map<String,Object> conditions, int pageNo, int pageSize);

	
	
	/**
	 * 添加关注记录。
	 * 成功返回true；失败返回false。
	 * 
	 * @param interested
	 */
	public boolean save(InterestedPerson interested);

	/**
	 * 置顶关注人员。
	 * 成功返回true，失败返回false。
	 * 
	 * @param id    关注记录id
	 */
	public boolean updateStickPerson(String id);

	/**
	 * 取消置顶关注人员。
	 * 成功返回true，失败返回false。
	 * 
	 * @param id    关注记录id
	 */
	public boolean updateUnstickPerson(String id);

}
