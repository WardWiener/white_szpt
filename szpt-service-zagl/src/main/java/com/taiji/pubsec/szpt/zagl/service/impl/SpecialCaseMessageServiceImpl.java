package com.taiji.pubsec.szpt.zagl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessage;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessageStickRecord;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMessageService;

import net.sf.json.JSONArray;

@Service("specialCaseMessageService")
public class SpecialCaseMessageServiceImpl implements SpecialCaseMessageService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private IPersonService personService;
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpecialCaseMessageServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addMessage(SpecialCaseMessage message) {
		try {
			dao.save(message);
		} catch (Exception e) {
			LOGGER.debug("添加留言失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteMessage(String mid) {
		try {
			dao.delete(SpecialCaseMessage.class, mid);
		} catch (Exception e) {
			LOGGER.debug("删除留言失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCaseMessage> findMessageByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select distinct m from SpecialCaseMessage as m, SpecialCaseRoleAssignment as a where m.createPerson.id = a.person.id and m.specialCaseMessageStickRecords.size = 0  ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		List<String> leaderRoleIds = new ArrayList<String>();
		leaderRoleIds.add(DICT.DICT_ROLE_CODE_BUREAULEADER.getValue());//领导角色id
		leaderRoleIds.add(DICT.DICT_ROLE_CODE_GROUPLEADER.getValue());//领导角色id
		leaderRoleIds.add(DICT.DICT_ROLE_CODE_PENDRAGON.getValue());//领导角色id
		xql.append(" and a.role.id not in (:roleId) ");
		xqlMap.put("roleId", leaderRoleIds);
		if(ParamMapUtil.isNotBlank(conditions.get("department"))){
			xql.append(" and m.createPerson.organization.id = :department ");
			xqlMap.put("department", conditions.get("department"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("createTimeStart"))){
			xql.append(" and m.createdTime >= :createTimeStart ");
			Date createTimeStart = DateFmtUtil.longToDate(Long.parseLong(conditions.get("createTimeStart").toString())) ;
			xqlMap.put("createTimeStart", createTimeStart); 
		}
		if(ParamMapUtil.isNotBlank(conditions.get("createTimeEnd"))){
			xql.append(" and m.createdTime < :createTimeEnd ");
			Date createTimeEnd = DateFmtUtil.longToDate(Long.parseLong(conditions.get("createTimeEnd").toString())) ;
			xqlMap.put("createTimeEnd", createTimeEnd);  
		}
		if(ParamMapUtil.isNotBlank(conditions.get("caseId"))){
			xql.append(" and m.specialCase.id = :caseId ");
			xqlMap.put("caseId", conditions.get("caseId"));
		}
		xql.append(" order by m.createdTime desc");
		
		return dao.findByPage(SpecialCaseMessage.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpecialCaseMessage> findMessgeByPerson(String personId, String caseId, int pageNo, int pageSize) {
		String xql = "select m from SpecialCaseMessage as m where m.createPerson.id = ? and m.specialCase.id = ? order by m.createdTime desc";
		return dao.findByPage(SpecialCaseMessage.class, new Object[]{personId, caseId}, xql, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialCaseMessageStickRecord> findStickyMessage(String personId, String caseId) {
		List<SpecialCaseMessageStickRecord> results = new ArrayList<SpecialCaseMessageStickRecord>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		List<String> leaderRoles = new ArrayList<String>();
		leaderRoles.add(DICT.DICT_ROLE_CODE_BUREAULEADER.getValue());//领导 角色id
		leaderRoles.add(DICT.DICT_ROLE_CODE_GROUPLEADER.getValue());//领导 角色id
		leaderRoles.add(DICT.DICT_ROLE_CODE_PENDRAGON.getValue());//领导 角色id
		StringBuilder xql = new StringBuilder("select m from SpecialCaseMessage as m, SpecialCaseRoleAssignment as a where m.createPerson.id = a.person.id ");
		xql.append(" and a.role.id in (:leaderRoles) ");
		conditions.put("leaderRoles", leaderRoles);
		xql.append("and a.specialCase.id = :caseId");
		conditions.put("caseId", caseId);
		List<SpecialCaseMessage> messages = dao.findAllByParams(SpecialCaseMessage.class, xql.toString(), conditions);
		for(SpecialCaseMessage message: messages){
			SpecialCaseMessageStickRecord record = new SpecialCaseMessageStickRecord();
			record.setMessage(message);
			results.add(record);
		}
		String xql1 = "select r from SpecialCaseMessageStickRecord as r, SpecialCaseMessage as m where r.message.id = m.id and m.specialCase.id = ? and r.stickPerson.id = ?";
		List<SpecialCaseMessageStickRecord> records = dao.findAllByParams(SpecialCaseMessageStickRecord.class, xql1, new Object[]{caseId, personId});
		results.addAll(records);
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean stickMessage(String messageId, String personId, Date stickTime) {
		try {
			SpecialCaseMessageStickRecord record = new SpecialCaseMessageStickRecord();
			record.setMessage(this.findById(messageId));
			record.setStickPerson(personService.findById(personId));
			record.setStickTime(stickTime);
			dao.save(record);
		} catch (Exception e) {
			LOGGER.debug("置顶留言失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean unstickMessage(String messageId, String personId) {
		try {
			String xql = "select r from SpecialCaseMessageStickRecord as r where r.message.id = ? and r.stickPerson.id = ?";
			SpecialCaseMessageStickRecord record = (SpecialCaseMessageStickRecord) dao.findByParams(SpecialCaseMessageStickRecord.class, xql, new Object[]{messageId, personId});
			dao.delete(record);
		} catch (Exception e) {
			LOGGER.debug("取消置顶留言失败", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SpecialCaseMessage findById(String messageId) {
		return (SpecialCaseMessage) dao.findById(SpecialCaseMessage.class, messageId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findStickRecordByPersonIdAndMessageId(String personId, String messageId) {
		String xql = "select r from SpecialCaseMessageStickRecord as r where r.message.id = ? and r.stickPerson.id = ?";
		SpecialCaseMessageStickRecord stickRecord = (SpecialCaseMessageStickRecord) dao.findByParams(SpecialCaseMessageStickRecord.class, xql, new Object[]{messageId, personId});
		if(stickRecord != null){
			return true;
		}else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<SpecialCaseMessage>> findMessageByCondition(Map<String , Object> conditions) {
		Map<String, List<SpecialCaseMessage>> result = new HashMap<String, List<SpecialCaseMessage>>();
		StringBuilder xql = new StringBuilder("select m from SpecialCaseMessage as m where 1 = 1 ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		if(ParamMapUtil.isNotBlank(conditions.get("createTimeStart"))){
			xql.append(" and m.createdTime >= :createTimeStart ");
			Date createTimeStart = DateFmtUtil.longToDate(Long.parseLong(conditions.get("createTimeStart").toString())) ;
			xqlMap.put("createTimeStart", createTimeStart); 
		}
		if(ParamMapUtil.isNotBlank(conditions.get("createTimeEnd"))){
			xql.append(" and m.createdTime < :createTimeEnd ");
			Date createTimeEnd = DateFmtUtil.longToDate(Long.parseLong(conditions.get("createTimeEnd").toString())) ;
			xqlMap.put("createTimeEnd", createTimeEnd);  
		}
		if(ParamMapUtil.isNotBlank(conditions.get("caseId"))){
			xql.append(" and m.specialCase.id = :caseId ");
			xqlMap.put("caseId", conditions.get("caseId"));
		}
		if(!(Boolean)conditions.get("flag")){
			if(ParamMapUtil.isNotBlank(conditions.get("departments"))){
				List<String> departments = JSONArray.fromObject(conditions.get("departments"));
				xql.append(" and m.createPerson.organization.id in ("+inParamFormatForQuery((String[])departments.toArray(new String[departments.size()]))+")");
			}
			xql.append(" order by m.createdTime desc");
			List<SpecialCaseMessage> messages = dao.findAllByParams(SpecialCaseMessage.class, xql.toString(), xqlMap);
			result.put("all", messages);
		}else{
			String hql;
			if(ParamMapUtil.isNotBlank(conditions.get("departments"))){
				List<String> departments = JSONArray.fromObject(conditions.get("departments"));
				for (String department : departments) {
					hql = xql.toString();
					hql += " and m.createPerson.organization.id = :department ";
					xqlMap.put("department", department);
					hql += " order by m.createdTime desc";
					List<SpecialCaseMessage> messages = dao.findAllByParams(SpecialCaseMessage.class, hql, xqlMap);
					result.put(department, messages);
				}
			}
		}

		return result;
	}
	
	 /**
		 * 组装查询语句中IN参数。
		 * 
		 * @param Object[] formatInParmArr  IN参数数组
		 * @param 
		 * @return 返回数据形式：'a1','a2','a3'
		 */
	private String inParamFormatForQuery(Object[] formatInParmArr) {
		String formatInParm = "' '";
		if(null != formatInParmArr && formatInParmArr.length > 0) {
			for(int i = 0; i < formatInParmArr.length; i++) {
				if(null != formatInParmArr[i] && !"".equals(formatInParmArr[i].toString().trim())) {
					if(i == 0) {
						formatInParm = "";
						formatInParm += "'" + formatInParmArr[i].toString() + "'";
					}else {
						formatInParm += ",'" + formatInParmArr[i].toString() + "'";
					}
				}
			}
		}
		return formatInParm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SpecialCaseMessage> findMessagesByRoleIdAndCaseId(String roleId, String caseId) {
		List<SpecialCaseMessage> messages = new ArrayList<SpecialCaseMessage>();
		String xql = "select m from SpecialCaseMessage as m, SpecialCaseRoleAssignment as a where m.createPerson.id = a.person.id and m.specialCase.id = a.specialCase.id and m.specialCase.id = ? and a.role.id = ?";
		messages = dao.findAllByParams(SpecialCaseMessage.class, xql, new Object[]{caseId, roleId});
		return messages;
	}
}
