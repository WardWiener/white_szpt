package com.taiji.pubsec.szpt.httpinterface.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IAccountService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.httpinterface.action.bean.FeedBackActionBean;
import com.taiji.pubsec.szpt.httpinterface.action.bean.InstructionPDABean;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectSign;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * 指令PDAAction
 * @author chengkai
 */
@Controller("instructionForPDAAction")
@Scope("prototype")
public class InstructionForPDAAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private IInstructionService instructionService;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private IUnitService unitService;
	
	@Resource
	private IAccountService accountService;
	
	private String accountName;	//用户名
	
	private Date queryTime; //查询时间
	
	private int size;	//页面大小
	
	private String flag; 	//
	
	private String id;	//指令id
	
	private String signPeopleId;	//签收人id(反馈人id)
	
	private String content;	//反馈内容
	
	private String unitId;	//单位id
	
	private String signPeopleName;	//签收人姓名
	
	private List<InstructionPDABean> instructionBeans = new ArrayList<InstructionPDABean>();//指令信息list
	
	private List<FeedBackActionBean> feedBackBeans = new ArrayList<FeedBackActionBean>();//反馈信息list
	
	private static Logger LOGGER = LoggerFactory
			.getLogger(InstructionForPDAAction.class);
	
	//通过用户名和时间查询新的已办的指令消息
	public String findNewDoneInstructionByConditions(){
		List<Instruction> instructions = instructionService.findNewInstructionByConditions(accountName, queryTime, "1");
		for(Instruction i: instructions){
			InstructionPDABean bean = this.instructionToBean(i);
			instructionBeans.add(bean);
		}
		return SUCCESS;
	}
	
	//通过用户名和时间查询新的待办的指令消息
	public String findNewToDoInstructionByConditions(){
		List<Instruction> instructions = instructionService.findNewInstructionByConditions(accountName, queryTime, "0");
		for(Instruction i: instructions){
			InstructionPDABean bean = this.instructionToBean(i);
			instructionBeans.add(bean);
		}
		return SUCCESS;
	}
	
	//通过用户名和时间查询相应数量的已有的已办的指令消息 
	public String findOldDoneInstructionByConditions(){
		Map<Boolean,List<Instruction>> map = instructionService.findOldInstructionByConditions(accountName, queryTime, size, "1");
		boolean bl = map.keySet().iterator().next();
		if(bl){
			flag = "true";
		}else{
			flag = "false";
		}
		List<Instruction> instructions = map.get(bl);
		for(Instruction i: instructions){
			InstructionPDABean bean = this.instructionToBean(i);
			instructionBeans.add(bean);
		}
		return SUCCESS;
	}
	
	//通过用户名和时间查询相应数量的已有的待办的指令消息 
	public String findOldToDoInstructionByConditions(){
		Map<Boolean,List<Instruction>> map = instructionService.findOldInstructionByConditions(accountName, queryTime, size, "0");
		boolean bl = map.keySet().iterator().next();
		if(bl){
			flag = "true";
		}else{
			flag = "false";
		}
		List<Instruction> instructions = map.get(bl);
		for(Instruction i: instructions){
			InstructionPDABean bean = this.instructionToBean(i);
			instructionBeans.add(bean);
		}
		return SUCCESS;
	}
	
	//签收指令
	public String signInstruction(){
		try {
			InstructionReceiveSubjectSign sign = new InstructionReceiveSubjectSign();
			Person person = personService.findById(signPeopleId);
			sign.setSignPeopleId(signPeopleId);
			sign.setSignPeopleName(person.getId());
			sign.setSignTime(new Date());
			InstructionReceiveSubject receive = instructionService.findReceiveSubjectByInstructionIdAndUnitId(id, person.getOrganization().getId());
			instructionService.signInstruction(sign, receive.getId());
			flag = "true";
		} catch (Exception e) {
			LOGGER.debug("指令签收失败", e);
			flag = "false";
		}
		
		return SUCCESS;
	}
	
	//反馈指令
	public String feedbackInstruction(){
		try {
			InstructionReceiveSubjectFeedback feedBack = new InstructionReceiveSubjectFeedback();
			Person person = personService.findById(signPeopleId);
			feedBack.setFeedbackContent(content);
			feedBack.setFeedbackTime(new Date());
			feedBack.setFeedbackPeopleId(signPeopleId);
			feedBack.setFeedbackPeopleName(person.getName());
			InstructionReceiveSubject receive = instructionService.findReceiveSubjectByInstructionIdAndUnitId(id, person.getOrganization().getId());
			instructionService.feedbackInstruction(feedBack, receive.getId());
			flag = "true";
		} catch (Exception e) {
			LOGGER.debug("指令反馈失败", e);
			flag = "false";
		}
		
		return SUCCESS;
	}
	
	//通过指令id和反馈人id查询相应的反馈list
	public String findFeedBacksByInstructionIdAndPersonId(){
		List<InstructionReceiveSubjectFeedback> feedBacks = instructionService.findFeedBacksByInstructionIdAndPersonId(id, signPeopleId);
		for(InstructionReceiveSubjectFeedback feedback: feedBacks){
			FeedBackActionBean bean = this.feedBackToBean(feedback);
			feedBackBeans.add(bean);
		}
		return SUCCESS;
	}
	
	//指令model转bean
	@SuppressWarnings({ "rawtypes" })
	private InstructionPDABean instructionToBean(Instruction instruction){
		InstructionPDABean bean = new InstructionPDABean();
		Account account = accountService.findAccountByName(accountName);
		boolean flag = instructionService.findInstructionSignByInstructionIdAndUnitId(instruction.getId(), account.getPerson().getOrganization().getId());
		if(flag){
			bean.setSignStatus("true");
		}else{
			bean.setSignStatus("false");
		}
		bean.setId(instruction.getId());
		bean.setCreateTime(instruction.getCreateTime());
		bean.setContent(instruction.getContent());
		bean.setCreatePeopleDepartmentName(unitService.findById(instruction.getCreatePeopleDepartmentId()).getShortName());
		bean.setSource("实战平台");		
		bean.setType(instruction.getType());
		String typeContent = instruction.getTypeContent();
		JSONObject jsonObject = JSONObject.fromObject(typeContent);
		Map<String, String> typeMap = new HashMap<String, String>();
		Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());  
	           String value = (String) jsonObject.get(key);  
	           typeMap.put(key, value);  
	       }  
		bean.setName(typeMap.get("name"));
		bean.setIdCardNo(typeMap.get("idcode"));
		bean.setAddress(typeMap.get("personLocation"));
		return bean;
	}
	
	//反馈model转bean
	private FeedBackActionBean feedBackToBean(InstructionReceiveSubjectFeedback feedBack){
		FeedBackActionBean bean = new FeedBackActionBean();
		bean.setFeedbackContent(feedBack.getFeedbackContent());
		bean.setFeedbackPeopleId(feedBack.getFeedbackPeopleId());
		bean.setFeedbackPeopleName(personService.findById(feedBack.getFeedbackPeopleId()).getName());
		bean.setFeedbackTime(feedBack.getFeedbackTime());
		bean.setId(feedBack.getId());
		return bean;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<InstructionPDABean> getInstructionBeans() {
		return instructionBeans;
	}

	public void setInstructionBeans(List<InstructionPDABean> instructionBeans) {
		this.instructionBeans = instructionBeans;
	}

	public String getSignPeopleId() {
		return signPeopleId;
	}

	public void setSignPeopleId(String signPeopleId) {
		this.signPeopleId = signPeopleId;
	}

	public String getSignPeopleName() {
		return signPeopleName;
	}

	public void setSignPeopleName(String signPeopleName) {
		this.signPeopleName = signPeopleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<FeedBackActionBean> getFeedBackBeans() {
		return feedBackBeans;
	}

	public void setFeedBackBeans(List<FeedBackActionBean> feedBackBeans) {
		this.feedBackBeans = feedBackBeans;
	}
	
}
