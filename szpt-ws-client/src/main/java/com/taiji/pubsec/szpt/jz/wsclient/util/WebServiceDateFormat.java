package com.taiji.pubsec.szpt.jz.wsclient.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebServiceDateFormat {
	
	public static final String DATE_FORMAT = "yyyyMMddHHmmss";
	
	/**
	 * 调用警综平台webservice接口传入时间值的时候需要格式化指定格式字符串
	 * @param date
	 * @return
	 */
	public static String date2str(Date date) {
		String result = "";
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			result = sdf.format(date);
		}
		return result;
	}
	

}
