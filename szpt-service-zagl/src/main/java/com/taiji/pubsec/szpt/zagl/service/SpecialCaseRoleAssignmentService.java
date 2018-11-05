package com.taiji.pubsec.szpt.zagl.service;

import java.util.List;
import java.util.Map;

import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;


/**
 * 专案角色分配接口
 * @author dixiaofeng
 */
public interface SpecialCaseRoleAssignmentService {

	/**
	 * 给角色分配人员，不限专案，保存到LeaderRoleAssignment。已有的专案自动添加到专案角色分配表中
	 * @param roleId 专案角色id
	 * @param personId 人员id
	 * @return 成功返回true；失败返回false
	 */
	public boolean createAssignLeaderRole(String roleId, String personId);

	/**
	 * 给专案按角色分配人员，保存到SpecialCaseRoleAssignment。
	 * @param roleId    专案角色id
	 * @param personId    人员id
	 * @param caseId    专案id
	 * @return 成功返回true；失败返回false
	 */
	public boolean createSpecialCaseRole(String roleId, String personId, String caseId);
	
	/**
	 * 取消专案角色分配人员
	 * @param roleId 角色id
	 * @param personId 人员id
	 * @param caseId 专案id
	 * @return 成功返回true；失败返回false
	 */
	public boolean removeAssignedSpeccialCaseRole(String roleId, String personId, String caseId);
	
	/**
	 * 移除领导角色分配人员
	 * @param roleId 角色id
	 * @param personId 人员id
	 * @return 成功返回true；失败返回false
	 */
	public boolean removeAssignedLeaderRole(String roleId, String personId);
	
	/**
	 * 查询领导角色已分配的人员。
	 * @param roleId    角色id
	 * @param unitId 单位id
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回人员分页列表，没有列表位空
	 */
	public Pager<Person> findLeaderRolePersons(String roleId, String unitId, int pageNo, int pageSize);

	/**
	 * 根据条件查询专案角色可分配人员
	 * @param conditions 查询条件，包括：
	 * <br>organization:单位或部门id
	 * <br>name:姓名
	 * <br>sex:性别
	 * <br>caseId:专案id
	 * <br>ifSub: 是否查询下级单位，“0”表示否，“1”表示是
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回人员分页信息
	 */
	public Pager<Person> findPersonsByConditions(Map<String, Object> conditions, int pageNo, int pageSize);
	
	/**
	 * 根据条件查询专案角色已分配的人员
	 * @param conditions 查询条件，包括：
	 * <br>organization:单位或部门id
	 * <br>caseId:专案id
	 * <br>roleId 角色id
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回人员分页信息
	 */
	public Pager<SpecialCaseRoleAssignment> findAssignedPersonsByConditions(Map<String, Object> conditions, int pageNo, int pageSize);
	
	/**
	 * 查询领导角色可分配的人员
	 * @param conditions 查询条件，包括：
	 * <br>organization:单位或部门id
	 * <br>name:姓名
	 * <br>sex:性别
	 * <br>roleId:角色id
	 * <br>ifSub: 是否查询下级单位，“0”表示否，“1”表示是
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 返回人员分页信息
	 */
	public Pager<Person> findLeaderPersonsByConditions(Map<String, Object> conditions, int pageNo, int pageSize);
	
	/**
	 * 通过专案id查询专案组成员所在部门list
	 * @param caseId 专案id
	 * @return 返回部门信息list
	 */
	public List<Organization> findCaseUnitByCaseId(String caseId);
	
	/**
	 * 通过专案id查询该专案的角色和相应角色的人员
	 * @param caseId 专案id
	 * @return 返回角色和人员信息,map中的key是角色，对应的value是人员list
	 */
	public Map<SpecialCaseRole, List<Person>> findRolesAndPersonsByCaseId(String caseId);
	
}