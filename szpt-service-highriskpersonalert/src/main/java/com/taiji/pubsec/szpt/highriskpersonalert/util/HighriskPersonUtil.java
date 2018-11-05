package com.taiji.pubsec.szpt.highriskpersonalert.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author wangfx
 *
 */
public class HighriskPersonUtil {
	
	public static String highriskPersonTrackDateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd日 HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String highriskPersonTrackTimeFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(date);
	}
	
	public static boolean isSameDate(Date oneDate, Date anotherDate) {
		Calendar oneCal = Calendar.getInstance();
		oneCal.setTime(oneDate);

	       Calendar anotherCal = Calendar.getInstance();
	       anotherCal.setTime(anotherDate);

	       boolean isSameYear = oneCal.get(Calendar.YEAR) == anotherCal.get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear && oneCal.get(Calendar.MONTH) == anotherCal.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth && oneCal.get(Calendar.DAY_OF_MONTH) == anotherCal.get(Calendar.DAY_OF_MONTH);
	      
	       return isSameDate;
	}
	
	public static String date2str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

}
