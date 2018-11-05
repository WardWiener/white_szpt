package com.taiji.pubsec.sso;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;

/**
 * 该Filter用于拦截统一平台登录，获取登录信息
 * @author dxb
 *
 */
public class CasClientFilter implements Filter {
	

	public void destroy() {

	}

	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {
		
		
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse)resp ;
			
			HttpSession session = request.getSession();
			//判断session中是否有username,如果有则到下一个filter,
			//如果没有则判断单点登录客户端是否收到登录后返回的登录信息
			String casUsername = (String) session.getAttribute("casUsername");
			
			if (casUsername == null) {
				//获取单点登录服务器传过来的登录信息
				Assertion assertion = AssertionHolder.getAssertion();
				AttributePrincipal principal = assertion.getPrincipal();
				casUsername=principal.getName();
				Map<String ,Object> attrs=principal.getAttributes();
				/*for(String s:attrs.keySet()){
					System.out.println(s);
				}*/
				
				if(StringUtils.isBlank(casUsername)){
					//没有登陆的跳转
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}else{
					session.setAttribute("casUsername", casUsername);
					session.setAttribute("casAttrs", attrs);
				}
			}
			
			fc.doFilter(req, resp);

		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
