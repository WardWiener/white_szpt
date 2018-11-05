package com.taiji.pubsec.szpt.customizedmenu.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.customizedmenu.bean.MenuZtreeBean;
import com.taiji.pubsec.szpt.customizedmenu.bean.ModulBean;
import com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu;
import com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu;
import com.taiji.pubsec.szpt.customizedmenu.service.CustomizedMenuService;
import com.taiji.pubsec.szpt.customizedmenu.service.SystemMenuService;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectSign;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.DateFmtUtil;

import net.sf.json.JSONObject;

@Controller("instructionSend")
@Scope("prototype")
public class InstructionSendAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SystemMenuService systemMenuService;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource
	private IUnitService unitService;
	
	@Resource
	private IInstructionService instructionService;
		
	private String queryStr;
	private Integer start;
	private Integer length;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public String findInstruction(){
		Map<String, Object> map = assemblyMap();
		Pager<InstructionReceiveSubject> page = instructionService.findInstructionsByPageOfReceiveDepartment(null,map,0,3);
		List<InstructionReceiveSubjectBean> list = new ArrayList<>();
		if(page.getPageList().size()>0){
			for(InstructionReceiveSubject irs : page.getPageList()){
				list.add(transformInstructionReceiveSubjectBean(irs));
			}
		}
		resultMap.put("result", list);
		return SUCCESS;
	}
	
	private  Map<String, Object> assemblyMap(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Map<String, Object> map = new HashMap<>();
		map.put("loginUnitId", findCurrentOrganizationId());
		Date endDate = new Date();
		Date startDate = new Date(endDate.getTime() - 60*60*24*3*1000);
		String strDate = df.format(startDate);
		try {
			startDate = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		map.put("createTimeStart", startDate);
		map.put("createTimeEnd", endDate);
		//map.put("status", Instruction.ZLZT_DQS);
		return map;
	}
	
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
		DictionaryItem type = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(com.taiji.pubsec.szpt.instruction.action.util.Constant.ZLLX,
				instructionBean.getType(), com.taiji.pubsec.szpt.instruction.action.util.Constant.ENABLED);
		if (type != null) {
			instructionBean.setTypeName(type.getName());
		}
		Unit unit = unitService.findById(model.getInstruction().getCreatePeopleDepartmentId());
		if (unit != null) {
			instructionBean.setCreatePeopleDepartmentName(unit.getName());
		}
		DictionaryItem status = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(com.taiji.pubsec.szpt.instruction.action.util.Constant.ZLZT,
				model.getStatus(), com.taiji.pubsec.szpt.instruction.action.util.Constant.ENABLED);
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
		return bean;
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


	public Integer getStart() {
		return start;
	}


	public void setStart(Integer start) {
		this.start = start;
	}


	public Integer getLength() {
		return length;
	}


	public void setLength(Integer length) {
		this.length = length;
	}
	
}
