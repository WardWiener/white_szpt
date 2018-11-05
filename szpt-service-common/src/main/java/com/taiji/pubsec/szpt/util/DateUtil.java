package com.taiji.pubsec.szpt.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date fmtDay(Date day){
		Calendar cal = Calendar.getInstance() ;
		cal.setTime(day);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return cal.getTime() ;
	}

	public static Date endDayAddOne(Date day){
		Calendar cal = Calendar.getInstance() ;
		cal.setTime(day);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		
		return cal.getTime() ;
	}
	
	public static Date longToDate(Long time){
		Calendar cal = Calendar.getInstance() ;
		cal.setTimeInMillis(time);
		return cal.getTime() ;
	}
	
	public static Date[] getHbDayRange(Date start, Date end){
		
		Calendar calStart = Calendar.getInstance() ;
		calStart.setTime(start);
		
		calStart.set(Calendar.HOUR_OF_DAY, 0);
		calStart.set(Calendar.MINUTE, 0);
		calStart.set(Calendar.SECOND, 0);
		
		Calendar calEnd = Calendar.getInstance() ;
		calEnd.setTime(end);
		
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		
		Long time1 = calStart.getTimeInMillis();                  
		Long time2 = calEnd.getTimeInMillis(); 
		Long step = (time2-time1)/(1000*3600*24);		
        Integer hb_step= Integer.parseInt(String.valueOf(step));
		
	//	Integer hb_step = calEnd.get(Calendar.DAY_OF_MONTH) - calStart.get(Calendar.DAY_OF_MONTH) ;
		
		calStart.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH) - hb_step);
		calEnd.set(Calendar.DAY_OF_MONTH, calEnd.get(Calendar.DAY_OF_MONTH) - hb_step);
		
		return new Date[]{calStart.getTime(), calEnd.getTime()};
	}
}
