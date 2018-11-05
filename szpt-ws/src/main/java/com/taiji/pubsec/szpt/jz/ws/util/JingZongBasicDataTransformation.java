package com.taiji.pubsec.szpt.jz.ws.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taiji.pubsec.szpt.jz.ws.pojo.JingZongOrganizationResponse;
import com.taiji.pubsec.szpt.jz.ws.pojo.JingZongUserResponse;

public class JingZongBasicDataTransformation {
	
	/**
	 * 警综组织机构json转JingZongOrganizationResponse.class
	 * @param stringResponse
	 * @return
	 */
	public static JingZongOrganizationResponse stringResponse2JingZongOrganizationResponse(String stringResponse) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(stringResponse, JingZongOrganizationResponse.class);
	}
	
	/**
	 * 警综用户json转JingZongUserResponse.class
	 * @param stringResponse
	 * @return
	 */
	public static JingZongUserResponse stringResponse2JingZongUserResponse(String stringResponse) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(stringResponse, JingZongUserResponse.class);
	}
	
	/**
	 * 警综平台组织机构状态转业务系统组织机构状态的数据字典编码
	 * @param jingZongOrganizationState
	 * @return
	 */
	public static String JingZongOrganizationState2BusinessSystemStateCode(String jingZongOrganizationState) {
		String code = "";
		if ("0".equals(jingZongOrganizationState)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_STATE_CODE_ENABLE;
		}
		if ("1".equals(jingZongOrganizationState)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_STATE_CODE_DISABLE;
		}
		return code;
	}
	
	/**
	 * 警综平台用户状态转业务系统用户状态的数据字典编码
	 * @param jingZongUserYhzt
	 * @return
	 */
	public static String JingZongUserYhzt2BusinessSystemStateCode(String jingZongUserYhzt) {
		String code = "";
		if ("0".equals(jingZongUserYhzt)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_STATE_CODE_ENABLE;
		}
		if ("1".equals(jingZongUserYhzt)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_STATE_CODE_DISABLE;
		}
		return code;
	}

	/**
	 * 警综平台用户性别转业务系统人员性别的数据字典编码
	 * @param jingZongUserRyxb
	 * @return
	 */
	public static String JingZongUserRyxb2BusinessSystemSexCode(String jingZongUserRyxb) {
		String code = null;
		if ("0".equals(jingZongUserRyxb)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_SEX_CODE_UNKNOWN; 
		} else if ("1".equals(jingZongUserRyxb)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_SEX_CODE_MALE;
		} else if ("2".equals(jingZongUserRyxb)) {
			code = DataSyncConstant.BUSINESS_SYSTEM_SEX_CODE_FEMALE;
		}
		return code;
	}

	
}
