package com.taiji.pubsec.szpt.surveillance.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date longToDate(Long time){
		if(time==null){
			return null ;
		}
		
		Calendar cal = Calendar.getInstance() ;
		cal.setTimeInMillis(time);
		
		return cal.getTime() ;
	}
}
