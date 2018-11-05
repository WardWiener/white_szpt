package com.taiji.pubsec.szpt.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taiji.pubsec.businesscomponents.authority.model.Authority;
import com.taiji.pubsec.businesscomponents.authority.model.Role;
import com.taiji.pubsec.businesscomponents.organization.model.Account;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.service.impl.SystemManagerServiceImpl;

public class SzptSystemManagerServiceImpl extends SystemManagerServiceImpl{
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> findUserDetailsByUserName(String userName){
		String xql = "select u from Account as u where u.accountName=:userName" ;
		Map<String, Object> xqlMap  =new HashMap<String, Object>(0) ;
		xqlMap.put("userName", userName) ;	
		List<Account> list = super.jpaDao.findAllByParams(Account.class, xql, xqlMap) ;
		
		Map<String, Object> map  =new HashMap<String, Object>(0) ;
		if(list.size()>0){
			Account entity = list.get(0) ;
			Person person = entity.getPerson() ;
			
			map.put("userName", entity.getAccountName()) ;
			map.put("password", entity.getPassword()) ;
			
			boolean enabled = true ;
			boolean accountNonExpired = true ;

			if(entity.getStatus().equals("0")){
				enabled = false ;
			}
			
			Date start = entity.getStartDate() ;
			Date end = entity.getEndDate() ;
			
			Date nowDate = new Date() ;
			
			if(start!=null && nowDate.before(start)){
				accountNonExpired = false ;
			}
			
			if(end!=null && nowDate.after(end)){
				accountNonExpired = false ;
			}
			
			map.put("enabled", enabled) ;
			map.put("accountNonExpired", accountNonExpired) ;
			
			List<Role> roles = this.findRolesByUserName(userName) ;
			
			List<Map<String, String>> roleMaps = new ArrayList<Map<String, String>>(0) ;
			List<String> authorityCodes = new ArrayList<String>(0) ;
			for(int i=0; i<roles.size(); i++){
				Map<String, String> rmap = new HashMap<String, String>(0) ;
				Role re = roles.get(i) ;
				rmap.put("code", re.getRoleCode()) ;
				List<Authority> auths = re.getAuthorities() ;
				for(int j=0; j<auths.size(); j++){
					authorityCodes.add(auths.get(j).getAuthorityCode()) ;
				}
				roleMaps.add(rmap) ;
			}
			
			//TODO 获取额外的信息
			
			if(person!=null){
				Map<String, Object> org = new HashMap<String, Object>(0) ;
				org.put("id", person.getOrganization().getId()) ;
				org.put("code", person.getOrganization().getCode()) ;
				org.put("name", person.getOrganization().getName()) ;
				org.put("shortName", person.getOrganization().getShortName()) ;
				org.put("type", person.getOrganization().getClass().getName()) ;
				if(person.getOrganization().getSuperOrg()!=null){
					org.put("superOrgCode", person.getOrganization().getSuperOrg().getCode()) ;
				}
				map.put("org", org) ;
				
				Map<String, Object> personMap = new HashMap<String, Object>(0) ;
				personMap.put("id", person.getId()) ;
				personMap.put("name", person.getName()) ;
				personMap.put("code", person.getCode()) ;
				personMap.put("idNumber", person.getIdNumber());
				map.put("person", personMap) ;
			}
			

			map.put("roles", roleMaps) ;
			map.put("authorityCodes", authorityCodes) ;
			
			return map ;
		}else{
			return null ;
		}
	}

}
