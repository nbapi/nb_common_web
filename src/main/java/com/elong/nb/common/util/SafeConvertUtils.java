/**   
 * @(#)SafeConvertUtils.java	2016年8月23日	下午3:55:18	   
 *     
 * Copyrights (C) 2016艺龙旅行网保留所有权利
 */
package com.elong.nb.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 系统类型安全转换处理类
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2016年8月23日 下午3:55:18   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public class SafeConvertUtils {

	/** 
	 * 将价格向上转化为整数
	 *
	 * @param price
	 * @param convertType (0=不处理,1=Round,2=Ceil)
	 * @return
	 */
	public static double toIntegerPrice(Double price, int convertType) {
		switch (convertType) {
		case 0:
			;
			break;
		case 1:
			BigDecimal b = new BigDecimal(price);
			price = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
			break;
		case 2:
			price = Math.ceil(price);
			break;
		default:
			break;
		}
		return price;
	}
	// / 转换酒店艺龙酒店id 目前酒店id是8位
		public static String ToHotelId(long hotelid) {
			return FixLengthWithZero(hotelid, 8);
		}

		public static int toInt32(String obj) {
			return toInt32(obj, 0);
		}

		public static int toInt32(String obj, int defaultValue) {
			if (StringUtils.isBlank(obj) || !NumberUtils.isDigits(obj)) {
				return defaultValue;
			} else {
				return Integer.parseInt(obj.toString());
			}
		}

		public static long toInt64(String s) {
			return toInt64(s, 0);
		}

		public static long toInt64(String s, long defaultValue) {
			if (NumberUtils.isDigits(s)) {
				return Long.valueOf(s);
			} else {
				return defaultValue;
			}
		}

		// / 字符串前边加0 满足现有的id体系
		public static String FixLengthWithZero(long id, int fixLength) {
			String str = String.valueOf(id);
			int len = fixLength - str.length();
			if (len > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < len; i++) {
					sb.append("0");
				}
				return sb.append(str).toString();
			}
			return str;
		}

		public static String toCityId(long cityId) {
			return FixLengthWithZero(cityId, 4);
		}

		public static String ToRoomId(long roomid) {
			return FixLengthWithZero(roomid, 4);
		}

		public static Date toDateTime(String datetime) {
			if (StringUtils.isBlank(datetime)) {
				// 如果出现异常，则返回当前日期
				Date now = org.apache.commons.lang3.time.DateUtils.truncate(
						new Date(), Calendar.DATE);
				return now;
			}
			return toBizDateTime(datetime);

		}

		private static Date toBizDateTime(String datetime) {
			try {
				Date parseDate = org.apache.commons.lang3.time.DateUtils.parseDate(
						datetime, new String[] { "yyyy-MM-dd'T'HH:mm:ss'+08:00'",
								"yyyy-MM-dd HH:mm:ss", "yyyy.MM.dd.HH.mm.ss",
								"yyyy.MM.dd.HH:mm:ss", "yyyy-MM-dd", "yyyy.MM.dd",
								"MM/dd/yyyy", "yyyy年MM月dd日", "HH.mm.ss",
								"HH:mm:ss", "HH.mm", "HH:mm" });
				// 有的时候只有时间,则日期数据提取当天日期
				if (parseDate.getYear() == 70) {	//表示1970年
					Date date = org.apache.commons.lang3.time.DateUtils.truncate(
							new Date(), Calendar.DATE);
					parseDate.setYear(date.getYear());
					parseDate.setMonth(date.getMonth());
					// 因为有的时候，传来次日会大于24小时，而转换日期后，会是1970年的第二天，你懂的
					if (parseDate.getDate() == 2) {
						parseDate.setDate(date.getDate() + 1);
					} else {
						parseDate.setDate(date.getDate());
					}
				}
				// 有的次日时间会大于24小时
				return parseDate;
			} catch (ParseException e) {
				e.printStackTrace();
				Date date = org.apache.commons.lang3.time.DateUtils.truncate(
						new Date(), Calendar.DATE);
				return date;
			}
		}

}
