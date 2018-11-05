package com.taiji.pubsec.szpt.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期与毫秒转换类
 * @author huangda
 *
 */
public class DateFmtUtil {
	
	public static Date longToDate(Long millis){
		if(millis == null){
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		return c.getTime();
	}
	
	public static Long dateToLong(Date date){
		if(date == null){
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	
	private DateFmtUtil(){
		
	}
}
