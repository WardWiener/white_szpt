package com.taiji.pubsec.szpt.instruction.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdPushService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectFeedbackBean;
import com.taiji.pubsec.szpt.instruction.action.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.instruction.action.util.Constant;
import com.taiji.pubsec.szpt.instruction.action.util.InsturctionCommonUtil;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectSign;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.bean.FileObjectBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;

import net.sf.json.JSONObject;

/**
 * 指令
 * 
 */
@Controller("instructionAction")
@Scope("prototype")
public class InstructionAction extends PageCommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructionAction.class);
	@Resource
	private IInstructionService instructionService;
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IUnitService unitService;
	@Resource
	private IPersonService personService;
	@Resource
	private IOrganizationService organizationService;
	@Resource
	private InsturctionCommonUtil insturctionCommonUtil;// 指令公用方法工具类
	@Resource(name="criminalSzptPersonService")
	private CriminalPersonService criminalPersonService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;// 附件接口
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	private List<FileObjectBean> fileObjectBeans = new ArrayList<FileObjectBean>();// 附件Bean

	private String id;
	private String ids;
	private String status;
	private Integer start;
	private Integer length;
	private String feedbackContent;
	private InstructionBean instructionBean;
	private InstructionReceiveSubjectBean receiveSubjectBean;
	private Pager<InstructionBean> instructionBeanPager;
	private Pager<InstructionReceiveSubjectBean> instructionReceiveSubjectBeanPager;
	private List<InstructionReceiveSubjectFeedbackBean> feedbackBeanList;
	private List<InstructionReceiveSubjectBean> receiveSubjectBeanList;
	private List<OperationRecordBean> operationRecordBeanList;
	private String otherFeedbackContent;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private String queryStr;

	/**
	 * 指令列表
	 * 
	 * @return
	 */
	public String queryInstructionList() {
		Map<String, Object> map = searchMap();
		Pager<Instruction> pager = instructionService.findInstructionsByPage(map, start / length, length);
		instructionBeanPager = new Pager<InstructionBean>();
		instructionBeanPager.setTotalNum(pager.getTotalNum());
		List<InstructionBean> list = new ArrayList<InstructionBean>();
		for (Instruction instruction : pager.getPageList()) {
			list.add(transformInstructionBean(instruction));
		}
		instructionBeanPager.setPageList(list);
		String temp = "content=" + map.get("content") + ",createTimeStart=" + String.valueOf(map.get("createTimeStart"))
				+ ",createTimeEnd=" + String.valueOf(map.get("createTimeEnd")) + ",type=" + map.get("type")
				+ ",receiveUnitId=" + map.get("receiveUnitId") + ",requireFeedbackTimeStart="
				+ String.valueOf(map.get("requireFeedbackTimeStart")) + ",requireFeedbackTimeEnd="
				+ String.valueOf(map.get("requireFeedbackTimeEnd")) + ",loginUnitId=" + map.get("loginUnitId");
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--指令列表", temp);
		return SUCCESS;
	}

	/**
	 * 根据主体id查询指令列表(分页)
	 * 
	 * @return
	 */
	public String queryInstructionPagerByBodyId() {
		Map<String, Object> mapCondition = new HashMap<String, Object>();
		mapCondition.put("relatedObjectId", queryStr);
		Pager<Instruction> pager = instructionService.findInstructionsByPage(mapCondition, this.getStart() / this.getLength(), this.getLength());
		List<InstructionBean> instructions = new ArrayList<InstructionBean>();
		if (pager == null || pager.getPageList().isEmpty()) {
			resultMap.put("instructions", instructions);
			resultMap.put("totalNum", 0);
			return SUCCESS;
		}

		for (Instruction in : pager.getPageList()) {
			InstructionBean inb = transformInstructionBean(in);
			if (inb == null) {
				continue;
			}
			instructions.add(inb);
		}
		resultMap.put("instructions", instructions);
		resultMap.put("totalNum", pager.getTotalNum());
		return SUCCESS;
	}
	
	/**
	 * 根据指令主体id查询所有带已推送单位的反馈
	 * 
	 * @return
	 */
	public String queryJudeResultFeedback(){
		List<InstructionReceiveSubjectFeedbackBean> irsfbs = queryAllFeedbackByBodyId(queryStr);
		for(InstructionReceiveSubjectFeedbackBean irsfb : irsfbs){
			List<Instruction> ins = instructionService.findInstructionByTypeContent(irsfb.getId());
			for(Instruction in : ins){
				irsfb.getResultSendUnits().addAll(instructionToResultSendUnits(in));
			}
		}
		resultMap.put("irsfbs", irsfbs);
		return SUCCESS;
	}
	
	
	/**
	 * 根据指令主体id查询指令类型为研判指令的所有带已推送单位的反馈
	 * 
	 * @return
	 */
	public String queryYanPanJudeResultFeedback(){
		List<InstructionReceiveSubjectFeedbackBean> irsfbs = queryAllFeedbackByBodyId(queryStr);
		for(InstructionReceiveSubjectFeedbackBean irsfb : irsfbs){
			List<Instruction> ins = instructionService.findInstructionByTypeContent(irsfb.getId());
			for(Instruction in : ins){
				DictionaryItem type = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.ZLLX,
						in.getType(), Constant.ENABLED);
				if(type.getName().equals("研判指令")){
					irsfb.getResultSendUnits().addAll(instructionToResultSendUnits(in));
				}
			}
		}
		resultMap.put("irsfbs", irsfbs);
		return SUCCESS;
	}
	
	/**
	 * 根据指令获取发送主体集合
	 * 
	 * @param in 指令对象
	 * @return
	 */
	private List<Map<String, String>> instructionToResultSendUnits(Instruction in){
		List<Map<String, String>> resultSendUnits = new ArrayList<Map<String, String>>();
		if(in == null || in.getInstructionReceiveSubjects() == null || in.getInstructionReceiveSubjects().isEmpty()){
			return resultSendUnits;
		}
		for(InstructionReceiveSubject irs : in.getInstructionReceiveSubjects()){
			Map<String, String> map = insturctionCommonUtil.queryInstructionReceiveBodyNameByBodyTypeAndId(irs.getReceiveSubjectType(), irs.getReceiveSubjectId());
			if(map == null){
				continue;
			}
			resultSendUnits.add(map);
		}
		return resultSendUnits;
	}

	/**
	 * 根据主体id查询所有反馈信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryFeedbackByInstructionBodyId() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String alarm = (String)rqst.get("jqId");
		resultMap.put("irsfbs", queryAllFeedbackByBodyId(alarm));
		return SUCCESS;
	}
	
	/**
	 * 根据主体id查询所有的指令反馈
	 * 
	 * @param bodyId 主体id
	 * @return
	 */
	private List<InstructionReceiveSubjectFeedbackBean> queryAllFeedbackByBodyId(String bodyId){
		Map<String, Object> mapCondition = new HashMap<String, Object>();
		mapCondition.put("relatedObjectId", bodyId);
		Pager<Instruction> pager = instructionService.findInstructionsByPage(mapCondition, 0, Integer.MAX_VALUE);
		List<InstructionReceiveSubjectFeedbackBean> irsfbs = new ArrayList<InstructionReceiveSubjectFeedbackBean>();
		if (pager == null || pager.getPageList().isEmpty()) {
			return irsfbs;
		}
		for (Instruction in : pager.getPageList()) {
			List<InstructionReceiveSubjectFeedbackBean> irsfbsNew = instructionToFeedbackBean(in);
			if(irsfbs == null){
				continue;
			}
			irsfbs.addAll(irsfbsNew);
		}
		return irsfbs;
	}
	
	/**
	 * 指令model转指令反馈Bean集合
	 * 
	 * @param in
	 * @return
	 */
	private List<InstructionReceiveSubjectFeedbackBean> instructionToFeedbackBean(Instruction in){
		if(in == null || in.getInstructionReceiveSubjects() == null || in.getInstructionReceiveSubjects().isEmpty()){
			return null;
		}
		List<InstructionReceiveSubjectFeedbackBean> irsfbs = new ArrayList<InstructionReceiveSubjectFeedbackBean>();
		for(InstructionReceiveSubject irs : in.getInstructionReceiveSubjects()){
			for(InstructionReceiveSubjectFeedback irsf : irs.getInstructionReceiveSubjectFeedbacks()){
				InstructionReceiveSubjectFeedbackBean irsfb = new InstructionReceiveSubjectFeedbackBean();
				irsfb.setId(irsf.getId());
				irsfb.setFeedbackContent(irsf.getFeedbackContent());
				irsfb.setFeedbackPeopleId(irsf.getFeedbackPeopleId());
				irsfb.setFeedbackPeopleName(irsf.getFeedbackPeopleName());
				irsfb.setRelateObjectType(irsf.getRelateObjectType());
				irsfb.setRelateObjectId(irsf.getRelateObjectId());
				irsfb.setOtherFeedbackContent(irsf.getOtherFeedbackContent());
				irsfb.setReceiveSubjectType(irs.getReceiveSubjectType());
				irsfb.setReceiveSubjectId(irs.getReceiveSubjectId());
				irsfb.setReceiveTime(irs.getReceiveTime()==null?null:irs.getReceiveTime().getTime());
				irsfb.setStatus(irs.getStatus());
				irsfb.setInstructionId(irs.getInstruction()==null?"":irs.getInstruction().getId());
				Map<String, String> map = insturctionCommonUtil.queryInstructionReceiveBodyNameByBodyTypeAndId(irs.getReceiveSubjectType(), irs.getReceiveSubjectId());
				irsfb.setReceiveSubjectName(map.get("name"));
				irsfbs.add(irsfb);
			}
		}
		return irsfbs;
	}

	/**
	 * 指令签收列表
	 * 
	 * @return
	 */
	public String queryInstructionReceiveList() {
		Map<String, Object> map = searchMap();
		Pager<InstructionReceiveSubject> pager = instructionService
				.findInstructionsByPageOfReceiveDepartment(instructionBean.getContent(), map, start / length, length);
		instructionReceiveSubjectBeanPager = new Pager<InstructionReceiveSubjectBean>();
		instructionReceiveSubjectBeanPager.setTotalNum(pager.getTotalNum());
		List<InstructionReceiveSubjectBean> list = new ArrayList<InstructionReceiveSubjectBean>();
		for (InstructionReceiveSubject sub : pager.getPageList()) {
			list.add(transformInstructionReceiveSubjectBean(sub));
		}
		instructionReceiveSubjectBeanPager.setPageList(list);
		String temp = "content=" + map.get("content") + ",createTimeStart=" + String.valueOf(map.get("createTimeStart"))
				+ ",createTimeEnd=" + String.valueOf(map.get("createTimeEnd")) + ",type=" + map.get("type")
				+ ",receiveUnitId=" + map.get("receiveUnitId") + ",requireFeedbackTimeStart="
				+ String.valueOf(map.get("requireFeedbackTimeStart")) + ",requireFeedbackTimeEnd="
				+ String.valueOf(map.get("requireFeedbackTimeEnd")) + ",loginUnitId=" + map.get("loginUnitId")
				+ ",pageNo=" + String.valueOf(start / length) + ",pageSize=" + String.valueOf(length);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--指令签收", temp);
		return SUCCESS;
	}

	
	
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
			in.setCreatePeopleId(findCurrentPersonId());
			in.setCreatePeopleDepartmentId(findCurrentOrganizationId());
			
			id = instructionService.createInstruction(in);
		}else{
			Instruction instruction = instructionService.findInstructionById(instructionBean.getId());
			in.setCreatePeopleDepartmentId(instruction.getCreatePeopleDepartmentId());
			in.setCreateTime(instruction.getCreateTime());
			for (InstructionReceiveSubject sub : in.getInstructionReceiveSubjects()) {
				sub.getInstruction().setId(instructionBean.getId());
				instructionService.addInstructionReceiveSubject(sub);
			}
			id = instructionBean.getId();
		}
		for(InstructionReceiveSubject irs : in.getInstructionReceiveSubjects()){
			LOGGER.debug("实战指令推送任务ForPC弹框");
			CometdPushService cometdPushService = (CometdPushService)SpringContextUtil.getBean("defaultCometdPushService");
			Organization receiveUnit = organizationService.findOrganizationById(irs.getReceiveSubjectId());
			Organization org = organizationService.findOrganizationById(in.getCreatePeopleDepartmentId());
			DefaultHintMsg msgForPC = new DefaultHintMsg("{businessType:\"AlertMessage\", context:\"" 
				+ in.getContent() + "\", time:\"" + (in.getCreateTime().getHours()) + ":" + in.getCreateTime().getMinutes() 
				+ "\", unit:\"" + org.getShortName() + "\", id:\"" + irs.getId() + "\", existingTime:\"60000\"}");
			cometdPushService.pushHint(receiveUnit.getCode() + "PC", "/service/receive/hint", msgForPC);
		}
		saveOperationRecord("下发指令", id);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--指令新增", null);
		
		for(FileObjectBean fob : fileObjectBeans){
			Attachment att = attachmentCustomizedService.findById(fob.getId());
			att.setTargetId(in.getId());
			att.setType(Instruction.class.getName());
			attachmentCustomizedService.save(att);
		}
		
		return SUCCESS;
		/*Instruction instruction = transformInstruction(instructionBean);
		List<InstructionReceiveSubject> list = new ArrayList<InstructionReceiveSubject>();
		if (!StringUtils.isEmpty(instructionBean.getUnitId())) {
			String[] idList = instructionBean.getUnitId().split(",");
			for (int i = 0; i < idList.length; i++) {
				Unit unit = unitService.findById(idList[i]);
				InstructionReceiveSubject sub = new InstructionReceiveSubject(unit);
				sub.setStatus(Constant.ZLZT_DQS);
				sub.setReceiveTime(new Date());
				sub.setInstruction(instruction);
				list.add(sub);
			}
		}
		if (StringUtils.isEmpty(instruction.getId())) {
			instruction.setCreateTime(new Date());
			instruction.setCreatePeopleId(findCurrentPersonId());
			instruction.setCreatePeopleDepartmentId(findCurrentOrganizationId());
			Set<InstructionReceiveSubject> set = new HashSet<InstructionReceiveSubject>();
			for (InstructionReceiveSubject subject : list) {
				set.add(subject);
			}
			instruction.setInstructionReceiveSubjects(set);
			id = instructionService.createInstruction(instruction);
		} else {
			for (InstructionReceiveSubject sub : list) {
				instructionService.addInstructionReceiveSubject(sub);
			}
			id = instructionBean.getId();
		}*/
	}
	
	/**
	 * 查看指令
	 * 
	 * @return
	 */
	public String findInstruction() {
		Instruction instruction = instructionService.findInstructionById(id);
		instructionBean = transformInstructionBean(instruction);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--查看指令", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 查看接收指令
	 * 
	 * @return
	 */
	public String findInstructionReceiveSubject() {
		InstructionReceiveSubject receiveSubject = instructionService.findInstructionReceiveSubjectById(id);
		receiveSubjectBean = transformInstructionReceiveSubjectBean(receiveSubject);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--查看接收指令", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 查看接收列表
	 * 
	 * @return
	 */
	public String findReceiveSubjectBeanList() {
		Instruction instruction = instructionService.findInstructionById(id);
		receiveSubjectBeanList = new ArrayList<InstructionReceiveSubjectBean>();
		for (InstructionReceiveSubject receive : instruction.getInstructionReceiveSubjects()) {
			InstructionReceiveSubjectBean receiveBean = transformInstructionReceiveSubjectBean(receive);
			Unit unit = unitService.findById(receive.getReceiveSubjectId());
			if (unit != null) {
				receiveBean.setReceiveSubjectName(unit.getShortName());
			}
			List<InstructionReceiveSubjectFeedbackBean> feedbackList = new ArrayList<InstructionReceiveSubjectFeedbackBean>();
			for (InstructionReceiveSubjectFeedback feedback : receive.getInstructionReceiveSubjectFeedbacks()) {
				InstructionReceiveSubjectFeedbackBean feedbackBean = new InstructionReceiveSubjectFeedbackBean();
				BeanUtils.copyProperties(feedback, feedbackBean);
				feedbackBean.setFeedbackTime(DateFmtUtil.dateToLong(feedback.getFeedbackTime()));
				feedbackList.add(feedbackBean);
			}
			receiveBean.setFeedbackBeanList(feedbackList);
			receiveSubjectBeanList.add(receiveBean);
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--查看接受列表", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 查看反馈列表
	 * 
	 * @return
	 */
	public String findFeedbackBeanList() {
		InstructionReceiveSubject receiveSubject = instructionService.findInstructionReceiveSubjectById(id);
		feedbackBeanList = new ArrayList<InstructionReceiveSubjectFeedbackBean>();
		for (InstructionReceiveSubjectFeedback feedback : receiveSubject.getInstructionReceiveSubjectFeedbacks()) {
			InstructionReceiveSubjectFeedbackBean feedbackBean = new InstructionReceiveSubjectFeedbackBean();
			BeanUtils.copyProperties(feedback, feedbackBean);
			feedbackBean.setFeedbackTime(DateFmtUtil.dateToLong(feedback.getFeedbackTime()));
			feedbackBeanList.add(feedbackBean);
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--查看反馈列表", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 签收指令
	 * 
	 * @return
	 */
	public String signInstruction() {
		InstructionReceiveSubjectSign sign = new InstructionReceiveSubjectSign();
		sign.setSignPeopleId(findCurrentPersonId());
		Person person = personService.findById(findCurrentPersonId());
		if (person != null) {
			sign.setSignPeopleName(person.getName());
		}
		sign.setSignTime(new Date());
		instructionService.signInstruction(sign, id);
		InstructionReceiveSubject sub = instructionService.findInstructionReceiveSubjectById(id);
		if (sub != null && sub.getInstruction() != null) {
			saveOperationRecord("签收指令", sub.getInstruction().getId());
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--签收指令", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 反馈指令
	 * 
	 * @return
	 */
	public String feedbackInstruction() {
		InstructionReceiveSubjectFeedback feedback = new InstructionReceiveSubjectFeedback();
		feedback.setFeedbackContent(feedbackContent);
		feedback.setFeedbackPeopleId(findCurrentPersonId());
		Person person = personService.findById(findCurrentPersonId());
		if (person != null) {
			feedback.setFeedbackPeopleName(person.getName());
		}
		if (!StringUtils.isEmpty(otherFeedbackContent)) {
			feedback.setOtherFeedbackContent(otherFeedbackContent);
		}
		feedback.setFeedbackTime(new Date());
		instructionService.feedbackInstruction(feedback, id);
		InstructionReceiveSubject sub = instructionService.findInstructionReceiveSubjectById(id);
		if (sub != null && sub.getInstruction() != null) {
			saveOperationRecord("反馈指令", sub.getInstruction().getId());
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--指令反馈", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 根据接收主体id查看操作记录列表
	 * 
	 * @return
	 */
	public String findOperationRecordBeanListByReceiveSubjectId() {
		InstructionReceiveSubject sub = instructionService.findInstructionReceiveSubjectById(id);
		if (sub != null && sub.getInstruction() != null) {
			findOperationRecordBeanList(sub.getInstruction().getId(),
					Arrays.asList(findCurrentOrganizationId(), sub.getInstruction().getCreatePeopleDepartmentId()));
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--查看指令操作记录", "id=" + id);
		return SUCCESS;
	}

	/**
	 * 根据指令id查看操作记录列表
	 * 
	 * @return
	 */
	public String findOperationRecordBeanListByInstructionId() {
		findOperationRecordBeanList(id, null);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY, StandardLogRecord.OPERATERESULT_SUCCESS, null,
				"指令管理--查看操作记录", "id=" + id);
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

	/**
	 * 查看操作记录列表
	 * 
	 * @param instructionId
	 *            指令id
	 */
	private void findOperationRecordBeanList(String instructionId, List<String> unitIds) {
		List<OperationRecord> operationRecordList = instructionService.findOperationRecordByInstruction(instructionId,
				unitIds);
		operationRecordBeanList = new ArrayList<OperationRecordBean>();
		for (OperationRecord record : operationRecordList) {
			OperationRecordBean recordBean = transformOperationRecordBean(record);
			operationRecordBeanList.add(recordBean);
		}
	}

	/**
	 * 获取搜索条件
	 * 
	 * @return
	 */
	private Map<String, Object> searchMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("content", instructionBean.getContent());
		map.put("type", instructionBean.getType());
		map.put("receiveUnitId", instructionBean.getId());
		if (instructionBean.getRequireFeedbackTimeStart() != null) {
			map.put("requireFeedbackTimeStart", DateFmtUtil.longToDate(instructionBean.getRequireFeedbackTimeStart()));
		}
		if (instructionBean.getRequireFeedbackTimeEnd() != null) {
			map.put("requireFeedbackTimeEnd", DateFmtUtil.longToDate(instructionBean.getRequireFeedbackTimeEnd()));
		}
		if (instructionBean.getCreateTimeStart() != null) {
			map.put("createTimeStart", DateFmtUtil.longToDate(instructionBean.getCreateTimeStart()));
		}
		if (instructionBean.getCreateTimeEnd() != null) {
			map.put("createTimeEnd", DateFmtUtil.longToDate(instructionBean.getCreateTimeEnd()));
		}
		map.put("loginUnitId", findCurrentOrganizationId());
		map.put("status", status);
		return map;
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
		record.setOperator(findCurrentPersonId());
		instructionService.saveOperationRecord(record);
	}

	/**
	 * 指令model转指令bean
	 * 
	 * @param model
	 *            指令model
	 * @return 指令bean
	 */
	private InstructionBean transformInstructionBean(Instruction model) {
		if (model == null) {
			return null;
		}
		InstructionBean bean = new InstructionBean();
		BeanUtils.copyProperties(model, bean);
		bean.setCreateTime(DateFmtUtil.dateToLong(model.getCreateTime()));
		bean.setRequireFeedbackTime(DateFmtUtil.dateToLong(model.getRequireFeedbackTime()));
		DictionaryItem type = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.ZLLX,
				model.getType(), Constant.ENABLED);
		if (type != null) {
			bean.setTypeName(type.getName());
		}else{
			bean.setTypeName("");
		}
		List<InstructionReceiveSubjectBean> list = new ArrayList<InstructionReceiveSubjectBean>();
		StringBuffer idstr = new StringBuffer();
		idstr.append(",");
		StringBuffer namestr = new StringBuffer();
		for (InstructionReceiveSubject sub : model.getInstructionReceiveSubjects()) {
			Unit unit = unitService.findById(sub.getReceiveSubjectId());
			idstr.append(sub.getReceiveSubjectId()).append(",");
			if (unit != null) {
				InstructionReceiveSubjectBean subBean = new InstructionReceiveSubjectBean();
				BeanUtils.copyProperties(sub, subBean);
				subBean.setReceiveSubjectName(unit.getShortName());
				DictionaryItem status = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.ZLZT,
						sub.getStatus(), Constant.ENABLED);
				if (status != null) {
					subBean.setStatusName(status.getName());
				}
				list.add(subBean);
				namestr.append(unit.getShortName()).append(",");
			}
		}
		bean.setSubs(list);
		if (namestr.length() > 0) {
			bean.setIds(idstr.toString());
			bean.setNames(namestr.toString().substring(0, namestr.length() - 1));
		}else{
			bean.setIds("");
			bean.setNames("");
		}
		return bean;
	}

	/**
	 * 指令接收主体model转指令接收主体bean
	 * 
	 * @param model
	 *            指令接收主体model
	 * @return 指令接收主体bean
	 */
	private InstructionReceiveSubjectBean transformInstructionReceiveSubjectBean(InstructionReceiveSubject model) {
		InstructionReceiveSubjectBean bean = new InstructionReceiveSubjectBean();
		BeanUtils.copyProperties(model, bean);
		InstructionBean instructionBean = new InstructionBean();
		BeanUtils.copyProperties(model.getInstruction(), instructionBean);
		instructionBean.setCreateTime(DateFmtUtil.dateToLong(model.getInstruction().getCreateTime()));
		instructionBean.setRequireFeedbackTime(DateFmtUtil.dateToLong(model.getInstruction().getRequireFeedbackTime()));
		DictionaryItem type = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.ZLLX,
				instructionBean.getType(), Constant.ENABLED);
		if (type != null) {
			instructionBean.setTypeName(type.getName());
		}
		Unit unit = unitService.findById(model.getInstruction().getCreatePeopleDepartmentId());
		if (unit != null) {
			instructionBean.setCreatePeopleDepartmentName(unit.getName());
		}
		DictionaryItem status = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.ZLZT,
				model.getStatus(), Constant.ENABLED);
		if (status != null) {
			bean.setStatusName(status.getName());
		}
		bean.setFeedbackTime(DateFmtUtil.dateToLong(model.getLatestFeedbackTime()));
		InstructionReceiveSubjectSign sign = model.getInstructionReceiveSubjectSign();
		if (sign != null) {
			bean.setSignTime(DateFmtUtil.dateToLong(sign.getSignTime()));
			bean.setSignPeopleName(sign.getSignPeopleName());
		}
		bean.setInstructionBean(instructionBean);
		bean.setReceiveTime(DateFmtUtil.dateToLong(model.getReceiveTime()));
		List<InstructionReceiveSubjectFeedback> feedbacklist = new ArrayList(
				model.getInstructionReceiveSubjectFeedbacks());
		bean.setIsOverTime(0);
		if (!feedbacklist.isEmpty()) {
			InstructionReceiveSubjectFeedback feedback = feedbacklist.get(feedbacklist.size() - 1);
			if (feedback.getFeedbackTime().getTime() > instructionBean.getRequireFeedbackTime()) {
				bean.setIsOverTime(1);
			}
		} else {
			if (new Date().getTime() > instructionBean.getRequireFeedbackTime()) {
				bean.setIsOverTime(1);
			}
		}
		if(null !=instructionBean.getRelatedObjectId() &&  null != criminalPersonService.findCriminalPersonByIdcard(instructionBean.getRelatedObjectId())){
			bean.setIsGwr(true);
		}
		
		if(null !=instructionBean.getRelatedObjectId() && null != highriskPersonService.findByIdCode(instructionBean.getRelatedObjectId())){
			bean.setIsGwr(true);
		}
		
		return bean;
	}

	/**
	 * 操作记录bean转操作记录model
	 * 
	 * @param model
	 *            操作记录bean
	 * @return 操作记录model
	 */
	private OperationRecordBean transformOperationRecordBean(OperationRecord model) {
		OperationRecordBean bean = new OperationRecordBean();
		BeanUtils.copyProperties(model, bean);
		bean.setOperateTime(DateFmtUtil.dateToLong(model.getOperateTime()));
		Unit unit = unitService.findById(model.getOperateUnit());
		if (unit != null) {
			bean.setOperateUnitName(unit.getName());
		}
		Person person = personService.findById(model.getOperator());
		if (person != null) {
			bean.setOperatorName(person.getName());
		}
		return bean;
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

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public InstructionBean getInstructionBean() {
		return instructionBean;
	}

	public void setInstructionBean(InstructionBean instructionBean) {
		this.instructionBean = instructionBean;
	}

	public Pager<InstructionBean> getInstructionBeanPager() {
		return instructionBeanPager;
	}

	public void setInstructionBeanPager(Pager<InstructionBean> instructionBeanPager) {
		this.instructionBeanPager = instructionBeanPager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Pager<InstructionReceiveSubjectBean> getInstructionReceiveSubjectBeanPager() {
		return instructionReceiveSubjectBeanPager;
	}

	public void setInstructionReceiveSubjectBeanPager(
			Pager<InstructionReceiveSubjectBean> instructionReceiveSubjectBeanPager) {
		this.instructionReceiveSubjectBeanPager = instructionReceiveSubjectBeanPager;
	}

	public InstructionReceiveSubjectBean getReceiveSubjectBean() {
		return receiveSubjectBean;
	}

	public void setReceiveSubjectBean(InstructionReceiveSubjectBean receiveSubjectBean) {
		this.receiveSubjectBean = receiveSubjectBean;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public List<InstructionReceiveSubjectFeedbackBean> getFeedbackBeanList() {
		return feedbackBeanList;
	}

	public void setFeedbackBeanList(List<InstructionReceiveSubjectFeedbackBean> feedbackBeanList) {
		this.feedbackBeanList = feedbackBeanList;
	}

	public List<InstructionReceiveSubjectBean> getReceiveSubjectBeanList() {
		return receiveSubjectBeanList;
	}

	public void setReceiveSubjectBeanList(List<InstructionReceiveSubjectBean> receiveSubjectBeanList) {
		this.receiveSubjectBeanList = receiveSubjectBeanList;
	}

	public List<OperationRecordBean> getOperationRecordBeanList() {
		return operationRecordBeanList;
	}

	public void setOperationRecordBeanList(List<OperationRecordBean> operationRecordBeanList) {
		this.operationRecordBeanList = operationRecordBeanList;
	}

	public String getOtherFeedbackContent() {
		return otherFeedbackContent;
	}

	public void setOtherFeedbackContent(String otherFeedbackContent) {
		this.otherFeedbackContent = otherFeedbackContent;
	}

	public List<FileObjectBean> getFileObjectBeans() {
		return fileObjectBeans;
	}

	public void setFileObjectBeans(List<FileObjectBean> fileObjectBeans) {
		this.fileObjectBeans = fileObjectBeans;
	}
	
}
