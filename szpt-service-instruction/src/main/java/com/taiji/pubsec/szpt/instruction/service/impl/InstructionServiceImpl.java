package com.taiji.pubsec.szpt.instruction.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.common.tools.aophandler.annotation.AopAnno;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectSign;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.instruction.service.aop.InstructionModelSaveHandler;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("instructionService")
public class InstructionServiceImpl implements IInstructionService {
	
	@SuppressWarnings("rawtypes")
	@Resource 
	private Dao dao;
	
	@Resource
	private IOperationRecordService operationRecordService;
	
	@SuppressWarnings("unchecked")
	@Override
	public InstructionReceiveSubjectFeedback findFeedbackById(String feedbackId){
		return (InstructionReceiveSubjectFeedback)this.dao.findById(InstructionReceiveSubjectFeedback.class, feedbackId);
	}

	//TODO 发送单位
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Instruction> findInstructionsByPage(Map<String, Object> paramMap, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select distinct i.instruction from InstructionReceiveSubject as i where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("content"))) {
			xql.append(" and i.instruction.content like :content");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("content", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("content")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("createTimeStart"))) {
			xql.append(" and i.instruction.createTime >= :createTimeStart");
			xqlMap.put("createTimeStart", paramMap.get("createTimeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("createTimeEnd"))) {
			xql.append(" and i.instruction.createTime <= :createTimeEnd");
			xqlMap.put("createTimeEnd", paramMap.get("createTimeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("type"))) {
			xql.append(" and i.instruction.type like :type");
			xqlMap.put("type", paramMap.get("type") + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("receiveUnitId"))) {
			xql.append(" and i.receiveSubjectId = :receiveUnitId and i.receiveSubjectType = :receiveSubjectType");
			xqlMap.put("receiveUnitId", paramMap.get("receiveUnitId"));
			xqlMap.put("receiveSubjectType", Unit.class.getName());
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("requireFeedbackTimeStart"))) {
			xql.append(" and i.instruction.requireFeedbackTime >= :requireFeedbackTimeStart");
			xqlMap.put("requireFeedbackTimeStart", paramMap.get("requireFeedbackTimeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("requireFeedbackTimeEnd"))) {
			xql.append(" and i.instruction.requireFeedbackTime <= :requireFeedbackTimeEnd");
			xqlMap.put("requireFeedbackTimeEnd", paramMap.get("requireFeedbackTimeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("loginUnitId"))) {
			xql.append(" and i.instruction.createPeopleDepartmentId = :loginUnitId");
			xqlMap.put("loginUnitId", paramMap.get("loginUnitId"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("relatedObjectId"))) {
			xql.append(" and i.instruction.relatedObjectId = :relatedObjectId");
			xqlMap.put("relatedObjectId", paramMap.get("relatedObjectId"));
		}
		xql.append(" order by i.receiveTime desc");
		return dao.findByPage(InstructionReceiveSubject.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	//TODO 发送单位
	@SuppressWarnings("unchecked")
	@Override
	public Pager<InstructionReceiveSubject> findInstructionsByPageOfReceiveDepartment(String content,
			Map<String, Object> paramMap, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select i from InstructionReceiveSubject as i where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(StringUtils.isNotBlank(content)) {
			xql.append(" and i.instruction.content like :content");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("content", "%" + SQLTool.SQLSpecialChTranfer(content) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("createTimeStart"))) {
			xql.append(" and i.instruction.createTime >= :createTimeStart");
			xqlMap.put("createTimeStart", paramMap.get("createTimeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("createTimeEnd"))) {
			xql.append(" and i.instruction.createTime <= :createTimeEnd");
			xqlMap.put("createTimeEnd", paramMap.get("createTimeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("status"))) {
			xql.append(" and i.status = :status");
			xqlMap.put("status", paramMap.get("status"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("type"))) {
			xql.append(" and i.instruction.type = :type");
			xqlMap.put("type", paramMap.get("type"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("loginUnitId"))) {
			xql.append(" and i.receiveSubjectId = :loginUnitId");
			xqlMap.put("loginUnitId", paramMap.get("loginUnitId"));
			xql.append(" and i.receiveSubjectType = :receiveSubjectType");
			xqlMap.put("receiveSubjectType", Unit.class.getName());
		}
		
		xql.append(" order by i.receiveTime desc");
		return dao.findByPage(InstructionReceiveSubject.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@AopAnno(mark=InstructionModelSaveHandler.MARK)
	@SuppressWarnings("unchecked")
	@Override
	public String createInstruction(Instruction instruction) {
		dao.save(instruction);
		for(InstructionReceiveSubject instructionReceiveSubject : instruction.getInstructionReceiveSubjects()) {
			instructionReceiveSubject.setInstruction(instruction);
			instructionReceiveSubject.setStatus(Instruction.ZLZT_DQS);
			dao.save(instructionReceiveSubject);
		}
		return instruction.getId();
	}

	@Override
	public String saveOperationRecord(OperationRecord operationRecord) {
		return operationRecordService.saveOperationRecord(operationRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Instruction findInstructionById(String instructionId) {
		return (Instruction) dao.findById(Instruction.class, instructionId);
	}

	@Override
	public List<OperationRecord> findOperationRecordByInstruction(String instructionId, List<String> unitIds) {
		return operationRecordService.findOperationRecordByTarget(instructionId, Instruction.class.getName(), unitIds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String feedbackInstruction(InstructionReceiveSubjectFeedback instructionReceiveSubjectFeedback,
			String InstructionReceiveSubjectId) {
		InstructionReceiveSubject instructionReceiveSubject = (InstructionReceiveSubject) dao.findById(InstructionReceiveSubject.class, InstructionReceiveSubjectId);
		instructionReceiveSubjectFeedback.setInstructionReceiveSubject(instructionReceiveSubject);
		dao.save(instructionReceiveSubjectFeedback);
		instructionReceiveSubject.setStatus(Instruction.ZLZT_YFK);
		dao.update(instructionReceiveSubject);
		return instructionReceiveSubjectFeedback.getId() ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void signInstruction(InstructionReceiveSubjectSign instructionReceiveSubjectSign,
			String InstructionReceiveSubjectId) {
		InstructionReceiveSubject instructionReceiveSubject = (InstructionReceiveSubject) dao.findById(InstructionReceiveSubject.class, InstructionReceiveSubjectId);
		instructionReceiveSubjectSign.setInstructionReceiveSubject(instructionReceiveSubject);
		dao.save(instructionReceiveSubjectSign);
		instructionReceiveSubject.setStatus(Instruction.ZLZT_YQS);
		dao.update(instructionReceiveSubject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addInstructionReceiveSubject(InstructionReceiveSubject instructionReceiveSubject) {
		dao.save(instructionReceiveSubject);
		return instructionReceiveSubject.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public InstructionReceiveSubject findInstructionReceiveSubjectById(String instructionReceiveSubjectId) {
		return (InstructionReceiveSubject) dao.findById(InstructionReceiveSubject.class, instructionReceiveSubjectId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Instruction> findNewInstructionByConditions(String accountName, Date queryTime, String flag) {
		StringBuilder xql = new StringBuilder("select distinct i from Instruction as i, InstructionReceiveSubject as s "
				+ "  left join s.instructionReceiveSubjectFeedbacks as rs, "
				+ " Account as a, Person as p where i.id = s.instruction.id and s.receiveSubjectId "
				+ " = p.organization.id and p.id = a.person.id and a.accountName = ? ");
		xql.append(" and i.type = ? ");
		if("0".equals(flag)){
			xql.append(" and rs.id is null ");
		}else if("1".equals(flag)){
			xql.append(" and rs.id is not null ");
		}
		if(queryTime != null){
			xql.append(" and i.createTime > ? ");
			xql.append(" order by i.createTime desc");
			return dao.findAllByParams(Instruction.class, xql.toString(), new Object[]{accountName, DICT.DICT_ZLLX_LDZL_RYPC.getValue(), queryTime});
		}
		xql.append(" order by i.createTime desc");
		return dao.findAllByParams(Instruction.class, xql.toString(), new Object[]{accountName, DICT.DICT_ZLLX_LDZL_RYPC.getValue()});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Boolean, List<Instruction>> findOldInstructionByConditions(String accountName, Date queryTime,
			int size, String flag) {
		Map<Boolean, List<Instruction>> map = new HashMap<Boolean, List<Instruction>>();
		List<Instruction> instructions = new ArrayList<Instruction>();
		List<Instruction> instructions1 = new ArrayList<Instruction>();
		Pager<Instruction> instructionPager = new Pager<Instruction>();
		Pager<Instruction> instructionPager1 = new Pager<Instruction>();
		StringBuilder xql = new StringBuilder("select distinct i from Instruction as i, InstructionReceiveSubject as s "
				+ "  left join s.instructionReceiveSubjectFeedbacks as rs, "
				+ " Account as a, Person as p where i.id = s.instruction.id and s.receiveSubjectId "
				+ " = p.organization.id and p.id = a.person.id and a.accountName = ? ");
		xql.append(" and i.type = ? ");
		if("0".equals(flag)){
			xql.append(" and rs.id is null ");
		}else if("1".equals(flag)){
			xql.append(" and rs.id is not null ");
		}
		if(queryTime != null){
			xql.append(" and i.createTime > ? ");
			xql.append(" order by i.createTime desc");
			instructionPager =  dao.findByPage(Instruction.class, new Object[]{accountName, DICT.DICT_ZLLX_LDZL_RYPC.getValue(), queryTime}, xql.toString(), 0, size);
			instructionPager1 = dao.findByPage(Instruction.class, new Object[]{accountName, DICT.DICT_ZLLX_LDZL_RYPC.getValue(), queryTime}, xql.toString(), 0, size + 1);
		}else{
			xql.append(" order by i.createTime desc");
			instructionPager =  dao.findByPage(Instruction.class, new Object[]{accountName, DICT.DICT_ZLLX_LDZL_RYPC.getValue()}, xql.toString(), 0, size);
			instructionPager1 =  dao.findByPage(Instruction.class, new Object[]{accountName, DICT.DICT_ZLLX_LDZL_RYPC.getValue()}, xql.toString(), 0, size + 1);
		}
		instructions = instructionPager.getPageList();
		instructions1 = instructionPager1.getPageList();
		if(instructions.size() < instructions1.size()){
			map.put(true, instructions);
		}else{
			map.put(false, instructions);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InstructionReceiveSubject findReceiveSubjectByInstructionIdAndUnitId(String instructionId, String unitId) {
		String xql = "select s from InstructionReceiveSubject as s where s.instruction.id = ? and s.receiveSubjectId = ?";
		return (InstructionReceiveSubject) dao.findByParams(InstructionReceiveSubject.class, xql, new Object[]{instructionId, unitId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructionReceiveSubjectFeedback> findFeedBacksByInstructionIdAndPersonId(String instructionId, String feedBackPersonId) {
		String xql = "select f from InstructionReceiveSubjectFeedback as f where f.instructionReceiveSubject.instruction.id = ? and f.feedbackPeopleId = ? order by f.feedbackTime desc";
		return dao.findAllByParams(InstructionReceiveSubjectFeedback.class, xql, new Object[]{instructionId, feedBackPersonId});
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findInstructionSignByInstructionIdAndUnitId(String instructionId, String unitId) {
		String xql = "select s from InstructionReceiveSubjectSign as s where s.instructionReceiveSubject.receiveSubjectId = ? and s.instructionReceiveSubject.instruction.id = ?";
		InstructionReceiveSubjectSign sign = (InstructionReceiveSubjectSign) dao.findByParams(InstructionReceiveSubjectSign.class, xql, new Object[]{unitId, instructionId});
		if(sign != null){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Instruction> findInstructionByTypeContent(String typeContent) {
		StringBuilder xql = new StringBuilder("select i from Instruction as i where i.typeContent like :typeContent ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		SQLTool.SQLAddEscape(xql);
		xqlMap.put("typeContent", "%" + SQLTool.SQLSpecialChTranfer(typeContent) + "%");
		return dao.findAllByParams(Instruction.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Instruction> findInstructionByRelatedObjectId(String relatedObjectId) {
		StringBuilder xql = new StringBuilder("select i from Instruction as i where i.relatedObjectId = :relatedObjectId order by i.createTime desc");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("relatedObjectId", relatedObjectId);
		return dao.findAllByParams(Instruction.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructionReceiveSubjectFeedback> findFeedBacksByInstructionRelatedObjectId(String relatedObjectId) {
		StringBuilder xql =new StringBuilder("select f from InstructionReceiveSubjectFeedback as f where f.instructionReceiveSubject.instruction.id in ( select i.id from Instruction as i where i.relatedObjectId = :relatedObjectId ) order by f.feedbackTime desc");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("relatedObjectId", relatedObjectId);
		return dao.findAllByParams(InstructionReceiveSubjectFeedback.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Instruction> findInstructionByRelatedObjectIdAndType(String relatedObjectId, String type) {
		StringBuilder xql = new StringBuilder("select i from Instruction as i where i.relatedObjectId = :relatedObjectId and i.type = :type order by i.createTime desc");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("relatedObjectId", relatedObjectId);
		xqlMap.put("type", type);
		return dao.findAllByParams(Instruction.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Instruction> findInstructionsByPersonIdByPage(String createPeopleId, int pageNo, int pageSize) {
		
		StringBuilder xql = new StringBuilder(" select i from Instruction as i where 1 = 1 ");
	
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		
		if(ParamMapUtil.isNotBlank(createPeopleId)) {
			xql.append(" and i.createPeopleId = :createPeopleId ");
			xqlMap.put("createPeopleId", createPeopleId);
		}
		
		xql.append(" order by i.createTime desc ");
		
		return dao.findByPage(Instruction.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<InstructionReceiveSubjectFeedback> findInstructionsByRelatedObjectIdByPage(String RelatedObjectId,
			int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select fk from InstructionReceiveSubjectFeedback as fk where 1 = 1 ");
		
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		
		if(ParamMapUtil.isNotBlank(RelatedObjectId)) {
			xql.append(" and fk.instructionReceiveSubject.instruction.relatedObjectId = :RelatedObjectId ");
			xqlMap.put("RelatedObjectId", RelatedObjectId);
		}
		
		xql.append(" order by fk.feedbackTime desc ");
		
		return dao.findByPage(InstructionReceiveSubjectFeedback.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

}
