package com.taiji.pubsec.szpt.jz.wsclient;

import java.util.ArrayList;
import java.util.List;

import com.taiji.pubsec.szpt.jz.wsclient.WebServiceClient;

public class Test {
	public static void main(String[] args) {
		List<Object> parameterValue = new ArrayList<Object>();
		parameterValue.add("testbatchno");
		parameterValue.add("testidentify");
		parameterValue.add("testdatajson");
		String result = null;
		try {
			result = WebServiceClient.getDateFromWs("http://10.161.172.189:9003/ajgl-web-all/webService/receiveDataFromBusService?wsdl", 
					"receiveDataFromBusService", "http://service.datasynchronizate.pubsec.taiji.com", parameterValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}

}
