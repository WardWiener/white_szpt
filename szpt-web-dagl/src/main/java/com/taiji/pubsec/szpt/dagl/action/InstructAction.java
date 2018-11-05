package com.taiji.pubsec.szpt.dagl.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdPushService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.bean.AlarmInfo;
import com.taiji.pubsec.szpt.dagl.action.bean.InstructionAndFeedbackBean;
import com.taiji.pubsec.szpt.dagl.bean.PersonBriefInfo;
import com.taiji.pubsec.szpt.dagl.bean.YrydCybercafeBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydHotelBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydLocusBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydPlaneGoOutBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydTrainGoOutBean;
import com.taiji.pubsec.szpt.dagl.model.InterestedPerson;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;
import com.taiji.pubsec.szpt.dagl.service.PersonSearchService;
import com.taiji.pubsec.szpt.dagl.service.YrydService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.WifiTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.instruction.action.InstructionAction;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.instruction.action.util.Constant;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.LoginInfoAction;
import com.taiji.pubsec.szpt.util.SzptFmtUtil;
import com.taiji.pubsec.szpt.util.Constant.DICT;

@Controller("instruct")
@Scope("prototype")
public class InstructAction extends LoginInfoAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructAction.class);

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	private InstructionBean instructionBean;
	
	private String id;
	
	@Resource
	private IInstructionService instructionService;
	
	@Resource
	private IUnitService unitService;
	
	/**
	 * 保存指令
	 * 
	 * @return
	 */
	public String saveInstruction() {
		Instruction in = new Instruction();
		in.setRelatedObjectId(instructionBean.getRelatedObjectId());
		in.setRelatedObjectType(instructionBean.getRelatedObjectType());
		in.setRelateObjectContent(instructionBean.getRelateObjectContent());
		in.setContent(instructionBean.getContent());
		in.setType(instructionBean.getType());
		in.setRequireFeedbackTime(new Date(instructionBean.getRequireFeedbackTime()));
		in.setIsNofityDepartmentLeader(instructionBean.getIsNofityDepartmentLeader());
		in.setTypeContent(instructionBean.getTypeContent());
		if(instructionBean.getSubs() != null && !instructionBean.getSubs().isEmpty()){
			for(InstructionReceiveSubjectBean irsb : instructionBean.getSubs()){
				InstructionReceiveSubject irs = transformInstructionReceiveSubject(irsb, in);
				if(irs == null){
					continue;
				}
				in.getInstructionReceiveSubjects().add(irs);
				CometdPushService cometdPushService = (CometdPushService)SpringContextUtil.getBean("defaultCometdPushService");
				Unit unit = unitService.findById(irsb.getReceiveSubjectId());
				LOGGER.debug("实战指令推送任务ForPDA执行");
				DefaultHintMsg msg = new DefaultHintMsg("{businessType:\"SzptInstruction\", businessData:\"\", existingTime:\"\"}") ;
				cometdPushService.pushHint(unit.getCode(), "/service/receive/hint", msg);
			}
		}
		if (StringUtils.isEmpty(instructionBean.getId())) {
			in.setCreateTime(new Date());
			in.setCreatePeopleId(this.findCurrentPerson().getId());
			in.setCreatePeopleDepartmentId(findCurrentOrganizationId());
			
			id = instructionService.createInstruction(in);
		}else{
			for (InstructionReceiveSubject sub : in.getInstructionReceiveSubjects()) {
				instructionService.addInstructionReceiveSubject(sub);
			}
			id = instructionBean.getId();
		}
		saveOperationRecord("下发指令", id);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--指令新增", null);
		return SUCCESS;
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
	 * @param content
	 *            内容
	 * @param targetId
	 *            id
	 */
	private void saveOperationRecord(String content, String targetId) {
		Instruction ins = instructionService.findInstructionById(targetId);
		OperationRecord record = new OperationRecord(ins);
		record.setOperateTime(new Date());
		record.setContent(content);
		record.setOperateUnit(findCurrentOrganizationId());
		record.setOperator(this.findCurrentPerson().getId());
		instructionService.saveOperationRecord(record);
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public InstructionBean getInstructionBean() {
		return instructionBean;
	}

	public void setInstructionBean(InstructionBean instructionBean) {
		this.instructionBean = instructionBean;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
