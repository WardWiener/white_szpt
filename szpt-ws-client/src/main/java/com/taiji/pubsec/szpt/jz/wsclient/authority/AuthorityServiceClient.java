package com.taiji.pubsec.szpt.jz.wsclient.authority;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taiji.pubsec.szpt.jz.wsclient.WebServiceClient;
import com.taiji.pubsec.szpt.jz.wsclient.util.DataSyncConstant;

@Service("authServiceClient")
@Transactional(rollbackFor = Exception.class)
public class AuthorityServiceClient {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthorityServiceClient.class);
	
	@Value("${org_user.userservice.wsdl}")
	private String wsAuthorizationUrl;
	@Value("${org_user.userauthorized.methodname.receiveUserRoles}")
	private String wsAuthorizationMethod;
	
	@Value("${org_user.appuser.wsdl}")
	private String wsAppUserUrl;
	@Value("${org_user.appuser.methodname.queryAuthUser}")
	private String wsQueryMethod;
	@Value("${org_user.namespace}")
	private String wsNamespace; 

	/**
	 * 在警综平台上为用户授权
	 * @param idNumber 用户身份证号 填写18位身份证，最后一位为X请大写
	 * @param roleNo 角色编码
	 * @throws Exception 
	 */
	public void authorizeUser(String idNumber, String roleNo) throws Exception {
		LOGGER.debug("ws parameters are : {}, {}, {}", new Object[] { getWsAuthorizationUrl(),
				getWsAuthorizationMethod(), getWsNamespace() });
		List<Object> parameterValue = new ArrayList<>();
		parameterValue.add(idNumber);	//身份证号
		parameterValue.add(DataSyncConstant.JINGZONG_SZPT_UPPNO);//组件编号
		parameterValue.add(roleNo);	//角色编码
		parameterValue.add("false");	//撤销授权 填写true时表示撤销权限
		WebServiceClient.getDateFromWs(getWsAuthorizationUrl(), getWsAuthorizationMethod(),
				getWsNamespace(), parameterValue);
	}


	/**
	 * 在警综平台上取消用户授权
	 * @param idNumber 用户身份证号 填写18位身份证，最后一位位X请大写
	 * @param roleNo 角色编码
	 * @throws Exception
	 */
	public void revokeUserAuthorization(String idNumber, String roleNo) throws Exception {
		List<Object> parameterValue = new ArrayList<>();
		parameterValue.add(idNumber);	//身份证号
		parameterValue.add(DataSyncConstant.JINGZONG_SZPT_UPPNO);//组件编号
		parameterValue.add(roleNo);	//角色编码
		parameterValue.add("true");	//撤销授权 填写true时表示撤销权限
		WebServiceClient.getDateFromWs(getWsAuthorizationUrl(), getWsAuthorizationMethod(),
				getWsNamespace(), parameterValue);
	}
	
	/**
	 * 查询组件已授权用户
	 * @throws Exception 
	 */
	public List<JingZongAuthorization> queryAuthorizedUsers() throws Exception {
		LOGGER.debug("ws parameters are : {}, {}, {}", new Object[] { getWsAppUserUrl(),
				getWsQueryMethod(), getWsNamespace() });
		int totalPage = 1;
		int currentPage = 0;
		Gson gson = new GsonBuilder().create();
		List<JingZongAuthorization> users = new ArrayList<>();
		do {
			List<Object> params = produceParameter(DataSyncConstant.JINGZONG_SZPT_UPPNO, currentPage);
			String result = WebServiceClient.getDateFromWs(getWsAppUserUrl(),
					getWsQueryMethod(), getWsNamespace(), params);
			LOGGER.debug("ws return json:{}", result);
			
			JingZongAuthorizationResponse response =  gson.fromJson(result, JingZongAuthorizationResponse.class);
			users.addAll(response.getData());
			currentPage = response.getPageInfo().getCurrentPage();
			totalPage = response.getPageInfo().getTotalPage();
			currentPage++;
		} while (currentPage < totalPage);
		return users;
	}

	private List<Object> produceParameter(String componentCode, int currentPage) {
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.add(componentCode);	//arg0 组件编号
		parameterValue.add(currentPage);	//arg1 查询页页码
		return parameterValue;
	}
	
	public String getWsNamespace() {
		return wsNamespace;
	}

	public void setWsNamespace(String wsNamespace) {
		this.wsNamespace = wsNamespace;
	}


	public String getWsAuthorizationMethod() {
		return wsAuthorizationMethod;
	}


	public void setWsAuthorizationMethod(String wsAuthorizationMethod) {
		this.wsAuthorizationMethod = wsAuthorizationMethod;
	}


	public String getWsQueryMethod() {
		return wsQueryMethod;
	}


	public void setWsQueryMethod(String wsQueryMethod) {
		this.wsQueryMethod = wsQueryMethod;
	}


	public String getWsAuthorizationUrl() {
		return wsAuthorizationUrl;
	}


	public void setWsAuthorizationUrl(String wsAuthorizationUrl) {
		this.wsAuthorizationUrl = wsAuthorizationUrl;
	}


	public String getWsAppUserUrl() {
		return wsAppUserUrl;
	}


	public void setWsAppUserUrl(String wsAppUserUrl) {
		this.wsAppUserUrl = wsAppUserUrl;
	}
}
