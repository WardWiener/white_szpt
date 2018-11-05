package com.taiji.pubsec.szpt.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.service.ISystemManagerService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;

public class TestBase implements ApplicationContextAware{
	
	private ISystemManagerService systemManagerService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.setApplicationContextManually(applicationContext);
		//systemManagerService = (ISystemManagerService)SpringContextUtil.getBean("systemManagerService") ;
	}
	
	public void setLoinInfo(String userName){
		Map<String, Object> map = systemManagerService.findUserDetailsByUserName(userName) ;
		
	    String name = map.get("userName").toString() ;
	    String password = map.get("password").toString() ;
	    boolean enabled = (boolean)map.get("enabled") ;
	    boolean accountNonExpired = (boolean)map.get("accountNonExpired") ; 
	    
	    List<String> authorityCodes = (List<String>)map.get("authorityCodes") ;
	    
	    List<GrantedAuthority> gaList = new ArrayList<GrantedAuthority>(0) ;
		for(int i=0; i<authorityCodes.size(); i++){
			SimpleGrantedAuthority ga = new SimpleGrantedAuthority(authorityCodes.get(i));
			gaList.add(ga) ;
		}
		
		MySecureUser user = new MySecureUser(name, password, enabled, accountNonExpired, true, true, gaList);
		
		user.setUserMap(map) ;
		
		Authentication authen = new UsernamePasswordAuthenticationToken(user, userName, gaList) ;
		SecurityContextHolder.getContext().setAuthentication(authen);
	}
	
	public void setLoinInfoAdmin(){
		Map<String, Object> map = getAdminUser() ;
		
	    String name = map.get("userName").toString() ;
	    String password = map.get("password").toString() ;
	    boolean enabled = (boolean)map.get("enabled") ;
	    boolean accountNonExpired = (boolean)map.get("accountNonExpired") ; 
	    
	    List<String> authorityCodes = (List<String>)map.get("authorityCodes") ;
	    
	    List<GrantedAuthority> gaList = new ArrayList<GrantedAuthority>(0) ;
		for(int i=0; i<authorityCodes.size(); i++){
			SimpleGrantedAuthority ga = new SimpleGrantedAuthority(authorityCodes.get(i));
			gaList.add(ga) ;
		}
		
		MySecureUser user = new MySecureUser(name, password, enabled, accountNonExpired, true, true, gaList);
		
		user.setUserMap(map) ;
		
		Authentication authen = new UsernamePasswordAuthenticationToken(user, name, gaList) ;
		SecurityContextHolder.getContext().setAuthentication(authen);
	}
	
	private Map<String, Object> getAdminUser(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "administrator") ;	
		map.put("password", "123") ;	
		map.put("enabled", true) ;
		map.put("accountNonExpired", true) ;
		
		List<Map<String, String>> roleMaps = new ArrayList<Map<String, String>>();
		Map<String, String> rmap = new HashMap<String, String>(0) ;
		rmap.put("code", "admin") ;
		roleMaps.add(rmap);
		map.put("roles", roleMaps) ;
		
		List<String> authorityCodes = new ArrayList<String>(0) ;
		authorityCodes.add("admin") ;
		map.put("authorityCodes", authorityCodes) ;

		Map<String, Object> org = new HashMap<String, Object>(0) ;
		org.put("id", "123") ;
		org.put("code", "adminOrg") ;
		org.put("name", "adminOrg") ;
		org.put("shortName", "adminOrg") ;
		org.put("type", Unit.class.getName()) ;
		map.put("org", org) ;
		
		Map<String, Object> personMap = new HashMap<String, Object>(0) ;
		personMap.put("id", "123") ;
		personMap.put("name", "爱德敏") ;
		personMap.put("code", "admin") ;
		map.put("person", personMap) ;
		
		return map;
	}
}
