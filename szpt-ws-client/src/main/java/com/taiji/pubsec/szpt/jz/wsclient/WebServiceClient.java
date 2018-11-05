package com.taiji.pubsec.szpt.jz.wsclient;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


/**
 * 调用webService服务的Client
 * @author Administrator
 *
 */
public class WebServiceClient {
	
	static ClassLoader cl = Thread.currentThread().getContextClassLoader();
	
	/**
	 * 访问webservice服务
	 * @param wsdl 服务wsdlUrl
	 * @param method 服务方法名称
	 * @param targetNameSpace 服务命名空间
	 * @param parameterValueList 参数集合，参数按位匹配
	 * @return json
	 * @throws Exception
	 */
	public static String getDateFromWs(String wsdl, String method, String targetNameSpace, List<Object> parameterValueList) throws Exception{
		
		//重置上下文信息，否则同一个方法内多次调用client时会报错
		Thread.currentThread().setContextClassLoader(cl);
		
		JaxWsDynamicClientFactory dcf =  JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdl);
		
		QName opName = new QName(targetNameSpace, method);
		Object[] Objects = client.invoke(opName, parameterValueList.toArray());
		return Objects[0].toString();
	}

}
