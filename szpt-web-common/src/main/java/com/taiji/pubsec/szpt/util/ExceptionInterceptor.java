package com.taiji.pubsec.szpt.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ExceptionInterceptor extends MethodFilterInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6526749429735083373L;

	@Override
	protected String doIntercept(ActionInvocation actioninvocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		try{
			return actioninvocation.invoke();
		}catch(Exception e){
			e.printStackTrace();
			return "errorException" ;
		}
		
		
	}

}
