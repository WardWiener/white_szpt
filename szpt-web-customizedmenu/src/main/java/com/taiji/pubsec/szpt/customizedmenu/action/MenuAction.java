package com.taiji.pubsec.szpt.customizedmenu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.service.ISystemManagerService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.szpt.ajgl.service.ICriminalCaseService;
import com.taiji.pubsec.szpt.customizedmenu.bean.MenuZtreeBean;
import com.taiji.pubsec.szpt.customizedmenu.bean.ModulBean;
import com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu;
import com.taiji.pubsec.szpt.customizedmenu.model.SystemMenu;
import com.taiji.pubsec.szpt.customizedmenu.service.CustomizedMenuService;
import com.taiji.pubsec.szpt.customizedmenu.service.SystemMenuService;
import com.taiji.pubsec.szpt.customizedmenu.util.Util;
import com.taiji.pubsec.szpt.util.Constant;

import net.sf.json.JSONObject;

@Controller("menu")
@Scope("prototype")
public class MenuAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Resource
	private SystemMenuService systemMenuService;
	@Resource
	private IPersonService personService;
	@Resource
	private CustomizedMenuService customizedMenuService;
	
//	@Resource
//	private ISystemManagerService systemManagerService;
	
//	@Resource
//	private SessionUserDetailsUtil sessionUserDetailsUtil;
		
	private String queryStr;
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	public String findCustomizedMenu(){
		return SUCCESS;
	}
	
	public String findAllSystemMenu(){
		List<SystemMenu> systemMenus = systemMenuService.findAll();
		List<SystemMenu> userMenus = getUserMenus(systemMenus);
		List<MenuZtreeBean> menuZtreeBeanLst = systemMenuTrunMenuZtreeBean(userMenus);
		resultMap.put("result",menuZtreeBeanLst);
		return SUCCESS;
	}
	
	public String saveCustomizedMenu(){
		Map<String, Object> parameters = JSONObject.fromObject(read());
		List<String> idLst = (List<String>)parameters.get("ids");
		customizedMenuService.deleteCustomizedMenu(Util.findAccountId(), null);
		for(CustomizedMenu cm : getCustomizedMenus(idLst)){
			customizedMenuService.saveCustomizedMenu(cm);
		}
		return SUCCESS;
	}
	
	private List<CustomizedMenu> getCustomizedMenus(List<String> list){
		List<CustomizedMenu> cmLst = new ArrayList<CustomizedMenu>();
		if(list.size() > 0){
			for(String id : list){
				CustomizedMenu cm = new CustomizedMenu();
				cm.setTargetId(Util.findAccountId());
				cm.setSystemMenu(systemMenuService.findById(id));
				cmLst.add(cm);
			}
		}
		return cmLst;
	}
	
	private List<SystemMenu> getUserMenus(List<SystemMenu> systemMenus){
		
		String loginUserName = SessionUserDetailsUtil.getLoginUserName();
		System.out.println(loginUserName);
		
		List<SystemMenu> menus = new ArrayList<>();
		for(SystemMenu menu : systemMenus){
			boolean flag = SessionUserDetailsUtil.isResourceAccess(menu.getUrl());
			if(flag){
				if(null != menu.getSuperSystemMenu() && !menus.contains(menu.getSuperSystemMenu())){
					menus.add(menu.getSuperSystemMenu());
				}
				menus.add(menu);
			}
		}
		return menus;
	}
	
	private List<MenuZtreeBean> systemMenuTrunMenuZtreeBean(List<SystemMenu> systemMenus){
		List<MenuZtreeBean> menuZtreeBeans = new  ArrayList<MenuZtreeBean>();
		List<CustomizedMenu> customizedMenus = customizedMenuService.findAllModuleUrlByUserId(Util.findAccountId(), null);
		List<ModulBean> modulBeanLst = Util.customizedMenuTurnModulBean(customizedMenus);		if(systemMenus.size() > 0){
			for(SystemMenu s : systemMenus){
				MenuZtreeBean m = new MenuZtreeBean();
				m.setId(s.getId());
				if(null != s.getSuperSystemMenu()){
					m.setpId(s.getSuperSystemMenu().getId());
				}
				if(s.getSystemMenus().size() > 0){
					m.setOpen(true);
				
				}else{
					m.setOpen(false);
				}
				if(null == s.getUrl() || "".equals(s.getUrl())){
					m.setNocheck(true);
				}else{
					m.setNocheck(false);
				}
				
				m.setName(s.getName());
				if(s.getType().equals(Constant.MENU_TYPE.MENU_TYPE_MR.getValue())){
					m.setIsclick(true);
					m.setChecked(true);
				}else{
					m.setIsclick(false);
					m.setChecked(false);
				}
				if(modulBeanLst.size() > 0){
					for(ModulBean mb : modulBeanLst){
						if(mb.getId().equals(s.getId())){
							m.setChecked(true);	
						}
					}
				}
				menuZtreeBeans.add(m);
			} 
		}
		return menuZtreeBeans;
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
