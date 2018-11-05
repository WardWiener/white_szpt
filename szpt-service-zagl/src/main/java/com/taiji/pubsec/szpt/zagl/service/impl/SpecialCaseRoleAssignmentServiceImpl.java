package com.taiji.pubsec.szpt.zagl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.zagl.model.LeaderRoleAssignment;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleAssignmentService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseService;

@Service("specialCaseRoleAssignmentService")
public class SpecialCaseRoleAssignmentServiceImpl implements SpecialCaseRoleAssignmentService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpecialCaseRoleAssignmentServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao ;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private SpecialCaseRoleService  specialCaseRoleService;
	
	@Resource
	private SpecialCaseService specialCaseService;
	
	@Resource
	private IOrganizationService organizationService;
	
	@Resource
	private IUnitService unitService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean createAssignLeaderRole(String roleId, String personId) {
		try {
			LeaderRoleAssignment assignment = new LeaderRoleAssignment();
			assignment.setPerson(personService.findById(personId));
			assignment.setSpecialCaseRole(specialCaseRoleService.findById(roleId));
			dao.save(assignment);
			
			List<SpecialCase> cases = dao.findAll(SpecialCase.class);
			for(SpecialCase specialCase: cases){
				String xql = "select a from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.person.id = ? and a.role.id = ?";
				SpecialCaseRoleAssignment roleAssignment = (SpecialCaseRoleAssignment) dao.findByParams(SpecialCaseRoleAssignment.class, xql, new Object[]{specialCase.getId(), personId, roleId});
				if(roleAssignment == null){
					this.createSpecialCaseRole(roleId, personId, specialCase.getId());
				}
			}
		} catch (Exception e) {
			LOGGER.debug("新增角色分配人员失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean createSpecialCaseRole(String roleId, String personId, String caseId) {
		try {
			SpecialCaseRoleAssignment roleAssignment = new SpecialCaseRoleAssignment();
			roleAssignment.setPerson(personService.findById(personId));
			roleAssignment.setRole(specialCaseRoleService.findById(roleId));
			roleAssignment.setSpecialCase(specialCaseService.findSpecialCaseById(caseId));
			dao.save(roleAssignment);
		} catch (Exception e) {
			LOGGER.debug("专案按角色分配人员失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Person> findLeaderRolePersons(String roleId, String unitId, int pageNo, int pageSize) {
		String xql = "select a.person from LeaderRoleAssignment as a where a.specialCaseRole.id = ?";
		if(ParamMapUtil.isNotBlank(unitId) && !("null".equals(unitId))){
			xql += " and a.person.organization.id = ?";
			return dao.findByPage(Person.class, new Object[]{roleId, unitId}, xql, pageNo, pageSize);
		}else{
			return dao.findByPage(Person.class, new Object[]{roleId}, xql, pageNo, pageSize);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Person> findPersonsByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select p from Person as p where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xql.append(" and p.status = :status ");
		xqlMap.put("status", DICT.DICT_YES.getValue());
		if(ParamMapUtil.isNotBlank(conditions.get("name"))){
			xql.append(" and p.name like :name ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("name", "%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("name")) + "%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("sex"))){
			xql.append("and p.sex = :sex ");
			xqlMap.put("sex", conditions.get("sex"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("caseId"))){
			xql.append(" and p.id not in (select distinct a.person.id from SpecialCaseRoleAssignment as a where a.specialCase.id = :caseId ");
			xqlMap.put("caseId", conditions.get("caseId"));
			xql.append(") ");
		}
		if("1".equals(conditions.get("ifSub"))){
			List<Organization> subOrgList = dao.findAllByParams(Organization.class, "select o from Organization as o where o.superOrg.id = ?", new Object[]{conditions.get("organization")});
			List<String> ids = new ArrayList<String>();
			ids.add((String) conditions.get("organization"));
			for(Organization org: subOrgList){
				ids.add(org.getId());
			}
			xql.append(" and p.organization.id in (:organization) ");
			xqlMap.put("organization", ids);
		}else{
			if(ParamMapUtil.isNotBlank(conditions.get("organization"))){
				xql.append(" and p.organization.id = :organization ");
				xqlMap.put("organization", conditions.get("organization"));
			}
		}
		
		return dao.findByPage(Person.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCaseRoleAssignment> findAssignedPersonsByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select a from SpecialCaseRoleAssignment as a where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(conditions.get("organization"))){
			xql.append("and a.person.organization.id = :organization ");
			xqlMap.put("organization", conditions.get("organization"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("caseId"))){
			xql.append("and a.specialCase.id = :caseId ");
			xqlMap.put("caseId", conditions.get("caseId"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("roleId"))){
			xql.append("and a.role.id = :roleId ");
			xqlMap.put("roleId", conditions.get("roleId"));
		}
		xql.append(" order by a.role.code ");
		return dao.findByPage(SpecialCaseRoleAssignment.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAssignedSpeccialCaseRole(String roleId, String personId, String caseId) {
		try {
			String xql = "select a from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.person.id = ? and a.role.id = ?";
			SpecialCaseRoleAssignment assignment = (SpecialCaseRoleAssignment) dao.findByParams(SpecialCaseRoleAssignment.class, xql, new Object[]{caseId, personId, roleId});
			dao.delete(assignment);
		} catch (Exception e) {
			LOGGER.debug("取消专案角色分配失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAssignedLeaderRole(String roleId, String personId) {
		try {
			String xql = "select a from LeaderRoleAssignment as a where a.specialCaseRole.id = ? and a.person.id = ?";
			LeaderRoleAssignment assignment = (LeaderRoleAssignment) dao.findByParams(LeaderRoleAssignment.class, xql, new Object[]{roleId, personId});
			dao.delete(assignment);
			
			String xql1 = "select a from SpecialCaseRoleAssignment as a where a.person.id = ? and a.role.id = ?";
			List<SpecialCaseRoleAssignment> assignments = dao.findAllByParams(SpecialCaseRoleAssignment.class, xql1, new Object[]{assignment.getPerson().getId(), assignment.getSpecialCaseRole().getId()});
			for(SpecialCaseRoleAssignment roleAssignment: assignments){
				dao.delete(roleAssignment);
			}
		} catch (Exception e) {
			LOGGER.debug("移除领导角色分配失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Person> findLeaderPersonsByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select p from Person as p where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xql.append(" and p.status = :status ");
		xqlMap.put("status", DICT.DICT_YES.getValue());
		if(ParamMapUtil.isNotBlank(conditions.get("name"))){
			xql.append(" and p.name like :name ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("name", "%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("name")) + "%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("sex"))){
			xql.append("and p.sex = :sex ");
			xqlMap.put("sex", conditions.get("sex"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("roleId"))){
			xql.append(" and p.id not in (select distinct a.person.id from LeaderRoleAssignment as a where a.specialCaseRole.id = :roleId ");
			xqlMap.put("roleId", conditions.get("roleId"));
			xql.append(") ");
		}
		if("1".equals(conditions.get("ifSub"))){
			List<Organization> subOrgList = dao.findAllByParams(Organization.class, "select o from Organization as o where o.superOrg.id = ?", new Object[]{conditions.get("organization")});
			List<String> ids = new ArrayList<String>();
			ids.add((String) conditions.get("organization"));
			for(Organization org: subOrgList){
				ids.add(org.getId());
			}
			xql.append(" and p.organization.id in (:organization) ");
			xqlMap.put("organization", ids);
		}else{
			if(ParamMapUtil.isNotBlank(conditions.get("organization"))){
				xql.append(" and p.organization.id = :organization ");
				xqlMap.put("organization", conditions.get("organization"));
			}
		}
		
		return dao.findByPage(Person.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> findCaseUnitByCaseId(String caseId) {
		String xql = "select distinct a.person.organization from SpecialCaseRoleAssignment as a where a.specialCase.id = ?";
		return dao.findAllByParams(Organization.class, xql, new Object[]{caseId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<SpecialCaseRole, List<Person>> findRolesAndPersonsByCaseId(String caseId) {
		Map<SpecialCaseRole, List<Person>> result = new HashMap<SpecialCaseRole, List<Person>>();
		String xql = "select distinct a.role from SpecialCaseRoleAssignment as a where a.specialCase.id = ?";
		List<SpecialCaseRole> roles = dao.findAllByParams(SpecialCaseRole.class, xql, new Object[]{caseId});
		for(SpecialCaseRole role: roles){
			String xql1 = "select a.person from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.role.id = ?";
			List<Person> persons = dao.findAllByParams(Person.class, xql1, new Object[]{caseId, role.getId()});
			result.put(role, persons);
		}
		return result;
	}

	
}
