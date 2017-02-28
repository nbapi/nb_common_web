package com.elong.nb.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {
	public static int compareTo(Date date1,Date date2){
		return date1.compareTo(date2);
	}
	/**
	 * HotelListAdapter使用
	 * @param date
	 * @return
	 */
	public static long getTimeTotalSeconds(Date date){
		return date.getTime()/1000;
	}
	/**
	 * date > now() == 1 datetime 大于当前时间 返回>0
	 * date < now() == -1 datetime 小于当前时间 返回<0
	 * @param date1
	 * @return
	 */
	public static int compareNow(Date date1){
		return date1.compareTo(new Date());
	}
	public static String convertYMDDate(Date date){
		return convertDate(date, "yyyy-MM-dd");
	}
	public static void main(String[] args) {
//		Date date = new Date();
//		System.out.println(convertDate(date, "yy.MM.dd"));
//		StringUtils.splitByWholeSeparator(str, separator)
//		ArrayUtils.
		System.out.println(formatDay(new Date()));
	}
	public static String convertDate(Date date,String pattern ){
		SimpleDateFormat df;
		String returnValue = "";
		
		if (date != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}
	public static int compareYesterday(Date date1){
		Date today = new Date();
		Calendar gc = Calendar.getInstance();
		gc.setTime(today);
		gc.add(Calendar.DATE, -1);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return date1.compareTo(gc.getTime());
	}
	/**
	 * 删除Date中时分秒，保留日期
	 * @param date1
	 * @return
	 */
	public static Date formatDay(Date date1){
		Calendar gc = Calendar.getInstance();
		gc.setTime(date1);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();
	}
	public static Calendar getCalendar(Date date1){
		Calendar gc = Calendar.getInstance();
		gc.setTime(date1);
		return gc;
	}
	public static String getYesday(){
		Date today = new Date();
		String ret = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(today);
		gc.add(Calendar.DATE, -1);
		ret = df.format(gc.getTime());
		return ret;
	}
	public static Date convertYMDString(String strDate){
		SimpleDateFormat df;
		Date date = null;
		df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return date;
	}
}
