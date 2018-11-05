package com.taiji.pubsec.szpt.jz.wsclient;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.jz.wsclient.WebServiceClient;

public class UserAuthorizationTest {

	public static void main(String[] args) throws Exception {
		
		//授权
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.add("52011119750109063X");
		parameterValue.add("C5200001037");
		parameterValue.add("1301");
		parameterValue.add("false");
		String result = WebServiceClient.getDateFromWs("http://10.160.25.92:8081/ServiceBus/webService/appAuthorized?wsdl", 
				"receiveUserRoles", "http://webservice.orguser.rpkj.com/", parameterValue);
		System.out.println(result);
		
		
		
		//取消授权
//		List<Object> parameterValue = new ArrayList<Object>();
//		parameterValue.add("52011119750109063X");
//		parameterValue.add("C5200001037");
//		parameterValue.add("1301");
//		parameterValue.add("false");
//		String result = WebServiceClient.getDateFromWs("http://10.160.25.92:8081/ServiceBus/webService/appAuthorized?wsdl", 
//				"receiveUserRoles", "http://webservice.orguser.rpkj.com/", parameterValue);
//		System.out.println(result);
		
		//组件已授权用户查询
//		List<Object> parameterValue = new ArrayList<Object>();
//		parameterValue.add("C5200001037");
//		parameterValue.add(1);
//		String result = WebServiceClient.getDateFromWs("http://10.160.25.238:8001/ORG_USER/webservice/userservice?wsdl", 
//				"getUppAuthor", "http://webservice.orguser.rpkj.com/", parameterValue);
//		System.out.println(result);

	}

}
