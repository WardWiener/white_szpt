package com.taiji.pubsec.szpt.zagl.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.generatenum.service.IGenerateNumService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zagl.model.CaseRelation;
import com.taiji.pubsec.szpt.zagl.model.LeaderRoleAssignment;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPerson;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPersonRelation;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessage;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessageStickRecord;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseReport;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseStickRecord;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleAssignmentService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseService;

/**
 * 专案维护--实现
 * @author liyanpu
 *
 */
@Service("specialCaseService")
public class SpecialCaseServiceImpl implements SpecialCaseService{

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao ;
	
	@Resource
	private IGenerateNumService generateNumService;
	
	@Resource
	private SpecialCaseRoleAssignmentService specialCaseRoleAssignmentService;
	
	@Resource
	private IPersonService personService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpecialCaseServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean createSpecialCase(SpecialCase specialCase) {
		try {
			this.dao.save(specialCase);
			
			List<LeaderRoleAssignment> assignments = dao.findAll(LeaderRoleAssignment.class);
			for(LeaderRoleAssignment assignment: assignments){
				String xql = "select a from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.person.id = ? and a.role.id = ?";
				SpecialCaseRoleAssignment roleAssignment = (SpecialCaseRoleAssignment) dao.findByParams(SpecialCaseRoleAssignment.class, xql, new Object[]{specialCase.getId(), assignment.getPerson().getId(), assignment.getSpecialCaseRole().getId()});
				if(roleAssignment == null){
					specialCaseRoleAssignmentService.createSpecialCaseRole(assignment.getSpecialCaseRole().getId(), assignment.getPerson().getId(), specialCase.getId());
				}
			}
		} catch (Exception e) {
			LOGGER.debug("添加专案失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCaseInvolvedPerson> findInvolvedPersonByCase(
			String caseId, int pageNo, int pageSize) {
		String xql = "select p from SpecialCaseInvolvedPerson as p where p.specialCase.id = ? order by p.createdTime desc";
		return this.dao.findByPage(SpecialCaseInvolvedPerson.class, new Object[]{caseId}, xql, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialCaseInvolvedPersonRelation> findInvolvedPersonRelationsByCase(
			String caseId) {
		String xql = "select r from SpecialCaseInvolvedPersonRelation as r where r.SpecialCase.id = ?";
		return this.dao.findAllByParams(SpecialCaseInvolvedPersonRelation.class, xql, new Object[]{caseId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCase> findSpecialCaseByConditions(Map<String,Object> conditions,
			String personId, int pageNo, int pageSize) {
	    StringBuilder hql =new StringBuilder("select distinct s from SpecialCase s");
	    StringBuilder xql = new StringBuilder("  where  1=1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		 //专案编号
		if(ParamMapUtil.isNotBlank(conditions.get("zabh"))){
			xql.append(" and s.code like :zabh");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("zabh","%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("zabh")) + "%");
		} 
		 //案件名称
		if(ParamMapUtil.isNotBlank(conditions.get("zamc"))){
			xql.append(" and s.name like :zamc");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("zamc","%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("zamc")) + "%");
		} 
		//简要案情
		if(ParamMapUtil.isNotBlank(conditions.get("jyaq"))){
			xql.append(" and s.content like :jyaq");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("jyaq","%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("jyaq")) + "%");
		}  
		//案件性质
		if(ParamMapUtil.isNotBlank(conditions.get("ajxz"))){
			xql.append(" and s.nature = :ajxz");
			xqlMap.put("ajxz",conditions.get("ajxz"));
		}  
		//涉及子案件
		if(ParamMapUtil.isNotBlank(conditions.get("sjzaj"))){
			String[] strArray = null;  
		     strArray = String.valueOf(conditions.get("sjzaj")).split(",");
		     List<String> list=new ArrayList<String>();
		     for(int i=0;i<strArray.length;i++){
		    	 list.add(strArray[i]);
		     }
			hql.append(", CaseRelation c");
			xql.append("  and s.id=c.specialCase.id and c.caseCode in ( :sjzaj )");
			xqlMap.put("sjzaj",list);
		} 
		//专案组成员
		if(ParamMapUtil.isNotBlank(conditions.get("zazcy")) || ParamMapUtil.isNotBlank(personId)){
			hql.append(",SpecialCaseRoleAssignment r ");
			xql.append("and r.specialCase.id=s.id ");
			if(ParamMapUtil.isNotBlank(conditions.get("zazcy"))){
				 String[] strArray = null;  
			     strArray = String.valueOf(conditions.get("zazcy")).split(",");
			     List<String> list=new ArrayList<String>();
			     for(int i=0;i<strArray.length;i++){
			    	 list.add(strArray[i]);
			     }
				xql.append(" and r.person.id in ( :zazcy )");
				xqlMap.put("zazcy",list);
			}
			if(ParamMapUtil.isNotBlank(personId)){
				xql.append("and s.id in (select distinct s1.id from SpecialCase s1, SpecialCaseRoleAssignment r1 where r1.specialCase.id=s1.id and r1.person.id = :personId )");
				xqlMap.put("personId",personId);
				//xql.append(") ");
			}
			
		} 
		if(ParamMapUtil.isNotBlank(personId)){
			xql.append(" order by r.isStick desc, s.createdTime desc ");
		}else{
			xql.append(" order by s.createdTime desc ");
		}
		String s=hql.append(xql).toString();
		return this.dao.findByPage(SpecialCase.class, hql.append(xql).toString(), xqlMap, pageNo,pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCase findSpecialCaseById(String id) {
		return (SpecialCase) this.dao.findById(SpecialCase.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateInvolvedPersonRelations(String caseId,
			List<SpecialCaseInvolvedPersonRelation> toUpdateRelations,
			List<String> toDelRelations) {
		try {
			for(SpecialCaseInvolvedPersonRelation relation: toUpdateRelations){
				if(relation.getId() == null){
					dao.save(relation);
				}else{
					dao.update(relation);
				}
			}
			for(String id: toDelRelations){
				dao.delete(SpecialCaseInvolvedPersonRelation.class, id);
			}
		} catch (Exception e) {
			LOGGER.debug("新增或更新或删除专案人员关系信息失败", e);
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateInvolvedPersons(String caseId,
			List<SpecialCaseInvolvedPerson> toUpdatedPersons,
			List<String> toDelPersons) {
		try {
			SpecialCase specialCase = this.findSpecialCaseById(caseId);
			for(SpecialCaseInvolvedPerson person: toUpdatedPersons){
				if(person.getId() == null){
					person.setSpecialCase(specialCase);
					dao.save(person);
				}else{
					person.setSpecialCase(specialCase);
					dao.update(person);
				}
			}
			for(String id: toDelPersons){
				dao.delete(SpecialCaseInvolvedPerson.class, id);
			}
		} catch (Exception e) {
			LOGGER.debug("新增或更新或删除人员信息失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSpecialCase(SpecialCase specialCase) {
		try {
			this.dao.update(specialCase);
		} catch (Exception e) {
			LOGGER.debug("更新专案失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(String specialCaseId) {
		try {
			SpecialCase specialCase = findSpecialCaseById(specialCaseId);
			//删除子案件表
			for(CaseRelation caseRelation: specialCase.getCaseRelation()){
				dao.delete(caseRelation);
			}
			//删除专案涉案人员关系
			for(SpecialCaseInvolvedPersonRelation relation: specialCase.getSpecialCaseInvolvedPersonRelations()){
				dao.delete(relation);
			}
			//删除专案涉案人员
			for(SpecialCaseInvolvedPerson person: specialCase.getSpecialCaseInvolvedPerson()){
				dao.delete(person);
			}
			//删除专案资料
			for(SpecialCaseMaterial material: specialCase.getSpecialCaseMaterial()){
				dao.delete(material);
			}
			//删除专案报告
			for(SpecialCaseReport report: specialCase.getSpecialCaseReport()){
				dao.delete(report);
			}
			//删除留言
			for(SpecialCaseMessage message: specialCase.getSpecialCaseMessage()){
				//删除留言置顶记录
				for(SpecialCaseMessageStickRecord stick: message.getSpecialCaseMessageStickRecords()){
					dao.delete(stick);
				}

				dao.delete(message);
			}
			//删除专案人员角色分配
			for(SpecialCaseRoleAssignment assignment: specialCase.getSpecialCaseRoleAssignment()){
				dao.delete(assignment);
			}
			//删除专案置顶记录
			for(SpecialCaseStickRecord stick: specialCase.getSpecialCaseStickRecords()){
				dao.delete(stick);
			}
			
			//删除专案
			this.dao.delete(specialCase);
		} catch (Exception e) {
			LOGGER.debug("删除专案失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean stickSpecialCase(String caseId, String stickPerson, Date stickTime) {
		try {
			SpecialCaseStickRecord record = new SpecialCaseStickRecord();
			SpecialCase specialCase = this.findSpecialCaseById(caseId);
			Person person = personService.findById(stickPerson);
			record.setSpecialCase(specialCase);
			record.setStickPerson(person);
			record.setStickTime(stickTime);
			dao.save(record);
			
			String xql = "select a from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.person.id = ?";
			SpecialCaseRoleAssignment assignment = (SpecialCaseRoleAssignment) dao.findByParams(SpecialCaseRoleAssignment.class, xql, new Object[]{caseId, stickPerson});
			assignment.setIsStick("1");
			dao.update(assignment);
		} catch (Exception e) {
			LOGGER.debug("专案置顶失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean unstickSpecialCase(String caseId, String personId) {
		try {
			String xql = "select r from SpecialCaseStickRecord as r where r.specialCase.id = ? and r.stickPerson.id = ?";
			SpecialCaseStickRecord record = (SpecialCaseStickRecord) dao.findByParams(SpecialCaseStickRecord.class, xql, new Object[]{caseId, personId});
			dao.delete(record);
			
			String xql1 = "select a from SpecialCaseRoleAssignment as a where a.specialCase.id = ? and a.person.id = ?";
			SpecialCaseRoleAssignment assignment = (SpecialCaseRoleAssignment) dao.findByParams(SpecialCaseRoleAssignment.class, xql1, new Object[]{caseId, personId});
			assignment.setIsStick("0");
			dao.update(assignment);
		} catch (Exception e) {
			LOGGER.debug("专案取消置顶失败", e);
			return false;
		}
		return true;
	}

	@Override
	public String acquireNum() {
		String code = "ZA";
		String num = generateNumService.acquireNum(code);
		int iNum = Integer.parseInt(num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String nowYear = sdf.format(new java.util.Date());
		return nowYear + String.format("%04d", iNum);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addSonCase(CaseRelation caseRelation) {
		dao.save(caseRelation);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteSonCase(String caseId, String sonCaseCode) {
		try{
			String xql = "select r from CaseRelation as r where r.specialCase.id = ? and r.caseCode = ?";
			CaseRelation relation = (CaseRelation) dao.findByParams(CaseRelation.class, xql, new Object[]{caseId, sonCaseCode});
			dao.delete(relation);
		}catch(Exception e){
			LOGGER.debug("子案件删除失败", e);
			return false;
		}
		return true;
	}
	
}
