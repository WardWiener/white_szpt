package com.taiji.pubsec.szpt.customizedmenu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.szpt.customizedmenu.bean.ModulBean;
import com.taiji.pubsec.szpt.customizedmenu.model.CustomizedMenu;
import com.taiji.pubsec.szpt.customizedmenu.service.CustomizedMenuService;

public class Util {
	
	 public static List<ModulBean> customizedMenuTurnModulBean(List<CustomizedMenu>  customizedMenus){
		 List<ModulBean> modulBeanLst = new ArrayList<ModulBean>();
		if(customizedMenus.size() > 0){
			for(CustomizedMenu c : customizedMenus){
				ModulBean m = new ModulBean();
				m.setId(c.getSystemMenu().getId());
				m.setName(c.getSystemMenu().getName());
				m.setUrl(c.getSystemMenu().getUrl());
				modulBeanLst.add(m);
			}
		}
		return modulBeanLst;
	}
	 
	public static String findAccountId(){
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
		Map<String, Object> userMap = user.getUserMap() ;
		String accountId = (String)userMap.get("accountId") ;
		return accountId ;
	}
		
}
