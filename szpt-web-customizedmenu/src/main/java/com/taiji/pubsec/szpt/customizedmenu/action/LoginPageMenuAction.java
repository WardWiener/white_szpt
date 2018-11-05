package com.taiji.pubsec.szpt.customizedmenu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdPushService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.customizedmenu.bean.ModulBean;
import com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu;
import com.taiji.pubsec.szpt.customizedmenu.service.CustomizedMenuService;
import com.taiji.pubsec.szpt.customizedmenu.util.Util;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;

@Controller("loginPage")
@Scope("prototype")
public class LoginPageMenuAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private String queryStr;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	@Resource
	private ICriminalCaseService criminalCaseService ;

	@Resource
	private IOrganizationService organizationService;
	
	@Resource
	private CustomizedMenuService customizedMenuService;
	
	public String findAllUserUsedModuleUrl(){
		List<CustomizedMenu> customizedMenus = customizedMenuService.findAllModuleUrlByUserId(Util.findAccountId(), null);
		List<ModulBean> modulBeanLst = Util.customizedMenuTurnModulBean(customizedMenus);
		resultMap.put("result", modulBeanLst);
		return SUCCESS;
	}
	

	public String deleteModuleUrl(){
		
		
		return SUCCESS;
	}

	public String createNewIn(){
		Instruction in = new Instruction();
		InstructionReceiveSubject irs1 = new InstructionReceiveSubject();
		irs1.setInstruction(in);
		irs1.setId("irs1Id");
		irs1.setReceiveSubjectId("c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c");
		irs1.setReceiveSubjectType(Organization.class.getName());
		in.getInstructionReceiveSubjects().add(irs1);
		in.setContent("野生的范鑫出现了！");
		in.setCreateTime(new Date());
		in.setCreatePeopleDepartmentId("c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c");
		in.setId("inId");
		for(InstructionReceiveSubject irs : in.getInstructionReceiveSubjects()){
			CometdPushService cometdPushService = (CometdPushService)SpringContextUtil.getBean("defaultCometdPushService");
			Organization receiveUnit = organizationService.findOrganizationById(irs.getReceiveSubjectId());
			Organization org = organizationService.findOrganizationById(in.getCreatePeopleDepartmentId());
			DefaultHintMsg msgForPC = new DefaultHintMsg("{businessType:\"AlertMessage\", context:\"" 
				+ in.getContent() + "\", time:\"" + (in.getCreateTime().getHours()) + ":" + in.getCreateTime().getMinutes() 
				+ "\", unit:\"" + org.getShortName() + "\", id:\"" + irs.getId() + "\", existingTime:\"60000\"}");
			cometdPushService.pushHint(receiveUnit.getCode() + "PC", "/service/receive/hint", msgForPC);
		}
		return SUCCESS;
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
	
	
}
