package com.taiji.pubsec.szpt.httpinterface.action;

import java.util.*;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.placemonitor.service.IPlaceStatisticsService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.szpt.httpinterface.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.httpinterface.action.bean.InstructionWsBean;
import com.taiji.pubsec.szpt.instruction.action.util.Constant;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.util.PageCommonAction;

@Controller("instructionWSAction")
@Scope("prototype")
public class InstructionWSAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructionWSAction.class);
	
	private String id;

	private Map<String, Object> resultMap = new HashMap<String, Object>() ;

	@Resource
	private IPlaceStatisticsService placeStatisticsSerivice;
	
	@Resource
	private IUnitService unitService;

	@Resource
	private IPersonService personService;
	
	@Resource
	private IInstructionService instructionService;

	/**
	 * wifi网络围栏统计
	 * @return 统计结果列表。StatisticsInfoTwoValue中name为派出所名称  value为采集数量 value_two为五色人员数量
	 */
	@SuppressWarnings("unchecked")
	public String wifiStatistics() {
		Date startDay = DateFmtUtil.longToDate(Long.valueOf(request.getParameter("startTime"))) ;
		Date endDay = DateFmtUtil.longToDate(Long.valueOf(request.getParameter("endTime"))) ;
		List<String> pcsCodes = new ArrayList<>() ;
		pcsCodes.add(request.getParameter("pcsCode"));
		resultMap.put("list", placeStatisticsSerivice.wifiStatistics(startDay, endDay, pcsCodes)) ;
		return SUCCESS;
	}
	
	/**
	 * 案件管理-保存指令
	 * 
	 * @return
	 */
	public String saveInstruction() {
	
		String jsonStr = super.read() ;
		InstructionWsBean instructionWsBean = new InstructionWsBean();
		try {
			instructionWsBean = getMapper().readValue(jsonStr, InstructionWsBean.class) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Person person = personService.findPersonByCode(instructionWsBean.getCreatePeopleCode());
		
		Unit unit = unitService.findUnitByCode(instructionWsBean.getCreatePeopleDepartmentCode());
		
		Instruction in = new Instruction();
		
		in.setRelatedObjectId(instructionWsBean.getRelatedObjectId());
		in.setRelatedObjectType(instructionWsBean.getRelatedObjectType());
		in.setRelateObjectContent(instructionWsBean.getRelateObjectContent());
		in.setContent(instructionWsBean.getContent());
		in.setType(instructionWsBean.getType());
		in.setRequireFeedbackTime(new Date(instructionWsBean.getRequireFeedbackTime()));
		in.setIsNofityDepartmentLeader(instructionWsBean.getIsNofityDepartmentLeader());
		in.setTypeContent(instructionWsBean.getTypeContent());
		if(instructionWsBean.getSubs() != null && !instructionWsBean.getSubs().isEmpty()){
			for(InstructionReceiveSubjectBean irsb : instructionWsBean.getSubs()){
				InstructionReceiveSubject irs = transformInstructionReceiveSubject(irsb, in);
				if(irs == null){
					continue;
				}
				in.getInstructionReceiveSubjects().add(irs);
			}
		}
		if (StringUtils.isEmpty(instructionWsBean.getId())) {
			in.setCreateTime(new Date());
			in.setCreatePeopleId(person.getId());
			in.setCreatePeopleDepartmentId(unit.getId());
			
			id = instructionService.createInstruction(in);
		}else{
			for (InstructionReceiveSubject sub : in.getInstructionReceiveSubjects()) {
				instructionService.addInstructionReceiveSubject(sub);
			}
			id = instructionWsBean.getId();
		}
		
		saveOperationRecord("下发指令", id, person, unit);
		
		return SUCCESS;
	}

	/**
	 * 实战平台-保存指令
	 * @return
	 */
	public String szptSaveInstruction() {
	
		String jsonStr = super.read() ;
		InstructionWsBean instructionWsBean = new InstructionWsBean();
		try {
			instructionWsBean = getMapper().readValue(jsonStr, InstructionWsBean.class) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Instruction in = new Instruction();
		
		in.setRelatedObjectId(instructionWsBean.getRelatedObjectId());
		in.setRelatedObjectType(instructionWsBean.getRelatedObjectType());
		in.setRelateObjectContent(instructionWsBean.getRelateObjectContent());
		in.setContent(instructionWsBean.getContent());
		in.setType(instructionWsBean.getType());
		in.setRequireFeedbackTime(new Date(instructionWsBean.getRequireFeedbackTime()));
		in.setIsNofityDepartmentLeader(instructionWsBean.getIsNofityDepartmentLeader());
		in.setTypeContent(instructionWsBean.getTypeContent());
		if(instructionWsBean.getSubs() != null && !instructionWsBean.getSubs().isEmpty()){
			for(InstructionReceiveSubjectBean irsb : instructionWsBean.getSubs()){
				InstructionReceiveSubject irs = transformInstructionReceiveSubject(irsb, in);
				if(irs == null){
					continue;
				}
				in.getInstructionReceiveSubjects().add(irs);
			}
		}
		if (StringUtils.isEmpty(instructionWsBean.getId())) {
			in.setCreateTime(new Date());
			in.setCreatePeopleId(findCurrentPersonId());
			in.setCreatePeopleDepartmentId(findCurrentOrganizationId());
			
			id = instructionService.createInstruction(in);
		}else{
			for (InstructionReceiveSubject sub : in.getInstructionReceiveSubjects()) {
				instructionService.addInstructionReceiveSubject(sub);
			}
			id = instructionWsBean.getId();
		}
		return SUCCESS;
	}
	
	private ObjectMapper getMapper(){
		return new ObjectMapper();
	}
	
	/**
	 * 指令接收主体Bean转Model
	 * 
	 * @param irsb 指令接收主体Bean
	 * @param in 指令Model
	 * @return irs 指令接收主体Model
	 */
	public InstructionReceiveSubject transformInstructionReceiveSubject(InstructionReceiveSubjectBean irsb, Instruction in){
		if(irsb == null){
			return null;
		}
		InstructionReceiveSubject irs = new InstructionReceiveSubject();
		irs.setId(StringUtil.isBlank(irsb.getId()) || "null".equals(irsb.getId())?null:irsb.getId());
		irs.setReceiveSubjectType(irsb.getReceiveSubjectType());
		irs.setReceiveSubjectId(irsb.getReceiveSubjectId());
		irs.setReceiveTime(new Date());
		irs.setStatus(Constant.ZLZT_DQS);
		irs.setInstruction(in);
		return irs;
	}
	
	/**
	 * 查看当前登录人id
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String findCurrentPersonId() {
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		Map<String, String> mPerson = new HashMap<String, String>(0);
		if (userMap.get("person") != null) {
			mPerson = (Map<String, String>) userMap.get("person");
		}
		return mPerson.get("id");
	}
	

	/**
	 * 查看当前登录单位id
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String findCurrentOrganizationId() {
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		Map<String, String> mOrg = new HashMap<String, String>(0);
		if (userMap.get("org") != null) {
			mOrg = (Map<String, String>) userMap.get("org");
		}
		return mOrg.get("id");
	}


	/**
	 * 保存操作记录
	 * 
	 * @param content  内容
	 *           
	 * @param targetId 指令id
	 *            
	 */
	private void saveOperationRecord(String content, String targetId, Person person, Unit unit) {
		Instruction ins = instructionService.findInstructionById(targetId);
		OperationRecord record = new OperationRecord(ins);
		record.setOperateTime(new Date());
		record.setContent(content);
		record.setOperateUnit(unit.getName());
		record.setOperator(person.getName());
		instructionService.saveOperationRecord(record);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}
