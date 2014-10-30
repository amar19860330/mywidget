package com.metarnet.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeDateUtil {
	private static TimeZone timeZone = TimeZone.getDefault();
	private static int offHour = timeZone.getRawOffset()/3600000;
	/**
	 * yyyy-mm-dd 
	 * @param time
	 * @return
	 */
	public static String getDate(long time){
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(c.getTime());
	}
	
	/**
	 * HH:mm
	 * @param time
	 * @return
	 */
	public static String getSimpltTime(long time){
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("HH:mm");
		return dateformat.format(c.getTime());
	}

	
	/**
	 * yyyy-mm-dd
	 * @param date
	 * @return
	 */
	public static String getDate(Date date){
		
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(date);
	}

	/**
	 * HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String getTime(long time){ 
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("HH:mm:ss");
		return dateformat.format(c.getTime());
	}
	
	/**
	 * HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getTime(Date date){
		SimpleDateFormat dateformat =new SimpleDateFormat("HH:mm:ss");
		return dateformat.format(date);
	}

	/**
	 * yyyy-mm-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String getDateTime(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(c.getTime());
	}
	
	/**
	 * yy/MM/dd
	 * @param time
	 * @return
	 */
	public static String getSimpleMonth(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("yy/MM/dd");
		return dateformat.format(c.getTime());
	}
	
	/**
	 * mm/dd HH
	 * @param time
	 * @return
	 */
	public static String getSimpleDate(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("MM/dd HH");
		return dateformat.format(c.getTime());
	}
	
	/**
	 * mm-dd HH:mm
	 * @param time
	 * @return
	 */
	public static String getSimpleDateTime(long time){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		SimpleDateFormat dateformat =new SimpleDateFormat("MM-dd HH:mm");
		return dateformat.format(c.getTime());
	}
	
	/**
	 * yyyy-mm-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date){
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
	}
	
	/**
	 * 返回timeinmills
	 * @param yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Long getLongDate(String date){
				 
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateformat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	/**
	 * 返回timeinmills
	 * @param yyyy-MM-dd
	 * @return
	 */
	public static Long getLongDay(String date){
		if(date == null || "".equals(date))return null;
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateformat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	/**
	 * 返回timeinmills
	 * @param yyyy-MM
	 * @return
	 */
	public static Long getLongDayNoDay(String date){
		if(date == null || "".equals(date))return null;
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM");
		try {
			return dateformat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	/**
	 * 返回timeinmills
	 * @param yyyy
	 * @return
	 */
	public static Long getLongDayYear(String date){
		if(date == null || "".equals(date))return null;
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy");
		try {
			return dateformat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	/**
	 * 返回timeinmills
	 * @param yyyy-MM-dd HH:mm
	 * @return
	 */
	public static Long getLongDateExcepSec(String date){ 
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return dateformat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0L;
	}
	/**
	 * 取得 yyyy-MM
	 */
	public static String getMonth(long date){
//		date = date/1000;//s
//		date += timeZone.getRawOffset();
//		long s = date % 60;
//		long m = (date % 3600)/60;
//		long h = (date % 86400)/3600 ; 
//		System.out.println("s="+s+",m="+m+",h="+h);
		SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM");
		return  dateformat.format(new Date(date)); 
	}
	/**
	 * 取得date中的年月日时间，忽略时分秒。即xxxx年xx月xx日0时0分0秒
	 * 输入输出参数都是the difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC.
	 */
	public static long getDayTime(long date){
		
		date += offHour*3600000;
		return date - date%86400000 - offHour*3600000; 
	}
	/**
	 * 计算在sTime时间开始持续tTime秒的东西，到目前为止，还有多少秒
	 * sTime是毫秒时间，tTime为秒
	 */
	public static int getRemainSeconds(int tTime, long sTime){
		long df = tTime -  System.currentTimeMillis()/1000 + sTime/1000; 
		return (int) (df<0?0:df);
	}
	/**
	 * t1 - t1的日期差多少日
	 * t1,t2为long型时间
	 */
	public static int dayDiff(long t1, long t2){
//		Calendar c1 = Calendar.getInstance();
//		c1.setTimeInMillis(t1);
//		Calendar c2 = Calendar.getInstance();
//		c2.setTimeInMillis(t2);
//		
//		c1.set(Calendar.HOUR_OF_DAY, 0);
//	    c1.set(Calendar.MINUTE, 0);
//	    c1.set(Calendar.SECOND, 0);
//	    c1.set(Calendar.MILLISECOND, 0);
//	    
//	    c2.set(Calendar.HOUR_OF_DAY, 0);
//	    c2.set(Calendar.MINUTE, 0);
//	    c2.set(Calendar.SECOND, 0);
//	    c2.set(Calendar.MILLISECOND, 0);
//	    
//		long diff = c1.getTimeInMillis() - c2.getTimeInMillis();
//		return (int)(diff /(24 * 3600* 1000));
		
		
//		t1 = t1/1000;
//		t1 = t1 - t1%86400/* - offHour*3600*/;
//		t2 = t2/1000;
//		t2 = t2 - t2%86400/* - offHour*3600*/; 
//		return (int) ((t1-t2)/86400);
		
		t1 = t1 - t1%86400000;
		t2 = t2 - t2%86400000; 
		return (int) ((t1-t2)/86400000);
	}

	public static void main(String[] args)
	{ 
//		System.out.println(getDateTime(TimeDateUtil.getDayTime(System.currentTimeMillis())));
//		System.out.println(getDateTime(TimeDateUtil.getDayTime(System.currentTimeMillis()) + 24*3600*1000));
//		System.out.println(getDateTime(System.currentTimeMillis()));
//		System.out.println(getMonth(System.currentTimeMillis()));
//		long st = System.currentTimeMillis();
//		int i=1000;
//		while(i-->0)
//			dayDiff(1281494058406L,1283214328406L);
//		long et = System.currentTimeMillis();
//		System.out.println(et-st);
//		long d = 1281494058406L;
//		System.out.println(getDateTime(d)); 
//		System.out.println(d);
//		d=d/1000;
//		System.out.println(d);
//		d=d - d%(24*3600) - 8*3600;
//		//d = d&0xFFFFFFF8;	
//		System.out.println(d); 
//		System.out.println(getDateTime(d*1000)); 
//		long d1=1283214328406L;
//		d1 = d1/1000;
//		d1 = d1 - d1%(24*3600) - 8*3600;
//		
//		System.out.println((d-d1)/(24*3600)); 
		System.out.println(dayDiff(1281494058406L,1283214328406L));
//		TimeZone tz = TimeZone.getDefault();
//		System.out.println(tz.getRawOffset()/3600000);
	}
}
